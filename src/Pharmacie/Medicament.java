package Pharmacie;

public class Medicament {
    private String nom;
    private double prix;
    private int quantiteEnStock;

    public Medicament(String nom, double prix, int quantiteEnStock) {
        this.nom = nom;
        this.prix = prix;
        this.quantiteEnStock = quantiteEnStock;
    }

    // Getters and setters
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public double getPrix() {
        return prix;
    }
    public void setPrix(double prix) {
        this.prix = prix;
    }
    public int getQuantiteEnStock() {
        return quantiteEnStock;
    }
    public void setQuantiteEnStock(int quantiteEnStock) {
        this.quantiteEnStock = quantiteEnStock;
    }
    @Override
    public String toString() {
        return "Medicament{" +
                "nom='" + nom + '\'' +
                ", prix=" + prix +
                ", quantiteEnStock=" + quantiteEnStock +
                '}';
    }
    public void incrementerQuantiteEnStock(int quantite) {
        this.quantiteEnStock += quantite;
    }
    public void decrementerQuantiteEnStock(int quantite) {
        this.quantiteEnStock -= quantite;
    }
    // Méthodes pour la gestion du stock
    public boolean estEnStock(int quantite) {
        return this.quantiteEnStock >= quantite;
    }
    public void vendre(int quantite) {
        if (estEnStock(quantite)) {
            this.quantiteEnStock -= quantite;
        } else {
            System.out.println("Quantité insuffisante en stock.");
        }
    }
    public void approvisionner(int quantite) {
        this.quantiteEnStock += quantite;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Medicament that = (Medicament) o;
        return nom.equals(that.nom);
    }
    @Override
    public int hashCode() {
        return nom.hashCode();
    }
    public int compareTo(Medicament o) {
        return this.nom.compareTo(o.nom);
    }
    public boolean estPlusCherQue(Medicament o) {
        return this.prix > o.prix;
    }
    public boolean estMoinsCherQue(Medicament o) {
        return this.prix < o.prix;
    }
    public boolean aLaMemePrixQue(Medicament o) {
        return this.prix == o.prix;
    }
    public boolean estPlusStockeQue(Medicament o) {
        return this.quantiteEnStock > o.quantiteEnStock;
    }
    public boolean estMoinsStockeQue(Medicament o) {
        return this.quantiteEnStock < o.quantiteEnStock;
    }
    public boolean aLaMemeQuantiteEnStockQue(Medicament o) {
        return this.quantiteEnStock == o.quantiteEnStock;
    }
    public boolean estPlusCherEtPlusStockeQue(Medicament o) {
        return this.prix > o.prix && this.quantiteEnStock > o.quantiteEnStock;
    }
    public boolean estMoinsCherEtPlusStockeQue(Medicament o) {
        return this.prix < o.prix && this.quantiteEnStock > o.quantiteEnStock;
    }
    public boolean estPlusCherEtMoinsStockeQue(Medicament o) {
        return this.prix > o.prix && this.quantiteEnStock < o.quantiteEnStock;
    }
    public boolean estMoinsCherEtMoinsStockeQue(Medicament o) {
        return this.prix < o.prix && this.quantiteEnStock < o.quantiteEnStock;
    }
    public boolean aLaMemePrixEtPlusStockeQue(Medicament o) {
        return this.prix == o.prix && this.quantiteEnStock > o.quantiteEnStock;
    }
    public boolean aLaMemePrixEtMoinsStockeQue(Medicament o) {
        return this.prix == o.prix && this.quantiteEnStock < o.quantiteEnStock;
    }
    public boolean aLaMemePrixEtMemeStockQue(Medicament o) {
        return this.prix == o.prix && this.quantiteEnStock == o.quantiteEnStock;
    }
    public boolean aLaMemePrixEtPlusCherQue(Medicament o) {
        return this.prix == o.prix && this.prix > o.prix;
    }
}
