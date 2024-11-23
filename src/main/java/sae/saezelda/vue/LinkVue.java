package sae.saezelda.vue;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import sae.saezelda.Main;
import sae.saezelda.modele.Direction;
import sae.saezelda.modele.Item;
import sae.saezelda.modele.arme.Arc;
import sae.saezelda.modele.arme.Bombe;
import sae.saezelda.modele.arme.Couteau;
import sae.saezelda.modele.personnage.Link;
import sae.saezelda.modele.potion.Potion;

public class LinkVue {
    private Link link;
    private Pane panneauJeu;
    private TerrainVue terrainVue;
    private ImageView linkImageView;
    private Image[] imagesLink;
    private Image[] imagesLinkArc;
    private Image[] imagesLinkCouteau;
    private Image arcImage;
    private ImageView arcImageView;
    private ImageView imageArmeSol;

    public LinkVue(Link link, Pane panneauJeu, TerrainVue terrainVue) {
        this.link = link;
        this.panneauJeu = panneauJeu;
        this.terrainVue = terrainVue;
        this.imageArmeSol = null;
        imagesLink = new Image[9];
        imagesLink[Direction.DOWN.ordinal()] = new Image(String.valueOf(Main.class.getResource("/image/personnage/linkbas.gif")));
        imagesLink[Direction.UP.ordinal()] = new Image(String.valueOf(Main.class.getResource("/image/personnage/linkhaut.gif")));
        imagesLink[Direction.RIGHT.ordinal()] = new Image(String.valueOf(Main.class.getResource("/image/personnage/linkdroit.gif")));
        imagesLink[Direction.LEFT.ordinal()] = new Image(String.valueOf(Main.class.getResource("/image/personnage/linkgauche.gif")));
        imagesLink[Direction.NEUTRE.ordinal()] = new Image(String.valueOf(Main.class.getResource("/image/personnage/persoFace.png")));
        imagesLink[Direction.DOWN_LEFT.ordinal()] = new Image(String.valueOf(Main.class.getResource("/image/personnage/linkgauche.gif")));
        imagesLink[Direction.DOWN_RIGHT.ordinal()] = new Image(String.valueOf(Main.class.getResource("/image/personnage/linkdroit.gif")));
        imagesLink[Direction.UP_LEFT.ordinal()] = new Image(String.valueOf(Main.class.getResource("/image/personnage/linkgauche.gif")));
        imagesLink[Direction.UP_RIGHT.ordinal()] = new Image(String.valueOf(Main.class.getResource("/image/personnage/linkdroit.gif")));

        imagesLinkArc = new Image[9];
        imagesLinkArc[Direction.DOWN.ordinal()] = new Image(String.valueOf(Main.class.getResource("/image/personnage/link_arc_bas.png")));
        imagesLinkArc[Direction.UP.ordinal()] = new Image(String.valueOf(Main.class.getResource("/image/personnage/link_arc_haut.png")));
        imagesLinkArc[Direction.RIGHT.ordinal()] = new Image(String.valueOf(Main.class.getResource("/image/personnage/link_arc_droit.png")));
        imagesLinkArc[Direction.LEFT.ordinal()] = new Image(String.valueOf(Main.class.getResource("/image/personnage/link_arc_gauche.png")));
        imagesLinkArc[Direction.NEUTRE.ordinal()] = new Image(String.valueOf(Main.class.getResource("/image/personnage/link_arc_bas.png")));
        imagesLinkArc[Direction.DOWN_LEFT.ordinal()] = new Image(String.valueOf(Main.class.getResource("/image/personnage/link_arc_gauche.png")));
        imagesLinkArc[Direction.DOWN_RIGHT.ordinal()] = new Image(String.valueOf(Main.class.getResource("/image/personnage/link_arc_droit.png")));
        imagesLinkArc[Direction.UP_LEFT.ordinal()] = new Image(String.valueOf(Main.class.getResource("/image/personnage/link_arc_gauche.png")));
        imagesLinkArc[Direction.UP_RIGHT.ordinal()] = new Image(String.valueOf(Main.class.getResource("/image/personnage/link_arc_droit.png")));

        imagesLinkCouteau = new Image[9];
        imagesLinkCouteau[Direction.DOWN.ordinal()] = new Image(String.valueOf(Main.class.getResource("/image/personnage/couteaubas.gif")));
        imagesLinkCouteau[Direction.UP.ordinal()] = new Image(String.valueOf(Main.class.getResource("/image/personnage/couteauhaut.gif")));
        imagesLinkCouteau[Direction.RIGHT.ordinal()] = new Image(String.valueOf(Main.class.getResource("/image/personnage/couteaudroit.gif")));
        imagesLinkCouteau[Direction.LEFT.ordinal()] = new Image(String.valueOf(Main.class.getResource("/image/personnage/couteaugauche.gif")));
        imagesLinkCouteau[Direction.NEUTRE.ordinal()] = new Image(String.valueOf(Main.class.getResource("/image/personnage/couteaubas.gif")));
        imagesLinkCouteau[Direction.DOWN_LEFT.ordinal()] = new Image(String.valueOf(Main.class.getResource("/image/personnage/couteaugauche.gif")));
        imagesLinkCouteau[Direction.DOWN_RIGHT.ordinal()] = new Image(String.valueOf(Main.class.getResource("/image/personnage/couteaudroit.gif")));
        imagesLinkCouteau[Direction.UP_LEFT.ordinal()] = new Image(String.valueOf(Main.class.getResource("/image/personnage/couteaugauche.gif")));
        imagesLinkCouteau[Direction.UP_RIGHT.ordinal()] = new Image(String.valueOf(Main.class.getResource("/image/personnage/couteaudroit.gif")));

        Image imageLinkMort = new Image(String.valueOf(Main.class.getResource("/image/personnage/link_mort.png")));
        arcImage = new Image(String.valueOf(Main.class.getResource("/image/arc.png")));

        linkImageView = new ImageView();
        linkImageView.setFitWidth(19);
        linkImageView.setFitHeight(32);
        updateLinkImageView();

        panneauJeu.getChildren().add(linkImageView);
        linkImageView.translateXProperty().bind(link.getXProperties());
        linkImageView.translateYProperty().bind(link.getYProperties());

        link.getDirectionProperty().addListener((observable, oldValue, newValue) -> updateLinkImageView());

        link.getMortProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                linkImageView.setFitWidth(32);
                linkImageView.setFitHeight(13);
                linkImageView.setImage(imageLinkMort);
            }
        });
        link.getInventaire().getItemEquipeProperty().addListener((observable, oldValue, newValue) ->
                updateLinkImageView());

        if (link.getInventaire().getItemActuelle() instanceof Couteau) {
            Couteau couteau = (Couteau) link.getInventaire().getItemActuelle();
            couteau.getAttaqueCouteauProperty().addListener((observable, oldValue, newValue) -> updateLinkImageView());
        }

        link.getInventaire().getItemJeterProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                afficherItemAuSol();
            }
            else {
                retirerItemDuSol();
            }
        });
    }


    public void afficherItemAuSol() {
        if (imageArmeSol == null) {
            Item item = link.getInventaire().getItemActuelle();
            imageArmeSol = choixImage(item);
            imageArmeSol.setTranslateX(link.getX());
            imageArmeSol.setTranslateY(link.getY());
            panneauJeu.getChildren().add(imageArmeSol);
        }

        imageArmeSol.setTranslateX(link.getX());
        imageArmeSol.setTranslateY(link.getY());
    }

    public void retirerItemDuSol() {
        if (imageArmeSol != null && panneauJeu.getChildren().contains(imageArmeSol)) {
            panneauJeu.getChildren().remove(imageArmeSol);
            imageArmeSol = null;
            System.out.println("On retire l'arme du sol");
        }
    }



    public void updateLinkImageView() {
        int direction = link.getDirectionValue();
        if (direction >= 0 && direction < imagesLink.length && !link.getMortValue()) {
            if (link.getInventaire().getItemEquipeValue() && link.getInventaire().getItemActuelle() instanceof Arc) {
                linkImageView.setImage(imagesLinkArc[direction]);
            } else if (link.getInventaire().getItemActuelle() instanceof Couteau && ((Couteau) link.getInventaire().getItemActuelle()).getAttaqueCouteauProperty().get()) {
                linkImageView.setImage(imagesLinkCouteau[direction]);
            } else {
                linkImageView.setImage(imagesLink[direction]);
            }
        }
    }

    public ImageView choixImage(Item item) {
        if (item instanceof Couteau) {
            return new ImageView(String.valueOf(Main.class.getResource("/image/orcish_dagger.png")));
        } else if (item instanceof Potion) {
            return new ImageView(String.valueOf(Main.class.getResource("/image/ruby.png")));
        } else if (item instanceof Bombe) {
            return new ImageView(String.valueOf(Main.class.getResource("/image/bombe.png")));
        } else if (item instanceof Arc) {
            return new ImageView(String.valueOf(Main.class.getResource("/image/arc.png")));
        } else {
            return new ImageView(String.valueOf(Main.class.getResource("/image/sol.png")));
        }
    }

}