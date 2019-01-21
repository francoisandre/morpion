package morpion.domaine;

import org.junit.Assert;
import org.junit.Test;

import morpion.servlet.Constantes;

public class TestPartie1Contre1 {

	@Test(expected = Exception.class)
	public void testPartieImpossible() throws Exception {
		Partie1Contre1 partie = new Partie1Contre1();
		partie.activeTrace(true);
		partie.jouePiece(0, 0);
		partie.jouePiece(0, 0);
	}

	@Test
	public void testPartieSimple() throws Exception {
		Partie1Contre1 partie = new Partie1Contre1();

		partie.activeTrace(true);

		partie.jouePiece(0, 0);
		partie.jouePiece(1, 2);
		partie.jouePiece(0, 1);
		partie.jouePiece(1, 1);
		String statut = partie.jouePiece(0, 2);
		Assert.assertEquals(statut, Constantes.STATUT_O_GAGNANT);

	}

	@Test
	public void testPartieNulle() throws Exception {
		Partie1Contre1 partie = new Partie1Contre1();

		partie.activeTrace(true);

		partie.jouePiece(0, 2);
		partie.jouePiece(0, 1);
		partie.jouePiece(1, 0);
		partie.jouePiece(0, 0);
		partie.jouePiece(1, 1);
		partie.jouePiece(1, 2);
		partie.jouePiece(2, 1);
		partie.jouePiece(2, 0);
		String statut = partie.jouePiece(2, 2);
		Assert.assertEquals(statut, Constantes.STATUT_MATCH_NULL);

	}

}
