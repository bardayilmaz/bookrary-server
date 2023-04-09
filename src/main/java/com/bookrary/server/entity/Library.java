package com.bookrary.server.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "book")
@Entity
@Getter
@Setter
public class Library extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

}
