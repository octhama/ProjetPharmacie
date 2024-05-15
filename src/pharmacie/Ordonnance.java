package pharmacie;

import java.time.LocalDate;
import java.util.List;

public class Ordonnance {
    private LocalDate datePrescription;
    private List<Medicament> medicaments;

    public Ordonnance(LocalDate datePrescription, List<Medicament> medicaments) {
        this.datePrescription = datePrescription;
        this.medicaments = medicaments;
    }

}
