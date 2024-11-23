package sae.saezelda.vue;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import sae.saezelda.Main;
import sae.saezelda.modele.obstacle.Obstacle;

public class ObstacleVue {
    private Obstacle obstacle;
    private ImageView imageView;

    public ObstacleVue(Pane panneauJeu, Obstacle obstacle) {
        this.obstacle = obstacle;
        Image obstacles = new Image(String.valueOf(Main.class.getResource("/image/pierre.png")));
        this.imageView = new ImageView(obstacles);

        imageView.setId(obstacle.nom() + obstacle.getId());

        imageView.translateXProperty().bind(obstacle.getXProperties());
        imageView.translateYProperty().bind(obstacle.getYProperties());

        panneauJeu.getChildren().add(imageView);
    }
}
