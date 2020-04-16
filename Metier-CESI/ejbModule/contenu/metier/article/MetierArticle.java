package contenu.metier.article;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
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
import interaction.entite.Commande;
import interaction.enume.StatutCommande;
import utilisateurs.entite.User;

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
	public static final String ATTRIBUT_ARTICLE_MODIF      = "articleModification";

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


	@Override
	public void supprimerArticle(Article article) {
		persistanceArticle.supprimerArticle(article);
	}

	public List<Article> lireTousArticle() {
		// return new ArrayList<>(etudiants.values());

		return persistanceArticle.lireTousArticle();
	}
	
	
	@Override
	public List<Article> selectArticleByTheme(String theme) {
		// TODO Auto-generated method stub
		return persistanceArticle.selectArticleByTheme(theme);
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
	public Article creerArticleUserRequestSession(HttpServletRequest request, HttpSession session) {
		// TODO Auto-generated method stub

		System.out.println("Creer article User Request Session");
		
		//System.out.println("User paramse : " + userParams.toString() );
		
	Enumeration<String> sessionAttributs = session.getAttributeNames(); System.out.println(sessionAttributs);
	
	
	String loginUser = (String) session.getAttribute("userLogin");
	System.err.println("Session Login User : "+ loginUser);
	
	Long user_id = (Long) session.getAttribute("userId");
	System.err.println("Session USER_ID : "+ user_id);
	
	User userLectureBDD = (User)persistanceUser.lireUser(user_id); 
	System.out.println("Lire User BDD select are : " + userLectureBDD); System.out.println("User BDD  ID select are : " + userLectureBDD.getUser_id( ));
	
	
	Cookie[] cookies = request.getCookies();

		System.out.println("Request user are : " + request.getAttribute("utilisateur"));	
		System.out.println("Session user are : " + session.getAttribute("utilisateur"));
	
		String art_titre = request.getParameter("art_titre");
		String art_url = request.getParameter("art_url");

		String art_description = request.getParameter("art_description");
		String art_contenu = request.getParameter("art_contenu");
		String art_frais = request.getParameter("art_frais");
		String art_prix = request.getParameter("art_prix");
		String acronymeTheme = request.getParameter("acronymeTheme");
		System.out.println(acronymeTheme);

		Article article = new Article();

		Theme theme = persistanceTheme.lireThemeName(acronymeTheme);
		System.out.println(theme);
		
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
		 * System.out.println("Test  metier : url "); Validation du champ username 
		 * try {
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
		
		
		article.setVendeur(userLectureBDD);
		
		article.setStatus(StatutArticle.DISPONIBLE);
		
		article.setTheme(theme);
		
		article.setDate(new Date());
		
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

		
			
			System.out.println("updateArticle");
			
			
			
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
	public Article updateArticleUserRequestSession(HttpServletRequest request,  HttpSession session) {
		// TODO Auto-generated method stub

		System.out.println("Creer article User Request Session");	
		//Enumeration<String> sessionAttributs = session.getAttributeNames(); System.out.println(sessionAttributs);
	
	
	String loginUser = (String) session.getAttribute("userLogin");
	System.err.println("Session Login User : "+ loginUser);
	
	Long user_id = (Long) session.getAttribute("userId");
	System.err.println("Session USER_ID : "+ user_id);
	

	User userLectureBDD = (User)persistanceUser.lireUser(user_id); 
	System.out.println("Lire User BDD select are : " + userLectureBDD); System.out.println("User BDD  ID select are : " + userLectureBDD.getUser_id( ));

		System.out.println("Request user are : " + request.getAttribute("utilisateur"));
		
		System.out.println("Session user are : " + session.getAttribute("utilisateur"));

	
		String art_titre = request.getParameter("art_titre");
		String art_url = request.getParameter("art_url");

		String art_description = request.getParameter("art_description");
		String art_contenu = request.getParameter("art_contenu");
		String art_frais = request.getParameter("art_frais");
		String art_prix = request.getParameter("art_prix");
		
		String acronymeTheme = request.getParameter("acronymeTheme");
		System.out.println(acronymeTheme);
		Theme theme = persistanceTheme.lireThemeName(acronymeTheme);
		System.out.println(theme);
		
		
		Long article_id = (Long) request.getAttribute("modifier");
		Article article = rechercherArticleIndex(article_id);
		//Article article = (Article) request.getAttribute(ATTRIBUT_ARTICLE_MODIF);
		System.out.println("Article a modifier" + article);
		//Article article = new Article();

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


		article.setVendeur(userLectureBDD);
		
		article.setStatus(StatutArticle.DISPONIBLE);
		
		article.setTheme(theme);
		
		article.setDate(new Date());
		
		/* Initialisation du résultat global de la validation. */
		if (erreurs.isEmpty() || (!article.getTitre().isEmpty() && !article.getDescription().isEmpty()
				&& !article.getContenu().isEmpty())) {
		
			this.setResultat("Succès dépot Article");
			this.resultat = "Succès du dépot Article";

			resultat = "Succès de la connexion.";
			System.out.println("Succes du dépot Article ");
			System.out.println("Erreurs : " + erreurs);
			
			System.out.println("Update Article in Metier User Index Method");
			
			modifierArticleReturn(article);
			
			
		
			
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
	public void validationTitre(String titre) throws Exception {
		// TODO Auto-generated method stub
		if (titre != null) {
			if (titre.length() < 3) {
				throw new Exception("Le titre doit contenir au moins 3 caractères.");
			}
	
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
			
		} else {
			throw new Exception("Merci de saisir un titre plus précis.");
		}
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
	
	@Override
	public Article modifierArticleReturn(Article article) {
		return persistanceArticle.modifierArticle(article);
	}
	
	
	@Override
	public void modifierArticle(Article article) {
		System.out.println("modifier un article");
		 persistanceArticle.mergeArticle(article);
	}

	
	@Override
	public void updateArticleDate(Article article) {
		// TODO Auto-generated method stub
		persistanceArticle.updateArticleDate(article);
	}
	
	
	@Override 
	public User rechercherUserLogin(String login){
		
		return persistanceUser.rechercherUserLogin(login);
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
	
	@Override
	public void supprimerArticleByIndexException(Long article_id) {
		// TODO Auto-generated method stub
		Article article = rechercherArticleIndex(article_id);
		
		System.out.println("Commande Index is =" + article);
		
		supprimerArticle(article);
		
	}
	
	

	@Override
	public void validerArticeByIndexException(Long article_id) {
		// TODO Auto-generated method stub
		Article article = rechercherArticleIndex(article_id);
		
		updateArticleStatut(article, StatutArticle.VENDU);
		
		
	}
	
	@Override
	public void updateArticleStatut(Article article, StatutArticle status) {
		persistanceArticle.updateArticleStatut(article, status);
	}

}
