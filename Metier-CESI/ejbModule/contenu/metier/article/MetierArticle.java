package contenu.metier.article;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import contenu.entite.Article;
import contenu.entite.Theme;
import contenu.enume.StatutArticle;
import contenu.persistance.article.PersistanceArticleItf;
import contenu.persistance.theme.PersistanceThemeItf;
import utilisateurs.entite.User;
import utilisateurs.entite.Utilisateur;
import utilisateurs.persistance.PersistanceUserItf;

@Stateless
public class MetierArticle implements MetierInterfaceArticle {

	@EJB
	private PersistanceArticleItf persistanceArticle;

	@EJB
	private PersistanceThemeItf persistanceTheme;
	
	@EJB
	private PersistanceUserItf persistanceUser;

	private Map<Long, Article> articles;

	private Map<Theme, Article> articlesThematiques;

	private Map<String, String> erreurs;

	private String resultat;

	public static final String ATTRIBUT_USER = "utilisateur";
	private final String CHAMP_TITRE = "art_titre";
	private final String CHAMP_URL = "art_url";
	private final String CHAMP_DESCL = "art_description";
	private final String CHAMP_CONTENT = "art_contenu";

	private final String CHAMP_CONF = "confirmation";

	public MetierArticle() {
		System.out.println("Constructeur Metier");
		articles = new HashMap<Long, Article>();

		erreurs = new HashMap<String, String>();

		articlesThematiques = new HashMap<Theme, Article>();
		init();
	}

	public void persisterArticle(Article article) {
		persistanceArticle.persisterArticle(article);
	}

	public void creerArticle(Article article) {
		articles.put(article.getId(), article);
	}

	public Article lireArticle(Long id) {
		return articles.get(id);
	}

	public void mettreAJourArticle(Article article) {
		articles.put(article.getId(), article);
	}

	public void supprimerArticle(Article article) {
		articles.remove(article.getId());
	}

	public List<Article> lireTousArticle() {
		// return new ArrayList<>(etudiants.values());

		return persistanceArticle.lireTousArticle();
	}

	private void init() {
		System.out.println("Metier - init");

		/*
		 * Article article = new
		 * Article("Math","Le mond est indéfini, les inconnus sont multiples",
		 * "puissant le philosophe du dimanche qui fait le mec qui parle des Math zerma"
		 * ); persistanceArticle.persisterArticle(article);
		 * 
		 * Article article1 = new Article("Info","2020 c'est pas des lol",
		 * "ça taquine en bombe"); persistanceArticle.persisterArticle(article1);
		 */

	}
	
