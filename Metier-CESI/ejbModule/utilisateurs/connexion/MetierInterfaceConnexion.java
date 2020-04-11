package utilisateurs.connexion;

import java.util.Collection;

import javax.ejb.Local;

import utilisateurs.entite.User;

@Local
public interface MetierInterfaceConnexion {

	boolean connexionUtilisateur(String login, String motDePasse);

	User getUtilisateur(String login);
	Collection<User> getAllUtilisateur();
	void addUtilisateur(User utilisateur);

	User findUserNotBDD(String login, String password) throws Exception;

	void setErreur(String champ, String message);

	User findUserBDD(String login, String password) throws Exception;

	

	User selectUserConnexion(String login);

	User selectUserAllArgs(String login);

	User lireLoginUser(String login);

	User connecterUtilisateurLoginMdp(String login, String motDePasse);

	User connexionUtilisateurSimplyBDD(String login, String password);
}
