package morpion.domaine;

import morpion.servlet.Constantes;

public class Partie1Contre1 {

	private Plateau plateau = new Plateau();
	private String pieceActuelle = Constantes.PIECE_O;
	private boolean activeTrace = false;

	public String jouePiece(int x, int y) throws Exception {
		if (!plateau.estVide(x, y)) {
			throw new Exception("La case est déjà occupée");
		}
		String status = plateau.positionnePiece(x, y, pieceActuelle);

		if (activeTrace) {
			System.out.println("----------------------------");
			System.out.println("Positionnement de la piece " + pieceActuelle + " sur la ligne " + x + "et la colonne " + y);
			System.out.println(plateau);
			System.out.println("Statut: " + status);
			System.out.println("----------------------------\n");
		}

		changePieceActuelle();
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

}