package org.example.Livraisonderepas.security.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.example.Livraisonderepas.model.Repas;

import java.util.List;
import java.util.Set;

@Entity
public class Admin extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Admin() {
        super();
    }
    @OneToMany(mappedBy = "admin")
    @JsonManagedReference
    private Set<Client> clients;

    @OneToMany(mappedBy = "admin")
    @JsonManagedReference
    private List<Livreur> livreurs;

    @OneToMany
    private List<Repas> repasList;

    public Set<Client> getClients() {
        return clients;
    }

    public void addLivreur(Livreur livreur) {
        this.livreurs.add(livreur);
    }

    public void addClient(Client client) {
        this.clients.add(client);
    }

   // public void setClients(Set<Client> utilisateurs) {this.clients = utilisateurs;}

    public List<Livreur> getLivreur() {
        return livreurs;
    }

    //public void setLivreurs(Set<Livreur> deliverys) {this.livreurs = deliverys;}
}

