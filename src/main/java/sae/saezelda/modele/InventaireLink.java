package sae.saezelda.modele;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import sae.saezelda.modele.arme.Bombe;
import sae.saezelda.modele.arme.Couteau;

import java.util.ArrayList;

public class InventaireLink extends Inventaire {
    private Item itemJeteeTemporaire;
    private BooleanProperty itemJeter;

    public InventaireLink() {
        this.itemJeter = new SimpleBooleanProperty(false);
        this.itemJeteeTemporaire = null;
        setItemActuelle(new Couteau());
        setItemEquipe(true);
    }

    public void jeterItem() {
        if (getItemEquipeProperty() != null && getItemEquipeValue()) {
            itemJeter.set(true);
            setItemEquipe(false);

            itemJeteeTemporaire = getItemActuelle();
            getItems().remove(getItemActuelle());
            setItemActuelle(null);
        }
    }

    public void recupererItemJeter() {
        if (itemJeteeTemporaire != null  && !getItems().contains(getItemActuelle())) {
            Item itemtemp = itemJeteeTemporaire;
            getItems().add(itemtemp);
            itemJeteeTemporaire = null;
            itemJeter.set(false);

            System.out.println("Item récupérée et ajoutée à l'inventaire.");
        }
    }

    public void ajouterItems(ArrayList<Item> itemsAAjouter) {
        for(Item item : itemsAAjouter) {
            ajouterItem(item);
        }
    }

    public void utiliser(ArrayList<Item> items) {
        if (items != null && !items.isEmpty()) {
            ajouterItems(items);
            System.out.println("Tous les objets du coffre ont été ajoutés à l'inventaire.");
        } else {
            System.out.println("Aucun objet à ramasser dans le coffre.");
        }
    }

    public boolean aDesBombes() {
        for(Item item : getItems()) {
            if (item instanceof Bombe) {
                return true;
            }
        }
        return false;
    }

    public BooleanProperty getItemJeterProperty() {
        return itemJeter;
    }
    public void setItemJeter(boolean itemJeter) {
        this.itemJeter.set(itemJeter);
    }

}
