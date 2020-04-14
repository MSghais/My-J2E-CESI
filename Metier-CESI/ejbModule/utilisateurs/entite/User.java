package utilisateurs.entite;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
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
import interaction.entite.Commande;



/*  @Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
		
		name="USER_TYPE",
		discriminatorType = DiscriminatorType.STRING
		) */

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
	private List<Article >ventesArticles;
	

	
	
	@OneToMany
	private List<Article> achatsArticles;
	
	/*  @OneToMany(mappedBy="user")
	@JoinTable(name="vendeur_article", joinColumns=@JoinColumn(name="user_id"),
	inverseJoinColumns=@JoinColumn(name="fk_article_id")
			)
	private List<Article >ventesArticles;
	

	
	
	@OneToMany(mappedBy="user")
	@JoinTable(name="achat_article", joinColumns=@JoinColumn(name="user_id"),
	inverseJoinColumns=@JoinColumn(name="fk_article_id")
			)
	private List<Article> achatsArticles; */
	/*
	 * @OneToMany
	 * 
	 * @JoinTable(name="vendeur_article", joinColumns=@JoinColumn(name="user_id"),
	 * inverseJoinColumns=@JoinColumn(name="fk_article_id") ) private Map<Long,
	 * Article> ventesArticles;
	 * 
	 * 
	 * 
	 * 
	 * @OneToMany
	 * 
	 * @JoinTable(name="achat_article", joinColumns=@JoinColumn(name="user_id"),
	 * inverseJoinColumns=@JoinColumn(name="fk_article_id") ) private Map<Long,
	 * Article> achatsArticles;
	 */

	
	/*  	
	@OneToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name="commandes_article", joinColumns=@JoinColumn(name="user_id"),
	inverseJoinColumns=@JoinColumn(name="fk_commande_id")
			)
	private List<Commande> commandesArticles;
	 */
	
	/* @OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name="commandes_article", joinColumns=@JoinColumn(name="user_id"),
	inverseJoinColumns=@JoinColumn(name="fk_article_id")
			)
	private Map<Long, Commande> commandesArticles;  */
	
	/* 	@OneToMany(mappedBy= "id")
	private Map<Long, Article> ventesArticles;
	
	@OneToMany(mappedBy= "id")
	private Map<Long, Article> commandesArticles;
	
	
	@OneToMany(mappedBy= "id")
	private Map<Long, Article> achatsArticles; */
	
	/*
	 * public void addArticlesVentes(Article article) {
	 * 
	 * ventesArticles.put(this.user_id, article); }
	 * 
	 * public void addAchatArticles(Article article) { // TODO Auto-generated method
	 * stub achatsArticles.put(this.user_id, article); }
	 */
	
public void addArticlesVentes(Article article) {
		
		ventesArticles.add(article);
	}
	
	public void addAchatArticles(Article article) {
		// TODO Auto-generated method stub
		achatsArticles.add(article);
	}
	
	/*
	 * public void addCommandeArticles(Commande commande) { // TODO Auto-generated
	 * method stub commandesArticles.put(this.user_id, commande); }
	 */
	public void addCommandeArticle(Commande commande) {
		// TODO Auto-generated method stub
	//	this.commandesArticles.add(commande);
	}
	
	
	public void addArticleAchat(Article article) {
		// TODO Auto-generated method stub
		this.achatsArticles.add(article);
	}
	
	/*  WITH COLLECTIONS
	@OneToMany
	@JoinTable(name="vendeur_article", joinColumns=@JoinColumn(name="user_id"),
	inverseJoinColumns=@JoinColumn(name="fk_article_id")
			)
	private Collection<Article> ventesArticles = new ArrayList<Article>(); */
	
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

	public User(Long user_id, String username, String login, Role role) {
		super();
		this.user_id = user_id;
		this.username = username;
		this.login = login;
		this.role = role;
	}

	public User(Long user_id, String login, Role role) {
		super();
		this.user_id = user_id;
		this.login = login;
		this.role = role;
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
			HashMap<Long, Article> ventesArticles, HashMap<Long, Article> achatsArticles) {
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

	public List<Article> getVentesArticles() {
		return ventesArticles;
	}

	public void setVentesArticles(List<Article> ventesArticles) {
		this.ventesArticles = ventesArticles;
	}



	public List<Article> getAchatArticles() {
		return achatsArticles;
	}

	public void setAchatArticles(List<Article> achatsArticles) {
		this.achatsArticles = achatsArticles;
	}

	public User(Long user_id, String username, String login, String email, String password, Role role,
			Map<Long, Article> ventesArticles, Map<Long, Article> achatsArticles,
			Map<Long, Article> commandesArticles) {
		super();
		this.user_id = user_id;
		this.username = username;
		this.login = login;
		this.email = email;
		this.password = password;
		this.role = role;
		this.ventesArticles =  new ArrayList<Article>();
		//this.commandesArticles = new ArrayList<Commande>();
		this.achatsArticles = new ArrayList<Article>();
	}

	/*
	 * public List<Commande> getCommandesArticles() { return commandesArticles; }
	 * 
	 * public void setCommandesArticles(List<Commande> commandesArticles) {
	 * this.commandesArticles = commandesArticles; }
	 */

/* 	public User(Long user_id, String username, String login, String email, String password, Role role,
			Collection<Article> ventesArticles, Collection<Article> commandesArticles,
			Collection<Article> achatArticles) {
		super();
		this.user_id = user_id;
		this.username = username;
		this.login = login;
		this.email = email;
		this.password = password;
		this.role = role;
		this.ventesArticles =  new HashMap<Long, Article>();
		this.commandesArticles = new ArrayList<Article>();
		this.achatArticles = new ArrayList<Article>();
	} */
	
	
	
}
