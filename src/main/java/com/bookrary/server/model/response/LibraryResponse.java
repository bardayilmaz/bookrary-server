package com.bookrary.server.model.response;

import com.bookrary.server.entity.Library;
import lombok.*;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class LibraryResponse {

    private String id;
    private ZonedDateTime created;
    private ZonedDateTime updated;
    private String name;
    private String address;

    public static LibraryResponse fromEntity(Library library) {
        return LibraryResponse.builder()
                .id(library.getId())
                .created(library.getCreated())
                .updated(library.getUpdated())
                .name(library.getName())
                .address(library.getAddress())
                .build();
    }

}
