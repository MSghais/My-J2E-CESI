package utilisateurs.persistance;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import utilisateurs.entite.User;
import utilisateurs.entite.Utilisateur;



@Stateless
public class PersisterUser implements PersistanceUserItf {
	
	@PersistenceContext(unitName="UP_ETUDIANT")
	
	private EntityManager entityManager;
	
	@Override
	public User connecterUtilisateurLoginMdp(String login, String passwd) {
		System.out.println("PersistanceUtilisateur - connecterUtilisateur");
		Query query = entityManager.createQuery("select u from User u where login=:login and password=:password");
		query.setParameter("login", login);
		query.setParameter("password", passwd);
		return (User) query.getSingleResult();
	}
	
	@Override
	public User lireUser(Long id) {
		return entityManager.find(User.class, id);
	}
	@Override
	public User lireUserName(String username) {
		return entityManager.find(User.class, username);
	}
	
	@Override
	public User selectUserName(String username) {
		// TODO Auto-generated method stub
		Query req = entityManager.createQuery("select u from User u where u.username =: username ");
		req.setParameter("username", username);
		return (User) req.getSingleResult();
		
	}
	
	@Override
	public User lireLoginUser(String login) {
		return entityManager.find(User.class, login);
	}
	
	@Override
	public User selectUserLogin(String login) {
		// TODO Auto-generated method stub
		Query req = entityManager.createQuery("select e from User e where e.login =: login ");
		req.setParameter("login", login);
		return (User) req.getSingleResult();
		
	}

	
	public List<User> lireTousUser() {
		// TODO Auto-generated method stub
		Query req = entityManager.createQuery("select e from User e");
		return (List<User>) req.getResultList();
		
	}



	@Override
	public void persisterUser(User user) {
		// TODO Auto-generated method stub
		entityManager.persist(user);
		
		
		entityManager.joinTransaction();
		
	}
	


	@Override
	public void persisterUtilisateur(Utilisateur utilisateur) {
		// TODO Auto-generated method stub
		
		entityManager.persist(utilisateur);
	}



	@Override
	public void persisterUserTeacher(User user) {
		// TODO Auto-generated method stub
		entityManager.persist(user);
		
	}

	@Override
	public User lireEmailUser(String email) {
		// TODO Auto-generated method stub
		return entityManager.find(User.class, email);
	}

	
	@Override
	public User selectEmailUSer(String email) {
		// TODO Auto-generated method stub
		Query req = entityManager.createQuery("select e from User e where e.email =: email ");
		req.setParameter("email", email);
		return (User) req.getSingleResult();
	}
		
		
	@Override
		public List selectCountLoginUser(String login) {
			// TODO Auto-generated method stub
			Query requ = entityManager.createQuery("select count(e) from User e where e.login =: login ");
			requ.setParameter("login", login);
			
		
			return  requ.getResultList();
			
		}
	@Override
	public User selectLoginUser(String login) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/*
	 * @Override public User userAlreadyExist(String login, String password) throws
	 * Exception { // TODO Auto-generated method stub
	 * System.out.println("PersistanceUtilisateur - connecterUtilisateur"); Query
	 * query = entityManager.
	 * createQuery("select u from User u where login=:login and password=:password"
	 * ); query.setParameter("login", login); query.setParameter("password",
	 * password); return (User) query.getSingleResult(); }
	 */
	
	
	
	




}
