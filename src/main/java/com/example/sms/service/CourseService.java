
package com.example.sms.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.sms.entity.Course;
import com.example.sms.repository.CourseRepository;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    public Course getCourseById(Long id) {
        return courseRepository.findById(id)
            .orElseThrow(() ->
                new RuntimeException("Course not found"));
    }

    public Course updateCourse(Long id, Course course) {

        Course existing = getCourseById(id);

        existing.setCourseName(course.getCourseName());
        existing.setCourseCode(course.getCourseCode());
        existing.setDuration(course.getDuration());
        existing.setDescription(course.getDescription());

        return courseRepository.save(existing);
    }

    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }

    public long getTotalCourses() {
        return courseRepository.count();
    }
}
