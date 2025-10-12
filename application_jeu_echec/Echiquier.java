import java.util.Arrays;

public class Echiquier {
    private Case[][] cases;

    public Echiquier() {
        cases = new Case[8][8];
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                cases[x][y] = new Case(x, y);
            }
        }
        initialiserPieces();
    }

    public void afficher() {
    System.out.println("    A    B    C    D    E    F    G    H");
    System.out.println("  +----+----+----+----+----+----+----+----+");

    for (int y = 7; y >= 0; y--) {
        System.out.print((y + 1) + " |");
        for (int x = 0; x < 8; x++) {
            Piece piece = cases[x][y].getPiece();

            if (piece == null) {
                System.out.print("    |");
            } else {
                String symbole = " ";
                String nom = piece.getClass().getSimpleName();
                String couleur = piece.getCouleur();

                if (nom.equals("Roi") && couleur.equals("noir")) {symbole = "Rn";}
                else if (nom.equals("Roi")) {symbole = "Rb";}
                else if (nom.equals("Dame") && couleur.equals("noir")) {symbole = "Dn";}
                else if (nom.equals("Dame")) {symbole = "Db";}
                else if (nom.equals("Tour") && couleur.equals("noir")) {symbole = "Tn";}
                else if (nom.equals("Tour")) {symbole = "Tb";}
                else if (nom.equals("Fou") && couleur.equals("noir")) {symbole = "Fn";}
                else if (nom.equals("Fou")) {symbole = "Fb";}
                else if (nom.equals("Cavalier") && couleur.equals("noir")) {symbole = "Cn";}
                else if (nom.equals("Cavalier")) {symbole = "Cb";}
                else if (nom.equals("Pion") && couleur.equals("noir")) {symbole = "Pn";}
                else if (nom.equals("Pion")) {symbole = "Pb";}

                System.out.print(" " + symbole + " |");
            }
        }
        System.out.println(" " + (y + 1));
        System.out.println("  +----+----+----+----+----+----+----+----+");
    }

    System.out.println("    A    B    C    D    E    F    G    H");
}

    public Case getCase(int x, int y) {
        if (x >= 0 && x < 8 && y >= 0 && y < 8) {
            return cases[x][y];
        }
        return null;
    }

    public void initialiserPieces() {
    cases[0][0].setPiece(new Tour("blanc", cases[0][0]));
    cases[1][0].setPiece(new Cavalier("blanc", cases[1][0]));
    cases[2][0].setPiece(new Fou("blanc", cases[2][0]));
    cases[3][0].setPiece(new Dame("blanc", cases[3][0]));
    cases[4][0].setPiece(new Roi("blanc", cases[4][0]));
    cases[5][0].setPiece(new Fou("blanc", cases[5][0]));
    cases[6][0].setPiece(new Cavalier("blanc", cases[6][0]));
    cases[7][0].setPiece(new Tour("blanc", cases[7][0]));
    for (int x = 0; x < 8; x++) {
        cases[x][1].setPiece(new Pion("blanc", cases[x][1]));
    }

    cases[0][7].setPiece(new Tour("noir", cases[0][7]));
    cases[1][7].setPiece(new Cavalier("noir", cases[1][7]));
    cases[2][7].setPiece(new Fou("noir", cases[2][7]));
    cases[3][7].setPiece(new Dame("noir", cases[3][7]));
    cases[4][7].setPiece(new Roi("noir", cases[4][7]));
    cases[5][7].setPiece(new Fou("noir", cases[5][7]));
    cases[6][7].setPiece(new Cavalier("noir", cases[6][7]));
    cases[7][7].setPiece(new Tour("noir", cases[7][7]));
    for (int x = 0; x < 8; x++) {
        cases[x][6].setPiece(new Pion("noir", cases[x][6]));
    }
}

}
