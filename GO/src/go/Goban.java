package go;
import java.util.HashSet;
import java.util.Random;
import joueur.*;

public class Goban {
	private static final int MAX = 50;
	private IJoueur blanc, noir;
    private int taille;
    private char[][] goban;
    private char[] colonne;
    private HashSet<Position> tabVerif;
    private int cptCap;
    private Position tmp;
    
    public Goban(int taille){
        if (taille < 5 || taille > 26) throw new IllegalArgumentException("Taille invalide");
        
        this.taille = taille;
        this.goban = new char[taille][taille];
        this.colonne = new char[taille];
        this.tabVerif = new HashSet<Position>();
        setColonne();
        
        for(int i = 0; i < taille; i++) {
    		for(int j = 0; j<taille;j++) {
    			this.goban[i][j] = '.';
    		}
    	}
    }
    private boolean caseOccupée(int ligne, int colonne) {
    	return goban[colonne][ligne] != '.';
    }
    public boolean joueurConsolePresent() {
    	return !getEtatNoir() || !getEtatBlanc();
    }
    public boolean getEtatNoir() {
    	return noir.getEtat();
    }
    public boolean getEtatBlanc() {
    	return blanc.getEtat();
    }
  
    public boolean prêtAJouer() {
    	return noir != null && blanc != null;
    }
    
    public void Joueur(String joueur, boolean etat) {
    	if(joueur.equals("black")) {
    		this.noir = new Noir(etat);
    	}
    	else {
    		this.blanc = new Blanc(etat);
    	}
    }
    
    public boolean gobanPlein() {
    	for(int i = 0; i<taille; i++) {
    		for(int j = 0; j<taille; j++) {
    			if(goban[i][j] == '.')
    				return false;
    		}
    	}
    	return true;
    }
    
    public void clearBoard() {
    	for(int i = 0;i<taille; i++)
    		for(int j = 0; j<taille; j++) 
    			this.goban[i][j] = '.';   	
    }
    
    public void JouerRandom(char couleur, char couleurO) {
    	Random r = new Random();
    	int ligne = r.nextInt(taille);
    	int colonne = r.nextInt(taille);
    	while(true) {
    		if(goban[colonne][ligne] != '.') {
    			ligne = r.nextInt(taille);
    	    	colonne = r.nextInt(taille);
    		}
    		else {
    	    	Jouer(ligne, colonne, couleur);
    	    	Capture(ligne, colonne, couleurO, 1, couleur);
    			Decision(couleur);
    	    	break;
    		}
    	}
    }
    
    public void Jouer(int ligne, int colonne, char couleur) {
    	if(ligne < taille && colonne < taille) {
    		if(!caseOccupée(ligne,colonne)) {
        		this.goban[colonne][ligne] = couleur;        		
        	}
    		tabVerif.clear();
    		cptCap = 0;
    	}
    }
    public void Capture(int ligne, int colonne, char couleur, int depth, char couleurO) {
    	if (depth >= MAX) {
            return; // Ajoutez cette ligne pour sortir de la récursion après un certain nombre d'itérations
        }
        if (colonne + 1 < taille) {
            if (goban[colonne+1][ligne] == couleur) { //en haut
            	if(goban[colonne][ligne] == couleurO) {
            		tabVerif.clear();
            		cptCap = 0;
            	}
                if (!tabVerif.contains(new Position(colonne+1, ligne))) {
                	tmp = new Position(colonne+1, ligne);
                	tabVerif.add(tmp);
                    Capture(ligne, colonne + 1, couleur, depth+1, couleurO);
                    cptCap += verifCapture(ligne, colonne + 1, couleur);
                }
            }
            else
            	cptCap = 0;
        }
        if (colonne - 1 >= 0) {
            if (goban[colonne - 1][ligne] == couleur) { //en bas
            	if(goban[colonne][ligne] == couleurO) {
            		tabVerif.clear();
            		cptCap = 0;
            	}
                if (!tabVerif.contains(new Position(colonne-1, ligne))) {
                	tmp = new Position(colonne-1, ligne);
                	tabVerif.add(tmp);
                    Capture(ligne, colonne - 1, couleur, depth+1, couleurO);
                    cptCap += verifCapture(ligne, colonne - 1, couleur);
                }
            }
        }
        if (ligne - 1 >= 0) {
            if (goban[colonne][ligne - 1] == couleur) { //a gauche
            	if(goban[colonne][ligne] == couleurO) {
            		tabVerif.clear();
            		cptCap = 0;
            	}
                if (!tabVerif.contains(new Position(colonne, 1-ligne))) {
                	tmp = new Position(colonne, ligne-1);
                	tabVerif.add(tmp);
                    Capture(ligne - 1, colonne, couleur, depth+1, couleurO);
                    cptCap += verifCapture(ligne - 1, colonne, couleur);
                }
            }
        }
        if (ligne + 1 < taille) {
            if (goban[colonne][ligne+1] == couleur) { //a droite
            	if(goban[colonne][ligne] == couleurO) {
            		tabVerif.clear();
            		cptCap = 0;
            	}
                if (!tabVerif.contains(new Position(colonne,1+ligne))){
                	tmp = new Position(colonne, 1+ligne);
                	tabVerif.add(tmp);
                    Capture(ligne + 1, colonne, couleur, depth+1, couleurO);
                    cptCap += verifCapture(ligne + 1, colonne, couleur);     
                }
            }            
        }
    }

    private int verifCapture(int ligne, int colonne,  char couleur) {
    	int tmp = 0;
    	if(colonne +1 < taille) {
    		if(goban[colonne+1][ligne] == '.') {//en haut
        		tmp++;
        	}
    	}
    	if(colonne-1 >= 0) {
    		if(goban[colonne-1][ligne] == '.') {//en bas
        		tmp++;
        	}
    	}
    	if(ligne+1 < taille) {
    		if(goban[colonne][ligne+1] == '.') {// a droite 
        		tmp++;
        	}
    	}
    	if(ligne - 1 >= 0) {
    		if(goban[colonne][ligne-1] == '.') {//a gauche
        		tmp++;
        	}
    	}
    	return tmp;
    }
    
    public void Decision(char joueurQuiReçoit) {
    	if(cptCap == 0) {
    		if(joueurQuiReçoit == 'X')
    			noir.setPionsCapturé(tabVerif.size());
    		else
    			blanc.setPionsCapturé(tabVerif.size());
    		for(Position position : tabVerif) {
    			Eradication(position.getColonne(), position.getLigne());
    		}
    	}
    }
    
    private void Eradication(int colonne, int ligne) {
       if (colonne >= 0 && colonne < taille && ligne >= 0 && ligne < taille) 
            goban[colonne][ligne] = '.';
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
        			sb.append("     WHITE (O) has captured " + this.blanc.getPionsCapturé() + " stones");
        		else if(i == 0)
        			sb.append("     BLACK (X) has captured " + this.noir.getPionsCapturé() + " stones");
        		sb.append("\n");
        	}
        sb.append("   ");
        for (char column : colonne) {
            sb.append(column + " ");
        }
        return sb.toString();
    }
}
