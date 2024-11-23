    package sae.saezelda.modele.objet;

    import sae.saezelda.modele.Direction;
    import sae.saezelda.modele.EntiteMobile;
    import sae.saezelda.modele.Environnement;
    //import sae.saezelda.modele.degat.degatProjectile.DegatProjectile;
    //import sae.saezelda.modele.degat.degatProjectile.DegatProjectile;
    import sae.saezelda.modele.degat.StrategieDegat;
    import sae.saezelda.modele.personnage.Personnage;

    public abstract class Projectile extends EntiteMobile {
        protected int vitesse;
        private boolean toucher;
        private StrategieDegat strategieDegat;

        public Projectile(int x, int y, Direction direction, int vitesse, StrategieDegat strategieDegat) {
            super(x, y);
            this.setDirectionValue(direction);
            this.vitesse = vitesse;
            this.toucher = false;
            this.strategieDegat = strategieDegat;
        }
        @Override
        public void agir() {
            deplacer();

            if (aDepasseLimites() || getToucher()) {
                setToucher(true);
                Environnement.getInstance().retirerEntite(this);
            }
        }


        public abstract void deplacer();
        public boolean aDepasseLimites() {
            return this.getXProperties().getValue() < 0 || getXProperties().getValue() > 650 || getYProperties().getValue() < 0 || getYProperties().getValue() > 330;
        }

        public StrategieDegat getStrategieDegat() {
            return strategieDegat;
        }

        public boolean getToucher() {
            return toucher;
        }
        public void setToucher(boolean toucher) {
            this.toucher = toucher;
        }
    }