package pharmacie.personnes;

public class Patient extends Personne {

    // Constructeur privé
    public Patient(String nom) {
        super(nom);
    }

    @Override
    public Personne.Builder medecinBuilder() {
        return null;
    }

    @Override
    public Personne.Builder pharmacienBuilder() {
        return null;
    }

    // Méthode pour créer un builder de Patient
    public Builder patientBuilder() {
        return new Builder();
    }

    // Builder pour créer des instances de Patient
    public static class Builder extends Personne.Builder<Patient> {
        public Patient build() {
            return new Patient(nom);
        }

        public Object NomPatient(String jeanDupont) {
            return NomPatient(jeanDupont);
        }
    }

    // Autres méthodes spécifiques pour Patient
}

