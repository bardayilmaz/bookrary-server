package com.bookrary.server.model.response;

import com.bookrary.server.entity.Advert;
import com.bookrary.server.entity.AdvertStatus;
import lombok.*;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class AdvertResponse {

    private String id;
    private ZonedDateTime created;
    private ZonedDateTime updated;
    private double price;
    private AdvertStatus advertStatus;
    private BookResponse book;

    public static AdvertResponse fromEntity(Advert advert) {
        return AdvertResponse.builder()
                .id(advert.getId())
                .created(advert.getCreated())
                .updated(advert.getUpdated())
                .price(advert.getPrice())
                .advertStatus(advert.getAdvertStatus())
                .book(BookResponse.fromEntity(advert.getBook()))
                .build();
    }

}
