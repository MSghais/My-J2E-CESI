package interaction;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import contenu.entite.Article;
import contenu.entite.Theme;
import contenu.enume.Status;
import utilisateurs.entite.User;

@Entity
public class Commande {

	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long commande_id;



	
	private String commande_prix, commande_cb, commande_code;
	
	private Date date_creation, date_envoi;
	
	protected Status status;
	
	@OneToOne(optional = true)
	@JoinColumn(name="theme_id")
	private Theme theme;
	
	@OneToOne(optional = false)
	@JoinColumn(name="article_id")
	private Article article;
	
	@OneToOne(optional = false)
	@JoinColumn(name="acheteur_id")
	private User user_acheteur;
	
	@OneToOne(optional = false)
	@JoinColumn(name="vendeur_id")
	private User user_vendeur;

	public Commande(Long commande_id, String commande_prix, String commande_cb, String commande_code, Theme theme,
			Article article, User user_acheteur, User user_vendeur, Status status) {
		super();
		this.commande_id = commande_id;
		this.commande_prix = commande_prix;
		this.commande_cb = commande_cb;
		this.commande_code = commande_code;
		this.theme = theme;
		this.article = article;
		this.user_acheteur = user_acheteur;
		this.user_vendeur = user_vendeur;
		
		this.status = status;
	}
	

	
	


	
	
	public Long getCommande_id() {
		return commande_id;
	}

	public void setCommande_id(Long commande_id) {
		this.commande_id = commande_id;
	}

	public String getCommande_prix() {
		return commande_prix;
	}

	public void setCommande_prix(String commande_prix) {
		this.commande_prix = commande_prix;
	}

	
	
	public Date getDate_creation() {
		return date_creation;
	}

	public void setDate_creation(Date date_creation) {
		this.date_creation = date_creation;
	}

	public Date getDate_envoi() {
		return date_envoi;
	}

	public void setDate_envoi(Date date_envoi) {
		this.date_envoi = date_envoi;
	}

	public String getCommande_cb() {
		return commande_cb;
	}

	public void setCommande_cb(String commande_cb) {
		this.commande_cb = commande_cb;
	}

	public String getCommande_code() {
		return commande_code;
	}

	public void setCommande_code(String commande_code) {
		this.commande_code = commande_code;
	}

	public Theme getTheme() {
		return theme;
	}

	public void setTheme(Theme theme) {
		this.theme = theme;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}
	

	


	

	public User getUser_acheteur() {
		return user_acheteur;
	}

	public void setUser_acheteur(User user_acheteur) {
		this.user_acheteur = user_acheteur;
	}

	public User getUser_vendeur() {
		return user_vendeur;
	}

	public void setUser_vendeur(User user_vendeur) {
		this.user_vendeur = user_vendeur;
	}
	
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	
	
}
