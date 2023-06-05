package com.bookrary.server.controller;

import com.bookrary.server.model.response.SaleResponse;
import com.bookrary.server.service.SaleService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/sale")
@AllArgsConstructor
public class SaleController {

    private final SaleService saleService;

    @GetMapping
    public Page<SaleResponse> getSales(@ApiIgnore Pageable pageable) {
        return saleService.getSales(pageable);
    }
}
