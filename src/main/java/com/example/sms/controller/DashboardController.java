
package com.example.sms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.sms.service.StudentService;
import com.example.sms.service.CourseService;
import com.example.sms.service.AttendanceService;

@Controller
public class DashboardController {

    private final StudentService studentService;
    private final CourseService courseService;
    private final AttendanceService attendanceService;

    public DashboardController(
            StudentService studentService,
            CourseService courseService,
            AttendanceService attendanceService) {

        this.studentService = studentService;
        this.courseService = courseService;
        this.attendanceService = attendanceService;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {

        long totalStudents =
            studentService.getTotalStudents();

        long totalCourses =
            courseService.getTotalCourses();

        long present =
            attendanceService.getPresentCount();

        long absent =
            attendanceService.getAbsentCount();

        long totalAttendance = present + absent;

        double attendancePercentage = 0;

        if (totalAttendance > 0) {
            attendancePercentage =
                (present * 100.0) / totalAttendance;
        }

        model.addAttribute(
            "totalStudents", totalStudents);

        model.addAttribute(
            "totalCourses", totalCourses);

        model.addAttribute(
            "presentCount", present);

        model.addAttribute(
            "absentCount", absent);

        model.addAttribute(
            "attendancePercentage",
            String.format("%.1f", attendancePercentage));

        return "dashboard";
    }
}

