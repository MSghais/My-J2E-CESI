package contenu.persistance.article;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
		return rep.getResultList();
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
	public void persisterUserArticle(User user, Article article) {
		// TODO Auto-generated method stub
		/*
		 * article.setUser_vendeur(user); entityManager.persist(article);
		 * 
		 * article.setUser_vendeur(user);
		 */
		
		entityManager.persist(article);
		
		Article articleModif = entityManager.find(Article.class, article.getId());
		articleModif.setUser_vendeur(user);
		
		
		entityManager.merge(articleModif);
		
		
		
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
		articleModif.setUser_vendeur(user);
		
		
		entityManager.persist(articleModif);
		
		
		
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
		Promotion promotion = entityManager.find(Promotion.class, acronyme);
        promotion.addEtudiant(etudiant);		
	}
	
	*/
	
	

}
