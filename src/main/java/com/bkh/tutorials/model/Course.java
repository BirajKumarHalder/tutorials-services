package com.bkh.tutorials.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class Course {

    private Integer courseId;
    private String courseName;
    private Integer courseLogoId;
    private File courseLogo;
    private List<Topic> topics;
    private Integer topicCount;

}
