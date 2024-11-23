package sae.saezelda.modele.degat;

import sae.saezelda.modele.Entite;
import sae.saezelda.modele.EntitePosition;

public interface StrategieDegat {
    void appliquerDegats(int x, int y, int degats);
    void infligerDegatsA(EntitePosition entite, int degats);
}
