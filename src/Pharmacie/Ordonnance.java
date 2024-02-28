package Pharmacie;

public class Ordonnance extends DocumentMedical{

    public Ordonnance(Medecin medecin, Patient patient, Pharmacien pharmacien, Medicament medicament, MedicamentGeneric medicamentGeneric, MedicamentNonGeneric medicamentNonGeneric, MedicamentSansOrdonnance medicamentSansOrdonnance, MedicamentAvecOrdonnance medicamentAvecOrdonnance, Pharmacie pharmacie, String nomHopital, String emailHopital, String adresseHopital, String dateConsulation, String datePrescription, String dateFacturation, double prixConsulation, String numSecu, String mutuelle, String allergene, String antecedent) {
        super(medecin, patient, pharmacien, medicament, medicamentGeneric, medicamentNonGeneric, medicamentSansOrdonnance, medicamentAvecOrdonnance, pharmacie, nomHopital, emailHopital, adresseHopital, dateConsulation, datePrescription, dateFacturation, prixConsulation, numSecu, mutuelle, allergene, antecedent);
    }
}
