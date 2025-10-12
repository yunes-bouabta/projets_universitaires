public class Dame extends Piece {
    public Dame(String couleur, Case position) {
        super(couleur, position);
    }

    public boolean deplacement(Case destination) {
        int x = Math.abs(destination.getX() - this.getPosition().getX());
        int y = Math.abs(destination.getY() - this.getPosition().getY());
        return x == y || x == 0 || y == 0;
        /*Dame est un mélange de fou et tour donc 
        combinaison des différents return préalablement établis
        */
    }
}
