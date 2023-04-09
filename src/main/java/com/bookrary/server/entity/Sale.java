package com.bookrary.server.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "book")
@Entity
@Getter
@Setter
public class Sale extends BaseEntity {

    @ManyToOne
    @JoinTable(name = "seller_id")
    private User seller;

    @ManyToOne
    @JoinTable(name = "buyer_id")
    private User buyer;

    @ManyToOne
    @JoinTable(name = "book_id")
    private Book book;

    @Column(name = "sale_statues")
    @Enumerated(EnumType.STRING)
    private SaleStatus saleStatus;


}
