package com.bkh.tutorials.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class Topic {

    private Integer topicId;
    private Integer courseId;
    private String topicName;
    private Integer topicOrder;
    private String topicContent;

}
