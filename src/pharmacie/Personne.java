package pharmacie;

public abstract class Personne {
    private String nom;
    private String prenom;
    private String adresse;
    private String telephone;
    private String email;
    private String specialite;
    private String codePostal;
    private String ville;
    private String numeroNational;
    private String dateDeNaissance;
    private String lieuDeNaissance;
    private String sexe;
    private String numeroCarteEuroAssuranceMaladie;

    public Personne(String nomMedicament) {
        this.nom = nomMedicament;
    }

    // ... (autres attributs et méthodes)
}
