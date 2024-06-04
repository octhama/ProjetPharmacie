package pharmacie;
/**
 * Classe pour les médecins.
 * On peut créer un médecin.
 * @see Personne
 */
public class Medecin extends Personne{
    // Constructeur
    public Medecin(String Id, String MotDePasse, String nom, String prenom) {
        super(Id, MotDePasse, nom, prenom);
    }
}
