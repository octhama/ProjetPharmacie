package Pharmacie.medicament;

import Pharmacie.Medicament;

public class MedicamentOrdinaire extends Medicament {
    public MedicamentOrdinaire(String nom, double prix, int quantiteEnStock) {
        super(nom, prix, quantiteEnStock);
    }

    // Méthodes spécifiques aux médicaments délivrables librement
    public void vendre(int quantite) {
        if (estEnStock(quantite)) {
            decrementerQuantiteEnStock(quantite);
        } else {
            System.out.println("Quantité insuffisante en stock.");
        }
    }

    public boolean estEnStock(int quantite) {
        int quantiteEnStock = getQuantiteEnStock();
        return quantiteEnStock >= quantite;
    }

    public void decrementerQuantiteEnStock(int quantite) {
        int quantiteEnStock = getQuantiteEnStock();
        quantiteEnStock -= quantite;
        setQuantiteEnStock(quantiteEnStock);
    }

    public void setQuantiteEnStock(int quantiteEnStock) {
        int quantite = 0;
        this.setQuantiteEnStock(quantite);
    }

    @Override
    public String toString() {
        String nom = "";
        String prix = "";
        return "MedicamentOrdinaire{" +
                "nom='" + nom + '\'' +
                ", prix=" + prix +
                ", quantiteEnStock=" + getQuantiteEnStock() +
                '}';
    }  
}
