package pharmacie;
/**
 * Classe pour les patients.
 * On peut créer un patient.
 * @see Personne
 * @see Patient#Patient(String, String, String, String)
 */
public class Patient extends Personne{
    public Patient(String Id, String MotDePasse, String nom, String prenom) {
        super(Id, MotDePasse, nom, prenom);
    }
}
