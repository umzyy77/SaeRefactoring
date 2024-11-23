package sae.saezelda.controleur.observableListe;

import javafx.collections.ListChangeListener;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import sae.saezelda.modele.Entite;
import sae.saezelda.vue.CreerVuesEntites;

public class MonObservableListeEntite implements ListChangeListener<Entite> {

    private final Pane panneauJeu;

    public MonObservableListeEntite(Pane panneauJeu) {
        this.panneauJeu = panneauJeu;
    }

    @Override
    public void onChanged(Change<? extends Entite> change) {
        while (change.next()) {
            if (change.wasAdded()) {
                for (Entite entite : change.getAddedSubList()) {
                    CreerVuesEntites.creerVue(entite, panneauJeu);
                }
            }
            if (change.wasRemoved()) {
                for (Entite entite : change.getRemoved()) {
                    ImageView imageView = (ImageView) panneauJeu.lookup("#" + entite.nom() + entite.getId());
                    if (imageView != null) {
                        panneauJeu.getChildren().remove(imageView);
                    }
                }
            }
        }
    }
}