package org.example.Livraisonderepas.controller;



import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.example.Livraisonderepas.model.Categorie;
import org.example.Livraisonderepas.model.Commande;
import org.example.Livraisonderepas.model.Repas;
import org.example.Livraisonderepas.model.User;
import org.example.Livraisonderepas.service.CommandeService;
import org.example.Livraisonderepas.service.RepasService;
import org.example.Livraisonderepas.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UtilisateurService userService;

    @Autowired
    private CommandeService commandeService;
    @Autowired
    private RepasService repasService;

    @GetMapping("/categories/{categorie}")
    public Collection<Repas> findByCategorie(@PathVariable Categorie categorie) {
        return repasService.findByCategorie(categorie);
    }


    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/commandes/{utilisateurId}")
    public ResponseEntity<List<Commande>> getCommandesByUtilisateurId(@PathVariable Long utilisateurId) {
        List<Commande> commandes = commandeService.getCommandesByUtilisateurId(utilisateurId);
        return new ResponseEntity<>(commandes, HttpStatus.OK);
    }

    @PostMapping("/commander/{utilisateurId}")
    public ResponseEntity<Commande> creerCommande(@PathVariable Long utilisateurId) {
        Commande nouvelleCommande = commandeService.creerCommande(utilisateurId);
        return new ResponseEntity<>(nouvelleCommande, HttpStatus.CREATED);
    }

    @PostMapping("/signIn")
    public User signIn(@RequestBody User user) {
        return userService.signIn(user.getEmail(), user.getPassword());
    }


    @PostMapping("/signUp/fullName/{fullName}/email/{email}/password/{password}")
    public int signUp(@PathVariable String fullName,@PathVariable String email, @PathVariable String password) {
        return userService.signUp(fullName,email, password);
    }

    //forgot password
    @PostMapping("/forgotPassword/{email}")
    @Transactional
    public User forgotPassword(@PathVariable String email) {
        return userService.forgotPassword(email);
    }

    //change password
    @PostMapping("/changePassword/token/{token}/password/{password}")
    @Transactional
    public User changePassword(@PathVariable String token,@PathVariable String password) {
        return userService.changePassword(token, password);
    }

    @PostMapping("/updateUser")
    public User updateUser(@RequestBody User updatedUser) {
        return userService.updateUser(updatedUser);
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
            return "User logged out";
        } else {
            return "No active session to logout";
        }
    }



}