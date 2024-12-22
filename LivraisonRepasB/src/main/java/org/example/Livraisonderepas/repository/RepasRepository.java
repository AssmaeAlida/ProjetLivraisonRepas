package org.example.Livraisonderepas.repository;


import org.example.Livraisonderepas.model.Categorie;
import org.example.Livraisonderepas.model.Repas;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface RepasRepository extends JpaRepository<Repas, Long> {


    Collection<Repas> findByCategorie(Categorie categorie);
    Repas findById(long id);
}
