package com.bkh.tutorials.util;

import com.bkh.tutorials.model.Course;
import com.bkh.tutorials.model.File;
import com.bkh.tutorials.model.Topic;
import com.bkh.tutorials.repo.entity.CourseEntity;
import com.bkh.tutorials.repo.entity.FileEntity;
import com.bkh.tutorials.repo.entity.TopicEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public interface Mappers {

    Function<Course, CourseEntity> mapCourseModelToEntity = course -> {
        CourseEntity courseEntity = new CourseEntity();
        courseEntity.setCourseName(course.getCourseName());
        return courseEntity;
    };

    Function<CourseEntity, Course> mapCourseEntityToModel = courseEntity -> {
        Course course = new Course();
        course.setCourseId(courseEntity.getCourseId());
        course.setCourseName(courseEntity.getCourseName());
        Optional.ofNullable(courseEntity.getCourseLogo())
                .ifPresent(courseLogo -> course.setCourseLogo(Mappers.mapFileEntityToModel.apply(courseLogo)));
        Optional.ofNullable(courseEntity.getTopics())
                .ifPresent(topicEntities ->
                        course.setTopics(topicEntities.stream()
                                .map(topicEntity -> {
                                    Topic topic = new Topic();
                                    topic.setTopicId(topicEntity.getTopicId());
                                    topic.setTopicName(topicEntity.getTopicName());
                                    topic.setTopicOrder(topicEntity.getTopicOrder());
                                    return topic;
                                }).collect(Collectors.toList())));
        course.setTopicCount(course.getTopics().size());
        return course;
    };

    Function<Topic, TopicEntity> mapTopicModelToEntity = topicModel -> {
        TopicEntity topicEntity = new TopicEntity();
        topicEntity.setTopicName(topicModel.getTopicName());
        topicEntity.setTopicOrder(topicModel.getTopicOrder());
        topicEntity.setTopicContent(topicModel.getTopicContent());
        return topicEntity;
    };

    Function<TopicEntity, Topic> mapTopicEntityToModel = topicEntity -> {
        Topic topic = new Topic();
        topic.setTopicId(topicEntity.getTopicId());
        topic.setCourseId(topicEntity.getCourse().getCourseId());
        topic.setTopicName(topicEntity.getTopicName());
        topic.setTopicOrder(topicEntity.getTopicOrder());
        topic.setTopicContent(topicEntity.getTopicContent());
        return topic;
    };

    Function<FileEntity, File> mapFileEntityToModel = fileEntity -> {
        File file = new File();
        file.setFileId(fileEntity.getFileId());
        file.setFileName(fileEntity.getFileName());
        file.setData(fileEntity.getData());
        file.setFileType(fileEntity.getFileType());
        return file;
    };

    Function<MultipartFile, FileEntity> mapMultipartFileToEntity = file -> {
        FileEntity fileEntity = new FileEntity();
        fileEntity.setFileName(StringUtils.cleanPath(file.getOriginalFilename()));
        fileEntity.setFileType(file.getContentType());
        try {
            fileEntity.setData(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileEntity;
    };

}
