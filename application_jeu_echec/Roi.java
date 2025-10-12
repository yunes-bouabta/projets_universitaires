public class Roi extends Piece {
    public Roi(String couleur, Case position) {
        super(couleur, position);
    }

    public boolean deplacement(Case destination) {
        int x = Math.abs(destination.getX() - this.getPosition().getX());
        int y = Math.abs(destination.getY() - this.getPosition().getY());
        return x <= 1 && y <= 1 && (x != 0 || y != 0);
        /*Le roi se déplace sur l'une des cases collées à celle de son départ
        Exemple: Je suis en y=5, x=5 et je veux aller en y=4, x=5
        On fait y=5-4=1 et x=5-5=0. C'est valide car les deux valeurs sont inférieures à 1
        donc on est resté collé à la case de départ et l'une d'entre elles n'est pas égale à 0
        sinon on aurait pas bougé.
        */
    }
}
