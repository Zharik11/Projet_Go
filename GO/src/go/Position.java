package go;

public class Position {
	private int colonne;
	private int ligne;
	
	public Position(int colonne, int ligne) {
        this.colonne = colonne;
        this.ligne = ligne;
    }
	public int getColonne() {
		return this.colonne;
	}
	public int getLigne() {
		return this.ligne;
	}
}
