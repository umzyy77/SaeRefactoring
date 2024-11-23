package sae.saezelda.modele.degat;

import sae.saezelda.modele.Entite;
import sae.saezelda.modele.EntitePosition;

public class DegatLanceMonstre extends AbstractStrategieDegat {

    public DegatLanceMonstre() {
        super(150);
    }

    @Override
    protected boolean exclureEntite(EntitePosition entite) {
        return false;
    }
}