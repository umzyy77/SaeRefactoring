package sae.saezelda.modele.degat;

import sae.saezelda.modele.*;

import java.util.ArrayList;

public abstract class AbstractStrategieDegat implements StrategieDegat {
    private int portee;

    public AbstractStrategieDegat(int portee) {
        this.portee = portee;
    }

    protected abstract boolean exclureEntite(EntitePosition entite);

    @Override
    public void appliquerDegats(int x, int y, int degats) {
        Environnement environnement = Environnement.getInstance();

        ArrayList<EntitePosition> copieEntitesPositionnees = new ArrayList<>(environnement.getEntitesPositionnees());

        for (EntitePosition positionnee : copieEntitesPositionnees) {
            if (positionnee.estDansZone(x, y, this.portee) && !exclureEntite(positionnee)) {
                infligerDegatsA(positionnee, degats);
            }
        }
        environnement.nettoyerEntitesASupprimer();
    }


    @Override
    public void infligerDegatsA(EntitePosition entite, int degats) {
        Environnement environnement = Environnement.getInstance();
        for (VulnerableDegatInterface vulnerable : environnement.getEntitesVulnerables()) {
            if (vulnerable == entite) {
                vulnerable.recevoirDegats(degats);
                break;
            }
        }
    }
}