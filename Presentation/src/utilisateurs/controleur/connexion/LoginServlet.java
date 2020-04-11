package utilisateurs.controleur.connexion;

import java.io.IOException;
import java.security.Principal;

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
	public static final String ATTRIBUT_USER         = "utilisateur";
	public static final String ATTRIBUT_USER_LOGIN         = "userLogin";
	public static final String ATTRIBUT_USER_ID      = "userId";
	public static final String ATTRIBUT_USER_ROLE      = "userRole";
	
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
    			
    			System.out.println("Do get Connexion Servlet");
    			
    			request.getRequestDispatcher(URL_VUE_CONNEXION).forward(request, response); 

    			doPost(request, response);
	}
	@Override
    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
    	System.out.println("LoginServlet - doPost");
    	//request.getRequestDispatcher(URL_VUE_CONNEXION).forward(request, response);
    	
	     HttpSession session = request.getSession();
	       
        Principal userPrincipal = request.getUserPrincipal();
		
    	//request.getRequestDispatcher(URL_VUE_CONNEXION).forward(request, response); 
        
    	//request.getRequestDispatcher(URL_VUE_CONNEXION).forward(request, response); 
		
				if(request.getParameter("connecter") != null) {
					
					
			     String login = request.getParameter("login");
			     String password = request.getParameter("password");
			        
			     System.out.println("test connection utilisateur func servlet");
			     User utilisateur = connecterUtilisateurSimply(login,password);
			     System.out.println(utilisateur);
			     
			     
			     System.out.println("LoginServlet utilisateur="+utilisateur);
			   //  System.out.println(utilisateur.toString());
	
			        
			        System.out.println(userPrincipal);
			     //    if(utilisateur.getUser_id().equals(null) || utilisateur.getLogin().isEmpty()  ){
			        
			        if( utilisateur.getLogin().isEmpty()  || utilisateur.getUser_id() == null || utilisateur.equals(null) || utilisateur==null){
			        	
			        	
				    	 request.setAttribute(ATTRIBUT_ERREUR_MSG,erreurMsg);
				    	
				    	 
				    	// session.setAttribute(ATTRIBUT_ERREUR_MSG,erreurMsg);
				    	 session.setAttribute("utilisateur",null);
				    	 
				    	 System.out.println("Renvoi au formulaire de connexion avec erreurs");
				    	 request.getRequestDispatcher(URL_VUE_CONNEXION).include(request, response); 
				     }
			        
			        if(utilisateur!=null) {
					     	session.setAttribute(ATTRIBUT_USER_LOGIN,utilisateur.getLogin());
					     	request.setAttribute(ATTRIBUT_USER_LOGIN, utilisateur.getLogin());   	
					     	System.out.println(ATTRIBUT_USER_LOGIN + " = " + utilisateur.getLogin());
					  
					     	
					    	session.setAttribute(ATTRIBUT_USER_ID, utilisateur.getUser_id() );
					     	request.setAttribute(ATTRIBUT_USER_ID, utilisateur.getUser_id());	
					     	System.out.println(ATTRIBUT_USER_ID + " = " +  utilisateur.getUser_id());
					     
					    	session.setAttribute(ATTRIBUT_USER_ROLE, utilisateur.getRole() );
					     	request.setAttribute(ATTRIBUT_USER_ROLE, utilisateur.getRole() );	
					     	System.out.println(ATTRIBUT_USER_ROLE + " = " + utilisateur.getRole());
					     	
					     	 session.setAttribute(ATTRIBUT_USER,utilisateur);
							 request.setAttribute(ATTRIBUT_USER, utilisateur);
							 
						/*
						 * session.setAttribute(ATTRIBUT_USER,utilisateur);
						 * request.setAttribute(ATTRIBUT_USER, utilisateur);
						 */
		
					     	System.out.println(session.getAttribute(ATTRIBUT_USER));
					  
					     	Cookie cook_log =new Cookie("user_login",utilisateur.getLogin());//creating cookie object  
					     	response.addCookie(cook_log);//adding cookie in the response 
					       	System.out.println("Cookie login : " + cook_log.getValue ());
					     	  
					     	Cookie cook_name =new Cookie("user_id",utilisateur.getUsername() );//creating cookie object  
					     	response.addCookie(cook_name);//adding cookie in the response     	
					     	System.out.println("Cookie name : " + cook_name.getValue());
			     	
			     	
						/*
						 * Cookie cook_index =new Cookie("user_id", utilisateur.getUser_id()
						 * );//creating cookie object response.addCookie(cook_name);//adding cookie in
						 * the response System.out.println("Cookie name : " + cook_index.getValue());
						 */
			     	
			     	System.out.println("Redirection ajouterArticle");
			     	
				 //    request.getRequestDispatcher("/toutArticles").forward(request, response); 

				     request.getRequestDispatcher("/addArticleMVC").include(request, response); 
	     	
				     	
				     	System.out.println("Redirection toutArticles");
	   //  request.getRequestDispatcher("addArticleMVC").include(request, response); 
	     		     	
	     }
	    	
	     //Fin boutton
	}
		
		
		
		//Fin do post
	}
	 private User connecterUtilisateurSimply(String login,String password) {
		 
		 User utilisateur = metier.connexionUtilisateurSimplyBDD(login, password);
	    // User utilisateur = metier.connecterUtilisateurLoginMdp(login,motDePasse);
         if(utilisateur==null)
	       	erreurMsg = "L'utilisateur " + login + " - " + password + ", n'a pas de compte associ�.";   
	     return utilisateur;
	}
	     
					
	
	
	
	
}




