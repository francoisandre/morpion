package morpion;

import java.util.HashMap;
import java.util.Map;

public class Plateau {

	private final String X = "X";
	private final String O = "O";

	private Map < String, String > grille = new HashMap <>();

	public boolean estVide(int x, int y) {
		String aux = grille.get(getKey(x, y));
	}

	public void remiseAZero() {
		grille.clear();
	}

	private String getKey(int x, int y) {
		return "" + x + "" + y;
	}

}
