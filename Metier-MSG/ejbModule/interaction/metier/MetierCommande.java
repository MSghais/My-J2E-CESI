package interaction.metier;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;

import org.jboss.resteasy.spi.HttpRequest;

import contenu.entite.Article;
import contenu.entite.Theme;
import contenu.enume.StatutArticle;

import contenu.persistance.article.PersistanceArticleItf;
import contenu.persistance.theme.PersistanceThemeItf;
import interaction.entite.Commande;
import interaction.enume.StatutCommande;
import interaction.persistance.PersistanceCommandeItf;
import utilisateurs.entite.User;

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

	private Map<String, String> erreurMap;
	
	private String erreurMsg;

	private String erreurPicto;

	private String resultat;

	private final String CHAMP_TITRE = "art_titre";
	private final String CHAMP_URL = "art_url";
	private final String CHAMP_DESCL = "art_description";
	private final String CHAMP_CONTENT = "art_contenu";

	private final String CHAMP_CONF = "confirmation";

	public static final String ATTRIBUT_ERREUR_MSG = "msgErreur";
	public static final String ATTRIBUT_ERREUR_MAP_CB = "erreursMapCB";
	public static final String ATTRIBUT_ERREUR = "erreurs";

	public static final String ATTRIBUT_ERREUR_CB = "erreurCB";
	public static final String ATTRIBUT_ERREUR_PICTO = "erreurPicto";

	public MetierCommande() {
		System.out.println("Constructeur Metier");
		articles = new HashMap<Long, Article>();

		erreurMap = new HashMap<String, String>();

		articlesThematiques = new HashMap<Theme, Article>();
		init();
	}


	
	@Override
	public void creerCommandeSimply(String login, Long article_id) {	
		User user = (User) persistanceUser.rechercherUserLogin(login);
	
		Article article = (Article) persistanceArticle.lireArticle(article_id);
		
		//article.setStatus(StatutArticle.RESERVE);
		updateArticleStatut(article, StatutArticle.RESERVE);
		Commande commande = new Commande(article.getPrix(), article, user);
		commande.setStatus(StatutCommande.ENCOURS);
		
		commande.setDateCreation(new Date());
		persistanceCommande.persisterCommande(commande);
		
		//ajouterArticleAchat(user, article);
	}

	
	
	@Override
	public void supprimerCommandeByArticleIndexException(Long article_id) {
		// TODO Auto-generated method stub
		Article article = persistanceArticle.lireArticle(article_id);
		Commande commande = lireTousCommandeByArticleException(article.getId()) ;
		System.out.println("Commande Index is =" + commande);
		
		supprimerCommande(commande);
		
	}
	
	@Override
	public List<Article> lireTousArticleByVendeurException(Long user_id) {
		// TODO Auto-generated method stub
		return persistanceCommande.lireTousArticleByVendeurException(user_id);
	}
	
	@Override
	public void validerCommandeByArticleIndexException(Long article_id) {
		// TODO Auto-generated method stub
		Article article = persistanceArticle.rechercherArticleIndex(article_id);
		
		updateArticleStatut(article, StatutArticle.VENDU);
		Commande commande = lireTousCommandeByArticleException(article.getId()) ;
		System.out.println("Commande Index is =" + commande);
		
		updateCommandeStatut(commande, StatutCommande.VALIDEE);
		
		
	}
	
	@Override
	public void envoyerCommandeByArticleIndexException(Long article_id) {
		// TODO Auto-generated method stub
		Article article = persistanceArticle.rechercherArticleIndex(article_id);
		
		updateArticleStatut(article, StatutArticle.VENDU);
		Commande commande = lireTousCommandeByArticleException(article.getId()) ;
		System.out.println("Commande Index is =" + commande);
		
		updateCommandeStatut(commande, StatutCommande.ENVOYEE);
		
		updateCommandeDateEnvoi(commande);
		
		
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
			if (picto.length() < 2 || picto.length() > 3) {
				throw new Exception("Le pictogramme doit faire 3 chiffres.");
			}
		}

	}
	
	@Override
	public boolean validerCodeBanque(HttpServletRequest request) {
		
		
		String codeBanquaire = request.getParameter("codeBanquaire");
		 System.out.println("metier try validation banquaire : ");
		  try {
			  	validationBanquaire(codeBanquaire); 
			  	return true;
		  }
		  catch (Exception e) 
		  { // TODO
			 // e.printStackTrace();
			  
				  System.out.println("Catch exception banquaire."); setErreur(  ATTRIBUT_ERREUR_MAP_CB, e.getMessage() );
				setErreur( ATTRIBUT_ERREUR_MSG, e.getMessage() ); 				 
			   setErreur(ATTRIBUT_ERREUR_CB, e.getMessage() );
		  
				  System.out.println("attribut erreurs Map" + ATTRIBUT_ERREUR_MAP_CB);
				  erreurMap.put(ATTRIBUT_ERREUR_CB, e.getMessage() );
				  
				  erreurMsg = e.getMessage();
				  
				  System.out.println("attribut erreurs Map" + erreurMap.get(ATTRIBUT_ERREUR_CB) );
		  
				  request.setAttribute(ATTRIBUT_ERREUR_CB, e.getMessage());
		  return false;
		  }
		  
		 
		 
	}
	
	
	@Override
	public boolean validerPicto(HttpServletRequest request) {
		

		String chiffreSecret = request.getParameter("chiffreSecret");
		
		 
		  try {
			  validationPictogramme(chiffreSecret); 
			  return true;
		  } catch (Exception e) { //
		 //e.printStackTrace();
			 setErreur( ATTRIBUT_ERREUR_MAP_CB, e.getMessage() );
			 setErreur( ATTRIBUT_ERREUR_MSG, e.getMessage() );
			 setErreur( ATTRIBUT_ERREUR_PICTO, e.getMessage() );
		  
			 
			  erreurPicto = e.getMessage();
			  System.out.println("attribut erreurs Map" + ATTRIBUT_ERREUR_MAP_CB);
			  erreurMap.put(ATTRIBUT_ERREUR_PICTO, e.getMessage());
	  
			  request.setAttribute(ATTRIBUT_ERREUR_PICTO, erreurPicto);
			  return false;
		  }
		 
		
		
	}
	
	@Override
	public Commande selectCommandeByIndex(Long id) {
		return persistanceCommande.selectCommandeByIndex(id);
	}

	@Override
	public List<Commande> lireTousCommande() {
		// return new ArrayList<>(etudiants.values());

		return persistanceCommande.lireTousCommande();
	}

	private void init() {
		System.out.println("Metier Commande");

	
	}
	

	@Override
	public Commande modifierCommande(Long id,  StatutCommande statut) {
	
		Commande commande = selectCommandeByArticle(id);
		commande.setStatus(statut);
		commande.setDateEnvoi(new Date() );
		return persistanceCommande.mergeCommandeReturn(commande);	
	}
	
	@Override
	public Commande selectCommandeByLastIndex() {
		// TODO Auto-generated method stub
		return persistanceCommande.selectCommandeByLastIndex();
	}
	
	@Override
	public void setErreur(String champ, String message) {
		// TODO Auto-generated method stub
		erreurMap.put(champ, message);
	}

	@Override
	public Map<String, String> getErreurs() {
		// TODO Auto-generated method stub
		return erreurMap;
	}

	@Override
	public String getResultat() {
		// TODO Auto-generated method stub
		return resultat;
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

	@Override
	public void setResultat(String resultat) {
		this.resultat = resultat;
	}


	@Override
	public Commande lireCommande(Long id) {
		// TODO Auto-generated method stub
		return persistanceCommande.lireCommande(id);
	}

	@Override
	public void mettreAJourCommande(Commande commande) {
		persistanceCommande.mergeCommandeReturn(commande);

	}

	@Override
	public void supprimerCommande(Commande commande) {
		// TODO Auto-generated method stub
		
		persistanceCommande.supprimerCommande(commande);

	}
	
	@Override
	public Article lireArticle(Long article_id) {
		return persistanceArticle.lireArticle(article_id);
	}
	@Override
	public Commande lireCommandeByArticleIndexException(Long article_id) {
		// TODO Auto-generated method stub
		Article article = lireArticle(article_id);
		Commande commande = lireTousCommandeByArticleException(article.getId()) ;
		System.out.println("Commande Index is =" + commande);
		
		return commande;
		
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
	public Commande lireTousCommandeByArticleException(Long article_id) {
		// TODO Auto-generated method stub
		return persistanceCommande.lireTousCommandeByArticleException(article_id);
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


@Override
public void updateCommandeDateCreation(Commande commande) {
	// TODO Auto-generated method stub
	persistanceCommande.updateCommandeDateCreation(commande);
}

@Override
public void updateCommandeDateEnvoi(Commande commande) {
	// TODO Auto-generated method stub
	persistanceCommande.updateCommandeDateEnvoi(commande);
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