	@Override
	public Article creerArticleUserRequestSession(HttpServletRequest request, User userParams, HttpSession session) {
		// TODO Auto-generated method stub

		System.out.println("Creer article User Request Session");
		
		//System.out.println("User paramse : " + userParams.toString() );
		
	Enumeration<String> sessionAttributs = session.getAttributeNames(); System.out.println(sessionAttributs);
	
	
	String loginUser = (String) session.getAttribute("userLogin");
	System.err.println("Session Login User : "+ loginUser);
	
	Long user_id = (Long) session.getAttribute("userId");
	System.err.println("Session USER_ID : "+ user_id);

	Cookie[] cookies = request.getCookies();

	

	
		System.out.println("Request user are : " + request.getAttribute("utilisateur"));
		
		System.out.println("Session user are : " + session.getAttribute("utilisateur"));

	//	User userTest = (User) session.getAttributeNames(ATTRIBUT_USER);
	//	System.out.println("Session ARGS ALL VALUES : " + ((Object) session).getValues());
		
		String art_titre = request.getParameter("art_titre");
		String art_url = request.getParameter("art_url");

		String art_description = request.getParameter("art_description");
		String art_contenu = request.getParameter("art_contenu");
		String art_frais = request.getParameter("art_frais");
		String art_prix = request.getParameter("art_prix");

		Article article = new Article();

		System.out.println("Test des exceptions du formulaire a partir du MEtier");

		System.out.println("Test  metier : titre ");
		/* Validation du champ username */
		try {
			validationTitre(art_titre);
		} catch (Exception e) {
			this.setErreur(CHAMP_TITRE, e.getMessage());
		}
		article.setTitre(art_titre);

		/*
		 * System.out.println("Test  metier : url "); Validation du champ username try {
		 * validationUrl(art_url);
		 * 
		 * } catch ( Exception e ) { this.setErreur( CHAMP_URL, e.getMessage() ); }
		 */
		article.setUrl(art_url);

		System.out.println("Test  metier : description ");
		/* Validation du champ username */
		try {
			validationDescription(art_description);

		} catch (Exception e) {
			setErreur(CHAMP_DESCL, e.getMessage());
		}
		article.setDescription(art_description);

		System.out.println("Test  metier : Contenu");
		/* Validation du champ mot de passe. */
		try {
			validationContenu(art_contenu);
		} catch (Exception e) {
			setErreur(CHAMP_CONTENT, e.getMessage());
		}
		article.setContenu(art_contenu);

		if (art_frais != null) {
			article.setFrais(art_frais);
		}

		if (art_prix != null) {
			article.setPrix(art_prix);
		}

		article.setStatus(StatutArticle.DISPONIBLE);
		/*
		 * User userSession = (User)session.getAttribute(ATTRIBUT_USER);
		 * System.out.println("userSession are  + " + userSession.toString() + "login" +
		 * userSession.getLogin() + "id"+ userSession.getUser_id());
		 * article.setUser_vendeur(userParams);
		 * System.out.println(article.getUser_vendeur()); article.addUser(userParams);
		 * System.out.println(article.getUser_vendeur());
		 * 
		 * article.setUser_Id(userParams.getUser_id());
		 */

		/* Initialisation du résultat global de la validation. */
		if (erreurs.isEmpty() || (!article.getTitre().isEmpty() && !article.getDescription().isEmpty()
				&& !article.getContenu().isEmpty())) {
			// || ( !article.getArt_titre().isEmpty() && !article.getArt_url().isEmpty() &&
			// !article.getArt_description().isEmpty() &&
			// !article.getArt_contenu().isEmpty() )
			this.setResultat("Succès dépot Article");
			this.resultat = "Succès du dépot Article";

			resultat = "Succès de la connexion.";
			System.out.println("Succes du dépot Article ");
			System.out.println("Erreurs : " + erreurs);
			
			System.out.println("Persister Article in Metier User Index Method");

			persistanceArticle.persisterArticle(article);
			
		//	User userBDD = (User)persistanceUser.selectLoginUser(loginUser); System.out.println("User BDD select are : " + userBDD); System.out.println("User BDD  ID select are : " + userBDD.getUser_id( ));
			
			
			
			
	//		User userLectureBDD = (User)persistanceUser.lireLoginUser(loginUser); System.out.println("User BDD select are : " + userLectureBDD); System.out.println("User BDD  ID select are : " + userLectureBDD.getUser_id( ));
			
			
			User userLectureBDD = (User)persistanceUser.lireUser(user_id); System.out.println("Lire User BDD select are : " + userLectureBDD); System.out.println("User BDD  ID select are : " + userLectureBDD.getUser_id( ));
			
			
			System.out.println("updateArticle");
			
			persistanceArticle.updateArticleWithUser(article, userLectureBDD);

			persistanceArticle.insertIntoVenteUser(userLectureBDD, article);
			
			//persistanceArticle.insertionJoinTableIdAndKey(userLectureBDD.getUser_id() ,	article.getId());
			
		
			
			
			/*
			 * User userRechercher = (User) rechercherUserLogin(loginUser);
			 * System.out.println("User BDD select are : " + userRechercher);
			 * System.out.println("User BDD  ID select are : " + userRechercher.getUser_id(
			 * ));
			 * 
			 * System.out.println("updateArticle");
			 * 
			 * persistanceArticle.insertionJoinTableIdAndKey(userRechercher.getUser_id() ,
			 * article.getId());
			 * 
			 * persistanceArticle.updateArticleWithUser(article, userRechercher);
			 * 
			 * 
			 * User userRechercherIndex = (User) rechercherUserIndex(user_id);
			 * System.out.println("User BDD select are : " + userRechercher);
			 * System.out.println("User BDD  ID select are : " +
			 * userRechercherIndex.getUser_id( ));
			 * 
			 * 
			 * System.out.println("updateArticle");
			 * 
			 * persistanceArticle.insertionJoinTableIdAndKey(userRechercherIndex.getUser_id(
			 * ) , article.getId());
			 * 
			 * persistanceArticle.updateArticleWithUser(article, userRechercherIndex);
			 */
			
			
			/*
			 * System.out.println("insertion Join Table ID and Key");
			 * 
			 * persistanceArticle.insertionJoinTableIdAndKey(userBDD.getUser_id() ,
			 * article.getId());
			 * 
			 * persistanceArticle.updateArticleWithUser(article, userBDD);
			 */
			
			
			
			/*
			 * System.out.println("insertion Join Table ID and Key");
			 * 
			 * persistanceArticle.insertionJoinTableIdAndKey(userParams.getUser_id() ,
			 * article.getId());
			 * 
			 * persistanceArticle.updateArticleWithUser(article, userParams);
			 */
			

			/*
			 * System.out.println("Persister Article in User Method");
			 * 
			 * persistanceArticle.persisterArticleInUser(userParams, article);
			 * 
			 * System.out.println("Persister Article in User Method");
			 * 
			 * persistanceArticle.persisterArticle(article);
			 */

			// updateArticleWithUser(article, userParams);
			// persistanceArticle.persisterUserArticle(userParams, article);
			// persistanceArticle.persisterArticleInUser(userParams, article);
			/*
			 * System.out.println("Updadte Article column");
			 * 
			 * persistanceArticle.updateArticleWithUser(article, userParams);
			 * 
			 * 
			 * System.out.println("Inssert into Vender with List");
			 * 
			 * persistanceArticle.insertIntoVenteUser(userParams, article);
			 * 
			 * 
			 * 
			 * System.out.println("Insertion Join table Object");
			 * 
			 * persistanceArticle.insertionJoinTableObject(userParams, article);
			 * 
			 * 
			 * System.out.println("Insertion Join table Index");
			 * 
			 * persistanceArticle.insertionJoinTableId(userParams.getUser_id(),
			 * article.getId());
			 * 
			 * 
			 * 
			 * System.out.println("Persister UserAndArticle Method");
			 */
			// persistanceArticle.persisterUserAndArticle(userParams, article);
			//

			// insertJoinArticleUserWithQuery(userParams, article);

			// persistanceArticle.persisterUserAndArticle(userParams, article);

			System.out.println("Persister Article OK ");
			return article;
			
		} else {
			resultat = "Échec de la connexion.";

			this.setResultat(resultat);

			System.out.println("Echec de la connexion ");
			System.out.println("Erreur : " + erreurs);
		}

		// return user;
		return article;
	}

