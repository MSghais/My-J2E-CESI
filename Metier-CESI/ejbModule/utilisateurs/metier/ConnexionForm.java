package utilisateurs.metier;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import utilisateurs.entite.User;
import utilisateurs.entite.Utilisateur;
import utilisateurs.persistance.PersistanceUserItf;

@Stateless
public class ConnexionForm implements ConnexionFormInterface{
	
	
	@EJB
	PersistanceUserItf persistanceUser;
	
    private static final String CHAMP_USERNAME  = "username";
    private static final String CHAMP_LOGIN   = "login";
    private static final String CHAMP_EMAIL  = "email";
    private static final String CHAMP_PASS   = "password";

    private static final String CHAMP_OK  = "OK";
    private static String CHAMP_GOOD  = "OK";
    
    private static String CHAMP_NOTGOOD;
    private static String CHAMP_PRESENT  = "OK";
    
    private String              resultat;
    private Map<String, String> erreurs      = new HashMap<String, String>();
	
    private Map<String,User> utilisateurs;
    
    @Override
    public User connecterUser( HttpServletRequest request ) {
        /* Récupération des champs du formulaire */
    	/*
        System.out.println(" metier : Récupération champs formulaire ");
        String username = getValeurChamp( request, CHAMP_USERNAME );
        String login = getValeurChamp( request, CHAMP_LOGIN );
        
        
        String email = getValeurChamp( request, CHAMP_EMAIL );
        String password = getValeurChamp( request, CHAMP_PASS );
        String confirmation = getValeurChamp( request, CHAMP_CONF );  */

		
        String username = request.getParameter("username");
        String login = request.getParameter("login");
        
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmation = request.getParameter("confirmation");
        
        HttpSession session = request.getSession();
        
        User user = null;
       // User user = new User();

       // Utilisateur utilisateur = new Utilisateur();
        
        System.out.println("metier: Analsye des erreurs et exceptions ");
        
   
  
        System.out.println("Test  metier : login ");
        
        /* Validation du champ login. */
        try {
            validationLogin( login );
        } catch ( Exception e ) {
        	this.setErreur( CHAMP_LOGIN, e.getMessage() );
        }       
        user.setLogin(login);
      
        
        /* Validation du champ mot de passe. */
        System.out.println("Test  metier: mot de passe");
        try {
        	validationPassword(password);
        } catch ( Exception e ) { 

            this.setErreur( CHAMP_PASS, e.getMessage() );
        }
        user.setPassword( password );
        
        /*
        try {
            validationEmail( email );
        } catch ( Exception e ) {
        	this.setErreur( CHAMP_EMAIL, e.getMessage() );
        }
        user.setEmail( email );
        */
        /* Chercher utilisateur en BDD. */
        
        System.out.println("Test  : FInd User BDD");
        try {
            findUserBDD(login, password);
        } catch ( Exception e ) {
        	this.setErreur( CHAMP_GOOD, e.getMessage() );
        	this.setErreur( CHAMP_PRESENT, e.getMessage() );
        }
       // user.setEmail( email );
       
    	/*
        User userBDDSelect;
		try {
			User userBDD = keepUserBDD(login, password);
			
			//return userBDDSelect = persistanceUser.selectUserLogin(login);
			//return userBDD;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		userBDDSelect = persistanceUser.selectUserLogin(login);
		*/
        
		User userKeep = keepUserBDDSimply(login, password);
		/*  Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("prenom")) {
                    request.setAttribute("prenom", cookie.getValue());
                }
            }
        }*/
        
        /* Initialisation du résultat global de la validation. */
        /*if ( erreurs.isEmpty() ||  ( !user.getLogin().isEmpty() && !user.getPassword().isEmpty() && !user.getEmail().isEmpty() ) )  {
         */   
        System.out.println("Test  : démarrage conditionnel");
        if (
        		//!erreurs.get(CHAMP_NOTGOOD).contentEquals("ERROR_NO_IN_DB") ||
        		user != null 
        		|| 
        		erreurs.get(CHAMP_GOOD).contentEquals("goodgame") || 
        		 erreurs.get(CHAMP_PRESENT).contentEquals("present")  || 
        		( !user.getLogin().isEmpty() && !user.getPassword().isEmpty()  ) 
        		
        		//|| ( userKeep.getLogin().isEmpty() && userKeep.getPassword().isEmpty()  )
        		
        		){
            
        	this.setResultat("Succès de la connection.");
        	this.resultat = "Succès de la connection.";
        	
            System.out.println("Utilisateur trouvé en BDD");
           // persistanceUser.persisterUser(user);
            
           // request.isRequestedSessionIdFromCookie();
            
            //getCookieValue(request, nom)
       /* 	Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("prenom")) {
                        request.setAttribute("prenom", cookie.getValue());
                    }
                }
            }*/
            
            
          
         	System.out.println("Erreurs : " + erreurs);
         	//System.out.println("Erreurs : " + erreurs.get(CHAMP_GOOD) );
         
            
          
        } else {
        	        	
        	
        	this.setResultat("Échec de la connection.");
        	this.resultat = "Échec de  la connection.";
            resultat = "Échec de la connexion.";
           
            System.out.println("Not User FIND !!! ");
            
            
            System.out.println("Erreurs : " + erreurs);
         	//System.out.println("Erreurs : " + erreurs.get(CHAMP_NOTGOOD) );
            
         
            System.out.println("Echec de la connexion  metier ");
            
            
           // return null;
        }
        
        
        return userKeep;
       
     
    }
    
    
    @Override
    public boolean connecterUserQuestion( HttpServletRequest request ) {
        /* Récupération des champs du formulaire */
    	/*
        System.out.println(" metier : Récupération champs formulaire ");
        String username = getValeurChamp( request, CHAMP_USERNAME );
        String login = getValeurChamp( request, CHAMP_LOGIN );
        
        
        String email = getValeurChamp( request, CHAMP_EMAIL );
        String password = getValeurChamp( request, CHAMP_PASS );
        String confirmation = getValeurChamp( request, CHAMP_CONF );  */

		
        String username = request.getParameter("username");
        String login = request.getParameter("login");
        
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmation = request.getParameter("confirmation");
        
        HttpSession session = request.getSession();
        
        User user = new User();

        Utilisateur utilisateur = new Utilisateur();
        
        System.out.println("metier: Analsye des erreurs et exceptions ");
        
   
  
        System.out.println("Test  metier : login ");
        
        /* Validation du champ login. */
        try {
            validationLogin( login );
        } catch ( Exception e ) {
        	this.setErreur( CHAMP_LOGIN, e.getMessage() );
        }       
        user.setLogin(login);
      
        
        /* Validation du champ mot de passe. */
        System.out.println("Test  metier: mot de passe");
        try {
        	validationPassword(password);
        } catch ( Exception e ) { 

            this.setErreur( CHAMP_PASS, e.getMessage() );
        }
        user.setPassword( password );
        
        /*
        try {
            validationEmail( email );
        } catch ( Exception e ) {
        	this.setErreur( CHAMP_EMAIL, e.getMessage() );
        }
        user.setEmail( email );
        */
        /* Chercher utilisateur en BDD. */
        
        System.out.println("Test  : FInd User BDD");
        try {
            findUserBDD(login, password);
        } catch ( Exception e ) {
        	this.setErreur( CHAMP_GOOD, e.getMessage() );
        	this.setErreur( CHAMP_PRESENT, e.getMessage() );
        }
       // user.setEmail( email );
       
    	/*
        User userBDDSelect;
		try {
			User userBDD = keepUserBDD(login, password);
			
			//return userBDDSelect = persistanceUser.selectUserLogin(login);
			//return userBDD;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		userBDDSelect = persistanceUser.selectUserLogin(login);
		*/
        
		User userKeep = keepUserBDDSimply(login, password);
		/*  Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("prenom")) {
                    request.setAttribute("prenom", cookie.getValue());
                }
            }
        }*/
        
        /* Initialisation du résultat global de la validation. */
        /*if ( erreurs.isEmpty() ||  ( !user.getLogin().isEmpty() && !user.getPassword().isEmpty() && !user.getEmail().isEmpty() ) )  {
         */   
        System.out.println("Test  : démarrage conditionnel");
        if (
        		//!erreurs.get(CHAMP_NOTGOOD).contentEquals("ERROR_NO_IN_DB") ||
        		
        		erreurs.get(CHAMP_GOOD).contentEquals("goodgame") || 
        		 erreurs.get(CHAMP_PRESENT).contentEquals("present")  || 
        		( !user.getLogin().isEmpty() && !user.getPassword().isEmpty()  ) 
        		
        		//|| ( userKeep.getLogin().isEmpty() && userKeep.getPassword().isEmpty()  )
        		
        		){
            
        	this.setResultat("Succès de la connection.");
        	this.resultat = "Succès de la connection.";
        	
            System.out.println("Utilisateur trouvé en BDD");
           // persistanceUser.persisterUser(user);
            
           // request.isRequestedSessionIdFromCookie();
            
            //getCookieValue(request, nom)
       /* 	Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("prenom")) {
                        request.setAttribute("prenom", cookie.getValue());
                    }
                }
            }*/
            
            
          
         	System.out.println("Erreurs : " + erreurs);
         	//System.out.println("Erreurs : " + erreurs.get(CHAMP_GOOD) );
         
            return true;
          
        } else{
        	        	
        	
        	this.setResultat("Échec de la connection.");
        	this.resultat = "Échec de  la connection.";
            resultat = "Échec de la connexion.";
           
            System.out.println("Not User FIND !!! ");
            
            
            System.out.println("Erreurs : " + erreurs);
         	//System.out.println("Erreurs : " + erreurs.get(CHAMP_NOTGOOD) );
            
         
            System.out.println("Echec de la connexion  metier ");
            
            
            return false;
           // return null;
        }
        
        
      
       
     
    }
    
    
    

