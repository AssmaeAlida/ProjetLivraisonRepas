package org.example.Livraisonderepas.controller;


import org.example.Livraisonderepas.model.Repas;
import org.example.Livraisonderepas.model.Admin;
import org.example.Livraisonderepas.model.Role;
import org.example.Livraisonderepas.model.User;
import org.example.Livraisonderepas.service.AdminService;
import org.example.Livraisonderepas.service.RepasService;
import org.example.Livraisonderepas.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    //@Qualifier("adminService")
    @Autowired
    UtilisateurService userService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private RepasService repasService;

    //for repas

    //upload image for repas
    @PostMapping(value = "/uploadImage", consumes = {"multipart/form-data"})
    public Repas uploadImage(@RequestParam("id") Long id, @RequestParam("file") MultipartFile file) throws IOException {
        return repasService.uploadImage(id, file);
    }



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

    //for user


    @GetMapping("/")
    public List<User> findAll() {
        return userService.findAll();
    }

//    @GetMapping("/role/{role}")
//    public Collection<User> findByRole(@PathVariable Role role) {
//        return userService.findByRole(role);
//    }

    @GetMapping("/{id}")
    public User findById(@PathVariable Long id) {
        return userService.findById(id);
    }


    @PostMapping("/updateUser")
    public User updateUser(@RequestBody User updatedUser) {
        return userService.updateUser(updatedUser);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    //adduser
    @PostMapping("/")
    public User addLivreur(@RequestBody User user) {
        return userService.addLivreur(user);
    }

//for admin

    @PostMapping("/signIn")
    public Admin signIn(@RequestBody Admin admin) {
        return adminService.signIn(admin.getEmail(), admin.getPassword());
    }

    @PostMapping("/signUp/fullName/{fullName}/email/{email}/password/{password}")
    public int signUp(@PathVariable String fullName,@PathVariable String email,@PathVariable String password) {
        return adminService.signUp(fullName,email, password);
    }


}
