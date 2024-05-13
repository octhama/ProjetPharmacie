package pharmacie;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Stack;

import javax.swing.JOptionPane;

import io.EcritureOrdonnancesCsv;
import io.LectureMedecinCsv;

public class Ordonnance {

    public String idOrdonnance;
    public String referenceMedecin;
    public String referencePatient;
    public Date datePrescription;
    public static List<Medicament> medicaments;
    public String ingredientsPreparation;

    public Ordonnance(List<Medicament> medicaments) {
        Ordonnance.medicaments = medicaments;
    }

    public static void enregistrerOrdonnance() {
        // Demander à l'utilisateur de s'authentifier en tant que médecin
        String idMedecin = JOptionPane.showInputDialog(null, "Veuillez saisir votre identifiant médecin :");
        String passwordMedecin = JOptionPane.showInputDialog(null, "Veuillez saisir votre mot de passe médecin :");

        // Vérifier l'authentification
        if (LectureMedecinCsv.authentifierMedecin(idMedecin, passwordMedecin)) {
            // Authentification réussie, continuer avec l'enregistrement de l'ordonnance

            // Afficher une boîte de dialogue pour saisir le nom du médecin
            String referenceMedecin = JOptionPane.showInputDialog(null, "Veuillez saisir la référence du médecin :");
            // Afficher une boîte de dialogue pour saisir le nom du patient
            String referencePatient = JOptionPane.showInputDialog(null, "Veuillez saisir la référence du patient :");
            // Afficher une boîte de dialogue pour saisir la date de prescription
            String datePrescriptionString = JOptionPane.showInputDialog(null, "Veuillez saisir la date de prescription (format : yyyy-MM-dd) :");
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date datePrescription = null;
            try {
                datePrescription = dateFormat.parse(datePrescriptionString);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Format de date invalide. Veuillez saisir une date au format yyyy-MM-dd.");
                return;
            }

            // Afficher une boîte de dialogue pour saisir le ou les médicament(s) prescrit(s)
            String medicamentsString = JOptionPane.showInputDialog(null, "Veuillez saisir le ou les médicament(s) prescrit(s) (séparés par des virgules) :");
            String[] medicamentsArray = medicamentsString.split(",");
            Stack<String> medicaments = new Stack<>();
            for (String medicament : medicamentsArray) {
                medicaments.push(medicament);
            }
            // Enregistrer l'ordonnance
            EcritureOrdonnancesCsv.ecrireOrdonnanceCsv(referenceMedecin, referencePatient, datePrescription, medicaments);
        } else {
            // Afficher un message d'erreur si l'authentification a échoué
            JOptionPane.showMessageDialog(null, "Identifiant ou mot de passe médecin incorrect. Vous n'êtes pas autorisé à enregistrer une ordonnance.");
        }
    }

    public long getMedicaments() {
        return 0;
    }
}
