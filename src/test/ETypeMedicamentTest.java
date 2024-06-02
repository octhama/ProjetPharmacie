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

        assertEquals(ETypeMedicament.VENTE_LIBRE, venteLibre); // le type de médicament doit être bien défini
        assertEquals(ETypeMedicament.ORDONNANCE, ordonnance); // le type de médicament doit être bien défini
        assertNotEquals(venteLibre, ordonnance); // les types de médicaments ne doivent pas être égaux
    }

}