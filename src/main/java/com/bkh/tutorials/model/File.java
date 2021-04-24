package com.bkh.tutorials.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Lob;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class File {

    private Integer fileId;

    private String fileName;

    private String fileType;

    private byte[] data;

}
