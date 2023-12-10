package go;

import java.util.Objects;

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
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Position position = (Position) obj;
        return colonne == position.colonne && ligne == position.ligne;
    }

    @Override
    public int hashCode() {
        return Objects.hash(colonne, ligne);
    }
}
