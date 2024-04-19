package pharmacie;
import java.util.List;

// Classe Medecin héritant de la classe Personne

public class Medecin extends Personne{
    public Medecin(String nomMedicament) {
        super(nomMedicament);
    }
    // ... (autres attributs et méthodes spécifiques aux médecins)


    public Ordonnance creerOrdonnance(Patient patient, List<Medicament> medicaments) {
        // ... (Création d'une ordonnance sans préparation)
        return null;
    }

    public Ordonnance creerOrdonnance(Patient patient, Preparation preparation) {
        // ... (Création d'une ordonnance avec préparation)
        return null;
    }
}
