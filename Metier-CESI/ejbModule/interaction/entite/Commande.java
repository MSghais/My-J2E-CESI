package interaction.entite;

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
	private Long commandeId;



	
	private String commandePrix;
	
	//commande_cb, commande_code;
	
	private Date dateCreation, dateEnvoi;
	
	protected Status status;
	
	@OneToOne(optional = true)
	@JoinColumn(name="theme_id")
	private Theme theme;
	
	@OneToOne(optional = false)
	@JoinColumn(name="article_id")
	private Article article;
	
	@OneToOne(optional = false)
	@JoinColumn(name="acheteur_id")
	private User acheteurUser;
	
	@OneToOne(optional = false)
	@JoinColumn(name="vendeur_id")
	private User vendeurUser;
	

	public Commande(Long commande_id, String commande_prix, Theme theme,
			Article article, User user_acheteur, User user_vendeur, Status status) {
		super();
		this.commandeId = commande_id;
		this.commandePrix = commande_prix;

		this.theme = theme;
		this.article = article;
		
		
		this.status = status;
	}
	
	
	
	public Commande(String commande_prix, Date dateCreation, Date dateEnvoi, Status status, Theme theme,
			Article article, User acheteurUser, User vendeurUser) {
		super();
		this.commandePrix = commande_prix;
		this.dateCreation = dateCreation;
		this.dateEnvoi = dateEnvoi;
		this.status = status;
		this.theme = theme;
		this.article = article;
		this.acheteurUser = acheteurUser;
		this.vendeurUser = vendeurUser;
	}



	public Date getDateCreation() {
		return dateCreation;
	}



	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}



	public Date getDateEnvoi() {
		return dateEnvoi;
	}



	public void setDateEnvoi(Date dateEnvoi) {
		this.dateEnvoi = dateEnvoi;
	}



	public User getAcheteurUser() {
		return acheteurUser;
	}



	public void setAcheteurUser(User acheteurUser) {
		this.acheteurUser = acheteurUser;
	}



	public User getVendeurUser() {
		return vendeurUser;
	}



	public void setVendeurUser(User vendeurUser) {
		this.vendeurUser = vendeurUser;
	}



	public Commande() {
		// TODO Auto-generated constructor stub
	}



	public Long getCommande_id() {
		return commandeId;
	}

	public void setCommande_id(Long commande_id) {
		this.commandeId = commande_id;
	}

	public String getCommande_prix() {
		return commandePrix;
	}

	public void setCommande_prix(String commande_prix) {
		this.commandePrix = commande_prix;
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
	

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	
	
}
