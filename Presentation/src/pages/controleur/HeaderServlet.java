

package pages.controleur;

import java.io.IOException;
import java.security.Principal;

import javax.annotation.PostConstruct;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import contenu.model.ModelContenu;
import utilisateurs.model.ModelUser;



@WebServlet("/headerServlet")
public class HeaderServlet extends HttpServlet { 
	//private MetierItf metier = MetierEtudiantPromo.getInstance(); 
	public static final String ATTRIBUT_USER         = "utilisateur";
    public static final String ATTRIBUT_ERREUR_MSG   = "msgErreur";
    public static final String VUE_HEADER   = "WEB-INF/headerPhantom.jsp";
	private String erreurMsg;
	
	@PostConstruct
	public void init() {
		System.out.println("MyServlet init");
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
	
		 HttpSession sessionServlet = request.getSession();
		
		Principal userPrincipal = request.getUserPrincipal();
		
		System.out.println("User principale " + userPrincipal);
		  if(userPrincipal!=null) {
		     	sessionServlet.setAttribute(ATTRIBUT_USER,userPrincipal);
		     }
		     else {
		     	request.setAttribute(ATTRIBUT_ERREUR_MSG,erreurMsg);
		     	sessionServlet.setAttribute("utilisateur",null);
		     }
		
		   HttpSession session = request.getSession();
		   
			
			request.getRequestDispatcher(VUE_HEADER).include(request, response); 
			
			doPost(request,response);
			
	       /*
		     if(utilisateur!=null) {
		     	session.setAttribute(ATTRIBUT_USER,utilisateur);
		     }
		     else {
		     	request.setAttribute(ATTRIBUT_ERREUR_MSG,erreurMsg);
		     	session.setAttribute("utilisateur",null);
		     }
		     request.getRequestDispatcher(URL_VUE_CONNEXION).forward(request, response); */
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int nb = 0;
		System.out.println("MyServlet Accueil est la"+ nb);
		nb++;
		//String urlVue = "WEB-INF/JSP/accueil.jsp";
		/*
		 * String urlVue = "WEB-INF/pages/indexPhantom.jsp";
		 * request.getRequestDispatcher(urlVue).forward(request, response);
		 */

		
		
		ModelUser modelUser = new ModelUser(); 
		
		ModelContenu modelContenu = new ModelContenu(); 
		

		 


		 
		 	/*     if(utilisateur!=null) {
	     	session.setAttribute(ATTRIBUT_USER,utilisateur);
	     }
	     else {
	     	request.setAttribute(ATTRIBUT_ERREUR_MSG,erreurMsg);
	     	session.setAttribute("utilisateur",null);
	     }
	     request.getRequestDispatcher(URL_VUE_CONNEXION).forward(request, response);  */

		/*
		HttpSession session = request.getSession();
		Panier panier = (Panier)session.getAttribute("monPanier"); // Je r�cup�re le bean panier stock� dans ma session
		
		if(fruits == null) fruits = new HashMap<String,Integer>();
		Integer nbFruit = fruits.get(fruit);
		if(fruits.get(fruit) == null) nbFruit = 0;
		fruits.put(fruit, nbFruit+1);
		panier.setFruits(fruits);
		session.setAttribute("monPanier", panier);
		}
		RequestDispatcher dispatcher;
		dispatcher = request.getRequestDispatcher("/index.jsp");
		dispatcher.forward(request, response);*/
	
		/*
				if(request.getParameter("creerPromotion") != null){
					String creerPromotion = request.getParameter("creerPromotion");
					System.out.println("creerPromotion=" + creerPromotion);
					String intitule = request.getParameter("intitule");  
					String acronyme = request.getParameter("acronyme"); 
					Promotion promotion = new Promotion(acronyme,intitule);
					metier.creerPromotion(promotion);
				}
				List<Promotion> promotions = metier.lireTousPromotion();
				ModelPromo modelPromo = new ModelPromo(); 
				modelPromo.setPromotions(promotions);
				request.setAttribute("modelPromo", modelPromo);
				
				// Selectionner Promotion et ses étudiants
				
				if(request.getParameter("choixPromotion") != null){
					String choixPromotion = request.getParameter("choixPromotion");
					System.out.println("choixPromotion=" + choixPromotion);
					String acronyme = request.getParameter("acronymePromo"); 
				
					Promotion promotion = new Promotion();
					promotion = metier.lirePromotion(acronyme);
					
					List<Etudiant> etudiantPromo = promotion.getEtudiants();
					ModelEtudiant modelEtudiant = new ModelEtudiant(); 
					modelEtudiant.setEtudiants(etudiantPromo);
					
					request.setAttribute("modelEtudiant", modelEtudiant);					
					request.getRequestDispatcher(urlVue).forward(request, response); 
					
				}
				
				//List<Etudiant> etudiants = metier.lireTousEtudiant();
				
				// Creer Etudiant
				if(request.getParameter("creerEtudiant") != null){
					String creerEtudiant = request.getParameter("creerEtudiant");
					System.out.println("creerEtudiant=" + creerEtudiant);
					String acronyme = request.getParameter("acronyme"); 
					String nom = request.getParameter("nom");  
					String prenom = request.getParameter("prenom"); 
					Etudiant etudiant = new Etudiant(nom, prenom);
					
					metier.inscrireEtudiantPromotion(acronyme, etudiant);
					
				}
				
				List<Etudiant> etudiants = metier.lireTousEtudiant();
				ModelEtudiant modelEtudiant = new ModelEtudiant(); 
				modelEtudiant.setEtudiants(etudiants);
				request.setAttribute("modelEtudiant", modelEtudiant);
				request.getRequestDispatcher(urlVue).forward(request, response); 
				
				*/
		
	}
}

