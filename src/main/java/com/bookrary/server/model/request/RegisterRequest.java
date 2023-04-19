package com.bookrary.server.model.request;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class RegisterRequest {

    private String email;

    private String firstName;

    private String lastName;

    private String password;

    private String passwordAgain;

    private String libraryId;

    private String phoneNumber;
}
