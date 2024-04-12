package Pharmacie.ordonnance;

import Pharmacie.Medicament;

import java.util.List;

public class Ordonnance {
    private String referenceMedecin;
    private String referencePatient;
    private String datePrescription;
    private List<Medicament> medicamentsPrescrits;
    private List<String> ingredientsPreparation;

    public Ordonnance(String referenceMedecin, String referencePatient, String datePrescription, List<Medicament> medicamentsPrescrits) {
        this.referenceMedecin = referenceMedecin;
        this.referencePatient = referencePatient;
        this.datePrescription = datePrescription;
        this.medicamentsPrescrits = medicamentsPrescrits;
        this.ingredientsPreparation = ingredientsPreparation;
    }

    // Getters and setters
    public String getReferenceMedecin() {
        return referenceMedecin;
    }
    public void setReferenceMedecin(String referenceMedecin) {
        this.referenceMedecin = referenceMedecin;
    }

    public String getReferencePatient() {
        return referencePatient;
    }
    public void setReferencePatient(String referencePatient) {
        this.referencePatient = referencePatient;
    }

    public String getDatePrescription() {
        return datePrescription;
    }
    public void setDatePrescription(String datePrescription) {
        this.datePrescription = datePrescription;
    }

    public List<Medicament> getMedicamentsPrescrits() {
        return medicamentsPrescrits;
    }
    public void setMedicamentsPrescrits(List<Medicament> medicamentsPrescrits) {
        this.medicamentsPrescrits = medicamentsPrescrits;
    }

    public List<String> getIngredientsPreparation() {
        return ingredientsPreparation;
    }
    public void setIngredientsPreparation(List<String> ingredientsPreparation) {
        this.ingredientsPreparation = ingredientsPreparation;
    }

    // Méthodes supplémentaires

    @Override
    public String toString() {
        return "Ordonnance{" +
                "referenceMedecin='" + referenceMedecin + '\'' +
                ", referencePatient='" + referencePatient + '\'' +
                ", datePrescription='" + datePrescription + '\'' +
                ", medicamentsPrescrits=" + medicamentsPrescrits +
                ", ingredientsPreparation=" + ingredientsPreparation +
                '}';
    }

    public double getPrixTotal() {
        double prixTotal = 0;
        for (Medicament medicament : medicamentsPrescrits) {
            prixTotal += medicament.getPrix();
        }
        return prixTotal;
    }

    public boolean estValide() {
        return referenceMedecin != null && referencePatient != null && datePrescription != null && medicamentsPrescrits != null && ingredientsPreparation != null;
    }

    public boolean estComplete() {
        return estValide() && !medicamentsPrescrits.isEmpty() && !ingredientsPreparation.isEmpty();
    }

    public boolean estValidePourPatient(String referencePatient) {
        return this.referencePatient.equals(referencePatient);
    }

    public boolean estValidePourMedecin(String referenceMedecin) {
        return this.referenceMedecin.equals(referenceMedecin);
    }

    public boolean estValidePourDate(String datePrescription) {
        return this.datePrescription.equals(datePrescription);
    }

    public boolean estValidePourMedicaments(List<Medicament> medicamentsPrescrits) {
        return this.medicamentsPrescrits.equals(medicamentsPrescrits);
    }

    public boolean estValidePourIngredients(List<String> ingredientsPreparation) {
        return this.ingredientsPreparation.equals(ingredientsPreparation);
    }

    public boolean estValidePourPrixTotal(double prixTotal) {
        return this.getPrixTotal() == prixTotal;
    }

    public String getNomDuMedecin() {
        return null;
    }

    public String getNomDuPatient() {
        return null;
    }

    public String getDateDePrescription() {
        return null;
    }

    public String getMedicaments() {
        return null;
    }
}

