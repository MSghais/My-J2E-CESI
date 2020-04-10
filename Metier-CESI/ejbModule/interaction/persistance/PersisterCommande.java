package interaction.persistance;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import contenu.entite.Article;
import interaction.entite.Commande;
import utilisateurs.entite.User;


@Stateless
public class PersisterCommande implements PersistanceCommandeItf{
	
	
	@PersistenceContext(unitName="UP_ETUDIANT")
	
	private EntityManager entityManager;
	
	
	@Override
	public void persisterUserCommande(User user, Commande article) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public Commande selectArticleTitre(String titre) {
		// TODO Auto-generated method stub
		Query req = entityManager.createQuery("select c from Commande c  where c.art_titre =: titre ");
		req.setParameter("titre", titre);
		return (Commande) req.getSingleResult();
		
	}
	
	@Override
	public Commande selectCommandeByUser(Long user_id) {
		// TODO Auto-generated method stub
		Query req = entityManager.createQuery("select a from user_article a  where a.user_id =: user_id ");
		req.setParameter("user_id", user_id);
		return (Commande) req.getSingleResult();
		
	}

	
	@Override
	public Commande selectUserByCommande(Long article_id) {
		// TODO Auto-generated method stub
		Query req = entityManager.createQuery("select a from user_article a  where a.fk_article_user =: article_id ");
		req.setParameter("article_id", article_id);
		return (Commande) req.getSingleResult();
		
	}


	@Override
	public void persisterCommande(Commande article) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public List<Article> lireTousCommande() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Commande lireCommande(Long id) {
		// TODO Auto-generated method stub
		return entityManager.find(Commande.class, id);
	}


	@Override
	public Commande lireCommandeName(String name) {
		// TODO Auto-generated method stub
		return entityManager.find(Commande.class, name);
	}


	@Override
	public Commande selectCommandeTitre(String titre) {
		// TODO Auto-generated method stub
		return null;
	}





	
	

}
