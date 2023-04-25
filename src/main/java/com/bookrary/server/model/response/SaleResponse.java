package com.bookrary.server.model.response;

import com.bookrary.server.entity.Sale;
import com.bookrary.server.entity.SaleStatus;
import lombok.*;

import java.time.ZonedDateTime;

@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class SaleResponse {

    private String id;
    private ZonedDateTime created;
    private ZonedDateTime updated;
    private UserResponse seller;
    private UserResponse buyer;
    private AdvertResponse advert;
    private SaleStatus saleStatus;

    public static SaleResponse fromEntity(Sale sale) {
        return SaleResponse.builder()
                .id(sale.getId())
                .created(sale.getCreated())
                .updated(sale.getUpdated())
                .seller(UserResponse.fromEntity(sale.getSeller()))
                .buyer(UserResponse.fromEntity(sale.getBuyer()))
                .advert(AdvertResponse.fromEntity(sale.getAdvert()))
                .build();

    }
}
