package contenu.enume;

public enum Remise {
	
	COURANTE(0.05, "Remise de 5%"), 
	FIDELITE(0.07, "Remise de 7%"),
	EXCEPTIONNELLE(0.10, "Remise de 10%");

	private final double taux;
	private final String libelle;
	private Remise(double taux, String libelle) {
		this.taux = taux;
		this.libelle = libelle;
	}
	public double getTaux() {
		return this.taux;
	}
	public String getLibelle() {
	    return this.libelle;
	}
	public double calculer(double valeur) {
		return (valeur * taux);
	}
}
