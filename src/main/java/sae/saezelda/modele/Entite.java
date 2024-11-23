package sae.saezelda.modele;


// classe qui identifie les autres classe
public abstract class Entite {
    private final int id;
    private static int nextId = 1;
    public Entite() {
        this.id = nextId++;
    }


    public int getId() {
        return id;
    }
    // recupere le nom dans chaque sous-classes
    public abstract String nom();
    public abstract void agir();
}
