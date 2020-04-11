package contenu.metier.article;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import contenu.entite.Article;
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
	void validationSkills(String skills) throws Exception;
	
	
	
	//void validationMotDePasse(String motdepasse) throws Exception;;
	
	Article creerArticle(HttpServletRequest request);


	String getValeurChamp(HttpServletRequest request, String nomChamp);
	
	void setErreur(String champ, String message);
	
	Map<String, String> getErreurs();
	String getResultat();
	
	Article creerArticleRequest(HttpServletRequest request);
	Article creerArticleRequestSession(HttpServletRequest request, HttpSession session);
	Article creerArticleUser(HttpServletRequest request, User user);
	Article creerArticleUserIndex(HttpServletRequest request, Long user_id);
	
	void insertJoinArticleUserWithQuery(User user, Article article);
	

	void insertJoinArticleUserWithQueryIndex(Long user_id, Long id);
	
	 void updateArticleWithUser(Article article, User user);
	 
	 
		void insertIntoVenteUser(User user, Article article);
		

		void insertionJoinTableId(Long userid, Long articleId);
		void insertionJoinTableObject(User user, Article article);
		
		Article creerArticleUserRequestSession(HttpServletRequest request, User userParams, HttpSession session);

	
	/*
	User connecterUser(HttpServletRequest request);
	*/
	
	//void ajouterAbsence(Long etudiantId);
	//void ajouterRetard(Long etudiantId);
	//void ajouterAbsence(Etudiant etudiant);


}
