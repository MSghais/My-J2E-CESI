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

	/*
	 * public LoginServlet() { metier = new Metier(); }
	 */

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("Do get Connexion Servlet");

		request.getRequestDispatcher(URL_VUE_CONNEXION).forward(request, response);

		doPost(request, response);
	}

	/**
	 *
	 */
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("LoginServlet - doPost");
		// request.getRequestDispatcher(URL_VUE_CONNEXION).forward(request, response);

		HttpSession session = request.getSession();

		Principal userPrincipal = request.getUserPrincipal();

		// request.getRequestDispatcher(URL_VUE_CONNEXION).forward(request, response);

		// request.getRequestDispatcher(URL_VUE_CONNEXION).forward(request, response);

		if (request.getParameter("connecter") != null) {

			String login = request.getParameter("login");
			String password = request.getParameter("password");

			System.out.println("test connection utilisateur func servlet Boolean USER BDD");

			
			  boolean isExist = boolConnecterUtilisateurSimply(login, password);
			  if(!isExist || isExist==false ){
			  
				  request.setAttribute(ATTRIBUT_ERREUR_MSG,erreurMsg);
				  
				  request.setAttribute(ATTRIBUT_ERREURS,erreurs);
				  
				  request.setAttribute(ATTRIBUT_ERREUR_MAP,erreursMaps);
				  
				  
				  // request.setAttribute(ATTRIBUT_ERREUR_MAP); //
				  session.setAttribute(ATTRIBUT_ERREUR_MSG,erreurMsg);
				  session.setAttribute("utilisateur",null);
				  
			  
			  
			  System.out.println("Renvoi au formulaire de connexion avec erreurs");
			  request.getRequestDispatcher(URL_VUE_CONNEXION).include(request, response);
			  
			  }
			  if( isExist || isExist == true) {
			  
						  System.out.println("User Session in test : " + userSession);
						  
						  User utilisateurBoolean = connecterUtilisateurBDD(login, password);
						  System.out.println("isExist ok : User are +" + utilisateurBoolean);
						  
						  session.setAttribute(ATTRIBUT_USER_LOGIN,utilisateurBoolean.getLogin());
						  request.setAttribute(ATTRIBUT_USER_LOGIN, utilisateurBoolean.getLogin());
						  System.out.println(ATTRIBUT_USER_LOGIN + " = " +
						  utilisateurBoolean.getLogin());
						  
						  
				/*
				 * session.setAttribute(ATTRIBUT_USER_ID, utilisateurBoolean.getUser_id() );
				 * request.setAttribute(ATTRIBUT_USER_ID, utilisateurBoolean.getUser_id());
				 */
						  Long userId = (Long) utilisateurBoolean.getUser_id() ;
								 
						  session.setAttribute(ATTRIBUT_USER_ID, userId );
							 request.setAttribute(ATTRIBUT_USER_ID, userId);  
						  
						  System.out.println(ATTRIBUT_USER_ID + " = " +
						  utilisateurBoolean.getUser_id());
						  
						  session.setAttribute(ATTRIBUT_USER_ROLE, utilisateurBoolean.getRole() );
						  request.setAttribute(ATTRIBUT_USER_ROLE, utilisateurBoolean.getRole() );
						  System.out.println(ATTRIBUT_USER_ROLE + " = " +
						  utilisateurBoolean.getRole());
						  
						  session.setAttribute(ATTRIBUT_USER,utilisateurBoolean);
						  request.setAttribute(ATTRIBUT_USER, utilisateurBoolean);
							 
						  System.out.println(" utilisateur ="  + session.getAttribute(ATTRIBUT_USER));
						  
						  
						  
						  session.setAttribute(ATTRIBUT_USER_SESSION,userSession);
						  request.setAttribute(ATTRIBUT_USER_SESSION, userSession);
						  System.out.println("userSession = " + session.getAttribute(ATTRIBUT_USER_SESSION));
					
						  
					
						  Cookie cook_log =new
						  Cookie("user_login",utilisateurBoolean.getLogin());//creating cookie object
						  response.addCookie(cook_log);//adding cookie in the response
						  System.out.println("Cookie login : " + cook_log.getValue ());
						  
						  Cookie cook_name =new Cookie("user_id",utilisateurBoolean.getUsername());
						  //creating cookie object response.addCookie(cook_name);//adding cookie in
						System.out.println("Cookie name : " + cook_name.getValue());
						  
						  
						  
						  System.out.println("Redirection ajouterArticle Boolean");
						  
						  
						  request.getRequestDispatcher("/addArticleMVC").include(request, response);
						  
						  
						 
			  
			  }
			 

			System.out.println("test connection utilisateur func servlet User BDD");
			// User utilisateur = connecterUtilisateurSimply(login,password);
			// User utilisateur = connecterUtilisateurBDD(login,password);

			/*
			 * User utilisateur = null; try { utilisateur
			 * =connecterUtilisateur(login,password); } catch(Exception e) { erreurMsg =
			 * "L'utilisateur saisi n'existe pas";
			 * System.out.println("Utilisateur inconnu"); }
			 * 
			 * if( utilisateur.equals(null) || utilisateur==null){
			 * 
			 * request.setAttribute(ATTRIBUT_ERREUR_MSG,erreurMsg);
			 * 
			 * request.setAttribute(ATTRIBUT_ERREUR_MAP,erreursMaps); //
			 * session.setAttribute(ATTRIBUT_ERREUR_MSG,erreurMsg);
			 * session.setAttribute("utilisateur",null);
			 * 
			 * session.setAttribute(ATTRIBUT_ERREUR_MAP,erreursMaps);
			 * 
			 * session.setAttribute(ATTRIBUT_ERREUR_MSG,erreurMsg);
			 * 
			 * 
			 * System.out.println("Renvoi au formulaire de connexion avec erreurs");
			 * request.getRequestDispatcher(URL_VUE_CONNEXION).include(request, response);
			 * 
			 * 
			 * } else if( utilisateur!=null || utilisateur.getUser_id() != null ) {
			 * 
			 * System.out.println("User Session in test : " + userSession);
			 * 
			 * 
			 * session.setAttribute(ATTRIBUT_USER_LOGIN,utilisateur.getLogin());
			 * request.setAttribute(ATTRIBUT_USER_LOGIN, utilisateur.getLogin());
			 * System.out.println(ATTRIBUT_USER_LOGIN + " = " + utilisateur.getLogin());
			 * 
			 * 
			 * session.setAttribute(ATTRIBUT_USER_ID, utilisateur.getUser_id() );
			 * request.setAttribute(ATTRIBUT_USER_ID, utilisateur.getUser_id());
			 * System.out.println(ATTRIBUT_USER_ID + " = " + utilisateur.getUser_id());
			 * 
			 * session.setAttribute(ATTRIBUT_USER_ROLE, utilisateur.getRole() );
			 * request.setAttribute(ATTRIBUT_USER_ROLE, utilisateur.getRole() );
			 * System.out.println(ATTRIBUT_USER_ROLE + " = " + utilisateur.getRole());
			 * 
			 * session.setAttribute(ATTRIBUT_USER,utilisateur);
			 * request.setAttribute(ATTRIBUT_USER, utilisateur);
			 * session.getAttribute(ATTRIBUT_USER);
			 * 
			 * session.setAttribute(ATTRIBUT_USER_SESSION,userSession);
			 * request.setAttribute(ATTRIBUT_USER_SESSION, userSession);
			 * session.getAttribute(ATTRIBUT_USER_SESSION);
			 * 
			 * System.out.println(session.getAttribute(ATTRIBUT_USER));
			 * 
			 * Cookie cook_log =new Cookie("user_login",utilisateur.getLogin());//creating
			 * response.addCookie(cook_log);//adding cookie in the response
			 * System.out.println("Cookie login : " + cook_log.getValue ());
			 * 
			 * Cookie cook_name =new Cookie("user_id",utilisateur.getUsername() );//creating
			 * response.addCookie(cook_name);//adding cookie in the response
			 * System.out.println("Cookie name : " + cook_name.getValue());
			 * 
			 * System.out.println("Redirection ajouterArticle Utilisateur");
			 * 
			 * request.getRequestDispatcher("/addArticleMVC").include(request, response);
			 * System.out.println("Redirection toutArticles"); }
			 */
			 

			

			
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
			erreurMsg = "L'utilisateur " + login + " - " + password + ", n'a pas de compte associé.";
			return false;
		}
		return isExist;

	}

	private User connecterUtilisateurBDD(String login, String password) {

		User user = metier.connexionUtilisateurTESTING(login, password);

		return user;
	}

}

