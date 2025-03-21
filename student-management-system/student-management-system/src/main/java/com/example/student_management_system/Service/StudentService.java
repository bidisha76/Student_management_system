package com.example.student_management_system.Service;

import com.example.student_management_system.Entity.Course;
import com.example.student_management_system.Entity.Student;
import com.example.student_management_system.Repository.CourseRepository;
import com.example.student_management_system.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class StudentService {
    @Autowired private StudentRepository studentRepo;
    @Autowired private CourseRepository courseRepo;

    public Student admitStudent(Student student) {
        return studentRepo.save(student);
    }

    public List<Student> searchStudentsByName(String name) {
        return studentRepo.findByNameContainingIgnoreCase(name);
    }

    public List<Student> getStudentsByCourse(Long courseId) {
        Optional<Course> course = courseRepo.findById(courseId);
        return course.map(c -> new ArrayList<>(c.getStudents())).orElse(new ArrayList<>());
    }
    public Student validateStudent(String studentCode, Date dob) {
        return studentRepo.findByStudentCodeAndDob(studentCode, dob)
                .orElseThrow(() -> new RuntimeException("Invalid student credentials"));
    }


    public Student assignCourses(Long studentId, List<Long> courseIds) {
        Student student = studentRepo.findById(studentId).orElseThrow();
        List<Course> courses = courseRepo.findAllById(courseIds);
        student.getCourses().addAll(courses);
        return studentRepo.save(student);
    }

    public Student updateProfile(Long id, Student updatedData) {
        Student student = studentRepo.findById(id).orElseThrow();
        student.setEmail(updatedData.getEmail());
        student.setMobile(updatedData.getMobile());
        student.setParentName(updatedData.getParentName());
        student.setAddresses(updatedData.getAddresses());
        return studentRepo.save(student);
    }

    public List<Course> searchAssignedCourses(Long studentId) {
        return courseRepo.findByStudentsId(studentId);
    }
    public Course uploadCourse(Course course) {
        return courseRepo.save(course);
    }


    public Student leaveCourse(Long studentId, Long courseId) {
        Student student = studentRepo.findById(studentId).orElseThrow();
        Course course = courseRepo.findById(courseId).orElseThrow();
        student.getCourses().remove(course);
        return studentRepo.save(student);
    }



}
