package contenu.persistance.article;

import java.util.List;

import contenu.entite.Article;


import utilisateurs.entite.User;



public interface PersistanceArticleItf {

	
	void persisterArticle(Article article);
	List<Article> lireTousArticle();
	Article lireArticle(Long id);
	Article lireArticleName(String name);
	


	
	void persisterUserArticle(User user, Article article);
	
	
	Article selectArticleTitre(String titre);
	
	Article selectArticleByUser(Long user_id);
	Article selectUserByArticle(Long article_id);
	void supprimerArticle(Article article);
	Article insertUserByArticle(Long article_id);
	void persisterArticleInUser(User user, Article article);
	void persisterUserAndArticle(User user, Article article);
	
	
	void insertJoinArticleUserWithQuery(User user, Article article);
	void insertJoinArticleUserWithQueryIndex(Long userid, Long articleid);
	void updateArticleWithUser(Article article, User user);
	

	
	void insertIntoVenteUser(User user, Article article);
	
	void insertionJoinTableObject(User user, Article article);
	
	void insertionJoinTableId(Long userid, Long articleId);
	
}
