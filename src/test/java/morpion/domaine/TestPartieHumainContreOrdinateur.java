package morpion.domaine;

import org.junit.Assert;
import org.junit.Test;

import morpion.servlet.Constantes;

public class TestPartieHumainContreOrdinateur {

	@Test
	public void testPartieSimple() throws Exception {
		for (int i = 0; i < 100; i++) {
			PartieHumainContreOrdinateur partie = new PartieHumainContreOrdinateur();
			partie.activeTrace(true);
			Case caseVideAleatoire = partie.getCaseVideAleatoire();
			partie.humainJouePiece(caseVideAleatoire.getLigne(), caseVideAleatoire.getColonne());
			caseVideAleatoire = partie.getCaseVideAleatoire();
			partie.humainJouePiece(caseVideAleatoire.getLigne(), caseVideAleatoire.getColonne());
			caseVideAleatoire = partie.getCaseVideAleatoire();
			partie.humainJouePiece(caseVideAleatoire.getLigne(), caseVideAleatoire.getColonne());
			caseVideAleatoire = partie.getCaseVideAleatoire();
			partie.humainJouePiece(caseVideAleatoire.getLigne(), caseVideAleatoire.getColonne());
			caseVideAleatoire = partie.getCaseVideAleatoire();
			String status = partie.humainJouePiece(caseVideAleatoire.getLigne(), caseVideAleatoire.getColonne());
			Assert.assertNotEquals(Constantes.STATUT_EN_COURS, status);
		}
	}

}
