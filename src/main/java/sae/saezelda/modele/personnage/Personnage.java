    package sae.saezelda.modele.personnage;

    import javafx.beans.property.BooleanProperty;
    import javafx.beans.property.IntegerProperty;
    import javafx.beans.property.SimpleBooleanProperty;
    import javafx.beans.property.SimpleIntegerProperty;
    import sae.saezelda.modele.VulnerableDegatInterface;
    import sae.saezelda.modele.Direction;
    import sae.saezelda.modele.EntiteMobile;
    import sae.saezelda.modele.Environnement;
    import sae.saezelda.modele.obstacle.Obstacle;

    public abstract class Personnage extends EntiteMobile  implements VulnerableDegatInterface{

    private static final int DEFAULT_MARGE = 5; // Marge pour les collisions
    private int largeur;
    private int hauteur;
    private int marge = DEFAULT_MARGE;
    private IntegerProperty pv;
    private BooleanProperty mort;

    private int capaciteMax;

    public Personnage(int x, int y, int capaciteMax, int hauteur, int largeur, int pv) {
        super(x, y);
        this.largeur = largeur;
        this.hauteur = hauteur;
        this.pv = new SimpleIntegerProperty(pv);
        this.mort = new SimpleBooleanProperty(false);
    }

        public void setMortValue(boolean bool) {
            mort.set(bool);
        }

        public BooleanProperty getMortProperty() {
            return mort;
        }

        public boolean getMortValue() {
            return mort.get();
        }

        @Override
        public void recevoirDegats(int degats) {
            if(!getMortValue()) {
                setPvValue(getPvValue() - degats);
            }
            if (getPvValue() <= 0) {
                mourir();
            }
        }

        public void mourir() {
            this.mort.set(true);
            setPvValue(0);
            Environnement.getInstance().retirerEntite(this);
        }

        @Override
        public void agir() {
            Direction direction = getDirection();
            int[] newCoords = move();
            if (canMove(direction, newCoords[0], newCoords[1])) {
                setX(newCoords[0]);
                setY(newCoords[1]);
            }
        }
        public int[] move() {
            int[] increments = getDirection().getIncrements();
            return new int[]{getX() + increments[0], getY() + increments[1]};
        }


//    public boolean canMove(Direction direction, int x, int y) {
//        int[] increments = direction.getIncrements();
//        int nouveauX = x + increments[0];
//        int nouveauY = y + increments[1];
//
//        if (!verifierLimites(nouveauX, nouveauY) || !verifierObstacles(nouveauX, nouveauY)) {
//            return false;
//        }
        public boolean canMove(Direction direction, int x, int y) {
            return Environnement.getInstance().getGestionLink().estDansLesLimites(x, y) &&
                    !detecterObstacle(direction, x, y);
        }

        public boolean detecterObstacle(Direction direction, int x, int y) {
            Environnement env = Environnement.getInstance();

            switch (direction) {
                case UP:
                    // on enlève 3 pixels pour un meilleur comportement.
                    return env.estObstacle(x, y + hauteur - 3) || env.estObstacle(x + largeur - 1, y + hauteur - 3);
                case DOWN:
                    // Collision tête.
                    return env.estObstacle(x, y + hauteur) || env.estObstacle(x + largeur - 1, y + hauteur);
                case LEFT:
                    return env.estObstacle(x, y + hauteur - 1 - 1); // Ajustement au pixel près.
                case RIGHT:
                    return env.estObstacle(x + largeur, y + hauteur - 1 - 1);
                case UP_LEFT:
                case UP_RIGHT:
                case DOWN_LEFT:
                case DOWN_RIGHT:
                    return detecterObstacle(Direction.UP, x, y) || detecterObstacle(Direction.LEFT, x, y);
                default:
                    return false;
            }
        }

    public boolean detecterPierre(Obstacle obstacle, int x, int y) {
        int linkX = x;
        int linkY = y;
        int largeurPerso = getLargeur();
        int hauteurPerso = getHauteur();

        int pierreX = obstacle.getX();
        int pierreY = obstacle.getY();
        int largeurPierre = obstacle.getLargeurObstacle();
        int hauteurPierre = obstacle.getHauteurObstacle();

        // Le 3 c'est juste pour ajuster les bords et les positions auquels link peu pousser la pierre pour un rendu propre

        switch (getDirection()) {
            case UP:
                return linkX + largeurPerso > pierreX + 3 && linkX < pierreX + largeurPierre - 3 &&
                        linkY + hauteurPerso >= pierreY && linkY + hauteurPerso <= pierreY + hauteurPierre;
            case DOWN:
                return linkX + largeurPerso > pierreX + 3 && linkX < pierreX + largeurPierre - 3 &&
                        linkY + hauteurPerso >= pierreY && linkY + hauteurPerso < pierreY + hauteurPierre;
            case LEFT:
                return linkY + hauteurPerso > pierreY + 3 && linkY + hauteurPerso <= pierreY + hauteurPierre - 3 &&
                        linkX <= pierreX + largeurPierre && linkX > pierreX;
            case RIGHT:
                return linkY + hauteurPerso > pierreY + 3 && linkY + hauteurPerso <= pierreY + hauteurPierre - 3 &&
                        linkX + largeurPerso >= pierreX && linkX + largeurPerso < pierreX + largeurPierre;
            case UP_LEFT:
            case UP_RIGHT:
            case DOWN_LEFT:
            case DOWN_RIGHT:
                // cas de figure en diagonale
                boolean collisionHorizontale = linkX + largeurPerso > pierreX && linkX < pierreX + largeurPierre;
                boolean collisionVerticale = linkY + hauteurPerso > pierreY && linkY < pierreY + hauteurPierre;
                return collisionHorizontale && collisionVerticale;
            default:
                return false;
        }
    }

//    private boolean pousserPierre(Obstacle pierre, Direction direction) {
//        int[] increments = direction.getIncrements();
//        int nouveauX = pierre.getXValue() + increments[0];
//        int nouveauY = pierre.getYValue() + increments[1];
//
//        // Vérifier si la pierre peut être poussée
//        if (!verifierLimites(nouveauX, nouveauY) || !verifierObstacles(nouveauX, nouveauY)) {
//            return false; // Mouvement impossible
//        }
//
//        // Déplacer la pierre
//        pierre.setX(nouveauX);
//        pierre.setYValue(nouveauY);
//        return true;
//    }

    public boolean peutPousserPierre() {
        return false; // Par défaut, les personnages ne peuvent pas pousser
    }


    private boolean verifierLimites(int x, int y) {
        return Environnement.getInstance().getGestionLink().estDansLesLimites(x, y);
    }


        public boolean estDansZone(int objetX, int objetY, int portee) {
            int persoX = getX();
            int persoY = getY();
            return persoX - getLargeur() < objetX + (portee * 2) &&
                    persoX + getLargeur() * 2 > objetX &&
                    persoY - getHauteur() < objetY + (portee * 2) &&
                    persoY + getHauteur() * 2 > objetY;
        }

        /* GETTEUR / SETTEUR */

        public IntegerProperty getPvProperties() {
            return pv;
        }

        public int getPvValue() {
            return pv.getValue();
        }

        public void setPvValue(int pv) {
            this.pv.setValue(pv);
        }

    public int getMarge() {
        return marge;
    }
        
    public int getLargeur() {
        return largeur;
    }

    public int getHauteur() {
        return hauteur;
    }
}
