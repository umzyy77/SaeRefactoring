package sae.saezelda.modele.obstacle;

import sae.saezelda.modele.EntiteMobile;
import sae.saezelda.modele.Environnement;
import sae.saezelda.modele.VulnerableDegatInterface;

public abstract class Obstacle extends EntiteMobile implements VulnerableDegatInterface {
    private int hauteur;
    private int largeur;

    public Obstacle(int x, int y, int hauteur, int largeur) {
        super(x,y);
        this.hauteur = hauteur;
        this.largeur = largeur;
    }

    public boolean estDansZone(int objetX, int objetY, int portee) {
        int xMin = getX() - portee;
        int xMax = getX() + portee;
        int yMin = getY() - portee;
        int yMax = getY() + portee;

        return objetX >= xMin && objetX <= xMax && objetY >= yMin && objetY <= yMax;
    }

    @Override
    public void recevoirDegats(int degats) {
        Environnement.getInstance().retirerEntite(this);
    }
    public int getHauteurObstacle() {
        return hauteur;
    }
    public int getLargeurObstacle() {
        return largeur;
    }
}
