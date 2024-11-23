package sae.saezelda.modele.objet;

import sae.saezelda.modele.Direction;
import sae.saezelda.modele.degat.degatProjectile.DegatFleche;

public class Fleche extends Projectile {
    public Fleche(int x, int y, Direction direction, int vitesse) {
        super(x,y, direction, vitesse, new DegatFleche());
        this.setDirectionValue(direction);
    }

    public void deplacer() {
        if (!getToucher()) {
            int[] increments = getDirection().getIncrements();
            setX(getX() + increments[0] * vitesse);
            setY(getY() + increments[1] * vitesse);

            getStrategieDegat().appliquerDegats(getX(), getY(), 10);
        }
    }

    public boolean estDansZone(int objetX, int objetY, int portee) {

        int xFleche = getX();
        int yFleche = getY();
        int xZombie = objetX;
        int yZombie = objetY;

        return xFleche + getLargeur() >= xZombie && xFleche <= xZombie + 19 &&
                yFleche >= yZombie && yFleche <= yZombie + 32;
    }


    public int getLargeur() {
        return 11;
    }
    @Override
    public String nom() {
        return "projectile";
    }


}
