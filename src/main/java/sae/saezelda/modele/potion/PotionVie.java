package sae.saezelda.modele.potion;

import sae.saezelda.modele.Environnement;
import sae.saezelda.modele.personnage.Link;

import static sae.saezelda.modele.personnage.Link.PV_MAX;

public class PotionVie extends Potion {
    int pv;
    public PotionVie(){
        super();
        this.pv=20;
    }

    @Override
    public void agir() {

    }
    @Override
    public String nom() {
        return "potion de vie";
    }

    @Override
    public void utiliser(Link link) {
        if (link.getPvValue() >= PV_MAX) {
            System.out.println("Tes PV sont déjà au maximum.");
        } else {
            link.setPvValue(Math.min(link.getPvValue() + pv, PV_MAX));
            link.getInventaire().getItems().remove(this);
            System.out.println("Tu as bu une potion de vie, régénération de " + pv + " PV.");
        }
    }
}
