package interaction.metier;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;
import javax.servlet.http.HttpServletRequest;

import org.jboss.resteasy.spi.HttpRequest;

import contenu.entite.Article;
import contenu.enume.StatutArticle;
import interaction.entite.Commande;
import interaction.enume.StatutCommande;
import utilisateurs.entite.User;





@Local
public interface MetierInterfaceCommande {
	// PROMOTIONS

	void creerCommandeSimply(String login, Long article_id);

	Commande lireCommande(Long id);	
	void mettreAJourCommande(Commande commande);
	void supprimerCommande(Commande commande);
	List<Commande> lireTousCommande();
	void persisterCommande(Commande commande);

	void validationPictogramme(String picto) throws Exception;
	void validationBanquaire(String cb_code) throws Exception;
	
	String getResultat();
	void setResultat(String resultat);
	
	String getValeurChamp(HttpServletRequest request, String nomChamp);
	void setErreur(String champ, String message);
	Map<String, String> getErreurs();

	List<Article> lireTousArticleReserveByVendeurException(Long user_id);
	
	List<Article> lireTousArticleByVendeurException(Long user_id);
	List<Article> rechercherArticleByStatut(Long id, StatutArticle statut);
	List<Article> lireTousArticleVendeur(Long login);
	
	Article lireArticle(Long article_id);
	
	
	void insertArticleAchat(User user, Article article);
	void insertArticleCommande(User user, Article article);
	
	Commande selectCommandeByArticle(Long article_id);
	Commande selectCommandeByLastIndex();
	Commande selectCommandeByIndex(Long id);

	Commande lireTousCommandeByArticleException(Long id);

	List<Commande> rechercherCommandeByAcheteur(Long acheteur_id);
	List<Commande> lireTousAchat(Long acheteur_id);
	List<Commande> lireTousVenteEnCours(Long acheteur_id);
	List<Commande> lireTousCommandeByAcheteur(Long user_id);
	
	
	List<Commande> lireTousCommandeByAcheteurException(Long user_id) ;
	List<Commande> lireTousCommandeByVendeurException(Long user_id);
	
	void updateArticleStatut(Article article, StatutArticle status);
	void updateCommandeStatut(Commande commande, StatutCommande status);
	void updateCommandeStatutindex(Long index, StatutCommande status) ;
	
	void ajouterArticleCommande(User user, Commande commande);
	void ajouterArticleAchat(User user, Article article);
	void ajouterArticleVenteEnCours(User user, Article article);
	
	void updateCommandeDateCreation(Commande commande);
	void updateCommandeDateEnvoi(Commande commande);
	
	
	Commande lireCommandeByArticleIndexException(Long article_id);
	Commande modifierCommande(Long id, StatutCommande statut);
	void supprimerCommandeByArticleIndexException(Long article_id);
	void validerCommandeByArticleIndexException(Long article_id);
	void envoyerCommandeByArticleIndexException(Long article_id);


	boolean validerPicto(HttpServletRequest request);

	boolean validerCodeBanque(HttpServletRequest request);

	

}
