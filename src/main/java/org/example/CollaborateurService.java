package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import java.util.List;

public class CollaborateurService {
    private final EntityManagerFactory emf;

    public CollaborateurService(EntityManagerFactory emf) {
        this.emf = emf;
    }

    // pr persister un collaborateur
    public void persister(Collaborateur c) {
        try (EntityManager em = emf.createEntityManager()) {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            try {
                em.persist(c);
                transaction.commit();
            } catch (RuntimeException e) {
                transaction.rollback();
                throw e;
            }
        }
    }

    // pr rouver un collaborateur avec identifiant
    public Collaborateur trouver(String identifiant) {
        try (EntityManager em = emf.createEntityManager()) {
            return em.find(Collaborateur.class, identifiant);
        }
    }

    public void augmenterSalaireCollaborateur(String id, double pourcentage) {
        try (EntityManager em = emf.createEntityManager()) {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            try {
                Collaborateur c = em.find(Collaborateur.class, id);
                if (c != null) {
                    c.augmenterSalaire(pourcentage); // Modification en mémoire
                }
                transaction.commit(); // C'est ici qu'Hibernate génère automatiquement le SQL UPDATE !
            } catch (RuntimeException e) {
                transaction.rollback();
                throw e;
            }
        }

    }

    public List<Collaborateur> trouverSalaireSuperieur(double seuil) {
        try (EntityManager em = emf.createEntityManager()) {
            return em.createQuery(
                            "select c from Collaborateur c where c.salaire > :seuil",
                            Collaborateur.class)
                    .setParameter("seuil", seuil)
                    .getResultList();
        }
    }
}