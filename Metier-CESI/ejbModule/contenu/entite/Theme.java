package contenu.entite;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
public class Theme {


	//private List<String> sousRubriques;
	//private List<String> secteurVisee;
	@Id @GeneratedValue
	private int theme_id;
	
	private String theme_intitule;
	
	private String theme_secteur;
	
	
	private String theme_description;
	
	@OneToOne(optional = true)
	@JoinColumn(name="article_id")
	private Article article;
	
	
	
	public Theme() {
		
	}

	
	public Theme(  String secteurVisee) {
		super();
		
		
		this.theme_secteur = secteurVisee;
		
	
	
	}
	
	public Theme(String nameRubrique, String secteurVisee, String description) {
		super();
		
		
		this.theme_secteur = secteurVisee;
		this.theme_description = description;
		
	
	}
	

	



	public Theme(String theme_secteur, String theme_description, Article article
			) {
		super();
		this.theme_secteur = theme_secteur;
		this.theme_description = theme_description;
		this.article = article;

	}

	public String getSecteurVisee() {
		return theme_secteur;
	}

	public void setSecteurVisee(String secteurVisee) {
		this.theme_secteur = secteurVisee;
	}


	public String getDescription() {
		return theme_description;
	}

	public void setDescription(String description) {
		this.theme_description = description;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public String getTheme_intitule() {
		return theme_intitule;
	}

	public void setTheme_intitule(String theme_intitule) {
		this.theme_intitule = theme_intitule;
	}
	
	
	
	
	/*
	 * public List<String> getSousRubriques() { return sousRubriques; }
	 * 
	 * public void setSousRubriques(List<String> sousRubriques) { this.sousRubriques
	 * = sousRubriques; }
	 * 
	 * public List<String> getSecteurVisee() { return secteurVisee; }
	 * 
	 * public void setSecteurVisee(List<String> secteurVisee) { this.secteurVisee =
	 * secteurVisee; }
	 */
	
	
}
