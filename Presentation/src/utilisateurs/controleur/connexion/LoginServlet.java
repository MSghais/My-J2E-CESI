package utilisateurs.controleur.connexion;

import java.io.IOException;
import java.security.Principal;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import utilisateurs.connexion.MetierInterfaceConnexion;
import utilisateurs.entite.User;




@WebServlet("/Connexion")
public class LoginServlet extends HttpServlet {
	public static final String ATTRIBUT_USER         = "utilisateur";
	public static final String ATTRIBUT_USER_LOGIN         = "userLogin";
    public static final String ATTRIBUT_ERREUR_MSG   = "msgErreur";
    public static final String URL_VUE_CONNEXION     = "WEB-INF/connexion/login.jsp";
    public static final String URL_VUE_CONNEXION_OK  =  "WEB-INF/connexion/accesRestreint.jsp";
    
    @EJB
	private MetierInterfaceConnexion metier;
    
	private String erreurMsg;
	
	/*
	 * public LoginServlet() { metier = new Metier(); }
	 */
	
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher(URL_VUE_CONNEXION).forward(request, response); 
	}
	@Override
    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
    	System.out.println("LoginServlet - doPost");
		
	     String login = request.getParameter("login");
	     String motDePasse = request.getParameter("motdepasse");
	        
	     User utilisateur = connecterUtilisateur(login,motDePasse);
	     System.out.println("LoginServlet utilisateur="+utilisateur);
	     HttpSession session = request.getSession();
	       
        Principal userPrincipal = request.getUserPrincipal();
	        
	        System.out.println(userPrincipal);
	     
	     if(utilisateur!=null) {
	     	session.setAttribute(ATTRIBUT_USER_LOGIN,utilisateur.getLogin());
	     	request.setAttribute(ATTRIBUT_USER_LOGIN, utilisateur.getLogin());

	     	System.out.println(session.getAttribute(ATTRIBUT_USER_LOGIN));
	     	
	     	System.out.println(utilisateur.getLogin() );
	     		     	
	     }
	     else {
	     	request.setAttribute(ATTRIBUT_ERREUR_MSG,erreurMsg);
	     	session.setAttribute("utilisateur",null);
	     }
	     request.getRequestDispatcher(URL_VUE_CONNEXION).forward(request, response); 
	 
	//request.getAttribute(ATTRIBUT_USER);
	}
	
	 private User connecterUtilisateur(String login,String motDePasse) {
	        
	     User utilisateur = null;
         boolean isConnect = metier.connexionUtilisateur(login,motDePasse);
         if(isConnect)
	       	utilisateur = new User(login, motDePasse);
	     else erreurMsg = "L'utilisateur " + login + " - " + motDePasse + ", n'a pas de compte associé.";
	      
	     return utilisateur;
	}
}
