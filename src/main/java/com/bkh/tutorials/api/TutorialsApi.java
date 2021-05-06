package com.bkh.tutorials.api;

import com.bkh.tutorials.model.Course;
import com.bkh.tutorials.model.Topic;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Api(tags = {"Tutorials"}, value = "tutorials")
public interface TutorialsApi {

    @PostMapping("/upload-file")
    ResponseEntity uploadFile(@RequestPart("file") MultipartFile file);

    @GetMapping("/all-courses")
    ResponseEntity getAllCourses();

    @GetMapping("/course/{courseId}")
    ResponseEntity getCourseById(@PathVariable("courseId") int courseId);

    @GetMapping("/topic/{topicId}")
    ResponseEntity getTopicById(@PathVariable("topicId") int topicId);

    @PostMapping("/course")
    ResponseEntity addCourse(@RequestBody Course course);

    @PostMapping("/course/{courseId}/topic")
    ResponseEntity addTopic(@PathVariable("courseId") int courseId, @RequestBody Topic topic);

    @PutMapping("/course/{courseId}")
    ResponseEntity updateCourse(@PathVariable("courseId") int courseId, @RequestBody Course course);

    @PutMapping("/course/{courseId}/topic/{topicId}")
    ResponseEntity updateTopic(@PathVariable("courseId") int courseId, @PathVariable("topicId") int topicId, @RequestBody Topic topic);

    @DeleteMapping("/course/{courseId}")
    ResponseEntity deleteCourse(@PathVariable("courseId") int courseId);

    @DeleteMapping("/course/{courseId}/topic/{topicId}")
    ResponseEntity deleteTopic(@PathVariable("courseId") int courseId, @PathVariable("topicId") int topicId);

}
