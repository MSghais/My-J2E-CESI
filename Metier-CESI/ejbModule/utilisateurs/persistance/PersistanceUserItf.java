package utilisateurs.persistance;

import java.util.List;

import utilisateurs.entite.User;
import utilisateurs.entite.Utilisateur;



public interface PersistanceUserItf {
	
	void persisterUser(User user);
	
	
	List<User> lireTousUser();
	
	void persisterUserTeacher(User user);


	User lireUser(Long id);
	User lireUserName(String name);
	
	User lireLoginUser(String login);
	User lireEmailUser(String email);
	void persisterUtilisateur(Utilisateur utilisateur);


	User selectUserName(String username);


	User selectUserLogin(String login);


	User selectEmailUSer(String email);

	List selectCountLoginUser(String login);


	User selectLoginUser(String login);


	User connecterUtilisateurLoginMdp(String login, String passwd);



}
