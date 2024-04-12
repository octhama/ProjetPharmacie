package Pharmacie.composants;

public class Medicament {

    private int id;
    private String nom;
    private String marque;
    private double prixUnitaire;
    private int quantiteStock;
    private boolean delivrableLibrement;
    private boolean necessitantOrdonnance;

    public Medicament(int id, String nom, String marque, double prixUnitaire, int quantiteStock, boolean delivrableLibrement, boolean necessitantOrdonnance) {
        this.id = id;
        this.nom = nom;
        this.marque = marque;
        this.prixUnitaire = prixUnitaire;
        this.quantiteStock = quantiteStock;
        this.delivrableLibrement = delivrableLibrement;
        this.necessitantOrdonnance = necessitantOrdonnance;
    }

    // Getters and setters for all fields

    @Override
    public String toString() {
        return "Medicament{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", marque='" + marque + '\'' +
                ", prixUnitaire=" + prixUnitaire +
                ", quantiteStock=" + quantiteStock +
                ", delivrableLibrement=" + delivrableLibrement +
                ", necessitantOrdonnance=" + necessitantOrdonnance +
                '}';
    }

    public Integer getQuantiteStock() {
        return quantiteStock;
    }

    public String getNom() {
        return nom;
    }

    public Object getMarque() {
        return marque;
    }

    public Object getPrixUnitaire() {
        return prixUnitaire;
    }
}



