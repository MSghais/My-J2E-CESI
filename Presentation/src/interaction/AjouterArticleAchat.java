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
	public static final String ATTRIBUT_USER         = "utilisateur";
	public static final String ATTRIBUT_USER_SESSION         = "utilisateurSession";
	public static final String ATTRIBUT_USER_LOGIN         = "userLogin";
	public static final String ATTRIBUT_USER_ID      = "userId";
	public static final String ATTRIBUT_USER_ROLE      = "userRole";
	
	// public static final String ATTRIBUT_ARTICLE_ID      = "acheterArticleId";
	public static final String ATTRIBUT_ARTICLE_ID      = "acheter";
	
    public static final String ATTRIBUT_ARTICLE_ACHAT  = "acheterArticle";
    
    public static final String ATTRIBUT_ERREUR   = "erreurs";
    public static final String ATTRIBUT_ERREUR_MSG   = "msgErreur";   
    public static final String ATTRIBUT_ERREUR_MAP_CB   = "erreursMapCB";
    public static final String ATTRIBUT_ERREUR_CB   = "erreurCB";
    public static final String ATTRIBUT_ERREUR_PICTO   = "erreurPicto";
    
    public static final String VUE_ACHETER_ARTICLE = "WEB-INF/interaction/acheterArticle.jsp";
		
	private String erreurMsg;
	
	
	private String erreurPicto;
	
	private HashMap<Long, Article> articleMap;
	
	private HashMap<String, String> erreursMap;
	
	@PostConstruct
	public void init() {
		System.out.println("MyServlet init");
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		System.out.println("Entrer do Get Acheter article");
		
		
		  
		  //
		 HttpSession sessionGET = request.getSession();
		Long acheter_id = Long.valueOf(request.getParameter("acheter"));
		sessionGET.setAttribute(ATTRIBUT_ARTICLE_ID, acheter_id);
		request.setAttribute(ATTRIBUT_ARTICLE_ID, acheter_id);
		/*
		 * HttpSession sessionGET = request.getSession();
		 * 
		 * Long acheter_id = Long.valueOf(request.getParameter("acheter")); //
		 * System.out.println("achat article id=" + acheter_id);
		 * sessionGET.setAttribute(ATTRIBUT_ARTICLE_ID, acheter_id);
		 * 
		 * Long user_id = (Long) sessionGET.getAttribute(ATTRIBUT_USER_ID);
		 * System.out.println("user id connecter est : " + user_id);
		 */
		 
		
		
	//request.getRequestDispatcher(VUE_ACHETER_ARTICLE).forward(request, response);
	
		//Long acheter_id = Long.valueOf(request.getParameter(ATTRIBUT_ARTICLE_ID));
		
		doPost(request,response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(" Acheter Article doPost");
		

		
		HttpSession sessionPOST = request.getSession();
		
		  User userConnecter = (User)sessionPOST.getAttribute(ATTRIBUT_USER);
		  System.out.println("user connecter est : " + userConnecter);
		  
		  // IN DO GET
		 // Long acheter_id = Long.valueOf(request.getParameter("acheter"));
		 // System.out.println("achat article id=" + acheter_id); 
		  
		
		Long acheter_id = (Long) sessionPOST.getAttribute(ATTRIBUT_ARTICLE_ID);
		 // Long acheter_id = (Long) request.getAttribute(ATTRIBUT_ARTICLE_ID);
		  System.out.println("achat article id=" + acheter_id); 
			
		  Article articleAchatID =  metierArticle.rechercherArticleIndex(acheter_id);
			 
		  System.out.println("article Achat = " + articleAchatID);
		  
		 // request.setAttribute(ATTRIBUT_ARTICLE_ID,acheter_id );
		 // Long acheter_id = Long.valueOf(request.getParameter( ATTRIBUT_ARTICLE_ID));
		 
		  

		  
		  
		  
		  
		  ModelArticleUnique modelArticle= new ModelArticleUnique();
		  
		  modelArticle.setArticle(articleAchatID);
		  
		  request.setAttribute("modelArticle", modelArticle);
		
		  
		  System.out.println(" Response getContextPath acheter  Article forward");
	//	response.sendRedirect( request.getContextPath() + "/acheterArticle");
			 
			 
		  
		  System.out.println("Envoi vue acheter article Do POST include ");
		request.getRequestDispatcher(VUE_ACHETER_ARTICLE).include(request, response);
		  
		//  request.getRequestDispatcher(VUE_ACHETER_ARTICLE).include(request, response);
		  
			if(request.getParameter("acheterArticleInput") != null ) {
					
					
					System.out.println("acheter Input activé. Validation en cours");
					
					String cb_code =  request.getParameter("codeBanquaire");
					System.out.println("cb = " + cb_code);
					String chiffre = request.getParameter("chiffreSecret");
					System.out.println("picot = " + chiffre);
					
					
					  
						if( !cb_code.isEmpty()
								|| !chiffre.isEmpty()
								
								
								)  {
						
						
						System.out.println("Paramètre non null, activé la couche metier Commande");
						
					
						
						
						HttpSession sessionAcheter = request.getSession();
						 Long user_idAcheter = (Long) sessionAcheter.getAttribute(ATTRIBUT_USER_ID);
						 System.out.println("user id  est : " + user_idAcheter);
						 
						 User userAcheteur = metierArticle.rechercherUserIndex(user_idAcheter);
						 
						 System.out.println("user connecter est : " + userAcheteur);
				
						 
						 
							
							System.out.println("creerCommandeAll");
							Commande commandeCreation = (Commande) metierCommande.creerCommandeAll(articleAchatID, userConnecter);
							
							 System.out.println("Update commande statut a en Cours");
							metierCommande.updateCommandeStatut(commandeCreation, StatutCommande.ENCOURS);
						 
							 System.out.println("Update  article statut Reservé");
							metierCommande.updateArticleStatut(articleAchatID, StatutArticle.RESERVE);
							
							
							
							
						
							
							System.out.println("ajouter Article Achat for user Connecter");
							metierCommande.ajouterArticleAchat(userAcheteur, articleAchatID);
							
							
							
							System.out.println("Creation vendeur a partir de Article.getVendeur()");
							User vendeur_id = (User) articleAchatID.getUser_vendeur();
							
							System.out.println("vendeur de l'article = " + vendeur_id);
							
							System.out.println("Ajouter Commande au vendeur de l'article ");
							metierCommande.ajouterArticleCommande(vendeur_id, commandeCreation);
							
							
							//metierCommande.insertIntoUserAchat(userAcheteur, articleAchatID);
				/*
				 * System.out.println("Insertion Achat article user connecter");
				 * metierCommande.insertIntoUserAchat(userAcheteur, articleAchatID);
				 */
							 
							 
						 System.out.println("metier try validation banquaire : ");
								try {
									metierCommande.validationBanquaire(cb_code);
								} catch (Exception e) {
									// TODO Auto-generated catch block
								//	e.printStackTrace();
									System.out.println("Catch exception banquaire.");
									metierCommande.setErreur( ATTRIBUT_ERREUR_MAP_CB, e.getMessage() );
									metierCommande.setErreur( ATTRIBUT_ERREUR_MSG, e.getMessage() );
									metierCommande.setErreur( ATTRIBUT_ERREUR_CB, e.getMessage() );
									
									System.out.println("attribut erreurs Map" + ATTRIBUT_ERREUR_MAP_CB);
									erreursMap.put(ATTRIBUT_ERREUR_CB, e.getMessage() );
									
									erreurMsg = e.getMessage();
									
									System.out.println("attribut erreurs Map" + erreursMap.get(ATTRIBUT_ERREUR_CB ) );
									
								}
								
						System.out.println("metier try validation chiffre secret : ");
								try {
									metierCommande.validationPictogramme(cb_code);
								} catch (Exception e) {
									// TODO Auto-generated catch block
									//e.printStackTrace();
									metierCommande.setErreur( ATTRIBUT_ERREUR_MAP_CB, e.getMessage() );
									metierCommande.setErreur( ATTRIBUT_ERREUR_MSG, e.getMessage() );
									metierCommande.setErreur( ATTRIBUT_ERREUR_PICTO, e.getMessage() );
									
									erreurPicto = e.getMessage();
									
									System.out.println("attribut erreurs Map" + ATTRIBUT_ERREUR_MAP_CB);
									
									erreursMap.put(ATTRIBUT_ERREUR_PICTO, e.getMessage());
									
									
									System.out.println("attribut erreurs Map" + erreursMap.get(ATTRIBUT_ERREUR_PICTO) );
									
									
								}
		
								
								if ( !erreurPicto.isEmpty() || !erreursMap.isEmpty() || !erreurMsg.isEmpty() 
										|| erreursMap.containsKey(ATTRIBUT_ERREUR_CB) || erreursMap.containsKey(ATTRIBUT_ERREUR_PICTO) ) {
	 
									request.setAttribute(ATTRIBUT_ERREUR_MAP_CB, erreursMap.values());									
									request.setAttribute(ATTRIBUT_ERREUR_CB, erreursMap.get(ATTRIBUT_ERREUR_CB));
									
									request.setAttribute(ATTRIBUT_ERREUR_PICTO, erreursMap.get(ATTRIBUT_ERREUR_PICTO));									
									request.setAttribute(ATTRIBUT_ERREUR_MSG, erreurMsg);
									
									System.out.println("erreurs envoyé et détecte. Renvoi forward acheter article");
									request.getRequestDispatcher("/acheterArticle").forward(request, response);
									
								}
								else if( !erreursMap.containsKey(ATTRIBUT_ERREUR_CB) || !erreursMap.containsKey(ATTRIBUT_ERREUR_PICTO)
										|| erreursMap.isEmpty() 
										|| erreurMsg.isEmpty()
										|| erreurPicto.isEmpty() )				
								{
										
									
									
									System.out.println("creerCommandeAll");
									metierCommande.creerCommandeAll(articleAchatID, userConnecter);
									
									System.out.println("method COMMANDE insert Article Achat");
									metierCommande.insertIntoUserAchat(userConnecter, articleAchatID);
																	
							
									
									System.out.println("Récupération Vendeur de l'article");
									User vendeur = (User ) articleAchatID.getUser_vendeur();
									System.out.println("vendeur = " + vendeur);
									
									
									System.out.println("Insertion Commande pour vendeur");
									metierCommande.insertIntoUserCommande(vendeur, articleAchatID);
									
								
						
												/*
												 * System.out.println("method Article ajouter Article Achat");
												 * metierArticle.ajouterArticleAchat(userConnecter, articleAchatID);
												 * 
												 * 
												 * System.out.println("method ajouter Article en Commande du vendeur");
												 * metierArticle.ajouterArticleCommande(userConnecter, articleAchatID);
												 * //HttpSession session = request.getSession();
												 * 
												 * System.out.println(sessionAcheter.getAttribute(ATTRIBUT_ARTICLE_ACHAT));
												 * 
												 * System.out.println(sessionAcheter.getAttribute(ATTRIBUT_ARTICLE_ACHAT));
												 */
									
										  
										  
								}
								
			
								 
								/*
								 * Long acheter_idIf = Long.valueOf( request.getParameter("acheter"));
								 * System.out.println("achat article id=" + acheter_idIf);
								 */
								/*
								 * Long acheter_idIf = (Long) request.getAttribute(ATTRIBUT_ARTICLE_ACHAT) ;
								 * System.out.println("achat article id=" + acheter_idIf);
								 */
								  
						/*
						 * Article articleAchat = metierArticle.rechercherArticleIndex(acheter_id);
						 * System.out.println("article Achat = " + articleAchat);
						 * 
						 * Article articleAchatValidation =
						 * metierArticle.rechercherArticleIndex(acheter_id);
						 * 
						 *  System.out.println("article Achat = " + articleAchatID);
										 request.setAttribute(ATTRIBUT_ARTICLE_ACHAT, articleAchatID);
										 sessionAcheter.setAttribute(ATTRIBUT_ARTICLE_ACHAT, articleAchatID);
						 */
								 
								 
		
			
			} // FIN IF INPUT SUBMIT
						
		
			if(request.getParameter("supprimer") != null ) {
				
				
				
			}
			
			
		
			
								} // FIN CONDITION
	
			} // FIN DO POST
			
		
	

} // FIN CLASSE


