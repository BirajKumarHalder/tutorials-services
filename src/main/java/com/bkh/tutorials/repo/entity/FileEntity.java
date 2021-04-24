package com.bkh.tutorials.repo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "files")
@Getter
@Setter
public class FileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_id")
    private Integer fileId;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "file_type")
    private String fileType;

    @Lob
    @Column(name = "data")
    private byte[] data;

    @OneToOne(mappedBy = "courseLogo", orphanRemoval = true, fetch = FetchType.LAZY)
    private CourseEntity course;


}
