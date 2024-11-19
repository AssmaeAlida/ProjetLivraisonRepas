package org.example.Livraisonderepas.service;



import org.example.Livraisonderepas.model.Repas;
import org.example.Livraisonderepas.repository.RepasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RepasService {

    @Autowired
    private RepasRepository repasRepository;

    // Create a new repas
    public Repas createRepas(Repas repas) {
        return repasRepository.save(repas);
    }

    // Get all repases
    public List<Repas> getAllRepases() {
        return repasRepository.findAll();
    }

    // Get a repas by id
    public Repas getRepasById(Long id) {
        Optional<Repas> repas = repasRepository.findById(id);
        return repas.orElse(null);
    }

    // Update a repas by id
    public Repas updateRepas(Long id, Repas repasDetails) {
        Optional<Repas> optionalRepas = repasRepository.findById(id);
        if (optionalRepas.isPresent()) {
            Repas repas = optionalRepas.get();
            repas.setNom(repasDetails.getNom());
            repas.setDescription(repasDetails.getDescription());
            repas.setPrix(repasDetails.getPrix());
            repas.setCategorie(repasDetails.getCategorie());
            repas.setImageUrl(repasDetails.getImageUrl());
            return repasRepository.save(repas);
        } else {
            return null;
        }
    }

    // Delete a repas by id
    public boolean deleteRepas(Long id) {
        Optional<Repas> repas = repasRepository.findById(id);
        if (repas.isPresent()) {
            repasRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