	@Override
	public Article creerArticleUser(HttpServletRequest request, User userParams) {
		// TODO Auto-generated method stub

		System.out.println("Creer article User");
		System.out.println("Request user are : " + request.getAttribute("utilisateur"));

		String art_titre = request.getParameter("art_titre");
		String art_url = request.getParameter("art_url");

		String art_description = request.getParameter("art_description");
		String art_contenu = request.getParameter("art_contenu");
		String art_frais = request.getParameter("art_frais");
		String art_prix = request.getParameter("art_prix");

		Article article = new Article();

		System.out.println("Test des exceptions du formulaire a partir du MEtier");

		System.out.println("Test  metier : titre ");
		/* Validation du champ username */
		try {
			validationTitre(art_titre);
		} catch (Exception e) {
			this.setErreur(CHAMP_TITRE, e.getMessage());
		}
		article.setTitre(art_titre);

		/*
		 * System.out.println("Test  metier : url "); Validation du champ username try {
		 * validationUrl(art_url);
		 * 
		 * } catch ( Exception e ) { this.setErreur( CHAMP_URL, e.getMessage() ); }
		 */
		article.setUrl(art_url);

		System.out.println("Test  metier : description ");
		/* Validation du champ username */
		try {
			validationDescription(art_description);

		} catch (Exception e) {
			setErreur(CHAMP_DESCL, e.getMessage());
		}
		article.setDescription(art_description);

		System.out.println("Test  metier : Contenu");
		/* Validation du champ mot de passe. */
		try {
			validationContenu(art_contenu);
		} catch (Exception e) {
			setErreur(CHAMP_CONTENT, e.getMessage());
		}
		article.setContenu(art_contenu);

		if (art_frais != null) {
			article.setFrais(art_frais);
		}

		if (art_prix != null) {
			article.setPrix(art_prix);
		}

		article.setUser_vendeur(userParams);
		System.out.println(article.getUser_vendeur());
		article.addUser(userParams);
		System.out.println(article.getUser_vendeur());

		article.setUser_Id(userParams.getUser_id());

		/* Initialisation du résultat global de la validation. */
		if (erreurs.isEmpty() || (!article.getTitre().isEmpty() && !article.getDescription().isEmpty()
				&& !article.getContenu().isEmpty())) {
			// || ( !article.getArt_titre().isEmpty() && !article.getArt_url().isEmpty() &&
			// !article.getArt_description().isEmpty() &&
			// !article.getArt_contenu().isEmpty() )
			this.setResultat("Succès dépot Article");
			this.resultat = "Succès du dépot Article";

			resultat = "Succès de la connexion.";
			System.out.println("Succes du dépot Article ");
			System.out.println("Erreurs : " + erreurs);
			
			System.out.println("Persister Article in Metier User Index Method");

			persistanceArticle.persisterArticle(article);
			
			
			System.out.println("insertion Join Table ID and Key");

			persistanceArticle.insertionJoinTableIdAndKey(userParams.getUser_id() ,	article.getId());
					
			persistanceArticle.updateArticleWithUser(article, userParams);
			

			/*
			 * System.out.println("Persister Article in User Method");
			 * 
			 * persistanceArticle.persisterArticleInUser(userParams, article);
			 * 
			 * System.out.println("Persister Article in User Method");
			 * 
			 * persistanceArticle.persisterArticle(article);
			 */

			// updateArticleWithUser(article, userParams);
			// persistanceArticle.persisterUserArticle(userParams, article);
			// persistanceArticle.persisterArticleInUser(userParams, article);
			/*
			 * System.out.println("Updadte Article column");
			 * 
			 * persistanceArticle.updateArticleWithUser(article, userParams);
			 * 
			 * 
			 * System.out.println("Inssert into Vender with List");
			 * 
			 * persistanceArticle.insertIntoVenteUser(userParams, article);
			 * 
			 * 
			 * 
			 * System.out.println("Insertion Join table Object");
			 * 
			 * persistanceArticle.insertionJoinTableObject(userParams, article);
			 * 
			 * 
			 * System.out.println("Insertion Join table Index");
			 * 
			 * persistanceArticle.insertionJoinTableId(userParams.getUser_id(),
			 * article.getId());
			 * 
			 * 
			 * 
			 * System.out.println("Persister UserAndArticle Method");
			 */
			// persistanceArticle.persisterUserAndArticle(userParams, article);
			//

			// insertJoinArticleUserWithQuery(userParams, article);

			// persistanceArticle.persisterUserAndArticle(userParams, article);

			System.out.println("Persister Article OK ");
			return article;
		} else {
			resultat = "Échec de la connexion.";

			this.setResultat(resultat);

			System.out.println("Echec de la connexion ");
			System.out.println("Erreur : " + erreurs);
		}

		// return user;
		return article;
	}

