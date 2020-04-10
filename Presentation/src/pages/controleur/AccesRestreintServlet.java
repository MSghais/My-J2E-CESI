package pages.controleur;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import utilisateurs.entite.User;

/**
 * Servlet implementation class AccesRestreintServlet
 */
@WebServlet("/accesrestreint")
public class AccesRestreintServlet extends HttpServlet {
	public static final String ATTRIBUT_USER 	= "utilisateur";
	public static final String URL_VUE_CONNEXION     = "/login.jsp";
	public static final String ATTRIBUT_ERREUR_MSG   = "msgErreur";
	public static final String ACCES_RESTREINT  =  "/WEB-INF/accesRestreint.jsp";
	
    public AccesRestreintServlet() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

        /*
         * Si l'objet utilisateur n'existe pas dans la session en cours, alors
         * l'utilisateur n'est pas connecté.
         */
        if ( session.getAttribute( ATTRIBUT_USER ) == null ) {
        	System.out.println("L'utilisateur n'est pas connecté");
            //response.sendRedirect( request.getContextPath() + URL_VUE_CONNEXION);
        	response.sendRedirect( "www.equipe.fr");
        } else {
            /* Affichage de la page restreinte */
        	User user =  (User) session.getAttribute("user");
        	System.out.println("L'utilisateur est connecté utilisateur=" + user);
        	request.getRequestDispatcher(ACCES_RESTREINT).forward(request, response); 
        	
        }
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
