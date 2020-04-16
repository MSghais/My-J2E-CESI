

package pages.controleur;

import java.io.IOException;
import java.security.Principal;

import javax.annotation.PostConstruct;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import contenu.model.ModelContenu;
import utilisateurs.model.ModelUser;



@WebServlet("/headerServlet")
public class HeaderServlet extends HttpServlet { 
	//private MetierItf metier = MetierEtudiantPromo.getInstance(); 
	public static final String ATTRIBUT_USER         = "utilisateur";
    public static final String ATTRIBUT_ERREUR_MSG   = "msgErreur";
    public static final String VUE_HEADER   = "WEB-INF/headerPhantom.jsp";
	private String erreurMsg;
	
	@PostConstruct
	public void init() {
		System.out.println("MyServlet init");
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
	
		 HttpSession sessionServlet = request.getSession();
		
		Principal userPrincipal = request.getUserPrincipal();
		
		System.out.println("User principale " + userPrincipal);
		  if(userPrincipal!=null) {
		     	sessionServlet.setAttribute(ATTRIBUT_USER,userPrincipal);
		     }
		     else {
		     	request.setAttribute(ATTRIBUT_ERREUR_MSG,erreurMsg);
		     	sessionServlet.setAttribute("utilisateur",null);
		     }
		
		   HttpSession session = request.getSession();
		   
			
			request.getRequestDispatcher(VUE_HEADER).include(request, response); 
			
			doPost(request,response);
			
	       /*
		     if(utilisateur!=null) {
		     	session.setAttribute(ATTRIBUT_USER,utilisateur);
		     }
		     else {
		     	request.setAttribute(ATTRIBUT_ERREUR_MSG,erreurMsg);
		     	session.setAttribute("utilisateur",null);
		     }
		     request.getRequestDispatcher(URL_VUE_CONNEXION).forward(request, response); */
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int nb = 0;
		System.out.println("MyServlet Accueil est la"+ nb);
		nb++;
		//String urlVue = "WEB-INF/JSP/accueil.jsp";
		/*
		 * String urlVue = "WEB-INF/pages/indexPhantom.jsp";
		 * request.getRequestDispatcher(urlVue).forward(request, response);
		 */

		
		
		ModelUser modelUser = new ModelUser(); 
		
		ModelContenu modelContenu = new ModelContenu(); 
		

		 
		
	}
}

