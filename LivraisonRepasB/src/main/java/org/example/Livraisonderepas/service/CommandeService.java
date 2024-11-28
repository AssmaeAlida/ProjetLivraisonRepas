package org.example.Livraisonderepas.service;


import org.example.Livraisonderepas.model.Commande;
import org.example.Livraisonderepas.model.User;
import org.example.Livraisonderepas.repository.CommandeRepository;
import org.example.Livraisonderepas.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CommandeService {

    @Autowired
    private CommandeRepository commandeRepository;

    @Autowired
    private UserRepository userRepository; // Ajout d'un repository Client

    // Créer une commande à partir du panier de l'utilisateur
    public Commande creerCommande(Long clientId) {
        Commande commande = new Commande();
        // Vous devrez probablement remplir la commande avec des items
       // commande.getReference(commande);
        commande.setDate(new Date());
        commande.setStatusCommmende(false);

        // Récupérer le client et associer à la commande
        User client = userRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client non trouvé"));
        commande.setClient(client);

        // Sauvegarder la commande
        return commandeRepository.save(commande);
    }

    // Récupérer les commandes de client
    public List<Commande> getCommandesByUtilisateurId(Long clientId) {
        // Récupérer le client
        User client = userRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client non trouvé"));

        // Récupérer les commandes associées au client
        return commandeRepository.findByClient(client);
    }
}
