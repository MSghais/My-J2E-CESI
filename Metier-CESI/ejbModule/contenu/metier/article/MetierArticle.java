package contenu.metier.article;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import contenu.entite.Article;
import contenu.entite.Theme;
import contenu.persistance.article.PersistanceArticleItf;
import contenu.persistance.theme.PersistanceThemeItf;
import utilisateurs.entite.User;
import utilisateurs.entite.Utilisateur;







@Stateless
public class MetierArticle implements MetierInterfaceArticle {
	
	
	
	@EJB 
	private PersistanceArticleItf persistanceArticle;
	
	@EJB 
	private PersistanceThemeItf persistanceTheme;

	private Map<Long,Article> articles;
	
	private Map<Theme,Article> articlesThematiques;
	

	 private Map<String, String> erreurs;
	 
	 private String              resultat;
     
	 public static final String ATTRIBUT_USER = "utilisateur";
    private final String CHAMP_TITRE  = "art_titre";
    private final String CHAMP_URL   = "art_url";
    private final String CHAMP_DESCL  = "art_description";
    private final String CHAMP_CONTENT   = "art_contenu";
    
    private final String CHAMP_CONF   = "confirmation";
    

	
	public MetierArticle() {
		System.out.println("Constructeur Metier");
		articles = new HashMap<Long,Article>();
		
		erreurs = new HashMap<String, String>();
		
		articlesThematiques = new HashMap<Theme, Article>();
		init();
	}
	
	
	
	public void persisterArticle(Article article) {
		persistanceArticle.persisterArticle(article);
	}
	
	
	public void creerArticle(Article article) {
		articles.put(article.getId(),article);
	}
	
	
	public Article lireArticle(Long id) {
		return articles.get(id);
	}


	
	public void mettreAJourArticle(Article article) {
		articles.put(article.getId(),article);
	}
	
	public void supprimerArticle(Article article) {
		articles.remove(article.getId());
	}
	
	
	public List<Article> lireTousArticle() {
		//return new ArrayList<>(etudiants.values());  
		
		return persistanceArticle.lireTousArticle();
	}
	
	
	private void init() {
		System.out.println("Metier - init");
	
				/*
		Article article = new Article("Math","Le mond est indéfini, les inconnus sont multiples", "puissant le philosophe du dimanche qui fait le mec qui parle des Math zerma");
		persistanceArticle.persisterArticle(article);
		
		Article article1 = new Article("Info","2020 c'est pas des lol", "ça taquine en bombe");
		persistanceArticle.persisterArticle(article1);
		*/
	
	}


	@Override
	public Article creerArticleRequest(HttpServletRequest request, HttpSession session) {
		// TODO Auto-generated method stub

		
	

        String art_titre = request.getParameter("art_titre");
        String art_url = request.getParameter("art_url");
        
        String art_description = request.getParameter("art_description");
        String art_contenu = request.getParameter("art_contenu");
        Article article = new Article();

     
      
        System.out.println("Test des exceptions du formulaire a partir du MEtier");
        
        System.out.println("Test  metier : titre ");
        /* Validation du champ username */
        try {
           validationTitre(art_titre);
        } catch ( Exception e ) {
            this.setErreur( CHAMP_TITRE, e.getMessage() );
        }       
        article.setTitre(art_titre);
        
        System.out.println("Test  metier : url ");
        /* Validation du champ username */
        try {
            validationUrl(art_url);
            
        } catch ( Exception e ) {
            this.setErreur( CHAMP_URL, e.getMessage() );
        }       
        article.setUrl(art_url);
        
        
        System.out.println("Test  metier : description ");
        /* Validation du champ username */
        try {
         validationDescription(art_description);
            
        } catch ( Exception e ) {
            setErreur( CHAMP_DESCL, e.getMessage() );
        }       
        article.setDescription(art_description);
        
        
        
        System.out.println("Test  metier : Contenu");
        /* Validation du champ mot de passe. */
        try {
            validationContenu(art_contenu);
        } catch ( Exception e ) {
            setErreur( CHAMP_CONTENT, e.getMessage() );
        }
        article.setContenu(art_contenu);
        
        
        
       // session.
        User userSession = (User) session.getAttribute(ATTRIBUT_USER);
        
        HttpSession sessionServlet = request.getSession();
        Principal userPrincipal = request.getUserPrincipal();
       
        
      //  article.setUser_vendeur(userSession);
        
        
        // || ( !utilisateur.getLogin().isEmpty() && !utilisateur.getMotDePasse().isEmpty() && !utilisateur.getEmail().isEmpty()
        
        /* Initialisation du résultat global de la validation. */
        if ( erreurs.isEmpty()   || ( !article.getTitre().isEmpty()  && !article.getDescription().isEmpty() && !article.getContenu().isEmpty() ) ) {
        	//|| ( !article.getArt_titre().isEmpty() && !article.getArt_url().isEmpty() && !article.getArt_description().isEmpty() && !article.getArt_contenu().isEmpty() )
        	this.setResultat("Succès dépot Article");
        	this.resultat = "Succès du dépot Article";
        	
       
            resultat = "Succès de la connexion.";
            System.out.println("Succes du dépot Article ");
         	System.out.println("Erreurs : " + erreurs);
         persistanceArticle.persisterArticle(article);
         
         System.out.println("Persister utilisateur OK ");
        } else {
            resultat = "Échec de la connexion.";
            

        	this.setResultat(resultat);
        	
            System.out.println("Echec de la connexion ");
         	System.out.println("Erreur : " + erreurs);
        }

        
       // return user;
        return article;
	}
	

