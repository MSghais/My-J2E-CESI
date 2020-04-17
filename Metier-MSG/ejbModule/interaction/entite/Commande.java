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
	
	
	//@Id @GeneratedValue(strategy=GenerationType.SEQUENCE)
	/*
	 * @Id @GeneratedValue(strategy=GenerationType.AUTO, generator = "native")
	 * 
	 * @Column(name="id",updatable=false,nullable=false)
	 */
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	/* 	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id; */
	

	private String prix;
	
	private Date date_creation, date_envoi;
	
	protected StatutCommande statutCommande;
	

	
	@OneToOne
	@JoinColumn(name="article_id")
	private Article article;
	
	@OneToOne
	@JoinColumn(name="acheteur_id")
	private User acheteur;
	
	
	/*
	 * @OneToOne(optional = true)
	 * 
	 * @JoinColumn(name="vendeur_id") private User vendeurUser;
	 */
	
	public Commande() {
		// TODO Auto-generated constructor stub
	}
	
	public Commande(String prix,Article article, User acheteurUser) {
		super();
		this.prix = prix;
		this.article = article;
		this.acheteur = acheteurUser;
	}
	
	public Commande(String prix, StatutCommande statutCommande, Article article, User acheteurUser) {
		super();
		this.prix = prix;
		this.statutCommande = statutCommande;
		this.article = article;
		this.acheteur = acheteurUser;
	}


	public Commande(Long id) {
		// TODO Auto-generated constructor stub
		this.id = id;
	}


	public Commande(Article article, User acheteur) {
		// TODO Auto-generated constructor stub
		this.article = article;
		this.acheteur = acheteur;
	}

	
	
	public Commande(Long commande_id, String commande_prix, 
			Article article, User user_acheteur, User user_vendeur, StatutCommande status) {
		super();
		this.id = commande_id;
		this.prix = commande_prix;

		
		this.article = article;

		this.statutCommande = status;
	}
	

	public Commande( String commande_prix, 
			Article article, User user_acheteur, User user_vendeur, StatutCommande status) {
		super();

		this.prix = commande_prix;

		
		this.article = article;

		this.statutCommande = status;
	}
	

	public Commande( String commande_prix, 
			Article article, User user_acheteur, User user_vendeur) {
		super();

		this.prix = commande_prix;

		
		this.article = article;
		this.acheteur = user_acheteur;
		
		// this.vendeurUser= user_vendeur;

	}
	
	
	public Commande(String commande_prix, Date dateCreation, Date dateEnvoi, StatutCommande status, 
			Article article, User acheteurUser, User vendeurUser) {
		super();
		this.prix = commande_prix;
		this.date_creation = dateCreation;
		this.date_envoi = dateEnvoi;
		this.statutCommande = status;
	
		this.article = article;
		this.acheteur = acheteurUser;
		// this.vendeurUser = vendeurUser;
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
		return acheteur;
	}


	public void setAcheteurUser(User acheteurUser) {
		this.acheteur = acheteurUser;
	}

	/*
	 * public User getVendeurUser() { // return vendeurUser; }
	 * 
	 * public void setVendeurUser(User vendeurUser) { //this.vendeurUser =
	 * vendeurUser; }
	 */
	

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
