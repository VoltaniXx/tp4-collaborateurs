package org.example;

/**
 * Programmeur : collaborateur spécialisé dans le développement logiciel.
 * Implémente Formateur car un programmeur peut animer des formations internes.
 *
 * Reprise du TP2 avec ajout de l'identifiant (délégué à Collaborateur).
 */
public class Programmeur extends Collaborateur implements Formateur {

    private String langagePrefere;

    public Programmeur(
            String identifiant,
            String prenom,
            String nom,
            double salaire,
            Adresse adresse,
            String langagePrefere) {
        super(identifiant, prenom, nom, salaire, adresse);
        this.langagePrefere = langagePrefere;
    }

    public String getLangagePrefere() { return this.langagePrefere; }

    @Override
    public String getMetier() {
        return "Programmeur (" + this.langagePrefere + ")";
    }

    @Override
    public void travailler() {
        System.out.println(this.getPrenom() + " développe une fonctionnalité en " + this.langagePrefere + ".");
    }

    @Override
    public void former() {
        System.out.println(this.getPrenom() + " anime une formation interne.");
    }
}
