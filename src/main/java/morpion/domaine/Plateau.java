package morpion.domaine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import morpion.servlet.Constantes;

public class Plateau {

	public static Map < String, String > statusGagnant = new HashMap <>();

	static {
		statusGagnant.put(Constantes.PIECE_X, Constantes.STATUT_X_GAGNANT);
		statusGagnant.put(Constantes.PIECE_O, Constantes.STATUT_O_GAGNANT);
	}

	private Map < String, String > grille = new HashMap <>();
	private String status = Constantes.STATUT_EN_COURS;

	public boolean estVide(int x, int y) {
		String aux = grille.get(getKey(x, y));
		if (aux == null) {
			return true;
		} else {
			return false;
		}

	}

	public void remiseAZero() {
		grille.clear();
	}

	private String getKey(int x, int y) {
		return "" + x + "" + y;
	}

	public String positionnePiece(int x, int y, String valeur) {
		grille.put(getKey(x, y), valeur);
		status = calculeStatus(valeur);
		return status;
	}

	private String calculeStatus(String valeur) {
		if (estGagnantHorizontal(valeur)) {
			return statusGagnant.get(valeur);
		} else if (estGagnantVertical(valeur)) {
			return statusGagnant.get(valeur);
		} else if (estGagnantDiagonal(valeur)) {
			return statusGagnant.get(valeur);
		} else if (grille.size() == 9) {
			return Constantes.STATUT_MATCH_NULL;
		}

		else {
			return Constantes.STATUT_EN_COURS;
		}
	}

	private boolean estGagnantDiagonal(String valeur) {
		String aux1 = grille.get(getKey(0, 0));
		String aux2 = grille.get(getKey(1, 1));
		String aux3 = grille.get(getKey(2, 2));

		if ((valeur.equals(aux1)) && (valeur.equals(aux2)) && (valeur.equals(aux3))) {
			return true;
		}

		aux1 = grille.get(getKey(2, 0));
		aux2 = grille.get(getKey(1, 1));
		aux3 = grille.get(getKey(0, 2));

		if ((valeur.equals(aux1)) && (valeur.equals(aux2)) && (valeur.equals(aux3))) {
			return true;
		}

		return false;
	}

	private boolean estGagnantHorizontal(String valeur) {
		for (int i = 0; i < 3; i++) {
			String aux1 = grille.get(getKey(0, i));
			String aux2 = grille.get(getKey(1, i));
			String aux3 = grille.get(getKey(2, i));

			if ((valeur.equals(aux1)) && (valeur.equals(aux2)) && (valeur.equals(aux3))) {
				return true;
			}
		}

		return false;
	}

	private boolean estGagnantVertical(String valeur) {
		for (int i = 0; i < 3; i++) {
			String aux1 = grille.get(getKey(i, 0));
			String aux2 = grille.get(getKey(i, 1));
			String aux3 = grille.get(getKey(i, 2));

			if ((valeur.equals(aux1)) && (valeur.equals(aux2)) && (valeur.equals(aux3))) {
				return true;
			}
		}

		return false;
	}

	private List < Case > getCasesVides() {
		List < Case > resultat = new ArrayList <>();
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				String aux = getKey(i, j);
				if (grille.get(aux) == null) {
					resultat.add(new Case(i, j));
				}
			}
		}

		return resultat;
	}

	public Case getCaseVideAleatoire() {
		Random rand = new Random();
		List < Case > casesVides = getCasesVides();
		return casesVides.get(rand.nextInt(casesVides.size()));
	}

	@Override
	public String toString() {
		String resultat = "";
		for (int i = 0; i < 3; i++) {

			for (int j = 0; j < 3; j++) {
				String aux = grille.get(getKey(i, j));
				if (aux == null) {
					resultat = resultat + "-";
				} else {
					resultat = resultat + aux;
				}
			}
			if (i < 2) {
				resultat = resultat + '\n';
			}
		}
		return resultat;
	}

	public String getValeur(int x, int y) {
		return grille.get(getKey(x, y));
	}

	public boolean estEnCours() {
		return status.equals(Constantes.STATUT_EN_COURS);
	}

	public String getStatus() {
		return status;
	}

}
