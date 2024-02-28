package Pharmacie;

public class Medicament{

    //Attributs
    private String nom;
    private String dosage;
    private String forme;
    private String quantite;
    private double prix;
    private boolean generique;
    private boolean ordonnance;
    private boolean remboursement;
    private String laboratoire;
    private boolean stockRupture;

    //Constructeur
    public Medicament(String nom, String dosage, String forme, String quantite, double prix, boolean generique, boolean ordonnance, boolean remboursement, String laboratoire, boolean stockRupture) {
        this.nom = nom;
        this.dosage = dosage;
        this.forme = forme;
        this.quantite = quantite;
        this.prix = prix;
        this.generique = generique;
        this.ordonnance = ordonnance;
        this.remboursement = remboursement;
        this.laboratoire = laboratoire;
        this.stockRupture = stockRupture;
    }

    //Getters
    public String getNom() {
        return nom;
    }

    public String getDosage() {
        return dosage;
    }

    public String getForme() {
        return forme;
    }

    public String getQuantite() {
        return quantite;
    }

    public double getPrix() {
        return prix;
    }

    public boolean isGenerique() {
        return generique;
    }

    public boolean isOrdonnance() {
        return ordonnance;
    }

    public boolean isRemboursement() {
        return remboursement;
    }

    public String getLaboratoire() {
        return laboratoire;
    }

    public boolean isStockRupture() {
        return stockRupture;
    }

    //Setters
    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public void setForme(String forme) {
        this.forme = forme;
    }

    public void setQuantite(String quantite) {
        this.quantite = quantite;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public void setGenerique(boolean generique) {
        this.generique = generique;
    }

    public void setOrdonnance(boolean ordonnance) {
        this.ordonnance = ordonnance;
    }

    public void setRemboursement(boolean remboursement) {
        this.remboursement = remboursement;
    }

    public void setLaboratoire(String laboratoire) {
        this.laboratoire = laboratoire;
    }

    public void setStockRupture(boolean stockRupture) {
        this.stockRupture = stockRupture;
    }

    @Override
    public String toString() {
        return "Medicament [nom=" + nom + ", dosage=" + dosage + ", forme=" + forme + ", quantite=" + quantite
                + ", prix=" + prix + ", generique=" + generique + ", ordonnance=" + ordonnance + ", remboursement="
                + remboursement + ", laboratoire=" + laboratoire + ", stockRupture=" + stockRupture + "]";
    }

    //Méthodes
    public void ajouterMedicament(Medicament medicament){

    }

    public void supprimerMedicament(Medicament medicament){

    }


}
