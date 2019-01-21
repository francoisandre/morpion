package morpion.domaine;

public interface Partie {

	String getValeurCase(int x, int y);

	boolean estEnCours();

	String getMessageFinDePartie();

}
