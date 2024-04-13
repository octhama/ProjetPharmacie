package pharmacie;

import pharmacie.entites.Pharmacie;
import pharmacie.ui.UiCli;
import pharmacie.ui.UiGui;

public class Main {
    public static void main(String[] args) {
        // version graphique
        Pharmacie pharmacie = new Pharmacie();
        UiGui gui = new UiGui(pharmacie);
        gui.afficher();

        // version console
        /*Pharmacie pharmacie = new Pharmacie();
        UiCli cli = new UiCli(pharmacie);
        while (true) {
            cli.afficherMenu();*/
    }
}

