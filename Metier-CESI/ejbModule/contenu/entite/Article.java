package contenu.entite;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import contenu.enume.StatutArticle;
import utilisateurs.entite.User;



@Entity
public class Article {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="article_id")
	private Long id;

	private Date date;
	private String titre,  image,  url, description,   contenu, frais, prix;
	
	@OneToOne(optional = true)
	@JoinColumn(name="theme_id")
	private Theme theme;

	@OneToOne(optional = true)
	@JoinColumn(name="vendeur_id")
	private User vendeur;
	
	
	protected StatutArticle status;
	
	public void addUser(User user) {
		
		this.setVendeur(user);
	}

	public Article() {}
	
	

	public Article(String art_titre, String art_url, String art_description, String art_contenu
			) {
		super();
		this.titre = art_titre;
		this.url = art_url;
		this.description = art_description;
		this.contenu = art_contenu;
	
		
	}


	public Article( String art_description, String art_contenu, String art_url) {
		super();
		this.description = art_description;
		this.contenu = art_contenu;
		this.url = art_url;
	}


public Article(String nom, Theme theme, String description) {

		
		this.theme = theme;
		this.description = description;
		
	}
	
	

	public Article(Theme theme, String description, String contenu) {
		super();
		this.theme = theme;
		
		this.description = description;
		this.contenu = contenu;
	}




	public Article(Theme theme, String art_description, String art_contenu, String art_img) {
		super();
		this.theme = theme;
		this.description = art_description;
		this.contenu = art_contenu;
		this.url = art_img;
	}



	public Article(Long id, Date date, String titre, String image, String url, String description, String contenu,
			String frais, String prix, Theme theme, User user_vendeur, StatutArticle status) {
		super();
		this.id = id;
		this.date = date;
		this.titre = titre;
		this.image = image;
		this.url = url;
		this.description = description;
		this.contenu = contenu;
		this.frais = frais;
		this.prix = prix;
		this.theme = theme;
		this.vendeur = user_vendeur;
		this.status = status;
	}



	public Article(Long id, Date date, String titre, String image, String url, String description, String contenu,
			Theme theme, User user_vendeur, StatutArticle status) {
		super();
		this.id = id;
		this.date = date;
		this.titre = titre;
		this.image = image;
		this.url = url;
		this.description = description;
		this.contenu = contenu;
		this.theme = theme;
		this.vendeur = user_vendeur;
		this.status = status;
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public Date getDate() {
		return date;
	}



	public void setDate(Date date) {
		this.date = date;
	}



	public String getTitre() {
		return titre;
	}



	public void setTitre(String titre) {
		this.titre = titre;
	}



	public String getImage() {
		return image;
	}



	public void setImage(String image) {
		this.image = image;
	}



	public String getUrl() {
		return url;
	}



	public void setUrl(String url) {
		this.url = url;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public String getContenu() {
		return contenu;
	}



	public void setContenu(String contenu) {
		this.contenu = contenu;
	}



	public Theme getTheme() {
		return theme;
	}



	public void setTheme(Theme theme) {
		this.theme = theme;
	}


	

	public String getFrais() {
		return frais;
	}



	public void setFrais(String frais) {
		this.frais = frais;
	}



	public String getPrix() {
		return prix;
	}



	public void setPrix(String prix) {
		this.prix = prix;
	}



	public User getVendeur() {
		return vendeur;
	}



	public void setVendeur(User user_vendeur) {
		this.vendeur = user_vendeur;
	}



	public StatutArticle getStatus() {
		return status;
	}



	public void setStatus(StatutArticle status) {
		this.status = status;
	}



	


	


	
	
	
	
}