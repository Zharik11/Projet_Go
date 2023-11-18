public class Goban {
    private static int taille;
    private char[][] goban;
    private char[] colonne;
    public Goban(int taille){
        if (taille < 1 || taille > 26) throw new IllegalArgumentException("Taille invalide");
        this.taille = taille;
        this.goban = new char[taille][taille];
        this.colonne = new char[taille];
        setColonne();
    }
    public char[] setColonne(){
        for(int i = 0; i < taille; i++){
            this.colonne[i] = (char)('A' + i);
        }
        return this.colonne;
    }
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("     ");
        for (char column : colonne) {
            sb.append(column + " ");
        }
        sb.append("\n");
        for (int i = 0; i < taille; i++) {
            sb.append(String.format("%2d ", i + 1) + "\n"); // Numéro de ligne
        }
        return sb.toString();
    }
}
