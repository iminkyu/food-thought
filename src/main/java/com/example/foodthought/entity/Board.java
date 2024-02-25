package com.example.foodthought.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Getter
@Table(name = "boards")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String booktitle;

    @Column(nullable = false, length = 255)
    private String bookauthor;

    @Column(nullable = false, length = 255)
    private String bookpublisher;

    @Column(nullable = false, length = 65535)
    private String bookimage;

    @Column(nullable = false, length = 255)
    private String bookcategory;

    @Column(nullable = false, length = 65535)
    private String contents;


}
