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
import contenu.metier.article.MetierInterfaceArticle;
import contenu.model.ModelArticleUnique;
import contenu.model.ModelContenu;
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
	
	public static final String ATTRIBUT_ARTICLE_ID      = "acheterArticleId";
    public static final String ATTRIBUT_ARTICLE_ACHAT  = "acheterArticle";
    
    public static final String ATTRIBUT_ERREUR   = "erreurs";
    public static final String ATTRIBUT_ERREUR_MSG   = "msgErreur";   
    public static final String ATTRIBUT_ERREUR_MAP_CB   = "erreursMapCB";
    
    public static final String VUE_ACHETER_ARTICLE = "WEB-INF/interaction/acheterArticle.jsp";
		
	private String erreurMsg;
	
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
		
		HttpSession sessionGET = request.getSession();
		
		/*
		 * 	ModelUser modelUser = new ModelUser(); 
			
			ModelContenu modelContenu = new ModelContenu(); 
		 * List<Article> articles = metierArticle.lireTousArticle();
		 * 
		 * List<Article> articlesSelect = (List<Article>)
		 * modelContenu.addArticle(articleSearch);
		 * 
		 * 
		 * 
		 * articleMap = modelContenu.addArticleMap(userConnecter, articleSearch);
		 * 
		 * modelContenu.addArticle(articleSearch);
		 * 
		 * modelContenu.addArticle(articleSearch);
		 * 
		 * 
		 * modelContenu.setArticles(modelContenu.addArticle(articleSearch) );
		 */
		
		
					
		
			

		  
		  
		 // HttpSession session = request.getSession();
		  
		 // Long user_id = (Long) sessionGET.getAttribute(ATTRIBUT_USER_ID); 
		  //System.out.println("user id connecter est : " + user_id);
		//  User userConnecter = metierArticle.rechercherUserIndex(user_id);
		  
		 // User userConnecter = (User)request.getAttribute(ATTRIBUT_USER_SESSION);
		  User userConnecter = (User)request.getAttribute(ATTRIBUT_USER);
		  System.out.println("user connecter est : " + userConnecter);
		  
		  // IN DO GET
		 // Long acheter_id = Long.valueOf(request.getParameter("acheter"));
		 // System.out.println("achat article id=" + acheter_id); 
		  
		
		  Long acheter_id = (Long) sessionGET.getAttribute(ATTRIBUT_ARTICLE_ID);
		  System.out.println("achat article id=" + acheter_id); 
			
		
		  
		 // request.setAttribute(ATTRIBUT_ARTICLE_ID,acheter_id );
		 // Long acheter_id = Long.valueOf(request.getParameter( ATTRIBUT_ARTICLE_ID));
		 
		  
		  Article articleAchatID =  metierArticle.rechercherArticleIndex(acheter_id);
		 
		  System.out.println("article Achat = " + articleAchatID);
		  
		  
		  
		  
		  ModelArticleUnique modelArticle= new ModelArticleUnique();
		  
		  modelArticle.setArticle(articleAchatID);
		  
		  request.setAttribute("modelArticle", modelArticle);
		
		  
		  System.out.println(" Response getContextPath acheter  Article forward");
	//	response.sendRedirect( request.getContextPath() + "/acheterArticle");
			 
			 
		  
		  System.out.println("Envoi vue acheter article Do Get");
		request.getRequestDispatcher(VUE_ACHETER_ARTICLE).include(request, response);
		  
		//  request.getRequestDispatcher(VUE_ACHETER_ARTICLE).include(request, response);
		  
			
		if(request.getParameter("acheterArticleButton") != null ) {
			
			System.out.println("acheter Button activé. Validation en cours");
			
			String cb_code = (String) request.getParameter("codeBanquaire");
			System.out.println(cb_code);
			String chiffre =request.getParameter("chiffreSecret");
			System.out.println(chiffre);
			
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
				 */
			
					String codeBanquaire = String.valueOf(request.getParameter("codeBanquaire")) ;
					String pictogramme= String.valueOf(request.getParameter("chiffreSecret")) ;
							
				
							
						try {
							metierCommande.validationBanquaire(codeBanquaire);
						} catch (Exception e) {
							// TODO Auto-generated catch block
						//	e.printStackTrace();
							metierCommande.setErreur( ATTRIBUT_ERREUR_MAP_CB, e.getMessage() );
							metierCommande.setErreur( ATTRIBUT_ERREUR_MSG, e.getMessage() );
						}
						
						try {
							metierCommande.validationPictogramme(pictogramme);
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
							
							}
							//request.getRequestDispatcher(VUE_COMMANDE).forward(request, response);
							
		//	metierArticle.
			//metier.ajouterRetard(id);
			
		}
	
				if(request.getParameter("acheterArticleInput") != null ) {
			
			System.out.println("acheter Button activé. Validation en cours");
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
				 */
			
					String codeBanquaire = String.valueOf(request.getParameter("codeBanquaire")) ;
					String pictogramme= String.valueOf(request.getParameter("chiffreSecret")) ;
							
				
							
						try {
							metierCommande.validationBanquaire(codeBanquaire);
						} catch (Exception e) {
							// TODO Auto-generated catch block
						//	e.printStackTrace();
							metierCommande.setErreur( ATTRIBUT_ERREUR_MAP_CB, e.getMessage() );
							metierCommande.setErreur( ATTRIBUT_ERREUR_MSG, e.getMessage() );
						}
						
						try {
							metierCommande.validationPictogramme(pictogramme);
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
							
							}
							//request.getRequestDispatcher(VUE_COMMANDE).forward(request, response);
							
		//	metierArticle.
			//metier.ajouterRetard(id);
			
		}
		
				
		/*
		 * if(request.getParameter("acheter_id") != null ) {
		 * 
		 * System.out.println("acheter id capté"); Long id_art =
		 * Long.valueOf(request.getParameter("acheterA"));
		 * System.out.println("achat article id=" + id_art);
		 * 
		 * Article articleAchatValideId = metierArticle.rechercherArticleIndex(id_art);
		 * System.out.println("article Achat = " + articleAchatValideId);
		 * 
		 * 
		 * System.out.println("article Achat = " + articleAchatValideId);
		 * request.setAttribute(ATTRIBUT_ARTICLE_ACHAT, articleAchatValideId);
		 * session.setAttribute(ATTRIBUT_ARTICLE_ACHAT, articleAchatValideId);
		 * 
		 * System.out.println(session.getAttribute(ATTRIBUT_ARTICLE_ACHAT));
		 * 
		 * 
		 * System.out.println(session.getAttribute(ATTRIBUT_ARTICLE_ACHAT));
		 * 
		 * 
		 * //request.getRequestDispatcher(VUE_COMMANDE).forward(request, response);
		 * 
		 * // metierArticle. //metier.ajouterRetard(id);
		 * 
		 * }
		 */
		
			if(request.getParameter("supprimer") != null ) {
				
				
				
			}
			
			
			
			/*  		if(request.getParameter("acheterA") != null ) {
					System.out.println("acheterA capté");
					Long id_banque = Long.valueOf(request.getParameter("acheterA"));
					System.out.println(id_banque);

					 Article articleAchatValidation = metierArticle.rechercherArticleIndex(id_banque);
					 
					 System.out.println("article Achat = " + articleAchatValidation);
					 request.setAttribute(ATTRIBUT_ARTICLE_ACHAT, articleAchatValidation);
					 session.setAttribute(ATTRIBUT_ARTICLE_ACHAT, articleAchatValidation);
					
					System.out.println(session.getAttribute(ATTRIBUT_ARTICLE_ACHAT));
					 
					metierArticle.ajouterArticleAchat(userConnecter, articleAchatValidation);
						
						//metierArticle.ajouterArticleAchat(userSession, articleAchat);
					
					//metierArticle.ajouterArticleAchat(userSession, articleAchat);
					
					
					System.out.println(session.getAttribute(ATTRIBUT_ARTICLE_ACHAT));
					
					  Article articleAchat = metierArticle.rechercherArticleIndex(id_banque);
					 System.out.println("article Achat = " + articleAchat);
					 request.setAttribute(ATTRIBUT_ARTICLE_ACHAT, articleAchat);
					 session.setAttribute(ATTRIBUT_ARTICLE_ACHAT, articleAchat);
					 
					 System.out.println(session.getAttribute(ATTRIBUT_ARTICLE_ACHAT));
					 
						
						//metierArticle.ajouterArticleAchat(userSession, articleAchat);
						
						
						
						
						request.getRequestDispatcher("/mesAchats").forward(request, response);
					
					//request.getRequestDispatcher(VUE_COMMANDE).forward(request, response);
					
				//	metierArticle.
					//metier.ajouterRetard(id);
					
				} */
			
			
			
