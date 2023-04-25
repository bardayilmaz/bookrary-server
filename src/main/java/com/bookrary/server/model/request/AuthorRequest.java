package com.bookrary.server.model.request;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

@Data
@ToString
public class AuthorRequest {

    @NotEmpty(message = "Yazar ismi boş bırakılamaz")
    private String firstName;

    @NotEmpty(message = "Yazar soy ismi boş bırakılamaz")
    private String lastName;
}
