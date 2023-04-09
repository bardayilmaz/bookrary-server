package com.bookrary.server.model.response;

import com.bookrary.server.entity.AdvertStatus;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class AdvertResponse {

    private String id;
    private LocalDateTime created;
    private LocalDateTime updated;
    private double price;
    private AdvertStatus advertStatus;
    private BookResponse book;

}