	@Override
	public Article creerArticleUser(HttpServletRequest request, User userParams) {
		// TODO Auto-generated method stub

		
	

        String art_titre = request.getParameter("art_titre");
        String art_url = request.getParameter("art_url");
        
        String art_description = request.getParameter("art_description");
        String art_contenu = request.getParameter("art_contenu");
        Article article = new Article();

     
      
        System.out.println("Test des exceptions du formulaire a partir du MEtier");
        
        System.out.println("Test  metier : titre ");
        /* Validation du champ username */
        try {
           validationTitre(art_titre);
        } catch ( Exception e ) {
            this.setErreur( CHAMP_TITRE, e.getMessage() );
        }       
        article.setTitre(art_titre);
        
        System.out.println("Test  metier : url ");
        /* Validation du champ username */
        try {
            validationUrl(art_url);
            
        } catch ( Exception e ) {
            this.setErreur( CHAMP_URL, e.getMessage() );
        }       
        article.setUrl(art_url);
        
        
        System.out.println("Test  metier : description ");
        /* Validation du champ username */
        try {
         validationDescription(art_description);
            
        } catch ( Exception e ) {
            setErreur( CHAMP_DESCL, e.getMessage() );
        }       
        article.setDescription(art_description);
        
        
        
        System.out.println("Test  metier : Contenu");
        /* Validation du champ mot de passe. */
        try {
            validationContenu(art_contenu);
        } catch ( Exception e ) {
            setErreur( CHAMP_CONTENT, e.getMessage() );
        }
        article.setContenu(art_contenu);
        
        
        
       // session.
       
     //    User userSession = (User) session.getAttribute(ATTRIBUT_USER);
        
		/*
		 * HttpSession sessionServlet = request.getSession(); Principal userPrincipal =
		 * request.getUserPrincipal();
		 */
       
        
   //     article.setUser_vendeur(userParams.getUser_id());
        
        
        // || ( !utilisateur.getLogin().isEmpty() && !utilisateur.getMotDePasse().isEmpty() && !utilisateur.getEmail().isEmpty()
        
        /* Initialisation du résultat global de la validation. */
        if ( erreurs.isEmpty()   || ( !article.getTitre().isEmpty()  && !article.getDescription().isEmpty() && !article.getContenu().isEmpty() ) ) {
        	//|| ( !article.getArt_titre().isEmpty() && !article.getArt_url().isEmpty() && !article.getArt_description().isEmpty() && !article.getArt_contenu().isEmpty() )
        	this.setResultat("Succès dépot Article");
        	this.resultat = "Succès du dépot Article";
        	
       
            resultat = "Succès de la connexion.";
            System.out.println("Succes du dépot Article ");
         	System.out.println("Erreurs : " + erreurs);
         persistanceArticle.persisterUserArticle(userParams, article);
         
         System.out.println("Persister Article OK ");
         return article;
        } else {
            resultat = "Échec de la connexion.";
            

        	this.setResultat(resultat);
        	
            System.out.println("Echec de la connexion ");
         	System.out.println("Erreur : " + erreurs);
        }

        
       // return user;
        return article;
	}

