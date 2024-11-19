package org.example.Livraisonderepas.security.service;




import org.example.Livraisonderepas.security.model.Client;
import org.example.Livraisonderepas.security.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    // Créer un utilisateur (inscription)
    public Client createUtilisateur(Client utilisateur) {
        return clientRepository.save(utilisateur);
    }

    // Récupérer les informations d'un utilisateur par son id
    public Client getUtilisateurById(Long id) {
        return clientRepository.findById(id).orElse(null);
    }
}

