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
	Commande creerCommandeAll(Article article, User acheteur);
	void insertIntoCommandeAcheteurVendeur(User user, Article article);
	void ajouterArticleCommande(User user, Commande commande);
	Commande selectCommandeByIndex(Long id);
	void ajouterArticleAchat(User user, Article article);
	Commande selectCommandeByAcheteur(Long user_id);
	
	
}
