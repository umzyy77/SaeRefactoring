package sae.saezelda.modele.terrain;

public class Terrain1 implements Terrain {
    @Override
    public String getNom() {
        return "Loby";
    }

    @Override
    public String getMessageTerrain() {
        return "Bienvenue dans le Loby !";
    }

    @Override
    public int[] generateTerrainData() {
        return new int[]{
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 0, 0, 0,
                3, 3, 3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 3, 3, 3, 3, 3,
                0, 0, 0, 0, 0, 0, 3, 3, 3, 3, 3, 3, 3, 0, 0, 3, 0, 0, 0, 0,
                1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 3, 3, 3, 3, 0, 2, 0, 0,
                0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0,
                0, 0, 2, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2,
                0, 0, 0, 2, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 2, 0, 1, 5,
        };
    }



}
