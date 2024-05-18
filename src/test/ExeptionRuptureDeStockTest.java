package test;

import exceptions.ExeptionRuptureDeStock;

import org.junit.Test;
import static org.junit.Assert.*;

public class ExeptionRuptureDeStockTest {


    @Test
    public void test_verif_error_perso_rp_stck_ok() {
        String message = "Stock rupture";
        ExeptionRuptureDeStock exception = new ExeptionRuptureDeStock(message);
        assertEquals(message, exception.getMessage());
    }

}