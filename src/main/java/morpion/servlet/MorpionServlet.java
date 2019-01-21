package morpion.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import morpion.domaine.Partie;
import morpion.domaine.PartieHumainContreOrdinateur;

public class MorpionServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();

		if (req.getRequestURL().toString().toLowerCase().contains(Constantes.FRAGMENT_URL_NOUVELLE_PARTIE)) {
			String typePartie = req.getParameter(Constantes.NOM_PARAMETRE_TYPE);
			if (typePartie.equals("ordinateur")) {
				session.setAttribute(Constantes.NOM_ATTRIBUT_PARTIE, new PartieHumainContreOrdinateur());
			}
		}

		Partie partie = (Partie) session.getAttribute(Constantes.NOM_ATTRIBUT_PARTIE);
		if (partie == null) {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(Constantes.URL_NOUVELLE_PARTIE);
			dispatcher.forward(req, resp);
			return;
		} else {

			if (req.getRequestURL().toString().toLowerCase().contains(Constantes.FRAGMENT_URL_COUP)) {
				if (partie instanceof PartieHumainContreOrdinateur) {
					PartieHumainContreOrdinateur aux = (PartieHumainContreOrdinateur) partie;
					Integer x = new Integer(req.getParameter(Constantes.NOM_PARAMETRE_X));
					Integer y = new Integer(req.getParameter(Constantes.NOM_PARAMETRE_Y));
					try {
						aux.humainJouePiece(x, y);
					} catch (Exception e) {
						// Cas non possible
					}
				}
			}

			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(Constantes.URL_AFFICHAGE);
			dispatcher.forward(req, resp);
			return;
		}
	}

}
