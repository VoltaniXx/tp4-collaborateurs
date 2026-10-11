package org.example;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("PROG")
public class Programmeur extends Collaborateur {

    private String langage;

    protected Programmeur() {
        super();
    }

    public Programmeur(String identifiant, String prenom, String nom, double salaire, Adresse adresse, String langage) {
        super(identifiant, prenom, nom, salaire, adresse);
        this.langage = langage;
    }

    public String getLangage() {
        return langage;
    }

    public void setLangage(String langage) {
        this.langage = langage;
    }

    @Override
    public String getMetier() {
        return "Programmeur";
    }

    @Override
    public void travailler() {
    }
}