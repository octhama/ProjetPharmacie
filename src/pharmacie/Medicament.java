package pharmacie;

import enums.ETypeMedicament;
import exceptions.ExeptionRuptureDeStock;

public class Medicament {
    private String nom;
    private double prix;
    private ETypeMedicament type;
    private boolean generique;
    private int quantiteEnStock;
    private boolean commandeA50Pourcent;

    public Medicament(String nom, double prix2, ETypeMedicament eTypeMedicament, boolean generique2, int quantiteStock, boolean commandeA50Pourcent) {
        this.nom = nom;
        this.prix = prix2;
        this.type = ETypeMedicament.valueOf(eTypeMedicament.toString());
        this.generique = generique2;
        this.quantiteEnStock = quantiteStock;
        this.commandeA50Pourcent = commandeA50Pourcent;
    }

    public Medicament(String[] split, boolean commandeA50Pourcent) {
        //TODO Auto-generated constructor stub
        this.commandeA50Pourcent = commandeA50Pourcent;
    }

    public Medicament(String nom2, String nom3, String nom4, String nom5, String nom6, boolean commandeA50Pourcent) {
        //TODO Auto-generated constructor stub
        this.nom = nom2;
        this.prix = Double.parseDouble(nom3);
        this.type = ETypeMedicament.valueOf(nom4);
        this.generique = Boolean.parseBoolean(nom5);
        this.quantiteEnStock = Integer.parseInt(nom6);
        this.commandeA50Pourcent = commandeA50Pourcent;
    }

    public Medicament(String nom, int quantitePrescrite, boolean commandeA50Pourcent) {
        //TODO Auto-generated constructor stub
        this.nom = nom;
        this.quantiteEnStock = quantitePrescrite;
        this.commandeA50Pourcent = commandeA50Pourcent;
        this.prix = 0;
        this.type = null;
        this.generique = false;
    }

    public Medicament(String medicamentString, boolean commandeA50Pourcent) {
        this.commandeA50Pourcent = commandeA50Pourcent;
        //TODO Auto-generated constructor stub
        String[] parts = medicamentString.split("\\(");
        this.nom = parts[0];
        this.quantiteEnStock = Integer.parseInt(parts[1].substring(0, parts[1].length() - 1));
    }

    public Medicament(String nom, int quantitePrescrite) {
        //TODO Auto-generated constructor stub
        this.nom = nom;
        this.quantiteEnStock = quantitePrescrite;
    }

    public Medicament(String medicamentString) {
        //TODO Auto-generated constructor stub
        String[] parts = medicamentString.split("\\(");
        this.nom = parts[0];
        this.quantiteEnStock = Integer.parseInt(parts[1].substring(0, parts[1].length() - 1));
    }

    public Medicament(String nom, double prix, ETypeMedicament type, boolean generique, int quantiteStock) {
        this.nom = nom;
        this.prix = prix;
        this.type = type;
        this.generique = generique;
        this.quantiteEnStock = quantiteStock;
    }

    public String getNom() {
        return nom;
    }

    public double getPrix() {
        return prix;
    }

    public ETypeMedicament getType() {
        return type;
    }

    public boolean isGenerique() {
        return generique;
    }

    public int getQuantiteEnStock() {
        return quantiteEnStock;
    }


    public void acheter(int quantite) throws ExeptionRuptureDeStock {
        // Augmenter la quantité en stock du médicament
        this.quantiteEnStock += quantite;
    }

    public ETypeMedicament getTypeMedicament() {
        return type;
    }

    public void setQuantiteEnStock(int quantite) {
        this.quantiteEnStock = quantite;
    }

    public void commander(int quantite) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'commander'");
    }

    public String getPrincipeActif() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPrincipeActif'");
    }

    public boolean estCommandeA50Pourcent() {
        return commandeA50Pourcent;
    }
}
