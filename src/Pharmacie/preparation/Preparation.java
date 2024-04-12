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
    public Map<Medicament, Integer> getMedicaments() {
        return medicaments;
    }
    // Méthodes pour calculer le coût total de la préparation
    public int getCoutTotal() {
        int coutTotal = 0;
        for (Map.Entry<Medicament, Integer> entry : medicaments.entrySet()) {
            coutTotal += (int) (entry.getKey().getPrix() * entry.getValue());
        }
        return coutTotal;
    }

    public String getPatient() {
        return patient;
    }

    public boolean isEstDelivrableLeLendemain() {
        return estDelivrableLeLendemain;
    }

    public String getNumero() {
        return null;
    }

    public double calculerMontantTotal() {
        return 0;
    }

    public void ordonnancer() {

    }

    public String getNom() {
        return null;
    }

    public void archiver() {

    }

    public void delivrer() {

    }
}

