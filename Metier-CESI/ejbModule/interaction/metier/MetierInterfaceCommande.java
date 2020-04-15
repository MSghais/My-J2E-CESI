package interaction.metier;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;
import javax.servlet.http.HttpServletRequest;

import contenu.entite.Article;
import contenu.enume.StatutArticle;
import interaction.entite.Commande;
import interaction.enume.StatutCommande;
import utilisateurs.entite.User;
import utilisateurs.entite.Utilisateur;




@Local
public interface MetierInterfaceCommande {
	// PROMOTIONS
	void creerCommande(Commande commande);
	Commande lireCommande(Long id);
	
	void mettreAJourCommande(Commande commande);
	void supprimerCommande(Commande commande);
	List<Commande> lireTousCommande();
	void persisterCommande(Commande commande);

	

	String getValeurChamp(HttpServletRequest request, String nomChamp);
	
	void setErreur(String champ, String message);
	
	Map<String, String> getErreurs();
	String getResultat();
	Commande creerCommandeRequest(HttpServletRequest request);
	
	void validationPictogramme(String picto) throws Exception;
	void validationBanquaire(String cb_code) throws Exception;
	
	

	
	void insertArticleAchat(User user, Article article);
	void insertArticleCommande(User user, Article article);
	
	Commande selectCommandeByAcheteur(Long user_id);
	
	Commande selectCommandeByArticle(Long article_id);
	Commande selectCommandeByLastIndex();

	void updateCommandeReservationAll(Commande commande, StatutCommande status, Article article, User acheteur);
	

	
	void creerCommandeSimply(String login, Long article_id);
	
	void voidCreateAndInsertCommandeMetierIn(Article article, User userAcheteur);
	
	List<Article> rechercherArticleByStatut(Long id, StatutArticle statut);
	
	List<Commande> rechercherCommandeByAcheteur(Long acheteur_id);

	List<Commande> lireTousAchat(Long acheteur_id);
	
	List<Commande> lireTousVenteEnCours(Long acheteur_id);
	List<Article> lireTousArticleVendeur(Long login);
	
	List<Commande> lireTousCommandeByAcheteur(Long user_id);
	Commande selectCommandeByIndex(Long id);
	
	List<Commande> lireTousCommandeByAcheteurException(Long user_id) ;
	List<Commande> lireTousCommandeByVendeurException(Long user_id);
	
	List<Article> lireTousArticleReserveByVendeurException(Long user_id);
	
	Commande lireTousCommandeByArticleException(Long id);

	void updateArticleStatut(Article article, StatutArticle status);

	void updateCommandeStatut(Commande commande, StatutCommande status);
	void updateCommandeStatutindex(Long index, StatutCommande status) ;
	
	void ajouterArticleCommande(User user, Commande commande);
	void ajouterArticleAchat(User user, Article article);
	void ajouterArticleVenteEnCours(User user, Article article);
	
	void updateCommandeDateCreation(Commande commande);
	void updateCommandeDateEnvoi(Commande commande);

}
