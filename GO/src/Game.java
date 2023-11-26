import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // System.out.println("Veuillez saisir la taille du plateau :");
        int taille; //= sc.nextInt();
        String cmd = "";
        Goban g = null;// = new Goban(taille);
        int cpt = 0;
        String input[];
        String commandes;
        String paramètres;
        String azdqsd;

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
            cmd = sc.nextLine().trim().toLowerCase();
            input = cmd.split(" ");
            commandes = input[0];
            //paramètres.matches("\\d+")

            // boardsize 19
            if (commandes.equals("boardsize")) {
                if (input.length >= 2) {
                    paramètres = input[1];
                    taille = Integer.parseInt(paramètres);
                    g = new Goban(taille);
                    
                } else {
                    System.out.println("? " + cmd + " Paramètre manquant pour boardsize");
                }
            } 
            else if (commandes.equals("showboard")) {
            	if(g != null)
            		 g.Jouer(1, 1, 'b');
                     g.Jouer(0, 0, 'b');
                     g.Jouer(2, 2, 'b');
                     g.Jouer(3, 3, 'b');
                     g.Jouer(4, 4, 'b');
            		System.out.println(g.toString());	
            } 
            else if (cmd.equals("quit")) {
                break;
            } 
            else {
                System.out.println("? " + cmd + " Unknown command");
            }
            
        }
        sc.close();
    }
}
