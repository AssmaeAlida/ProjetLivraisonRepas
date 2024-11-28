package org.example.Livraisonderepas.repository;

import org.example.Livraisonderepas.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Admin findByEmail(String email);
    List<Admin> findAll();
}
