public class Case {
    private int x;
    private int y; 
    private Piece piece;

    public Case(int x, int y) {
        this.x = x;
        this.y = y;
        this.piece = null;
    }

    public boolean estVide() {
        return this.piece == null;
    }

    public Piece getPiece() {
        return this.piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public int getX() { 
        return this.x; 
    }

    public int getY() { 
        return this.y; 
    }
}
