package pharmacie;

import enums.ETypeMedicament;
import exceptions.ExeptionRuptureDeStock;

public class MedicamentMarque extends Medicament {
    public MedicamentMarque(String nom, double prix2, ETypeMedicament eTypeMedicament, boolean generique2, int quantiteStock, boolean commandeA50Pourcent) {
        super(nom, prix2, eTypeMedicament, generique2, quantiteStock, commandeA50Pourcent);
    }

    public MedicamentMarque(String[] split, boolean commandeA50Pourcent) {
        super(split, commandeA50Pourcent);
    }

    public MedicamentMarque(String nom2, String nom3, String nom4, String nom5, String nom6, boolean commandeA50Pourcent) {
        super(nom2, nom3, nom4, nom5, nom6, commandeA50Pourcent);
    }

    public MedicamentMarque(String nom, int quantitePrescrite, boolean commandeA50Pourcent) {
        super(nom, quantitePrescrite, commandeA50Pourcent);
    }

    public MedicamentMarque(String medicamentString, boolean commandeA50Pourcent) {
        super(medicamentString, commandeA50Pourcent);
    }

    public MedicamentMarque(String nom, int quantitePrescrite) {
        super(nom, quantitePrescrite);
    }

    public MedicamentMarque(String medicamentString) {
        super(medicamentString);
    }

    public MedicamentMarque(String nom, double prix, ETypeMedicament type, boolean generique, int quantiteStock) {
        super(nom, prix, type, generique, quantiteStock);
    }

    public MedicamentMarque(String nom, double prix, int quantite, ETypeMedicament type) {
        super(nom, prix, quantite, type);
    }

    public MedicamentMarque(String nom, int prix, ETypeMedicament type, boolean generique, Integer quantiteStock) {
        super(nom, prix, type, generique, quantiteStock);
    }
}
