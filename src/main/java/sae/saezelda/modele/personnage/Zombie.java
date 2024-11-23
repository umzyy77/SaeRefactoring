/* La classe Zombie est une entité ennemie qui se déplace automatiquement (verticalement ou vers Link), peut attaquer à proximité
avec un système de cooldown, et interagit avec des objets (bombes, couteaux) dans sa zone.
Elle hérite des capacités de base de Personnage tout en ajoutant une logique spécifique pour ses déplacements,
 ses attaques, et ses interactions contextuelles.*/


package sae.saezelda.modele.personnage;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.util.Duration;
import sae.saezelda.modele.Direction;
import sae.saezelda.modele.Environnement;
import sae.saezelda.modele.degat.StrategieDegat;

public class Zombie extends Ennemie {

    private static final int LARGEUR_ZOMBIE = 19;
    private static final int HAUTEUR_ZOMBIE = 32;
    private static final int PROXIMITE_ATTAQUE = 2;
    private static final int COOLDOWN_DURATION = 3;
    private boolean moveUp = true;
    private BooleanProperty attaqueLink = new SimpleBooleanProperty(false);
    private boolean enCooldown = false;


    public Zombie(int posx, int posy) {
        super(posx, posy, 20, HAUTEUR_ZOMBIE, LARGEUR_ZOMBIE, 100);
    }

    public void attaqueLink() {
        Environnement environnement = Environnement.getInstance();
        Link link = environnement.getGestionLink().getLink();
        int linkX = link.getX();
        int linkY = link.getY();

        if (!link.getMortValue()) {
            deplacerVersLink(linkX, linkY);
        } else {
            deplacer();
        }
    }

    // Déplacement vertical automatique
    public void deplacer() {
        Direction direction = moveUp ? Direction.UP : Direction.DOWN;
        setDirectionValue(direction);
        int[] nextPosition = super.move();
        int newX = nextPosition[0];
        int newY = nextPosition[1];

        if (super.canMove(direction, newX, newY)) {
            setX(newX);
            setY(newY);
        } else {
            moveUp = !moveUp;
        }
    }

    // Déplacement vers Link
    public void deplacerVersLink(int linkX, int linkY) {
        if (!getMortValue()) {
            if (getY() == linkY) {
                deplacerHorizontalement(linkX);
            } else if (getX() == linkX) {
                deplacerVerticalement(linkY);
            } else {
                deplacer();
            }
            coupLink();
        } else if (Environnement.getInstance().getGestionLink().getLink().getMortValue() && !getMortValue()) {
            deplacer();
        }
    }


    private void deplacerVerticalement(int linkY) {
        if (getY() < linkY) {
            deplacerDansDirection(Direction.UP, 2);
        } else if (getY() > linkY) {
            deplacerDansDirection(Direction.DOWN, 2);
        }
    }

    private void deplacerHorizontalement(int linkX) {
        if (getX() < linkX) {
            deplacerDansDirection(Direction.RIGHT, 2);
        } else if (getX() > linkX) {
            deplacerDansDirection(Direction.LEFT, 2);
        }
    }

    private void deplacerDansDirection(Direction direction, int pas) {
        int newX = getX() + direction.getIncrements()[0] * pas;
        int newY = getY() + direction.getIncrements()[1] * pas;
        if (super.canMove(direction, newX, newY)) {
            setX(newX);
            setY(newY);
        }
    }

    // Attaque Link
    public void coupLink() {
        Link link = Environnement.getInstance().getGestionLink().getLink();
        if (!getMortValue() && !link.getMortValue() && !enCooldown && estALaPorteeDuLink(link)) {
            attaqueLink.set(true);
            link.recevoirDegats(10);
            demarrerCooldown();
        } else {
            attaqueLink.set(false);
        }
    }

    private boolean estALaPorteeDuLink(Link link) {
        int distanceX = Math.abs(getX() - link.getX());
        int distanceY = Math.abs(getY() - link.getY());
        return distanceX <= PROXIMITE_ATTAQUE && distanceY <= PROXIMITE_ATTAQUE;
    }


    // Cooldown d'attaque
    private void demarrerCooldown() {
        enCooldown = true;
        Timeline cooldownTimeline = new Timeline(new KeyFrame(Duration.seconds(COOLDOWN_DURATION), event -> {
            enCooldown = false;
        }));
        cooldownTimeline.play();
    }

    @Override
    public String nom() {
        return "zombie";
    }
    public BooleanProperty attaqueLinkProperty() {
        return attaqueLink;
    }

}