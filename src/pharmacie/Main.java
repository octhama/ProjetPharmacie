package pharmacie;

import pharmacie.entites.Pharmacie;
import pharmacie.ui.UiCli;

public class Main {
    public static void main(String[] args) {
        Pharmacie pharmacie = new Pharmacie();
        UiCli cli = new UiCli(pharmacie);
        while (true) {
            cli.afficherMenu();
        }
    }
}

