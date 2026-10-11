package org.example;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE_COLLAB", discriminatorType = DiscriminatorType.STRING)
public abstract class Collaborateur {

    @Id
    private String identifiant;
    @Column(length = 50)
    private String prenom;
    @Column(length = 50)
    private String nom;
    private double salaire;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "adresse_id")
    private Adresse adresse;

    protected Collaborateur() {
    }

    protected Collaborateur(
            String identifiant,
            String prenom,
            String nom,
            double salaire,
            Adresse adresse) {
        this.identifiant = identifiant;
        this.prenom = prenom;
        this.nom = nom;
        this.salaire = salaire;
        this.adresse = adresse;
    }

    public String getIdentifiant() { return this.identifiant; }
    public String getPrenom()      { return this.prenom; }
    public String getNom()         { return this.nom; }
    public double getSalaire()     { return this.salaire; }
    public Adresse getAdresse()    { return this.adresse; }

    public void augmenterSalaire(double pourcentage) {
        if (pourcentage > 0) {
            this.salaire = this.salaire * (1 + pourcentage / 100);
        }
        else {
            this.salaire = this.salaire * (1 - pourcentage / 100);
        }
    }

    public abstract String getMetier();

    public abstract void travailler();

    public void afficherFiche() {
        System.out.println("[" + this.identifiant + "] " + this.prenom + " " + this.nom);
        System.out.println("Métier  : " + this.getMetier());
        System.out.println("Salaire : " + this.salaire + " €");
        System.out.println("Adresse :");
        this.adresse.afficher();
    }

    @Override
    public boolean equals(Object autre) {
        if (this == autre) return true;
        if (!(autre instanceof Collaborateur collaborateur)) return false;
        return this.identifiant.equals(collaborateur.identifiant);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.identifiant);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s %s (%s) - %.2f €",
                this.identifiant, this.prenom, this.nom, this.getMetier(), this.salaire);
    }



}