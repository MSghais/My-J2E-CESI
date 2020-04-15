package interaction;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import contenu.entite.Article;
import contenu.enume.StatutArticle;
import contenu.metier.article.MetierInterfaceArticle;
import contenu.model.ModelArticleUnique;
import contenu.model.ModelContenu;
import interaction.entite.Commande;
import interaction.enume.StatutCommande;
import interaction.metier.MetierInterfaceCommande;
import utilisateurs.entite.User;

import utilisateurs.model.ModelUser;

@WebServlet("/acheterArticle")
public class AjouterArticleAchat extends HttpServlet {

	@EJB
	MetierInterfaceArticle metierArticle;

	@EJB
	MetierInterfaceCommande metierCommande;

	public static final String ATT_USER = "utilisateur";
	public static final String ATT_FORM = "form";
	public static final String ATTRIBUT_USER = "utilisateur";
	public static final String ATTRIBUT_USER_SESSION = "utilisateurSession";
	public static final String ATTRIBUT_USER_LOGIN = "userLogin";
	public static final String ATTRIBUT_USER_ID = "userId";
	public static final String ATTRIBUT_USER_ROLE = "userRole";

	// public static final String ATTRIBUT_ARTICLE_ID = "acheterArticleId";
	public static final String ATTRIBUT_ARTICLE_ID = "acheter";

	public static final String ATTRIBUT_ARTICLE_ACHAT = "acheterArticle";

	public static final String ATTRIBUT_ERREUR = "erreurs";
	public static final String ATTRIBUT_ERREUR_MSG = "msgErreur";
	public static final String ATTRIBUT_ERREUR_MAP_CB = "erreursMapCB";
	public static final String ATTRIBUT_ERREUR_CB = "erreurCB";
	public static final String ATTRIBUT_ERREUR_PICTO = "erreurPicto";

	public static final String VUE_ACHETER_ARTICLE = "WEB-INF/interaction/acheterArticle.jsp";

	private String erreurMsg;

	private String erreurPicto;

	private HashMap<Long, Article> articleMap;

	private HashMap<String, String> erreursMap;

	private Long nombreCommande = 1L;

