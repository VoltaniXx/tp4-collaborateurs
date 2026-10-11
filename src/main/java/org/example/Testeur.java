package org.example;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("TEST")
public class Testeur extends Collaborateur {

    protected Testeur() {
        super();
    }

    public Testeur(String identifiant, String prenom, String nom, double salaire, Adresse adresse) {
        super(identifiant, prenom, nom, salaire, adresse);
    }

    @Override
    public String getMetier() {
        return "Testeur";
    }

    @Override
    public void travailler() {
    }
}