/* BOOLEAN CONNEXION */

// DO POST TESTING BOOLEAN 

/*
 * boolean isExist = boolConnecterUtilisateurSimply(login, password);
 * if(!isExist || isExist==false ){
 * 
 * request.setAttribute(ATTRIBUT_ERREUR_MSG,erreurMsg);
 * 
 * request.setAttribute(ATTRIBUT_ERREUR_MSG,erreursMaps); //
 * session.setAttribute(ATTRIBUT_ERREUR_MSG,erreurMsg);
 * session.setAttribute("utilisateur",null);
 * 
 * 
 * 
 * System.out.println("Renvoi au formulaire de connexion avec erreurs");
 * request.getRequestDispatcher(URL_VUE_CONNEXION).include(request, response); }
 * 
 * // WITH USER TESTING // if(utilisateur!=null) { // if(utilisateur!=null) {
 * 
 * if( isExist || isExist == true) {
 * 
 * System.out.println("User Session in test : " + userSession);
 * 
 * User utilisateurBoolean = connecterUtilisateurBDD(login, password);
 * System.out.println("isExist ok : User are +" + utilisateurBoolean);
 * 
 * session.setAttribute(ATTRIBUT_USER_LOGIN,utilisateurBoolean.getLogin());
 * request.setAttribute(ATTRIBUT_USER_LOGIN, utilisateurBoolean.getLogin());
 * System.out.println(ATTRIBUT_USER_LOGIN + " = " +
 * utilisateurBoolean.getLogin());
 * 
 * 
 * session.setAttribute(ATTRIBUT_USER_ID, utilisateurBoolean.getUser_id() );
 * request.setAttribute(ATTRIBUT_USER_ID, utilisateurBoolean.getUser_id());
 * System.out.println(ATTRIBUT_USER_ID + " = " +
 * utilisateurBoolean.getUser_id());
 * 
 * session.setAttribute(ATTRIBUT_USER_ROLE, utilisateurBoolean.getRole() );
 * request.setAttribute(ATTRIBUT_USER_ROLE, utilisateurBoolean.getRole() );
 * System.out.println(ATTRIBUT_USER_ROLE + " = " +
 * utilisateurBoolean.getRole());
 * 
 * session.setAttribute(ATTRIBUT_USER,utilisateurBoolean);
 * request.setAttribute(ATTRIBUT_USER, utilisateurBoolean);
 * session.getAttribute(ATTRIBUT_USER);
 * 
 * session.setAttribute(ATTRIBUT_USER_SESSION,userSession);
 * request.setAttribute(ATTRIBUT_USER_SESSION, userSession);
 * session.getAttribute(ATTRIBUT_USER_SESSION);
 * 
 * session.setAttribute(ATTRIBUT_USER,utilisateur);
 * request.setAttribute(ATTRIBUT_USER, utilisateur);
 * 
 * 
 * System.out.println(session.getAttribute(ATTRIBUT_USER));
 * 
 * Cookie cook_log =new
 * Cookie("user_login",utilisateurBoolean.getLogin());//creating cookie object
 * response.addCookie(cook_log);//adding cookie in the response
 * System.out.println("Cookie login : " + cook_log.getValue ());
 * 
 * Cookie cook_name =new Cookie("user_id",utilisateurBoolean.getUsername()
 * );//creating cookie object response.addCookie(cook_name);//adding cookie in
 * the response System.out.println("Cookie name : " + cook_name.getValue());
 * 
 * 
 * /* Cookie cook_index =new Cookie("user_id", utilisateur.getUser_id()
 * );//creating cookie object response.addCookie(cook_name);//adding cookie in
 * the response System.out.println("Cookie name : " + cook_index.getValue());
 * 
 * 
 * System.out.println("Redirection ajouterArticle Boolean");
 * 
 * // request.getRequestDispatcher("/toutArticles").forward(request, response);
 * 
 * request.getRequestDispatcher("/addArticleMVC").include(request, response);
 * 
 * 
 * System.out.println("Redirection toutArticles"); //
 * request.getRequestDispatcher("addArticleMVC").include(request, response);
 * 
 * }
 */

