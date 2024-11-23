package sae.saezelda.modele.degat.degatBoom;

import sae.saezelda.modele.EntitePosition;
import sae.saezelda.modele.degat.AbstractStrategieDegat;

public class DegatBoom extends AbstractStrategieDegat {
    public DegatBoom() {
        super(35);
    }

    @Override
    protected boolean exclureEntite(EntitePosition entite) {
        return false;
    }

}