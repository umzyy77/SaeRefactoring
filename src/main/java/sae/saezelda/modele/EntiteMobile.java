package sae.saezelda.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class EntiteMobile extends EntitePosition {

    private final IntegerProperty x;
    private final IntegerProperty y;
    private final IntegerProperty direction;

    public EntiteMobile(int positionX, int positionY) {
        this.x = new SimpleIntegerProperty(positionX);
        this.y = new SimpleIntegerProperty(positionY);
        this.direction = new SimpleIntegerProperty(Direction.NEUTRE.ordinal());
        this.direction.addListener((obs, oldVal, newVal) -> {
            int newDirection = newVal.intValue();
            setDirectionValue(Direction.values()[newDirection]);
        });
    }

    public int[] move() {
        int[] increments = getDirection().getIncrements();
        return new int[]{getX() + increments[0], getY() + increments[1]};
    }

    public Direction getDirection() {
        return Direction.values()[direction.getValue()];
    }

    public IntegerProperty getXProperties() {
        return this.x;
    }

    @Override
    public int getX() {
        return this.x.getValue();
    }

    @Override
    public void setX(int x) {
        this.x.setValue(x);
    }

    public IntegerProperty getYProperties() {
        return this.y;
    }

    @Override
    public int getY() {
        return this.y.getValue();
    }

    @Override
    public void setY(int y) {
        this.y.setValue(y);
    }

    public IntegerProperty getDirectionProperty() {
        return this.direction;
    }

    public int getDirectionValue() {
        return this.direction.getValue();
    }

    public void setDirectionValue(Direction direction) {
        this.direction.setValue(direction.ordinal());
    }
}