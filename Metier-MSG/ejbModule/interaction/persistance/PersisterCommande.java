package interaction.persistance;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import contenu.entite.Article;
import contenu.enume.StatutArticle;
import interaction.entite.Commande;
import interaction.enume.StatutCommande;
import utilisateurs.entite.User;


@Stateless
public class PersisterCommande implements PersistanceCommandeItf{
	
	Long nombreCommande= 1L;
	
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
	public Commande selectCommandeByIndex(Long id) {
		// TODO Auto-generated method stub
		Query req = entityManager.createQuery("select c from Commande c where c.id =: id ");
		req.setParameter("id", id);
		return (Commande) req.getSingleResult();
		
	}
	
/*

	*/
	
	@Override
	public Commande selectCommandeByLastIndex() {
		// TODO Auto-generated method stub
		Query req = entityManager.createQuery ("SELECT c FROM Commande c WHERE c.id = (SELECT MAX(c.id) FROM Commande )");
		Commande result = (Commande) req.getSingleResult ();
	//req.setParameter("id", id);
		return (Commande) req.getSingleResult();
		
	}



@Override
public void insertArticleAchat(User user, Article article) {
	
	Query query = entityManager.createNativeQuery("INSERT INTO achat_article "
			+ "( acheteur_id, fk_article_id)"
			+ " VALUES"
			+ " (  :acheteur_id, :fk_article_id );");
	
      query.setParameter("acheteur_id", user.getUser_id());
  
   
      query.setParameter("fk_article_id", article.getId());
      // :vendeur_id query.setParameter("vendeur_id", article.getUser_vendeur());
      query.executeUpdate();
	
  
	
}
@Override
public void insertArticleCommande(User user, Article article) {
	// TODO Auto-generated method stub
	Query query = entityManager.createNativeQuery("INSERT INTO vente_article "
			+ "( vendeur_id, fk_article_id)"
			+ " VALUES"
			+ " (  :vendeur_id, :fk_article_id );");
	
      query.setParameter("vendeur_id", user.getUser_id());
  
   
      query.setParameter("fk_article_id", article.getId());
      // :vendeur_id query.setParameter("vendeur_id", article.getUser_vendeur());
      query.executeUpdate();
      
      ajouterArticleAchat(user, article);
}

@Override
public void ajouterArticleAchat(User user, Article article) {
	// TODO Auto-generated method stub
	
	System.out.println("Ajouter Article Achat ");
		user.addAchatArticles(article);
	
		
		  System.out.println("Get and Ajouter Article Achat ");
		  user.getAchatArticles().add(article);
		 
	//user.getAchatArticles().put(user.getUser_id(), article);
}

@Override
public void ajouterArticleVente(User user, Article article) {
	// TODO Auto-generated method stub
	
	System.out.println("Ajouter Article Ventes ");
		user.addArticlesVentes(article);
	
		System.out.println("Get and Ajouter Article Ventes ");
		user.getVentesArticles().add(article);
	//user.getAchatArticles().put(user.getUser_id(), article);
}

@Override
public void ajouterArticleCommande(User user, Commande commande) {
	// TODO Auto-generated method stub
		//user.addCommandeArticle(commande);
	
	//user.getCommandesArticles().add(commande);
}

@Override
public void updateCommandeStatutIndex(Long index, StatutCommande status) {
	// TODO Auto-generated method stub
	Commande commandeModif = entityManager.find(Commande.class, index);
	
	commandeModif.setStatus(status);

	mergeCommandeReturn(commandeModif);
}

@Override
public void updateCommandeDateEnvoi(Commande commande) {
	// TODO Auto-generated method stub
	Query req = entityManager.createQuery("UPDATE commande c SET c.date_envoi = now() WHERE c.id = :p");
	req.setParameter("p", commande.getCommande_id());
	req.executeUpdate();
	
	
}

@Override
public void updateCommandeDateCreation(Commande commande) {
	// TODO Auto-generated method stub
	Query req = entityManager.createQuery("UPDATE commande c SET c.date_creation = now() WHERE c.id = :p");
	req.setParameter("p", commande.getCommande_id());
	req.executeUpdate();
	
	
}




	
	@Override
	public List<Commande> selectListCommandeAcheteur(Long id) {
		// TODO Auto-generated method stub
		List<Commande> commandes;
		try {
			
			Query req = entityManager.createQuery ("SELECT c FROM Commande c WHERE c.acheteur_id = id");
			req.setParameter("id", id);
			commandes = req.getResultList();
		}catch(Exception e) {
			
			commandes=null;
		}
		
		return commandes;
		
	}
	


