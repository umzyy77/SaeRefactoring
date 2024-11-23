package sae.saezelda.modele;

import sae.saezelda.modele.obstacle.Obstacle;
import sae.saezelda.modele.personnage.Link;
import sae.saezelda.modele.personnage.Pnj;

public class GestionLink {

    Link link;

    public GestionLink(Link link) {
        this.link = link;
    }

    public void setLink(Link link) {
        this.link = link;
    }

    public boolean estDansLesLimites(int x, int y) {
        if (link == null) {
            return false;
        }
        int largeurLink = link.getLargeur();
        int hauteurLink = link.getHauteur();

        return x >= 0 && x <= (Environnement.getInstance().getGestionTerrain().getLargeur() - largeurLink) && y >= -hauteurLink && y <= (Environnement.getInstance().getGestionTerrain().getHauteur() - hauteurLink);
    }



    public boolean linkEstDansZoneTeleportation() {
        final int minX = 600, maxX = 625, minY = 280, maxY = 320;
        int linkX = link.getX();
        int linkY = link.getY();

        System.out.println(linkX);
        System.out.println(linkY);

        return (linkX >= minX && linkX <= maxX && linkY >= minY && linkY <= maxY);
    }

    public boolean estDansItemZone() {
        int itemX = link.getX();
        int itemY = link.getY();
        int hauteurLink = link.getHauteur();
        int dimension32 = 32;

        return (itemX - hauteurLink < itemX + dimension32 &&
                itemX + (hauteurLink * 2) > itemX &&
                itemY - hauteurLink < itemY + dimension32 &&
                itemY + (hauteurLink * 2) > itemY);
    }

    public Link getLink() {
        return link;
    }
}
