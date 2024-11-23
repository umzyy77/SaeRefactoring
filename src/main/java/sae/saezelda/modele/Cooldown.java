package sae.saezelda.modele;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class Cooldown {
    private final double delais;
    private Runnable action;
    private boolean isRunning;

    public Cooldown(double delais) {
        this.delais = delais;
        this.isRunning = false;
    }

    public void demarrer(Runnable action) {
        this.action = action;
        isRunning = true;

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(delais), event -> {
            if (action != null) {
                action.run();
            }
            isRunning = false;
        }));
        timeline.play();
    }

    public boolean isRunning() {
        return isRunning;
    }
}
