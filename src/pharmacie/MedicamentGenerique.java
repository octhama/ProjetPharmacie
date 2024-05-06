package pharmacie;

import enums.ETypeMedicament;
import exceptions.ExeptionRuptureDeStock;

public class MedicamentGenerique extends Medicament{

    public MedicamentGenerique(String nom, double prix2, ETypeMedicament eTypeMedicament, boolean generique2, int quantiteStock, boolean commandeA50Pourcent) {
        super(nom, prix2, eTypeMedicament, generique2, quantiteStock, commandeA50Pourcent);
    }

    public MedicamentGenerique(String[] split, boolean commandeA50Pourcent) {
        super(split, commandeA50Pourcent);
    }

    public MedicamentGenerique(String nom2, String nom3, String nom4, String nom5, String nom6, boolean commandeA50Pourcent) {
        super(nom2, nom3, nom4, nom5, nom6, commandeA50Pourcent);
    }

    public MedicamentGenerique(String nom, int quantitePrescrite, boolean commandeA50Pourcent) {
        super(nom, quantitePrescrite, commandeA50Pourcent);
    }

    public MedicamentGenerique(String medicamentString, boolean commandeA50Pourcent) {
        super(medicamentString, commandeA50Pourcent);
    }

    public MedicamentGenerique(String nom, int quantitePrescrite) {
        super(nom, quantitePrescrite);
    }

    public MedicamentGenerique(String medicamentString) {
        super(medicamentString);
    }

    public MedicamentGenerique(String nom, double prix, ETypeMedicament type, boolean generique, int quantiteStock) {
        super(nom, prix, type, generique, quantiteStock);
    }

    public MedicamentGenerique(String nom, double prix, int quantite, ETypeMedicament type) {
        super(nom, prix, quantite, type);
    }

    public MedicamentGenerique(String nom, int prix, ETypeMedicament type, boolean generique, Integer quantiteStock) {
        super(nom, prix, type, generique, quantiteStock);
    }
}