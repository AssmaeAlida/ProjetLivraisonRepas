package org.example.Livraisonderepas.repository;




import org.example.Livraisonderepas.model.Commande;
import org.example.Livraisonderepas.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommandeRepository extends JpaRepository<Commande, Long> {
        List<Commande> findByClient(User client);
}


