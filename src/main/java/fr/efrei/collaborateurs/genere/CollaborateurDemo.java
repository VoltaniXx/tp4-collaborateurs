package fr.efrei.collaborateurs.genere;
@jakarta.persistence.Entity
@jakarta.persistence.Table(name = "collaborateur_demo")
public class CollaborateurDemo {
@jakarta.persistence.Id
@jakarta.persistence.Column(name = "id_collaborateur", nullable = false, length = 10)
private java.lang.String idCollaborateur;

@jakarta.persistence.Column(name = "prenom", nullable = false, length = 50)
private java.lang.String prenom;

@jakarta.persistence.Column(name = "nom", nullable = false, length = 50)
private java.lang.String nom;

@jakarta.persistence.Column(name = "metier", nullable = false, length = 40)
private java.lang.String metier;

@jakarta.persistence.Column(name = "salaire", nullable = false)
private java.lang.Double salaire;

@jakarta.persistence.Column(name = "langage_prefere", length = 40)
private java.lang.String langagePrefere;

public java.lang.String getIdCollaborateur() {
  return idCollaborateur;
}public void setIdCollaborateur(java.lang.String idCollaborateur) {
  this.idCollaborateur = idCollaborateur;
}

public java.lang.String getPrenom() {
  return prenom;
}public void setPrenom(java.lang.String prenom) {
  this.prenom = prenom;
}

public java.lang.String getNom() {
  return nom;
}public void setNom(java.lang.String nom) {
  this.nom = nom;
}

public java.lang.String getMetier() {
  return metier;
}public void setMetier(java.lang.String metier) {
  this.metier = metier;
}

public java.lang.Double getSalaire() {
  return salaire;
}public void setSalaire(java.lang.Double salaire) {
  this.salaire = salaire;
}

public java.lang.String getLangagePrefere() {
  return langagePrefere;
}public void setLangagePrefere(java.lang.String langagePrefere) {
  this.langagePrefere = langagePrefere;
}

}