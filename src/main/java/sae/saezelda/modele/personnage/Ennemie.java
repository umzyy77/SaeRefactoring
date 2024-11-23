package sae.saezelda.modele.personnage;

import sae.saezelda.modele.Environnement;

public abstract class Ennemie extends Personnage{
    Link link;
    public Ennemie(int x, int y,int capaciteMax, int hauteur, int largeur, int pv){
        super( x,  y, capaciteMax, hauteur,  largeur,  pv);
        this.link= Environnement.getInstance().getGestionLink().getLink();
    }

    public void agir(){
        attaqueLink();
    }
    public abstract void attaqueLink();

}