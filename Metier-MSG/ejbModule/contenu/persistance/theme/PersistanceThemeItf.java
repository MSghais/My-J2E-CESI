package contenu.persistance.theme;

import java.util.List;

import contenu.entite.Article;

import contenu.entite.Theme;

import utilisateurs.entite.User;



public interface PersistanceThemeItf {

	


	
	void persisterTheme(Theme theme);
	Theme lireTheme(Long id);
	Theme lireThemeName(String name);
	List<Theme> lireTousTheme();

	void persisterUserTheme(User user,Theme theme);

	Theme selectThemeTitre(String titre);
	
	Theme selectThemeByUser(Long user_id);
	Theme selectUserByTheme(Long theme_id);

	
}
