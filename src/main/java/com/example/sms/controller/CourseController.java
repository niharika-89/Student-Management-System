
package com.example.sms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.sms.entity.Course;
import com.example.sms.service.CourseService;

@Controller
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public String viewCourses(Model model) {

        model.addAttribute(
            "courses", courseService.getAllCourses());

        model.addAttribute("course", new Course());

        return "courses";
    }

    @PostMapping("/save")
    public String saveCourse(
            @ModelAttribute Course course) {

        courseService.saveCourse(course);

        return "redirect:/courses";
    }

    @GetMapping("/edit/{id}")
    public String editCourse(
            @PathVariable Long id,
            Model model) {

        model.addAttribute(
            "course", courseService.getCourseById(id));

        model.addAttribute(
            "courses", courseService.getAllCourses());

        return "courses";
    }

    @PostMapping("/update/{id}")
    public String updateCourse(
            @PathVariable Long id,
            @ModelAttribute Course course) {

        courseService.updateCourse(id, course);

        return "redirect:/courses";
    }

    @GetMapping("/delete/{id}")
    public String deleteCourse(@PathVariable Long id) {

        courseService.deleteCourse(id);

        return "redirect:/courses";
    }
}
