package interaction.persistance;

import java.util.List;

import contenu.entite.Article;
import interaction.entite.Commande;
import utilisateurs.entite.User;



public interface PersistanceCommandeItf {

	
	void persisterCommande(Commande article);
	List<Article> lireTousCommande();
	Commande lireCommande(Long id);
	Commande lireCommandeName(String name);
	


	
	void persisterUserCommande(User user, Commande article);
	
	
	Commande selectArticleTitre(String titre);
	
	Commande selectCommandeByUser(Long user_id);
	Commande selectUserByCommande(Long article_id);
	Commande selectCommandeTitre(String titre);
	
}
