package sae.saezelda.vue;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import sae.saezelda.Main;
import sae.saezelda.modele.Item;
import sae.saezelda.modele.arme.Arc;
import sae.saezelda.modele.arme.Bombe;
import sae.saezelda.modele.arme.Couteau;
import sae.saezelda.modele.potion.Potion;
import sae.saezelda.modele.personnage.Link;

public class InventaireVue {
    GridPane gi;
    GridPane ge;
    VBox vbox;
    Link link;
    Image image;
    int ligne;
    int colonne;

    final int NB_MAX = 8;

    public InventaireVue(VBox vbox, GridPane gi, GridPane ge, Link link) {
        this.gi = gi;
        this.ge = ge;
        this.vbox = vbox;
        this.link = link;
        this.image = new Image(String.valueOf(Main.class.getResource("/image/case.png")));
        ligne = 0;
        colonne = 0;
        dessinePane();
    }

    public void dessinePane() {
        synchronized (this) {
            gi.getChildren().clear();
            ge.getChildren().clear();

            ImageView itemEquipeeView;
            if (link.getInventaire().getItemActuelle() == null) {
                itemEquipeeView = new ImageView(new Image(String.valueOf(Main.class.getResource("/image/case.png"))));
            } else {
                itemEquipeeView = new ImageView(choixImage(link.getInventaire().getItemActuelle()));
                itemEquipeeView.setOnMouseClicked(event -> {
                    System.out.println("Item cliquée");
                    link.getInventaire().desequiperItem();
                    dessinePane();
                });
            }
            ge.add(itemEquipeeView, 0, 0);

            ImageView armeView = new ImageView(new Image(String.valueOf(Main.class.getResource("/image/case.png"))));
            armeView.setOnMouseClicked(event -> {
                System.out.println("Armure cliquée");
                link.getInventaire().desequiperItem();
                dessinePane();
            });
            ge.add(armeView, 0, 1);

            colonne = 0;
            ligne = 0;
            for (int i = 0; i < NB_MAX; i++) {
                ImageView imageView;
                if (i < link.getInventaire().getItems().size()) {
                    image = choixImage(link.getInventaire().getItems().get(i));

                    imageView = new ImageView(image);
                    imageView.setFitWidth(32);
                    imageView.setFitHeight(32);
                    int finalI = i;
                    imageView.setOnMouseClicked(event -> {
                        System.out.println("Inventaire cliqué");
                        link.getInventaire().equiper(link.getInventaire().getItems().get(finalI));
                        dessinePane();
                    });
                }
                else {
                    imageView = new ImageView(new Image(String.valueOf(Main.class.getResource("/image/case.png"))));
                }
                gi.add(imageView, colonne, ligne);

                colonne++;
                if (colonne == 2) {
                    ligne++;
                    colonne = 0;
                }
            }

        }
    }


    public Image choixImage(Item item) {
        if (item == null) {
            return new Image(String.valueOf(Main.class.getResource("/image/sol.png")));
        }


        if (item instanceof Couteau) {
            return new Image(String.valueOf(Main.class.getResource("/image/epee.png")));
        } else if (item instanceof Potion) {
            return new Image(String.valueOf(Main.class.getResource("/image/potion.png")));
        } else if (item instanceof Bombe) {
            return new Image(String.valueOf(Main.class.getResource("/image/case_bombe.png")));
        } else if (item instanceof Arc) {
            return new Image(String.valueOf(Main.class.getResource("/image/case_arc.png")));
        } else {
            return new Image(String.valueOf(Main.class.getResource("/image/sol.png")));
        }
    }
}