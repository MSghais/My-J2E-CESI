package contenu.metier.theme;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;

import contenu.entite.Article;

import contenu.entite.Theme;
import contenu.persistance.article.PersistanceArticleItf;
import contenu.persistance.theme.PersistanceThemeItf;
import utilisateurs.entite.User;

@Stateless
public class MetierTheme implements MetierInterfaceTheme {

	@EJB
	private PersistanceThemeItf persistanceTheme;



	private Map<Long, Article> articles;

	private Map<Theme, Article> articlesThematiques;

	private Map<String, String> erreurs;
	private String resultat;

	private final String CHAMP_TITRE = "art_titre";
	private final String CHAMP_URL = "art_url";
	private final String CHAMP_DESCL = "art_description";
	private final String CHAMP_CONTENT = "art_contenu";

	private final String CHAMP_CONF = "confirmation";

	private final String CHAMP_SKILLS_FK = "skills_requis";

	public MetierTheme() {
		System.out.println("Constructeur Metier");
		articles = new HashMap<Long, Article>();

		articlesThematiques = new HashMap<Theme, Article>();
		init();
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



	private void init() {
		/*
		 * System.out.println("Metier - init");
		 * 
		 * Article article = new Article("Math",
		 * "Le mond est indéfini, les inconnus sont multiples",
		 * "puissant le philosophe du dimanche qui fait le mec qui parle des Math zerma"
		 * ); persisterArticle(article);
		 * 
		 * Article article1 = new Article("Info", "2020 c'est pas des lol",
		 * "ça taquine en bombe"); persisterArticle(article1);
		 */

	}

	
	

	@Override
	public Theme creerTheme(HttpServletRequest request) {
		// TODO Auto-generated method stub

		
		String theme_intitule = request.getParameter("intitule");
		Theme theme = new Theme();

		System.out.println("Test des exceptions du formulaire a partir du MEtier");

		System.out.println("Test  metier : Creer un Hashtag");
		/* Validation du champ mot de passe. */
		try {
			validationTheme(theme_intitule);
		} catch (Exception e) {
			setErreur(CHAMP_SKILLS_FK, e.getMessage());
		}
		theme.setTheme_intitule(theme_intitule);

		
		/* Initialisation du résultat global de la validation. */
		if (erreurs.isEmpty()
			
				|| !theme.getTheme_intitule().isEmpty()) {
		
			this.setResultat("Succès dépot Theme");
			this.resultat = "Succès du Theme";

			resultat = "Succès du dépot du Theme";
			System.out.println("Succès du dépot du Theme ");
			System.out.println("Erreurs : " + erreurs);
			

			persistanceTheme.persisterTheme(theme);

			System.out.println("Persister utilisateur OK ");
		} else {
			resultat = "Échec de la connexion.";

			this.setResultat(resultat);

			System.out.println("Echec de la connexion ");
			System.out.println("Erreur : " + erreurs);
		}

		// return user;
		return theme;
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
	public Theme selectThemeTitre(String titre) {
		// TODO Auto-generated method stub
		return persistanceTheme.selectThemeTitre(titre);
	}

	@Override
	public void persisterTheme(Theme theme) {
		// TODO Auto-generated method stub
		persistanceTheme.persisterTheme(theme);
	}


	@Override
	public Theme lireTheme(Long id) {
		// TODO Auto-generated method stub
		return persistanceTheme.lireTheme(id);
	}

	@Override
	public Theme lireThemeName(String name) {
		// TODO Auto-generated method stub
		return persistanceTheme.lireThemeName(name);
	}

	@Override
	public List<Theme> lireTousTheme() {
		// TODO Auto-generated method stub
	return persistanceTheme.lireTousTheme();
	}

	@Override
	public void persisterUserTheme(User user, Theme theme) {
		// TODO Auto-generated method stub
		persistanceTheme.persisterUserTheme(user, theme );
	}

	@Override
	public Theme selectThemeByUser(Long user_id) {
		// TODO Auto-generated method stub
		return persistanceTheme.selectThemeByUser(user_id);
	}

	@Override
	public Theme selectUserByTheme(Long theme_id) {
		// TODO Auto-generated method stub
		return persistanceTheme.selectUserByTheme(theme_id);
	}
}
