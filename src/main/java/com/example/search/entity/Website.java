package com.example.search.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "website")
public class Website {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Wid", nullable = false)
    private Integer id;

    @Column(name = "Description", length = 100)
    private String description;

    @Column(name = "Url", length = 200)
    private String url;

    @Column(name = "CreateTime", length = 50)
    private String createTime;

    public Website(String description, String url, String createTime) {
        this.description = description;
        this.url = url;
        this.createTime = createTime;
    }

    public Website() {
    }
}