	@Override
	public Article creerArticleUserIndex(HttpServletRequest request, Long user_id) {
		// TODO Auto-generated method stub

		String art_titre = request.getParameter("art_titre");
		String art_url = request.getParameter("art_url");

		String art_description = request.getParameter("art_description");
		String art_contenu = request.getParameter("art_contenu");
		String art_frais = request.getParameter("art_frais");
		String art_prix = request.getParameter("art_prix");

		Article article = new Article();

		System.out.println("Test des exceptions du formulaire a partir du MEtier");

		System.out.println("Test  metier : titre ");
		/* Validation du champ username */
		try {
			validationTitre(art_titre);
		} catch (Exception e) {
			this.setErreur(CHAMP_TITRE, e.getMessage());
		}
		article.setTitre(art_titre);

		/*
		 * System.out.println("Test  metier : url "); Validation du champ username try {
		 * validationUrl(art_url);
		 * 
		 * } catch (Exception e) { this.setErreur(CHAMP_URL, e.getMessage()); }
		 */
		article.setUrl(art_url);

		System.out.println("Test  metier : description ");
		/* Validation du champ username */
		try {
			validationDescription(art_description);

		} catch (Exception e) {
			setErreur(CHAMP_DESCL, e.getMessage());
		}
		article.setDescription(art_description);

		System.out.println("Test  metier : Contenu");
		/* Validation du champ mot de passe. */
		try {
			validationContenu(art_contenu);
		} catch (Exception e) {
			setErreur(CHAMP_CONTENT, e.getMessage());
		}
		article.setContenu(art_contenu);

		if (art_frais != null) {
			article.setFrais(art_frais);
		}

		if (art_prix != null) {
			article.setPrix(art_prix);
		}

		
		 // article.setUser_vendeur(user_id);
		  System.out.println(article.getUser_vendeur());

		  article.addUser(user_id);
		  // System.out.println(article.getUser_id());
		  article.setUser_Id(user_id);
		  System.out.println(article.getUser_vendeur());

		/* Initialisation du résultat global de la validation. */
		if (erreurs.isEmpty() || (!article.getTitre().isEmpty() && !article.getDescription().isEmpty()
				&& !article.getContenu().isEmpty())) {
			// || ( !article.getArt_titre().isEmpty() && !article.getArt_url().isEmpty() &&
			// !article.getArt_description().isEmpty() &&
			// !article.getArt_contenu().isEmpty() )
			this.setResultat("Succès dépot Article");
			this.resultat = "Succès du dépot Article";

			resultat = "Succès de la connexion.";
			System.out.println("Succes du dépot Article ");
			System.out.println("Erreurs : " + erreurs);

			
			// persistanceArticle.persisterArticleInUser(userParams, article);

			System.out.println("Persister Article in Metier User Index Method");

			persistanceArticle.persisterArticle(article);
			
			
			System.out.println("insertion Join Table ID and Key");

			persistanceArticle.insertionJoinTableIdAndKey(user_id,
					article.getId());
			
		
			
			 

			// updateArticleWithUser(article, userParams);
			// persistanceArticle.persisterUserArticle(userParams, article);
			// persistanceArticle.persisterArticleInUser(userParams, article);
			/*
			 * System.out.println("Updadte Article column");
			 * 
			 * persistanceArticle.updateArticleWithUser(article, userParams);
			 * 
			 * 
			 * System.out.println("Inssert into Vender with List");
			 * 
			 * persistanceArticle.insertIntoVenteUser(userParams, article);
			 * 
			 * 
			 * 
			 * System.out.println("Insertion Join table Object");
			 * 
			 * persistanceArticle.insertionJoinTableObject(userParams, article);
			 * 
			 * 
			 * System.out.println("Insertion Join table Index");
			 * 
			 * persistanceArticle.insertionJoinTableId(userParams.getUser_id(),
			 * article.getId());
			 * 
			 * 
			 * 
			 * System.out.println("Persister UserAndArticle Method");
			 */
			// persistanceArticle.persisterUserAndArticle(userParams, article);
			//

			// insertJoinArticleUserWithQuery(userParams, article);

			// persistanceArticle.persisterUserAndArticle(userParams, article);

			System.out.println("Persister Article OK ");
			return article;
		} else {
			resultat = "Échec de la connexion.";

			this.setResultat(resultat);

			System.out.println("Echec de la connexion ");
			System.out.println("Erreur : " + erreurs);
		}

		// return user;
		return article;
	}

	/* EXEMPLE */

