package org.example.Livraisonderepas.service;

import org.example.Livraisonderepas.model.Admin;
import org.example.Livraisonderepas.model.User;
import org.example.Livraisonderepas.repository.AdminRepository;
import org.example.Livraisonderepas.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class AdminService  {
    @Autowired
    AdminRepository adminRepository;


    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Transactional
    public Admin signIn(String email, String password) {
        Admin loadedAdmin = adminRepository.findByEmail(email);
        if (loadedAdmin != null && passwordEncoder.matches(password, loadedAdmin.getPassword())) {
            return loadedAdmin;
        }
            return null;
    }

    @Transactional
    public int signUp(String fullName  ,String email, String password) {
        Admin loadedAdmin = adminRepository.findByEmail(email);
        if (loadedAdmin == null) {
            Admin admin = new Admin();
            admin.setFullName(fullName);
            admin.setEmail(email);
            admin.setPassword(passwordEncoder.encode(password));
            adminRepository.save(admin);
            return 1;
        }
            return -1;
    }

    public Admin addAdmin(Admin admin) {
        Admin loadedAdmin = adminRepository.findByEmail(admin.getEmail());
        if (loadedAdmin != null) {
            throw new RuntimeException("Admin already exists");
        }
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        return adminRepository.save(admin);
    }

    public List<Admin> findAll() {
        return adminRepository.findAll();
    }
















}
