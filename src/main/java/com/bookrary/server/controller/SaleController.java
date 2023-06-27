package com.bookrary.server.controller;

import com.bookrary.server.model.request.SaleRequest;
import com.bookrary.server.model.response.SaleResponse;
import com.bookrary.server.service.SaleService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/{saleId}")
    public SaleResponse getSale(@PathVariable String saleId) {
        return saleService.getSale(saleId);
    }

    @PostMapping
    public SaleResponse addSale(SaleRequest saleRequest) {
        return saleService.addSale(saleRequest);
    }

    @PutMapping("/{saleId}")
    public SaleResponse updateSale(@PathVariable String saleId, @RequestBody SaleRequest saleRequest) {
        return saleService.updateSale(saleId, saleRequest);
    }
}
