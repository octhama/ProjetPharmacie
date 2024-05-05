package pharmacie;

public class DemandeVersionGenerique {
    private String reference;
    private String date;
    private String nomMedicament;
    private boolean etatDeLaDemande;

    public DemandeVersionGenerique(String reference, String date, String nomMedicament, boolean etatDeLaDemande) {
        this.reference = reference;
        this.date = date;
        this.nomMedicament = nomMedicament;
        this.etatDeLaDemande = etatDeLaDemande;
    }

    public DemandeVersionGenerique() {
        this.reference = "";
        this.date = "";
        this.nomMedicament = "";
        this.etatDeLaDemande = false;
    }

    public DemandeVersionGenerique(String nom, boolean b) {
        this.nomMedicament = nom;
        this.etatDeLaDemande = b;
    }

    public String getNomMedicament() {
        return nomMedicament;
    }

    public String getReference() {
        return reference;
    }

    public String getDate() {
        return date;
    }

    public boolean isDemande() {
        return etatDeLaDemande;
    }

    // Méthode pour convertir la demande en format CSV
    public String toCsv() {
        return nomMedicament + "," + (isDemande() ? "1" : "0") + "," + date;
    }
}
