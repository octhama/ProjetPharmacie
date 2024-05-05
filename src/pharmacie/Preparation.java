package pharmacie;

import java.util.Calendar;
import java.util.List;

public  class Preparation {
    public String getIdCommande;
    private List<Medicament> medicaments;
    private String nom;
    private int quantite;
    private String date;
    private double coutTotal;


    // Ajoutez ce constructeur à la classe Preparation
    public Preparation(String nom, int quantite, String date, List<Medicament> medicaments, double coutTotal) {
        this.nom = nom;
        this.quantite = quantite;
        this.date = date;
        this.medicaments = medicaments;
        this.coutTotal = coutTotal;
    }


    public Preparation() {
        this.medicaments = null;
        this.coutTotal = 0;

    }

    public Preparation(List<Medicament> selectedMedicaments) {
        this.medicaments = selectedMedicaments;
    }

    public void setMedicaments(Medicament[] medicaments) {
        this.medicaments = List.of(medicaments);
    }

    public double calculerCoutTotal() {
        double coutTotal = 0;
        for (Medicament medicament : medicaments) {
            coutTotal += medicament.getPrix();
        }
        setCoutTotal(coutTotal);
        return coutTotal;
    }

    public void setCoutTotal(Object o) {
        this.coutTotal = (double) o;
    }

    public List<Medicament> getMedicaments() {
        return medicaments;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getCoutTotal() {
        return coutTotal;
    }

    public Medicament getMedicament() {
        return null;
    }

    public Calendar getDatePreparation() {
        return null;
    }

    public String getNomMedicament() {
        return null;
    }
}
