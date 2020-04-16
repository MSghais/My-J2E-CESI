package contenu.metier.theme;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;
import javax.servlet.http.HttpServletRequest;

import contenu.entite.Article;

import contenu.entite.Theme;
import utilisateurs.entite.User;




@Local
public interface MetierInterfaceTheme {
	// PROMOTIONS

	void validationTheme(String theme) throws Exception;

	void setErreur(String champ, String message);
	Map<String, String> getErreurs();
	String getValeurChamp(HttpServletRequest request, String nomChamp);

	String getResultat();

	
	void persisterTheme(Theme theme);
	Theme lireTheme(Long id);
	Theme lireThemeName(String name);
	List<Theme> lireTousTheme();

	void persisterUserTheme(User user,Theme theme);

	Theme selectThemeTitre(String titre);
	
	Theme selectThemeByUser(Long user_id);
	Theme selectUserByTheme(Long theme_id);

	Theme creerTheme(HttpServletRequest request);
	
	//void ajouterAbsence(Long etudiantId);
	//void ajouterRetard(Long etudiantId);
	//void ajouterAbsence(Etudiant etudiant);


}
