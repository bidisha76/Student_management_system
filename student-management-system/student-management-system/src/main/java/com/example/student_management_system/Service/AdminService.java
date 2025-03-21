package com.example.student_management_system.Service;

import com.example.student_management_system.Repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepo;

    public boolean validateLogin(String username, String password) {
        return adminRepo.findByUsernameAndPassword(username, password).isPresent();
    }
}

