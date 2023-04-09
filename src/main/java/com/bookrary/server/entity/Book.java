package com.bookrary.server.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table(name = "book")
@Entity
@Getter
@Setter
public class Book extends BaseEntity {

    @Column(name = "title")
    private String title;

    @Column(name = "page_count")
    private int pageCount;

    @Column(name = "publication_date")
    private LocalDateTime publicationDate;

    @Column(name = "language")
    @Enumerated(EnumType.STRING)
    private BookLanguage language;

    @Column(name = "book_type")
    @Enumerated(EnumType.STRING)
    private BookType type;

    @Column(name = "publisher_name")
    private String publisherName;

    @ManyToOne
    @JoinTable(name = "author_id")
    private Author author;

    @OneToOne
    @JoinTable(name = "advert_id")
    private Advert advert;
}
