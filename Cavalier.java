public class Cavalier extends Piece {
    public Cavalier(String couleur, Case position) {
        super(couleur, position);
    }

    public boolean deplacement(Case destination) {
        int x = Math.abs(destination.getX() - position.getX());
        int y = Math.abs(destination.getY() - position.getY());
        return (x == 2 && y == 1) || (x == 1 && y == 2); 
        /* Le cavalier se deplce en L, c'est a dire soit une case en avant et deux sur les cotés
        soit deux cases en avant et une sur les cotés */
    }
}
