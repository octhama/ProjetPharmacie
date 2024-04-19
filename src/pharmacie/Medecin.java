package pharmacie;
import java.util.List;

// Classe Medecin héritant de la classe Personne

public class Medecin extends Personne{
    // Constructeur
    public Medecin(String nom, String prenom, String adresse, String telephone, String email, String specialite, String codePostal, String ville, String numeroNational, String dateDeNaissance, String lieuDeNaissance, String sexe, String numeroCarteEuroAssuranceMaladie) {
        super(nom, prenom, adresse, telephone, email, specialite, codePostal, ville, numeroNational, dateDeNaissance, lieuDeNaissance, sexe, numeroCarteEuroAssuranceMaladie);
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
