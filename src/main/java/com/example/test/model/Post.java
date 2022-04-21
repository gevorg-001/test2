package com.example.test.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String header;
    String type;
    String phone;
    @ManyToOne
    @JoinColumn(name = "posts")
    private User user;

}
