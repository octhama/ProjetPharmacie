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

    public Personne(String nom, String prenom, String adresse, String telephone, String email, String specialite, String codePostal, String ville, String numeroNational, String dateDeNaissance, String lieuDeNaissance, String sexe, String numeroCarteEuroAssuranceMaladie) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.telephone = telephone;
        this.email = email;
        this.specialite = specialite;
        this.codePostal = codePostal;
        this.ville = ville;
        this.numeroNational = numeroNational;
        this.dateDeNaissance = dateDeNaissance;
        this.lieuDeNaissance = lieuDeNaissance;
        this.sexe = sexe;
        this.numeroCarteEuroAssuranceMaladie = numeroCarteEuroAssuranceMaladie;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getPrenom() {
        return prenom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public String getAdresse() {
        return adresse;
    }
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
    public String getTelephone() {
        return telephone;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getSpecialite() {
        return specialite;
    }
    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }
    public String getCodePostal() {
        return codePostal;
    }
    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }
    public String getVille() {
        return ville;
    }
    public void setVille(String ville) {
        this.ville = ville;
    }
    public String getNumeroNational() {
        return numeroNational;
    }
    public void setNumeroNational(String numeroNational) {
        this.numeroNational = numeroNational;
    }
    public String getDateDeNaissance() {
        return dateDeNaissance;
    }
    public void setDateDeNaissance(String dateDeNaissance) {
        this.dateDeNaissance = dateDeNaissance;
    }
    public String getLieuDeNaissance() {
        return lieuDeNaissance;
    }
    public void setLieuDeNaissance(String lieuDeNaissance) {
        this.lieuDeNaissance = lieuDeNaissance;
    }
    public String getSexe() {
        return sexe;
    }
    public void setSexe(String sexe) {
        this.sexe = sexe;
    }
    public String getNumeroCarteEuroAssuranceMaladie() {
        return numeroCarteEuroAssuranceMaladie;
    }
    public void setNumeroCarteEuroAssuranceMaladie(String numeroCarteEuroAssuranceMaladie) {
        this.numeroCarteEuroAssuranceMaladie = numeroCarteEuroAssuranceMaladie;
    }
}
