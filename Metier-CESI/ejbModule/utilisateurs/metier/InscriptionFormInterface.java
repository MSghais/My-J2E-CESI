package utilisateurs.metier;

import java.util.Map;

import javax.ejb.Local;
import javax.servlet.http.HttpServletRequest;

import utilisateurs.entite.User;
import utilisateurs.entite.Utilisateur;


@Local
public interface InscriptionFormInterface {


	
	void validationLogin(String login) throws Exception; ;
	void validationEmail(String email) throws Exception; ;
	void validationUsername(String usernale) throws Exception;
	//void validationMotDePasse(String motdepasse) throws Exception;;
	
	Utilisateur connecterUtilisateur(HttpServletRequest request);
	
	User inscrireUser(HttpServletRequest request);
	
	
	String getValeurChamp(HttpServletRequest request, String nomChamp);
	
	void setErreur(String champ, String message);
	
	Map<String, String> getErreurs();
	String getResultat();
	
	void persisterUtilisateur(Utilisateur utilisateur);
	
	void persisterUser(User user);
	
	
	void validationMotsDePasse(String motDePasse, String confirmation) throws Exception;
	
	
	
}
