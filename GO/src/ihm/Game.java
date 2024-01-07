package ihm;
import java.util.Scanner;
import go.Goban;

public class Game {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int taille = 19;
        String cmd = "";
        Goban g = new Goban(taille);
        int cpt = 0;
        int cptTour = 0;
        String input[];
        String commandes;
        String paramètres;
        String paramètre2;

        // g.Jouer(1, 1, 'b');
        // g.Jouer(0, 0, 'b');
        // g.Jouer(2, 2, 'b');
        // g.Jouer(3, 3, 'b');
        // g.Jouer(4, 4, 'b');
        //
        //System.out.println(g.goban);
        //cmd = sc.next();
        while (true) {
            System.out.print(++cpt + " ");
            if(g.prêtAJouer()) {
            	if(g.getEtatNoir()) {
            		if(cptTour % 2 == 0) {
                		if(!g.gobanPlein()) {
                			g.JouerRandom('X','O');
                			System.out.println(g.toString());
                        	cptTour++;
                		}
                		else
                			break;
                    }
            	}
            	if(g.getEtatBlanc()) {
            		if (cptTour % 2 != 0) {
                		if(!g.gobanPlein()) {
                			g.JouerRandom('O', 'X');
                			System.out.println(g.toString());
                        	cptTour++;
                		}
                		else 
                			break;
                	}
            	}
            }
            if(!g.prêtAJouer() || g.joueurConsolePresent()) {
	            if (g.gobanPlein())
	                break;
	            cmd = sc.nextLine().trim().toLowerCase();
	            input = cmd.split(" ");
	            commandes = input[0];
	
	            
	            // boardsize 19
	            if (commandes.equals("boardsize")) {
	                if (input.length == 2) {
	                    paramètres = input[1];
	                    taille = Integer.parseInt(paramètres);
	                    g = new Goban(taille);
	                    
	                } else {
	                    System.out.println("? " + cmd + " Paramètre manquant pour boardsize");
	                }
	            } 
	            else if (commandes.equals("player")) {
	            	if(input.length == 3) {
	            		paramètres = input[1];
	                	paramètre2 = input[2];
	                	if(paramètres.equals("black")) {
	                		if(paramètre2.equals("console"))
	                			g.Joueur(paramètres, false);
	                		else
	                			g.Joueur(paramètres, true);
	                	}
	                	else if(paramètres.equals("white")) {
	                		if(paramètre2.equals("console"))
	                			g.Joueur(paramètres, false);
	                		else
	                			g.Joueur(paramètres, true);
	                	}
	                	else {
	                		System.out.println("=? unknow command");
	                	}
	            	}
	            	
	            }
	            else if (commandes.equals("showboard")) {
	            	if(g.prêtAJouer()) {
	            		System.out.println(cpt + "=\n" + g.toString());
	            	}
	                else
	                    System.out.println(cpt +"=? missing player");
	            } 
	            
	            else if (commandes.equals("play")) {
	                if(g.prêtAJouer()){ 
	                    if(input.length == 3) {
	                    	if(g.prêtAJouer()) {
		                        paramètres = input[1];
		                        paramètre2 = input[2];
		                        if(paramètres.equals("black") && !g.getEtatNoir()) {
		                        		if(cptTour % 2 == 0){
		                            		cptTour++;
		                            		if((int) paramètre2.charAt(0) - 97 >= 8) { //permet de voir si la lettre interdite est atteinte
		                                		if(paramètre2.length() >= 3) {
		                                			g.Jouer((int) paramètre2.charAt(0) - 98,Integer.parseInt(paramètre2.substring(1,3)) - 1,'X'); //Permet de convertir la lettre et la case en int
		                                			g.Capture((int) paramètre2.charAt(0) - 98,Integer.parseInt(paramètre2.substring(1,3)) - 1,'O', 1,'X');
		                                			g.Decision('X');
		                                		}
		                                		else {
		                                			g.Jouer((int) paramètre2.charAt(0) - 98, (int) paramètre2.charAt(1) - 49,'X'); //Si c'est à partir de I
		                                			g.Capture((int) paramètre2.charAt(0) - 98, (int) paramètre2.charAt(1) - 49,'O', 1,'X');
		                                			g.Decision('X');
		                                		}	
		                                	}
		                                	else {
		                                		if(paramètre2.length() >= 3) {
		                                			g.Jouer((int) paramètre2.charAt(0) - 97,Integer.parseInt(paramètre2.substring(1,3))-1,'X');
		                                			g.Capture((int) paramètre2.charAt(0) - 97,Integer.parseInt(paramètre2.substring(1,3)) - 1,'O', 1,'X');
		                                			g.Decision('X');
		                                		}
		                                		else {
		                                			g.Jouer((int) paramètre2.charAt(0) - 97, (int) paramètre2.charAt(1) - 49,'X'); //Si c'est avant I
		                                			g.Capture((int) paramètre2.charAt(0) - 97, (int) paramètre2.charAt(1) - 49,'O', 1,'X');
		                                			g.Decision('X');
		                                		}                       		
		                                	}
		                            	}
		                        		if(g.getEtatBlanc()) {
		                        			
		                        		}
		                        		else 
		                        			System.out.println("= ? not your turn");
		                        }        
		                        else if (paramètres.equals("white") && !g.getEtatBlanc()) {
		                        	if(cptTour % 2 != 0) {
		                        		cptTour++;
		                        		if((int) paramètre2.charAt(0) - 97 >= 8) { //permet de voir si la lettre interdite est atteinte
		                            		if(paramètre2.length() >= 3) {
		                            			g.Jouer((int) paramètre2.charAt(0) - 98,Integer.parseInt(paramètre2.substring(1,3)) - 1,'O'); //Permet de convertir la lettre et la case en int
		                            			g.Capture((int) paramètre2.charAt(0) - 98,Integer.parseInt(paramètre2.substring(1,3)) - 1,'X', 1,'O');
		                            			g.Decision('O');
		                            		}
		                            		else {
		                            			g.Jouer((int) paramètre2.charAt(0) - 98, (int) paramètre2.charAt(1) - 49,'O'); 
		                            			g.Capture((int) paramètre2.charAt(0) - 98, (int) paramètre2.charAt(1) - 49,'X', 1,'O');
		                            			g.Decision('O');
		                            		}	
		                            	}
		                            	else { // lorsque la lettre interdite n'est pas atteinte
		                            		if(paramètre2.length() >= 3) {
		                            			g.Jouer((int) paramètre2.charAt(0) - 97,Integer.parseInt(paramètre2.substring(1,3)) - 1,'O');
		                            			g.Capture((int) paramètre2.charAt(0) - 97,Integer.parseInt(paramètre2.substring(1,3)) - 1,'X', 1,'O');
		                            			g.Decision('O');
		                            		}
		                            		else {
		                            			g.Jouer((int) paramètre2.charAt(0) - 97, (int) paramètre2.charAt(1) - 49,'O'); //Si c'est avant I
		                            			g.Capture((int) paramètre2.charAt(0) - 97, (int) paramètre2.charAt(1) - 49,'X', 1,'O');
		                            			g.Decision('O');
		                            		}                       		
		                            	}                       
		                        	}	 
		                        	else 
	                        			System.out.println("= ? not your turn");
		                        }
	                    	}
	                        else
	                            System.out.println(cpt + "=? missing player");
	                        
	                        System.out.println(g.toString());
	                    }
	                    
	                    else {
	                        System.out.println(cpt + "=?");
	                    }
	                } 
	            }
	            else if(commandes.equals("clearboard")) {
	            	if (g != null)
	            		g.clearBoard();
	            }
	            else if (cmd.equals("passer") && g.prêtAJouer()) {
	            	cptTour++;
	            }
	            else if (cmd.equals("quit")) {
	                break;
	            } 
	            
	            else {
	                System.out.println(cpt + "=? " + cmd + " Unknown command");
	            }
	        }
        }
        sc.close();
    }
}
