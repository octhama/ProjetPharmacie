package Pharmacie;

public class Facture extends DocumentMedical implements IFacture{

    public Facture(Medecin medecin, Patient patient, Pharmacien pharmacien, Medicament medicament, MedicamentGeneric medicamentGeneric, MedicamentNonGeneric medicamentNonGeneric, MedicamentSansOrdonnance medicamentSansOrdonnance, MedicamentAvecOrdonnance medicamentAvecOrdonnance, Pharmacie pharmacie, String nomHopital, String emailHopital, String adresseHopital, String dateConsulation, String datePrescription, String dateFacturation, double prixConsulation, String numSecu, String mutuelle, String allergene, String antecedent) {
        super(medecin, patient, pharmacien, medicament, medicamentGeneric, medicamentNonGeneric, medicamentSansOrdonnance, medicamentAvecOrdonnance, pharmacie, nomHopital, emailHopital, adresseHopital, dateConsulation, datePrescription, dateFacturation, prixConsulation, numSecu, mutuelle, allergene, antecedent);
    }

    @Override
    public void imprimer() {
        System.out.println("Imprimer la facture");
    }

    @Override
    public void envoyer() {
        System.out.println("Envoyer la facture");
    }

    @Override
    public void rechercher() {

    }

    @Override
    public void ajouter() {

    }

    @Override
    public void exporter() {

    }

    @Override
    public void importer() {

    }

    @Override
    public void trier() {

    }

    @Override
    public void calculer() {

    }

    @Override
    public void valider() {
        System.out.println("Valider la facture");
    }

    @Override
    public void archiver() {
        System.out.println("Archiver la facture");
    }

    @Override
    public void supprimer() {
        System.out.println("Supprimer la facture");
    }

    @Override
    public void consulter() {
        System.out.println("Consulter la facture");
    }

    @Override
    public void afficher(){
        System.out.println("Afficher la facture");
    }

    @Override
    public void modifier() {
        System.out.println("Modifier la facture");
    }

    @Override
    public void transferer() {
        System.out.println("Transferer la facture");
    }

    @Override
    public void enregistrer() {
        System.out.println("Enregistrer la facture");
    }


}
