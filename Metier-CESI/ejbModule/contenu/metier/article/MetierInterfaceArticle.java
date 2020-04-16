package contenu.metier.article;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import contenu.entite.Article;
import contenu.enume.StatutArticle;
import utilisateurs.entite.User;
import utilisateurs.entite.Utilisateur;




@Local
public interface MetierInterfaceArticle {
	// PROMOTIONS
	void creerArticle(Article article);
	Article lireArticle(Long id);
	void mettreAJourArticle(Article article);
	void supprimerArticle(Article article);
	List<Article> lireTousArticle();
	
	
	void persisterArticle(Article article);
	

	void validationTitre(String titre) throws Exception; ;
	void validationContenu(String contenu) throws Exception; ;
	void validationUrl(String url) throws Exception;
	void validationDescription(String description) throws Exception;
	void validationTheme(String theme) throws Exception;

	
	List<Article> selectArticleByTheme(String theme);
	
	//void validationMotDePasse(String motdepasse) throws Exception;;
	
	Article creerArticle(HttpServletRequest request);


	String getValeurChamp(HttpServletRequest request, String nomChamp);
	
	void setErreur(String champ, String message);
	
	Map<String, String> getErreurs();
	String getResultat();
	
	
	Article creerArticleRequestSession(HttpServletRequest request, HttpSession session);

	void insertJoinArticleUserWithQuery(User user, Article article);
	

	void insertJoinArticleUserWithQueryIndex(Long user_id, Long id);
	
	 void updateArticleWithUser(Article article, User user);
	 
	 
		void insertIntoVenteUser(User user, Article article);
		

		void insertionJoinTableId(Long userid, Long articleId);
		void insertionJoinTableObject(User user, Article article);
		
		Article creerArticleUserRequestSession(HttpServletRequest request, User userParams, HttpSession session);


		User rechercherUserLogin(String login);

	
		User rechercherUserIndex(Long index);
		List<Article> lireTousArticleByUserVente(Long user_id);
		

		
		
		void ajouterArticleAchat(User user, Article article);
	
		
		Article rechercherArticleIndex(Long id);
		Article updateArticleUserRequestSession(HttpServletRequest request, User userParams, HttpSession session);
		Article updateArticle(HttpServletRequest request);
		void updateArticleDate(Article article);
	/*
	User connecterUser(HttpServletRequest request);
	*/

		void validerArticeByIndexException(Long article_id);
		void updateArticleStatut(Article article, StatutArticle status);
		void supprimerArticleByIndexException(Long article_id);
	
	//void ajouterAbsence(Long etudiantId);
	//void ajouterRetard(Long etudiantId);
	//void ajouterAbsence(Etudiant etudiant);


}
