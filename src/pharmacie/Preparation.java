package pharmacie;

public class Preparation {
    private String nom;


    // Ajoutez ce constructeur à la classe Preparation
    public Preparation(String nom) {
        this.nom = nom;
    }
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Medicament getMedicament() {
        return null;
    }

}
