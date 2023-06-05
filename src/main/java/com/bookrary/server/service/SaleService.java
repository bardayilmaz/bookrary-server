package com.bookrary.server.service;

import com.bookrary.server.entity.Advert;
import com.bookrary.server.entity.Sale;
import com.bookrary.server.entity.User;
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
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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