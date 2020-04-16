package contenu.persistance.article;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import contenu.entite.Article;
import contenu.entite.Theme;
import contenu.enume.StatutArticle;

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
	public List<Article> selectArticleByTheme(String theme) {
		// TODO Auto-generated method stub
		Query req = entityManager.createQuery("select a from Article a  where a.theme.theme_intitule =: theme ");
		req.setParameter("theme", theme);
		return req.getResultList();
		
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

	@Override 
	public Article mergeArticleReturn(Article article) {
		
		return entityManager.merge(article);
	}

	@Override 
	public void mergeArticle(Article article) {
		
		 entityManager.merge(article);
	}

	
	@Override
	public void persisterUserArticle(User user, Article article) {
		// TODO Auto-generated method stub
	
		entityManager.persist(article);
		Article articleModif = entityManager.find(Article.class, article.getId());
		articleModif.setVendeur(user);	
		entityManager.merge(articleModif);	
		
	}
	
	
	@Override
	public void updateArticleStatut(Article article, StatutArticle status) {
		
		//  entityManager.getTransaction().begin();
		  
		Article articleModif = entityManager.find(Article.class, article.getId());
		
		articleModif.setStatus(status);
		
		modifierArticle(articleModif);
		
	}

	@Override
	public Article modifierArticle(Article article) {
		return entityManager.merge(article);
	}
	
	@Override
	public void updateArticleDate(Article article) {
		// TODO Auto-generated method stub
		
		Query req = entityManager.createQuery("UPDATE article a SET a.date = now() WHERE a.id = :p");
		req.setParameter("p", article.getId());
		req.executeUpdate();
		
		
	}
	@Override
	public List<Article> lireTousArticleByUserVente(Long user_id) {
		// TODO Auto-generated method stub
		Query req = entityManager.createQuery("select a from Article a  where vendeur_id =: user_id ");
		
		
		req.setParameter("user_id", user_id);
		return (List<Article>)  req.getResultList();
	}

	@Override
	public Article rechercherArticleIndex(Long id) {
		System.out.println("rechercherUserLogin");
		
			Query query = entityManager.createQuery("select a from Article a where a.id =: id ");
			query.setParameter("id", id);
		
			return (Article) query.getSingleResult();
	
	}
	
	@Override
	public void ajouterArticleAchat(User user, Article article) {
		// TODO Auto-generated method stub
			user.addAchatArticles(article);
		
			user.getAchatArticles().add(article);
	}
	

}
