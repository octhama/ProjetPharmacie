package pharmacie;

import java.util.List;

public  class Preparation {
    private List<Medicament> medicaments;
    private String nom;
    private int quantite;
    private String date;
    private double coutTotal;


    // Ajoutez ce constructeur à la classe Preparation
    public Preparation(String nom, int quantite, String date, Medicament[] medicaments, double coutTotal) {
        this.nom = nom;
        this.quantite = quantite;
        this.date = date;
        this.medicaments = List.of(medicaments);
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

    public String getNom() {
        return nom;
    }
    public int getQuantite() {
        return quantite;
    }
    public String getDate() {
        return date;
    }
}
