package org.example.Livraisonderepas.controller;



import org.example.Livraisonderepas.model.Categorie;
import org.example.Livraisonderepas.model.Commande;
import org.example.Livraisonderepas.model.Repas;
import org.example.Livraisonderepas.model.User;
import org.example.Livraisonderepas.service.CommandeService;
import org.example.Livraisonderepas.service.RepasService;
import org.example.Livraisonderepas.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @PostMapping("/signUp")
    public int signUp(@RequestBody User user) {
        return userService.signUp(user.getEmail(), user.getPassword());
    }

    //forgot password
    @PostMapping("/forgotPassword")
    public User forgotPassword(@RequestBody User user) {
        return userService.forgotPassword(user.getEmail());
    }

    //change password
    @PostMapping("/changePassword")
    public User changePassword(@RequestBody User user) {
        return userService.changePassword(user.getToken(), user.getPassword());
    }

    @PostMapping("/updateUser")
    public User updateUser(@RequestBody User updatedUser) {
        return userService.updateUser(updatedUser);
    }




}

