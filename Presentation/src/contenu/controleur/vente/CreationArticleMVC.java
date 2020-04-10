package contenu.controleur.vente;

import java.io.IOException;
import java.lang.reflect.Array;
import java.security.Principal;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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

@WebServlet("/addArticleMVC")
public class CreationArticleMVC extends HttpServlet {
	
	
	@EJB
	MetierInterfaceArticle form;
	
	@EJB
	MetierInterfaceAllContent formContent;
	
	    public static final String ATT_USER = "utilisateur";
	    public static final String ATT_FORM = "form";
		public static final String ATTRIBUT_USER         = "utilisateur";
	    public static final String ATTRIBUT_ERREUR_MSG   = "msgErreur";
	    public static final String VUE = "WEB-INF/contenu/vente/ajouterArticle.jsp";
			
		private String erreurMsg;
		
    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
	        /* Affichage de la page d'inscription */
	    	System.out.println("Arriv� doGET");
	    	
	      	
	    	ModelAllContent modelAll = new ModelAllContent();
	    	 
	    	
	        
	        List<Theme> themes = formContent.lireTousTheme();
	        
	        modelAll.setThemes(themes);
	        
	        
	        request.setAttribute("modelAll", modelAll);
	        
	        
	  
	    //	modelAll.setSecteurAll(secteurValues);
	    	
	    	request.getRequestDispatcher(VUE).forward(request, response);
	    	
	    	doPost(request,response);/*
	        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
	        */
	       // System.out.println("doGET envoi la page");
	    }
		
    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
	        /* Pr�paration de l'objet formulaire */
	    	System.out.println("doPOST entree");
	  
	      //  InscriptionForm form = new InscriptionForm();
	    
	    	System.out.println("doPOST envoi la vue");
	    	
	    	

	   if(request.getParameter("creationArticle") != null ) {

		 	System.out.println("button activ� par User ppur Article");
		 	
				        /* Appel au traitement et � la validation de la requ�te, et r�cup�ration du bean en r�sultant */

		 	HttpSession sessionServlet = request.getSession();
			Principal userPrincipal = request.getUserPrincipal();
			
			System.out.println("User principale " + userPrincipal);
			
			
			
			  if(userPrincipal!=null) {
			     	sessionServlet.setAttribute(ATTRIBUT_USER,userPrincipal);
			     	request.setAttribute(ATTRIBUT_USER, (User) userPrincipal);
			     }
			     else {
			     	request.setAttribute(ATTRIBUT_ERREUR_MSG,erreurMsg);
			     	sessionServlet.setAttribute("utilisateur",null);
			     //response.sendRedirect(getContext + "login");
			     }
		
		//	  User userSession = (User) sessionServlet.getAttribute(ATTRIBUT_USER);
			  
			  User userSession = (User) sessionServlet.getAttribute(ATTRIBUT_USER);
			  
				//Article article = form.creerArticle(request);
		        System.out.println("Inscription Article EJB " );
			  
			  Article articleUser =  form.creerArticleUser(request, userSession);
			  
			  
			  if( (articleUser != null) && (userSession != null)  ) {
				  
			       System.out.println("Insertion JPQL Query " );
			       
			       
				  form.insertJoinArticleUserWithQuery( userSession , articleUser);
				  
				  form.insertJoinArticleUserWithQueryIndex(userSession.getUser_id(), articleUser.getId());
			  }
	
			  
			  
			  
		        request.setAttribute( ATT_FORM, form );
		        request.setAttribute( ATT_USER, articleUser);
			  
		        request.setAttribute( ATTRIBUT_ERREUR_MSG, form.getErreurs());
		 
		 
      
				        /* Stockage du formulaire et du bean dans l'objet request */
				       
				      
				        System.out.println("Requete set Attributs de utilisateur et inscriptionForm " );
				        
				
				        
				        
				        
				        System.out.println("doPOST Article renvoi page en forward" );
				        
				      	request.getRequestDispatcher(VUE).include(request, response);
	   		}
	   
	   
	   //FIN DO POST
	    }
	    
	    
	    // FIN DE LA CLASSE
	}