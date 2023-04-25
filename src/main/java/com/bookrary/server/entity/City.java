package com.bookrary.server.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "city")
@Entity
@Getter
@Setter
public class City extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "plate_number")
    private int plateNumber;
}
