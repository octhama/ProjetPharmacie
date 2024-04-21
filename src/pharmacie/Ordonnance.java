package pharmacie;

import java.time.LocalDate;
import java.util.List;

public class Ordonnance {
    private String referenceMedecin;
    private String referencePatient;
    private LocalDate datePrescription;
    private List<Medicament> medicaments;

    public Ordonnance(String referenceMedecin, String referencePatient, LocalDate datePrescription, List<Medicament> medicaments) {
        this.referenceMedecin = referenceMedecin;
        this.referencePatient = referencePatient;
        this.datePrescription = datePrescription;
        this.medicaments = medicaments;
    }

    public Ordonnance(LocalDate datePrescription, List<Medicament> medicaments) {
        this.datePrescription = datePrescription;
        this.medicaments = medicaments;
    }

    public Ordonnance(List<Medicament> selectedMedicaments) {
        this.medicaments = selectedMedicaments;
    }

    public String getReferenceMedecin() {
        return referenceMedecin;
    }
    public String getReferencePatient() {
        return referencePatient;
    }
    public LocalDate getDatePrescription() {
        return datePrescription;
    }
    public void setReferenceMedecin(String referenceMedecin) {
        this.referenceMedecin = referenceMedecin;
    }
    public void setReferencePatient(String referencePatient) {
        this.referencePatient = referencePatient;
    }
    public void setDatePrescription(LocalDate datePrescription) {
        this.datePrescription = datePrescription;
    }
    public void setMedicaments(List<Medicament> medicaments) {
        this.medicaments = medicaments;
    }
    public void addMedicament(Medicament medicament) {
        medicaments.add(medicament);
    }
    public void removeMedicament(Medicament medicament) {
        medicaments.remove(medicament);
    }
    public Medicament getMedicament(int index) {
        return medicaments.get(index);
    }
    public int getNbMedicaments() {
        return medicaments.size();
    }
    public boolean containsMedicament(Medicament medicament) {
        return medicaments.contains(medicament);
    }
    public boolean isEmpty() {
        return medicaments.isEmpty();
    }
    public void clear() {
        medicaments.clear();
    }
    public Medicament[] getMedicaments() {
        return medicaments.toArray(new Medicament[0]);
    }
    public void setMedicaments(Medicament[] medicaments) {
        this.medicaments = List.of(medicaments);
    }

    public char[] getReferencesMedecin() {
        return null;
    }

    public char[] getReferencesPatient() {
        return null;
    }

    // Ajoute d'autres méthodes si nécessaire
}
