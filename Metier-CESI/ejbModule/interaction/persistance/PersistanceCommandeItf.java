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
	


	
	void persisterUserCommande(User user, Commande article);
	
	void persisterAcheteurVendeurCommande(User vendeur, User acheteur, Article article);
	
	Commande selectArticleTitre(String titre);
	
	Commande selectCommandeByUser(Long user_id);
	Commande selectUserByCommande(Long article_id);
	Commande selectCommandeTitre(String titre);
	
	
	void updateCommandeWithAcheteur(Commande commande, User user);
	void updateCommandeWithVendeur(Commande commande, User user, Article article);
	
	
	void updateArticleStatut(Article article, StatutArticle status);
	void updateCommandeStatut(Commande commande,  StatutCommande status);
	
	void insertIntoCommandeAcheteurVendeur(User user, Article article);
	void ajouterArticleCommande(User user, Commande commande);
	Commande selectCommandeByIndex(Long id);
	void ajouterArticleAchat(User user, Article article);
	Commande selectCommandeByAcheteur(Long user_id);
	
	Commande creerCommandeAll(Article article, User acheteur);
	
	
	void updateCommandeReservationAll(Commande commande, StatutCommande status, Article article, User acheteur);
	
	
	Commande selectCommandeByLastIndex();
	void voidInsertCommandeMetier(Article article, User userAcheteur);
	Commande insertCommandeMetier(Article article, User userAcheteur);
	
	Commande createAndInsertCommandeMetier(Article article, User userAcheteur );
	Commande voidInsertCommandeArray(Article article, User userAcheteur);
	Commande persisterCommandeAndReturn(Commande commande);
	
	
	
	List<Commande> rechercherCommandeByAcheteur(Long acheteur_id);
	List<Article> rechercherArticleByCommande(Long id);
	List<Article> rechercherArticleByStatut(Long id, StatutArticle statut);
	Commande selectListCommandeAcheteur();
	
	
	
	List<Commande> lireTousAchats(Long id);
	List<Commande> lireTousVenteEnCours(Long login);
	List<Article> lireTousArticleVendeur(Long login);
	
	List<Commande> lireTousCommandeByAcheteur(Long user_id);
	
	List<Commande> lireTousCommandeByAcheteurException(Long user_id) ;
	List<Commande> lireTousCommandeByVendeurException(Long user_id);
	
}
