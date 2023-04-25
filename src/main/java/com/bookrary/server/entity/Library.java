package com.bookrary.server.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "library")
@Entity
@Getter
@Setter
public class Library extends BaseEntity {

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @Column(name = "address")
    private String address;

}
