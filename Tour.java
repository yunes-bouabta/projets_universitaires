public class Tour extends Piece {
    public Tour(String couleur, Case position) {
        super(couleur, position);
    }

    public boolean deplacement(Case destination) {
        int x = Math.abs(destination.getX() - this.getPosition().getX());
        int y = Math.abs(destination.getY() - this.getPosition().getY());
        return (x == 0 || y == 0);
        /*Deplacement sur ligne droite, exemple:
        Je suis en y=5 et x=5, je souhaite aller en y=5 et x=4,
        On effectue les opérations x=5-4=1 et y=5-5=0
        Soit y==0 donc true est renvoyé car on reste sur la ligne y.
        */
    }
}
