package sae.saezelda.modele.arme;

import sae.saezelda.modele.Cooldown;
import sae.saezelda.modele.Environnement;
import sae.saezelda.modele.Item;
//import sae.saezelda.modele.degat.Degat;
import sae.saezelda.modele.degat.StrategieDegat;

public abstract class Arme extends Item {
    protected StrategieDegat degat;
    private Cooldown minuteur;

    public Arme(double cooldown, StrategieDegat strategieDegat) {
        super( cooldown);
        if (cooldown >= 0) {
            this.degat = strategieDegat;
            this.minuteur = new Cooldown(cooldown);
        }
    }
    @Override
    public void agir() {

    }
}