	@PostConstruct
	public void init() {
		System.out.println("MyServlet init");
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("Entrer do Get Acheter article");

		HttpSession sessionGET = request.getSession();

		
		Long acheter_id = Long.valueOf(request.getParameter("acheter"));
		System.out.println("acheter id DO GET = " + acheter_id);
		
		request.setAttribute(ATTRIBUT_ARTICLE_ID, acheter_id);
		sessionGET.setAttribute(ATTRIBUT_ARTICLE_ID, acheter_id);
	
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println(" Acheter Article doPost");

		HttpSession sessionPOST = request.getSession();
		
		

		User userConnecter = (User) sessionPOST.getAttribute(ATTRIBUT_USER);
		System.out.println("user connecter est : " + userConnecter);


		
		Long acheter_id = (Long) sessionPOST.getAttribute(ATTRIBUT_ARTICLE_ID);
		System.out.println("achat article id=" + acheter_id);
	

		Article articleAchatID = metierArticle.rechercherArticleIndex(acheter_id);

		System.out.println("article Achat = " + articleAchatID);

		ModelArticleUnique modelArticle = new ModelArticleUnique();

		modelArticle.setArticle(articleAchatID);

		request.setAttribute("modelArticle", modelArticle);

		System.out.println(" Response getContextPath acheter  Article forward");
		// response.sendRedirect( request.getContextPath() + "/acheterArticle");

		System.out.println("Envoi vue acheter article Do POST include ");
		request.getRequestDispatcher(VUE_ACHETER_ARTICLE).include(request, response);

		// request.getRequestDispatcher(VUE_ACHETER_ARTICLE).include(request, response);

		if (request.getParameter("acheterArticleInput") != null) {

			System.out.println("acheter Input activé. Validation en cours");

			String cb_code = request.getParameter("codeBanquaire");
			System.out.println("cb = " + cb_code);
			String chiffre = request.getParameter("chiffreSecret");
			System.out.println("picot = " + chiffre);

			if (!cb_code.isEmpty() || !chiffre.isEmpty()

			) {

				System.out.println("Paramètre non null, activé la couche metier Commande");

				HttpSession sessionAcheter = request.getSession();
				Long user_idAcheter = (Long) sessionAcheter.getAttribute(ATTRIBUT_USER_ID);
				System.out.println("user id  est : " + user_idAcheter);
				User userAcheteur = metierArticle.rechercherUserIndex(user_idAcheter);

				System.out.println("user connecter est : " + userAcheteur);

				  System.out.println("creer commande simply");
				  metierCommande.creerCommandeSimply(userAcheteur.getLogin(), articleAchatID.getId());
				
				/*
				 * System.out.println("creer commande simply");
				 * metierCommande.creerCommandeSimply(userAcheteur.getLogin(),
				 * articleAchatID.getId());
				 */
				  
				  System.out.println("Update  article statut Reservé");
				  metierCommande.updateArticleStatut(articleAchatID, StatutArticle.RESERVE);

				  
				// metierCommande.insertArticleAchat(userConnecter, articleAchatID);
				
				 
				 User userVendeur = metierArticle.rechercherUserIndex(articleAchatID.getUser_vendeur().getUser_id() );
				 System.out.println("Uservendeur ID " + userVendeur.getUser_id());
				 Commande commandeLastID = metierCommande.selectCommandeByLastIndex();
				 System.out.println("Commande last id + " + commandeLastID.getCommande_id());
				 
				// metierCommande.insertArticleCommande(userVendeur, articleAchatID);
				 
				metierCommande.ajouterArticleAchat(userAcheteur, articleAchatID);
				
				

				//metierCommande.ajouterArticleVenteEnCours(userVendeur, articleAchatID)  ;
				 
				//
				 
				
		  System.out.println("metier try validation banquaire : ");
		  try {
			  	metierCommande.validationBanquaire(cb_code); 
		  }
		  catch (Exception e) 
		  { // TODO
			 // e.printStackTrace();
			  
				  System.out.println("Catch exception banquaire."); metierCommande.setErreur(  ATTRIBUT_ERREUR_MAP_CB, e.getMessage() );
				 metierCommande.setErreur( ATTRIBUT_ERREUR_MSG, e.getMessage() ); 				 
				 metierCommande.setErreur(ATTRIBUT_ERREUR_CB, e.getMessage() );
		  
				  System.out.println("attribut erreurs Map" + ATTRIBUT_ERREUR_MAP_CB);
				  erreursMap.put(ATTRIBUT_ERREUR_CB, e.getMessage() );
				  
				  erreurMsg = e.getMessage();
				  
				  System.out.println("attribut erreurs Map" + erreursMap.get(ATTRIBUT_ERREUR_CB) );
		  
		  
		  }
		  
		  System.out.println("metier try validation chiffre secret : "); 
		  try {
			  metierCommande.validationPictogramme(cb_code); 
		  } catch (Exception e) { //
		 //e.printStackTrace();
			  metierCommande.setErreur( ATTRIBUT_ERREUR_MAP_CB, e.getMessage() );
			  metierCommande.setErreur( ATTRIBUT_ERREUR_MSG, e.getMessage() );
			  metierCommande.setErreur( ATTRIBUT_ERREUR_PICTO, e.getMessage() );
		  
			 
			  erreurPicto = e.getMessage();
			  
			  System.out.println("attribut erreurs Map" + ATTRIBUT_ERREUR_MAP_CB);
			  
			  erreursMap.put(ATTRIBUT_ERREUR_PICTO, e.getMessage());
			  
			
		  
		  
		  }
		 

				
		  System.out.println(erreursMap);	
		  System.out.println(erreurPicto + erreurMsg); 
		  System.out.println("Vérification des validations conditionnels ");
		  
		  
				  if ( !erreurPicto.isEmpty() || !erreursMap.isEmpty() || !erreurMsg.isEmpty()
				  || erreursMap.containsKey(ATTRIBUT_ERREUR_CB) ||
				  erreursMap.containsKey(ATTRIBUT_ERREUR_PICTO) ) {
				  
				  request.setAttribute(ATTRIBUT_ERREUR_MAP_CB, erreursMap.values());
				  request.setAttribute(ATTRIBUT_ERREUR_CB, erreursMap.get(ATTRIBUT_ERREUR_CB));
				  
			
				  System.out. println("erreurs envoyé et détecte. Renvoi forward acheter article");
				
				  request.getRequestDispatcher("/acheterArticle").forward(request, response);
				  
				  } 
				  else if( !erreursMap.containsKey(ATTRIBUT_ERREUR_CB) ||
				  !erreursMap.containsKey(ATTRIBUT_ERREUR_PICTO) || erreursMap.isEmpty() ||
				  erreurMsg.isEmpty() || erreurPicto.isEmpty() ) {
					/*
					 * 
					 * System.out.println("creer commande simply");
					 * metierCommande.creerCommandeSimply(userConnecter.getLogin(),
					 * articleAchatID.getId());
					 * 
					 * System.out.println("Update  article statut Reservé");
					 * metierCommande.updateArticleStatut(articleAchatID, StatutArticle.RESERVE);
					 * 
					 * metierCommande.ajouterArticleAchat(userAcheteur, articleAchatID);
					 * 
					 * 
					 * User userVendeur =
					 * metierArticle.rechercherUserIndex(articleAchatID.getUser_vendeur().getUser_id
					 * () ); System.out.println("Uservendeur ID " + userVendeur.getUser_id());
					 * Commande commandeLastID = metierCommande.selectCommandeByLastIndex();
					 * System.out.println("Commande last id + " + commandeLastID.getCommande_id());
					 * 
					 * metierCommande.ajouterArticleCommande(userVendeur, commandeLastID) ;
					 */
					  
				  }
				  
				
				 

			} // FIN IF INPUT SUBMIT

			if (request.getParameter("supprimer") != null) {

			}

		} // FIN CONDITION

	} // FIN DO POST

} // FIN CLASSE
