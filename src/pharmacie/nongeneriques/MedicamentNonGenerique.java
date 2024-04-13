package pharmacie.nongeneriques;

import pharmacie.entites.Medicament;

public class MedicamentNonGenerique extends Medicament {
    public MedicamentNonGenerique(String nom, double prix, int quantiteEnStock) {
        super(nom, prix, quantiteEnStock);
    }

    // Builder pour MedicamentNonGenerique
public static class Builder extends Medicament.Builder<MedicamentNonGenerique> {
        @Override
        public MedicamentNonGenerique build() {
            return new MedicamentNonGenerique(nom, prix, quantiteEnStock);
        }
    }

}
