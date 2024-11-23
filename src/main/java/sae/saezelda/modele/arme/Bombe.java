package sae.saezelda.modele.arme;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import sae.saezelda.modele.Environnement;
import sae.saezelda.modele.degat.degatBoom.DegatBoom;
import sae.saezelda.modele.personnage.Link;

public class Bombe extends Arme {
    private IntegerProperty x;
    private IntegerProperty y;

    public Bombe() {
        super(2,new DegatBoom());
        this.x = new SimpleIntegerProperty(0);
        this.y = new SimpleIntegerProperty(0);
    }


    public void placerBombe(Link link) {
        this.x.set(link.getX());
        this.y.set(link.getY());
        if (!isEnCooldown()) {
            System.out.println("Link pose une bombe.");
            demarrerCooldown(this::exploser);

        } else {
            System.out.println("Bombe est encore en cooldown");
        }
    }
    @Override
    public void utiliser(Link link) {
        if (!isEnCooldown() && !link.getMortValue() && link.getInventaire().aDesBombes()) {
            Bombe bombe = new Bombe();
            this.placerBombe(link);

            Environnement.getInstance().ajouterEntite(bombe);

            bombe.placerBombe(link);
        }
        else {
            System.out.println("Tu n'a plus de bombe.");
        }
    }

    public void exploser() {
        System.out.println("La bombe explose");
        degat.appliquerDegats(getXValue(), getYValue(), 35);
        Environnement.getInstance().retirerEntite(this);
    }


    public int getXValue() {
        return x.getValue();
    }
    public int getYValue() {
        return y.getValue();
    }
    public IntegerProperty getXProperty() {
        return x;
    }
    public IntegerProperty getYProperty() {
        return y;
    }
    @Override
    public String nom() {
        return "bombe";
    }
}
