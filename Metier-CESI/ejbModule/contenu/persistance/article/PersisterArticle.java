package contenu.persistance.article;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import contenu.entite.Article;

import utilisateurs.entite.User;


@Stateless
public class PersisterArticle implements PersistanceArticleItf{
	
	
	@PersistenceContext(unitName="UP_ETUDIANT")
	
	private EntityManager entityManager;
	
	

	

	@Override
	public List<Article> lireTousArticle() {
		// TODO Auto-generated method stub
		Query rep = entityManager.createQuery("select a from Article a");
		return (List<Article>)  rep.getResultList();
	}
	

	@Override
	public void persisterArticle(Article article) {
		// TODO Auto-generated method stub
		entityManager.persist(article);
		
	}


	@Override
	public Article lireArticle(Long id) {
		// TODO Auto-generated method stub
		return entityManager.find(Article.class, id);
	}


	@Override
	public Article lireArticleName(String name) {
		// TODO Auto-generated method stub
		return entityManager.find(Article.class, name);
	}



	
	@Override
	public Article selectArticleTitre(String titre) {
		// TODO Auto-generated method stub
		Query req = entityManager.createQuery("select a from Article a  where a.art_titre =: titre ");
		req.setParameter("titre", titre);
		return (Article) req.getSingleResult();
		
	}
	
	@Override
	public Article selectArticleByUser(Long user_id) {
		// TODO Auto-generated method stub
		Query req = entityManager.createQuery("select a from user_article a  where a.user_id =: user_id ");
		req.setParameter("user_id", user_id);
		return (Article) req.getSingleResult();
		
	}

	
	@Override
	public Article selectUserByArticle(Long article_id) {
		// TODO Auto-generated method stub
		Query req = entityManager.createQuery("select a from user_article a  where a.fk_article_user =: article_id ");
		req.setParameter("article_id", article_id);
		return (Article) req.getSingleResult();
		
	}

	@Override
	public Article insertUserByArticle(Long article_id) {
		// TODO Auto-generated method stub
		Query req = entityManager.createQuery("select a from user_article a  where a.fk_article_user =: article_id ");
		req.setParameter("article_id", article_id);
		return (Article) req.getSingleResult();
		
	}

	
	@Override
	public void supprimerArticle(Article article) {
		// TODO Auto-generated method stub
		entityManager.merge(article);
		entityManager.remove(article);
	}
	
	/* 
	
	 	@Override
	public void inscrireEtudiantPromotion(String acronyme, Etudiant etudiant) {
		System.out.println("Persistance - inscrireEtudiantPromotion acronyme=" + acronyme + " etudiant" + etudiant);
	
	}
	
	*/
	
	@Override
	public void persisterUserArticle(User user, Article article) {
		// TODO Auto-generated method stub
		/*
		 * article.setUser_vendeur(user); entityManager.persist(article);
		 * 
		 * article.setUser_vendeur(user);
		 */
		
		entityManager.persist(article);
		
		// article.getUser_vendeur().addUser(user);
		
		Article articleModif = entityManager.find(Article.class, article.getId());
		articleModif.setUser_vendeur(user);
		
		
		entityManager.merge(articleModif);
		
		
		
	}
	
	@Override
	public void persisterUserAndArticle(User user, Article article) {
		// TODO Auto-generated method stub
		/*
		 * article.setUser_vendeur(user); entityManager.persist(article);
		 * 
		 * article.setUser_vendeur(user);
		 */
		
		entityManager.persist(article);
		
		
		user.addArticlesVentes(article);
		
		
		User userTest = entityManager.find(User.class, user.getUser_id());
    
		
		/*
		 * Article articleModif = entityManager.find(Article.class, article.getId());
		 * articleModif.setUser_vendeur(user);
		 * 
		 * 
		 * entityManager.merge(articleModif);
		 */
		
		
		
	}
	

	@Override
	public void persisterArticleInUser(User user, Article article) {
		// TODO Auto-generated method stub
		/*
		 * article.setUser_vendeur(user); entityManager.persist(article);
		 * 
		 * article.setUser_vendeur(user);
		 */
		
		entityManager.persist(article);
		

		Article articleModif = entityManager.find(Article.class, article.getId());
		
		//entityManager.getTransaction().begin();
		  
		  
		articleModif.setUser_vendeur(user);
		
		System.out.println("update column" + 	articleModif.getUser_vendeur())  ; 
		insertIntoVenteUser(user, articleModif);
		
		 entityManager.merge(articleModif);
		entityManager.persist(articleModif);
		// entityManager.merge(articleModif);
//		entityManager.persist(articleModif);
		
		
		//entityManager.getTransaction().commit();
	
		
		
	}
	
	
	//
	@Transactional
	@Override
	public void insertJoinArticleUserWithQuery(User user, Article article) {
	    entityManager.createNativeQuery("INSERT INTO vendeur_article (user_id, fk_article_id) VALUES (?,?)")

	      .setParameter(1, user.getUser_id())
	      .setParameter(2, article.getId())
	   
	      .executeUpdate();
	}
	
	@Transactional
	@Override
	public void insertJoinArticleUserWithQueryIndex(Long userid, Long articleId) {
		
		entityManager.getTransaction().begin();
		
		
	    entityManager.createNativeQuery("INSERT INTO vendeur_article (user_id, fk_article_id) VALUES (?,?)")

	      .setParameter(1,userid)
	      .setParameter(2, articleId)
	   
	      .executeUpdate();
	}

	
	
	@Override
	public void insertionJoinTableId(Long userid, Long articleId) {
		
	
		
		
			Query query = entityManager.createNativeQuery("INSERT INTO vendeur_article (user_id, fk_article_id) VALUES (?,?)");

		 // entityManager.getTransaction().begin();
			
	      query.setParameter(1,userid);
	      query.setParameter(2, articleId);
	   
	      query.executeUpdate();
			
		//	entityManager.getTransaction().commit();
	}
	
	@Override
	public void insertionJoinTableIdAndKey(Long userid, Long articleId) {
		
	
		
		
			Query query = entityManager.createNativeQuery("INSERT INTO vendeur_article (user_id, fk_article_id, ventesArticles_KEY) VALUES (?,?, ?)");

		 // entityManager.getTransaction().begin();
			
	      query.setParameter(1,userid);
	      query.setParameter(2, articleId);
	      query.setParameter(3, userid);
	      
	      query.executeUpdate();
			
		//	entityManager.getTransaction().commit();
	}




	@Override
	public void insertionJoinTableObject(User user, Article article) {
		
	
		
		
			Query query = entityManager.createNativeQuery("INSERT INTO vendeur_article (user_id, fk_article_id) VALUES (:user_id,:fk_article_id);");

		  entityManager.getTransaction().begin();
			
	      query.setParameter("user_id" ,user.getUser_id());
	      query.setParameter("fk_article_id", article.getId());
	   
	      query.executeUpdate();
			
			entityManager.getTransaction().commit();
	}

	@Override
	public void updateArticleWithUser(Article article, User user) {
		
		//  entityManager.getTransaction().begin();
		  
		Article articleModif = entityManager.find(Article.class, article.getId());
		
		articleModif.setUser_vendeur(user);

		
		 //  entityManager.persist(articleModif);
		 
		//  entityManager.getTransaction().commit();
		  
		  
		
	}
	
	
	@Override
	public void insertIntoVenteUser(User user, Article article ) {
		
		System.out.println("Add Article in User list");
		
		//entityManager.getTransaction().begin();
		
		user.addArticlesVentes(article);
		
		user.getVentesArticles().put(user.getUser_id(), article);
		
		
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
	
	

}
