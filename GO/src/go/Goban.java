package go;
public class Goban {
	
    private int taille;
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
    
    public void clearBoard() {
    	for(int i = 0;i<taille; i++) 
    		for(int j = 0; j<taille; j++) 
    			this.goban[i][j] = '.';   	
    }
    
    public void Jouer(int ligne, int colonne, char couleur) {
    	if(ligne < taille && colonne < taille) {
    		if(this.goban[colonne][ligne] == '.') {
        		this.goban[colonne][ligne] = couleur;
        	}
    	}
    }
    
    private char[] setColonne(){
        for(int i = 0; i < taille; i++)
        	if (i >= 8) {
        	    this.colonne[i] = (char)('A' + i + 1); // Lorsque la lettreInterdite est atteinte
        	} else {
        	    this.colonne[i] = (char)('A' + i);
        	}
        return this.colonne;
    }
    
    public String toString(){
        StringBuilder sb = new StringBuilder();
        int a = 0;
        sb.append("   ");
        for (char column : colonne) { // Lettre de colonne
            sb.append(column + " ");
            //System.out.println("---- " + column + " ----");
        }
        sb.append("\n");       
        for (int i = taille - 1; i >= 0 ; i--) {        	
            sb.append(String.format("%2d ", i+1)); // Numéro de ligne
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
