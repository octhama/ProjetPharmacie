package test;

import enums.ETypeMedicament;

import org.junit.Test;
import static org.junit.Assert.*;

public class ETypeMedicamentTest {


    // ETypeMedicament values can be accessed and used correctly
    @Test
    public void test_access_and_use_values() {
        ETypeMedicament venteLibre = ETypeMedicament.VENTE_LIBRE;
        ETypeMedicament ordonnance = ETypeMedicament.ORDONNANCE;

        assertEquals(ETypeMedicament.VENTE_LIBRE, venteLibre);
        assertEquals(ETypeMedicament.ORDONNANCE, ordonnance);
    }

}