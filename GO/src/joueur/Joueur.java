package joueur;
import go.Goban;

public class Joueur implements IJoueur {
	int pionsCapturés;
	boolean ia = false;
	
	public Joueur (boolean b) {
		pionsCapturés = 0;
		ia = b;
	}
	
	public void setPionsCapturé(int pionsCapturés) {
		this.pionsCapturés += pionsCapturés;
	}
	
	@Override
	public int getPionsCapturé() {
		return pionsCapturés;
	}
	public boolean getEtat() {
		return ia;
	}
}
