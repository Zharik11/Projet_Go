package go;
import java.util.HashSet;

public class Goban {
	private static final int MAX = 50;
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
    		tabVerif.clear();
    		cptCap = 0;
    	}
    }
    public void Capture(int ligne, int colonne, char couleur, int depth, char couleurO) {
    	if (depth >= MAX) {
            return; // Ajoutez cette ligne pour sortir de la récursion après un certain nombre d'itérations
        }
        System.out.println("Test colonne : " + (1 + ligne) + "//  Test ligne : " + (1 + colonne) + " //Couleur : " + couleur);
        //goban[colonne][ligne] = '?';
        
        System.out.println(goban[colonne][ligne]);
        
        System.out.println("---- tabVerif : " + tabVerif);
        if (colonne + 1 < taille) {
        	System.out.println("----- ça peut rentrer en haut --------Colonne : " + (1 + ligne) + " Ligne : " + (1 + colonne));
            if (goban[colonne+1][ligne] == couleur) { //en haut
            	if(goban[colonne][ligne] == couleurO) {
            		tabVerif.clear();
            		cptCap = 0;
            	}
                if (!tabVerif.contains(new Position(colonne+1, ligne))) {
                	tmp = new Position(colonne+1, ligne);
                	System.out.println("Colonne nouveau pion : " + (1+tmp.getLigne() + " et ligne nouveau pion : "+(tmp.getColonne()+1)));
                	tabVerif.add(tmp);
                    System.out.println("--- C'est rentré en haut ---Colonne : " + (1 + ligne) + " Ligne : " + (1 + colonne)+ " ---Libertés : " + cptCap);
                    Capture(ligne, colonne + 1, couleur, depth+1, couleurO);
                    cptCap += verifCapture(ligne, colonne + 1, couleur);
                    System.out.println("--- C'est sorti en haut ---Colonne : " + (1 + ligne) + " Ligne : " + (1 + colonne)+ " ---Libertés : " + cptCap);
                }
            }
            else
            	cptCap = 0;
        }
        if (colonne - 1 >= 0) {
            System.out.println("----- ça peut rentrer en bas --------Colonne : " + (1 + ligne) + " Ligne : " + (1 + colonne));
            if (goban[colonne - 1][ligne] == couleur) { //en bas
            	if(goban[colonne][ligne] == couleurO) {
            		tabVerif.clear();
            		cptCap = 0;
            	}
                if (!tabVerif.contains(new Position(colonne-1, ligne))) {
                	tmp = new Position(colonne-1, ligne);
                	System.out.println("Colonne nouveau pion : " + (1+tmp.getLigne() + " et ligne nouveau pion : "+(tmp.getColonne()+1)));
                	tabVerif.add(tmp);
                    System.out.println("--- C'est rentré en bas ---Colonne : " + (1 + ligne) + " Ligne : " + (1 + colonne)+ " ---Libertés : " + cptCap);
                    Capture(ligne, colonne - 1, couleur, depth+1, couleurO);
                    cptCap += verifCapture(ligne, colonne - 1, couleur);
                    System.out.println("--- C'est sorti en bas ---Colonne : " + (1 + ligne) + " Ligne : " + (1 + colonne)+ " ---Libertés : " + cptCap);
                }
            }
        }
        if (ligne - 1 >= 0) {
            System.out.println("----- ça peut rentrer A gauche --------Colonne : " + (1 + ligne) + " Ligne : " + (1 + colonne));
            if (goban[colonne][ligne - 1] == couleur) { //a gauche
            	if(goban[colonne][ligne] == couleurO) {
            		tabVerif.clear();
            		cptCap = 0;
            	}
                if (!tabVerif.contains(new Position(colonne, 1-ligne))) {
                	tmp = new Position(colonne, ligne-1);
                	System.out.println("Colonne nouveau pion : " + (1+tmp.getLigne() + " et ligne nouveau pion : "+(tmp.getColonne()+1)));
                	tabVerif.add(tmp);
                    System.out.println("--- C'est rentré a gauche ---Colonne : " + (1 + ligne) + " Ligne : " + (1 + colonne)+ " ---Libertés : " + cptCap);
                    Capture(ligne - 1, colonne, couleur, depth+1, couleurO);
                    cptCap += verifCapture(ligne - 1, colonne, couleur);
                    System.out.println("--- C'est sorti a gauche ---Colonne : " + (1 + ligne) + " Ligne : " + (1 + colonne)+ " ---Libertés : " + cptCap);
                }
            }
        }
        if (ligne + 1 < taille) {
        	System.out.println("----- ça peut rentrer A droite --------Colonne : " + (1 + ligne) + " Ligne : " + (1 + colonne));
            if (goban[colonne][ligne+1] == couleur) { //a droite
            	if(goban[colonne][ligne] == couleurO) {
            		tabVerif.clear();
            		cptCap = 0;
            	}
                if (!tabVerif.contains(new Position(colonne,1+ligne))){
                	tmp = new Position(colonne, 1+ligne);
                	System.out.println("Colonne nouveau pion : " + (1+tmp.getLigne() + " et ligne nouveau pion : "+(tmp.getColonne()+1)));
                	tabVerif.add(tmp);
                    System.out.println("--- C'est rentré a droite ---Colonne : " + (1 + ligne) + " Ligne : " + (1 + colonne)+ " ---Libertés : " + cptCap);
                    Capture(ligne + 1, colonne, couleur, depth+1, couleurO);
                    cptCap += verifCapture(ligne + 1, colonne, couleur);
                    System.out.println("--- C'est sorti a droite ---Colonne : " + (1 + ligne) + " Ligne : " + (1 + colonne)+ " ---Libertés : " + cptCap);
                    
                }
            }            
        }
    }

    private int verifCapture(int ligne, int colonne,  char couleur) {
    	int tmp = 0;
    	System.out.println("verifCapture notre position, colonne : "+ (1+ligne)+ " et ligne : " +(1+colonne));
    	if(colonne +1 < taille) {
    		if(goban[colonne+1][ligne] == '.') {//en haut
        		System.out.println("verifCapture en haut Colonne : " +(1+ligne)+ " //Ligne : " + (2+colonne)+ " //Couleur : " +couleur);
        		tmp++;
        	}
    	}
    	else
    		tmp++;
    	if(colonne-1 >= 0) {
    		if(goban[colonne-1][ligne] == '.') {//en bas
        		System.out.println("verifCapture en bas Colonne : " +(1+ligne)+ " //Ligne : " + (colonne)+ " //Couleur : " +couleur);
        		tmp++;
        	}
    	}
    	else
    		tmp++;
    	if(ligne+1 < taille) {
    		if(goban[colonne][ligne+1] == '.') {// a droite 
        		System.out.println("verifCapture a droite Colonne : " +(2+ligne)+ " //Ligne : " + (1+colonne)+ " //Couleur : " +couleur);
        		tmp++;
        	}
    	}
    	else
    		tmp++;
    	if(ligne - 1 >= 0) {
    		if(goban[colonne][ligne-1] == '.') {//a gauche
        		System.out.println("verifCapture a gauche Colonne : " +(ligne)+ " //Ligne : " + (1+colonne)+ " //Couleur : " +couleur);
        		tmp++;
        	}
    	}
    	else
    		tmp++;
    	return tmp;
    }
    
    public void Decision() {
    	if(cptCap == 0) {
    		for(Position position : tabVerif) {
    			System.out.println("§§§ DecisionTest §§§ Colonne : " + (position.getColonne()+1)+ " et Ligne : " +(1+position.getLigne()));
    			Eradication(position.getColonne(), position.getLigne());
    		}
    	}
    }
    
    private void Eradication(int colonne, int ligne) {
       if (colonne >= 0 && colonne < taille && ligne >= 0 && ligne < taille) 
            goban[colonne][ligne] = '!';
         //else 
            //System.out.println("Indices invalides : colonne = " + colonne + ", ligne = " + ligne);
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
