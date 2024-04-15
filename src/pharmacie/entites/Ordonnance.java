package pharmacie.entites;


public class Ordonnance implements IOrdonnance {

    private String nomPatient;
    private String prenomPatient;
    private String dateNaissance;
    private String adresse;
    private String telephone;
    private String email;
    private String dateOrdonnance;
    private String medecin;
    private String numeroOrdonnance;
    private String specialite;
    private String medicament;
    private String quantite;
    private String posologie;
    private String duree;
    private String commentaire;

    public Ordonnance(IOrdonnance ordonnance) {
    }

    @Override
    public Ordonnance build() {
            return new Ordonnance(this);
    }

    @Override
    public Ordonnance self() {
        return this;
    }

    @Override
    public Ordonnance avecNomPatient(String nomPatient) {
        this.nomPatient = nomPatient;
        return this;
    }

    @Override
    public Ordonnance avecPrenomPatient(String prenomPatient) {
        this.prenomPatient = prenomPatient;
        return this;
    }

    @Override
    public Ordonnance avecDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
        return this;
    }

    @Override
    public Ordonnance avecAdresse(String adresse) {
        this.adresse = adresse;
        return this;
    }

    @Override
    public Ordonnance avecTelephone(String telephone) {
        this.telephone = telephone;
        return this;
    }

    @Override
    public Ordonnance avecEmail(String email) {
        this.email = email;
        return this;
    }

    @Override
    public Ordonnance avecDateOrdonnance(String dateOrdonnance) {
        this.dateOrdonnance = dateOrdonnance;
        return this;
    }

    @Override
    public Ordonnance avecMedecin(String medecin) {
        this.medecin = medecin;
        return this;
    }

    @Override
    public Ordonnance avecNumeroOrdonnance(String numeroOrdonnance) {
        this.numeroOrdonnance = numeroOrdonnance;
        return this;
    }

    @Override
    public Ordonnance avecSpecialite(String specialite) {
        this.specialite = specialite;
        return this;
    }

    @Override
    public Ordonnance avecMedicament(String medicament) {
        this.medicament = medicament;
        return this;
    }

    @Override
    public Ordonnance avecQuantite(String quantite) {
        this.quantite = quantite;
        return this;
    }

    @Override
    public Ordonnance avecPosologie(String posologie) {
        this.posologie = posologie;
        return this;
    }

    @Override
    public Ordonnance avecDuree(String duree) {
        this.duree = duree;
        return this;
    }

    @Override
    public Ordonnance avecCommentaire(String commentaire) {
        this.commentaire = commentaire;
        return this;
    }
}
