package utilisateurs.metier;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.Stateless;

import utilisateurs.entite.User;

@Stateless
public class Metier {
	private Map<String,User> utilisateurs;

	public Metier() {
		utilisateurs = new HashMap<String, User>();
		initialiser();
	}
	public boolean connexionUtilisateur(String login,String passwd) {
		if(utilisateurs.containsKey(login)&& utilisateurs.get(login).getPassword().equals(passwd) )
			return true;
		else return false;
	}
	public User getUtilisateur(String login) {
		return utilisateurs.get(login);
	}
	public Collection<User> getAllUtilisateur(){
		return utilisateurs.values();
	}
	public void addUtilisateur(User utilisateur) {
		utilisateurs.put(utilisateur.getLogin(), utilisateur);
	}
	@Override
	public String toString() {
		return "Metier [utilisateurs=" + utilisateurs + "]";
	}
	public void initialiser() {
		User u = new User("login1", "passwd1");
		utilisateurs.put(u.getLogin(), u);
		u = new User("login2", "passwd2");
		utilisateurs.put(u.getLogin(), u);
	}
	
	
}
