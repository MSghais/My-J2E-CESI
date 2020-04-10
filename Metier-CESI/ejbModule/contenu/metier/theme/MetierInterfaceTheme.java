package contenu.metier.theme;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;
import javax.servlet.http.HttpServletRequest;

import contenu.entite.Article;

import contenu.entite.Theme;




@Local
public interface MetierInterfaceTheme {
	// PROMOTIONS
	void creerArticle(Article article);
	Article lireArticle(Long id);
	void mettreAJourArticle(Article article);
	void supprimerArticle(Article article);
	List<Article> lireTousArticle();
	void persisterArticle(Article article);
	

	void validationTitre(String titre) throws Exception;
	Theme creerTheme(HttpServletRequest request);
	void validationUrl(String url) throws Exception;
	void validationContenu(String contenu) throws Exception;
	void validationDescription(String description) throws Exception;
	void validationTheme(String theme) throws Exception;

	void setErreur(String champ, String message);
	Map<String, String> getErreurs();
	String getValeurChamp(HttpServletRequest request, String nomChamp);
	Theme selectThemeTitre(String titre);
	void persisterTheme(Theme theme);
	String getResultat();

	
	//void ajouterAbsence(Long etudiantId);
	//void ajouterRetard(Long etudiantId);
	//void ajouterAbsence(Etudiant etudiant);


}
