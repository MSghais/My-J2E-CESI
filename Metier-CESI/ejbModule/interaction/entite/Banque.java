package interaction.entite;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Banque {

	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long banqueId;
	private String nom, adresse;

	public void validationBanquaire(String cb) throws Exception  {
		
		if( cb.length() < 10 || cb.isEmpty() ) {
			
		}
		
	}
	
	
	public Banque(String nom, String adresse) {
		super();
		this.nom = nom;
		this.adresse = adresse;
	}
	
	

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	
	
	
	
	
	
}
