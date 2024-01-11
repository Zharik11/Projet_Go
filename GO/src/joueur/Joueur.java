package joueur;
import go.IJoueur;

public class Joueur implements IJoueur {
	private int pionsCapturés;
	private boolean ia = false;
	
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
