package com.csaba79coder.uploadimagestoreindatabaserenderwiththymeleaf.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "student")
@Setter
@Getter
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String profilePicture;
    private long size;
    private byte [] content;
}