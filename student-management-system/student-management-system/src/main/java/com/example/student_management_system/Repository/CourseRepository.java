package com.example.student_management_system.Repository;

import com.example.student_management_system.Entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByStudentsId(Long studentId);
}