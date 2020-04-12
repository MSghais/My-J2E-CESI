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
import utilisateurs.entite.Utilisateur;
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
	    	System.out.println("Arriv� doGET");
	    	
	    
	    	doPost(request,response);/*
	        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
	        */
	       // System.out.println("doGET envoi la page");
	    }
		
    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
	        /* Pr�paration de l'objet formulaire */
	    	System.out.println("doPOST entree");
	    	
	    	//String urlVueMVC = "WEB-INF/utilisateurs/vueInscriptionMVC.jsp";
	    	
	     	HttpSession session = request.getSession();
	    	
	    	Role[] roleStatus = Role.values();
	    	request.setAttribute("roleStatus", roleStatus);
	    	session.setAttribute("roleStatus", roleStatus);
	    	
	    	request.getRequestDispatcher(VUE).forward(request, response);
	    	
	    	// 	    	request.getRequestDispatcher(VUE).forward(request, response);
	    	
	      //  InscriptionForm form = new InscriptionForm();
	    
	    	System.out.println("doPOST envoi la vue");
	    	
	
	   if(request.getParameter("inscriptionUserMVC") != null ) {

		 	System.out.println("button activ� par User");
		 	
	 		User user = inscriptionForm.inscrireUser(request);
	        System.out.println("Inscription User EJB " );
	        
		        
		        if(user != null) {
			     	//session.setAttribute(ATT_USER,user);
			        //request.setAttribute( ATT_USER, user );
			        System.out.println("User cr�er : renvoi page en forward Connexion" );
			      //  request.setAttribute( ATT_FORM, inscriptionForm );
				       
			        request.setAttribute( ATT_USER, user );
			        
			        
			        request.getRequestDispatcher("/Connexion" ).forward( request, response );
			        
			        
			     }
			     else if(! inscriptionForm.getErreurs().isEmpty()  ) {
			    	 
			    	 // Gestion erreurs
			    	 	request.setAttribute( ATT_FORM, inscriptionForm );
				     	request.setAttribute(ATT_ERRORS, inscriptionForm.getErreurs() );
				     	
				     	erreursMaps.putAll(inscriptionForm.getErreurs() );
				     	request.setAttribute(ATT_ERRORS_FORM, erreursMaps );
			     	// session.setAttribute( ATT_ERRORS, inscriptionForm.getErreurs() );
				        
				        
			     	session.setAttribute("utilisateur",null);
			     	
			     	System.out.println("Requete set Attributs de utilisateur et inscriptionForm " );
			        
			        
			        
			        System.out.println("doPOST renvoi page en forward" );
			        
			        request.getRequestDispatcher(VUE).forward(request, response); 
			     }
			   
		        
		        
		      //  request.getRequestDispatcher(VUE).forward(request, response); 
		 
	        
			      
			        
			       
			        
				       // request.getRequestDispatcher("/Inscription" ).forward( request, response );
				      //  request.getRequestDispatcher("/Connexion" ).include( request, response );
				        
				       //request.getRequestDispatcher( VUE ).include( request, response );
				        
				       // this.getServletContext().getRequestDispatcher( VUE ).include( request, response );
				        
	   		}
	   
	   
	   //FIN DO POST
	    }
	    
	    
	    // FIN DE LA CLASSE
	}