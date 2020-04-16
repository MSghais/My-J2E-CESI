package utilisateurs.controleur.connexion;

import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import utilisateurs.connexion.MetierInterfaceConnexion;
import utilisateurs.entite.User;

@WebServlet("/Connexion")
public class LoginServlet extends HttpServlet {
	public static final String ATTRIBUT_USER = "utilisateur";
	public static final String ATTRIBUT_USER_SESSION = "utilisateurSession";
	public static final String ATTRIBUT_USER_LOGIN = "userLogin";
	public static final String ATTRIBUT_USER_ID = "userId";
	public static final String ATTRIBUT_USER_ROLE = "userRole";

	public static final String ATTRIBUT_ERREUR_MSG = "msgErreur";
	public static final String ATTRIBUT_ERREUR_MAP = "erreursMaps";
	public static final String ATTRIBUT_ERREURS = "erreurs";

	public static final String URL_VUE_CONNEXION = "WEB-INF/connexion/login.jsp";
	public static final String URL_VUE_CONNEXION_OK = "WEB-INF/connexion/accesRestreint.jsp";
	User userSession;

	@EJB
	private MetierInterfaceConnexion metier;

	private String erreurMsg;

	private String erreurs;

	private Map<String, String> erreursMaps = new HashMap<String, String>();;

	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("Do get Connexion Servlet");

	request.getRequestDispatcher(URL_VUE_CONNEXION).forward(request, response);

		doPost(request, response);
	}


	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("LoginServlet - doPost");
		// request.getRequestDispatcher(URL_VUE_CONNEXION).forward(request, response);

		HttpSession session = request.getSession();

		Principal userPrincipal = request.getUserPrincipal();

		//request.getRequestDispatcher(URL_VUE_CONNEXION).forward(request, response);
		
		if (request.getParameter("connecter") != null) {

			String login = request.getParameter("login");
			String password = request.getParameter("password");
			System.out.println("test connection utilisateur func servlet Boolean USER BDD");
			  boolean isExist = boolConnecterUtilisateurSimply(login, password);
			  
			  if(!isExist || isExist==false ){
			  
				  request.setAttribute(ATTRIBUT_ERREUR_MSG,erreurMsg);			  
				  request.setAttribute(ATTRIBUT_ERREURS,erreurs);		
				  erreursMaps = metier.getErreurs();
				  request.setAttribute(ATTRIBUT_ERREUR_MAP,erreursMaps);			  
				 
				  session.setAttribute(ATTRIBUT_ERREUR_MSG,erreurMsg);
				  session.setAttribute("utilisateur",null);
				  
				  
			  
			  System.out.println("Renvoi au formulaire de connexion avec erreurs");
			  request.getRequestDispatcher(URL_VUE_CONNEXION).forward(request, response);
			  
			  }
			  if( isExist || isExist == true) {
		  
					  System.out.println("User Session in test : " + userSession);
					  User utilisateurBoolean = connecterUtilisateurBDD(login, password);
					  System.out.println("isExist ok : User are +" + utilisateurBoolean);
					  
					  session.setAttribute(ATTRIBUT_USER_LOGIN,utilisateurBoolean.getLogin());
					  request.setAttribute(ATTRIBUT_USER_LOGIN, utilisateurBoolean.getLogin());
					  System.out.println(ATTRIBUT_USER_LOGIN + " = " + utilisateurBoolean.getLogin());
					  
				
					  Long userId = (Long) utilisateurBoolean.getUser_id() ;	 
					  session.setAttribute(ATTRIBUT_USER_ID, userId );
					  request.setAttribute(ATTRIBUT_USER_ID, userId);  
					  System.out.println(ATTRIBUT_USER_ID + " = " + utilisateurBoolean.getUser_id());
					  
					  session.setAttribute(ATTRIBUT_USER_ROLE, utilisateurBoolean.getRole() );
					  request.setAttribute(ATTRIBUT_USER_ROLE, utilisateurBoolean.getRole() );
					  System.out.println(ATTRIBUT_USER_ROLE + " = " +utilisateurBoolean.getRole());						  
					  
					  session.setAttribute(ATTRIBUT_USER,utilisateurBoolean);
					  request.setAttribute(ATTRIBUT_USER, utilisateurBoolean);	 
					  System.out.println(" utilisateur ="  + session.getAttribute(ATTRIBUT_USER));
					  					  
					  session.setAttribute(ATTRIBUT_USER_SESSION,userSession);
					  request.setAttribute(ATTRIBUT_USER_SESSION, userSession);
					  System.out.println("userSession = " + session.getAttribute(ATTRIBUT_USER_SESSION));					  
				
						  Cookie cook_log =new  Cookie("user_login",utilisateurBoolean.getLogin());//creating cookie object
						  response.addCookie(cook_log);//adding cookie in the response
						  System.out.println("Cookie login : " + cook_log.getValue ());
						  
						  Cookie cook_name =new Cookie("user_id",utilisateurBoolean.getUsername());
						  //creating cookie object response.addCookie(cook_name);//adding cookie in
						 System.out.println("Cookie name : " + cook_name.getValue());
				  
						  System.out.println("Redirection ajouterArticle Boolean");
						  request.getRequestDispatcher("/addArticleMVC").forward(request, response);
		 
			  
			  }
		
		}  // Fin boutton

		// Fin do post
	}

	private User connecterUtilisateur(String login, String password) {
		User utilisateur = metier.connexionUtilisateurSimplyBDD(login, password);
		if (utilisateur == null)
			erreurMsg = "L'utilisateur " + login + " - " + password + ", n'a pas de compte associé.";
		return utilisateur;
	}

	private boolean boolConnecterUtilisateurSimply(String login, String password) {

		boolean isExist = metier.connexionUtilisateurTestBoolean(login, password);
		if (isExist) {
			User utilisateur = metier.connecterUtilisateurLoginMdp(login, password);
			System.out.println(utilisateur);
			return true;
		}
		// User utilisateur = metier.connecterUtilisateurLoginMdp(login,motDePasse);
		if (!isExist) {
			erreurMsg = "L'utilisateur " + login + " "+  "n'a pas de compte associé.";
			
			erreurs= "Veuillez réesayer avec un autre identifiant ou mot de passe";
			
			return false;
		}
		return isExist;

	}

	private User connecterUtilisateurBDD(String login, String password) {

		User user = metier.connexionUtilisateurTESTING(login, password);

		return user;
	}

}

