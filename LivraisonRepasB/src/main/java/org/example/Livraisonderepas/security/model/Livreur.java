package org.example.Livraisonderepas.security.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class Livreur extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String lieu;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    @JsonBackReference // Ensures that Jackson avoids infinite recursion
    private Admin admin;

    // Getters and Setters
    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }
}
