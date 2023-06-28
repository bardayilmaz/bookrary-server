package com.bookrary.server.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "files")
@Entity
@Getter
@Setter
public class File extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "content_type")
    private String contentType;

    @Column(name = "path")
    private String path;

    @Column(name = "uploader_id")
    private String uploaderId;
}
