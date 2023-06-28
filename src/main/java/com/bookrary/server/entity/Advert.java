package com.bookrary.server.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table(name = "advert")
@Entity
@Getter
@Setter
public class Advert extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private User seller;

    @Column(name = "price")
    private double price;

    @Column(name = "advert_status")
    @Enumerated(EnumType.STRING)
    private AdvertStatus advertStatus;

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
    @JoinColumn(name = "author_id")
    private Author author;

    @OneToOne
    @JoinColumn(name = "file_id")
    private File bookPicture;

}
