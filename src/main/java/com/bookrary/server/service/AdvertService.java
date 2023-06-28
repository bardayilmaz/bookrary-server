package com.bookrary.server.service;

import com.bookrary.server.entity.*;
import com.bookrary.server.exception.BusinessException;
import com.bookrary.server.exception.ErrorCode;
import com.bookrary.server.model.request.AdvertRequest;
import com.bookrary.server.model.response.AdvertResponse;
import com.bookrary.server.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class AdvertService {

    private final AdvertRepository advertRepository;
    private final AuthorRepository authorRepository;
    private final SaleRepository saleRepository;
    private final CityRepository cityRepository;
    private final LibraryRepository libraryRepository;
    private final UserRepository userRepository;
    private final FileRepository fileRepository;

    public Page<AdvertResponse> getAdverts(Pageable pageable, String name, String cityId) {
        List<City> cities = cityId != null ? Arrays.asList(cityRepository.findById(cityId)
                .orElseThrow(() -> new BusinessException("City not found", ErrorCode.resource_missing))) :
                cityRepository.findAll();
        List<Library> libraries = libraryRepository.findAllByCityIn(cities);
        List<User> sellers = userRepository.findAllByLibraryIn(libraries);
        return advertRepository.findByTitleContainingIgnoreCaseAndSellerIn(pageable, (name != null ? name : ""), sellers).map(AdvertResponse::fromEntity);
    }

    public AdvertResponse getAdverts(String bookId) {
        return AdvertResponse.fromEntity(advertRepository.findById(bookId)
                .orElseThrow(() -> new BusinessException("Book not found", ErrorCode.resource_missing)));
    }

    public AdvertResponse addAdvert(AdvertRequest advertRequest) {
        Advert advert = fromRequest(new Advert(), advertRequest);
        advertRepository.save(advert);
        return AdvertResponse.fromEntity(advert);
    }

    public AdvertResponse updateAdvert(AdvertRequest advertRequest, String bookId) {
        Advert advert = advertRepository.findById(bookId)
                .orElseThrow(() -> new BusinessException("Book not found.", ErrorCode.resource_missing));

        if(saleRepository.existsByAdvert(advert)) {
            throw new BusinessException("Sold books can not be updated", ErrorCode.validation);
        }
        fromRequest(advert, advertRequest);
        advertRepository.save(advert);
        return AdvertResponse.fromEntity(advert);
    }

    private Advert fromRequest(Advert newAdvert, AdvertRequest advertRequest) {
        newAdvert.setAdvertStatus(advertRequest.getAdvertStatus());
        newAdvert.setPrice(advertRequest.getPrice());
        newAdvert.setLanguage(advertRequest.getLanguage());
        newAdvert.setAuthor(authorRepository.findById(advertRequest.getAuthorId())
                .orElseThrow(() -> new BusinessException("Author not found", ErrorCode.resource_missing)));
        newAdvert.setType(advertRequest.getType());
        newAdvert.setTitle(advertRequest.getTitle());
        newAdvert.setPageCount(advertRequest.getPageCount());
        newAdvert.setPublisherName(advertRequest.getPublisherName());
        newAdvert.setPublicationDate(advertRequest.getPublicationDate());
        newAdvert.setBookPicture(getFile(newAdvert, advertRequest.getBookPictureId()));
        return newAdvert;
    }

    private File getFile(Advert advert, String fileId) {
        File file = fileRepository.findById(fileId)
                .orElseThrow(() -> new BusinessException("File does not exists", ErrorCode.resource_missing));
        if (advert.getBookPicture() != null) {
            if (!advert.getBookPicture().getId().equals(fileId) && advertRepository.existsByBookPicture(file)) {
                throw new BusinessException("File is already used by another advert!", ErrorCode.validation);
            }
        } else {
            if (advertRepository.existsByBookPicture(file)) {
                throw new BusinessException("File is already used by another advert!", ErrorCode.validation);
            }
        }
        return file;
    }

}
