package sae.saezelda.modele;

public abstract class EntiteStatique extends EntitePosition {
    private int x;
    private int y;

    public EntiteStatique(int positionX, int positionY) {
        this.x = positionX;
        this.y = positionY;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }
}
