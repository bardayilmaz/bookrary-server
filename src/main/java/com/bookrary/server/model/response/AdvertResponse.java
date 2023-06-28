package com.bookrary.server.model.response;

import com.bookrary.server.entity.Advert;
import com.bookrary.server.entity.AdvertStatus;
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
public class AdvertResponse {

    private String id;
    private ZonedDateTime created;
    private ZonedDateTime updated;
    private double price;
    private AdvertStatus advertStatus;
    private int pageCount;
    private LocalDateTime publicationDate;
    private BookLanguage language;
    private BookType type;
    private String publisherName;
    private String title;
    private AuthorResponse author;
    private FileResponse file;

    public static AdvertResponse fromEntity(Advert advert) {
        return AdvertResponse.builder()
                .id(advert.getId())
                .created(advert.getCreated())
                .updated(advert.getUpdated())
                .price(advert.getPrice())
                .advertStatus(advert.getAdvertStatus())
                .pageCount(advert.getPageCount())
                .publicationDate(advert.getPublicationDate())
                .language(advert.getLanguage())
                .type(advert.getType())
                .publisherName(advert.getPublisherName())
                .title(advert.getTitle())
                .author(AuthorResponse.fromEntity(advert.getAuthor()))
                .file(FileResponse.fromEntity(advert.getBookPicture()))
                .build();
    }

}
