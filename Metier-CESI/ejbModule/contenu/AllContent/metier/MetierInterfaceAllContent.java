package contenu.AllContent.metier;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import contenu.entite.Article;

import contenu.entite.Theme;
import utilisateurs.entite.User;

public interface MetierInterfaceAllContent {
	
	void creerArticle(Article article);
	Article lireArticle(Long id);
	void mettreAJourArticle(Article article);
	void supprimerArticle(Article article);
	List<Article> lireTousArticle();
	void persisterArticle(Article article);
	

	Article selectArticleTitre(String titre);
	
	Article selectArticleByUser(Long user_id);
	Article selectUserByArticle(Long article_id);
	
	
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
	
	
	
	
	
	void persisterTheme(Theme theme);
	Theme lireTheme(Long id);
	Theme lireThemeName(String name);
	List<Theme> lireTousTheme();
	void persisterUserTheme(User user,Theme theme);
	
	Theme selectThemeTitre(String titre);
	
	Theme selectThemeByUser(Long user_id);
	Theme selectUserByTheme(Long theme_id);
	
}