/* private User connecterUtilisateur(String login,String password) {
        
		 
	     User utilisateur = null;
	     
	        
		 System.out.println("metier connexionUtilisateur test boolean");
        boolean isConnect = metier.connexionUtilisateur(login,password);
        if(isConnect) {
       	// utilisateur = new User(login, password);
       	 
       	 System.out.println("Boolean is corect, select UserAllArgs");
       	 
    	
       	 //utilisateur = metier.selectUserConnexion(login);
			/*
			 * User utilisateurBDD = metier.lireLoginUser(login);
			 * System.out.println("User in Local scope to String = " +
			 * utilisateurBDD.toString());
			 
       //	 utilisateur = metier.getUtilisateur(login);
       	 
       	 System.out.println("selectUserAllArgs in servlet");
       	 utilisateur = metier.selectUserAllArgs(login);
       	 System.out.println("User in Local scope to String = " + utilisateur.toString());
		 return utilisateur;
      
       	 
        }   
	     else 
	     	{
	    	 
	    	 erreurMsg = "L'utilisateur " + login + " - " + password + ", n'a pas de compte associé.";
	    	 
	    	 }
	
        return utilisateur;
       // System.out.println('');
	     //return utilisateur;
	}  */


/*   private User connecterUtilisateurBDD(String login,String password) {

System.out.println("Test connecter  user Servlet ");

User utilisateur = null;

   
System.out.println("metier connexionUtilisateur ");
boolean isConnect = metier.connexionUtilisateur(login,password);
if(isConnect) {
	// utilisateur = new User(login, password);
	 
	 System.out.println("Boolean is corect");
	// utilisateur = metier.selectUserConnexion(login);
	 
	 utilisateur = metier.selectUserAllArgs(login);
	 System.out.println("User in Local scope" + utilisateur);
	 
	 if(utilisateur != null ) {

   	 System.out.println("User in Local scope to String" + utilisateur.toString());
		 return utilisateur;
	 }
	 
}   
else 
	{
	 erreurMsg = "L'utilisateur " + login + " - " + password + ", n'a pas de compte associé.";
	 }
 
System.out.println("Servlet user in Local scope = " + utilisateur);
System.out.println(utilisateur.toString());
return utilisateur;
// System.out.println('');
//return utilisateur;
} */
