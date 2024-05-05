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

    public DemandeVersionGenerique(String nom) {
        this.nomMedicament = nom;
    }

    public String getNomMedicament() {
        return nomMedicament;
    }

    public boolean isDemande() {
        return etatDeLaDemande;
    }


    public String isVersionGeneriqueDemandee() {
        return null;
    }
}
