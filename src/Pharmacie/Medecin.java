package Pharmacie;

public class Medecin extends Personne{
    public Medecin(String nom, String prenom, String adresse, String telephone, String email) {
        super(nom, prenom, adresse, telephone, email);
    }
    private String specialite;

    public String getSpecialite() {
        return specialite;
    }private String diplome;
    public String getDiplome() {
        return diplome;
    }
    public void setDiplome(String diplome) {
        this.diplome = diplome;
    }
    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }
}
