package org.example.Livraisonderepas.service;



import org.example.Livraisonderepas.model.Categorie;
import org.example.Livraisonderepas.model.Repas;
import org.example.Livraisonderepas.repository.RepasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class RepasService {

    @Autowired
    private RepasRepository repasRepository;

    public Repas uploadImage(Long id, MultipartFile file) throws IOException {
        String baseUrl = "/images/Repas/"; // URL to store the image path
        String filename = StringUtils.cleanPath(file.getOriginalFilename());

        // Define the storage directory
        //D:\5IIR\projet-multiplateforme-e2425g7_3\LivrRepas-Angular\src\assets\images\Repas
        Path storageDirectory = Paths.get("D:\\5IIR\\projet-multiplateforme-e2425g7_3\\LivrRepas-Angular\\src\\assets\\images\\Repas");  // Modify as per your directory

        // Create directory if it doesn't exist
        if (!Files.exists(storageDirectory)) {
            Files.createDirectories(storageDirectory);
        }

        Path destinationPath = storageDirectory.resolve(filename);

        // Transfer the file to the server's directory
        file.transferTo(destinationPath);

        // Update image URL in the database
        Repas repas = repasRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Repas not found"));
        repas.setImageUrl(baseUrl + filename);  // Store the relative image URL
        repasRepository.save(repas);

        return repas;
    }

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

    public Collection<Repas> findByCategorie(Categorie categorie) {
        return repasRepository.findByCategorie(categorie);
    }
}
