package com.example.search.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Table(name = "user")
@Accessors(chain = true)
@Data
public class User {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 10)
    private String name;

    @Column(name = "password", nullable = false, length = 10)
    private String password;


}