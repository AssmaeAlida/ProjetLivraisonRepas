package org.example.Livraisonderepas.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
public class Commentaire {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    private String message;
    private int evaluationEtoile;
    private Date date;

    @ManyToOne
    private User client;

    @OneToMany
    private List<Repas> repas;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getEvaluationEtoile() {
        return evaluationEtoile;
    }

    public void setEvaluationEtoile(int evaluationEtoile) {
        this.evaluationEtoile = evaluationEtoile;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public List<Repas> getRepas() {
        return repas;
    }

    public void setRepas(List<Repas> repas) {
        this.repas = repas;
    }
}