    @Override
   	public void findUserNotBDD(String login, String password) throws Exception {
   		// TODO Auto-generated method stub
    	/*

	       */
	       
	       
   	   	 User user = persistanceUser.selectUserLogin(login);
   	   	 
   		if(  user.equals(null) ){

    		CHAMP_NOTGOOD = "ERROR_NO_IN_DB";
    		
    		CHAMP_PRESENT = "NOT_PRESENT";
         throw new Exception( "Veuillez ressaisir votre login ou votre mot de passe !" );
         
	    	
	     	
	    	 } else if( ( user.getPassword().equals(password) ) && ( user.getLogin().contentEquals(login) ) ) {
	    			CHAMP_PRESENT = "present";
		    		
	    	     	//return user; 
	     }
	    	
   	    	 
   	}
    
    @Override
   	public User keepUserBDDSimply(String login, String password){
   		// TODO Auto-generated method stub
    	/*

	       */
	       
	       
   	   	 User user = persistanceUser.selectUserLogin(login);
   	  
	    	// User user = persistanceUser.lireLoginUser(login);
	    
   	    	if( ( user.getPassword().contentEquals(password) ) && ( user.getLogin().contentEquals(login) ) ) {
   	    	
   	    		CHAMP_GOOD = "goodgame";
   	    		
   	    		return user;
   	    		
   	     	
   	    	 } else {
   	    		 
   	    		CHAMP_NOTGOOD = "ERROR_NO_IN_DB";
   	        
   	    		return null;
   	     }
   	    	// User user = persistanceUser.lireLoginUser(login);
   	    	

   	     	//return user;
   	    	 
   	}
    
