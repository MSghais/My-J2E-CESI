package utilisateurs.metier;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;

import utilisateurs.entite.Role;
import utilisateurs.entite.User;
import utilisateurs.entite.Utilisateur;
import utilisateurs.persistance.PersistanceUserItf;


@Stateless
public class InscriptionForm implements InscriptionFormInterface  {
	
	
	@EJB 
	private PersistanceUserItf persistanceUser;
	
	/*@EJB 
	private MetierItfUserTeacher metier;*/
	
	private List<User> users;
	   private final String CHAMP_USERBDD  = "userInBDD";
	   
	   
    private final String CHAMP_USERNAME  = "username";
    private final String CHAMP_LOGIN   = "login";
    private final String CHAMP_EMAIL  = "email";
    private final String CHAMP_PASS   = "password";
    
    private final String CHAMP_EXISTE   = "existeErreurs";
    private final String CHAMP_CONF   = "confirmation";
    private static final String CHAMP_ERRORS   = "erreurs";

	
	 private Map<String, String> erreurs  = new HashMap<String, String>(); ;
	 
	
	    
		private Map<String,User> utilisateurs;
		
	    private String         resultat;
   
	 
	
	
	public InscriptionForm() {
		
		System.out.println("Constructeur Metier");
		//etudiants = new HashMap<Long,Etudiant>();
		erreurs = new HashMap<String, String>();
		
		System.out.println("Test initilisation jeu d'utilisateurs");
		init();
	}
	
	


    @Override
    public String getResultat() {
        return resultat;
    }
    
    @Override
    public Map<String, String> getErreurs() {
        return erreurs;
    }

