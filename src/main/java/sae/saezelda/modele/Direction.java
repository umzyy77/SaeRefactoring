package sae.saezelda.modele;

public enum Direction {
    UP(0, -1),
    DOWN(0, 1),
    LEFT(-1, 0),
    RIGHT(1, 0),
    NEUTRE(0, 0),
    UP_LEFT(-1, -1),
    UP_RIGHT(1, -1),
    DOWN_LEFT(-1, 1),
    DOWN_RIGHT(1, 1);

    private final int deltaX;
    private final int deltaY;

    public int incrementation(Direction direction){
        return this.ordinal();
    }

    Direction(int deltaX, int deltaY) {
        this.deltaX = deltaX;
        this.deltaY = deltaY;
    }

    public int[] getIncrements() {
        return new int[]{deltaX, deltaY};
    }

    // Retourne la direction à partir d'un entier (ordinal)
    public static Direction fromOrdinal(int ordinal) {
        if (ordinal < 0 || ordinal >= values().length) {
            throw new IllegalArgumentException("Ordinal invalide pour la Direction: " + ordinal);
        }
        return values()[ordinal];
    }

    // Retourne le deltaX associé à la direction
    public int getDeltaX() {
        return deltaX;
    }

    // Retourne le deltaY associé à la direction
    public int getDeltaY() {
        return deltaY;
    }

}