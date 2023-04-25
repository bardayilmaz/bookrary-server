package com.bookrary.server.model.request;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@ToString
public class CityRequest {

    @NotEmpty(message = "Şehir ismi boş bırakılamaz")
    private String name;

    @NotNull(message = "plaka boş bırakılamaz")
    private int plateNumber;
}
