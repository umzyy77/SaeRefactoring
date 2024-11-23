package sae.saezelda.modele;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sae.saezelda.modele.personnage.Link;
import sae.saezelda.modele.terrain.GestionnaireTerrain;
import sae.saezelda.modele.objet.Fleche;
import java.util.ArrayList;

public class Environnement {
    private static Environnement instance = null;
    //cree par la class terrain c'est donc a lui d'avoir cette variable
    private ObservableList<Entite> entites;
    private ArrayList<Entite> entitesASupprimer = new ArrayList<>();
    private GestionLink gestionLink;
    private GestionnaireTerrain gestionTerrain;
    private ArrayList<EntitePosition> entitesPositionnees = new ArrayList<>();
    private ArrayList<VulnerableDegatInterface> entitesVulnerables = new ArrayList<>();

    private Environnement() {
        entites = FXCollections.observableArrayList();
        this.gestionTerrain = new GestionnaireTerrain();
        this.gestionLink = new GestionLink(new Link());
    }

    public static Environnement getInstance() {
        if (instance == null) {
            instance = new Environnement();
        }
        return instance;
    }

    public void faireAgirToutesLesEntites() {
        for (Entite entite : new ArrayList<>(entites)) {
            entite.agir();
        }
        entites.removeAll(entitesASupprimer);
        entitesASupprimer.clear();
    }

    public void creerEtAjouterFleche(int x, int y, Direction direction) {
        Fleche fleche = new Fleche(x, y, direction, 5);
        entites.add(fleche);
    }

    public boolean estObstacle(int x, int y) {
        int indice = gestionTerrain.getIndiceTuile(x, y);
        if (indice < 0) {
            return false;
        }
        return gestionTerrain.getTerrainData()[indice] == 1 || gestionTerrain.getTerrainData()[indice] == 4;
    }

    public void ajouterEntite(Entite entite) {
        entites.add(entite);

        if (entite instanceof EntitePosition) {
            entitesPositionnees.add((EntitePosition) entite);
        }
        if (entite instanceof VulnerableDegatInterface) {
            entitesVulnerables.add((VulnerableDegatInterface) entite);
        }
    }

    public void retirerEntite(Entite entite) {
        entitesASupprimer.add(entite);
        entitesPositionnees.remove(entite);
        entitesVulnerables.remove(entite);
    }

    public void nettoyerEntitesASupprimer() {
        entites.removeAll(entitesASupprimer);
        entitesPositionnees.removeAll(entitesASupprimer);
        entitesVulnerables.removeAll(entitesASupprimer);
        entitesASupprimer.clear();
    }

    public ObservableList<Entite> getEntites(){
        return entites;
    }
    public GestionnaireTerrain getGestionTerrain(){
        return this.gestionTerrain;
    }
    public GestionLink getGestionLink(){return this.gestionLink;}
    public ArrayList<EntitePosition> getEntitesPositionnees() {
        return entitesPositionnees;
    }
    public ArrayList<VulnerableDegatInterface> getEntitesVulnerables() {
        return entitesVulnerables;
    }

}