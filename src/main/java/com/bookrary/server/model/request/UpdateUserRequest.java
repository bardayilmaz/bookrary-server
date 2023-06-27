package com.bookrary.server.model.request;

import com.bookrary.server.entity.Library;
import com.bookrary.server.entity.UserRole;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@ToString
public class UpdateUserRequest {

    @NotEmpty(message = "first name can not be empty")
    private String firstName;

    @NotEmpty(message = "last name can not be empty")
    private String lastName;

    @NotNull(message = "Library can not be empty")
    private Library library;

    @NotEmpty(message = "Email can not be empty")
    @Email(message = "Invalid email format")
    private String email;

    @NotEmpty(message = "phone cannot be empty")
    private String phoneNumber;

    @NotNull(message = "User role cannot be empty")
    private UserRole role;
}
