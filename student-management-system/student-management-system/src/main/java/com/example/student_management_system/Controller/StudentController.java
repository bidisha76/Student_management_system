package com.example.student_management_system.Controller;

import com.example.student_management_system.Entity.Course;
import com.example.student_management_system.Entity.Student;
import com.example.student_management_system.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;


import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/student")

public class StudentController {
    @Autowired private StudentService studentService;

    @PostMapping("/validate")
    public Student validate(@RequestParam String studentCode,
                            @RequestParam("dob") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dob) {
        Student student = studentService.validateStudent(studentCode, dob);
        if (student == null) {
            throw new RuntimeException("Invalid studentCode or date of birth");
        }
        return student;
    }

    @PutMapping("/update/{id}")
    public Student updateProfile(@PathVariable Long id, @RequestBody Student student) {
        return studentService.updateProfile(id, student);
    }

    @GetMapping("/courses/{studentId}")
    public List<Course> searchCourses(@PathVariable Long studentId) {
        return studentService.searchAssignedCourses(studentId);
    }

    @DeleteMapping("/leave-course/{studentId}/{courseId}")
    public Student leaveCourse(@PathVariable Long studentId, @PathVariable Long courseId) {
        return studentService.leaveCourse(studentId, courseId);
    }
}