	@Override
	public void persisterCommande(Commande commande) {
		// TODO Auto-generated method stub
		
		entityManager.persist(commande);
		
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
	public List<Commande> lireTousCommande() {
		// TODO Auto-generated method stub
		Query rep = entityManager.createQuery("select c from Commande c");
		return (List<Commande>)  rep.getResultList();
	}

	@Override
	public Commande selectCommandeTitre(String titre) {
		// TODO Auto-generated method stub
		return null;
	}




	
	@Override
	public void persisterAcheteurVendeurCommande(User vendeur, User acheteur, Article article) {
		// TODO Auto-generated method stub
		
		Query query = entityManager.createNativeQuery("INSERT INTO vendeur_article (user_id, fk_article_id) VALUES (:user_id,:fk_article_id);");
;
	      query.setParameter("fk_article_id", article.getId());
	   
	      query.executeUpdate();
		
		
		entityManager.persist(article);
		
		
	}
	
	
	@Override
	public void supprimerCommande(Commande commande) {
		entityManager.merge(commande);
		entityManager.remove(commande);
	}
	
	@Override
	public Commande selectCommandeByArticle(Long article_id) {
		// TODO Auto-generated method stub
		Commande commande;
		try {
			
			Query req = entityManager.createQuery("select c from Commande c  where c.article_id=: article_id ");
			req.setParameter("article_id", article_id);
			commande = (Commande) req.getSingleResult();
		}catch(Exception e) {
			
			commande=null;
		}
		
			return commande;
	}
	
	

	@Override 
	public Commande mergeCommandeReturn(Commande commande) {
		
		return entityManager.merge(commande);
	}
	


	

	@Override
	public void updateArticleStatut(Article article, StatutArticle status) {
		// TODO Auto-generated method stub
		  
				Article articleModif = entityManager.find(Article.class, article.getId());
				
				articleModif.setStatus( status);
				
				entityManager.merge(articleModif);
	}

	@Override
	public void updateCommandeStatut(Commande commande, StatutCommande status) {
		// TODO Auto-generated method stub
		
		  
		Commande commandeModif = entityManager.find(Commande.class, commande.getCommande_id());
				
		commandeModif.setStatus(status);
		
		
		entityManager.merge(commandeModif);
		
			
	}
	


	@Override
	public List<Commande> rechercherCommandeByAcheteur(Long id_acheteur) {
		// TODO Auto-generated method stub
		Query rep = entityManager.createQuery ("SELECT c FROM Commande c WHERE acheteur_id =:  id_acheteur");
		
		rep.setParameter("id_acheteur", id_acheteur);
		return (List<Commande>) rep.getResultList();
		
	}
	
	@Override
	public List<Article> rechercherArticleByCommande(Long id_article) {
		// TODO Auto-generated method stub
		Query rep = entityManager.createQuery ("SELECT a FROM Article a WHERE c.article_id =:  id_article ");
		
		rep.setParameter("id_article", id_article);
		return rep.getResultList();
		
	}
	
	@Override
	public List<Article> rechercherArticleByStatut(Long id, StatutArticle statut) {
		// TODO Auto-generated method stub
		Query rep = entityManager.createQuery ("SELECT a FROM Article a WHERE a.vendeur_id =:  id AND a.statutArticle := statut ;");
		
		rep.setParameter("id", id);
		rep.setParameter("statut", statut.RESERVE);
		return (List<Article>) rep.getResultList();
		
	}

	@Override
	public List<Article> lireTousArticleVendeur(Long login) {
		Query req = entityManager.createQuery("select a from Article a where vendeur.login=:login");
		req.setParameter("login", login);
		return req.getResultList();
	}	
	@Override
	public List<Commande> lireTousAchats(Long id) {
		Query req = entityManager.createQuery("select c from Commande c where acheteurUser.id=:id");
		req.setParameter("id", id);
		return req.getResultList();		
	}
	@Override
	public List<Commande> lireTousVenteEnCours(Long login) {
		Query req = entityManager.createQuery("select c from Commande c where article.vendeur_id=: login");
		req.setParameter("login", login);
		return req.getResultList();			
	}
	
	@Override
	public List<Commande> lireTousCommandeByAcheteur(Long user_id) {
		// TODO Auto-generated method stub
		Query req = entityManager.createQuery("select c from Commande c  where c.acheteur_id=: user_id ");
		req.setParameter("user_id", user_id);
		return (List<Commande>)  req.getResultList();
	}

	
@Override
	public List<Commande> lireTousCommandeByVendeurException(Long user_id) {
		// TODO Auto-generated method stub
	List<Commande> commandes;
		try {
			
			Query req = entityManager.createQuery("select c from Commande c  where article.user.user_id=: user_id AND where article.status =: status");
			req.setParameter("user_id", user_id);
			req.setParameter("status", StatutArticle.RESERVE);
			commandes = req.getResultList();
		}catch(Exception e) {
			
			commandes=null;
		}
		
			return commandes;
		
	}


@Override
public Commande lireTousCommandeByArticleException(Long article_id) {
	// TODO Auto-generated method stub
 Commande commande;
	try {
		
		Query req = entityManager.createQuery("select c from Commande c  where c.article_id=: article_id AND where article.status =: status");
		req.setParameter("article_id", article_id);
		req.setParameter("status", StatutArticle.RESERVE);
		commande = (Commande) req.getSingleResult();
	}catch(Exception e) {
		
		commande=null;
	}
	
		return commande;
	
}


@Override
public List<Article> lireTousArticleReserveByVendeurException(Long user_id) {
	// TODO Auto-generated method stub
	List<Article> articlesReserve;
	try {
		
		Query req = entityManager.createQuery("select a from Article a  where vendeur_id=: user_id AND  a.status =: status");
		req.setParameter("user_id", user_id);
		req.setParameter("status", StatutArticle.RESERVE);
		articlesReserve = req.getResultList();
	}catch(Exception e) {
		
		articlesReserve=null;
	}
	
		return articlesReserve;
}

@Override
public List<Article> lireTousArticleByVendeurException(Long user_id) {
	// TODO Auto-generated method stub
	List<Article> articlesReserve;
	try {
		
		Query req = entityManager.createQuery("select a from Article a  where vendeur_id=: user_id ");
		req.setParameter("user_id", user_id);

		articlesReserve = req.getResultList();
	}catch(Exception e) {
		
		articlesReserve=null;
	}
	
		return articlesReserve;
}

@Override
public List<Commande> lireTousCommandeByAcheteurException(Long user_id) {
	// TODO Auto-generated method stub
List<Commande> commandes;
	try {
		
		Query req = entityManager.createQuery("select c from Commande c  where c.acheteur_id=: user_id ");
		req.setParameter("user_id", user_id);
		commandes = req.getResultList();
	}catch(Exception e) {
		
		commandes=null;
	}
	
		return commandes;
	
}



/*  @Override
	public List<Commande> lireTousCommandeByAcheteurException(Long user_id) {
		// TODO Auto-generated method stub
	List<Commande> commandes;
		try {
			
			Query req = entityManager.createQuery("select c from Commande c  where article.user.user_id=: user_id ");
			req.setParameter("user_id", user_id);
			commandes = req.getResultList();
		}catch(Exception e) {
			
			commandes=null;
		}
		
			return commandes;
		
	}

@Override
public List<Commande> lireTousCommandeByVendeurException(Long user_id) {
	// TODO Auto-generated method stub
List<Commande> commandes;
	try {
		
		Query req = entityManager.createQuery("select c from Commande c  where c.user=: user_id ");
		req.setParameter("user_id", user_id);
		commandes = req.getResultList();
	}catch(Exception e) {
		
		commandes=null;
	}
	
		return commandes;
	
}
 */


	/*  	@Override
	public void creerCommandeAll(Article article, User acheteur) {
		// TODO Auto-generated method stub

		Long nombreCommande= 1L;
		
		Query query = entityManager.createNativeQuery("INSERT INTO commande "
				+ "(id, date_creation,  prix, statutCommande, acheteur_id, article_id, vendeur_id)"
				+ " VALUES"
				+ " ( :id, now(), :prix, :statutCommande, :acheteur_id, :article_id,  :vendeur_id);");

		//  entityManager.getTransaction().begin();
			
	   //   query.setParameter("user_id" ,user.getUser_id());
		 query.setParameter("id", nombreCommande++);
		 query.setParameter("prix", article.getPrix());
	      query.setParameter("acheteur_id", acheteur.getUser_id());
	      
	      
	      query.setParameter("statutCommande", StatutCommande.ENCOURS);
	      
	      
	      query.setParameter("vendeur_id", article.getUser_vendeur());
	      
	      query.setParameter("article_id", article.getId());
	      query.setParameter("vendeur_id", article.getUser_vendeur());
	      query.executeUpdate();
		
	     // article.setStatus(StatutArticle.RESERVE);
		
		//entityManager.persist(article);
		  
		
		
	} */
	
}
