package sae.saezelda.modele.arme;

import sae.saezelda.modele.Direction;
import sae.saezelda.modele.Environnement;
//import sae.saezelda.modele.degat.degatProjectile.DegatProjectile;
//import sae.saezelda.modele.degat.degatProjectile.DegatProjectile;
import sae.saezelda.modele.personnage.Link;

public class Arc extends Arme {
    private int nbFleche;
    public Arc( int nbFleche) {
        super(2, null);
        this.nbFleche = nbFleche;
    }

    @Override
    public void agir() {
    }

    @Override
    public void utiliser(Link link) {
        if (!isEnCooldown()) {
            if (nbFleche > 0) {
                tirerAvecArc(link);
                nbFleche--;
                System.out.println("Link utilise l'arc. Nombre de flèches restantes : " + nbFleche);
                demarrerCooldown(this::finCooldown);
            } else {
                System.out.println("Pas assez de flèches pour utiliser l'arc !");
            }
        } else {
            System.out.println("Arc est encore en cooldown");
        }
    }

    private void tirerAvecArc(Link link) {
        if (!link.getMortValue() && link.getInventaire().getItemEquipeValue()) {

            Direction direction = link.getDirection();

            if (direction == Direction.NEUTRE) {
                direction = Direction.DOWN;
            }

            int deltaX = direction.getIncrements()[0];
            int deltaY = direction.getIncrements()[1];


            int flecheX = link.getX() + (deltaX > 0 ? link.getLargeur() : (deltaX < 0 ? 0 : link.getLargeur() / 2));


            int flecheY = link.getY() + (deltaY > 0 ? link.getHauteur() : (deltaY < 0 ? 0 : link.getHauteur() / 2));


            Environnement.getInstance().creerEtAjouterFleche(flecheX, flecheY, direction);
        } else {
            if (link.getInventaire().getItemActuelle() == null) {
                System.out.println("Tu n'as pas d'arc");
            }
        }
    }

    private void finCooldown() {
        System.out.println("Cooldown de l'arc terminé.");
    }
    @Override
    public String toString() {
        return "Arc{" +
                "nbFleche=" + nbFleche +
                '}';
    }


    @Override
    public String nom() {
        return "arc";
    }


}
