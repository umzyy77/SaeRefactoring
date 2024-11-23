package sae.saezelda.vue;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import sae.saezelda.Main;
import sae.saezelda.modele.objet.Coffre;

public class CoffreVue {
    private Coffre coffre;
    private Pane panneauJeu;
    private ImageView coffreImageView;

    public CoffreVue(Coffre coffre, Pane panneauJeu) {
        this.coffre = coffre;
        this.panneauJeu = panneauJeu;

        this.coffreImageView = new ImageView();
        this.coffreImageView.setFitWidth(32);
        this.coffreImageView.setFitHeight(32);
        this.coffreImageView.setTranslateX(coffre.getX());
        this.coffreImageView.setTranslateY(coffre.getY());

        Image coffreFermeImage = new Image(String.valueOf(Main.class.getResource("/image/coffre_ferme.png")));
        Image coffreOuvertImage = new Image(String.valueOf(Main.class.getResource("/image/coffre_ouvert.png")));
        coffreImageView.setId("coffre" + coffre.getId());

        this.coffreImageView.setImage(coffreFermeImage);

        this.panneauJeu.getChildren().add(this.coffreImageView);

        this.coffre.estOuvertProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                System.out.println("Coffre ouvert");
                this.coffreImageView.setImage(coffreOuvertImage);
            }
        });
    }
}
