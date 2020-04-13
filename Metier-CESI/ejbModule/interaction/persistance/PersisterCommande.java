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
	
	@Override
	public Commande selectCommandeByUser(Long user_id) {
		// TODO Auto-generated method stub
		Query req = entityManager.createQuery("select a from user_article a  where a.user_id =: user_id ");
		req.setParameter("user_id", user_id);
		return (Commande) req.getSingleResult();
		
	}
	
	@Override
	public Commande selectCommandeByAcheteur(Long user_id) {
		// TODO Auto-generated method stub
		Query req = entityManager.createQuery("select c from Commande c  where a.acheteur_id =: user_id ");
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

		//  entityManager.getTransaction().begin();
			
	   //   query.setParameter("user_id" ,user.getUser_id());
	      query.setParameter("fk_article_id", article.getId());
	   
	      query.executeUpdate();
		
		
		entityManager.persist(article);
		
		
	}
	
	@Override
	public void updateCommandeWithAcheteur(Commande commande, User user) {
		
		//  entityManager.getTransaction().begin();
		  
		Commande commandeModif = entityManager.find(Commande.class, commande.getCommande_id());
		
		commandeModif.setAcheteurUser(user);

		
		 //  entityManager.persist(articleModif);
		 
		//  entityManager.getTransaction().commit();
		  
		  
		
	}
	
	@Override
	public void updateCommandeWithVendeur(Commande commande, User user, Article article) {
		
		//  entityManager.getTransaction().begin();
		  
		Commande commandeModif = entityManager.find(Commande.class, commande.getCommande_id());
		
		commandeModif.setAcheteurUser(user);
		
		commandeModif.setArticle(article);
		
		commandeModif.setStatus(StatutCommande.ENCOURS);

		
		 //  entityManager.persist(articleModif);
		 
		//  entityManager.getTransaction().commit();
		  
		  
		
	}

	@Override
	public void updateArticleStatut(Article article, StatutArticle status) {
		// TODO Auto-generated method stub
		  
				Article articleModif = entityManager.find(Article.class, article.getId());
				
				articleModif.setStatus(status);
	}

	@Override
	public void updateCommandeStatut(Commande commande, StatutCommande status) {
		// TODO Auto-generated method stub
		
		  
		Commande commandeModif = entityManager.find(Commande.class, commande.getCommande_id());
				
		commandeModif.setStatus(status);
		
	}
	
	
	@Override
	public Commande creerCommandeAll(Article article, User acheteur) {
		// TODO Auto-generated method stub

		
		
		Query query = entityManager.createNativeQuery("INSERT INTO commande "
				+ "( date_creation,  prix,  acheteur_id, article_id, vendeur_id)"
				+ " VALUES"
				+ " (  now(), :prix,  :acheteur_id, :article_id,  :vendeur_id);");


		 //query.setParameter("id", nombreCommande);
		
		
		 query.setParameter("prix", article.getPrix());
	      query.setParameter("acheteur_id", acheteur.getUser_id());
	  
	      query.setParameter("vendeur_id", article.getUser_vendeur());
	      
	      query.setParameter("article_id", article.getId());
	      query.setParameter("vendeur_id", article.getUser_vendeur());
	      query.executeUpdate();
		
	      nombreCommande++;
	      
	      
	     
	      //return  selectCommandeByIndex(nombreCommande-1);
	      
	      return  selectCommandeByAcheteur(acheteur.getUser_id());
	      
	     // article.setStatus(StatutArticle.RESERVE);
		
		//entityManager.persist(article);
		  
		
		
	}
	
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
	
	@Override
	public void insertIntoCommandeAcheteurVendeur(User user, Article article ) {
		
		System.out.println("Add Article Achat ");
		
		//entityManager.getTransaction().begin();
		
		user.addArticleAchat(article);
		
		user.getAchatArticles().put(user.getUser_id(), article);
		
		
		
		//article.setStatus(StatutArticle.RESERVE);
		

		/* 		User vendeur = (User) article.getUser_vendeur();
		
		vendeur.add
		
		user.addArticleAchat(article);
		
		user.getVentesArticles().put(user.getUser_id(), article);  */
		
		
		// entityManager.getTransaction().commit();
		
		/*
		 * Article articleModif = entityManager.find(Article.class, article.getId());
		 * 
		 * articleModif.setUser_vendeur(user);
		 * 
		 * entityManager.getTransaction().begin(); //
		 * entityManager.persist(articleModif);
		 * 
		 * entityManager.getTransaction().commit();
		 */
		  
		  
		
	}


	
	@Override
	public void ajouterArticleAchat(User user, Article article) {
		// TODO Auto-generated method stub
			user.addAchatArticles(article);
		
		user.getAchatArticles().put(user.getUser_id(), article);
	}

	@Override
	public void ajouterArticleCommande(User user, Commande commande) {
		// TODO Auto-generated method stub
			user.addCommandeArticle(commande);
		
		user.getCommandesArticles().add(commande);
	}
	
	/*@Override
	public void ajouterArticleCommande(User user, Commande commande) {
		// TODO Auto-generated method stub
			user.addCommandeArticles(commande);
		
		user.getCommandesArticles().put(user.getUser_id(), commande);
	}*/
	
	/*  @Override
	public Commande creerCommandeAll(Article article, User acheteur) {
		// TODO Auto-generated method stub

		Long nombreCommande= 1L;
		
		Query query = entityManager.createNativeQuery("INSERT INTO commande "
				+ "(id, date_creation,  prix,  acheteur_id, article_id, vendeur_id)"
				+ " VALUES"
				+ " ( :id, now(), :prix,  :acheteur_id, :article_id,  :vendeur_id);");

		//  entityManager.getTransaction().begin();
			
	   //   query.setParameter("user_id" ,user.getUser_id());
		 query.setParameter("id", nombreCommande);
		 query.setParameter("prix", article.getPrix());
	      query.setParameter("acheteur_id", acheteur.getUser_id());
	  
	      query.setParameter("vendeur_id", article.getUser_vendeur());
	      
	      query.setParameter("article_id", article.getId());
	      query.setParameter("vendeur_id", article.getUser_vendeur());
	      query.executeUpdate();
		
	      nombreCommande = nombreCommande + 1;
	      
	      
	     
	      return  selectCommandeByIndex(nombreCommande-1);
	     // article.setStatus(StatutArticle.RESERVE);
		
		//entityManager.persist(article);
		  
		
		
	} */

}
