package com.example.student_management_system.Repository;

import com.example.student_management_system.Entity.StudentAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentAddressRepository extends JpaRepository<StudentAddress, Long> {

}
