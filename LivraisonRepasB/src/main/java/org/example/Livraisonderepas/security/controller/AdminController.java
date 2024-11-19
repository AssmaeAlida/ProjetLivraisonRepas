package org.example.Livraisonderepas.security.controller;


import org.example.Livraisonderepas.model.Repas;
import org.example.Livraisonderepas.security.model.Admin;
import org.example.Livraisonderepas.security.model.User;
import org.example.Livraisonderepas.security.service.AdminService;
import org.example.Livraisonderepas.security.service.UserService;
import org.example.Livraisonderepas.service.RepasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Qualifier("adminService")
    @Autowired
    UserService userService;
    @Autowired
    private AdminService adminService;
    @PostMapping("/")
    public User save(@RequestBody Admin admin) {
        return userService.save(admin);
    }

    @Autowired
    private RepasService repasService;

    // Create a new repas
    @PostMapping("/repas")
    public ResponseEntity<Repas> createRepas(@RequestBody Repas repas) {
        Repas newRepas = repasService.createRepas(repas);
        return new ResponseEntity<>(newRepas, HttpStatus.CREATED);
    }

    // Get all repases
    @GetMapping("/repases")
    public ResponseEntity<List<Repas>> getAllRepases() {
        List<Repas> repases = repasService.getAllRepases();
        return new ResponseEntity<>(repases, HttpStatus.OK);
    }

    // Get a repas by id
    @GetMapping("/repases/{id}")
    public ResponseEntity<Repas> getRepasById(@PathVariable Long id) {
        Repas repas = repasService.getRepasById(id);
        if (repas != null) {
            return new ResponseEntity<>(repas, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Update a repas by id
    @PutMapping("/repases/{id}")
    public ResponseEntity<Repas> updateRepas(@PathVariable Long id, @RequestBody Repas repasDetails) {
        Repas updatedRepas = repasService.updateRepas(id, repasDetails);
        if (updatedRepas != null) {
            return new ResponseEntity<>(updatedRepas, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete a repas by id
    @DeleteMapping("/repases/{id}")
    public ResponseEntity<Void> deleteRepas(@PathVariable Long id) {
        boolean isDeleted = repasService.deleteRepas(id);
        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/")
    public List<User> findAll() {
        return userService.findAll();
    }



    @GetMapping("/role/{role}")
    public Collection<User> findByRole(@PathVariable String role) {
        return userService.findByRole(role);
    }
}
