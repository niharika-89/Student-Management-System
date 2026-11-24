
package com.example.sms.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.sms.entity.Course;
import com.example.sms.repository.CourseRepository;

@Configuration
public class CourseDataInitializer {

    @Bean
    public CommandLineRunner addDefaultCourses(
            CourseRepository courseRepository) {

        return args -> {

            String[][] courses = {
                {"Java Full Stack", "JAVA101", "6 Months", "Java and Spring Boot development"},
                {"Python Programming", "PYTHON101", "3 Months", "Python programming fundamentals"},
                {"Data Science", "DS101", "6 Months", "Data science and analytics"},
                {"Artificial Intelligence", "AI101", "6 Months", "Artificial intelligence fundamentals"},
                {"Machine Learning", "ML101", "4 Months", "Machine learning concepts"},
                {"Web Development", "WEB101", "3 Months", "HTML CSS and JavaScript"},
                {"Cloud Computing", "CLOUD101", "4 Months", "Cloud computing fundamentals"},
                {"Cyber Security", "CYBER101", "4 Months", "Cyber security fundamentals"},
                {"Database Management", "DB101", "3 Months", "SQL and database management"},
                {"Data Analytics", "DA101", "4 Months", "Excel Power BI and data analytics"}
            };

            for (String[] details : courses) {

                if (!courseRepository.existsByCourseCode(details[1])) {

                    Course course = new Course();

                    course.setCourseName(details[0]);
                    course.setCourseCode(details[1]);
                    course.setDuration(details[2]);
                    course.setDescription(details[3]);

                    courseRepository.save(course);
                }
            }

            System.out.println("10 default courses checked and added!");
        };
    }
}
