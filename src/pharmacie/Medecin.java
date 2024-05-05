package pharmacie;
import java.util.List;

// Classe Medecin héritant de la classe Personne

public class Medecin extends Personne{
    // Constructeur
    public Medecin(String nom, String prenom, String adresse, String telephone, String email, String specialite, String codePostal, String ville, String numeroNational, String dateDeNaissance, String lieuDeNaissance, String sexe, String numeroCarteEuroAssuranceMaladie) {
        super(nom, prenom, adresse, telephone, email, specialite, codePostal, ville, numeroNational, dateDeNaissance, lieuDeNaissance, sexe, numeroCarteEuroAssuranceMaladie);
    }

    public Medecin(String element, String element1, String element2, String element3) {
        super(element, element1, element2, element3);
    }

    public Ordonnance creerOrdonnance(Patient patient, List<Medicament> medicaments) {
        return null;
    }

    public Ordonnance creerOrdonnance(Patient patient, Preparation preparation) {
        return null;
    }
}
