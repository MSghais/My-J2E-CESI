package interaction.metier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;

import contenu.entite.Article;
import contenu.entite.Theme;
import contenu.enume.StatutArticle;
import contenu.persistance.article.PersistanceArticleItf;
import contenu.persistance.theme.PersistanceThemeItf;
import interaction.entite.Commande;
import interaction.enume.StatutCommande;
import interaction.persistance.PersistanceCommandeItf;
import utilisateurs.entite.User;
import utilisateurs.entite.Utilisateur;
import utilisateurs.persistance.PersistanceUserItf;

@Stateless
public class MetierCommande implements MetierInterfaceCommande {

	@EJB
	private PersistanceThemeItf persistanceTheme;
	
	@EJB
	private PersistanceArticleItf persistanceArticle;

	@EJB
	private PersistanceCommandeItf persistanceCommande;
	
	@EJB 
	private PersistanceUserItf persistanceUser;

	private Long nombreCommande = 1L;

	private Map<Long, Article> articles;

	private Map<Theme, Article> articlesThematiques;

	private Map<String, String> erreurs;

	private String resultat;

	private final String CHAMP_TITRE = "art_titre";
	private final String CHAMP_URL = "art_url";
	private final String CHAMP_DESCL = "art_description";
	private final String CHAMP_CONTENT = "art_contenu";

	private final String CHAMP_CONF = "confirmation";

	public static final String ATTRIBUT_ERREUR_MSG = "msgErreur";
	public static final String ATTRIBUT_ERREUR_MAP_CB = "erreursMapCB";

	public MetierCommande() {
		System.out.println("Constructeur Metier");
		articles = new HashMap<Long, Article>();

		erreurs = new HashMap<String, String>();

		articlesThematiques = new HashMap<Theme, Article>();
		init();
	}

	public void creerArticle(Article article) {
		articles.put(article.getId(), article);
	}

	public Article lireArticle(Long id) {
		return articles.get(id);
	}

	@Override
	public Commande selectCommandeByIndex(Long id) {
		return persistanceCommande.selectCommandeByIndex(id);
	}
	public void mettreAJourArticle(Article article) {
		articles.put(article.getId(), article);
	}

	public void supprimerArticle(Article article) {
		articles.remove(article.getId());
	}

	public List<Commande> lireTousCommande() {
		// return new ArrayList<>(etudiants.values());

		return persistanceCommande.lireTousCommande();
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
	public Commande creerCommandeRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub

		String art_titre = request.getParameter("art_titre");
		String art_url = request.getParameter("art_url");

		String art_description = request.getParameter("art_description");
		String art_contenu = request.getParameter("art_contenu");
		Article article = new Article();

		Commande commande = new Commande();

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
			// persistanceCommande.persisterArticle(article);

			System.out.println("Persister utilisateur OK ");
		} else {
			resultat = "Échec de la connexion.";

			this.setResultat(resultat);

			System.out.println("Echec de la connexion ");
			System.out.println("Erreur : " + erreurs);
		}

		// return user;
		return commande;
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

	@Override
	public void creerCommande(Commande commande) {
		// TODO Auto-generated method stub

	}

