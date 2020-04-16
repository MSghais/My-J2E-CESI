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
	public static final String ATTRIBUT_ERREUR_MAP = "erreursMap";
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
			
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
			System.out.println("Envoi vue acheter article Do POST include ");
			request.getRequestDispatcher(VUE_ACHETER_ARTICLE).include(request, response);

			if (request.getParameter("acheterArticleInput") != null) {
	
				System.out.println("acheter Input activé. Validation en cours");
				String cb_code = request.getParameter("codeBanquaire");
				System.out.println("cb = " + cb_code);
				String chiffre = request.getParameter("chiffreSecret");
				System.out.println("picot = " + chiffre);

			
				boolean isValidation = boolValidationBanque(request);
				
				boolean isValidationPicto = boolValidationBanque(request);
			
					if (  		( isValidationPicto && isValidation )  )   {
							/*(!cb_code.isEmpty() && !chiffre.isEmpty() ) 
							
							&& ( cb_code.length() == 10 || chiffre.length() == 3)*/
							
								System.out.println("Paramètre non null, activé la couche metier Commande");
				
								HttpSession sessionAcheter = request.getSession();
								Long user_idAcheter = (Long) sessionAcheter.getAttribute(ATTRIBUT_USER_ID);
								System.out.println("user id  est : " + user_idAcheter);
								User userAcheteur = metierArticle.rechercherUserIndex(user_idAcheter);
								System.out.println("user connecter est : " + userAcheteur);
				
								  System.out.println("creer commande simply");
								  metierCommande.creerCommandeSimply(userAcheteur.getLogin(), articleAchatID.getId());
								
				
								  System.out.println("Update  article statut Reservé");
								  metierCommande.updateArticleStatut(articleAchatID, StatutArticle.RESERVE);
				 
				/*
				 * User userVendeur =
				 * metierArticle.rechercherUserIndex(articleAchatID.getVendeur().getUser_id() );
				 * System.out.println("User vendeur ID " + userVendeur.getUser_id()); Commande
				 * commandeLastID = metierCommande.selectCommandeByLastIndex();
				 * System.out.println("Commande last id + " + commandeLastID.getCommande_id());
				 */
				/*
				 * System.out.println("Update commande Date Creation");
				 * metierCommande.updateCommandeDateCreation(commandeLastID);
				 * 
		 */					metierCommande.insertArticleAchat(userAcheteur, articleAchatID);
							metierCommande.insertArticleCommande(articleAchatID.getVendeur(),  articleAchatID);
					
							

					      	request.getRequestDispatcher("/mesAchats").forward(request, response);
						  
							//metierCommande.ajouterArticleAchat(userAcheteur, articleAchatID);
							
									}
					
						else // if ( !isValidation && !isValidationPicto)
						{
							
							  erreurMsg = "Veuillez insérer 10 chiffres pour votre CB";
							  erreurPicto = "Veuillez insérer votre pictogramme a 3 chiffres";
							  request.setAttribute(ATTRIBUT_ERREUR_CB, erreurMsg);
							  request.setAttribute(ATTRIBUT_ERREUR_PICTO, erreurPicto);		

							  request.setAttribute(ATTRIBUT_ERREUR_MAP, metierCommande.getErreurs());		

							  request.getRequestDispatcher(VUE_ACHETER_ARTICLE).forward(request, response);

							}
		
			
				} // FIN IF INPUT SUBMIT
	  
				  
						  
				if (request.getParameter("supprimer") != null) {
		
				}
				 			

	} // Fin doPost
	

private boolean boolValidationBanque(HttpServletRequest request) {

	boolean isBanque = metierCommande.validerCodeBanque(request);
	if (isBanque) {
		
		return true;
	}
	
	if (!isBanque) {
		
		erreurMsg = "Veuillez insérer 10 chiffres pour votre CB";
		
		
		return false;
	}
	return isBanque;

}

		private boolean boolValidationPicto(HttpServletRequest request) {
		
			boolean isBanque = metierCommande.validerPicto(request);
			if (isBanque) {
				
				return true;
			}
			// User utilisateur = metier.connecterUtilisateurLoginMdp(login,motDePasse);
			if (!isBanque) {
				
				
				erreurPicto = "Veuillez insérer votre pictogramme a 3 chiffres";
				
				
				return false;
			}
			return isBanque;
		}
		
		


}  //FIN CLASSE


