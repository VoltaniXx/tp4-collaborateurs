package org.example;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Représente l'adresse postale d'un collaborateur.
 * Reprise de TP2 avec ajout de toString() pour les affichages en ligne (Annuaire).
 */
@Entity
public class Adresse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String rue;
    private String codePostal;
    private String ville;
    private String pays;

    public Adresse(String rue, String codePostal, String ville, String pays) {
        this.rue = rue;
        this.codePostal = codePostal;
        this.ville = ville;
        this.pays = pays;
    }

    protected Adresse()
    {

    }

    public String getRue()        { return this.rue; }
    public String getCodePostal() { return this.codePostal; }
    public String getVille()      { return this.ville; }
    public String getPays()       { return this.pays; }

    /** Affichage multiligne (hérité du TP2 — utilisé dans afficherFiche()). */
    public void afficher() {
        System.out.println(this.rue);
        System.out.println(this.codePostal + " " + this.ville);
        System.out.println(this.pays);
    }

    /** Représentation compacte sur une ligne (utilisée dans toString() de Collaborateur). */
    @Override
    public String toString() {
        return this.rue + ", " + this.codePostal + " " + this.ville + " (" + this.pays + ")";
    }
}
