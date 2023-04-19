package com.bookrary.server.model.request;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

@Data
@ToString
public class LibraryRequest {

    @NotEmpty(message = "Kütüphane ismi boş bırakılamaz")
    private String name;

    @NotEmpty(message = "Kütüphane adresi boş bırakılamaz")
    private String address;
}
