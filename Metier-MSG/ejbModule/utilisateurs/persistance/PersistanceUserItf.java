package utilisateurs.persistance;

import java.util.List;

import utilisateurs.entite.User;

public interface PersistanceUserItf {
	
	void persisterUser(User user);
	
	
	List<User> lireTousUser();
	
	User lireUser(Long id);
	User lireUserName(String name);
	
	User lireLoginUser(String login);
	User lireEmailUser(String email);



	User selectUserName(String username);


	User selectUserLogin(String login);


	User selectEmailUSer(String email);

	List selectCountLoginUser(String login);


	User selectLoginUser(String login);


	User connecterUtilisateurLoginMdp(String login, String passwd) ;


	User rechercherUserLoginMdp(String login, String passwd);


	User rechercherUserPassword(String password);


	User rechercherUserLogin(String login);


	User rechercherUserIndex(Long index);


	User lireUserLogin(String login);




}
