package utilisateurs.model;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


import utilisateurs.entite.User;

public final class InscriptionForm {
    private static final String CHAMP_USERNAME  = "username";
    private static final String CHAMP_LOGIN   = "login";
    private static final String CHAMP_EMAIL  = "email";
    private static final String CHAMP_PASS   = "password";
    private static final String CHAMP_ROLE   = "role";
    
    private String              resultat;
    private Map<String, String> erreurs      = new HashMap<String, String>();

    public String getResultat() {
        return resultat;
    }

    public Map<String, String> getErreurs() {
        return erreurs;
    }

    public User inscrireUtilisateur( HttpServletRequest request ) {
        /* Récupération des champs du formulaire */
        String username = getValeurChamp( request, CHAMP_USERNAME );
        String login = getValeurChamp( request, CHAMP_LOGIN );
        
        
        String email = getValeurChamp( request, CHAMP_EMAIL );
        String password = getValeurChamp( request, CHAMP_PASS );

        User user = new User();

        
        /* Validation du champ username */
        try {
            validationUsername( username );
        } catch ( Exception e ) {
            setErreur( CHAMP_LOGIN, e.getMessage() );
        }
        user.setUsername(username);

        
        
        /* Validation du champ email. */
        try {
            validationEmail( email );
        } catch ( Exception e ) {
            setErreur( CHAMP_EMAIL, e.getMessage() );
        }
        user.setEmail( email );

        /* Validation du champ mot de passe. */
        try {
            validationMotDePasse( password );
        } catch ( Exception e ) {
            setErreur( CHAMP_PASS, e.getMessage() );
        }
        user.setPassword( password );

        /* Initialisation du résultat global de la validation. */
        if ( erreurs.isEmpty() ) {
            resultat = "Succès de la connexion.";
        } else {
            resultat = "Échec de la connexion.";
        }

        return user;
    }

    private void validationUsername(String username)  throws Exception {
		// TODO Auto-generated method stub
		
    	  if ( username != null && !username.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ) {
              throw new Exception( "Merci de saisir un User Name Valide !" );
          }
	}
    
    private void validationLogin(String login)  throws Exception {
 		// TODO Auto-generated method stub
 		
    	   if ( login != null ) {
               if ( login.length() < 3 ) {
                   throw new Exception( "Le mot de passe doit contenir au moins 3 caractères." );
               }
              /* else if(login.get(int i).equals(int.class) ) {
            	   
               }
               else if(login.con) {
            	   
               }*/
           } else {
               throw new Exception( "Merci de saisir votre mot de passe." );
           }
 	}

	/**
     * Valide l'adresse email saisie.
     */
    private void validationEmail( String email ) throws Exception {
        if ( email != null && !email.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ) {
            throw new Exception( "Merci de saisir une adresse mail valide." );
        }
    }

    /**
     * Valide le mot de passe saisi.
     */
    private void validationMotDePasse( String motDePasse ) throws Exception {
        if ( motDePasse != null ) {
            if ( motDePasse.length() < 3 ) {
                throw new Exception( "Le mot de passe doit contenir au moins 3 caractères." );
            }
        } else {
            throw new Exception( "Merci de saisir votre mot de passe." );
        }
    }

    /*
     * Ajoute un message correspondant au champ spécifié à la map des erreurs.
     */
    private void setErreur( String champ, String message ) {
        erreurs.put( champ, message );
    }

    /*
     * Méthode utilitaire qui retourne null si un champ est vide, et son contenu
     * sinon.
     */
    private static String getValeurChamp( HttpServletRequest request, String nomChamp ) {
        String valeur = request.getParameter( nomChamp );
        if ( valeur == null || valeur.trim().length() == 0 ) {
            return null;
        } else {
            return valeur;
        }
    }


}