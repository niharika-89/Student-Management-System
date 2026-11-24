
package com.example.sms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.sms.entity.Marks;
import com.example.sms.entity.Student;
import com.example.sms.service.MarksService;
import com.example.sms.service.StudentService;

@Controller
@RequestMapping("/marks")
public class MarksController {

    private final MarksService marksService;
    private final StudentService studentService;

    public MarksController(
            MarksService marksService,
            StudentService studentService) {

        this.marksService = marksService;
        this.studentService = studentService;
    }

    @GetMapping
    public String viewMarks(Model model) {

        model.addAttribute(
            "marksRecords", marksService.getAllMarks());

        model.addAttribute(
            "students", studentService.getAllStudents());

        return "marks";
    }

    @PostMapping("/save")
    public String saveMarks(
            @RequestParam Long studentId,
            @RequestParam String subject,
            @RequestParam String examName,
            @RequestParam Double maxMarks,
            @RequestParam Double obtainedMarks) {

        if (maxMarks == null || obtainedMarks == null ||
            maxMarks <= 0 || obtainedMarks < 0 ||
            obtainedMarks > maxMarks) {

            throw new IllegalArgumentException(
                "Please enter valid marks");
        }

        Student student =
            studentService.getStudentById(studentId);

        Marks marks = new Marks();

        marks.setStudent(student);
        marks.setSubject(subject);
        marks.setExamName(examName);
        marks.setMaxMarks(maxMarks);
        marks.setObtainedMarks(obtainedMarks);

        marksService.saveMarks(marks);

        return "redirect:/marks";
    }

    @GetMapping("/delete/{id}")
    public String deleteMarks(@PathVariable Long id) {

        marksService.deleteMarks(id);

        return "redirect:/marks";
    }
}
