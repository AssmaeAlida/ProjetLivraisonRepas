package org.example.Livraisonderepas.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String FullName;
    private String email;
    private String password;
    private String role;

    @OneToMany(mappedBy = "admin")
    @JsonManagedReference
    private List<User> users;


    @OneToMany
    private List<Repas> repasList;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setClients(List<User> users) {
        this.users = users;
    }

    public List<Repas> getRepasList() {
        return repasList;
    }

    public void setRepasList(List<Repas> repasList) {
        this.repasList = repasList;
    }
}

