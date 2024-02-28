package Pharmacie;

public class Preparation {

    private String nom;
    private String quantite;
    private String temps;
    private String description;
    private DocumentMedical documentMedical;
    private Medicament medicament;

    public Preparation(String nom, String quantite, String temps, String description, DocumentMedical documentMedical, Medicament medicament) {
        this.nom = nom;
        this.quantite = quantite;
        this.temps = temps;
        this.description = description;
        this.documentMedical = documentMedical;
        this.medicament = medicament;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getQuantite() {
        return quantite;
    }

    public void setQuantite(String quantite) {
        this.quantite = quantite;
    }

    public String getTemps() {
        return temps;
    }

    public void setTemps(String temps) {
        this.temps = temps;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DocumentMedical getDocumentMedical() {
        return documentMedical;
    }

    public void setDocumentMedical(DocumentMedical documentMedical) {
        this.documentMedical = documentMedical;
    }

    public Medicament getMedicament() {
        return medicament;
    }

    public void setMedicament(Medicament medicament) {
        this.medicament = medicament;
    }

    @Override
    public String toString() {
        return "Preparation{" + "nom=" + nom + ", quantite=" + quantite + ", temps=" + temps + ", description=" + description + ", documentMedical=" + documentMedical + ", medicament=" + medicament + '}';
    }
}
