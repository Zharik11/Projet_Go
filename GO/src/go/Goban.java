package go;
public class Goban {
	
    private static int taille;
    private char[][] goban;
    private char[] colonne;
    
    public Goban(int taille){
        if (taille < 5 || taille > 26) throw new IllegalArgumentException("Taille invalide");
        this.taille = taille;
        this.goban = new char[taille][taille];
        this.colonne = new char[taille];
        setColonne();
        
        for(int i = 0; i < taille; i++) {
    		for(int j = 0; j<taille;j++) {
    			this.goban[i][j] = '.';
    		}
    	}
    }
    
    public void Jouer(int x, int y, char couleur) {
    	this.goban[y][x] = couleur;
    }
    
    private char[] setColonne(){
        for(int i = 0; i < taille; i++){
            this.colonne[i] = (char)('A' + i);
        }
        return this.colonne;
    }
    
    public String toString(){
        StringBuilder sb = new StringBuilder();
        int a = 0;
        sb.append("   ");
        for (char column : colonne) {
            sb.append(column + " ");
        }
        sb.append("\n");       
        for (int i = taille - 1; i >= 0 ; i--) {
            sb.append(String.format("%2d ", i + 1)); // Numéro de ligne
            a = i;
        		for(int j = 0; j<taille;j++) {
        			sb.append(this.goban[a][j]);
        			sb.append(" ");
        		}
        		sb.append(a+1);
        		if(i == 1)
        			sb.append("     WHITE (O) has captured 0 stones");
        		else if(i == 0)
        			sb.append("     BLACK (X) has captured 0 stones");
        		sb.append("\n");
        	}
        sb.append("   ");
        for (char column : colonne) {
            sb.append(column + " ");
        }
        return sb.toString();
    }
}
