package com.bookrary.server.model.request;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
@ToString
public class LoginRequest {

    @NotEmpty(message = "Email boş bırakılamaz")
    @Email(message = "Uygun olmayan email formatı")
    private String email;

    @NotEmpty(message = "Şifre boş bırakılamaz")
    private String password;
}
