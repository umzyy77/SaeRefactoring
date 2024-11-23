package main.test;

import org.junit.Before;
import org.junit.Test;
import sae.saezelda.modele.*;

import sae.saezelda.modele.arme.Arc;
import sae.saezelda.modele.arme.Couteau;
import sae.saezelda.modele.objet.Coffre;
import sae.saezelda.modele.personnage.Link;
import sae.saezelda.modele.personnage.Zombie;
import sae.saezelda.modele.terrain.GestionnaireTerrain;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class LinkTest {
    private Environnement environnement = Environnement.getInstance();
    private Link link;
    private GestionnaireTerrain gestionnaireTerrain;
    private Zombie zombie;
    private Coffre coffre;
    private Arc arc;


    @Before
    public void setUp() {
        ArrayList<Item> items1 = new ArrayList<>();
        ArrayList<Item> items2 = new ArrayList<>();
        gestionnaireTerrain = new GestionnaireTerrain();
        link = new Link();
        zombie = new Zombie(100, 100);
        environnement.ajouterEntite(zombie);
        Couteau couteau = new Couteau();
        items1.add(couteau);
        link.getInventaire().equiper(couteau);
        coffre = new Coffre(100,100, items1);
        arc = new Arc(200);
        items2.add(arc);
        link.getInventaire().utiliser(items2);
        coffre = new Coffre(100,100, items2);
    }

    /* Test du coffre */

    @Test
    public void testEstDansZoneCoffreTrue() {
        link.setX(100);
        link.setY(100);

        coffre.setX(120);
        coffre.setY(120);

        assertTrue(coffre.linkEstProche(link.getX(), link.getY()));
    }
    @Test
    public void testEstDansZoneCoffreFalseCauseX() {
        link.setX(0);
        link.setY(100);

        coffre.setX(200);
        coffre.setY(100);

        assertFalse(coffre.linkEstProche(link.getX(), link.getY()));
    }

    @Test
    public void testEstDansZoneCoffreFalseCauseY() {
        link.setX(100);
        link.setY(0);

        coffre.setX(100);
        coffre.setY(200);

        assertFalse(coffre.linkEstProche(link.getX(), link.getY()));
    }

    @Test
    public void testEstDansZoneCoffreOver() {
        link.setX(150);
        link.setY(150);

        coffre.setX(100);
        coffre.setY(100);

        assertTrue(coffre.linkEstProche(link.getX(), link.getY()));
    }

    /* Test de l'équipement de l'arc */

    @Test
    public void testEquiperArc() {
        link.getInventaire().equiperItem(arc);

        assertTrue(link.getInventaire().getItemEquipeProperty().get());
//        assertTrue(link.peutTirerFLeches.get());
        assertFalse(link.getInventaire().getItems().contains(arc));
    }

    /* Test des dégats */

    @Test
    public void testLinkSubitDegats() {
        int vieInitiale = link.getPvValue();
        int degats = 20;
        link.recevoirDegats(degats);
        assertEquals(vieInitiale - degats, link.getPvValue());
    }

    @Test
    public void testLinkInfligeDegatsZombie() {
        int vieInitialeZombie = zombie.getPvValue();
        int degats = 30;
        zombie.recevoirDegats(30);
        assertEquals(vieInitialeZombie - degats, zombie.getPvValue());
    }

    @Test
    public void testZombieInfligeDegatsLink() {
        int vieInitialeLink = link.getPvValue();
        int degats = 15;
        link.recevoirDegats(15);
        assertEquals(vieInitialeLink - degats, link.getPvValue());
    }

}