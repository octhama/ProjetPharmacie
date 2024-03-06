package Pharmacie;

import Pharmacie.Pharmacie;

public class Main {
/**
 *Dans une pharmacie, les personnes viennent
 * Chercher des médicaments
 * Commander des préparations.
 *
 * Il y a plusieurs médicaments dans une pharmacie :
 * Certains sont de stock
 * Les autres doivent être commandés
 *
 * Il y a 2 catégories de médicaments :
 * Ceux qui sont délivrables librement
 * Ceux pour lesquels il faut une ordonnance
 *
 * Pour chaque médicament, il est possible de demander la version générique plutôt que la marque (sauf pour les préparations). Les médecins peuvent éventuellement mettre le médicament générique sur l'ordonnance.
 *
 * Les génériques coûtent moins chers.
 *
 * Pour commander une préparation, il faut une ordonnance.
 * Une préparation contient plusieurs médicaments et est délivrable le lendemain (sauf le dimanche).
 * S'il ne faut que 50% d'un médicament pour la préparation, il faut malgré tout payer le prix complet du médicament et le reste est donné au patient. Il peut revenir avec le reste pour une prochaine préparation.
 *
 * Les médicaments commandés arrivent le lendemain (sauf le dimanche)
 *
 * Les ordonnances sont délivrées par des médecins de manière électronique à la pharmacie.
 *
 * Une ordonnance comprend :
 * Références du médecin
 * Références du patient
 * Une date de prescription
 * Liste de médicaments
 * Ingrédients pour une préparation
 *
 */


    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Pharmacie pharmacie = new Pharmacie("Pharmacie de la gare", "1 rue de la gare", "01 02 03 04 05", "bQb0w@example.com");
        System.out.println(pharmacie);
        Medecin medecin = new Medecin("Dupont", "Jean", "2 rue de la gare", "06 07 08 09 10", "HJWwU@example.com");
        System.out.println(medecin);
        Patient patient = new Patient("Durand", "Marie", "3 rue de la gare", "06 07 08 09 10", "bQb0w@example.com", "1234567890", "Mutuelle");
        System.out.println(patient);
        Pharmacien pharmacien = new Pharmacien("Martin", "Pierre", "4 rue de la gare", "06 07 08 09 10", "bQb0w@example.com");
        System.out.println(pharmacien);
        Medicament medicament = new Medicament("Doliprane", "5", "Gellule", "Oui", 30.0, false, true, false, "Laboratoire 1", false);
        System.out.println(medicament);
        MedicamentGeneric medicamentGeneric = new MedicamentGeneric("Doliprane", "5", "Gellule", "Oui", 30.0, true, true, false, "Laboratoire 1", false);
        System.out.println(medicamentGeneric);
        MedicamentNonGeneric medicamentNonGeneric = new MedicamentNonGeneric("Doliprane", "5", "Gellule", "Oui", 30.0, false, true, false, "Laboratoire 1", false);
        System.out.println(medicamentNonGeneric);
        MedicamentSansOrdonnance medicamentSansOrdonnance = new MedicamentSansOrdonnance("Doliprane", "5", "Gellule", "Oui", 30.0, false, false, false, "Laboratoire 1", false);
        System.out.println(medicamentSansOrdonnance);
        MedicamentAvecOrdonnance medicamentAvecOrdonnance = new MedicamentAvecOrdonnance("Doliprane", "5", "Gellule", "Oui", 30.0, false, true, false, "Laboratoire 1", false);
        System.out.println(medicamentAvecOrdonnance);

        // Etablissons une ordonnance pour un patient donné et l'afficher

        Ordonnance ordonnance = new Ordonnance(medecin, patient, pharmacien, medicament, medicamentGeneric, medicamentNonGeneric, medicamentSansOrdonnance, medicamentAvecOrdonnance, pharmacie, "Hopital de la gare", "bQb0w@example.com", "4 rue de la gare", "06 07 08 09 10", "06 07 08 09 10", "06 07 08 09 10", 30.0, "1234567890", "Mutuelle", "Aucun", "Aucun");
        System.out.println(ordonnance);

        // Etablissons une reception d'ordonnance pour un patient donné envoyé par mail par un medecin et receptionné dans la boite mail de la pharmacie et l'afficher
        BoiteMailMedecin boiteMailMedecin = new BoiteMailMedecin("medecin@example.com", "Ordonnance", "Ordonnance pour le patient");
        System.out.println(boiteMailMedecin);
        BoiteMailPharmacie boiteMailPharmacie = new BoiteMailPharmacie("pharmacie@example.com", "Ordonnance", "Ordonnance pour le patient");
        System.out.println(boiteMailPharmacie);
        ReceptionOrdonnance receptionOrdonnance = new ReceptionOrdonnance(medecin, patient, pharmacien, medicament, medicamentGeneric, medicamentNonGeneric, medicamentSansOrdonnance, medicamentAvecOrdonnance, pharmacie, "Hopital de la gare", "bQb0w@example.com", "4 rue de la gare", "06 07 08 09 10", "06 07 08 09 10", "06 07 08 09 10", 30.0, "1234567890", "Mutuelle", "Aucun", "Aucun");
        System.out.println(receptionOrdonnance);

        // Etablissons un document médical pour un patient donné et l'afficher
        DocumentMedical documentMedical = new DocumentMedical(medecin, patient, pharmacien, medicament, medicamentGeneric, medicamentNonGeneric, medicamentSansOrdonnance, medicamentAvecOrdonnance, pharmacie, "Hopital de la gare", "bQb0w@example.com", "4 rue de la gare", "06 07 08 09 10", "06 07 08 09 10", "06 07 08 09 10", 30.0, "1234567890", "Mutuelle", "Aucun", "Aucun");
        System.out.println(documentMedical);

        // A partir des enumérations, affiche les medicaments disponibles
        System.out.println("Les medicaments disponibles sont : ");
        for (EMedicament medicamentEnum : EMedicament.values()) {
            System.out.println(medicamentEnum);
        }

        // Etablissons une préparation pour un patient donné et l'afficher
        Preparation preparation = new Preparation("Preparation 1", "5", "1", "Aucune", documentMedical, medicament);
        System.out.println(preparation);

        // Etablissons une facture pour un patient donné et l'afficher
        Facture facture = new Facture(medecin, patient, pharmacien, medicament, medicamentGeneric, medicamentNonGeneric, medicamentSansOrdonnance, medicamentAvecOrdonnance, pharmacie, "Hopital de la gare", "bQb0w@example.com", "4 rue de la gare", "06 07 08 09 10", "06 07 08 09 10", "06 07 08 09 10", 30.0, "1234567890", "Mutuelle", "Aucun", "Aucun");
        System.out.println(facture);
    }


}