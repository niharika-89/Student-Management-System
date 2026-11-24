
package com.example.sms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.sms.entity.Marks;

@Repository
public interface MarksRepository
        extends JpaRepository<Marks, Long> {

    List<Marks> findByStudent_Id(Long studentId);

    List<Marks> findBySubjectContainingIgnoreCase(String subject);

    List<Marks> findByExamNameContainingIgnoreCase(String examName);
}
