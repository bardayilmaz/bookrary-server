package com.bookrary.server.model.response;

import com.bookrary.server.entity.Author;
import lombok.*;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class AuthorResponse {

    private String id;
    private ZonedDateTime created;
    private ZonedDateTime updated;
    private String firstName;
    private String lastName;

    public static AuthorResponse fromEntity(Author author) {
        return AuthorResponse.builder()
                .id(author.getId())
                .created(author.getCreated())
                .updated(author.getUpdated())
                .firstName(author.getFirstName())
                .lastName(author.getLastName())
                .build();
    }
}
