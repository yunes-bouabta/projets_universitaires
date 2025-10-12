public class Fou extends Piece {
    public Fou(String couleur, Case position) {
        super(couleur, position);
    }

    public boolean deplacement(Case destination) {
        int x = Math.abs(destination.getX() - this.getPosition().getX());
        int y = Math.abs(destination.getY() - this.getPosition().getY());
        return x == y; 
        /*Car mouvement sur diagonale, exemple: 
        Je suis en y=4 et x=2, je veux aller en y=5 et x=3
        On effectue les opérations y=5-4=1 et x=3-2=1
        Soit y=x donc true est retourné*/
    }
}
