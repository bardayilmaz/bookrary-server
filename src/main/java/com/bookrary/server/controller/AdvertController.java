package com.bookrary.server.controller;

import com.bookrary.server.model.request.AdvertRequest;
import com.bookrary.server.model.response.AdvertResponse;
import com.bookrary.server.service.AdvertService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/advert")
public class AdvertController {

    private final AdvertService advertService;

    @GetMapping
    public Page<AdvertResponse> getAdverts(@ApiIgnore Pageable pageable,
                                           @RequestParam(value = "name", required = false) String name,
                                           @RequestParam(value = "city_id", required = false) String cityId) {
        return advertService.getAdverts(pageable, name, cityId);
    }

    @GetMapping("/{advertId}")
    public AdvertResponse getAdvert(@PathVariable String advertId) {
        return advertService.getAdverts(advertId);
    }

    @PostMapping
    public AdvertResponse addAdvert(@RequestBody @Valid AdvertRequest advertRequest) {
        return advertService.addAdvert(advertRequest);
    }

    @PutMapping("/{advertId}")
    public AdvertResponse updateAdvert(@RequestBody @Valid AdvertRequest advertRequest, @PathVariable String advertId) {
        return advertService.updateAdvert(advertRequest, advertId);
    }
}
