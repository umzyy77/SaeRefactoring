package sae.saezelda.modele.arme;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import sae.saezelda.modele.Cooldown;
//import sae.saezelda.modele.degat.degatCouteau.DegatCouteau;
import sae.saezelda.modele.degat.degatCouteau.DegatCouteau;
import sae.saezelda.modele.personnage.Link;

public class Couteau extends Arme {

    private BooleanProperty attaqueCouteau;
    public Couteau() {
        super(2, new DegatCouteau());
        this.attaqueCouteau = new SimpleBooleanProperty(false);
    }

    @Override
    public void utiliser(Link link) {
        if (!isEnCooldown()) {
            attaqueCouteau.set(true);
            degat.appliquerDegats(link.getX(), link.getY(),100);
            System.out.println("Attaque de couteau.");

            Cooldown cooldown = new Cooldown(1);
            cooldown.demarrer(() -> {
                attaqueCouteau.set(false);
            });

            demarrerCooldown(this::finCooldown);
        } else {
            System.out.println("Couteau est encore en cooldown");
        }
    }
    private void finCooldown() {
        System.out.println("Cooldown du couteau terminé.");
    }

    public BooleanProperty getAttaqueCouteauProperty() {
        return attaqueCouteau;
    }

    @Override
    public String nom() {
        return "couteau";
    }
}