/* if(request.getParameter("acheterArticleButton") != null ) {

System.out.println("acheter Button activé. Validation en cours");

String cb_codeBUTTON = (String) request.getParameter("codeBanquaire");
System.out.println(cb_codeBUTTON);
String chiffreBUTTON =request.getParameter("chiffreSecret");
System.out.println(chiffreBUTTON);

if( request.getParameter("codeBanquaire") != null
		&& request.getParameter("chiffreSecret") != null)  {
	
	System.out.println("Paramètre non null, activé la couche metier Commande");
	
	
	HttpSession sessionAcheter = request.getSession();
	 Long user_idAcheter = (Long) sessionAcheter.getAttribute(ATTRIBUT_USER_ID);
	 System.out.println("user id connecter est : " + user_idAcheter);
	 
	 User userAcheteur = metierArticle.rechercherUserIndex(user_idAcheter);
	 
	 System.out.println("user connecter est : " + userAcheteur);
	 
	 Long acheter_idIf = Long.valueOf( request.getParameter("acheter"));
	System.out.println("achat article id=" + acheter_idIf);
	  
	  Article articleAchat = metierArticle.rechercherArticleIndex(acheter_id);
	  System.out.println("article Achat = " + articleAchat);
	
	/*
	 * System.out.println("acheter un article capté capté"); Long id_banque =
	 * Long.valueOf(request.getParameter("acheterA"));
	 * System.out.println(id_banque);
	 

		/*
		 * String codeBanquaire = String.valueOf(request.getParameter("codeBanquaire"))
		 * ; String pictogramme= String.valueOf(request.getParameter("chiffreSecret")) ;
		 
	
				
			try {
				metierCommande.validationBanquaire(cb_codeBUTTON);
			} catch (Exception e) {
				// TODO Auto-generated catch block
			//	e.printStackTrace();
				metierCommande.setErreur( ATTRIBUT_ERREUR_MAP_CB, e.getMessage() );
				metierCommande.setErreur( ATTRIBUT_ERREUR_MSG, e.getMessage() );
			}
			
			try {
				metierCommande.validationPictogramme(chiffreBUTTON);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				metierCommande.setErreur( ATTRIBUT_ERREUR_MAP_CB, e.getMessage() );
				metierCommande.setErreur( ATTRIBUT_ERREUR_MSG, e.getMessage() );
			
			}

			 Article articleAchatValidation = metierArticle.rechercherArticleIndex(acheter_id);
			 
				
				metierArticle.ajouterArticleAchat(userConnecter, articleAchatValidation);
			 
				//HttpSession session = request.getSession();
			 System.out.println("article Achat = " + articleAchatValidation);
			 request.setAttribute(ATTRIBUT_ARTICLE_ACHAT, articleAchatValidation);
			 sessionAcheter.setAttribute(ATTRIBUT_ARTICLE_ACHAT, articleAchatValidation);
			
			System.out.println(sessionAcheter.getAttribute(ATTRIBUT_ARTICLE_ACHAT));
				
			metierArticle.ajouterArticleAchat(userConnecter, articleAchatValidation);
	
					//metierArticle.ajouterArticleAchat(userSession, articleAchat);
				
				//metierArticle.ajouterArticleAchat(userSession, articleAchat);
				
				
				System.out.println(sessionAcheter.getAttribute(ATTRIBUT_ARTICLE_ACHAT));
				
				}*/

