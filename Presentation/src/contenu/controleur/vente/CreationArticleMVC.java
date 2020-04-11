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
		public static final String ATTRIBUT_USER_LOGIN         = "userLogin";
		public static final String ATTRIBUT_USER_ID      = "userId";
		public static final String ATTRIBUT_USER_ROLE      = "userRole";
		
	    public static final String ATTRIBUT_ERREUR_MSG   = "msgErreur";
	    public static final String VUE = "WEB-INF/contenu/vente/ajouterArticle.jsp";
			
		private String erreurMsg;
		
    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
	        /* Affichage de la page d'inscription */
	    	System.out.println("Arrivé doGET");
	    	
	      	
	    	ModelAllContent modelAll = new ModelAllContent();
	    	 
	    	
	        
	        List<Theme> themes = formContent.lireTousTheme();
	        
	        modelAll.setThemes(themes);
	        
	        
	        request.setAttribute("modelAll", modelAll);
	        
	        
	  
	    //	modelAll.setSecteurAll(secteurValues);
	    	
	    	request.getRequestDispatcher(VUE).include(request, response);
	    	
	    	doPost(request,response);/*
	        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
	        */
	       // System.out.println("doGET envoi la page");
	    }
		
    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
	        /* Préparation de l'objet formulaire */
	    	System.out.println("doPOST entree");
	  
	      //  InscriptionForm form = new InscriptionForm();
	    
	    	System.out.println("doPOST envoi la vue");
	    	
	    	
	    	HttpSession session = request.getSession();
	    	System.out.println(session.getAttribute(ATTRIBUT_USER));
	    	System.out.println(session.getAttribute(ATTRIBUT_USER_ID));
	    	System.out.println(session.getAttribute(ATTRIBUT_USER_LOGIN));
	    	System.out.println(session.getAttribute(ATTRIBUT_USER_ROLE));

	   if(request.getParameter("creationArticle") != null ) {

		 	System.out.println("button activé par User ppur Article");
		 	
				        /* Appel au traitement et à la validation de la requête, et récupération du bean en résultant */

		 	HttpSession sessionServlet = request.getSession();
			Principal userPrincipal = request.getUserPrincipal();
			
			System.out.println("User principale " + userPrincipal);
			
			System.out.println("Request User object " +request.getAttribute(ATTRIBUT_USER));
			
			System.out.println("Request ID" + request.getAttribute(ATTRIBUT_USER_ID));
			System.out.println("Request LOGIN " + request.getAttribute(ATTRIBUT_USER_LOGIN));
			
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
			  
			  User userRequest= (User) request.getAttribute(ATTRIBUT_USER);
			  
			  System.out.println(userRequest);
			  
			  User userSession = (User) sessionServlet.getAttribute(ATTRIBUT_USER);
			  
			  System.out.println("user SESSION" + userSession);
			  
				//Article article = form.creerArticle(request);
		        System.out.println("Inscription Article EJB " );
			  
			  Article articleUser =  form.creerArticleUser(request, userSession);
			  
			  System.out.println(articleUser);
			  
			/*
			 * System.out.println("Insertion Join table Object");
			 * 
			 * form.insertionJoinTableObject(userSession, articleUser);
			 */
	        	
	        	
			/*
			 * System.out.println("Insertion Join table Index");
			 * 
			 * form.insertionJoinTableId(userSession.getUser_id(), articleUser.getId());
			 */
	        	
	        	
			/*
			 * System.out.println("Insertion Article into List User " );
			 * 
			 * 
			 * form.insertIntoVenteUser(userSession, articleUser);
			 */
			  
			  
			  
			
	        	
			  

		       
		       
			//  			  System.out.println("Insertion JPQL Query " ); form.insertJoinArticleUserWithQuery( userSession , articleUser);
			  
		//	  form.insertJoinArticleUserWithQueryIndex( userSession.getUser_id() , articleUser.getId());     System.out.println("Update JPQL Query " );
			  
			 
			     
			     
			 // form.updateArticleWithUser(articleUser, userSession);
			  
			
	
			/*   
						  
			  if( (articleUser != null) && (userSession != null)  ) {
				  
			       System.out.println("Insertion JPQL Query " );
			       
			       
				  form.insertJoinArticleUserWithQuery( userSession , articleUser);
				  
				     System.out.println("Update JPQL Query " );
				     
				     
				  form.updateArticleWithUser(articleUser, userSession);
			  } */
			  
			  
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