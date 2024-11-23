package sae.saezelda.modele;//package sae.saezelda.modele;
//
//import sae.saezelda.modele.personnage.Link;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public abstract class CompositeMemeItem extends Item {
//    private List<Item> items;
//
//    public CompositeMemeItem(double cooldown) {
//        super(cooldown);
//        this.items = new ArrayList<>();
//    }
//
//    public void ajouterItem(Item item) {
//        // Vérifie si l'item est du même type que celui de la liste
//        if (items.isEmpty() || items.get(0).getClass().equals(item.getClass())) {
//            items.add(item);
//        } else {
//            throw new IllegalArgumentException("Tous les items doivent être du même type.");
//        }
//    }
//
//    @Override
//    public void utiliser(Link link) {
//        Item itemAUtiliser = items.get(0);
//        if (!items.isEmpty() && !itemAUtiliser.isEnCooldown()) {
//             // Prend le premier item de la liste
//            itemAUtiliser.utiliser(link); // Utilise l'item courant
//
//            // Démarre le cooldown de l'item courant
//            itemAUtiliser.demarrerCooldown(() -> {
//                // Logique après le cooldown, si nécessaire
//            });
//
//            // Supprime l'item de la liste après utilisation
//            items.remove(0); // Supprime le premier item
//        }
//    }
//
//
//
//}
//
