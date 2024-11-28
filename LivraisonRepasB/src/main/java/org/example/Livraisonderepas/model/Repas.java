package org.example.Livraisonderepas.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Repas {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String description;
    private double prix;
    private String imageUrl;
    private Categorie categorie;

    @ManyToOne
    private Admin admin;

    @ManyToOne
    private Commentaire commentaire;

    @OneToMany
    private List<Repas> repasList;


    public Repas() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Commentaire getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(Commentaire commentaire) {
        this.commentaire = commentaire;
    }

    public List<Repas> getRepasList() {
        return repasList;
    }

    public void setRepasList(List<Repas> repasList) {
        this.repasList = repasList;
    }
}
