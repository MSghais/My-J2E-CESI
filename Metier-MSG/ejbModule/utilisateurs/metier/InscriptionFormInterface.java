package utilisateurs.metier;

import java.util.Map;

import javax.ejb.Local;
import javax.servlet.http.HttpServletRequest;

import utilisateurs.entite.User;


@Local
public interface InscriptionFormInterface {


	
	void validationLogin(String login) throws Exception; ;
	void validationEmail(String email) throws Exception; ;
	void validationUsername(String usernale) throws Exception;
	void validationMotsDePasse(String motDePasse, String confirmation) throws Exception;
	
	//void validationMotDePasse(String motdepasse) throws Exception;;
	
	User inscrireUser(HttpServletRequest request);
	String getValeurChamp(HttpServletRequest request, String nomChamp);
	
	void setErreur(String champ, String message);
	
	Map<String, String> getErreurs();
	String getResultat();

	void persisterUser(User user);
	
	
	void userAlreadyExistHard(String login, String password) throws Exception;
	
	
	
}
