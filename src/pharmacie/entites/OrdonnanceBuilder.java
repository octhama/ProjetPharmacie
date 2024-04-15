package pharmacie.entites;

public abstract class OrdonnanceBuilder<T extends OrdonnanceBuilder<T>> {
    protected String nomPatient;
    protected String prenomPatient;
    protected String dateNaissance;
    protected String adresse;
    protected String telephone;
    protected String email;
    protected String dateOrdonnance;
    protected String medecin;
    protected String specialite;
    protected String medicament;
    protected String posologie;
    protected String quantite;
    protected String duree;
    protected String renouvellement;
    protected String observation;
    protected String signature;

    public T avecNomPatient(String nomPatient) {
        this.nomPatient = nomPatient;
        return self();
    }

    public T avecPrenomPatient(String prenomPatient) {
        this.prenomPatient = prenomPatient;
        return self();
    }

    public T avecDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
        return self();
    }

    public T avecAdresse(String adresse) {
        this.adresse = adresse;
        return self();
    }

    public T avecTelephone(String telephone) {
        this.telephone = telephone;
        return self();
    }

    public T avecEmail(String email) {
        this.email = email;
        return self();
    }

    public T avecDateOrdonnance(String dateOrdonnance) {
        this.dateOrdonnance = dateOrdonnance;
        return self();
    }

    public T avecMedecin(String medecin) {
        this.medecin = medecin;
        return self();
    }

    public T avecSpecialite(String specialite) {
        this.specialite = specialite;
        return self();
    }

    public T avecMedicament(String medicament) {
        this.medicament = medicament;
        return self();
    }

    public T avecPosologie(String posologie) {
        this.posologie = posologie;
        return self();
    }

    public T avecQuantite(String quantite) {
        this.quantite = quantite;
        return self();
    }

    public T avecDuree(String duree) {
        this.duree = duree;
        return self();
    }

    public T avecRenouvellement(String renouvellement) {
        this.renouvellement = renouvellement;
        return self();
    }

    public T avecObservation(String observation)
    {
        this.observation = observation;
        return self();
    }
    
    public T avecSignature(String signature)
    {
        this.signature = signature;
        return self();
    }
    
    protected abstract T self();
    
    public abstract Ordonnance build();
}
