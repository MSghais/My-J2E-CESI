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
    	request.getRequestDispatcher(URL_VUE_CONNEXION).forward(request, response); 
    			doPost(request, response);
	}
	@Override
    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
    	System.out.println("LoginServlet - doPost");
		
	
		
		if(request.getParameter("connecter") != null) {
			
			
	     String login = request.getParameter("login");
	     String password = request.getParameter("password");
	        
	     System.out.println("test connection utilisateur func servlet");
	     User utilisateur = connecterUtilisateur(login,password);
	     
	     System.out.println("LoginServlet utilisateur="+utilisateur);
	     HttpSession session = request.getSession();
	       
        Principal userPrincipal = request.getUserPrincipal();
	        
	        System.out.println(userPrincipal);
	     
	     if(utilisateur!=null) {
	     	session.setAttribute(ATTRIBUT_USER_LOGIN,utilisateur.getLogin());
	     	request.setAttribute(ATTRIBUT_USER_LOGIN, utilisateur.getLogin());
    	
	     	System.out.println(ATTRIBUT_USER_LOGIN + utilisateur.getLogin());
	  
	     	
	    	session.setAttribute(ATTRIBUT_USER_ID, utilisateur.getUser_id() );
	     	request.setAttribute(ATTRIBUT_USER_ID, utilisateur.getUser_id());	
	     	System.out.println(ATTRIBUT_USER_ID + utilisateur.getUser_id());
	     
	    	session.setAttribute(ATTRIBUT_USER_ROLE, utilisateur.getRole() );
	     	request.setAttribute(ATTRIBUT_USER_ROLE, utilisateur.getRole() );	
	     	System.out.println(ATTRIBUT_USER_ROLE + utilisateur.getRole());
	     	
	    	session.setAttribute(ATTRIBUT_USER,utilisateur);
	     	request.setAttribute(ATTRIBUT_USER, utilisateur);

	     	System.out.println(session.getAttribute(ATTRIBUT_USER));
	  
	     	
	     	System.out.println("Redirection toutArticles");
		     request.getRequestDispatcher("/toutArticles").include(request, response); 

	     	
	   //  request.getRequestDispatcher("addArticleMVC").include(request, response); 
	     		     	
	     }
	     else {
	     	request.setAttribute(ATTRIBUT_ERREUR_MSG,erreurMsg);
	     	session.setAttribute("utilisateur",null);

		   request.getRequestDispatcher(URL_VUE_CONNEXION).forward(request, response); 
	     }
	     
	}
		
	    // request.getRequestDispatcher(URL_VUE_CONNEXION).forward(request, response); 
	 
	//request.getAttribute(ATTRIBUT_USER);
	}
	private User connecterUtilisateur(String login,String password) {
        
		 System.out.println("Test connecter  user Servlet ");
		 
	     User utilisateur = null;
	     
	        
		 System.out.println("metier connexionUtilisateur ");
        boolean isConnect = metier.connexionUtilisateur(login,password);
        if(isConnect) {
       	// utilisateur = new User(login, password);
       	 
       	 System.out.println("Boolean is corect");
       	 
    	
       	 utilisateur = metier.selectUserConnexion(login);
       	 
       	 System.out.println("User in Local scope" + utilisateur);
		 return utilisateur;
      
       	 
        }   
	     else 
	     	{
	    	 erreurMsg = "L'utilisateur " + login + " - " + password + ", n'a pas de compte associé.";
	    	 }
	      
        System.out.println("Servlet user in Local scope" + utilisateur);
   	 
        return utilisateur;
       // System.out.println('');
	     //return utilisateur;
	}
	 private User connecterUtilisateurBDD(String login,String password) {
	        
		 System.out.println("Test connecter  user Servlet ");
		 
	     User utilisateur = null;
	     
	        
		 System.out.println("metier connexionUtilisateur ");
         boolean isConnect = metier.connexionUtilisateur(login,password);
         if(isConnect) {
        	// utilisateur = new User(login, password);
        	 
        	 System.out.println("Boolean is corect");
        	 utilisateur = metier.selectUserConnexion(login);
        	 
        	 System.out.println("User in Local scope" + utilisateur);
        	 
        	 if(utilisateur != null ) {
        		 return utilisateur;
        	 }
        	 
         }   
	     else 
	     	{
	    	 erreurMsg = "L'utilisateur " + login + " - " + password + ", n'a pas de compte associé.";
	    	 }
	      
         System.out.println("Servlet user in Local scope" + utilisateur);
    	 
         return utilisateur;
        // System.out.println('');
	     //return utilisateur;
	}
}
