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
			//validationTitre(art_titre);
		} catch (Exception e) {
			this.setErreur(CHAMP_TITRE, e.getMessage());
		}
		article.setTitre(art_titre);

		System.out.println("Test  metier : url ");
		/* Validation du champ username */
		try {
			//validationUrl(art_url);

		} catch (Exception e) {
			this.setErreur(CHAMP_URL, e.getMessage());
		}
		article.setUrl(art_url);

		System.out.println("Test  metier : description ");
		/* Validation du champ username */
		try {
		//	validationDescription(art_description);

		} catch (Exception e) {
			setErreur(CHAMP_DESCL, e.getMessage());
		}
		article.setDescription(art_description);

		System.out.println("Test  metier : Contenu");
		/* Validation du champ mot de passe. */
		try {
			//validationContenu(art_contenu);
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
public Commande selectCommandeByLastIndex() {
	// TODO Auto-generated method stub
	return persistanceCommande.selectCommandeByLastIndex();
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
public Commande selectCommandeByArticle(Long article_id) {
	// TODO Auto-generated method stub
	return null;
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
	public void updateCommandeStatutindex(Long index, StatutCommande status) {
		// TODO Auto-generated method stub
		persistanceCommande.updateCommandeStatutIndex(index, status);
	}


	@Override
	public void updateCommandeReservationAll(Commande commande, StatutCommande status, Article article, User acheteur) {
		// TODO Auto-generated method stub

		persistanceCommande.updateCommandeReservationAll(commande, status, article, acheteur);
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

@Override
public List<Commande> lireTousCommandeByAcheteurException(Long user_id) {
	// TODO Auto-generated method stub
	return persistanceCommande.lireTousCommandeByAcheteurException(user_id);
}

@Override
public List<Commande> lireTousCommandeByVendeurException(Long user_id) {
	// TODO Auto-generated method stub
	return persistanceCommande.lireTousCommandeByVendeurException(user_id);
}

@Override
public List<Article> lireTousArticleReserveByVendeurException(Long user_id) {
	// TODO Auto-generated method stub
	return persistanceCommande.lireTousArticleReserveByVendeurException(user_id);
}

@Override
public void insertArticleAchat(User user, Article article) {
	// TODO Auto-generated method stub
	persistanceCommande.insertArticleAchat(user, article);
}
@Override
public void insertArticleCommande(User user, Article article) {
	// TODO Auto-generated method stub
	persistanceCommande.insertArticleCommande(user, article);
}

@Override
public void ajouterArticleVenteEnCours(User user, Article article) {
	// TODO Auto-generated method stub
	persistanceCommande.ajouterArticleVente(user, article);
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