    @Override
    public User inscrireUser( HttpServletRequest request ) {
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
        
        
        User user = new User();

        Utilisateur utilisateur = new Utilisateur();
        
        System.out.println("metier: Analsye des erreurs et exceptions ");
        
      // User userInBDD = persistanceUser.lireLoginUser(login);
        
       /* Validation du champ username */
       try {
           validationUsername( username );
       } catch ( Exception e ) {
       	this.setErreur( CHAMP_USERNAME, e.getMessage() );
       	this.setErreur( CHAMP_ERRORS, e.getMessage() );
       }
       
       
        System.out.println("Test  metier : username ");
        /* Validation du champ username */
        try {
            validationUsername( username );
        } catch ( Exception e ) {
        	this.setErreur( CHAMP_USERNAME, e.getMessage() );
        	this.setErreur( CHAMP_ERRORS, e.getMessage() );
        }
        user.setUsername(username);
  
        System.out.println("Test  metier : email ");
        
        try {
            validationLogin( login );
        } catch ( Exception e ) {
        	this.setErreur( CHAMP_LOGIN, e.getMessage() );
        	this.setErreur( CHAMP_ERRORS, e.getMessage() );
        }       
        user.setLogin(login);
        
        /* Validation du champ email. */
        try {
            validationEmail( email );
        } catch ( Exception e ) {
        	this.setErreur( CHAMP_EMAIL, e.getMessage() );
        	this.setErreur( CHAMP_ERRORS, e.getMessage() );
        }
        user.setEmail( email );
       
        /* Validation du champ mot de passe. */
        System.out.println("Test  metier: mot de passe");
        try {
        	validationMotsDePasse( password, confirmation );
        } catch ( Exception e ) { 

            this.setErreur( CHAMP_PASS, e.getMessage() );
            this.setErreur( CHAMP_ERRORS, e.getMessage() );
        }
        user.setPassword( password );
        
		/*
		 * System.out.println("Test  metier: User already in BDD ?"); try { User
		 * userAlreadyExist = persistanceUser.connecterUtilisateurLoginMdp(login,
		 * password); if(userAlreadyExist!= null) {
		 * 
		 * 
		 * } } catch ( Exception e ) {
		 * 
		 * this.setErreur( CHAMP_PASS, e.getMessage() ); this.setErreur( CHAMP_ERRORS,
		 * e.getMessage() ); }
		 */
     
        
        System.out.println("Test  metier: User already in BDD ?");       
        //User userAlready;
        try {
        	 userAlreadyExist(login, password);
    
        } catch ( Exception e ) { 

            this.setErreur( CHAMP_EXISTE, e.getMessage() );
        }
        
        
        // || !user.getLogin().isEmpty() 
        
        /* Initialisation du résultat global de la validation. */
        if ( erreurs.isEmpty() 
        		|| !getErreurs().containsKey(CHAMP_EXISTE)
        		|| !erreurs.containsKey(CHAMP_EXISTE)
        		|| !erreurs.containsValue(CHAMP_EXISTE)
        		||  ( !user.getLogin().isEmpty() && !user.getPassword().isEmpty() && !user.getEmail().isEmpty() )         ) 
        	{
            
			        	this.setResultat("Succès de l'inscription.");
			        	this.resultat = "Succès de l'inscription.";
			        	
			            System.out.println("Succes de l'inscription");
			            persistanceUser.persisterUser(user);
   
			         	System.out.println("Erreurs : " + erreurs);
			            System.out.println("Persister user  metier");
           
        } else {
        	       /*  erreurs.isEmpty() 
        		|| !getErreurs().containsKey(CHAMP_EXISTE)
        		|| !erreurs.containsKey(CHAMP_EXISTE)
        		|| !erreurs.containsValue(CHAMP_EXISTE) */ 	
        	this.setResultat("Échec de l'inscription.");
        	this.resultat = "Échec de  l'inscription..";
            resultat = "Échec de la connexion.";
            
            
            this.getErreurs();
            
         	System.out.println("Erreurs : " + erreurs);
            System.out.println("Echec de la connexion  metier ");
        }
        
        /*
        persisterUser(user);
        */
/*
        persistanceUser.persisterUser(user);
        
        persistanceUser.persisterUtilisateur(utilisateur);*/
        
       // return user;
        return user;
    }
    
    
    @Override
    public Utilisateur connecterUtilisateur( HttpServletRequest request ) {
        /* Récupération des champs du formulaire */
    	/*
    	System.out.println("Récupération des données utilisateurs ");
        String username = getValeurChamp( request, CHAMP_USERNAME );
        String login = getValeurChamp( request, CHAMP_LOGIN );
        
        
        String email = getValeurChamp( request, CHAMP_EMAIL );
        String password = getValeurChamp( request, CHAMP_PASS );
        String confirmation = getValeurChamp( request, CHAMP_CONF);*/

    	
		
        String username = request.getParameter("username");
        String login = request.getParameter("login");
        
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmation = request.getParameter("confirmation");
        
        
        User user = new User();

        Utilisateur utilisateur = new Utilisateur();
      
        System.out.println("Test des exceptions du formulaire a partir du MEtier");
        
        System.out.println("Test  metier : username ");
        /* Validation du champ username */
        try {
            validationUsername( username );
        } catch ( Exception e ) {
            setErreur( CHAMP_USERNAME, e.getMessage() );
        }       
        utilisateur.setUsername(username);
        
        System.out.println("Test  metier : login ");
        /* Validation du champ username */
        try {
            validationLogin( login );
        } catch ( Exception e ) {
            setErreur( CHAMP_LOGIN, e.getMessage() );
        }       
        utilisateur.setLogin(login);
        
        
        System.out.println("Test metier : email");
        /* Validation du champ email. */
        try {
            validationEmail( email );
        } catch ( Exception e ) {
            setErreur( CHAMP_EMAIL, e.getMessage() );
        }     
        utilisateur.setEmail( email );
        
        
        System.out.println("Test  metier : mot de passe");
        /* Validation du champ mot de passe. */
        try {
            validationMotsDePasse( password, confirmation );
        } catch ( Exception e ) {
            setErreur( CHAMP_PASS, e.getMessage() );
        }
        utilisateur.setMotDePasse( password );
        
        
        // || ( !utilisateur.getLogin().isEmpty() && !utilisateur.getMotDePasse().isEmpty() && !utilisateur.getEmail().isEmpty()
        
        /* Initialisation du résultat global de la validation. */
        if ( erreurs.isEmpty()   || ( !utilisateur.getLogin().isEmpty() && !utilisateur.getMotDePasse().isEmpty() && !utilisateur.getEmail().isEmpty() ) ) {
        	
       
        	this.setResultat("Succès de l'inscription.");
        	this.resultat = "Succès de l'inscription.";
        	
            System.out.println("Succes de l'inscription");
         
            resultat = "Succès de la connexion.";
            System.out.println("Succes de l'inscription ");
         	System.out.println("Erreurs : " + erreurs);
         persistanceUser.persisterUtilisateur(utilisateur);
         
         System.out.println("Persister utilisateur OK ");
        } else {
            resultat = "Échec de la connexion.";
            System.out.println("Echec de la connexion ");
         	System.out.println("Erreur : " + erreurs);
        }

        
       // return user;
        return utilisateur;
    }

  @Override
  public void validationUsername(String username)  throws Exception {
		// TODO Auto-generated method stub
		

	   if ( username != null ) {
          if ( username.length() < 3 ) {
              throw new Exception( "L'Username doit contenir au moins 3 caractères." );
          }
         /* else if(login.get(int i).equals(int.class) ) {
       	   
          }
          else if(login.con) {
       	   
          }*/
      } else {
          throw new Exception( "Merci de saisir votre mot de passe." );
      }
	}
    