	public Article creerArticleUserExemple(HttpServletRequest request, User userParams) {
		// TODO Auto-generated method stub

		String art_titre = request.getParameter("art_titre");
		String art_url = request.getParameter("art_url");

		String art_description = request.getParameter("art_description");
		String art_contenu = request.getParameter("art_contenu");
		String art_frais = request.getParameter("art_frais");
		String art_prix = request.getParameter("art_prix");

		Article article = new Article();

		System.out.println("Test des exceptions du formulaire a partir du MEtier");

		System.out.println("Test  metier : titre ");
		/* Validation du champ username */
		try {
			validationTitre(art_titre);
		} catch (Exception e) {
			this.setErreur(CHAMP_TITRE, e.getMessage());
		}
		article.setTitre(art_titre);

		System.out.println("Test  metier : url ");
		/* Validation du champ username */
		try {
			validationUrl(art_url);

		} catch (Exception e) {
			this.setErreur(CHAMP_URL, e.getMessage());
		}
		article.setUrl(art_url);

		System.out.println("Test  metier : description ");
		/* Validation du champ username */
		try {
			validationDescription(art_description);

		} catch (Exception e) {
			setErreur(CHAMP_DESCL, e.getMessage());
		}
		article.setDescription(art_description);

		System.out.println("Test  metier : Contenu");
		/* Validation du champ mot de passe. */
		try {
			validationContenu(art_contenu);
		} catch (Exception e) {
			setErreur(CHAMP_CONTENT, e.getMessage());
		}
		article.setContenu(art_contenu);

		if (art_frais != null) {
			article.setFrais(art_frais);
		}

		if (art_prix != null) {
			article.setPrix(art_prix);
		}

		article.setUser_vendeur(userParams);

		/* Initialisation du résultat global de la validation. */
		if (erreurs.isEmpty() || (!article.getTitre().isEmpty() && !article.getDescription().isEmpty()
				&& !article.getContenu().isEmpty())) {
			// || ( !article.getArt_titre().isEmpty() && !article.getArt_url().isEmpty() &&
			// !article.getArt_description().isEmpty() &&
			// !article.getArt_contenu().isEmpty() )
			this.setResultat("Succès dépot Article");
			this.resultat = "Succès du dépot Article";

			resultat = "Succès de la connexion.";
			System.out.println("Succes du dépot Article ");
			System.out.println("Erreurs : " + erreurs);

			System.out.println("Persister Article in User Method");

			persistanceArticle.persisterArticle(article);

			// updateArticleWithUser(article, userParams);
			// persistanceArticle.persisterUserArticle(userParams, article);
			// persistanceArticle.persisterArticleInUser(userParams, article);

			/*
			 * System.out.println("Insertion Join table Object");
			 * 
			 * persistanceArticle.insertionJoinTableObject(userParams, article);
			 * 
			 * 
			 * System.out.println("Insertion Join table Index");
			 * 
			 * persistanceArticle.insertionJoinTableId(userParams.getUser_id(),
			 * article.getId());
			 */

			System.out.println("Persister UserAndArticle Method");
			// persistanceArticle.persisterUserAndArticle(userParams, article);
			//

			// insertJoinArticleUserWithQuery(userParams, article);

			// persistanceArticle.persisterUserAndArticle(userParams, article);

			System.out.println("Persister Article OK ");
			return article;
		} else {
			resultat = "Échec de la connexion.";

			this.setResultat(resultat);

			System.out.println("Echec de la connexion ");
			System.out.println("Erreur : " + erreurs);
		}

		// return user;
		return article;
	}

	@Override
	public Article creerArticleRequestSession(HttpServletRequest request, HttpSession session) {
		// TODO Auto-generated method stub

		String art_titre = request.getParameter("art_titre");
		String art_url = request.getParameter("art_url");

		String art_description = request.getParameter("art_description");
		String art_contenu = request.getParameter("art_contenu");

		String art_frais = request.getParameter("art_frais");
		String art_prix = request.getParameter("art_prix");
		Article article = new Article();

		System.out.println("Test des exceptions du formulaire a partir du MEtier");

		System.out.println("Test  metier : titre ");
		/* Validation du champ username */
		try {
			validationTitre(art_titre);
		} catch (Exception e) {
			this.setErreur(CHAMP_TITRE, e.getMessage());
		}
		article.setTitre(art_titre);

		System.out.println("Test  metier : url ");
		/* Validation du champ username */
		try {
			validationUrl(art_url);

		} catch (Exception e) {
			this.setErreur(CHAMP_URL, e.getMessage());
		}
		article.setUrl(art_url);

		System.out.println("Test  metier : description ");
		/* Validation du champ username */
		try {
			validationDescription(art_description);

		} catch (Exception e) {
			setErreur(CHAMP_DESCL, e.getMessage());
		}
		article.setDescription(art_description);

		System.out.println("Test  metier : Contenu");
		/* Validation du champ mot de passe. */
		try {
			validationContenu(art_contenu);
		} catch (Exception e) {
			setErreur(CHAMP_CONTENT, e.getMessage());
		}
		article.setContenu(art_contenu);

		// session.
		User userSession = (User) session.getAttribute(ATTRIBUT_USER);

		HttpSession sessionServlet = request.getSession();
		Principal userPrincipal = request.getUserPrincipal();

		// article.setUser_vendeur(userSession);

		// || ( !utilisateur.getLogin().isEmpty() &&
		// !utilisateur.getMotDePasse().isEmpty() && !utilisateur.getEmail().isEmpty()

		/* Initialisation du résultat global de la validation. */
		if (erreurs.isEmpty() || (!article.getTitre().isEmpty() && !article.getDescription().isEmpty()
				&& !article.getContenu().isEmpty())) {
			// || ( !article.getArt_titre().isEmpty() && !article.getArt_url().isEmpty() &&
			// !article.getArt_description().isEmpty() &&
			// !article.getArt_contenu().isEmpty() )
			this.setResultat("Succès dépot Article");
			this.resultat = "Succès du dépot Article";

			resultat = "Succès de la connexion.";
			System.out.println("Succes du dépot Article ");
			System.out.println("Erreurs : " + erreurs);
			persistanceArticle.persisterArticle(article);

			System.out.println("Persister utilisateur OK ");
		} else {
			resultat = "Échec de la connexion.";

			this.setResultat(resultat);

			System.out.println("Echec de la connexion ");
			System.out.println("Erreur : " + erreurs);
		}

		// return user;
		return article;
	}

