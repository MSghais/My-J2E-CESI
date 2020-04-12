package utilisateurs.connexion;

import java.util.Collection;
import java.util.Map;

import javax.ejb.Local;
import javax.servlet.http.HttpServletRequest;

import utilisateurs.entite.User;

@Local
public interface MetierInterfaceConnexion {

	boolean connexionUtilisateur(String login, String motDePasse);

	User getUtilisateur(String login);
	Collection<User> getAllUtilisateur();
	void addUtilisateur(User utilisateur);

	User findUserNotBDD(String login, String password) throws Exception;



	User findUserBDD(String login, String password) throws Exception;

	
	String getValeurChamp(HttpServletRequest request, String nomChamp);
	
	void setErreur(String champ, String message);
	
	Map<String, String> getErreurs();
	String getResultat();

	User selectUserConnexion(String login);

	User selectUserAllArgs(String login);

	User lireLoginUser(String login);

	User connecterUtilisateurLoginMdp(String login, String motDePasse);

	User connexionUtilisateurSimplyBDD(String login, String password);
	
	
	User rechercherUserLogin(String login);

	User rechercherUserPassword(String password);

	User connexionUtilisateurTESTING(String login, String password);

	boolean connexionUtilisateurTestBoolean(String login, String password);
}
