package com.bookrary.server.model.response;


import com.bookrary.server.entity.Book;
import com.bookrary.server.entity.BookLanguage;
import com.bookrary.server.entity.BookType;
import lombok.*;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class BookResponse {

    private String id;
    private ZonedDateTime created;
    private ZonedDateTime updated;
    private int pageCount;
    private LocalDateTime publicationDate;
    private BookLanguage language;
    private BookType type;
    private String publisherName;
    private AuthorResponse author;

    public static BookResponse fromEntity(Book book) {
        return BookResponse.builder()
                .id(book.getId())
                .created(book.getCreated())
                .updated(book.getUpdated())
                .pageCount(book.getPageCount())
                .publicationDate(book.getPublicationDate())
                .language(book.getLanguage())
                .type(book.getType())
                .publisherName(book.getPublisherName())
                .author(AuthorResponse.fromEntity(book.getAuthor()))
                .build();
    }
}