	@Override
	public Article creerArticle(HttpServletRequest request) {
		// TODO Auto-generated method stub

		String art_titre = request.getParameter("art_titre");
		String art_url = request.getParameter("art_url");

		String art_description = request.getParameter("art_description");
		String art_contenu = request.getParameter("art_contenu");
		Article article = new Article();

		System.out.println("Test des exceptions du formulaire a partir du MEtier");

		System.out.println("Test  metier : titre ");
		/* Validation du champ username */
		try {
			validationTitre(art_titre);
		} catch (Exception e) {
			this.setErreur(CHAMP_TITRE, e.getMessage());
		}
		article.setTitre(art_titre);

		System.out.println("Test  metier : url ");
		/* Validation du champ username */
		try {
			validationUrl(art_url);

		} catch (Exception e) {
			this.setErreur(CHAMP_URL, e.getMessage());
		}
		article.setUrl(art_url);

		System.out.println("Test  metier : description ");
		/* Validation du champ username */
		try {
			validationDescription(art_description);

		} catch (Exception e) {
			setErreur(CHAMP_DESCL, e.getMessage());
		}
		article.setDescription(art_description);

		System.out.println("Test  metier : Contenu");
		/* Validation du champ mot de passe. */
		try {
			validationContenu(art_contenu);
		} catch (Exception e) {
			setErreur(CHAMP_CONTENT, e.getMessage());
		}
		article.setContenu(art_contenu);

		// || ( !utilisateur.getLogin().isEmpty() &&
		// !utilisateur.getMotDePasse().isEmpty() && !utilisateur.getEmail().isEmpty()

		/* Initialisation du résultat global de la validation. */
		if (erreurs.isEmpty() || (!article.getTitre().isEmpty() && !article.getDescription().isEmpty()
				&& !article.getContenu().isEmpty())) {
			// || ( !article.getArt_titre().isEmpty() && !article.getArt_url().isEmpty() &&
			// !article.getArt_description().isEmpty() &&
			// !article.getArt_contenu().isEmpty() )
			this.setResultat("Succès dépot Article");
			this.resultat = "Succès du dépot Article";

			resultat = "Succès de la connexion.";
			System.out.println("Succes du dépot Article ");
			System.out.println("Erreurs : " + erreurs);
			persistanceArticle.persisterArticle(article);

			System.out.println("Persister utilisateur OK ");
		} else {
			resultat = "Échec de la connexion.";

			this.setResultat(resultat);

			System.out.println("Echec de la connexion ");
			System.out.println("Erreur : " + erreurs);
		}

		// return user;
		return article;
	}

	@Override
	public void validationTitre(String titre) throws Exception {
		// TODO Auto-generated method stub
		if (titre != null) {
			if (titre.length() < 3) {
				throw new Exception("Le titre doit contenir au moins 3 caractères.");
			}
			/*
			 * else if(login.get(int i).equals(int.class) ) {
			 * 
			 * } else if(login.con) {
			 * 
			 * }
			 */
		} else {
			throw new Exception("Merci de saisir un titre plus précis.");
		}
	}

	@Override
	public void validationUrl(String url) throws Exception {
		if (url != null) {
			if (url.length() < 3) {
				throw new Exception("Le titre doit contenir au moins 3 caractères.");
			}
			/*
			 * else if(login.get(int i).equals(int.class) ) {
			 * 
			 * } else if(login.con) {
			 * 
			 * }
			 */
		} else {
			throw new Exception("Merci de saisir un titre plus précis.");
		}
	}

	@Override
	public void validationDescription(String description) throws Exception {
		// TODO Auto-generated method stub
		if (description != null) {
			if (description.length() < 3) {
				throw new Exception("Le titre doit contenir au moins 3 caractères.");
			}
			/*
			 * else if(login.get(int i).equals(int.class) ) {
			 * 
			 * } else if(login.con) {
			 * 
			 * }
			 */
		} else {
			throw new Exception("Merci de saisir un titre plus précis.");
		}
	}

