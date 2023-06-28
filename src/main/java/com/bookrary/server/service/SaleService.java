package com.bookrary.server.service;

import com.bookrary.server.entity.*;
import com.bookrary.server.exception.BusinessException;
import com.bookrary.server.exception.ErrorCode;
import com.bookrary.server.model.request.SaleRequest;
import com.bookrary.server.model.response.SaleResponse;
import com.bookrary.server.repository.AdvertRepository;
import com.bookrary.server.repository.SaleRepository;
import com.bookrary.server.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.ZonedDateTime;
import java.util.List;

@Transactional
@AllArgsConstructor
@Service
public class SaleService {

    private final SaleRepository saleRepository;
    private final AdvertRepository advertRepository;
    private final UserRepository userRepository;

    public Page<SaleResponse> getSales(Pageable pageable) {
        return saleRepository.findAll(pageable).map(SaleResponse::fromEntity);
    }

    public SaleResponse getSale(String saleId) {
        Sale sale = saleRepository.findById(saleId)
                .orElseThrow(() -> new BusinessException("Sale not found", ErrorCode.resource_missing));
        return SaleResponse.fromEntity(sale);
    }

    public SaleResponse addSale(SaleRequest saleRequest) {
        Sale newSale = fromRequest(new Sale(), saleRequest);
        saleRepository.save(newSale);
        return SaleResponse.fromEntity(newSale);
    }

    public SaleResponse updateSale(String saleId, SaleRequest saleRequest) {
        Sale sale = saleRepository.findById(saleId)
                .orElseThrow(() -> new BusinessException("sale not found", ErrorCode.resource_missing));
        if (sale.getSaleStatus().equals(SaleStatus.ACTIVE) && saleRequest.getSaleStatus().equals(SaleStatus.RECEIVED)) {
            if(sale.getCreated().plusHours(3).isBefore(ZonedDateTime.now())) {
                sale.setSaleStatus(SaleStatus.EXPIRED);
                saleRepository.save(sale);
                throw new BusinessException("Time expired! Setting status to expired...", ErrorCode.validation);
            }
        }
        fromRequest(sale, saleRequest);
        saleRepository.save(sale);
        return SaleResponse.fromEntity(sale);
    }

    public List<BookType> getMostSoldGenres() {
        return saleRepository.getMostSoldGenresFromDate(ZonedDateTime.now().minusDays(30));
    }

    private Sale fromRequest(Sale newSale, SaleRequest saleRequest) {
        Advert advert = advertRepository.findById(saleRequest.getAdvertId())
                        .orElseThrow(() -> new BusinessException("advert not found", ErrorCode.resource_missing));

        User buyer = userRepository.findById(saleRequest.getAdvertId())
                .orElseThrow(() -> new BusinessException("advert not found", ErrorCode.resource_missing));

        User seller = userRepository.findById(saleRequest.getAdvertId())
                .orElseThrow(() -> new BusinessException("advert not found", ErrorCode.resource_missing));

        if(!advert.getSeller().getId().equals(seller.getId())) {
            throw new BusinessException("Advert and seller are incorrect", ErrorCode.validation);
        }
        newSale.setSaleStatus(saleRequest.getSaleStatus());
        newSale.setAdvert(advert);
        newSale.setBuyer(buyer);
        newSale.setSeller(seller);
        return newSale;
    }
}
