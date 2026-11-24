
package com.example.sms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.sms.entity.Student;

@Repository
public interface StudentRepository
        extends JpaRepository<Student, Long> {

    List<Student> findByNameContainingIgnoreCase(String name);

    List<Student> findByEmailContainingIgnoreCase(String email);

    boolean existsByEmail(String email);
}
