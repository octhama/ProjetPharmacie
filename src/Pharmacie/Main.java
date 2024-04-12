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

import Pharmacie.ui.InterfaceUtilisateur;

public class Main {
    public static void main(String[] args) {
        // Point d'entrée de l'application
        InterfaceUtilisateur interfaceUtilisateur = new InterfaceUtilisateur();
        interfaceUtilisateur.demarrer();
    }// Fin de l'application
}