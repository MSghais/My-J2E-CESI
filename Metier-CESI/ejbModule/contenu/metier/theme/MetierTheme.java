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
	private PersistanceThemeItf persistanceSkills;

	@EJB
	private PersistanceArticleItf persistanceArticle;

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
	/*
	 * private List<Skills> skills;
	 * 
	 * private List<Skills> skills_requis;
	 * 
	 * 
	 * private Map<Long,Etudiant> etudiants;
	 * 
	 * private Map<Long,Teacher> teachers; private Map<Long,Article> Articles;
	 * private Long idEtudiant;
	 */

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

	public void persisterArticle(Article article) {
		persistanceArticle.persisterArticle(article);
	}

	public List<Article> lireTousArticle() {
		// return new ArrayList<>(etudiants.values());

		return persistanceArticle.lireTousArticle();
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
	public Theme creerTheme(HttpServletRequest request) {
		// TODO Auto-generated method stub

		
		String theme_intitule = request.getParameter("theme_intitule");
		Theme theme = new Theme();

		System.out.println("Test des exceptions du formulaire a partir du MEtier");

		/*
		 * System.out.println("Test  metier : Skills"); Validation du champ mot de
		 * passe. try { validationContenu(skills_intitule); } catch ( Exception e ) {
		 * setErreur( CHAMP_SKILLS, e.getMessage() ); }
		 * skill.setSkills_intitule(skills_intitule);
		 * 
		 * System.out.println("Test  metier : Skills Requis pour le skills"); Validation
		 * du champ mot de passe. try { validationContenu(skills_fk); } catch (
		 * Exception e ) { setErreur( CHAMP_SKILLS_FK, e.getMessage() ); }
		 * skill_req.setSkills_intitule(skills_fk);
		 */

		System.out.println("Test  metier : Creer un Hashtag");
		/* Validation du champ mot de passe. */
		try {
			validationContenu(theme_intitule);
		} catch (Exception e) {
			setErreur(CHAMP_SKILLS_FK, e.getMessage());
		}
		theme.setTheme_intitule(theme_intitule);

		// || ( !utilisateur.getLogin().isEmpty() &&
		// !utilisateur.getMotDePasse().isEmpty() && !utilisateur.getEmail().isEmpty()

		/* Initialisation du résultat global de la validation. */
		if (erreurs.isEmpty()
				// || (!article.getArt_titre().isEmpty() &&
				// !article.getArt_description().isEmpty()
				// && !article.getArt_contenu().isEmpty())

				|| !theme.getTheme_intitule().isEmpty()) {
			// || ( !article.getArt_titre().isEmpty() && !article.getArt_url().isEmpty() &&
			// !article.getArt_description().isEmpty() &&
			// !article.getArt_contenu().isEmpty() )
			this.setResultat("Succès dépot Theme");
			this.resultat = "Succès du Theme";

			resultat = "Succès du dépot du Theme";
			System.out.println("Succès du dépot du Theme ");
			System.out.println("Erreurs : " + erreurs);
			// persistanceArticle.persisterArticle(article);

			/*
			 * persistanceSkills.persisterSkills(skill);
			 * 
			 * 
			 * persistanceSkills.persisterSkills(skill_req);
			 */

			persistanceSkills.persisterTheme(theme);

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
		return persistanceSkills.selectThemeTitre(titre);
	}

	@Override
	public void persisterTheme(Theme theme) {
		// TODO Auto-generated method stub
		persistanceSkills.persisterTheme(theme);
	}
	/*
	 * @Override public Skills lireSkills(Long id) { // TODO Auto-generated method
	 * stub return null; }
	 * 
	 * @Override public Skills lireSkillsName(String name) { // TODO Auto-generated
	 * method stub return null; }
	 * 
	 * @Override public List<Skills> lireTousSkills() { // TODO Auto-generated
	 * method stub return null; }
	 * 
	 * @Override public void persisterUserSkills(User user, Skills skills) { // TODO
	 * Auto-generated method stub persistanceSkills.persisterSkills(skills); }
	 * 
	 * @Override public Skills selectSkillsTitre(String titre) { // TODO
	 * Auto-generated method stub return null; }
	 * 
	 * @Override public Skills selectSkillsByUser(Long user_id) { // TODO
	 * Auto-generated method stub return null; }
	 * 
	 * @Override public Skills selectUserBySkills(Long skills_id) { // TODO
	 * Auto-generated method stub return null; }
	 * 
	 * @Override public void persisterTheme(Theme theme) { // TODO Auto-generated
	 * method stub
	 * 
	 * }
	 * 
	 * @Override public Theme lireTheme(Long id) { // TODO Auto-generated method
	 * stub return null; }
	 * 
	 * @Override public Theme lireThemeName(String name) { // TODO Auto-generated
	 * method stub return null; }
	 * 
	 * @Override public List<Theme> lireTousTheme() { // TODO Auto-generated method
	 * stub return null; }
	 * 
	 * @Override public void persisterUserTheme(User user, Theme theme) { // TODO
	 * Auto-generated method stub
	 * 
	 * }
	 * 
	 * 
	 * @Override public Theme selectThemeByUser(Long user_id) { // TODO
	 * Auto-generated method stub return null; }
	 * 
	 * @Override public Theme selectUserByTheme(Long theme_id) { // TODO
	 * Auto-generated method stub return null; }
	 * 
	 * @Override public Article selectArticleTitre(String titre) { return
	 * persistanceArticle.selectArticleTitre(titre); }
	 * 
	 * @Override public Article selectArticleByUser(Long user_id) { // TODO
	 * Auto-generated method stub return
	 * persistanceArticle.selectArticleByUser(user_id); }
	 * 
	 * @Override public Article selectUserByArticle(Long article_id) { // TODO
	 * Auto-generated method stub return
	 * persistanceArticle.selectUserByArticle(article_id); }
	 * 
	 */
}