    @Override
   	public User keepUserBDD(String login, String password) throws Exception {
   		// TODO Auto-generated method stub
    	/*

	       */
	       
	       
   	   	 User user = persistanceUser.selectUserLogin(login);
   	  
	    	// User user = persistanceUser.lireLoginUser(login);
	    
   	    	if( ( user.getPassword().equals(password) ) && ( user.getLogin().contentEquals(login) ) ) {
   	    	
   	    		CHAMP_GOOD = "goodgame";
   	    		
   	     	
   	    	 } else {
   	    		 
   	    		CHAMP_NOTGOOD = "ERROR_NO_IN_DB";
   	         throw new Exception( "Veuillez ressaisir votre login ou votre mot de passe !" );
   	     }
   	    	// User user = persistanceUser.lireLoginUser(login);
   	    	

   	     	return user;
   	    	 
   	}
    
    
    @Override
   	public void findUserBDD(String login, String password) throws Exception {
   		// TODO Auto-generated method stub
    	/*

	       */
	       
	       
   	   	 User user = persistanceUser.selectUserLogin(login);
   	   	 
   		
	    	// User user = persistanceUser.lireLoginUser(login);
	    	 
   	       	
   	    	if( ( user.getPassword().equals(password) ) && ( user.getLogin().contentEquals(login) ) ) {
   	    	
   	    	//	CHAMP_GOOD = "goodgame";
   	   		// = "present";
   	    		
   	    				
   	    	 } else {
   	    		 
   	    		//CHAMP_NOTGOOD = "ERROR_NO_IN_DB";
   	   		//CHAMP_PRESENT = "NOT_PRESENT";
   	         throw new Exception( "Veuillez ressaisir votre login ou votre mot de passe !" );
   	     }
   	    	// User user = persistanceUser.lireLoginUser(login);
   	    	 
   	
   	    	 
   	}

    
    
    
    public String getResultat() {
        return resultat;
    }

