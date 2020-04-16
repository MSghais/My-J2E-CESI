package interaction;

import java.io.IOException;
import java.lang.reflect.Array;
import java.security.Principal;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.jms.Session;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import contenu.entite.Article;
import contenu.entite.Theme;
import contenu.metier.article.MetierInterfaceArticle;
import contenu.metier.theme.MetierInterfaceTheme;
import contenu.model.ModelAllContent;

import utilisateurs.entite.Role;
import utilisateurs.entite.User;

/**
 * Servlet implementation class MVCInscription
 */

@WebServlet("/gererCategories")
public class GererCategories extends HttpServlet {
	
	
	@EJB
	MetierInterfaceArticle form;
	
	@EJB
	MetierInterfaceTheme metierTheme;
	
	    public static final String ATT_USER = "utilisateur";
	    public static final String ATT_FORM = "form";
		public static final String ATTRIBUT_USER         = "utilisateur";
		public static final String ATTRIBUT_USER_SESSION         = "utilisateurSession";
		public static final String ATTRIBUT_USER_LOGIN         = "userLogin";
		public static final String ATTRIBUT_USER_ID      = "userId";
		public static final String ATTRIBUT_USER_ROLE      = "userRole";
		
	    public static final String ATTRIBUT_ERREUR_MSG   = "msgErreur";
	    public static final String VUE = "WEB-INF/interaction/gererCategories.jsp";
			
		private String erreurMsg;
		
		
		
    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
	        /* Affichage de la page d'inscription */
	    	System.out.println("Arrivé doGET");
	    
	    	
	    	doPost(request,response);
	     
	    }
		
    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
	        /* Préparation de l'objet formulaire */
	    	System.out.println("doPOST entree");
	
	    	HttpSession session = request.getSession();
	    	
	    	User userConnecter = (User)session.getAttribute(ATTRIBUT_USER);
	    	System.out.println("User connecter");
	    	
	    	if( userConnecter.getRole() == Role.Administrateur) {
	    		
	    		ModelAllContent modelTheme = new ModelAllContent();
	    		List<Theme> themes = metierTheme.lireTousTheme();
	    		modelTheme.setThemes(themes);
	    		
	    		request.setAttribute("modelTheme", modelTheme);
		  
		     	User user =  (User) request.getAttribute(ATTRIBUT_USER);   
		     	System.out.println("user in session are " + user);
		     	

	
		    	request.getRequestDispatcher(VUE).include(request, response);
		    	System.out.println("doPOST envoi la vue");
		     	//HttpSession sessionServlet = request.getSession();
		     	Cookie[] cookies = request.getCookies(); System.out.println(cookies);
	    			
	    	}
	    	else {
	    	
	    		request.getRequestDispatcher("Shopping").forward(request, response);
	    	}
	    	

		 	   if(request.getParameter("creationCategorie") != null ) {

		 		 	System.out.println("button activé par User ppur Article");
		 	  
		 		        System.out.println("Inscription Theme EJB  " );
		 		        
		 		        metierTheme.creerTheme(request);
		 		      
		 		        System.out.println("doPOST Article renvoi page en forward" );

		 		      	request.getRequestDispatcher(VUE).forward(request, response);
		 			  
		 			
		 	   		}
	
	    

	   
	   
	   
	    } //FIN DO POST
	    
	    
	   
}  // FIN DE LA CLASSE