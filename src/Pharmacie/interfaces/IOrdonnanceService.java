package Pharmacie.interfaces;

import Pharmacie.composants.Ordonnance;
import Pharmacie.composants.Preparation;
import java.util.List;

public interface IOrdonnanceService {

    Preparation preparerOrdonnance(Ordonnance ordonnance) throws UnsupportedOperationException;

    void delivrerPreparation(Preparation preparation);

    List<Ordonnance> getHistoriqueOrdonnances();

    void recevoirOrdonnance(Ordonnance ordonnance);
}
