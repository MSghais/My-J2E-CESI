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

@WebServlet("/modificationArticle")
public class ModificationArticle extends HttpServlet {
	
	
	@EJB
	MetierInterfaceArticle metierArticle;
	
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
	    public static final String VUE = "WEB-INF/contenu/vente/modifierArticle.jsp";
			
		private String erreurMsg;
		
		
		
    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
	        /* Affichage de la page d'inscription */
	    	System.out.println("Arrivé doGET");
	    
	    	Long article_id = Long.valueOf(request.getParameter("modifier"));
	    	request.setAttribute("modifier", article_id);
	      	Article articleModif = metierArticle.rechercherArticleIndex(article_id);
	      	request.setAttribute("articleModif", articleModif);
	    	doPost(request,response);
	     
	    }
		
    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
	        /* Préparation de l'objet formulaire */
	    	System.out.println("doPOST entree");
	    	
	    	HttpSession session = request.getSession();	
	      //  InscriptionForm form = new InscriptionForm();
    		ModelAllContent modelAll = new ModelAllContent();
	        List<Theme> themes = metierTheme.lireTousTheme();   
	        modelAll.setThemes(themes);   
	        request.setAttribute("modelAll", modelAll);
 
	     	User user =  (User) session.getAttribute(ATTRIBUT_USER);
	    	System.out.println("user in session are " + user);
	     	System.out.println("Envoi vue Modificaiton article en forward");
	     
	    	request.getRequestDispatcher(VUE).forward(request, response);
	    	
	    
	    	System.out.println("doPOST envoi la vue");
	    
	     	//HttpSession sessionServlet = request.getSession();
	     	Cookie[] cookies = request.getCookies(); System.out.println(cookies);
	    	System.err.println( "Request cookie in modification Article : " + request.getCookies());


	   if(request.getParameter("modifierArticle") != null ) {

		 	System.out.println("button activé par User ppur Article");
		 	
	        /* Appel au traitement et à la validation de la requête, et récupération du bean en résultant */
		 	
		        System.out.println("Modification Metier Article EJB Updating " );
		        
		        
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