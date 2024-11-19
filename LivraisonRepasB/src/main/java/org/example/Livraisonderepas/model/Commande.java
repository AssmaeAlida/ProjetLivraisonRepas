package org.example.Livraisonderepas.model;

import jakarta.persistence.*;
import org.example.Livraisonderepas.security.model.Client;

import java.util.Date;
import java.util.List;

@Entity
public class Commande {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    private Date date;
    private Boolean statusCommmende;

    @ManyToOne
    @JoinColumn(name = "client_id")  // Nom de la colonne dans la base de données
    private Client client;  // Association avec l'entité Client

    @OneToMany
    private List<CommandeItem> commandeItems;

    @OneToMany
    private List<RepasItem> repasItems;

    // Getters et setters

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Boolean getStatusCommmende() {
        return statusCommmende;
    }

    public void setStatusCommmende(Boolean statusCommmende) {
        this.statusCommmende = statusCommmende;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<CommandeItem> getCommandeItems() {
        return commandeItems;
    }

    public void setCommandeItems(List<CommandeItem> commandeItems) {
        this.commandeItems = commandeItems;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
