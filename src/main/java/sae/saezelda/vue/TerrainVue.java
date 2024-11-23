package sae.saezelda.vue;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import sae.saezelda.Main;
import sae.saezelda.modele.terrain.GestionnaireTerrain;

public class TerrainVue {
    private GestionnaireTerrain gestionnaireTerrain;
    private TilePane panneauJeu;



    public TerrainVue(GestionnaireTerrain gestionnaireTerrain, TilePane panneauJeu){
        this.gestionnaireTerrain = gestionnaireTerrain;
        this.panneauJeu = panneauJeu;

        afficherTerrain();
    }

    public void setTerrainVue(GestionnaireTerrain gestionnaireTerrain) {
        this.gestionnaireTerrain = gestionnaireTerrain;
        this.panneauJeu = panneauJeu;
        afficherTerrain();
    }


    public void afficherTerrain(){
        panneauJeu.setPrefColumns(20);
        panneauJeu.setMaxWidth(20*32);
        panneauJeu.setPrefRows(10);
        panneauJeu.setMaxHeight(10*32);

        Image eau = new Image(String.valueOf(Main.class.getResource("/image/eau.png")));
        Image sol = new Image(String.valueOf(Main.class.getResource("/image/herbe.png")));
        Image solPierre = new Image(String.valueOf(Main.class.getResource("/image/solPierre.png")));
        Image fleur = new Image(String.valueOf(Main.class.getResource("/image/fleur.png")));
        Image obstaclePierre = new Image(String.valueOf(Main.class.getResource("/image/pierre.png")));
        Image portail = new Image(String.valueOf(Main.class.getResource("/image/portail.png")));


        panneauJeu.getChildren().clear();

        for (int i = 0; i < gestionnaireTerrain.getTerrainData().length; i++){
            switch (gestionnaireTerrain.getTerrainData()[i]) {
                case 0 :
                    ImageView ivSol = new ImageView(sol);
                    panneauJeu.getChildren().add(ivSol);
                    break;
                case 1 :
                    ImageView ivEau = new ImageView(eau);
                    panneauJeu.getChildren().add(ivEau);
                    break;

                case 2 :
                    ImageView ivfleur = new ImageView(fleur);
                    panneauJeu.getChildren().add(ivfleur);
                    break;
                case 3 :
                    ImageView ivSolPierre = new ImageView(solPierre);
                    panneauJeu.getChildren().add(ivSolPierre);
                    break;
                case 4 :
                    ImageView ivObstaclePierre = new ImageView(obstaclePierre);
                    panneauJeu.getChildren().add(ivObstaclePierre);
                case 5 :
                    ImageView imageViewPortail = new ImageView(portail);
                    panneauJeu.getChildren().add(imageViewPortail);
            }
        }
    }



    public TilePane getPanneauJeu() {
        return panneauJeu;
    }
}