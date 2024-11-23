package sae.saezelda;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import sae.saezelda.modele.*;
import sae.saezelda.modele.objet.Projectile;
import sae.saezelda.modele.personnage.Aquaman;
import sae.saezelda.modele.personnage.Link;
import sae.saezelda.modele.personnage.Zombie;
import sae.saezelda.vue.*;
import javafx.scene.layout.Pane;

public class GameLoop {
    private Environnement environnement = Environnement.getInstance();
    private Link link;
    private LinkVue linkVue;
    private final int FPS = 60;

    public GameLoop(Link link, LinkVue linkVue) {
        this.link = link;
        this.linkVue = linkVue;
    }

    public void startGameLoop(Pane paneJeu) {
        Duration duration = Duration.millis(1000.0 / FPS);
        KeyFrame keyFrame = new KeyFrame(duration, event -> {
            updateGame(paneJeu);
        });
        Timeline timeline = new Timeline(keyFrame);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void updateGame(Pane paneJeu) {
        environnement.faireAgirToutesLesEntites();
    }

}