/*
 * private boolean testConnecterUtilisateurSimply(String login,String password)
 * {
 * 
 * boolean isExist = metier.connexionUtilisateurTestBoolean(login, password);
 * if(isExist) { userSession = metier.connecterUtilisateurLoginMdp(login,
 * password); System.out.println(userSession); return true; } // User
 * utilisateur = metier.connecterUtilisateurLoginMdp(login,motDePasse);
 * if(!isExist) { erreurMsg = "L'utilisateur " + login + " - " + password +
 * ", n'a pas de compte associ�."; return false; } return isExist;
 * 
 * }
 */

/*
 * private void ConnecterUtilisateurSimply(String login,String password) {
 * 
 * boolean isExist = metier.connexionUtilisateurTestBoolean(login, password);
 * if(isExist) { User utilisateur = metier.connecterUtilisateurLoginMdp(login,
 * password);
 * 
 * } // User utilisateur =
 * metier.connecterUtilisateurLoginMdp(login,motDePasse); if(!isExist) {
 * erreurMsg = "L'utilisateur " + login + " - " + password +
 * ", n'a pas de compte associ�.";
 * 
 * }
 * 
 * 
 * }
 */

/*
 * private User connecterUtilisateurBDD(String login,String password) {
 * 
 * 
 * User user = metier.connexionUtilisateur(login, password); if(isExist) { User
 * utilisateur = metier.connecterUtilisateurLoginMdp(login, password); return
 * utilisateur; } // User utilisateur =
 * metier.connecterUtilisateurLoginMdp(login,motDePasse); if(!isExist) erreurMsg
 * = "L'utilisateur " + login + " - " + password +
 * ", n'a pas de compte associ�."; return erreurMsg; }
 */

