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

package Pharmacie;

import Pharmacie.ordonnance.Ordonnance;
import Pharmacie.preparation.Preparation;

import java.util.ArrayList;
import java.util.List;

public class Main {
   // Méthode principale
    public static void main(String[] args) {
        // Créer une liste de médicaments
        List<Medicament> medicaments = new ArrayList<>();
        // Ajouter des médicaments à la liste
        medicaments.add(new Medicament("Doliprane", 10, 100));
        medicaments.add(new Medicament("Aspirine", 5, 50));
        medicaments.add(new Medicament("Paracétamol", 8, 75));
        medicaments.add(new Medicament("Ibuprofène", 7, 60));
        medicaments.add(new Medicament("Efferalgan", 9, 80));
        // Afficher les médicaments
        for (Medicament medicament : medicaments) {
            System.out.println("Nom du médicament : " + medicament.getNom());
            System.out.println("Prix du médicament : " + medicament.getPrix());
            System.out.println("Quantité en stock : " + medicament.getQuantiteEnStock());
            System.out.println();
        }
        // Créer une liste d'ordonnances
        List<Ordonnance> ordonnances = new ArrayList<>();
        // Ajouter des ordonnances à la liste
        ordonnances.add(new Ordonnance ("Dr. Dupont", "Jean Dupont", "01/01/2021", medicaments));
        ordonnances.add(new Ordonnance("Dr. Durand", "Marie Durand", "02/01/2021", medicaments));
        ordonnances.add(new Ordonnance("Dr. Martin", "Pierre Martin", "03/01/2021", medicaments));
        // Afficher les ordonnances
        for (Ordonnance ordonnance : ordonnances) {
            System.out.println("Nom du médecin : " + ordonnance.getNomDuMedecin());
            System.out.println("Nom du patient : " + ordonnance.getNomDuPatient());
            System.out.println("Date de prescription : " + ordonnance.getDateDePrescription());
            System.out.println("Liste des médicaments : " + ordonnance.getMedicaments());
            System.out.println();
        }
        // Créer une liste de préparations
        List<Preparation> preparations = new ArrayList<>();
        // Ajouter des préparations à la liste
        preparations.add(new Preparation(medicaments, "Jean Dupont", true));
        preparations.add(new Preparation(medicaments, "Marie Durand", true));
        preparations.add(new Preparation(medicaments, "Pierre Martin", true));
        // Afficher les préparations
        for (Preparation preparation : preparations) {
            System.out.println("Nom du patient : " + preparation.getNomDuPatient());
            System.out.println("Liste des médicaments : " + preparation.getMedicaments());
            System.out.println("Date de préparation : " + preparation.getDateDePreparation());
            System.out.println();
        }
    }
}