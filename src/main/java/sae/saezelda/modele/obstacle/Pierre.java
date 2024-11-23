package sae.saezelda.modele.obstacle;

import sae.saezelda.Main;
import sae.saezelda.modele.Environnement;
import sae.saezelda.modele.personnage.Link;

public class Pierre extends Obstacle  {

    public Pierre(int x, int y) {
        super(x, y,32,32);
    }
    @Override
    public void agir() {
        estPierre();
    }
    public boolean estPierre() {
        Link link = Environnement.getInstance().getGestionLink().getLink();

        switch (link.getDirection()) {
            case UP:
            case DOWN:
            case LEFT:
            case RIGHT:
                if (link.detecterPierre(this, link.getX(), link.getY())) {
                    link.pousserPierre(link.getDirection(), this);
                    return true;
                }
                break;
            case UP_LEFT:
            case UP_RIGHT:
            case DOWN_LEFT:
            case DOWN_RIGHT:
                // En diagonale : c'est une collision
                if (link.detecterPierre(this, link.getX(), link.getY())) {
                    link.setPeutBouger(false);
                    return false;
                }
                break;
        }
        link.setPeutBouger(true);
        return false;
    }

    public void move(int x, int y) {
        setX(getX() + x);
        setY(getY() + y);
    }
    @Override
    public String nom() {
        return "Pierre";
    }
}