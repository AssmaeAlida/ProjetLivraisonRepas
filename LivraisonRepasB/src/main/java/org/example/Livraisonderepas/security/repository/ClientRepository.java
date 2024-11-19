package org.example.Livraisonderepas.security.repository;


import org.example.Livraisonderepas.security.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
    // Des méthodes supplémentaires peuvent être ajoutées selon les besoins
}
