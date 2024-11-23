package sae.saezelda.modele;


import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sae.saezelda.modele.Item;
import sae.saezelda.modele.arme.Bombe;
import sae.saezelda.modele.arme.Couteau;

import java.util.ArrayList;

public abstract class Inventaire {
    private ObservableList<Item> items;
    private Item itemActuelle;
    private BooleanProperty itemEquipe;
    public Inventaire() {
        this.items = FXCollections.observableArrayList();
        this.itemActuelle = null;
        this.itemEquipe = new SimpleBooleanProperty(false);
    }
    public void equiper(Item item) {
        if (itemActuelle == null) {
                equiperItem(item);
            }
            else {
                desequiperItem();
                equiperItem(item);
            }
            itemEquipe.set(true);
    }
    public void desequiperItem() {
        if (itemActuelle != null) {
            Item itemPrecedent = itemActuelle;
            items.add(itemPrecedent);
            itemActuelle = null;
            itemEquipe.set(false);
            System.out.println("Item déséquipée");
        }
    }
    public void equiperItem(Item item) {
        itemActuelle = item;
        itemEquipe.set(true);
        items.remove(item);
    }

    public ObservableList<Item> getItems() {
        return items;
    }

    public void setItemEquipe(boolean itemEquipe) {
        this.itemEquipe.set(itemEquipe);
    }

    public Item getItemActuelle() {
        return itemActuelle;
    }
    public void setItemActuelle(Item itemActuelle) {
        this.itemActuelle = itemActuelle;
    }
    public boolean getItemEquipeValue() {
        return itemEquipe.get();
    }
    public BooleanProperty getItemEquipeProperty() {
        return itemEquipe;
    }



    public void ajouterItem(Item item) {
        items.add(item);
    }
    public void ajouterItems(ArrayList<Item> itemsAAjouter) {
        for(Item item : itemsAAjouter) {
            ajouterItem(item);
        }
    }

}