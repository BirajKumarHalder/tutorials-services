package com.bkh.tutorials.repo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "topics")
@Getter
@Setter
@NoArgsConstructor
public class TopicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "topic_id")
    private Integer topicId;

    @Column(name = "topic_name")
    private String topicName;

    @Column(name = "topic_order")
    private Integer topicOrder;

    @Column(name = "topic_content")
    private String topicContent;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id")
    private CourseEntity course;

}
