package utilisateurs.connexion;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import utilisateurs.entite.User;
import utilisateurs.persistance.PersistanceUserItf;


@Stateless
public class MetierConnexion implements MetierInterfaceConnexion {
	
	
	@EJB
	PersistanceUserItf persistanceUser;
	
    private static final String CHAMP_USERNAME  = "username";
    private static final String CHAMP_LOGIN   = "login";
    
    
    private static final String CHAMP_EMAIL  = "email";
    private static final String CHAMP_PASS   = "password";
	
    private static final String CHAMP_ERRORS   = "erreurs";
	private Map<String,User> utilisateurs;
    private String              resultat;
    private Map<String, String> erreurs      = new HashMap<String, String>();

	public MetierConnexion() {
		utilisateurs = new HashMap<String, User>();
		initialiser();
	}
	public boolean connexionUtilisateurTest(String login,String password) {
		
	   	 //User user = persistanceUser.selectUserLogin(login);
	   	 
	   	User userBDD;
		try {
			userBDD = findUserNotBDD(login, password);
			if( userBDD.getPassword().equals(password) && userBDD != null)
				{ return true; }
		return true;
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.setErreur( CHAMP_LOGIN, e.getMessage() );
			
			this.setErreur( CHAMP_ERRORS, e.getMessage() );
			return false;
		}
	   	 /*
		if( userBDD.getPassword().equals(passwd) && userBDD != null)
			return true;
		else return false;*/
	}
	
	
	public boolean connexionUtilisateur(String login,String password) {
		
	   	 //User user = persistanceUser.selectUserLogin(login);
	   	 
	   	User userBDD;
		try {
			userBDD = findUserNotBDD(login, password);
			if(userBDD != null) {
				
				System.out.println("connexionUser boolean : return true ");
				return true;
			}
			/*
			 * if( userBDD.getPassword().equals(password) && userBDD != null) { return true;
			 * }
			 */
			System.out.println("return true");
		return true;
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Catch exception connexion User");
			e.printStackTrace();
			this.setErreur( CHAMP_LOGIN, e.getMessage() );
			
			this.setErreur( CHAMP_ERRORS, e.getMessage() );
			System.out.println("return false with ERRORS");
			return false;
		}
	   	 /*
		if( userBDD.getPassword().equals(passwd) && userBDD != null)
			return true;
		else return false;*/
	}
	
	
	public boolean connexionUtilisateurBDD(String login,String passwd) {
		
		
		if(utilisateurs.containsKey(login)&&utilisateurs.get(login).getPassword().equals(passwd))
			return true;
		else return false;
	}
	

	
    @Override
   	public User findUserNotBDD(String login, String password) throws Exception {
   		// TODO Auto-generated method stub
	       
	   	 User user = persistanceUser.selectUserLogin(login);
   	   	 
   	   	 
   		if(  user.equals(null) ){

         throw new Exception( "Veuillez ressaisir votre login ou votre mot de passe !" );
         
	    	//return null;
	     	
	    	 } else if( ( ( user.getPassword().contentEquals(password) ) && ( user.getLogin().contentEquals(login) ) )
	    			  
	    			 
			 
	    			 )  {
	    		 
	    		 System.out.println("Good response in content");
			/*
			 * User userBDD = new User(user.getUser_id(), user.getRole(),
			 * user.getUsername());
			 * 
			 * return userBDD;
			 */
	    		 
	    		// return user;
	    		 
	    	   	 User userBDDScope = new User(user.getUser_id(),  user.getLogin(),user.getUsername(), user.getRole());
	    			
	    	   	 System.out.println("User in IF : " + userBDDScope.getUser_id() + user.getLogin() + userBDDScope.getUsername() + userBDDScope.getRole() );
	    	   	 
	    	 	// System.out.println("Return User in IF" + userBDD);
	    		 //return userBDD;
	    		
	    	 
	    	     	//
	     }
   		

   	 User userBDD = new User(user.getUser_id(),  user.getLogin(),  user.getUsername(), user.getRole());
   	 System.out.println("User in Global findUserNotBDD : " + userBDD.getUser_id() + userBDD.getUsername() + userBDD.getRole() );
   	 
	 System.out.println("Return User global findUserNotBDD" + userBDD);
	 return userBDD;

	
   	// return user;
	 
	    	   	    	
   	}
    
    @Override
   	public User findUserBDD(String login, String password) throws Exception {
   		// TODO Auto-generated method stub
	       
	   	 User user = persistanceUser.selectUserLogin(login);
   	   	 
	   	 User userBDD;
   	   	 
   		if(  user.equals(null) ){

         throw new Exception( "Veuillez ressaisir votre login ou votre mot de passe !" );
         
	    	//return null;
	     	
	    	 } else if( ( ( user.getPassword().contentEquals(password) ) && ( user.getLogin().contentEquals(login) ) )
	    			  
			 
	    			 )  {
	    		 userBDD = new User(user.getUser_id(), user.getUsername(), user.getRole());
	    			
	    		 return userBDD;
	    		
	    	     	//
	     }
   		
   	return user;
	 
	    	   	    	
   	}
    
    @Override
   	public User selectUserConnexion(String login) {
   		// TODO Auto-generated method stub
	       
	  // 	 User user = persistanceUser.selectUserLogin(login);
   	   	 
	   	// User user = persistanceUser.lireLoginUser(login);
	   	 
    	System.out.println("selectUserConnexion : go select UserLogin de la persistance");
	   	 
	   	 User user = persistanceUser.selectUserLogin(login);
	   	 
	   	 System.out.println("user select connexion" + user) ;
	   	 
	   	 User userConnexion = new User(user.getUser_id(),  user.getLogin(),  user.getUsername(), user.getRole());
   	   	 
		 System.out.println("user args connexion" + userConnexion) ;
   		
	    	
	    			
	    		 return userConnexion;

	 
	    	   	    	
   	}
    
    
	public User getUtilisateur(String login) {
		return utilisateurs.get(login);
	}
	public Collection<User> getAllUtilisateur(){
		return utilisateurs.values();
	}
	public void addUtilisateur(User utilisateur) {
		utilisateurs.put(utilisateur.getLogin(), utilisateur);
	}
	@Override
	public String toString() {
		return "Metier [utilisateurs=" + utilisateurs + "]";
	}
	public void initialiser() {
		/*
		 * User u = new User("login1", "passwd1"); utilisateurs.put(u.getLogin(), u); u
		 * = new User("login2", "passwd2"); utilisateurs.put(u.getLogin(), u);
		 */
	}
	
	
    public String getResultat() {
        return resultat;
    }

    public Map<String, String> getErreurs() {
        return erreurs;
    }
	

	@Override
	public void setErreur(String champ, String message) {
		// TODO Auto-generated method stub
		  erreurs.put( champ, message );
		
	}
	
}
