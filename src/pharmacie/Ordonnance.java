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

    public void setMedicaments(List<Medicament> medicaments) {
        this.medicaments = medicaments;
    }
    public Medicament[] getMedicaments() {
        return medicaments.toArray(new Medicament[0]);
    }
    public void setMedicaments(Medicament[] medicaments) {
        this.medicaments = List.of(medicaments);
    }

}
