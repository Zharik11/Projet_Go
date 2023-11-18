import java.util.Scanner;

public class Game {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Veuillez saisir la taille du plateau :");
        int taille = sc.nextInt();
        Goban g = new Goban(taille);
        System.out.println(g.toString());
        sc.close();
    }
}
