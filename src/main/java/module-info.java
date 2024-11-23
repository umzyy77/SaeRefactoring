module sae.saezelda {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;
    requires junit;

    opens sae.saezelda to javafx.fxml;
    exports sae.saezelda;

    exports sae.saezelda.controleur;
    opens sae.saezelda.controleur to javafx.fxml;
    exports main.test to junit;
    opens main.test to junit;

    exports sae.saezelda.modele;
    opens sae.saezelda.modele to javafx.fxml;

    exports sae.saezelda.vue;
    opens sae.saezelda.vue to javafx.fxml;
    exports sae.saezelda.controleur.observableListe;
    opens sae.saezelda.controleur.observableListe to javafx.fxml;






    exports sae.saezelda.modele.arme;
    opens sae.saezelda.modele.arme to javafx.fxml;
    exports sae.saezelda.modele.potion;
    opens sae.saezelda.modele.potion to javafx.fxml;
    exports sae.saezelda.modele.obstacle;
    opens sae.saezelda.modele.obstacle to javafx.fxml;


    exports sae.saezelda.modele.objet;
    opens sae.saezelda.modele.objet to javafx.fxml;



    exports sae.saezelda.modele.personnage;
    opens sae.saezelda.modele.personnage to javafx.fxml;



    exports sae.saezelda.modele.degat;
    opens sae.saezelda.modele.degat to javafx.fxml;
    exports sae.saezelda.modele.degat.degatBoom;
    opens sae.saezelda.modele.degat.degatBoom to javafx.fxml;
    exports sae.saezelda.modele.degat.degatCouteau;
    opens sae.saezelda.modele.degat.degatCouteau to javafx.fxml;
    exports sae.saezelda.modele.degat.degatProjectile;
    opens sae.saezelda.modele.degat.degatProjectile to javafx.fxml;
}

