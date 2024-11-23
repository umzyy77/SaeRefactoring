package sae.saezelda.modele.terrain;

public interface Terrain {

    String getNom();

    int[] generateTerrainData();

    String getMessageTerrain();

    default int getWidth() {
        return 640;
    }


    default int getHeight() {
        return 320;
    }




}