	@Override
	public Article creerArticle(HttpServletRequest request) {
		// TODO Auto-generated method stub

		


        String art_titre = request.getParameter("art_titre");
        String art_url = request.getParameter("art_url");
        
        String art_description = request.getParameter("art_description");
        String art_contenu = request.getParameter("art_contenu");
        Article article = new Article();

     
      
        System.out.println("Test des exceptions du formulaire a partir du MEtier");
        
        System.out.println("Test  metier : titre ");
        /* Validation du champ username */
        try {
           validationTitre(art_titre);
        } catch ( Exception e ) {
            this.setErreur( CHAMP_TITRE, e.getMessage() );
        }       
        article.setTitre(art_titre);
        
        System.out.println("Test  metier : url ");
        /* Validation du champ username */
        try {
            validationUrl(art_url);
            
        } catch ( Exception e ) {
            this.setErreur( CHAMP_URL, e.getMessage() );
        }       
        article.setUrl(art_url);
        
        
        System.out.println("Test  metier : description ");
        /* Validation du champ username */
        try {
         validationDescription(art_description);
            
        } catch ( Exception e ) {
            setErreur( CHAMP_DESCL, e.getMessage() );
        }       
        article.setDescription(art_description);
        
        
        
        System.out.println("Test  metier : Contenu");
        /* Validation du champ mot de passe. */
        try {
            validationContenu(art_contenu);
        } catch ( Exception e ) {
            setErreur( CHAMP_CONTENT, e.getMessage() );
        }
        article.setContenu(art_contenu);
        
        
      
        
        
        // || ( !utilisateur.getLogin().isEmpty() && !utilisateur.getMotDePasse().isEmpty() && !utilisateur.getEmail().isEmpty()
        
        /* Initialisation du résultat global de la validation. */
        if ( erreurs.isEmpty()   || ( !article.getTitre().isEmpty()  && !article.getDescription().isEmpty() && !article.getContenu().isEmpty() ) ) {
        	//|| ( !article.getArt_titre().isEmpty() && !article.getArt_url().isEmpty() && !article.getArt_description().isEmpty() && !article.getArt_contenu().isEmpty() )
        	this.setResultat("Succès dépot Article");
        	this.resultat = "Succès du dépot Article";
        	
       
            resultat = "Succès de la connexion.";
            System.out.println("Succes du dépot Article ");
         	System.out.println("Erreurs : " + erreurs);
         persistanceArticle.persisterArticle(article);
         
         
         persistanceArticle.persisterArticle(article);
         
         System.out.println("Persister utilisateur OK ");
        } else {
            resultat = "Échec de la connexion.";
            

        	this.setResultat(resultat);
        	
            System.out.println("Echec de la connexion ");
         	System.out.println("Erreur : " + erreurs);
        }

        
       // return user;
        return article;
	}


	@Override
	public void validationTitre(String titre) throws Exception {
		// TODO Auto-generated method stub
		 if ( titre != null ) {
	          if ( titre.length() < 3 ) {
	              throw new Exception( "Le titre doit contenir au moins 3 caractères." );
	          }
	         /* else if(login.get(int i).equals(int.class) ) {
	       	   
	          }
	          else if(login.con) {
	       	   
	          }*/
	      } else {
	          throw new Exception( "Merci de saisir un titre plus précis." );
	      }
	}





	@Override
	public void validationUrl(String url) throws Exception {
		 if ( url != null ) {
	          if ( url.length() < 3 ) {
	              throw new Exception( "Le titre doit contenir au moins 3 caractères." );
	          }
	         /* else if(login.get(int i).equals(int.class) ) {
	       	   
	          }
	          else if(login.con) {
	       	   
	          }*/
	      } else {
	          throw new Exception( "Merci de saisir un titre plus précis." );
	      }
	}



