
package com.example.sms.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.sms.entity.Marks;
import com.example.sms.repository.MarksRepository;

@Service
public class MarksService {

    private final MarksRepository marksRepository;

    public MarksService(MarksRepository marksRepository) {
        this.marksRepository = marksRepository;
    }

    public List<Marks> getAllMarks() {
        return marksRepository.findAll();
    }

    public Marks saveMarks(Marks marks) {
        return marksRepository.save(marks);
    }

    public List<Marks> getMarksByStudent(Long studentId) {
        return marksRepository.findByStudent_Id(studentId);
    }

    public Marks getMarksById(Long id) {
        return marksRepository.findById(id)
            .orElseThrow(() ->
                new RuntimeException("Marks record not found"));
    }

    public void deleteMarks(Long id) {
        marksRepository.deleteById(id);
    }
}
