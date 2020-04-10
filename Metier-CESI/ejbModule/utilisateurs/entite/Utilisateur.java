package utilisateurs.entite;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


public class Utilisateur {

	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long user_id;
	
	 private String username;
	    private String login;
	    private String email;
	    private String motDePasse;

	    protected Role role;
	    
	    public Utilisateur(String username, String login, String email, String motDePasse) {
			super();
			this.username = username;
			this.login = login;
			this.email = email;
			this.motDePasse = motDePasse;
		}
	
		public Utilisateur() {
			super();
		}
		
		public void setEmail(String email) {
		this.email = email;
	    }
	    public String getEmail() {
		return email;
	    }

	    public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	    }
	    public String getMotDePasse() {
		return motDePasse;
	    }
		public Long getUser_id() {
			return user_id;
		}
		public void setUser_id(Long user_id) {
			this.user_id = user_id;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getLogin() {
			return login;
		}
		public void setLogin(String login) {
			this.login = login;
		}

	    
	
	}

