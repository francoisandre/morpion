package morpion.domaine;

import morpion.servlet.Constantes;

public class PartieHumainContreOrdinateur implements Partie {

	private Plateau plateau = new Plateau();
	private String pieceActuelle = Constantes.PIECE_O;
	private boolean activeTrace = false;

	public String humainJouePiece(int x, int y) throws Exception {
		if (!plateau.estVide(x, y)) {
			throw new Exception("La case est déjà occupée");
		}
		String status = plateau.positionnePiece(x, y, pieceActuelle);

		if (activeTrace) {
			System.out.println("----------------------------");
			System.out.println(
				"Positionnement de la piece de l'humain  (" + pieceActuelle + ") sur la ligne " + x + "et la colonne " + y);
			System.out.println(plateau);
			System.out.println("Statut: " + status);
			System.out.println("----------------------------\n");
		}

		if (status.equals(Constantes.STATUT_EN_COURS)) {
			changePieceActuelle();
			Case caseVideAleatoire = plateau.getCaseVideAleatoire();
			status = plateau.positionnePiece(caseVideAleatoire.getLigne(), caseVideAleatoire.getColonne(), pieceActuelle);
			if (activeTrace) {
				System.out.println("----------------------------");
				System.out.println("Positionnement de la piece de l'ordinateur (" + pieceActuelle + ") sur la ligne " + x
					+ "et la colonne " + y);
				System.out.println(plateau);
				System.out.println("Statut: " + status);
				System.out.println("----------------------------\n");
			}
			changePieceActuelle();
		}
		return status;

	}

	private void changePieceActuelle() {
		if (pieceActuelle.equals(Constantes.PIECE_O)) {
			pieceActuelle = Constantes.PIECE_X;
		} else {
			pieceActuelle = Constantes.PIECE_O;
		}
	}

	public void activeTrace(boolean valeur) {
		this.activeTrace = valeur;
	}

	public Case getCaseVideAleatoire() {
		return plateau.getCaseVideAleatoire();
	}

	@Override
	public String getValeurCase(int x, int y) {
		return plateau.getValeur(x, y);
	}

	@Override
	public boolean estEnCours() {
		return plateau.estEnCours();
	}

	@Override
	public String getMessageFinDePartie() {
		if (plateau.estEnCours()) {
			return "";
		} else {
			if (plateau.getStatus().equals(Constantes.STATUT_MATCH_NULL)) {
				return "Match nul";
			} else if (plateau.getStatus().equals(Constantes.STATUT_X_GAGNANT)) {
				return "Victoire de l'ordinateur";
			} else {
				return "Vous avez gagné";
			}
		}
	}
}