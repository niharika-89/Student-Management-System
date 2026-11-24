
package com.example.sms.controller;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.sms.entity.Attendance;
import com.example.sms.entity.Student;
import com.example.sms.service.AttendanceService;
import com.example.sms.service.StudentService;

@Controller
public class AttendanceController {

    private final AttendanceService attendanceService;
    private final StudentService studentService;

    public AttendanceController(
            AttendanceService attendanceService,
            StudentService studentService) {
        this.attendanceService = attendanceService;
        this.studentService = studentService;
    }

    @GetMapping("/attendance")
    public String attendancePage(Model model) {

        model.addAttribute(
                "attendanceRecords",
                attendanceService.getAllAttendance()
        );

        model.addAttribute(
                "students",
                studentService.getAllStudents()
        );

        model.addAttribute("today", LocalDate.now());

        return "attendance";
    }

    @PostMapping("/attendance/save")
    public String saveAttendance(
            @RequestParam Long studentId,
            @RequestParam String status,
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate attendanceDate) {

        if (!status.equals("Present") &&
                !status.equals("Absent")) {
            throw new IllegalArgumentException(
                    "Invalid attendance status"
            );
        }

        Student student =
                studentService.getStudentById(studentId);

        Attendance attendance = new Attendance();

        attendance.setStudent(student);
        attendance.setStatus(status);
        attendance.setAttendanceDate(attendanceDate);

        attendanceService.saveAttendance(attendance);

        return "redirect:/attendance";
    }

    @GetMapping("/attendance/date")
    public String filterAttendance(
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate date,
            Model model) {

        model.addAttribute(
                "attendanceRecords",
                attendanceService.getAttendanceByDate(date)
        );

        model.addAttribute(
                "students",
                studentService.getAllStudents()
        );

        model.addAttribute("today", date);

        return "attendance";
    }
}
