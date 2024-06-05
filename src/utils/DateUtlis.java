package utils;

import java.util.Calendar;

public class DateUtlis {
    /**
     * Fonction pour calculer la date de livraison en ajoutant un jour à la date actuelle
     * @return la date de livraison
     * @see Calendar#getInstance()
     * @see Calendar#add(int, int)
     * @see Calendar#DAY_OF_MONTH
     * @see Calendar#DAY_OF_WEEK
     * @see Calendar#SUNDAY
     * @see Calendar#get(int)
     * @see Calendar#add(int, int)
     */

    // Fonction pour calculer la date de livraison en ajoutant un jour à la date actuelle
    public static Calendar calculerDateLivraison() {
        Calendar dateLivraison = Calendar.getInstance();
        dateLivraison.add(Calendar.DAY_OF_MONTH, 1); // Ajouter un jour
        // Vérifier si la date de livraison est un dimanche
        if (dateLivraison.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            dateLivraison.add(Calendar.DAY_OF_MONTH, 1); // Ajouter un jour supplémentaire
        }
        return dateLivraison;
    }

}
