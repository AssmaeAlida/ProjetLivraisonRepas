package org.example.Livraisonderepas.repository;




import org.example.Livraisonderepas.model.Commande;
import org.example.Livraisonderepas.security.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommandeRepository extends JpaRepository<Commande, Long> {
    List<Commande> findByClient(Client client);
}