/*
 * private User connecterUtilisateurSimply(String login,String password) {
 * 
 * 
 * boolean isExist = metier.connexionUtilisateur(login, password); if(isExist) {
 * User utilisateur = metier.connecterUtilisateurLoginMdp(login, password);
 * return utilisateur; } // User utilisateur =
 * metier.connecterUtilisateurLoginMdp(login,motDePasse); if(!isExist) erreurMsg
 * = "L'utilisateur " + login + " - " + password +
 * ", n'a pas de compte associ�."; return erreurMsg; }
 */

/*
 * private boolean connecterUtilisateurSimplyBDD(String login,String password)
 * throws Exception { try { if( user.equals(null) || user == null ){
 * 
 * throw new Exception( "Veuillez ressaisir votre login ou votre mot de passe !"
 * ); User utilisateur = metier.connexionUtilisateurSimplyBDD(login, password);
 * 
 * } catch(Exception e) {
 * 
 * 
 * 
 * } /* // User utilisateur =
 * metier.connecterUtilisateurLoginMdp(login,motDePasse); if(utilisateur==null)
 * erreurMsg = "L'utilisateur " + login + " - " + password +
 * ", n'a pas de compte associ�."; return utilisateur;
 */

/*
 * private User connecterUtilisateur(String login,String password) {
 * 
 * 
 * User utilisateur = null;
 * 
 * 
 * System.out.println("metier connexionUtilisateur test boolean"); boolean
 * isConnect = metier.connexionUtilisateur(login,password); if(isConnect) { //
 * utilisateur = new User(login, password);
 * 
 * System.out.println("Boolean is corect, select UserAllArgs");
 * 
 * 
 * //utilisateur = metier.selectUserConnexion(login); /* User utilisateurBDD =
 * metier.lireLoginUser(login);
 * System.out.println("User in Local scope to String = " +
 * utilisateurBDD.toString());
 * 
 * // utilisateur = metier.getUtilisateur(login);
 * 
 * System.out.println("selectUserAllArgs in servlet"); utilisateur =
 * metier.selectUserAllArgs(login);
 * System.out.println("User in Local scope to String = " +
 * utilisateur.toString()); return utilisateur;
 * 
 * 
 * } else {
 * 
 * erreurMsg = "L'utilisateur " + login + " - " + password +
 * ", n'a pas de compte associé.";
 * 
 * }
 * 
 * return utilisateur; // System.out.println(''); //return utilisateur; }
 */

/*
 * private User connecterUtilisateurBDD(String login,String password) {
 * 
 * System.out.println("Test connecter  user Servlet ");
 * 
 * User utilisateur = null;
 * 
 * 
 * System.out.println("metier connexionUtilisateur "); boolean isConnect =
 * metier.connexionUtilisateur(login,password); if(isConnect) { // utilisateur =
 * new User(login, password);
 * 
 * System.out.println("Boolean is corect"); // utilisateur =
 * metier.selectUserConnexion(login);
 * 
 * utilisateur = metier.selectUserAllArgs(login);
 * System.out.println("User in Local scope" + utilisateur);
 * 
 * if(utilisateur != null ) {
 * 
 * System.out.println("User in Local scope to String" + utilisateur.toString());
 * return utilisateur; }
 * 
 * } else { erreurMsg = "L'utilisateur " + login + " - " + password +
 * ", n'a pas de compte associé."; }
 * 
 * System.out.println("Servlet user in Local scope = " + utilisateur);
 * System.out.println(utilisateur.toString()); return utilisateur; //
 * System.out.println(''); //return utilisateur; }
 */