    public Map<String, String> getErreurs() {
        return erreurs;
    }

	
    
   

    
@Override
public void findLoginUser( String login ) throws Exception {
    //	User user = persistanceUser.lireLoginUser(login);

	  if ( login != null ) {
          if ( login.length() < 3 ) {
              throw new Exception( "Le mot de passe doit contenir au moins 3 caractères." );
          }
      } else {
          throw new Exception( "Merci de saisir votre mot de passe." );
      }

	  
  	
User user =	persistanceUser.selectUserLogin(login);
	
	  
    	if( ( user.getLogin().contentEquals(login) )  ) {
    		
    		
    	 } else {
         throw new Exception( "Veuillez ressaisir votre login ou votre mot de passe !" );
     }
    	// User user = persistanceUser.lireLoginUser(login);
    	 
    	 
    	 
          
    }


    @Override
    public void validationLogin(String login)  throws Exception {
 		// TODO Auto-generated method stub
    	  if ( login != null ) {
              if ( login.length() < 3 ) {
                  throw new Exception( "Le mot de passe doit contenir au moins 3 caractères." );
              }
          } else {
              throw new Exception( "Merci de saisir votre mot de passe." );
          }
    	 
           
    	
 	}

	/**
     * Valide l'adresse email saisie.
     */
 

	
    @Override
    public void validationPassword(String password)  throws Exception {
 		// TODO Auto-generated method stub
 		
    	   if ( password != null ) {
               if ( password.length() < 3 ) {
                   throw new Exception( "Le login doit contenir au moins 3 caractères." );
               }
              /* else if(login.get(int i).equals(int.class) ) {
            	   
               }
               else if(login.con) {
            	   
               }*/
           } else {
               throw new Exception( "Merci de saisir un Login ! " );
           }
 	}



	@Override
	public String getValeurChamp(HttpServletRequest request, String nomChamp) {
		// TODO Auto-generated method stub
		   String valeur = request.getParameter( nomChamp );
	        if ( valeur == null || valeur.trim().length() == 0 ) {
	            return null;
	        } else {
	            return valeur;
	        }
	}

	@Override
	public void setErreur(String champ, String message) {
		// TODO Auto-generated method stub
		  erreurs.put( champ, message );
		
	}

	@Override
	public Utilisateur connecterUtilisateur(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setResultat(String resultat) {
		this.resultat = resultat;
	}
	
	
	
	/*  TEST */
@Override
	 public String getCookieValue( HttpServletRequest request, String nom ) {
	        Cookie[] cookies = request.getCookies();
	        if ( cookies != null ) {
	            for ( Cookie cookie : cookies ) {
	                if ( cookie != null && nom.equals( cookie.getName() ) ) {
	                    return cookie.getValue();
	                }
	            }
	        }
	        return null;
	    }
		

    public void validationEmail( String email ) throws Exception {
        
    	
    	
    	if ( email != null && !email.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ) {
            throw new Exception( "Merci de saisir une adresse mail valide." );
        }
    	

  	  if ( email != null ) {
            if ( email.length() < 3 ) {
                throw new Exception( "Le mot de passe doit contenir au moins 3 caractères." );
            }
        } else {
            throw new Exception( "Merci de saisir votre mot de passe." );
        }
  	 
    
          
          	//User user = persistanceUser.lireEmailUser(email);
    	

    	// User user = persistanceUser.lireLoginUser(login);
    	 
    }



}