	@Override
	public void validationContenu(String contenu) throws Exception {
		// TODO Auto-generated method stub
		if (contenu != null) {
			if (contenu.length() < 3) {
				throw new Exception("Le contenu doit contenir au moins 3 caractères.");
			}
			/*
			 * else if(login.get(int i).equals(int.class) ) {
			 * 
			 * } else if(login.con) {
			 * 
			 * }
			 */
		} else {
			throw new Exception("Merci de saisir votre mot de passe.");
		}
	}

	@Override
	public void validationTheme(String theme) throws Exception {
		// TODO Auto-generated method stub
		if (theme != null) {
			if (theme.length() < 3) {
				throw new Exception("Le titre doit contenir au moins 3 caractères.");
			}
			/*
			 * else if(login.get(int i).equals(int.class) ) {
			 * 
			 * } else if(login.con) {
			 * 
			 * }
			 */
		} else {
			throw new Exception("Merci de saisir un titre plus précis.");
		}
	}

	@Override
	public void validationSkills(String skills) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void setErreur(String champ, String message) {
		// TODO Auto-generated method stub
		erreurs.put(champ, message);
	}

	@Override
	public Map<String, String> getErreurs() {
		// TODO Auto-generated method stub
		return erreurs;
	}

	@Override
	public String getResultat() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getValeurChamp(HttpServletRequest request, String nomChamp) {
		String valeur = request.getParameter(nomChamp);
		if (valeur == null || valeur.trim().length() == 0) {
			return null;
		} else {
			return valeur;
		}
	}

	public void setResultat(String resultat) {
		this.resultat = resultat;
	}

	/*
	 * @Override public User connecterUser(HttpServletRequest request) { // TODO
	 * Auto-generated method stub return null; }
	 * 
	 */

	/*
	 * public void setErreurs(Map<String, String> erreurs) { this.erreurs = erreurs;
	 * }
	 */

	/*
	 * public void persisterEtudiantPromotion(String acronyme,Etudiant etudiant) {
	 * System.out.println("Metier - inscrireEtudiantPromotion acronyme=" + acronyme
	 * + " etudiant=" + etudiant); etudiant.setId(idEtudiant);
	 * etudiants.put(idEtudiant, etudiant); idEtudiant++; Promotion promotion =
	 * promotions.get(acronyme); promotion.addEtudiant(etudiant); }
	 * 
	 * 
	 * public void inscrireEtudiantPromotion(String acronyme,Etudiant etudiant) {
	 * System.out.println("Metier - inscrireEtudiantPromotion acronyme=" + acronyme
	 * + " etudiant=" + etudiant); etudiant.setId(idEtudiant);
	 * etudiants.put(idEtudiant, etudiant); idEtudiant++; Promotion promotion =
	 * promotions.get(acronyme); promotion.addEtudiant(etudiant); }
	 */

	/*
	 * public void ajouterRetard(Long index) {
	 * 
	 * //etudiants.get(index).getRetard().setRetard(retard++); //Metier metier =
	 * null; System.out.println("id etudiant=" + index); Etudiant etudiant =
	 * lireEtudiant(index);
	 * 
	 * System.out.println(etudiant); etudiant.setRetard(etudiant.getRetard()+1);
	 * System.out.println(etudiant);
	 * 
	 * }
	 * 
	 * public void ajouterAbsence(Long id) { System.out.println("id etudiant=" +
	 * id); Etudiant etudiant = lireEtudiant(id); System.out.println(etudiant);
	 * etudiant.setAbsence(etudiant.getAbsence()+1); System.out.println(etudiant);
	 * /*etudiants.get(index).getRetard().setRetard(retard++); int absence=
	 * etudiant.getRetard(); etudiant.setAbsence(absence++); }
	 */

	@Override
	public void insertJoinArticleUserWithQuery(User user, Article article) {
		// TODO Auto-generated method stub
		persistanceArticle.insertJoinArticleUserWithQuery(user, article);
	}

	@Override
	public void insertJoinArticleUserWithQueryIndex(Long user_id, Long id) {
		// TODO Auto-generated method stub
		persistanceArticle.insertJoinArticleUserWithQueryIndex(user_id, id);
	}

	@Override
	public void updateArticleWithUser(Article article, User user) {
		persistanceArticle.updateArticleWithUser(article, user);

	}

	@Override
	public void insertIntoVenteUser(User user, Article article) {
		// TODO Auto-generated method stub
		persistanceArticle.insertIntoVenteUser(user, article);
	}

	@Override
	public void insertionJoinTableId(Long userid, Long articleId) {
		// TODO Auto-generated method stub
		persistanceArticle.insertionJoinTableId(userid, articleId);
	}

	@Override
	public void insertionJoinTableObject(User user, Article article) {
		// TODO Auto-generated method stub
		persistanceArticle.insertionJoinTableObject(user, article);
	}
	
	
	@Override 
	public User rechercherUserLogin(String login){
		
		return persistanceUser.rechercherUserLogin(login);
	}

