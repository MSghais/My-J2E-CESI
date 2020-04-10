package contenu.persistance.theme;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import contenu.entite.Article;

import contenu.entite.Theme;

import utilisateurs.entite.User;


@Stateless
public class PersisterTheme implements PersistanceThemeItf{
	
	
	@PersistenceContext(unitName="UP_ETUDIANT")
	
	private EntityManager entityManager;
	
	

	
	@Override
	public void persisterTheme(Theme theme) {
		// TODO Auto-generated method stub
		entityManager.persist(theme);
	}

	@Override
	public Theme lireTheme(Long id) {
		return entityManager.find(Theme.class, id);
	}
	
	@Override
	public Theme lireThemeName(String name) {
		return entityManager.find(Theme.class, name);
	}
	

	@Override
	public List<Theme> lireTousTheme() {
		// TODO Auto-generated method stub
		Query rep = entityManager.createQuery("select t from Theme t");
		return rep.getResultList();
	}



	@Override
	public void persisterUserTheme(User user, Theme theme) {
		// TODO Auto-generated method stub
		
	}
	


	@Override
	public Theme selectThemeTitre(String titre) {
		// TODO Auto-generated method stub
		Query req = entityManager.createQuery("select a from user_article a  where a.fk_article_user =: article_id ");
		req.setParameter("titre", titre);
		return (Theme) req.getSingleResult();
	}

	@Override
	public Theme selectThemeByUser(Long user_id) {
		// TODO Auto-generated method stub
		Query req = entityManager.createQuery("select a from user_article a  where a.fk_article_user =: article_id ");
		req.setParameter("user_id", user_id);
		return (Theme) req.getSingleResult();
	}

	@Override
	public Theme selectUserByTheme(Long theme_id) {
		// TODO Auto-generated method stub
		Query req = entityManager.createQuery("select a from user_article a  where a.fk_article_user =: article_id ");
		req.setParameter("theme_id", theme_id);
		return (Theme) req.getSingleResult();
		
	}




	
	

}
