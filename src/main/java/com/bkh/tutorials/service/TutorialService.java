package com.bkh.tutorials.service;

import com.bkh.tutorials.model.Course;
import com.bkh.tutorials.model.Topic;
import com.bkh.tutorials.repo.CourseRepository;
import com.bkh.tutorials.repo.FIleRepository;
import com.bkh.tutorials.repo.TopicRepository;
import com.bkh.tutorials.repo.entity.CourseEntity;
import com.bkh.tutorials.repo.entity.TopicEntity;
import com.bkh.tutorials.util.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TutorialService {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private FIleRepository fIleRepository;

    public Integer uploadFile(MultipartFile file) {
        return fIleRepository.save(Mappers.mapMultipartFileToEntity.apply(file)).getFileId();
    }

    public List<Course> getAllCourses() {
        return Optional
                .ofNullable(courseRepository.findAll())
                .get().parallelStream()
                .map(courseEntity -> Mappers.mapCourseEntityToModel.apply(courseEntity))
                .collect(Collectors.toList());
    }

    public Course getCourseById(int courseId) {
        return Optional
                .ofNullable(courseRepository.findById(courseId))
                .get()
                .map(courseEntity -> Mappers.mapCourseEntityToModel.apply(courseEntity))
                .orElseThrow(() -> new RuntimeException("course not found"));
    }

    public Topic getTopicById(int topicId) {
        return Optional
                .ofNullable(topicRepository.findById(topicId))
                .get()
                .map(topicEntity -> Mappers.mapTopicEntityToModel.apply(topicEntity))
                .orElseThrow(() -> new RuntimeException("topic not found"));
    }

    public Course addCourse(Course course) {
        return Optional.ofNullable(fIleRepository.findById(course.getCourseLogoId()))
                .map(f -> {
                    CourseEntity courseEntity = Mappers.mapCourseModelToEntity.apply(course);
                    courseEntity.setCourseLogo(f.get());
                    courseEntity.setTopics(course.getTopics().stream()
                            .map(topic -> {
                                TopicEntity topicEntity = Mappers.mapTopicModelToEntity.apply(topic);
                                topicEntity.setCourse(courseEntity);
                                return topicEntity;
                            })
                            .collect(Collectors.toList()));
                    return Mappers.mapCourseEntityToModel.apply(courseRepository.save(courseEntity));
                }).orElseThrow(() -> new RuntimeException("logo file not found"));
    }

    public Course updateCourse(int courseId, Course course) {
        return Optional.ofNullable(courseRepository.findById(courseId))
                .map(c -> {
                    CourseEntity courseEntity = c.get();
                    Optional.ofNullable(course.getCourseLogoId()).ifPresent(courseLogoId ->
                            courseEntity.setCourseLogo(Optional.ofNullable(fIleRepository.findById(courseLogoId))
                                    .map(fileEntity -> fileEntity.get())
                                    .orElseThrow(() -> new RuntimeException("logo file not found"))));
                    Optional.ofNullable(course.getCourseName()).ifPresent(name -> courseEntity.setCourseName(name));
                    return Mappers.mapCourseEntityToModel.apply(courseRepository.save(courseEntity));
                }).orElseThrow(() -> new RuntimeException("course not found"));
    }

    public boolean deleteCourse(int courseId) {
        return Optional.ofNullable(courseRepository.findById(courseId))
                .map(c -> {
                    courseRepository.deleteById(courseId);
                    return true;
                }).orElseThrow(() -> new RuntimeException("course not found"));
    }


    public Topic addTopic(int courseId, Topic topic) {
        return Optional.ofNullable(courseRepository.findById(courseId))
                .map(c -> {
                    TopicEntity topicEntity = Mappers.mapTopicModelToEntity.apply(topic);
                    topicEntity.setCourse((c.get()));
                    if (topicEntity.getTopicOrder() == null) {
                        topicEntity.setTopicOrder(c.get().getTopics().size() + 1);
                    }
                    return Mappers.mapTopicEntityToModel.apply(topicRepository.save(topicEntity));
                }).orElseThrow(() -> new RuntimeException("course not found"));
    }

    public Topic updateTopic(int courseId, int topicId, Topic topic) {
        return Optional.ofNullable(topicRepository.findById(topicId))
                .map(t -> {
                    TopicEntity topicEntity = t.get();
                    Optional.ofNullable(topic.getTopicName()).ifPresent(name -> topicEntity.setTopicName(name));
                    Optional.ofNullable(topic.getTopicContent()).ifPresent(content -> topicEntity.setTopicContent(content));
                    Optional.ofNullable(topic.getTopicOrder()).ifPresent(order -> topicEntity.setTopicOrder(order));
                    System.out.println(topicEntity);
                    return Mappers.mapTopicEntityToModel.apply(topicRepository.save(topicEntity));
                }).orElseThrow(() -> new RuntimeException("topic not found"));
    }

    public boolean deleteTopic(int courseId, int topicId) {
        return Optional.ofNullable(topicRepository.findById(topicId))
                .map(t -> {
                    topicRepository.deleteById(topicId);
                    return true;
                }).orElseThrow(() -> new RuntimeException("topic not found"));
    }

}