	@Override
	public void validationDescription(String description) throws Exception {
		// TODO Auto-generated method stub
		 if ( description != null ) {
	          if ( description.length() < 3 ) {
	              throw new Exception( "Le titre doit contenir au moins 3 caractères." );
	          }
	         /* else if(login.get(int i).equals(int.class) ) {
	       	   
	          }
	          else if(login.con) {
	       	   
	          }*/
	      } else {
	          throw new Exception( "Merci de saisir un titre plus précis." );
	      }
	}
	


	@Override
	public void validationContenu(String contenu) throws Exception {
		// TODO Auto-generated method stub
		 if ( contenu != null ) {
	          if ( contenu.length() < 3 ) {
	              throw new Exception( "Le contenu doit contenir au moins 3 caractères." );
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
	public void validationTheme(String theme) throws Exception {
		// TODO Auto-generated method stub
		 if ( theme != null ) {
	          if ( theme.length() < 3 ) {
	              throw new Exception( "Le titre doit contenir au moins 3 caractères." );
	          }
	         /* else if(login.get(int i).equals(int.class) ) {
	       	   
	          }
	          else if(login.con) {
	       	   
	          }*/
	      } else {
	          throw new Exception( "Merci de saisir un titre plus précis." );
	      }
	}



	@Override
	public void validationSkills(String skills) throws Exception {
		// TODO Auto-generated method stub
		
	}




	@Override
	public void setErreur(String champ, String message) {
		// TODO Auto-generated method stub
		erreurs.put(champ, message );
	}



	@Override
	public Map<String, String> getErreurs() {
		// TODO Auto-generated method stub
		return erreurs;
	}





	@Override
	public String getResultat() {
		// TODO Auto-generated method stub
		return null;
	}


	 @Override
	    public String getValeurChamp( HttpServletRequest request, String nomChamp ) {
	        String valeur = request.getParameter( nomChamp );
	        if ( valeur == null || valeur.trim().length() == 0 ) {
	            return null;
	        } else {
	            return valeur;
	        }
	    }



	public void setResultat(String resultat) {
		this.resultat = resultat;
	}

/*
	@Override
	public User connecterUser(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}
 
	*/
	 
	 
	/* 
	public void setErreurs(Map<String, String> erreurs) {
		this.erreurs = erreurs;
	}
*  */


	/*
	public void persisterEtudiantPromotion(String acronyme,Etudiant etudiant) {
		System.out.println("Metier - inscrireEtudiantPromotion acronyme=" + acronyme + " etudiant=" + etudiant);
		etudiant.setId(idEtudiant);
		etudiants.put(idEtudiant, etudiant);
		idEtudiant++;
		Promotion promotion = promotions.get(acronyme);
		promotion.addEtudiant(etudiant);
	}
	
	
	public void inscrireEtudiantPromotion(String acronyme,Etudiant etudiant) {
		System.out.println("Metier - inscrireEtudiantPromotion acronyme=" + acronyme + " etudiant=" + etudiant);
		etudiant.setId(idEtudiant);
		etudiants.put(idEtudiant, etudiant);
		idEtudiant++;
		Promotion promotion = promotions.get(acronyme);
		promotion.addEtudiant(etudiant);
	}*/
	
	

	
	
	/*  	 public void ajouterRetard(Long index) {
	 
		//etudiants.get(index).getRetard().setRetard(retard++);
		//Metier metier = null;
		System.out.println("id etudiant=" + index);
		Etudiant etudiant = lireEtudiant(index);
		
		System.out.println(etudiant);
		etudiant.setRetard(etudiant.getRetard()+1);
		System.out.println(etudiant);
		
	} 
	
	public void ajouterAbsence(Long id) {
		System.out.println("id etudiant=" + id);
		Etudiant etudiant = lireEtudiant(id);
		System.out.println(etudiant);
		etudiant.setAbsence(etudiant.getAbsence()+1);
		System.out.println(etudiant);
		/*etudiants.get(index).getRetard().setRetard(retard++);
		int absence= etudiant.getRetard();
		etudiant.setAbsence(absence++);
	}   */ 
	
	
	
}
