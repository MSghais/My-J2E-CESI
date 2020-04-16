package utilisateurs.entite;

import java.util.List;

public enum Role {

	Business,  Acheteur, Vendeur, Administrateur;




	public String action() {
		switch(this) {
			case Administrateur : return "cinema";
			case Business : return "determiner";
			default : return "travailler";
		}
 	}

	
	public static List<Role> getRole(){
		List<Role> allValues = null;
		for(Role r : Role.values()) {
			
			allValues.add(r);
		}
		return allValues;
	}


}
