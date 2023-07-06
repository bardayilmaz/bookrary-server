package com.bookrary.server.model.response;

import com.bookrary.server.entity.User;
import com.bookrary.server.entity.UserRole;
import lombok.*;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class UserResponse {

    private String id;
    private ZonedDateTime created;
    private ZonedDateTime updated;
    private String firstName;
    private String lastName;
    private LibraryResponse library;
    private String email;
    private String phoneNumber;
    private UserRole userRole;
    public static UserResponse fromEntity(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .created(user.getCreated())
                .updated(user.getUpdated())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .library(LibraryResponse.fromEntity(user.getLibrary()))
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .userRole(user.getRole())
                .build();
    }
}
