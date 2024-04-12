package pharmacie.generiques;

import pharmacie.entites.Medicament;

public class MedicamentGenerique extends Medicament {
    // Constructeur
    public MedicamentGenerique(String nom, double prix, int quantiteEnStock) {
        super(nom, prix, quantiteEnStock);
    }

    // Builder pour MedicamentGenerique
    public static class Builder extends Medicament.Builder<MedicamentGenerique> {
        @Override
        public MedicamentGenerique build() {
            return new MedicamentGenerique(nom, prix, quantiteEnStock);
        }
    }
}

