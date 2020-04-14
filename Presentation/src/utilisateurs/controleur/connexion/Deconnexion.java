package utilisateurs.controleur.connexion;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
@WebServlet("/deconnexion")
public class Deconnexion extends HttpServlet {
   // public static final String URL_REDIRECTION = "http://www.equipe.fr";
	//public static final String URL_REDIRECTION = "http://www.equipe.fr";
	
    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        /* Récupération et destruction de la session en cours */
        HttpSession session = request.getSession();
        session.invalidate();
        response.sendRedirect( request.getContextPath() + "/Connexion");
    }
}
