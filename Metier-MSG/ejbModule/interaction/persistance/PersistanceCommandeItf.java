package interaction.persistance;

import java.util.List;

import contenu.entite.Article;
import contenu.enume.StatutArticle;
import interaction.entite.Commande;
import interaction.enume.StatutCommande;
import utilisateurs.entite.User;



public interface PersistanceCommandeItf {

	
	void persisterCommande(Commande article);
	List<Commande> lireTousCommande();
	Commande lireCommande(Long id);
	Commande lireCommandeName(String name);
	
	void insertArticleAchat(User user, Article article);
	void insertArticleCommande(User user, Article article);

	
	void persisterUserCommande(User user, Commande article);
	void persisterAcheteurVendeurCommande(User vendeur, User acheteur, Article article);
	
	Commande selectArticleTitre(String titre);
Commande selectCommandeTitre(String titre);
	
	
	void updateArticleStatut(Article article, StatutArticle status);
	void updateCommandeStatut(Commande commande,  StatutCommande status);
	void updateCommandeStatutIndex(Long index, StatutCommande status) ;
	
	void ajouterArticleCommande(User user, Commande commande);
	Commande selectCommandeByIndex(Long id);
	
	void ajouterArticleAchat(User user, Article article);

	Commande selectCommandeByLastIndex();
	Commande selectCommandeByArticle(Long article_id);
	

	List<Commande> rechercherCommandeByAcheteur(Long acheteur_id);
	List<Article> rechercherArticleByCommande(Long id);
	List<Article> rechercherArticleByStatut(Long id, StatutArticle statut);
	
	List<Commande> lireTousAchats(Long id);
	List<Commande> lireTousVenteEnCours(Long login);
	List<Article> lireTousArticleVendeur(Long login);
	
	
	List<Article> lireTousArticleByVendeurException(Long user_id);
	
	List<Commande> lireTousCommandeByAcheteur(Long user_id);
	
	List<Commande> lireTousCommandeByAcheteurException(Long user_id) ;
	List<Commande> lireTousCommandeByVendeurException(Long user_id);
	void ajouterArticleVente(User user, Article article);
	
	List<Article> lireTousArticleReserveByVendeurException(Long user_id);
	Commande lireTousCommandeByArticleException(Long article_id);
	

	
	void updateCommandeDateCreation(Commande commande);
	void updateCommandeDateEnvoi(Commande commande);
	Commande mergeCommandeReturn(Commande commande);
	void supprimerCommande(Commande commande);
	
	List<Commande> selectListCommandeAcheteur(Long id);


}
