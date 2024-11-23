package sae.saezelda.vue;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import sae.saezelda.Main;
import sae.saezelda.modele.objet.Fleche;
import sae.saezelda.modele.objet.Projectile;
import sae.saezelda.modele.Direction;

public class ProjectileVue {
    private Projectile projectile;
    private Pane panneauJeu;
    private ImageView imageView;

    public ProjectileVue(Projectile projectile, Pane panneauJeu) {
        this.projectile = projectile;
        this.panneauJeu = panneauJeu;
        this.imageView = new ImageView();
        this.imageView.setId("projectile" + projectile.getId());

        if (projectile instanceof Fleche) {
            creerFlecheVue((Fleche) projectile);
        } else {
            creerLanceurDeMonstreVue(projectile);
        }
    }

    private void creerFlecheVue(Fleche fleche) {
        Image[] imagesFleches = new Image[9];
        imagesFleches[Direction.RIGHT.ordinal()] = new Image(String.valueOf(Main.class.getResource("/image/fleche11x3.png")));
        imagesFleches[Direction.LEFT.ordinal()] = new Image(String.valueOf(Main.class.getResource("/image/fleche_gauche.png")));
        imagesFleches[Direction.UP.ordinal()] = new Image(String.valueOf(Main.class.getResource("/image/fleche_haut.png")));
        imagesFleches[Direction.DOWN.ordinal()] = new Image(String.valueOf(Main.class.getResource("/image/fleche_bas.png")));
        imagesFleches[Direction.NEUTRE.ordinal()] = new Image(String.valueOf(Main.class.getResource("/image/fleche_bas.png")));
        imagesFleches[Direction.UP_LEFT.ordinal()] = new Image(String.valueOf(Main.class.getResource("/image/fleche_gauche.png")));
        imagesFleches[Direction.UP_RIGHT.ordinal()] = new Image(String.valueOf(Main.class.getResource("/image/fleche_gauche.png")));
        imagesFleches[Direction.DOWN_LEFT.ordinal()] = new Image(String.valueOf(Main.class.getResource("/image/fleche_gauche.png")));
        imagesFleches[Direction.DOWN_RIGHT.ordinal()] = new Image(String.valueOf(Main.class.getResource("/image/fleche_gauche.png")));

        imageView.setImage(imagesFleches[fleche.getDirectionValue()]);
        panneauJeu.getChildren().add(imageView);
        imageView.translateXProperty().bind(fleche.getXProperties());
        imageView.translateYProperty().bind(fleche.getYProperties());
    }

    private void creerLanceurDeMonstreVue(Projectile projectile) {
        Image normalImage = new Image(String.valueOf(Main.class.getResource("/image/personnage/projectile.png")));
        imageView.setImage(normalImage);
        imageView.setFitWidth(19);
        imageView.setFitHeight(32);
        imageView.setTranslateX(projectile.getX());
        imageView.setTranslateY(projectile.getY());

        panneauJeu.getChildren().add(imageView);
        imageView.translateXProperty().bind(projectile.getXProperties());
        imageView.translateYProperty().bind(projectile.getYProperties());
    }
}
