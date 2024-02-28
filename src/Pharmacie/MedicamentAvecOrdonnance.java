package Pharmacie;

public class MedicamentAvecOrdonnance extends Medicament{
    public MedicamentAvecOrdonnance(String nom, String dosage, String forme, String quantite, double prix, boolean generique, boolean ordonnance, boolean remboursement, String laboratoire, boolean stockRupture) {
        super(nom, dosage, forme, quantite, prix, generique, ordonnance, remboursement, laboratoire, stockRupture);
    }
}
