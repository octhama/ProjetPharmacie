package Pharmacie.services;

import Pharmacie.composants.*;
import Pharmacie.interfaces.IOrdonnanceService;

import java.util.ArrayList;
import java.util.List;

public class OrdonnanceService<Pharmacie> implements IOrdonnanceService {

    private GestionStockService gestionStockService;
    private Pharmacie pharmacie;
    private List<Ordonnance> historiqueOrdonnances;

    public OrdonnanceService(Pharmacie pharmacie) {
        this.pharmacie = pharmacie;
        this.gestionStockService = gestionStockService;
        this.historiqueOrdonnances = new ArrayList<>();
    }

    @Override
    public Preparation preparerOrdonnance(Ordonnance ordonnance) throws UnsupportedOperationException {
        List<LignePreparation> lignesPreparation = new ArrayList<>();
        boolean allAvailable = true;
        for (LigneOrdonnance ligneOrdonnance : ordonnance.getLignesOrdonnance()) {
            Medicament medicament = ligneOrdonnance.getMedicament();
            int quantitePrescrite = ligneOrdonnance.getQuantitePrescrite();
            if (!gestionStockService.isMedicamentDisponible(medicament, quantitePrescrite)) {
                allAvailable = false;
                break;
            }
            lignesPreparation.add(new LignePreparation(medicament, quantitePrescrite));
        }

        if (allAvailable) {
            for (LignePreparation lignePreparation : lignesPreparation) {
                gestionStockService.retirerMedicament(lignePreparation.getMedicament(), lignePreparation.getQuantite());
            }
            Preparation preparation = new Preparation(ordonnance, ordonnance.getNomPatient(), lignesPreparation);
            historiqueOrdonnances.add(ordonnance);
            return preparation;
        } else {
            throw new Error("Un ou plusieurs médicaments indisponibles pour la préparation de l'ordonnance.");
        }
    }

    @Override
    public void delivrerPreparation(Preparation preparation) {
        // Mark the preparation as delivered (implementation can vary depending on your logic)
        preparation.marquerPret();
        System.out.println("Préparation délivrée au patient " + preparation.getNomPatient() + ".");
    }

    @Override
    public List<Ordonnance> getHistoriqueOrdonnances() {
        return historiqueOrdonnances;
    }

    @Override
    public void recevoirOrdonnance(Ordonnance ordonnance) {
        System.out.println("Ordonnance reçue pour le patient " + ordonnance.getNomPatient() + ".");
    }
}

