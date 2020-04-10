package utilisateurs.metier;

import java.util.Map;

import javax.ejb.Local;
import javax.servlet.http.HttpServletRequest;

import utilisateurs.entite.User;
import utilisateurs.entite.Utilisateur;

@Local
public interface ConnexionFormInterface {


	
	
	String getValeurChamp(HttpServletRequest request, String nomChamp);
	
	void setErreur(String champ, String message);
	
	Map<String, String> getErreurs();
	String getResultat();



	String getCookieValue(HttpServletRequest request, String nom);

	
	
	Utilisateur connecterUtilisateur(HttpServletRequest request);
	
	User connecterUser(HttpServletRequest request);
	


	void validationPassword(String password) throws Exception;


	void validationLogin(String login) throws Exception;


	void findLoginUser(String login) throws Exception;


	void findUserNotBDD(String login, String password) throws Exception;


	void findUserBDD(String login, String password) throws Exception;
	


	User keepUserBDD(String login, String password) throws Exception;

	User keepUserBDDSimply(String login, String password);

	boolean connecterUserQuestion(HttpServletRequest request);
	
}
