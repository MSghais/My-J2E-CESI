package utilisateurs.entite;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PersistenceContext;

import contenu.entite.Article;


import contenu.entite.Theme;


//@Inheritance
/* @DiscriminatorColumn(
		
		name="USER_TYPE",
		discriminatorType = DiscriminatorType.STRING
		) */
//@MappedSuperclass
/*@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = â€œidâ€�, updatable = false, nullable = false)*/


/*  @Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
		
		name="USER_TYPE",
		discriminatorType = DiscriminatorType.STRING
		) */

//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)

//@MappedSuperclass

//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)

//@MappedSuperclass

//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)

//@MappedSuperclass




//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
// @Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@MappedSuperclass

@Entity
public class User {
	
	//@PersistenceContext(name="UP_ETUDIANT")
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long user_id;
	
	private String username;
	private String login;
	private String email;
	private String password;
	

	protected Role role;
	
	
	@OneToMany
	@JoinTable(name="vendeur_article", joinColumns=@JoinColumn(name="user_id"),
	inverseJoinColumns=@JoinColumn(name="fk_vendeur_id")
			)
	private Collection<Article> ventesArticles = new ArrayList<Article>();
	
	@OneToMany
	@JoinTable(name="commandes_article", joinColumns=@JoinColumn(name="user_id"),
	inverseJoinColumns=@JoinColumn(name="fk_userCommande_id")
			)
	private Collection<Article> commandesArticles = new ArrayList<Article>();
	
	
	@OneToMany
	@JoinTable(name="achat_article", joinColumns=@JoinColumn(name="user_id"),
	inverseJoinColumns=@JoinColumn(name="fk_acheteur_id")
			)
	private Collection<Article> achatArticles = new ArrayList<Article>();
	
	
	public void addArticles(Article article) {
		
		ventesArticles.add(article);
	}
	
	/*
	@OneToOne(optional = true)
	@JoinColumn(name="teacher_id")
	private Teacher teacher;
	
	
	*/
	
	/*@JoinTable(name="user_teacher", joinColumns=@JoinColumn(name="user_id"),
	inverseJoinColumns=@JoinColumn(name="fk_pseudo_teacher")
			)*/

/*
	@OneToMany
	@JoinTable(name="user_theme", joinColumns=@JoinColumn(name="user_id"),
	inverseJoinColumns=@JoinColumn(name="fk_theme_niveau_user")
			)
	private Collection<Theme> user_theme = new ArrayList<Theme> ();
	
	
	
	@OneToMany
	@JoinTable(name="user_skills", joinColumns=@JoinColumn(name="user_id"),
	inverseJoinColumns=@JoinColumn(name="fk_skills_id")
			)
	private Collection<Skills> user_skills = new ArrayList<Skills>();
*/
	
	
	
	
	


	
	
 	public User(Long user_id, String username, String login, String email, String password) {
		super();
		this.user_id = user_id;
		this.username = username;
		this.login = login;
		this.email = email;
		this.password = password;
	}

	public User(Long user_id, String username, String login, String email, String password, Role role) {
		super();
		this.user_id = user_id;
		this.username = username;
		this.login = login;
		this.email = email;
		this.password = password;
		this.role = role;
	}
	
	public User( String username, String login, String email, String password, Role role) {
		super();
	
		this.username = username;		
		this.login = login;
		this.password = password;
		this.email = email;
	
		this.role = role;
	}

	public User(String login, String password) {
		super();
		this.login = login;
		this.password = password;
	}

	public User() {
		
	}
	
	public User(String username, String login, String email, String password) {
		this.username = username;
		this.login = login;
		this.email = email;
		this.password = password;
	}



	public User(Long user_id, String username, String login, String email, String password,
			Collection<Article> articlesLus, Collection<Article> articles) {
		super();
		this.user_id = user_id;
		this.username = username;
		this.login = login;
		this.email = email;
		this.password = password;
	
	}



	public User(Long user_id, String username, String login, String email, String password,
			Collection<Theme> user_theme, 
			Collection<Article> articles_finis, Collection<Article> articles_now) {
		super();
		this.user_id = user_id;
		this.username = username;
		this.login = login;
		this.email = email;
		this.password = password;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}


	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Collection<Article> getVentesArticles() {
		return ventesArticles;
	}

	public void setVentesArticles(Collection<Article> ventesArticles) {
		this.ventesArticles = ventesArticles;
	}

	public Collection<Article> getCommandesArticles() {
		return commandesArticles;
	}

	public void setCommandesArticles(Collection<Article> commandesArticles) {
		this.commandesArticles = commandesArticles;
	}

	public Collection<Article> getAchatArticles() {
		return achatArticles;
	}

	public void setAchatArticles(Collection<Article> achatArticles) {
		this.achatArticles = achatArticles;
	}

	
	
	
}
