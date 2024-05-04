package pharmacie;

public class DemandeVersionGenerique {
    private String reference;
    private String date;
    private String nomMedicament; // Ajout du champ nomMedicament
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

    // Méthode pour obtenir le nom du médicament
    public String getNomMedicament() {
        return nomMedicament;
    }

    // Méthode pour obtenir la référence de la demande
    public String getReference() {
        return reference;
    }

    // Méthode pour obtenir la date de la demande
    public String getDate() {
        return date;
    }

    // Méthode pour vérifier si la demande a été faite
    public boolean isDemande() {
        return etatDeLaDemande;
    }

    // Méthode pour convertir la demande en format CSV
    public String toCsv() {
        return nomMedicament + "," + (isDemande() ? "1" : "0");
    }
}
