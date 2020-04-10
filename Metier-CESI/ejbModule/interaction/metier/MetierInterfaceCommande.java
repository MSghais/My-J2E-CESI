package interaction.metier;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;
import javax.servlet.http.HttpServletRequest;

import contenu.entite.Article;
import interaction.entite.Commande;
import utilisateurs.entite.User;
import utilisateurs.entite.Utilisateur;




@Local
public interface MetierInterfaceCommande {
	// PROMOTIONS
	void creerCommande(Commande commande);
	Commande lireCommande(Long id);
	
	void mettreAJourCommande(Commande commande);
	void supprimerCommande(Commande commande);
	List<Commande> lireTousCommande();
	void persisterCommande(Commande commande);
	

	void validationTitre(String titre) throws Exception; ;
	void validationContenu(String contenu) throws Exception; ;
	void validationUrl(String url) throws Exception;
	void validationDescription(String description) throws Exception;
	void validationTheme(String theme) throws Exception;
	void validationSkills(String skills) throws Exception;
	
	
	
	//void validationMotDePasse(String motdepasse) throws Exception;;
	


	String getValeurChamp(HttpServletRequest request, String nomChamp);
	
	void setErreur(String champ, String message);
	
	Map<String, String> getErreurs();
	String getResultat();
	Commande creerCommandeRequest(HttpServletRequest request);
	
	
	
	/*
	User connecterUser(HttpServletRequest request);
	*/
	
	//void ajouterAbsence(Long etudiantId);
	//void ajouterRetard(Long etudiantId);
	//void ajouterAbsence(Etudiant etudiant);


}
