package interaction.entite;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import contenu.entite.Article;

import interaction.enume.StatutCommande;
import utilisateurs.entite.User;

@Entity
public class Commande {

	// @GenericGenerator
	//@Id @GeneratedValue(strategy = GenerationType.AUTO)
	
	//@Id @GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Id @GeneratedValue(strategy=GenerationType.TABLE, generator = "native")
	@Column(name="id",updatable=false,nullable=false)
	private Long id;

	

	private String prix;
	
	private Date date_creation, date_envoi;
	
	protected StatutCommande statutCommande;
	

	
	@OneToOne(optional = false)
	@JoinColumn(name="article_id")
	private Article article;
	
	@OneToOne(optional = false)
	@JoinColumn(name="acheteur_id")
	private User acheteurUser;
	
	@OneToOne(optional = false)
	@JoinColumn(name="vendeur_id")
	private User vendeurUser;
	
	public Commande() {
		// TODO Auto-generated constructor stub
	}

	public Commande(Long commande_id, String commande_prix, 
			Article article, User user_acheteur, User user_vendeur, StatutCommande status) {
		super();
		this.id = commande_id;
		this.prix = commande_prix;

		
		this.article = article;

		this.statutCommande = status;
	}
	
	
	
	public Commande(String commande_prix, Date dateCreation, Date dateEnvoi, StatutCommande status, 
			Article article, User acheteurUser, User vendeurUser) {
		super();
		this.prix = commande_prix;
		this.date_creation = dateCreation;
		this.date_envoi = dateEnvoi;
		this.statutCommande = status;
	
		this.article = article;
		this.acheteurUser = acheteurUser;
		this.vendeurUser = vendeurUser;
	}



	public Date getDateCreation() {
		return date_creation;
	}

	public void setDateCreation(Date dateCreation) {
		this.date_creation = dateCreation;
	}

	public Date getDateEnvoi() {
		return date_envoi;
	}

	public void setDateEnvoi(Date dateEnvoi) {
		this.date_envoi = dateEnvoi;
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

	

	public Long getCommande_id() {
		return id;
	}

	public void setCommande_id(Long commande_id) {
		this.id = commande_id;
	}

	public String getCommande_prix() {
		return prix;
	}

	public void setCommande_prix(String commande_prix) {
		this.prix = commande_prix;
	}
	
	

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}
	

	public StatutCommande getStatus() {
		return statutCommande;
	}

	public void setStatus(StatutCommande status) {
		this.statutCommande = status;
	}
	
	
	
}
