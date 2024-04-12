package Pharmacie.ordonnance;

import Pharmacie.Medicament;

import java.util.List;

public class Ordonnance {
    private String referenceMedecin;
    private String referencePatient;
    private String datePrescription;
    private List<Medicament> medicamentsPrescrits;
    private List<String> ingredientsPreparation;

    public Ordonnance(String referenceMedecin, String referencePatient, String datePrescription, List<Medicament> medicamentsPrescrits, List<String> ingredientsPreparation) {
        this.referenceMedecin = referenceMedecin;
        this.referencePatient = referencePatient;
        this.datePrescription = datePrescription;
        this.medicamentsPrescrits = medicamentsPrescrits;
        this.ingredientsPreparation = ingredientsPreparation;
    }

    // Getters and setters
}

