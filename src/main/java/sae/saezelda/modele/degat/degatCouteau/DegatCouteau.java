package sae.saezelda.modele.degat.degatCouteau;

import sae.saezelda.modele.Entite;
import sae.saezelda.modele.EntiteMobile;
import sae.saezelda.modele.EntitePosition;
import sae.saezelda.modele.Environnement;
import sae.saezelda.modele.degat.AbstractStrategieDegat;
import sae.saezelda.modele.personnage.Zombie;

public class DegatCouteau extends AbstractStrategieDegat {
    public DegatCouteau() {
        super(5);
    }

    // ici ce n'est pas de la duplication de code avec DegatFleche, c'est juste que ces deux armes font des degats aux memes type d'entite.
    @Override
    protected boolean exclureEntite(EntitePosition entite) {
        return !(entite instanceof Zombie);
    }
}
