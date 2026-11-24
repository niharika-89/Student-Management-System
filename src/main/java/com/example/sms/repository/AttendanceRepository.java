
package com.example.sms.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.sms.entity.Attendance;

@Repository
public interface AttendanceRepository
        extends JpaRepository<Attendance, Long> {

    List<Attendance> findByAttendanceDate(LocalDate attendanceDate);

    List<Attendance> findByStudent_Id(Long studentId);

    List<Attendance> findByStudent_IdAndAttendanceDate(
            Long studentId,
            LocalDate attendanceDate
    );

    long countByStatusIgnoreCase(String status);

    
    @Modifying
    @Query("DELETE FROM Attendance a WHERE a.student.id = :studentId")
    int deleteAllByStudentId(@Param("studentId") Long studentId);
}
