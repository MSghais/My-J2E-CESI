package contenu.metier.article;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import contenu.entite.Article;
import contenu.enume.StatutArticle;
import utilisateurs.entite.User;





@Local
public interface MetierInterfaceArticle {
	// PROMOTIONS
	
	
	void modifierArticle(Article article);
	void supprimerArticle(Article article);
	void persisterArticle(Article article);

	void validationTitre(String titre) throws Exception; ;
	void validationContenu(String contenu) throws Exception; ;
	void validationUrl(String url) throws Exception;
	void validationDescription(String description) throws Exception;
	void validationTheme(String theme) throws Exception;


	void updateArticleDate(Article article);
	void updateArticleStatut(Article article, StatutArticle status);
	void validerArticeByIndexException(Long article_id);
	void supprimerArticleByIndexException(Long article_id);
	void ajouterArticleAchat(User user, Article article);
	
	List<Article> lireTousArticle();
	List<Article> selectArticleByTheme(String theme);
	List<Article> lireTousArticleByUserVente(Long user_id);
	
	String getValeurChamp(HttpServletRequest request, String nomChamp);
	
	String getResultat();
	void setErreur(String champ, String message);
	Map<String, String> getErreurs();


	User rechercherUserLogin(String login);
	User rechercherUserIndex(Long index);

	Article rechercherArticleIndex(Long id);
	Article updateArticleUserRequestSession(HttpServletRequest request,  HttpSession session);
	Article modifierArticleReturn(Article article);
	Article creerArticleUserRequestSession(HttpServletRequest request, HttpSession session);



}
