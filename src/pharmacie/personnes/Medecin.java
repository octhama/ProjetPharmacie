package pharmacie.personnes;

public class Medecin extends Personne {

    // Constructeur privé
    public Medecin(String nom) {
        super(nom);
    }

    // Méthode pour créer un builder de Medecin
    public Builder medecinBuilder() {
        return new Builder();
    }

    @Override
    public Personne.Builder pharmacienBuilder() {
        return null;
    }

    @Override
    public Personne.Builder patientBuilder() {
        return null;
    }

    // Builder pour créer des instances de Medecin
    public static class Builder extends Personne.Builder<Medecin> {
        public Medecin build() {
            return new Medecin(nom);
        }
    }

    // Autres méthodes spécifiques pour Medecin
}

