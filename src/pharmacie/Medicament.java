package pharmacie;

import enums.ETypeMedicament;
import exceptions.ExeptionRuptureDeStock;

import javax.swing.*;

public class Medicament {
    private String nom;
    private double prix;
    private String type;
    private boolean generique;
    private int quantiteEnStock;

    public Medicament(String nom, double prix2, ETypeMedicament eTypeMedicament, boolean generique2, int quantiteStock) {
        this.nom = nom;
        this.prix = prix2;
        this.type = eTypeMedicament.toString();
        this.generique = generique2;
        this.quantiteEnStock = quantiteStock;
    }

    public Medicament(String[] split) {
        //TODO Auto-generated constructor stub
    }

    public Medicament(String nom2, String nom3, String nom4, String nom5, String nom6) {
        //TODO Auto-generated constructor stub
    }

    public String getNom() {
        return nom;
    }

    public double getPrix() {
        return prix;
    }

    public String getType() {
        return type;
    }

    public boolean isGenerique() {
        return generique;
    }

    public int getQuantiteEnStock() {
        return quantiteEnStock;
    }


    public void acheter(int quantite) {
        // Augmenter la quantité en stock du médicament
        this.quantiteEnStock += quantite;
    }

    public String getTypeMedicament() {
        return type;
    }

    public void setQuantiteEnStock(int quantite) {
        this.quantiteEnStock = quantite;
    }
}
