package pharmacie;

import exceptions.ExeptionRuptureDeStock;

import java.util.Date;
import java.util.List;

public class Ordonnance {
    private String numero;
    private Medecin medecin;
    private Patient patient;
    private Date datePrescription;
    // pour les ordonnances sans préparation
    private Preparation preparation; // pour les ordonnances avec préparation

    public Ordonnance(Medicament nouveauMedicament, int i, List<Medicament> medicaments) {
        medicaments.add(nouveauMedicament);
    }

    public Ordonnance(List<Medicament> medicaments, Preparation nouvellePreparation) {
        preparation = nouvellePreparation;
    }

    // ... (autres attributs et méthodes)

    public boolean estValide() {
        return false;
    }

    public List<Medicament> getMedicamentsADispenser() throws ExeptionRuptureDeStock {
        return null;
    }

    public void close() {
        // ... (Fermeture de l'ordonnance)
        if (preparation != null) {
            preparation.close();
        }
        System.out.println("L'ordonnance a été fermée.");
    }
}