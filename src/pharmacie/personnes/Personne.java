package pharmacie.personnes;

public abstract class Personne {
    protected String nom;

    // Constructeur protégé
    protected Personne(String nom) {
        this.nom = nom;
    }

    // Getter pour le nom
    public String getNom() {
        return nom;
    }

    // Méthode abstraite pour obtenir une instance du builder approprié
    public abstract Builder medecinBuilder();
    public abstract Builder pharmacienBuilder();
    public abstract Builder patientBuilder();

    public void setNom(String nomPatient) {
        // TODO Auto-generated method stub
        this.nom = nomPatient;
    }

    // Builder pour créer des instances de Personne
    public abstract static class Builder<T extends Personne> {
        protected String nom;

        public Builder avecNom(String nom) {
            this.nom = nom;
            return this;
        }

        public abstract T build(); // Méthode abstraite pour construire une instance de T
    }
}
