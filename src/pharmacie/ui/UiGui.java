package pharmacie.ui;

import pharmacie.entites.Pharmacie;
import pharmacie.entites.Medicament;
import pharmacie.entites.Preparation;
import pharmacie.entites.Commande;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class UiGui extends JFrame {
    private Pharmacie pharmacie;
    private JTextArea textArea;

    public UiGui(Pharmacie pharmacie) {
        this.pharmacie = pharmacie;
        setTitle("Pharmacie");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Création des composants graphiques et ajout à la fenêtre
        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel("Bienvenue dans notre pharmacie !");
        panel.add(label, BorderLayout.NORTH);
        textArea = new JTextArea();
        textArea.setEditable(false);
        panel.add(new JScrollPane(textArea), BorderLayout.CENTER);

        // Bouton pour afficher les médicaments en stock
        JButton btnMedicaments = new JButton("Afficher les médicaments en stock");
        btnMedicaments.addActionListener(e -> afficherMedicamentsEnStock());
        panel.add(btnMedicaments, BorderLayout.WEST);

        // Bouton pour afficher les préparations disponibles
        JButton btnPreparations = new JButton("Afficher les préparations disponibles");
        btnPreparations.addActionListener(e -> afficherPreparations());
        panel.add(btnPreparations, BorderLayout.EAST);

        // Ajout du panel à la fenêtre
        getContentPane().add(panel);
        setVisible(true);
    }

    private void afficherMedicamentsEnStock() {
        textArea.setText("");
        List<Medicament> medicaments = pharmacie.getMedicamentsEnStock();
        if (medicaments.isEmpty()) {
            textArea.append("Aucun médicament en stock.");
        } else {
            textArea.append("Médicaments en stock :\n");
            for (Medicament medicament : medicaments) {
                textArea.append(medicament.getNom() + " - Quantité : " + medicament.getQuantiteEnStock() + "\n");
            }
        }
    }

    private void afficherPreparations() {
        textArea.setText("");
        List<Preparation> preparations = pharmacie.getPreparationsDisponibles();
        if (preparations.isEmpty()) {
            textArea.append("Aucune préparation disponible.");
        } else {
            textArea.append("Préparations disponibles :\n");
            for (Preparation preparation : preparations) {
                textArea.append(preparation.getDescription() + " - Date de disponibilité : " + preparation.getDateDisponibilite() + "\n");
            }
        }
    }
}

