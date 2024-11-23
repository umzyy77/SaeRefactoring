package main.test;

import org.junit.Before;
import org.junit.Test;
import sae.saezelda.modele.*;
import sae.saezelda.modele.personnage.Link;
import sae.saezelda.modele.personnage.Zombie;
import sae.saezelda.modele.terrain.GestionnaireTerrain;

import static org.junit.Assert.*;

public class ZombieTest {

    private Environnement environnement = Environnement.getInstance();
    private Zombie zombie;
    private Link link;
    private GestionnaireTerrain gestionnaireTerrain;

    @Before
    public void setUp() {
        gestionnaireTerrain = new GestionnaireTerrain();
        zombie = new Zombie( 50, 50);
        environnement.ajouterEntite(zombie);
        link = new Link();
        environnement.getGestionLink().setLink(link);
    }

    /* Test déplacement */

    @Test
    public void testDeplacer() {
        int initialX = zombie.getX();
        int initialY = zombie.getY();
        zombie.deplacer();

        if (zombie.getDirection() == Direction.UP) {
            assertEquals(initialY - 1, zombie.getY());
        } else if (zombie.getDirection() == Direction.DOWN) {
            assertEquals(initialY + 1, zombie.getY());
        }
        assertEquals(initialX, zombie.getX());
    }

    @Test
    public void testDeplacerVersLink() {
        link.setX(55);
        link.setY(50);

        zombie.deplacerVersLink(link.getX(), link.getY());

        assertEquals(52, zombie.getX());
        assertEquals(50, zombie.getY());
    }

    /* Test zombie dans la zone d'explosion de la bombe */

    @Test
    public void testEstDansZoneBombe() {
        assertTrue(zombie.estDansZone(50, 50, 35));
        assertFalse(zombie.estDansZone(100, 100, 35));
    }
}