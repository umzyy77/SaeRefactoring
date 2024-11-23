package sae.saezelda.vue;

import javafx.scene.layout.Pane;
import sae.saezelda.modele.Entite;
import sae.saezelda.modele.personnage.Zombie;
import sae.saezelda.modele.personnage.Aquaman;
import sae.saezelda.modele.arme.Bombe;
import sae.saezelda.modele.objet.Coffre;
import sae.saezelda.modele.obstacle.Obstacle;
import sae.saezelda.modele.personnage.Pnj;
import sae.saezelda.modele.objet.Projectile;

public class CreerVuesEntites {

    public static void creerVue(Entite entite, Pane panneauJeu) {
        if (entite instanceof Zombie) {
            new ZombieVue((Zombie) entite, panneauJeu);
        }
        else if (entite instanceof Aquaman) {
            new AquamanVue((Aquaman) entite, panneauJeu);
        }
        else if (entite instanceof Bombe) {
            new BombeVue((Bombe) entite, panneauJeu);
        }
        else if (entite instanceof Coffre) {
            new CoffreVue((Coffre) entite, panneauJeu);
        }
        else if (entite instanceof Obstacle) {
            new ObstacleVue(panneauJeu, (Obstacle) entite);
        }
        else if (entite instanceof Pnj) {
            new PnjVue(panneauJeu, (Pnj) entite);
        }
        else if (entite instanceof Projectile) {
            new ProjectileVue((Projectile) entite, panneauJeu);
        }
        else {
            System.out.println("non reconnu");
        }
    }

}