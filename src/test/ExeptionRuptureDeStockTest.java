package test;

import exceptions.ExeptionRuptureDeStock;

import org.junit.Test;
import static org.junit.Assert.*;
/**
 * Classe de test pour vérifier l'exception de rupture de stock.
 * On teste si le message d'erreur est bien affiché.
 * @see ExeptionRuptureDeStock
 */
public class ExeptionRuptureDeStockTest {

    @Test
    public void test_verif_error_perso_rp_stck_ok() {
        String message = "Stock rupture";
        ExeptionRuptureDeStock exception = new ExeptionRuptureDeStock(message);
        assertEquals(message, exception.getMessage()); // le message d'erreur doit être bien affiché
    }

}