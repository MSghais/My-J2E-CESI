package contenu.controleur.vente;

import java.io.IOException;
import java.lang.reflect.Array;
import java.security.Principal;
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

import contenu.AllContent.metier.MetierInterfaceAllContent;
import contenu.entite.Article;
import contenu.entite.Theme;
import contenu.metier.article.MetierInterfaceArticle;
import contenu.model.ModelAllContent;

import utilisateurs.entite.Role;
import utilisateurs.entite.User;

/**
 * Servlet implementation class MVCInscription
 */

@WebServlet("/modificationArticle")
public class ModificationArticle extends HttpServlet {
	
	
	@EJB
	MetierInterfaceArticle metierArticle;
	
	@EJB
	MetierInterfaceAllContent formContent;
	
	    public static final String ATT_USER = "utilisateur";
	    public static final String ATT_FORM = "form";
		public static final String ATTRIBUT_USER         = "utilisateur";
		public static final String ATTRIBUT_USER_SESSION         = "utilisateurSession";
		public static final String ATTRIBUT_USER_LOGIN         = "userLogin";
		public static final String ATTRIBUT_USER_ID      = "userId";
		public static final String ATTRIBUT_USER_ROLE      = "userRole";
		
	    public static final String ATTRIBUT_ERREUR_MSG   = "msgErreur";
	    public static final String VUE = "WEB-INF/contenu/vente/modifierArticle.jsp";
			
		private String erreurMsg;
		
		
		
    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
	        /* Affichage de la page d'inscription */
	    	System.out.println("Arriv� doGET");
	    
	    	
	    	doPost(request,response);
	     
	    }
		
    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
	        /* Pr�paration de l'objet formulaire */
	    	System.out.println("doPOST entree");
	  
	      //  InscriptionForm form = new InscriptionForm();
	    		ModelAllContent modelAll = new ModelAllContent();
	        List<Theme> themes = formContent.lireTousTheme();   
	        modelAll.setThemes(themes);   
	        request.setAttribute("modelAll", modelAll);
	        
	        
	  
	     	User user =  (User) request.getAttribute(ATTRIBUT_USER);
	    	System.out.println("user in session are " + user);
	     	System.out.println("Envoi vue Modificaiton article en forward");
	    	request.getRequestDispatcher(VUE).include(request, response);
	    	
	    
	    	System.out.println("doPOST envoi la vue");
	    	HttpSession session = request.getSession();	
	     	//HttpSession sessionServlet = request.getSession();
	     	Cookie[] cookies = request.getCookies(); System.out.println(cookies);
	    	System.err.println( "Request cookie in modification Article : " + request.getCookies());


	   if(request.getParameter("modifierArticle") != null ) {

		 	System.out.println("button activ� par User ppur Article");
		 	
				        /* Appel au traitement et � la validation de la requ�te, et r�cup�ration du bean en r�sultant */
		 	
		        System.out.println("Modification Metier Article EJB  " );
		        Article articleSessionRequest =  metierArticle.updateArticleUserRequestSession(request, session);
		        System.out.println(articleSessionRequest);
		
			  
			   request.setAttribute( ATT_FORM, metierArticle );
		       request.setAttribute( ATTRIBUT_ERREUR_MSG, metierArticle.getErreurs());
		        
		        System.out.println("Requete set Attributs de utilisateur et inscriptionForm " );       
		        System.out.println("doPOST Article renvoi page en forward" );

		      	request.getRequestDispatcher(VUE).forward(request, response);
		
	   
	  
	    }
	    
	    
	    // FIN DE LA CLASSE
	}
    
    
}