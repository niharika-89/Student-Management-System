
package com.example.sms.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.sms.entity.Student;
import com.example.sms.repository.StudentRepository;
import com.example.sms.repository.AttendanceRepository;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final AttendanceRepository attendanceRepository;

    public StudentService(
            StudentRepository studentRepository,
            AttendanceRepository attendanceRepository) {

        this.studentRepository = studentRepository;
        this.attendanceRepository = attendanceRepository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
            .orElseThrow(() ->
                new RuntimeException("Student not found"));
    }

    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student updateStudent(Long id, Student student) {

        Student existing = getStudentById(id);

        existing.setName(student.getName());
        existing.setEmail(student.getEmail());
        existing.setPhone(student.getPhone());
        existing.setGender(student.getGender());
        existing.setDateOfBirth(student.getDateOfBirth());
        existing.setAddress(student.getAddress());
        existing.setCourse(student.getCourse());

        return studentRepository.save(existing);
    }

    
    @Transactional
    public void deleteStudent(Long id) {

        Student student = getStudentById(id);

        attendanceRepository.deleteAllByStudentId(id);

        studentRepository.delete(student);
    }

    public List<Student> searchStudents(String name) {
        return studentRepository
            .findByNameContainingIgnoreCase(name);
    }

    public long getTotalStudents() {
        return studentRepository.count();
    }
}