	@Override
	public Article creerArticleRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		System.out.println("Creer article User");
		System.out.println("Request user are : " + request.getAttribute("utilisateur"));

		String art_titre = request.getParameter("art_titre");
		String art_url = request.getParameter("art_url");

		String art_description = request.getParameter("art_description");
		String art_contenu = request.getParameter("art_contenu");
		String art_frais = request.getParameter("art_frais");
		String art_prix = request.getParameter("art_prix");

		Article article = new Article();

		System.out.println("Test des exceptions du formulaire a partir du MEtier");

		System.out.println("Test  metier : titre ");
		/* Validation du champ username */
		try {
			validationTitre(art_titre);
		} catch (Exception e) {
			this.setErreur(CHAMP_TITRE, e.getMessage());
		}
		article.setTitre(art_titre);

		System.out.println("Test  metier : url ");
		/* Validation du champ username */
		try {
			validationUrl(art_url);

		} catch (Exception e) {
			this.setErreur(CHAMP_URL, e.getMessage());
		}
		article.setUrl(art_url);

		System.out.println("Test  metier : description ");
		/* Validation du champ username */
		try {
			validationDescription(art_description);

		} catch (Exception e) {
			setErreur(CHAMP_DESCL, e.getMessage());
		}
		article.setDescription(art_description);

		System.out.println("Test  metier : Contenu");
		/* Validation du champ mot de passe. */
		try {
			validationContenu(art_contenu);
		} catch (Exception e) {
			setErreur(CHAMP_CONTENT, e.getMessage());
		}
		article.setContenu(art_contenu);

		if (art_frais != null) {
			article.setFrais(art_frais);
		}

		if (art_prix != null) {
			article.setPrix(art_prix);
		}

		User userParams = (User) request.getAttribute(ATTRIBUT_USER);
		article.setUser_vendeur(userParams);
		System.out.println(article.getUser_vendeur());
		article.addUser(userParams);
		System.out.println(article.getUser_vendeur());

		article.setUser_Id(userParams.getUser_id());

		/* Initialisation du résultat global de la validation. */
		if (erreurs.isEmpty() || (!article.getTitre().isEmpty() && !article.getDescription().isEmpty()
				&& !article.getContenu().isEmpty())) {
			// || ( !article.getArt_titre().isEmpty() && !article.getArt_url().isEmpty() &&
			// !article.getArt_description().isEmpty() &&
			// !article.getArt_contenu().isEmpty() )
			this.setResultat("Succès dépot Article");
			this.resultat = "Succès du dépot Article";

			resultat = "Succès de la connexion.";
			System.out.println("Succes du dépot Article ");
			System.out.println("Erreurs : " + erreurs);

			System.out.println("Persister Article in User Method");

			persistanceArticle.persisterArticleInUser(userParams, article);

			System.out.println("Persister Article in User Method");

			persistanceArticle.persisterArticle(article);

			// updateArticleWithUser(article, userParams);
			// persistanceArticle.persisterUserArticle(userParams, article);
			// persistanceArticle.persisterArticleInUser(userParams, article);
			/*
			 * System.out.println("Updadte Article column");
			 * 
			 * persistanceArticle.updateArticleWithUser(article, userParams);
			 * 
			 * 
			 * System.out.println("Inssert into Vender with List");
			 * 
			 * persistanceArticle.insertIntoVenteUser(userParams, article);
			 * 
			 * 
			 * 
			 * System.out.println("Insertion Join table Object");
			 * 
			 * persistanceArticle.insertionJoinTableObject(userParams, article);
			 * 
			 * 
			 * System.out.println("Insertion Join table Index");
			 * 
			 * persistanceArticle.insertionJoinTableId(userParams.getUser_id(),
			 * article.getId());
			 * 
			 * 
			 * 
			 * System.out.println("Persister UserAndArticle Method");
			 */
			// persistanceArticle.persisterUserAndArticle(userParams, article);
			//

			// insertJoinArticleUserWithQuery(userParams, article);

			// persistanceArticle.persisterUserAndArticle(userParams, article);

			System.out.println("Persister Article OK ");
			return article;
		} else {
			resultat = "Échec de la connexion.";

			this.setResultat(resultat);

			System.out.println("Echec de la connexion ");
			System.out.println("Erreur : " + erreurs);
		}

		// return user;
		return article;
	}

	@Override
	public User rechercherUserIndex(Long index) {
		// TODO Auto-generated method stub
		return persistanceUser.rechercherUserIndex(index);
	}

	@Override
	public List<Article> lireTousArticleByUserVente(Long user_id) {
		// TODO Auto-generated method stub
		return persistanceArticle.lireTousArticleByUserVente(user_id);
	}

	

	
	@Override
	public void ajouterArticleAchat(User user, Article article) {
		// TODO Auto-generated method stub
		persistanceArticle.ajouterArticleAchat(user, article);
		
	}

	@Override
	public Article rechercherArticleIndex(Long id) {
		// TODO Auto-generated method stub
		return persistanceArticle.rechercherArticleIndex(id);
	}
}
