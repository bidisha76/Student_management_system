package com.example.student_management_system.Controller;

import com.example.student_management_system.Entity.Course;
import com.example.student_management_system.Entity.Student;
import com.example.student_management_system.Service.AdminService;
import com.example.student_management_system.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired private StudentService studentService;
    @Autowired private AdminService adminService;

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        return adminService.validateLogin(username, password) ? "Login Successful" : "Invalid credentials";
    }

    @PostMapping("/admit")
    public Student admitStudent(@RequestBody Student student) {
        return studentService.admitStudent(student);
    }

    @PostMapping("/assign-courses/{studentId}")
    public Student assignCourses(@PathVariable Long studentId, @RequestBody List<Long> courseIds) {
        return studentService.assignCourses(studentId, courseIds);
    }

    @GetMapping("/students")
    public List<Student> searchStudents(@RequestParam String name) {
        return studentService.searchStudentsByName(name);
    }

    @GetMapping("/students-by-course/{courseId}")
    public List<Student> getStudentsByCourse(@PathVariable Long courseId) {
        return studentService.getStudentsByCourse(courseId);
    }

    @PostMapping("/course")
    public Course uploadCourse(@RequestBody Course course) {
        return studentService.uploadCourse(course);
    }
}



