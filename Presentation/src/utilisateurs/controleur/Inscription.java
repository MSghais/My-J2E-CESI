package utilisateurs.controleur;

import java.io.IOException;
import java.util.HashMap;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import utilisateurs.entite.Role;
import utilisateurs.entite.User;

import utilisateurs.metier.InscriptionFormInterface;

/**
 * Servlet implementation class MVCInscription
 */

@WebServlet("/Inscription")
public class Inscription extends HttpServlet {
	
	
	@EJB
	InscriptionFormInterface inscriptionForm;
	
	    public static final String ATT_USER = "utilisateur";
	    public static final String ATT_FORM = "inscriptionForm";
	    public static final String ATT_ERRORS = "erreurs";
	    public static final String ATT_ERRORS_FORM = "erreursAllForm";
	    public static final String VUE = "WEB-INF/utilisateurs/vueInscriptionMVC.jsp";
	    private String erreurs;
	    
	    private HashMap<String,String> erreursMaps;
			
    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
	        /* Affichage de la page d'inscription */
	    	System.out.println("Arrivé doGET");
	    	
	    
	    	doPost(request,response);
	    }
		
    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
	        /* Préparation de l'objet formulaire */
	    	System.out.println("doPOST entree");
    	HttpSession session = request.getSession();
	    	
	    	Role[] roleStatus = Role.values();
	    	request.setAttribute("roleStatus", roleStatus);
	    	session.setAttribute("roleStatus", roleStatus);
	    	
	    	request.getRequestDispatcher(VUE).forward(request, response);
	    
	    	System.out.println("doPOST envoi la vue");
	    	
	
	   if(request.getParameter("inscriptionUserMVC") != null ) {

		 	System.out.println("button activé par User");
		 	
	 		User user = inscriptionForm.inscrireUser(request);
	        System.out.println("Inscription User EJB " );
	        
		        
		        if(user != null) {
		        request.setAttribute( ATT_USER, user );
			        
			        System.out.println("User créer : renvoi page en include Connexion" );
			      //  request.getRequestDispatcher("/Connexion" ).include( request, response );
			        
			        System.out.println("User créer : renvoi page en forward Connexion" );
			        
			        request.getRequestDispatcher("/Connexion").forward(request, response);
			     }
			     else if( !inscriptionForm.getErreurs().isEmpty() || user==null  ) {
			    	 
			    	 // Gestion erreurs
			    	 	request.setAttribute( ATT_FORM, inscriptionForm );
				     	request.setAttribute(ATT_ERRORS, inscriptionForm.getErreurs() );
				     	
				     	erreursMaps.putAll(inscriptionForm.getErreurs() );
				     	request.setAttribute(ATT_ERRORS_FORM, erreursMaps );
				     	
				    	session.setAttribute( ATT_FORM, inscriptionForm );
				    	session.setAttribute(ATT_ERRORS, inscriptionForm.getErreurs() );
				    	session.setAttribute("utilisateur",null);
			     	
			     	System.out.println("Requete set Attributs de utilisateur et inscriptionForm " );
  
			        System.out.println("doPOST renvoi page en forward" );
			        
			        request.getRequestDispatcher(VUE).forward(request, response); 
			     }
			   
  		}
	   
	   
	   //FIN DO POST
	    }
	    
	    
	    // FIN DE LA CLASSE
	}