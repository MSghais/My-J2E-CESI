package contenu.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import interaction.entite.Commande;





public class ModelCommande {
	
	private List<Commande> commandesListe;
	

	private Map<Long, Commande> commandesListeMap;
	
    private Commande commande;


	public ModelCommande() {

	}


	public ModelCommande(List<Commande> commandes, Commande commande) {
		super();
		this.commandesListe = new ArrayList<Commande>();

		this.commandesListeMap = new HashMap<Long, Commande>();
		this.commande = commande;
	
	}


	
	public Map<Long, Commande> getCommandesListeMap() {
		return commandesListeMap;
	}


	public void setCommandesListeMap(Map<Long, Commande> commandesListeMap) {
		this.commandesListeMap = commandesListeMap;
	}

	

	public List<Commande> getCommandesListe() {
		return commandesListe;
	}


	public void setCommandesListe(List<Commande> commandesListe) {
		this.commandesListe = commandesListe;
	}


	public Commande getCommande() {
		return commande;
	}


	public void setCommande(Commande commande) {
		this.commande = commande;
	}


	@Override
	public String toString() {
		return "ModelCommande [commandes=" + commandesListe + "]";
	}


	

	

	
	
}
