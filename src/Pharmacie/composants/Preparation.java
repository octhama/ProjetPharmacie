package Pharmacie.composants;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class Preparation {

    private Ordonnance ordonnance;
    private String nomPatient;
    private List<LignePreparation> lignesPreparation;
    private boolean pret;

    public Preparation(Ordonnance ordonnance, String nomPatient, List<LignePreparation> lignesPreparation) {
        this.ordonnance = ordonnance;
        this.nomPatient = nomPatient;
        this.lignesPreparation = lignesPreparation;
        this.pret = false;
    }

    // Getters and setters for all fields

    public void marquerPret() {
        this.pret = true;
    }

    @Override
    public String toString() {
        return "Preparation{" +
                "ordonnance=" + ordonnance +
                ", nomPatient='" + nomPatient + '\'' +
                ", lignesPreparation=" + lignesPreparation +
                ", pret=" + pret +
                '}';
    }

    public String getNom() {
        return nomPatient;
    }

    public String getNomPatient() {
        return nomPatient;
    }
}



