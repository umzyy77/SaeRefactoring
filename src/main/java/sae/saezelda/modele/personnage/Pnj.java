/* La classe Pnj représente un personnage non-joueur qui interagit avec le joueur via des dialogues prédéfinis.
Elle intègre une logique pour déterminer si Link est à portée (zone de proximité)
et gère un système de dialogues cycliques, offrant une interaction narrative simple et fluide.*/

package sae.saezelda.modele.personnage;

import sae.saezelda.modele.Environnement;

import sae.saezelda.modele.personnage.Personnage;

public class Pnj extends Personnage {
    private String[] dialogues;
    private int dialogueIndice;

    public Pnj(int positionX, int positionY, int capaciteMax, int hauteur, int largeur, int pv) {
        super(positionX, positionY, capaciteMax, hauteur, largeur, pv);
        dialogues = new String[4];
        dialogues[0] = "Tu ne m'écoute pas ?";
        dialogues[1] = "Salut jeune aventurier, ne rentre surtout pas dans le portail situé en bas.";
        dialogues[2] = "Appui sur la touche 'P' sur le portail, il n'y a aucun retour en arrière !";
        dialogues[3] = "Bon courage !";
    }



    // L’index dialogueIndice a été cyclé avec le modulo pour éviter les réinitialisations
    @Override
    public void agir() {

    }
    public String parler() {
        dialogueIndice = (dialogueIndice + 1) % dialogues.length;
        return dialogues[dialogueIndice];
    }

    @Override
    public String nom() {
        return "pnj";
    }


}