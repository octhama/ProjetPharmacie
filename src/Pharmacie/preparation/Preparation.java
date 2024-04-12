package Pharmacie.preparation;

import Pharmacie.Medicament;

import java.util.Map;

public class Preparation {
    private Map<Medicament, Integer> medicaments;
    private String patient;
    private boolean estDelivrableLeLendemain;

    public Preparation(Map<Medicament, Integer> medicaments, String patient, boolean estDelivrableLeLendemain) {
        this.medicaments = medicaments;
        this.patient = patient;
        this.estDelivrableLeLendemain = estDelivrableLeLendemain;
    }

    // Getters and setters
    // Méthodes pour calculer le coût total de la préparation
}

