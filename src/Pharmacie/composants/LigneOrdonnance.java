package Pharmacie.composants;

import java.util.Objects;

public class LigneOrdonnance {

    private Medicament medicament;
    private int quantitePrescrite;

    public LigneOrdonnance(Medicament medicament, int quantitePrescrite) {
        this.medicament = medicament;
        this.quantitePrescrite = quantitePrescrite;
    }

    // Getters and setters for all fields

    @Override
    public String toString() {
        return "LigneOrdonnance{" +
                "medicament=" + medicament +
                ", quantitePrescrite=" + quantitePrescrite +
                '}';
    }

    public Medicament getMedicament() {
        return medicament;
    }

    public int getQuantitePrescrite() {
        return 0;
    }
}

