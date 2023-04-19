package com.bookrary.server.model.response;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class LoginResponse {

    private String id;

    private String email;

    private String token;
}