/*
			 * if(request.getParameter("acheterArticleButton") != null) {
			 * 
			 * System.out.println("acheter Button activé. Validation en cours");
			 * 
			 * 
			 * 
			 * 
			 * 
			 * System.out.println("acheter un article capté capté"); Long id_banque =
			 * Long.valueOf(request.getParameter("acheterA"));
			 * System.out.println(id_banque);
			 * 
			 * String codeBanquaire = String.valueOf(request.getParameter("codeBanquaire"))
			 * ; String pictogramme= String.valueOf(request.getParameter("chiffreSecret")) ;
			 * 
			 * 
			 * 
			 * try { metierCommande.validationBanquaire(codeBanquaire); } catch (Exception
			 * e) { // TODO Auto-generated catch block // e.printStackTrace();
			 * metierCommande.setErreur( ATTRIBUT_ERREUR_MAP_CB, e.getMessage() );
			 * metierCommande.setErreur( ATTRIBUT_ERREUR_MSG, e.getMessage() ); }
			 * 
			 * try { metierCommande.validationPictogramme(pictogramme); } catch (Exception
			 * e) { // TODO Auto-generated catch block //e.printStackTrace();
			 * metierCommande.setErreur( ATTRIBUT_ERREUR_MAP_CB, e.getMessage() );
			 * metierCommande.setErreur( ATTRIBUT_ERREUR_MSG, e.getMessage() );
			 * 
			 * }
			 * 
			 * Article articleAchatValidation =
			 * metierArticle.rechercherArticleIndex(id_banque);
			 * 
			 * System.out.println("article Achat = " + articleAchatValidation);
			 * request.setAttribute(ATTRIBUT_ARTICLE_ACHAT, articleAchatValidation);
			 * session.setAttribute(ATTRIBUT_ARTICLE_ACHAT, articleAchatValidation);
			 * 
			 * System.out.println(session.getAttribute(ATTRIBUT_ARTICLE_ACHAT));
			 * 
			 * metierArticle.ajouterArticleAchat(userConnecter, articleAchatValidation);
			 * 
			 * //metierArticle.ajouterArticleAchat(userSession, articleAchat);
			 * 
			 * //metierArticle.ajouterArticleAchat(userSession, articleAchat);
			 * 
			 * 
			 * System.out.println(session.getAttribute(ATTRIBUT_ARTICLE_ACHAT)); }
			 */
			
	} // FIN CONDITION
	
} // FIN DO POST
			
		
	

	

