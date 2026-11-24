
package com.example.sms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.sms.entity.Student;
import com.example.sms.service.StudentService;
import com.example.sms.service.CourseService;

@Controller
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;
    private final CourseService courseService;

    public StudentController(
            StudentService studentService,
            CourseService courseService) {

        this.studentService = studentService;
        this.courseService = courseService;
    }

    @GetMapping
    public String viewStudents(Model model) {

        model.addAttribute(
            "students", studentService.getAllStudents());

        return "students";
    }


@GetMapping("/new")
public String addStudentForm(Model model) {

    model.addAttribute("student", new Student());

    model.addAttribute(
        "courses",
        courseService.getAllCourses()
    );

    return "add-student";
}


    @PostMapping("/save")
    public String saveStudent(
            @ModelAttribute Student student) {

        studentService.saveStudent(student);

        return "redirect:/students";
    }


@GetMapping("/edit/{id}")
public String editStudent(@PathVariable Long id, Model model) {

    model.addAttribute(
        "student",
        studentService.getStudentById(id)
    );

    model.addAttribute(
        "courses",
        courseService.getAllCourses()
    );

    return "edit-student";
}


    @PostMapping("/update/{id}")
    public String updateStudent(
            @PathVariable Long id,
            @ModelAttribute Student student) {

        studentService.updateStudent(id, student);

        return "redirect:/students";
    }

    @PostMapping("/delete/{id}")
    public String deleteStudent(@PathVariable("id") Long id) {

        studentService.deleteStudent(id);

        return "redirect:/students";
    }

    @GetMapping("/search")
    public String searchStudents(
            @RequestParam String name,
            Model model) {

        model.addAttribute(
            "students", studentService.searchStudents(name));

        return "students";
    }
}
