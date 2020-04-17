package contenu.entite;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
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


	@Id @GeneratedValue
	@Column(name="theme_intitule")
	private String intitule;

	public Theme() {
		
	}

	public Theme(String theme_intitule) {
		super();
		
		this.intitule = theme_intitule;
	}

	public String getTheme_intitule() {
		return intitule;
	}

	public void setTheme_intitule(String theme_intitule) {
		this.intitule = theme_intitule;
	}
	
	
	
	
}
