package sae.saezelda.controleur;


import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import sae.saezelda.GameLoop;
import sae.saezelda.controleur.observableListe.*;
import sae.saezelda.modele.*;
import sae.saezelda.modele.arme.Arc;
import sae.saezelda.modele.arme.Bombe;
import sae.saezelda.modele.obstacle.*;
import sae.saezelda.modele.potion.PotionVie;
import sae.saezelda.modele.objet.Coffre;
import sae.saezelda.modele.personnage.Aquaman;
import sae.saezelda.modele.personnage.Link;
import sae.saezelda.modele.personnage.Pnj;
import sae.saezelda.modele.personnage.Zombie;
import sae.saezelda.modele.terrain.GestionnaireTerrain;
import sae.saezelda.modele.terrain.Donjon;
import sae.saezelda.modele.terrain.Terrain;
import sae.saezelda.vue.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controleur implements Initializable {
    private Environnement environnement ;

    private GameLoop gameLoop;
    @FXML
    private Pane paneJeu;
    @FXML
    private TilePane panneauDeJeu;
    @FXML
    private Label pvLink;
    @FXML
    private Label dialogueLabel;
    @FXML
    private Label gameOverLabel;
    @FXML
    private GridPane inventaireGrid;
    @FXML
    private GridPane porteGrid;
    @FXML
    private VBox sidePane;
    private boolean hPresser, bPresser, gPresser, dPresser;
    private Coffre coffre1;
    private Arc arc;
    private Link link;
    private LinkVue linkVue;
    private InventaireVue inventaireVue;

    private Pnj pnj;

    private ArrayList<Terrain> terrains = new ArrayList<>();
    private GestionnaireTerrain gestionnaireTerrain;
    private TerrainVue terrainVueActif;
    private boolean terrainRemplace = false;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //creation de l'environnement
        environnement= Environnement.getInstance();
        gestionnaireTerrain = environnement.getGestionTerrain();
        terrains.add(gestionnaireTerrain.getTerrain());
        terrainVueActif = new TerrainVue(gestionnaireTerrain, panneauDeJeu);

        //creation de link
        link = new Link();
        environnement.getGestionLink().setLink(link);
        environnement.ajouterEntite(link);
        linkVue = new LinkVue(link, paneJeu, terrainVueActif);
        link.getMortProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                afficherGameOver();
            }
        });


        MonObservableListeEntite monObservableListeEntite = new MonObservableListeEntite(paneJeu);
        environnement.getEntites().addListener(monObservableListeEntite);




        pvLink.textProperty().bind(link.getPvProperties().asString());

        Obstacle pierreSimple = new Pierre(100, 100);
        Environnement.getInstance().ajouterEntite(pierreSimple);



        ArrayList<Item> items = new ArrayList<>();
        items.add(new Bombe());
        items.add(new Bombe());
        items.add(new Bombe());
        items.add(new PotionVie());

        arc = new Arc( 2000);
        items.add(arc);
//        coffre1 = new Coffre(arc, 2 * 32, 0);
        coffre1 = new Coffre(64,0, items);
        environnement.ajouterEntite(coffre1);

        this.pnj = new Pnj( 620, 170, 10, 32, 19, 10000);
        environnement.ajouterEntite(pnj);
        inventaireVue = new InventaireVue(sidePane,inventaireGrid,porteGrid,link);
        link.getInventaire().getItems().addListener(new ListChangeListener() {
            @Override
            public void onChanged(Change change) {
                inventaireVue.dessinePane();
            }
        });

        Zombie zombie = new Zombie( 380, 50);
        environnement.ajouterEntite(zombie);
