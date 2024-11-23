package sae.saezelda.modele.objet;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import sae.saezelda.modele.EntiteStatique;
import sae.saezelda.modele.Environnement;
import sae.saezelda.modele.Item;
import sae.saezelda.modele.personnage.Link;

import java.util.ArrayList;

public class Coffre extends EntiteStatique {

    private ArrayList<Item> contenu;
    private BooleanProperty ouvert;
    private int width;
    private int height;

    public Coffre(int x, int y, ArrayList<Item> items) {
        super(x,y);
        this.contenu = items;
        this.width=32;
        this.height=32;
        this.ouvert=new SimpleBooleanProperty(false);
    }

    @Override
    public void agir() {
//        if (estProcheDeLink() && !estOuvert()) {
//            ouvrir();
//        }
        // mettre ce code et enlever dans le controleur la touche presser E
    }



    public int getLargeur(){
        return width;
    }
    public int getHauteur(){
        return height;
    }

    public boolean estOuvert(){
        return ouvert.getValue();
    }
    public BooleanProperty estOuvertProperty(){
        return ouvert;
    }
    public ArrayList<Item> ouvrir() {
        if (!ouvert.get()) {
            ouvert.setValue(true);
            System.out.println("Le coffre est ouvert, vous avez récupéré " + contenu.size() + " objets.");

            Link link = Environnement.getInstance().getGestionLink().getLink();
            for (Item item : contenu) {
                link.getInventaire().ajouterItem(item);
            }
            return contenu;
        } else {
            System.out.println("Ce coffre est déjà ouvert.");
            return new ArrayList<>();
        }
    }

    public boolean linkEstProche(int linkX, int linkY) {
        int rayon = 32;
        return linkX - rayon < getX() + getLargeur() &&
                linkX + (rayon * 2) > getX() &&
                linkY - rayon < getY() + getHauteur() &&
                linkY + (rayon * 2) > getY();
    }

    @Override
    public String nom() {
        return "coffre";
    }
}

