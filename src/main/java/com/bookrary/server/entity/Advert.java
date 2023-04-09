package com.bookrary.server.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "advert")
@Entity
@Getter
@Setter
public class Advert extends BaseEntity {

    @ManyToOne
    @JoinTable(name = "seller_id")
    private User seller;

    @OneToOne
    @JoinTable(name = "book_id")
    private Book book;

    @Column(name = "price")
    private double price;

    @Column(name = "advert_status")
    @Enumerated(EnumType.STRING)
    private AdvertStatus advertStatus;
}
