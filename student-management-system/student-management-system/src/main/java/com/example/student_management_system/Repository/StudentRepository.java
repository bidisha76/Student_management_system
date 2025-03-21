package com.example.student_management_system.Repository;

import com.example.student_management_system.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByNameContainingIgnoreCase(String name);
    Optional<Student> findByStudentCodeAndDob(String code, Date dob);
}

