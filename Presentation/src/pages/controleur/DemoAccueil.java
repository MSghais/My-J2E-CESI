

package pages.controleur;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



@WebServlet("/Accueil")
public class DemoAccueil extends HttpServlet { 
	//private MetierItf metier = MetierEtudiantPromo.getInstance(); 
	
	@PostConstruct
	public void init() {
		System.out.println("MyServlet init");
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doPost(request,response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int nb = 0;
		System.out.println("MyServlet doPost"+ nb);
		nb++;
		//String urlVue = "WEB-INF/JSP/accueil.jsp";
		String urlVue = "index-demo.html";
		request.getRequestDispatcher(urlVue).forward(request, response); 
		
		
		
		
		
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