//        environnement.ajouterEntite(zombie);

        gameLoop = new GameLoop(link, linkVue);
        gameLoop.startGameLoop(paneJeu);

        paneJeu.setFocusTraversable(true);
        paneJeu.requestFocus();
    }


    private void afficherGameOver() {
        gameOverLabel.setVisible(true);
    }

    private void remplacerTerrain() {
        if (!terrainRemplace) {
            terrains.add(new Donjon());

            gestionnaireTerrain.changerTerrain(terrains.get(1));
            ajouterElementsAuNouveauTerrain();
            terrainRemplace = true;

            terrainVueActif.setTerrainVue(gestionnaireTerrain);

        }

    }


    private void ajouterElementsAuNouveauTerrain() {
        ArrayList<Item> items = new ArrayList<>();
        items.add(new PotionVie());
        coffre1 = new Coffre(610, 0, items);
        environnement.ajouterEntite(coffre1);

        Aquaman aquaman = new Aquaman();
        environnement.ajouterEntite(aquaman);

        Pierre pierre1 = new Pierre(200, 150);
        environnement.ajouterEntite(pierre1);

        Pierre pierre2 = new Pierre(300, 40);
        environnement.ajouterEntite(pierre2);

        Zombie zombie = new Zombie( 250, 150);
        environnement.ajouterEntite(zombie);

        Zombie zombie2 = new Zombie(15, 200);
        environnement.ajouterEntite(zombie2);
    }

    @FXML
    public void touchePresser(KeyEvent event) {
        KeyCode code = event.getCode();
        if (code == KeyCode.F) {
            if (link.getInventaire().getItemEquipeValue()) {
                Item itemActuelle = link.getInventaire().getItemActuelle();

                if (itemActuelle != null) {
                    itemActuelle.utiliser(link);
                } else {
                    System.out.println("Aucune item actuelle à utiliser.");
                }
            } else {
                System.out.println("Vous êtes équipé d'aucun item.");
            }
        }
        if (code == KeyCode.Z) {
            hPresser = true;
        }
        else if (code == KeyCode.S) {
            bPresser = true;
        }
        else if (code == KeyCode.Q) {
            gPresser = true;
        }
        else if (code == KeyCode.D) {
            dPresser = true;
        }
        else if (code == KeyCode.E) {

            if (coffre1 != null && coffre1.linkEstProche(Environnement.getInstance().getGestionLink().getLink().getX(),
                    Environnement.getInstance().getGestionLink().getLink().getY())) {
                coffre1.ouvrir();
            }
            else if(environnement.getGestionLink().estDansItemZone()) {
                link.getInventaire().recupererItemJeter();
            }
        }
        else if(code == KeyCode.P) {
            if (environnement.getGestionLink().linkEstDansZoneTeleportation() && !terrainRemplace) {
                remplacerTerrain();
            }
        }
        else if (code == KeyCode.I) {
            if(link.estDansZone(pnj.getX(), pnj.getY(), 25))
                dialogueLabel.setText(pnj.parler());

        }
        else if (code == KeyCode.T) {
            link.getInventaire().jeterItem();
        }
        changerDirectionLink();
    }


    @FXML
    public void toucheRelacher(KeyEvent event) {
        KeyCode code = event.getCode();
        if (code == KeyCode.Z) {
            hPresser = false;
        } else if (code == KeyCode.S) {
            bPresser = false;
        } else if (code == KeyCode.Q) {
            gPresser = false;
        } else if (code == KeyCode.D) {
            dPresser = false;
        }
        changerDirectionLink();
    }




    private void changerDirectionLink() {
        if (hPresser && gPresser) {
            link.setDirectionValue(Direction.UP_LEFT);
        } else if (hPresser && dPresser) {
            link.setDirectionValue(Direction.UP_RIGHT);
        } else if (bPresser && gPresser) {
            link.setDirectionValue(Direction.DOWN_LEFT);
        } else if (bPresser && dPresser) {
            link.setDirectionValue(Direction.DOWN_RIGHT);
        } else if (hPresser) {
            link.setDirectionValue(Direction.UP);
        } else if (bPresser) {
            link.setDirectionValue(Direction.DOWN);
        } else if (gPresser) {
            link.setDirectionValue(Direction.LEFT);
        } else if (dPresser) {
            link.setDirectionValue(Direction.RIGHT);
        } else {
            link.setDirectionValue(Direction.NEUTRE);
        }
    }
}



