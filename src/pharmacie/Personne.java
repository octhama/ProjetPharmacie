package pharmacie;

public abstract class Personne {
    private String Id;
    private String NumeroNational;
    private String Reference;
    private String MotDePasse;
    private String civilite;
    private String nom;
    private String prenom;
    private String adresse;
    private String ContactGSM;
    private String statut;
    private String historiqueOrdonnance;

    public Personne(String Id, String NumeroNational, String Reference, String MotDePasse, String civilite, String nom, String prenom, String adresse, String ContactGSM, String statut) {
        this.Id = Id;
        this.NumeroNational = NumeroNational;
        this.Reference = Reference;
        this.MotDePasse = MotDePasse;
        this.civilite = civilite;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.ContactGSM = ContactGSM;
        this.statut = statut;
    }

    public Personne(String Id, String MotDePasse, String nom, String prenom) {
        this.Id = Id;
        this.MotDePasse = MotDePasse;
        this.nom = nom;
        this.prenom = prenom;
    }

}
