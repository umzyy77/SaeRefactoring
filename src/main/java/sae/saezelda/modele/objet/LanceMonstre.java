package sae.saezelda.modele.objet;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import sae.saezelda.modele.Direction;
import sae.saezelda.modele.Environnement;
import sae.saezelda.modele.degat.DegatLanceMonstre;
import sae.saezelda.modele.degat.StrategieDegat;
import sae.saezelda.modele.personnage.Link;
import sae.saezelda.modele.personnage.Personnage;

public class LanceMonstre extends Projectile {

    private BooleanProperty attaqueLink = new SimpleBooleanProperty(false);


    public LanceMonstre(Direction direction) {
        super(0, 0, direction, 5, new DegatLanceMonstre());
    }
    public void tir(int aquamanX, int aquamanY, int linkX, int linkY) {
        this.setX(aquamanX);
        this.setY(aquamanY);

        int deltaX = linkX - aquamanX;
        int deltaY = linkY - aquamanY;
        this.setDirectionValue(calculerDirection(deltaX, deltaY));
    }

    private Direction calculerDirection(int deltaX, int deltaY) {
        if (deltaX > 0 && deltaY > 0) return Direction.DOWN_RIGHT;
        if (deltaX > 0 && deltaY < 0) return Direction.UP_RIGHT;
        if (deltaX < 0 && deltaY > 0) return Direction.DOWN_LEFT;
        if (deltaX < 0 && deltaY < 0) return Direction.UP_LEFT;
        if (deltaX > 0) return Direction.RIGHT;
        if (deltaX < 0) return Direction.LEFT;
        if (deltaY > 0) return Direction.DOWN;
        if (deltaY < 0) return Direction.UP;
        return Direction.NEUTRE;
    }

    @Override
    public void deplacer() {
        int[] increments = getDirection().getIncrements();
        this.setX(this.getX() + increments[0] * vitesse);
        this.setY(this.getY() + increments[1] * vitesse);

        Personnage cible = toucheCible();
        if (cible != null) {
            getStrategieDegat().appliquerDegats(getX(), getY(), 20);
            Environnement.getInstance().retirerEntite(this);
        }
    }




    public Personnage toucheCible() {
        Link link = Environnement.getInstance().getGestionLink().getLink();
        if (!link.getMortValue()) {
            int distanceX = Math.abs(getX() - link.getX());
            int distanceY = Math.abs(getY() - link.getY());
            int proximite = 10;

            if (distanceX <= proximite && distanceY <= proximite) {
                attaqueLink.set(true);
                return link;
            }
            else {
                attaqueLink.set(false);
            }
        }
        return null;
    }
    @Override
    public String nom() {
        return "projectile";
    }
}