	@Override
	public Commande lireCommande(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void mettreAJourCommande(Commande commande) {
		// TODO Auto-generated method stub

	}

	@Override
	public void supprimerCommande(Commande commande) {
		// TODO Auto-generated method stub

	}

	@Override
	public void validationBanquaire(String CB) throws Exception {
		// TODO Auto-generated method stub
		if (CB != null) {
			if (CB.length() < 10) {
				throw new Exception("Le code doit faire 10 chiffres.");
			}
		}

	}

	@Override
	public void validationPictogramme(String picto) throws Exception {
		// TODO Auto-generated method stub
		if (picto != null) {
			if (picto.length() < 2) {
				throw new Exception("Le pictogramme doit faire 3 chiffres.");
			}
		}

	}

	@Override
	public void persisterCommande(Commande commande) {
		// TODO Auto-generated method stub

	}

	@Override
	public void insertIntoUserAchat(User user, Article article) {

		System.out.println("Add Article in User Achat list");

		// entityManager.getTransaction().begin();

		user.addAchatArticles(article);

		///user.getAchatArticles().put(user.getUser_id(), article);

		// entityManager.getTransaction().commit();

		/*
		 * Article articleModif = entityManager.find(Article.class, article.getId());
		 * 
		 * articleModif.setUser_vendeur(user);
		 * 
		 * entityManager.getTransaction().begin(); //
		 * entityManager.persist(articleModif);
		 * 
		 * entityManager.getTransaction().commit();
		 */

	}

	@Override
	public void insertIntoUserCommande(User user, Article article) {

		System.out.println("Add Article in User Commande list");

		// entityManager.getTransaction().begin();

		// user.addCommandeArticles(article);

		// user.getCommandesArticles().put(user.getUser_id(), article);

		// entityManager.getTransaction().commit();

		/*
		 * Article articleModif = entityManager.find(Article.class, article.getId());
		 * 
		 * articleModif.setUser_vendeur(user);
		 * 
		 * entityManager.getTransaction().begin(); //
		 * entityManager.persist(articleModif);
		 * 
		 * entityManager.getTransaction().commit();
		 */

	}

	@Override
	public void updateArticleStatut(Article article, StatutArticle status) {

		persistanceCommande.updateArticleStatut(article, status);

	}

	@Override
	public void updateCommandeStatut(Commande commande, StatutCommande status) {
		// TODO Auto-generated method stub
		persistanceCommande.updateCommandeStatut(commande, status);
	}

	@Override
	public Commande creerCommandeAll(Article article, User acheteur) {

		return persistanceCommande.creerCommandeAll(article, acheteur);
	}

	@Override
	public Commande creerCommandeAllIndex(Article article, User acheteur, Commande commande) {

		return persistanceCommande.creerCommandeAllIndex(article, acheteur, commande);
	}

	@Override
	public void updateCommandeReservationAll(Commande commande, StatutCommande status, Article article, User acheteur) {
		// TODO Auto-generated method stub

		persistanceCommande.updateCommandeReservationAll(commande, status, article, acheteur);
	}

	

	@Override
	public Commande insertCommandeMetier(Article article, User userAcheteur) {
		// TODO Auto-generated method stub

		return persistanceCommande.insertCommandeMetier(article, userAcheteur);
		/*
		 * System.out.println("Creation commande with Paramater"); Commande commandeID =
		 * new Commande(article.getPrix(), article, userAcheteur,
		 * article.getUser_vendeur());
		 * 
		 * System.out.println("Persister cette commande");
		 * persistanceCommande.persisterCommande(commandeID);
		 * 
		 * return persistanceCommande.selectCommandeByLastIndex();
		 */
		// return persistanceCommande.selectCommandeByLastIndex();
	}

	@Override
	public Commande createAndInsertCommandeMetier(Article article, User userAcheteur ) {
		// TODO Auto-generated method stub
		return persistanceCommande.createAndInsertCommandeMetier(article, userAcheteur);
		/*
		 * System.out.println("Creation commande with Paramater"); Commande commandeID =
		 * new Commande(article.getPrix(), article, userAcheteur,
		 * article.getUser_vendeur());
		 * 
		 * System.out.println("Persister cette commande");
		 * persistanceCommande.persisterCommande(commandeID);
		 * 
		 * return persistanceCommande.selectCommandeByLastIndex();
		 */
		//return persistanceCommande.selectCommandeByLastIndex();
	}
	
	@Override
	public Commande voidInsertCommandeArray(Article article, User userAcheteur) {
		// TODO Auto-generated method stub
		return persistanceCommande.voidInsertCommandeArray(article, userAcheteur);
	}
	
	@Override
	public Commande selectCommandeByLastIndex() {
		
		return persistanceCommande.selectCommandeByLastIndex();
	}
	/*
	 * @Override public Commande createAndInsertCommandeMetier(Article article, User
	 * userAcheteur ) { // TODO Auto-generated method stub
	 * 
	 * System.out.println("Creation commande with Paramater"); Commande commandeID =
	 * new Commande(article.getPrix(), article, userAcheteur,
	 * article.getUser_vendeur());
	 * 
	 * persistanceCommande.persisterCommande(commandeID);
	 * 
	 * Commande commande =
	 * persistanceCommande.selectCommandeByIndex(commandeID.getCommande_id()) ;
	 * return commande;
	 * 
	 * System.out.println("Creation commande with Paramater"); Commande commandeID =
	 * new Commande(article.getPrix(), article, userAcheteur,
	 * article.getUser_vendeur());
	 * 
	 * System.out.println("Persister cette commande");
	 * persistanceCommande.persisterCommande(commandeID);
	 * 
	 * return persistanceCommande.selectCommandeByLastIndex();
	 * 
	 * //return persistanceCommande.selectCommandeByLastIndex(); }
	 */

	@Override
	public void voidInsertCommandeMetier(Article article, User userAcheteur) {
		// TODO Auto-generated method stub

		persistanceCommande.voidInsertCommandeMetier(article, userAcheteur);
		/*
		 * System.out.println("Creation commande with Paramater"); Commande commandeID =
		 * new Commande(article.getPrix(), article, userAcheteur,
		 * article.getUser_vendeur());
		 * 
		 * System.out.println("Persister cette commande");
		 * persistanceCommande.persisterCommande(commandeID);
		 * 
		 * //return persistanceCommande.selectCommandeByLastIndex();
		 */ }

	/*
	 * @Override public Commande insertCommandeMetier(Article article, User
	 * userAcheteur ) { // TODO Auto-generated method stub
	 * 
	 * 
	 * Commande commandeID = new Commande(article.getPrix(), article, userAcheteur,
	 * article.getUser_vendeur());
	 * 
	 * persistanceCommande.persisterCommande(commandeID);
	 * 
	 * 
	 * return persistanceCommande.selectCommandeByLastIndex(); }
	 */
	/*
	 * 
	 * @Override public User connecterUser(HttpServletRequest request) { // TODO
	 * Auto-generated method stub return null; }
	 * 
	 */

	@Override
	public void insertIntoCommandeAcheteurVendeur(User user, Article article) {
		// TODO Auto-generated method stub

	}

	@Override
	public void ajouterArticleCommande(User user, Commande commande) {
		// TODO Auto-generated method stub
		persistanceCommande.ajouterArticleCommande(user, commande);
	}

	@Override
	public void ajouterArticleAchat(User user, Article article) {
		// TODO Auto-generated method stub
		persistanceCommande.ajouterArticleAchat(user, article);
	}

	@Override
	public Commande selectCommandeByAcheteur(Long user_id) {

		return persistanceCommande.selectCommandeByAcheteur(user_id);
	}
	
	@Override
	public Commande createAndInsertCommandeMetierIn(Article article, User userAcheteur ) {
		// TODO Auto-generated method stub
		
		 System.out.println("Creation and Insert  commande In Metier");
			Commande commandeID = new Commande(article.getPrix(), article, userAcheteur);
			
			
			commandeID.setStatus(StatutCommande.ENCOURS);
			
			persistanceCommande.persisterCommande(commandeID);
			
			article.setStatus(StatutArticle.RESERVE);
			
			System.out.println("array command ID = " +  commandeID.getCommande_id());
			//Commande commande = selectCommandeByIndex(commandeID.getCommande_id());
			
			Commande commande = selectCommandeByLastIndex();
			return commande;
			//return selectCommandeByIndex(commandeID.getCommande_id());
		/**/
		/*
		 * System.out.println("Creation commande with Paramater"); Commande commandeID =
		 * new Commande(article.getPrix(), article, userAcheteur,
		 * article.getUser_vendeur());
		 * 
		 * System.out.println("Persister cette commande");
		 * persistanceCommande.persisterCommande(commandeID);
		 * 
		 * return persistanceCommande.selectCommandeByLastIndex();
		 */
		//return persistanceCommande.selectCommandeByLastIndex();
	}
	
	@Override
	public void voidCreateAndInsertCommandeMetierIn(Article article, User userAcheteur ) {
		// TODO Auto-generated method stub
		
		 System.out.println("Creation and Insert  commande In Metier");
			Commande commandeID = new Commande(article.getPrix(), article, userAcheteur);
			
			
			commandeID.setStatus(StatutCommande.ENCOURS);
			
			persistanceCommande.persisterCommande(commandeID);
			
			article.setStatus(StatutArticle.RESERVE);
			
			System.out.println("array command ID = " +  commandeID.getCommande_id());
			//Commande commande = selectCommandeByIndex(commandeID.getCommande_id());
			
		
	
	}

	@Override
	public Commande insertArrayCommandeMetierIn(Commande commande, Article article, User userAcheteur ) {
		// TODO Auto-generated method stub
		
		 
			commande.setStatus(StatutCommande.ENCOURS);
			
			persisterCommande(commande);
			
			article.setStatus(StatutArticle.RESERVE);
			
			Commande commandeCreer = selectCommandeByIndex(commande.getCommande_id());
			return commandeCreer;
			//return selectCommandeByIndex(commandeID.getCommande_id());
		/**/
		/*
		 * System.out.println("Creation commande with Paramater"); Commande commandeID =
		 * new Commande(article.getPrix(), article, userAcheteur,
		 * article.getUser_vendeur());
		 * 
		 * System.out.println("Persister cette commande");
		 * persistanceCommande.persisterCommande(commandeID);
		 * 
		 * return persistanceCommande.selectCommandeByLastIndex();
		 */
		//return persistanceCommande.selectCommandeByLastIndex();
	}
	
	@Override
	public Commande creerCommandeReturn(Article article, User acheteur) {	
		/*
		 * User uconnecte = (User) persistanceUser.lireUtilisateur(acheteurLogin);
		 * Article article = persistanceArticle.lireArticle(articleId);
		 */
		//UConnecte vendeur = (UConnecte) persistanceUtilisateur.lireUtilisateur(article.getVendeur().getLogin());
		
		System.out.println("Create commande in Metier");
		Commande commande = new Commande(article,acheteur);
		
	//	Commande commande = new Commande(article.getPrix(), article, acheteur, article.getUser_vendeur(), StatutCommande.ENCOURS);
	
		
		System.out.println("go persister");
		persistanceCommande.persisterCommande(commande);
		
		System.out.println("Return commande");
		return commande;
		//persistanceCommande.persisterCommande(commande);
		
	}
	
	@Override
	public Commande creerCommandeArray(Article article, User acheteur) {	
		
		System.out.println("Create commande in Metier");
		//Commande commande = new Commande(article,acheteur);
		
		//Commande commande = new Commande(article.getPrix(), article, acheteur, article.getUser_vendeur(), StatutCommande.ENCOURS);
	
		Commande commande = new Commande(article.getPrix(), article, acheteur, article.getUser_vendeur());
		
		

		return commande;
		//persistanceCommande.persisterCommande(commande);
		
	}
	
	
	@Override
	public void creerCommande(Article article, User acheteur) {	
		/*
		 * User uconnecte = (User) persistanceUser.lireUtilisateur(acheteurLogin);
		 * Article article = persistanceArticle.lireArticle(articleId);
		 */
		//UConnecte vendeur = (UConnecte) persistanceUtilisateur.lireUtilisateur(article.getVendeur().getLogin());
		System.out.println("Create commande in Metier");
		Commande commande = new Commande(article,acheteur);
		
		//Commande commande = new Commande(article.getPrix(), article, acheteur, article.getUser_vendeur());
		commande.setStatus(StatutCommande.ENCOURS);
		
		
		System.out.println("Set Article Reserver ");
		article.setStatus(StatutArticle.RESERVE);
		
		
		
		persistanceCommande.persisterCommande(commande);
		
	}
	
	
	
	@Override
	public void creerCommandeSimply(String login, Long article_id) {	
		User uconnecte = (User) persistanceUser.rechercherUserLogin(login);
	
		Article article = (Article) persistanceArticle.rechercherArticleIndex(article_id);
		
		article.setStatus(StatutArticle.RESERVE);
		Commande commande = new Commande(article.getPrix(), article, uconnecte);
		commande.setStatus(StatutCommande.ENCOURS);
		persistanceCommande.persisterCommande(commande);
	}

	@Override
	public List<Commande> rechercherCommandeByAcheteur(Long acheteur_id) {
		// TODO Auto-generated method stub
		return (List<Commande> ) persistanceCommande.rechercherCommandeByAcheteur(acheteur_id);
	}

	@Override
	public List<Article> rechercherArticleByStatut(Long id, StatutArticle statut) {
		// TODO Auto-generated method stub
		return (List<Article> ) persistanceCommande.rechercherArticleByStatut(id, statut);
	}

	@Override
	public List<Commande> lireTousAchat(Long acheteur_id) {
		// TODO Auto-generated method stub
		return persistanceCommande.lireTousAchats(acheteur_id);
	}

	@Override
	public List<Commande> lireTousVenteEnCours(Long acheteur_id) {
		// TODO Auto-generated method stub
		return persistanceCommande.lireTousVenteEnCours(acheteur_id);
	}
	
@Override
	public List<Article> lireTousArticleVendeur(Long id) {
		// TODO Auto-generated method stub
		return persistanceCommande.lireTousArticleVendeur(id);
	}
	
@Override
public List<Commande> lireTousCommandeByAcheteur(Long user_id) {
	// TODO Auto-generated method stub
	return persistanceCommande.lireTousCommandeByAcheteur(user_id);
}
	/*
	 * 
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

}
