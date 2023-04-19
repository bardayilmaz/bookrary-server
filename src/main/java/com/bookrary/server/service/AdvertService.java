package com.bookrary.server.service;

import com.bookrary.server.model.response.AdvertResponse;
import com.bookrary.server.repository.AdvertRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class AdvertService {

    private final AdvertRepository advertRepository;

    public Page<AdvertResponse> getAdverts(Pageable pageable) {
        return advertRepository.findAll(pageable).map(AdvertResponse::fromEntity);
    }
}
