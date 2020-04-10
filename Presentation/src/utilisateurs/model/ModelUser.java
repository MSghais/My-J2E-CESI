package utilisateurs.model;

import java.util.ArrayList;
import java.util.List;


import utilisateurs.entite.User;



public class ModelUser {
	private List<User>users;

	private List<User> exclus;
	

	public ModelUser() {
		users = new ArrayList<>();
	
		exclus = new ArrayList<>();
	}


	public List<User> getUsers() {
		return users;
	}


	public void setUsers(List<User> users) {
		this.users = users;
	}




	public List<User> getExclus() {
		return exclus;
	}


	public void setExclus(List<User> exclus) {
		this.exclus = exclus;
	}


	@Override
	public String toString() {
		return "ModelUser [users=" + users + ", exclus=" + exclus + "]";
	}

	
}
