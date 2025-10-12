public class Pion extends Piece {
    public Pion(String couleur, Case position) {
        super(couleur, position);
    }


    public boolean deplacement(Case destination) {
        int x = destination.getX() - this.getPosition().getX();
        int y = destination.getY() - this.getPosition().getY();

        if (this.getCouleur().equals("blanc")) {
            // Avancer d'une case vers le haut car blanc, d'où la valeur 1 pour y (sans capture)
            if (x == 0 && y == 1 && destination.estVide()) {
                return true;
            }

            // Avancer de deux cases depuis la position initiale (avec Y=1 pour la 2e ligne car blanc) sans capture
            if (x == 0 && y == 2 && this.getPosition().getY() == 1 &&
                destination.estVide()) {
                return true;
            }

            // Capture diagonale si case destination non vide, et différente de la couleur du pion
            if (Math.abs(x) == 1 && y == 1 && !destination.estVide() &&
                !destination.getPiece().getCouleur().equals(this.getCouleur())) {
                return true;
            } /*Valeur absolue utilisée pour x car depuis x2, on peut aller en x1 ou x3 en diagonale
            soit 2-1=1 et 2-3=(-1). Or les deux coups sont corrects donc on prend en compte les deux possibilités*/

        } else if (this.getCouleur().equals("noir")) {
            // Avancer d'une case vers le bas car noir, d'où la valeur -1 pour y (sans capture)
            if (x == 0 && y == -1 && destination.estVide()) {
                return true;
            }

            // Avancer de deux cases depuis la position initiale (avec Y=6 pour la 7e ligne car noir) sans capture
            if (x == 0 && y == -2 && this.getPosition().getY() == 6 &&
                destination.estVide()) {
                return true;
            }

            // Capture diagonale si case destination non vide, et différente de la couleur du pion
            if ((Math.abs(x) == 1) && y == -1 && !destination.estVide() &&
                !destination.getPiece().getCouleur().equals(this.getCouleur())) {
                return true;
            } /*Valeur absolue utilisée pour x car depuis x2, on peut aller en x1 ou x3 en diagonale 
            soit 2-1=1 et 2-3=(-1). Or les deux coups sont corrects donc on prend en compte les deux possibilités*/
        }

        return false;
    }
}
