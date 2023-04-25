package com.bookrary.server.model.response;

import com.bookrary.server.entity.City;
import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class CityResponse {

    private String name;
    private int plateNumber;

    public static CityResponse fromEntity(City city) {
        return CityResponse.builder()
                .name(city.getName())
                .plateNumber(city.getPlateNumber())
                .build();
    }
}
