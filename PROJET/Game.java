import java.util.Scanner;

public class Game {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Veuillez saisir la taille du plateau :");
        int taille = sc.nextInt();
        Goban g = new Goban(taille);
        g.Jouer(1, 1, 'b');
        g.Jouer(0, 0, 'b');
        g.Jouer(2, 2, 'b');
        g.Jouer(3, 3, 'b');
        g.Jouer(4, 4, 'b');
        System.out.println(g.toString());
        //System.out.println(g.goban);
        sc.close();
    }
}
