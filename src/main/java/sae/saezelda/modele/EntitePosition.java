package sae.saezelda.modele;

import sae.saezelda.modele.degat.StrategieDegat;

public abstract class EntitePosition extends Entite {

    public abstract int getX();
    public abstract int getY();
    public abstract void setX(int x);
    public abstract void setY(int y);

    public boolean estDansZone(int objetX, int objetY, int portee) {
        int xMin = getX() - portee;
        int xMax = getX() + portee;
        int yMin = getY() - portee;
        int yMax = getY() + portee;

        return objetX >= xMin && objetX <= xMax && objetY >= yMin && objetY <= yMax;
    }

}
