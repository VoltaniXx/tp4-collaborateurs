package org.example;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;
import java.util.Scanner;

public class HelloEfrei {

    public static void main(String[] args) {
        EntityManagerFactory fabrique = Persistence.createEntityManagerFactory("collaborateurs-pu");

        CollaborateurService service = new CollaborateurService(fabrique);

        Adresse adresseAlice = new Adresse("12 rue des Lilas", "75000", "Paris", "France");
        Programmeur alice = new Programmeur("C001", "Alice", "Martin", 42000.0, adresseAlice, "Java");

        try {
            service.persister(alice);
            System.out.println("Alice enregistrée avec succès en base !");
        } catch (Exception e) {
            System.out.println("Alice existe déjà ou erreur : " + e.getMessage());
        }

        // on test find()
        Collaborateur trouveAlice = service.trouver("C001");
        if (trouveAlice != null) {
            System.out.println("Trouvé avec find('C001') : " + trouveAlice);
        }

        // Test find(...) avec un identifiant inexistant ("C999")
        Collaborateur inexistant = service.trouver("C999");
        // find() retourne null
        System.out.println("Recherche de 'C999' (inexistant) : " + inexistant);

        service.augmenterSalaireCollaborateur("C001", 10.0);
        System.out.println("Salaire d'Alice après augmentation : " + service.trouver("C001").getSalaire() + " €");

        List<Collaborateur> riches = service.trouverSalaireSuperieur(40000.0);
        System.out.println("Collaborateurs gagnant plus de 40 000 € (via JPQL) : " + riches.size());

        fabrique.close();

        Annuaire annuaire = new Annuaire();
        for (Collaborateur c : DonneesDemo.creerCollaborateurs()) {
            annuaire.ajouter(c);
        }

        Scanner scanner = new Scanner(System.in);
        int choix = -1;

        while (choix != 0) {
            afficherMenu();

            if (scanner.hasNextInt()) {
                choix = scanner.nextInt();
                scanner.nextLine();

                switch (choix) {
                    case 0 -> System.out.println("À bientôt !");

                    case 1 -> afficherListe(annuaire.tous());

                    case 2 -> {
                        System.out.print("Fragment de nom : ");
                        String fragment = scanner.nextLine();
                        afficherListe(annuaire.nomContenant(fragment));
                    }

                    case 3 -> {
                        System.out.print("Salaire minimum (€) : ");
                        if (scanner.hasNextDouble()) {
                            double seuil = scanner.nextDouble();
                            scanner.nextLine();
                            afficherListe(annuaire.salaireSuperieurA(seuil));
                        } else {
                            System.out.println("Saisie invalide.");
                            scanner.nextLine();
                        }
                    }

                    case 4 -> afficherListe(annuaire.programmeurs());

                    case 5 -> afficherListe(annuaire.triesParNom());

                    case 6 -> afficherListe(annuaire.triesParSalaire());

                    case 7 -> afficherListe(annuaire.triesParNomPuisPrenom());

                    case 8 -> {
                        System.out.print("Identifiant du collaborateur : ");
                        String id = scanner.nextLine();
                        Collaborateur trouve = annuaire.trouver(id);
                        if (trouve != null) {
                            System.out.println();
                            trouve.afficherFiche();
                        } else {
                            System.out.println("Collaborateur introuvable : " + id);
                        }
                    }

                    case 9 -> {
                        System.out.print("Identifiant du collaborateur : ");
                        String id = scanner.nextLine();
                        Collaborateur trouve = annuaire.trouver(id);
                        if (trouve != null) {
                            System.out.print("Pourcentage d'augmentation : ");
                            if (scanner.hasNextDouble()) {
                                double pourcentage = scanner.nextDouble();
                                scanner.nextLine();
                                double ancienSalaire = trouve.getSalaire();
                                trouve.augmenterSalaire(pourcentage);
                                System.out.printf("Salaire : %.2f € -> %.2f €%n",
                                        ancienSalaire, trouve.getSalaire());
                            } else {
                                System.out.println("Saisie invalide.");
                                scanner.nextLine();
                            }
                        } else {
                            System.out.println("Collaborateur introuvable : " + id);
                        }
                    }

                    default -> System.out.println("Option invalide. Choisissez entre 0 et 9.");
                }

            } else {
                System.out.println("Saisie invalide. Veuillez entrer un nombre.");
                scanner.nextLine();
            }
        }

        scanner.close();
    }

    private static void afficherMenu() {
        System.out.println();
        System.out.println("=== Annuaire des collaborateurs (" + "EFREI" + ") ===");
        System.out.println("1. Afficher tous les collaborateurs");
        System.out.println("2. Rechercher par nom");
        System.out.println("3. Filtrer par salaire minimum");
        System.out.println("4. Afficher les programmeurs");
        System.out.println("5. Trier par nom");
        System.out.println("6. Trier par salaire");
        System.out.println("7. Trier par nom puis prénom");
        System.out.println("8. Afficher la fiche d'un collaborateur");
        System.out.println("9. Augmenter le salaire d'un collaborateur");
        System.out.println("0. Quitter");
        System.out.print("Votre choix : ");
    }

    private static void afficherListe(List<? extends Collaborateur> liste) {
        if (liste.isEmpty()) {
            System.out.println("Aucun collaborateur trouvé.");
        } else {
            System.out.println(liste.size() + " collaborateur(s) :");
            for (Collaborateur c : liste) {
                System.out.println("  " + c);
            }
        }
    }
}