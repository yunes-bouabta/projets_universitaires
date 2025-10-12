public abstract class Piece {
    protected String couleur; 
    protected Case position;

    public Piece(String couleur, Case position) {
        this.couleur = couleur;
        this.position = position;
    }

    public String getCouleur() {
        return this.couleur;
    }

    public Case getPosition() {
        return this.position;
    }

    public void setPosition(Case position) {
        this.position = position;
    }

    public abstract boolean deplacement(Case destination);
}
