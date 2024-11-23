package sae.saezelda.modele.terrain;

import sae.saezelda.modele.Environnement;
import sae.saezelda.modele.personnage.Link;

public class GestionnaireTerrain {
    private String nom;
    private Terrain terrain;
    protected int[] terrainData;
    private int tailleTuile = 32;

    public GestionnaireTerrain() {
        this.terrain = new Terrain1();
        this.nom = terrain.getNom();

        terrainData=terrain.generateTerrainData();
    }


    public int getIndiceTuile(int x, int y) {
        int colonne = x / getTailleTuile();
        int ligne = y / getTailleTuile();

        if (colonne < 0 || colonne >= getLargeur() / getTailleTuile() || ligne < 0 || ligne >= getHauteur() / getTailleTuile()) {
            return -1;
        }
        return ligne * (getLargeur() / getTailleTuile()) + colonne;
    }
    public void reinitialiserElementsTerrain(Link link) {
        Environnement.getInstance().getGestionLink().getLink().getInventaire().setItemJeter(false);
        Environnement.getInstance().getEntites().clear();
        Environnement.getInstance().ajouterEntite(link);
    }


    public void changerTerrain(Terrain terrain) {
        setTerrain(terrain);
        reinitialiserElementsTerrain(Environnement.getInstance().getGestionLink().getLink());
        System.out.println(terrain.getMessageTerrain());

    }

    public void setTerrain(Terrain nouveauTerrain) {
        this.nom = nouveauTerrain.getNom();
        this.terrain = nouveauTerrain;
        this.terrainData= nouveauTerrain.generateTerrainData();

    }

    public Terrain getTerrain() {
        return terrain;
    }

    public String getNom() {
        return nom;
    }

    public int getLargeur() {
        return terrain.getWidth();
    }

    public int getTailleTuile(){return tailleTuile;}

    public int getHauteur() {
        return terrain.getHeight();
    }

    public int[] getTerrainData() {
        return terrainData;
    }
}
