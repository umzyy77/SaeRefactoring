/* La classe Link représente le personnage principal jouable, héritant des fonctionnalités de base de Personnage
tout en ajoutant une gestion de son inventaire et des capacités spécifiques comme pousser des pierres.
Elle intègre une logique de déplacement avec vérification des obstacles et interactions contextuelles (ex. terrain, obstacles).*/

package sae.saezelda.modele.personnage;

import sae.saezelda.modele.Direction;
import sae.saezelda.modele.Environnement;
import sae.saezelda.modele.InventaireLink;
import sae.saezelda.modele.obstacle.Obstacle;
import sae.saezelda.modele.obstacle.Pierre;

public class Link extends Personnage {
    public static final int PV_MAX = 200;
    private InventaireLink inventaire;
    private boolean peutBouger;
    public Link() {
        super(0, 0, 15, 32, 19, 200);
        this.inventaire = new InventaireLink();
        this.peutBouger = true;
    }

    @Override
    public void agir() {
        linkMove();
    }

    public void linkMove() {
        if(getPvValue() <= 0) {
            setMortValue(true);
        }
        if(!getMortValue()) {
            int[] tabindice = super.move();
            linkVerification(tabindice[0], tabindice[1]);
        }
    }

    public void pousserPierre(Direction direction, Pierre pierre) {
        int[] increments = direction.getIncrements();

        int deltaX = increments[0] * 2;
        int deltaY = increments[1] * 2;

        deplacerPierre(pierre, deltaX, deltaY);
    }

    public boolean deplacerPierre(Pierre pierre, int x, int y) {
        int terrainLargeur = Environnement.getInstance().getGestionTerrain().getLargeur();
        int terrainHauteur = Environnement.getInstance().getGestionTerrain().getHauteur();
        int newX = pierre.getX() + x;
        int newY = pierre.getY() + y;
        if (newX >= 0 && newX <= terrainLargeur - pierre.getLargeurObstacle() && newY >= 0 && newY <= terrainHauteur - pierre.getHauteurObstacle()) {
            pierre.move(x, y);
            return true;
        }
        return false;
    }

    public void linkVerification(int x, int y) {
        if (canMove(getDirection(), x, y) && getPeutBouger()) {
            setX(x);
            setY(y);
        }
    }

    @Override
    public String nom() {
        return "Link";
    }
    public InventaireLink getInventaire(){
        return inventaire;
    }

    public void setPeutBouger(boolean peutBouger) {
        this.peutBouger = peutBouger;
    }

    public boolean getPeutBouger() {
        return peutBouger;
    }
}