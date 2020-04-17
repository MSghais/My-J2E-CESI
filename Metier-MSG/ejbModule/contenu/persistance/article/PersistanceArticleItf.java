package contenu.persistance.article;

import java.util.List;

import contenu.entite.Article;
import contenu.entite.Theme;
import contenu.enume.StatutArticle;
import utilisateurs.entite.User;



public interface PersistanceArticleItf {

	
	void persisterArticle(Article article);
	List<Article> lireTousArticle();
	List<Article> lireTousArticleByUserVente(Long user_id);
	
	Article lireArticle(Long id);
	Article lireArticleName(String name);
	


	
	void persisterUserArticle(User user, Article article);
	Article selectArticleTitre(String titre);
	
	Article selectArticleByUser(Long user_id);
	Article selectUserByArticle(Long article_id);
	
	Article insertUserByArticle(Long article_id);


	

	void ajouterArticleAchat(User user, Article article);

	
	Article rechercherArticleIndex(Long id);
	
	void supprimerArticle(Article article);
	Article mergeArticleReturn(Article article);
	Article modifierArticle(Article article);
	void mergeArticle(Article article);
	void updateArticleStatut(Article article, StatutArticle status);
	void updateArticleDate(Article article);
	//List<Article> selectArticleByTheme(String theme);
	//List<Article> selectArticleByTheme(Theme theme);
	List<Article> selectArticleByTheme(String theme);
	
	
}
