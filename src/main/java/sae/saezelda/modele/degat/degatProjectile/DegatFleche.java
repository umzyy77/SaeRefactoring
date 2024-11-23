package sae.saezelda.modele.degat.degatProjectile;

import sae.saezelda.modele.Entite;
import sae.saezelda.modele.EntiteMobile;
import sae.saezelda.modele.EntitePosition;
import sae.saezelda.modele.Environnement;
import sae.saezelda.modele.degat.AbstractStrategieDegat;
import sae.saezelda.modele.personnage.Zombie;

public class DegatFleche extends AbstractStrategieDegat {

    public DegatFleche() {
        super(1);
    }


    @Override // Manque de temps pour le instanceof ici
    protected boolean exclureEntite(EntitePosition entite) {
        return !(entite instanceof Zombie);
    }
}
