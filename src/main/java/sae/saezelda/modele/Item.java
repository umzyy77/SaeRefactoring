package sae.saezelda.modele;

import sae.saezelda.modele.personnage.Link;

public abstract class Item extends Entite {
    private boolean enCooldown;
    private double cooldown;
    private Cooldown minuteur;
    public Item(double cooldown) {
        super();
        this.cooldown = cooldown;
        this.enCooldown = false;
        this.minuteur = new Cooldown(cooldown);

    }


    protected void demarrerCooldown(Runnable action) {
        minuteur.demarrer(action);
    }
    protected boolean isEnCooldown() {
        return minuteur.isRunning();
    }
    public abstract void utiliser(Link link);

}