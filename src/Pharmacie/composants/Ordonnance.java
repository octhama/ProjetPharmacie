package Pharmacie.composants;

import java.util.Date;
import java.util.List;

public class Ordonnance {

    private int id;
    private String nomPatient;
    private List<LigneOrdonnance> lignesOrdonnance;

    public Ordonnance(int id, String nomPatient, List<LigneOrdonnance> lignesOrdonnance) {
        this.id = id;
        this.nomPatient = nomPatient;
        this.lignesOrdonnance = lignesOrdonnance;
    }

    // Getters and setters for all fields

    @Override
    public String toString() {
        return "Ordonnance{" +
                "id=" + id +
                ", nomPatient='" + nomPatient + '\'' +
                ", lignesOrdonnance=" + lignesOrdonnance +
                '}';
    }

    public Iterable<? extends LigneOrdonnance> getLignesOrdonnance() {
        return lignesOrdonnance;
    }

    public String getNomPatient() {
        return nomPatient;
    }
}



