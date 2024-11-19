package org.example.Livraisonderepas.security.controller;

import org.example.Livraisonderepas.model.Commande;
import org.example.Livraisonderepas.security.model.Client;
import org.example.Livraisonderepas.security.service.ClientService;
import org.example.Livraisonderepas.service.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/utilisateur")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private CommandeService commandeService;

    // Créer un utilisateur (inscription)
    @PostMapping("/inscription")
    public ResponseEntity<Client> createUtilisateur(@RequestBody Client utilisateur) {
        Client newUser = clientService.createUtilisateur(utilisateur);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    // Consulter les informations de l'utilisateur
    @GetMapping("/{id}")
    public ResponseEntity<Client> getUtilisateurById(@PathVariable Long id) {
        Client utilisateur = clientService.getUtilisateurById(id);
        if (utilisateur != null) {
            return new ResponseEntity<>(utilisateur, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

//    // Consulter les commandes passées par l'utilisateur
//    @GetMapping("/commandes/{utilisateurId}")
//    public ResponseEntity<List<Commande>> getCommandesByUtilisateurId(@PathVariable Long utilisateurId) {
//        List<Commande> commandes = commandeService.getCommandesByUtilisateurId(utilisateurId);
//        return new ResponseEntity<>(commandes, HttpStatus.OK);
//    }

    // Créer une commande à partir du panier de l'utilisateur
    @PostMapping("/commander/{utilisateurId}")
    public ResponseEntity<Commande> creerCommande(@PathVariable Long utilisateurId) {
        Commande nouvelleCommande = commandeService.creerCommande(utilisateurId);
        return new ResponseEntity<>(nouvelleCommande, HttpStatus.CREATED);
    }


}
