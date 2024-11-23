/* La classe Aquaman représente un ennemi spécifique qui interagit avec l'environnement et Link.
Elle intègre une logique d'attaque basée sur la portée et un délai de tir,
tout en héritant des fonctionnalités de mouvement et gestion de vie définies dans la classe Personnage.*/

package sae.saezelda.modele.personnage;

import sae.saezelda.modele.Direction;
import sae.saezelda.modele.Environnement;
import sae.saezelda.modele.objet.LanceMonstre;
import sae.saezelda.modele.terrain.GestionnaireTerrain;

public class Aquaman extends Ennemie {
    private int delaisTir = 120;

    private Environnement environnement;


    public Aquaman() {
        super(530, 200, 20, 32, 19,10000);
        this.environnement = Environnement.getInstance();

    }

    public void attaqueLink() {
        if (this.getPvValue() > 0 && delaisTir <= 0) {
            if (link.estDansZone(this.getX(), this.getY(), 150)) {
                System.out.println("cote aquaman sa touche");
                LanceMonstre lanceMonstre = new LanceMonstre(Direction.NEUTRE);
                environnement.ajouterEntite(lanceMonstre);
                lanceMonstre.tir(getX(), getY(), link.getX(), link.getY());
                delaisTir = 120;
            }
        }
        delaisTir--;
    }

    @Override
    public String nom() {
        return "Aquaman";
    }
}