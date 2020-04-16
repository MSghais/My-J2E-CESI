package contenu.controleur.vente;

import java.io.IOException;
import java.lang.reflect.Array;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
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

@WebServlet("/addArticleMVC")
public class CreationArticleMVC extends HttpServlet {
	
	
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
		
	    public static final String ATTRIBUT_ERREUR_MSG   = "msgErreurArticle";
	    public static final String VUE = "WEB-INF/contenu/vente/ajouterArticle.jsp";
			
		private String erreurMsg;
		private HashMap<String,String> erreursMap;
		
		
    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
	        /* Affichage de la page d'inscription */
	    	System.out.println("Arrivé doGET");
	    
	    	
	    	doPost(request,response);
	     
	    }
		
    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
	        /* Préparation de l'objet formulaire */
	    	System.out.println("doPOST entree");
	
	    		ModelAllContent modelTheme = new ModelAllContent();
	    		List<Theme> themes = metierTheme.lireTousTheme();
	    		modelTheme.setThemes(themes);
	    		
	    		request.setAttribute("modelTheme", modelTheme);
	  
	     	User user =  (User) request.getAttribute(ATTRIBUT_USER);   
	     	System.out.println("user in session are " + user);

	    	request.getRequestDispatcher(VUE).include(request, response);
	    	System.out.println("doPOST envoi la vue");
	    	HttpSession session = request.getSession();
	     	//HttpSession sessionServlet = request.getSession();
	     	Cookie[] cookies = request.getCookies(); System.out.println(cookies);
	

	   if(request.getParameter("creationArticle") != null ) {

		 	System.out.println("button activé par User ppur Article");
	  
		        System.out.println("Inscription Article EJB  " );
		        Article articleSessionRequest =  form.creerArticleUserRequestSession(request,  session);
		        System.out.println(articleSessionRequest);
			
			    request.setAttribute( ATT_FORM, form );
			    request.setAttribute( ATTRIBUT_ERREUR_MSG, form.getErreurs());
			  
			    erreursMap = (HashMap<String, String>) form.getErreurs();
			    
			    request.setAttribute( "erreursMap", erreursMap);
		        
			    
			    if(articleSessionRequest != null && form.getErreurs().isEmpty()) {
			    	
			    	System.out.println("Requete set Attributs de utilisateur et inscriptionForm " );	        
			        System.out.println("doPOST Article renvoi page en forward" );

			      	request.getRequestDispatcher("/Shopping").forward(request, response);
				  
			    	
			    }
			    else {
			    	
			    	System.out.println("Requete set Attributs de utilisateur et inscriptionForm " );	        
			        System.out.println("doPOST Article renvoi page en forward" );

			      	request.getRequestDispatcher(VUE).forward(request, response);
			    	
			    	
			    }
		       
			  
			
	   		}
	   
	   
	   //FIN DO POST
	    }
	    
	    
	    // FIN DE LA CLASSE
	}