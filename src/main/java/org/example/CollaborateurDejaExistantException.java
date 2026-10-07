package org.example;

public class CollaborateurDejaExistantException extends RuntimeException {
    private final String identifiant;

    public CollaborateurDejaExistantException(String message, String identifiant) {
        super(message);
        this.identifiant = identifiant;
    }

    public String Identifiant() {
        return this.identifiant;
    }
    
}
