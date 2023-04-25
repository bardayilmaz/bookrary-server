package com.bookrary.server.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "sale")
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
    @JoinTable(name = "advert_id")
    private Advert advert;

    @Column(name = "sale_statues")
    @Enumerated(EnumType.STRING)
    private SaleStatus saleStatus;


}