  @Override
    public void validationLogin(String login)  throws Exception {
 		// TODO Auto-generated method stub
 		
    	   if ( login != null ) {
               if ( login.length() < 3 ) {
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

	/**
     * Valide l'adresse email saisie.
     */
  @Override
  public void validationEmail( String email ) throws Exception {
      if ( email != null && email.trim().length() != 0 ) {
          if ( !email.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ) {
              throw new Exception( "Merci de saisir une adresse mail valide." );
          }
      } else {
          throw new Exception( "Merci de saisir une adresse mail." );
      }
  }
  
    /**
     * Valide le mot de passe saisi.
     */
 /* @Override
    public void validationMotDePasse( String motDePasse ) throws Exception {
        if ( motDePasse != null ) {
            if ( motDePasse.length() < 3 ) {
                throw new Exception( "Le mot de passe doit contenir au moins 3 caractères." );
            }
        } else {
            throw new Exception( "Merci de saisir votre mot de passe." );
        }
    } */
  @Override
  public void validationMotsDePasse( String motDePasse, String confirmation ) throws Exception {
	    if ( motDePasse != null && confirmation != null ) {
	        if ( !motDePasse.equals( confirmation ) ) {
	            throw new Exception( "Les mots de passe entrés sont différents, merci de les saisir à nouveau." );
	        } else if ( motDePasse.length() < 3 ) {
	            throw new Exception( "Les mots de passe doivent contenir au moins 3 caractères." );
	        }
	    } else {
	        throw new Exception( "Merci de saisir et confirmer votre mot de passe." );
	    }
	}
  
  @Override
  public void userAlreadyExist( String login, String password ) throws Exception {
	  
	  User userExist = persistanceUser.connecterUtilisateurLoginMdp(login, password);
	  
	    //if ( motDePasse != null && confirmation != null ) {
	        if ( userExist!=null ) {
	            throw new Exception( "Cette utilisateur est déja existant, veuillez changer de Login ");
	        }
	      /*  } else if ( password.length() < 3 ) {
	            throw new Exception( "Les mots de passe doivent contenir au moins 3 caractères." );
	        }
	     else {
	        throw new Exception( "Merci de saisir et confirmer votre mot de passe." );
	    }*/
	}


  /*  
  @Override
    public void validationEmail( String email ) throws Exception {
        if ( email != null && !email.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ) {
            throw new Exception( "Merci de saisir une adresse mail valide." );
        }
    }
 */
    /*
     * Ajoute un message correspondant au champ spécifié à la map des erreurs.
     */
  @Override
    public void setErreur( String champ, String message ) {
        erreurs.put( champ, message );
    }

    /*
     * Méthode utilitaire qui retourne null si un champ est vide, et son contenu
     * sinon.
     */
  @Override
    public String getValeurChamp( HttpServletRequest request, String nomChamp ) {
        String valeur = request.getParameter( nomChamp );
        if ( valeur == null || valeur.trim().length() == 0 ) {
            return null;
        } else {
            return valeur;
        }
    }

@Override
public void persisterUtilisateur(Utilisateur utilisateur) {
	// TODO Auto-generated method stub
	
	//persistanceUser.persisterUtilisateur(utilisateur);
	
	System.out.println("User en cours de  persistance");
	
	
	persistanceUser.persisterUtilisateur(utilisateur);
	
	System.out.println("Utilisateur ! FIn de la fonction");
	
}

@Override
public void persisterUser(User user) {
	// TODO Auto-generated method stub
	System.out.println("User en cours de persistance");
	persistanceUser.persisterUser(user);
	
	System.out.println("User persister ! FIn de la fonction");
}


public void init() {
	
	
	System.out.println("Metier - init");
	
		/*
		 * User user = new User("Hiii","Galiere", "lesondusud@13oklm.fr",
		 * "puissantleMDP"); persistanceUser.persisterUser(user);
		 * 
		 * User user1 = new User("bijour","ehooo", "leson@oklmtest.fr", "lighttleMDP");
		 * persistanceUser.persisterUser(user1);
		 * 
		 * User user2 = new User("bijour","admin", "password", "leson@oklmtest.fr",
		 * Role.Administrateur); persistanceUser.persisterUser(user2);
		 */
	
		/*
		 * User user2 = new User("salut","ehooo", "baba@oklmtest.fr", "MDPahash");
		 * persistanceUser.persisterUser(user2);
		 * 
		 * 
		 * Utilisateur util1 = new Utilisateur("bijour","ehooo", "leson@oklmtest.fr",
		 * "lighttleMDP"); persistanceUser.persisterUtilisateur(util1);
		 * 
		 * Utilisateur util2 = new Utilisateur("salut","ehooo", "baba@oklmtest.fr",
		 * "MDPahash"); persisterUtilisateur(util2);
		 */
	
}

public void setResultat(String resultat) {
	this.resultat = resultat;
}

	


}