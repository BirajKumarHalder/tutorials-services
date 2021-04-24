package com.bkh.tutorials.api.controller;

import com.bkh.tutorials.api.TutorialsApi;
import com.bkh.tutorials.model.Course;
import com.bkh.tutorials.model.Topic;
import com.bkh.tutorials.service.TutorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin
public class TutorialsController implements TutorialsApi {

    @Autowired
    private TutorialService tutorialService;

    @Override
    public ResponseEntity uploadFile(MultipartFile file) {
        return ResponseEntity.ok(tutorialService.uploadFile(file));
    }

    @Override
    public ResponseEntity getAllCourses() {
        return ResponseEntity.ok(tutorialService.getAllCourses());
    }

    @Override
    public ResponseEntity getCourseById(int courseId) {
        return ResponseEntity.ok(tutorialService.getCourseById(courseId));
    }

    @Override
    public ResponseEntity getTopicById(int topicId) {
        return ResponseEntity.ok(tutorialService.getTopicById(topicId));
    }

    @Override
    public ResponseEntity addCourse(Course course) {
        return ResponseEntity.ok(tutorialService.addCourse(course));
    }

    @Override
    public ResponseEntity addTopic(int courseId, Topic topic) {
        return ResponseEntity.ok(tutorialService.addTopic(courseId, topic));
    }

    @Override
    public ResponseEntity updateCourse(int courseId, Course course) {
        return ResponseEntity.ok(tutorialService.updateCourse(courseId, course));
    }

    @Override
    public ResponseEntity updateTopic(int courseId, int topicId, Topic topic) {
        return ResponseEntity.ok(tutorialService.updateTopic(courseId, topicId, topic));
    }

    @Override
    public ResponseEntity deleteTopic(int topicId) {
        return ResponseEntity.ok(tutorialService.deleteTopic(topicId));
    }

    @Override
    public ResponseEntity deleteCourse(int courseId) {
        return ResponseEntity.ok(tutorialService.deleteCourse(courseId));
    }

}
