package test;

import enums.ETypeMedicament;

import org.junit.Test;
import static org.junit.Assert.*;
/**
 * Classe de test pour vérifier l'énumération des types de médicaments.
 * On teste si les types de médicaments sont bien définis.
 * @see ETypeMedicament
 */
public class ETypeMedicamentTest {

    @Test
    public void test_verif_enum_ok() {
        ETypeMedicament venteLibre = ETypeMedicament.VENTE_LIBRE;
        ETypeMedicament ordonnance = ETypeMedicament.ORDONNANCE;

        assertEquals(ETypeMedicament.VENTE_LIBRE, venteLibre);
        assertEquals(ETypeMedicament.ORDONNANCE, ordonnance);
        assertNotEquals(venteLibre, ordonnance);
    }

}