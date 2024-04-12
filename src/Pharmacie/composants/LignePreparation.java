package Pharmacie.composants;

public class LignePreparation {

    private Medicament medicament;
    private double quantite;

    public LignePreparation(Medicament medicament, double quantite) {
        this.medicament = medicament;
        this.quantite = quantite;
    }

    // Getters and setters for all fields

    @Override
    public String toString() {
        return "LignePreparation{" +
                "medicament=" + medicament +
                ", quantite=" + quantite +
                '}';
    }

    public Medicament getMedicament() {
        return medicament;
    }

    public int getQuantite() {
        return 0;
    }
}

