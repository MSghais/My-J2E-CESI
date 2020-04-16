

package contenu.controleur.vente;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.net.httpserver.Filter;

import contenu.entite.Article;
import contenu.enume.StatutArticle;
import contenu.metier.article.MetierInterfaceArticle;
import contenu.model.ModelCommande;
import contenu.model.ModelContenu;
import interaction.entite.Commande;
import interaction.enume.StatutCommande;
import interaction.metier.MetierInterfaceCommande;
import utilisateurs.entite.User;
import utilisateurs.model.ModelUser;



@WebServlet("/mesCommandes")
public class MesCommandes extends HttpServlet { 
	//private MetierItf metier = MetierEtudiantPromo.getInstance(); 
	
	@EJB
	MetierInterfaceArticle metierArticle;
	
	@EJB
	MetierInterfaceCommande metierCommande;
	
	public static final String ATTRIBUT_USER         = "utilisateur";
    public static final String ATTRIBUT_ERREUR_MSG   = "msgErreur";
    

	public static final String ATTRIBUT_USER_SESSION         = "utilisateurSession";
	public static final String ATTRIBUT_USER_LOGIN         = "userLogin";
	public static final String ATTRIBUT_USER_ID      = "userId";
	public static final String ATTRIBUT_USER_ROLE      = "userRole";
	
	public static final String ATTRIBUT_ARTICLE_ID      = "acheter";
	public static final String ATTRIBUT_ARTICLE_ACHAT      = "acheterArticle";
	public static final String ATTRIBUT_ARTICLE_MODIF      = "articleModification";
	private String erreurMsg;

	public static final String VUE   = "WEB-INF/contenu/vente/mesCommandes.jsp";
	
	@PostConstruct
	public void init() {
		System.out.println("MyServlet init");
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		
		doPost(request,response);
		

	 
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int nb = 0;
		System.out.println("Mes Commandes ");
		nb++;
	
		
		ModelCommande modelCommande = new ModelCommande();
		
		HttpSession session = request.getSession();
		
		Long session_id = (Long) session.getAttribute(ATTRIBUT_USER_ID);
		System.out.println("acheteur_id = " + session_id);
		

		User userSession = metierArticle.rechercherUserIndex(session_id);
		System.out.println("User session mes COmmandes = " +userSession);
		System.out.println("recherche vente article statut reserve and user session");
		//	List<Article> ventesArticles = metierCommande.lireTousArticleByVendeurException(session_id);
		
	
		List<Article> ventesArticles = metierCommande.lireTousArticleReserveByVendeurException(session_id);
		
		modelCommande.setVentesArticles(ventesArticles);

		request.setAttribute("modelCommande", modelCommande);
		request.getRequestDispatcher(VUE).forward(request, response); 

			
			if( request.getParameter("modifier") != null)
			{
				System.out.println("Boutton modifier un Article");
				Long article_id = Long.valueOf(request.getParameter("modifier"));
				System.out.println("article id is = " + article_id);
				
				Article articleModif = metierArticle.rechercherArticleIndex(article_id);
				System.out.println("Article modif is " + articleModif);
				request.setAttribute(ATTRIBUT_ARTICLE_MODIF, articleModif);
				
				System.out.println("Renvoi modification Article");		
				request.getRequestDispatcher("/modificationArticle").include(request, response);
			
			}
			
			if( request.getParameter("supprimer") != null)
			{
				
				System.out.println("Boutton supprimer un Article");
				Long article_id = Long.valueOf(request.getParameter("supprimer"));
				
				System.out.println("supprimer commande with Article index");
				metierCommande.supprimerCommandeByArticleIndexException(article_id);
				
				
				System.out.println("supprimer article by index exception");
				metierArticle.supprimerArticleByIndexException(article_id);
				
	
				/*
				 * Commande commande = metierCommande.selectCommandeByArticle(article_id);
				 * System.out.println("supprimer commande");
				 * metierCommande.supprimerCommande(commande);
				 */
				
				/*
				 * Article articleDelete = metierArticle.rechercherArticleIndex(article_id);
				 * 
				 * System.out.println("supprimer article");
				 * metierArticle.supprimerArticle(articleDelete);
				 */
				//request.getRequestDispatcher("/mesCommandes").include(request, response);
				request.getRequestDispatcher(VUE).forward(request, response); 
				
			}
			
			if( request.getParameter("valider") != null)
			{
				
				System.out.println("Boutton Valider un Article");
				Long article_id = Long.valueOf(request.getParameter("valider"));	
				//Article articleEnvoyer = metierArticle.rechercherArticleIndex(article_id);
				//System.out.println("Update article statut");
				// metierCommande.updateArticleStatut(articleEnvoyer, StatutArticle.VENDU);
				/* System.out.println("Update Commande statut");
				Commande commande = metierCommande.selectCommandeByArticle(article_id);
				metierCommande.updateCommandeStatut( commande, StatutCommande.VALIDEE);
				
				System.out.println("Update Date envoi statut");
				metierCommande.updateCommandeDateEnvoi(commande);
				 */
				System.out.println("Update article with index and status");
				metierArticle.validerArticeByIndexException(article_id);	
				
				System.out.println("Valider Commande statut with article index");
				metierCommande.validerCommandeByArticleIndexException(article_id);
		
				//request.getRequestDispatcher("/mesCommandes").include(request, response);
				request.getRequestDispatcher(VUE).forward(request, response); 
				
			}
			
			if( request.getParameter("envoyer") != null)
			{
				System.out.println("Boutton envoyer un Article");
				
				Long article_id = Long.valueOf(request.getParameter("envoyer"));	
				Article articleEnvoyer = metierArticle.rechercherArticleIndex(article_id);	
				System.out.println("Update article statut");
				metierCommande.updateArticleStatut(articleEnvoyer, StatutArticle.EXPEDIEE);
					
				Commande commande = metierCommande.selectCommandeByArticle(article_id);
				System.out.println("Update Commande statut");
				metierCommande.updateCommandeStatut( commande, StatutCommande.ENVOYEE);
				System.out.println("Update Date envoi Commande");
				metierCommande.updateCommandeDateEnvoi(commande);
				
				//request.getRequestDispatcher("/mesCommandes").include(request, response);
				request.getRequestDispatcher(VUE).forward(request, response); 
				
				
			}
			
			if( request.getParameter("supprimerCommande") != null)
			{
				
				System.out.println("Boutton supprimer une Commande");
				Long commande_id = Long.valueOf(request.getParameter("supprimerCommande"));
				System.out.println("Commande Index is =" + commande_id);
				/*
				 * Commande commande = metierCommande.lireCommande(commande_id);
				 * System.out.println("Commande Index is =" + commande);
				 * metierCommande.supprimerCommande(commande);
				 */
				System.out.println("supprimer une Commande avec un article index");
				metierCommande.supprimerCommandeByArticleIndexException(commande_id);
				System.out.println("Commande supprimer");
				
				//request.getRequestDispatcher("/mesCommandes").include(request, response);
				request.getRequestDispatcher(VUE).forward(request, response); 
				
				
			}
			
			if( request.getParameter("validerCommande") != null)
			{
				
				System.out.println("Boutton validez une Commande");
		
				Long commande_id = Long.valueOf(request.getParameter("validerCommande"));
				
				System.out.println("valider une Commande avec un article index");
				metierCommande.validerCommandeByArticleIndexException(commande_id);
				/*
				 * System.out.println("Commande Index is =" + commande_id); Commande
				 * commandeEnvoi = metierCommande.lireCommande(commande_id);
				 * System.out.println("Commande Index is =" + commandeEnvoi);
				 * System.out.println("Update commande statut");
				 * metierCommande.updateCommandeStatut( commandeEnvoi, StatutCommande.VALIDEE);
				 * System.out.println("Update date commande");
				 * metierCommande.updateCommandeDateEnvoi(commandeEnvoi);
				 */
				
				request.getRequestDispatcher("/mesCommandes").include(request, response);
				
							
			}
			
			if( request.getParameter("envoyerCommande") != null)
			{
						
				System.out.println("Boutton envoyer une Commande");
				Long commande_id = Long.valueOf(request.getParameter("envoyerCommande"));
				System.out.println("Commande Index is =" + commande_id);
				
	
				System.out.println("ENVOYER une Commande avec un article index");
				metierCommande.envoyerCommandeByArticleIndexException(commande_id);
				
				Commande commandeEnvoi = metierCommande.lireCommande(commande_id);		
				System.out.println("Commande Index is =" + commandeEnvoi);
				System.out.println("Update commande statut");
				metierCommande.updateCommandeStatut( commandeEnvoi, StatutCommande.ENVOYEE);
				System.out.println("Update date commande");
				metierCommande.updateCommandeDateEnvoi(commandeEnvoi);
				
				
				request.getRequestDispatcher("/mesCommandes").include(request, response);
				
				
			}
		
	
		
		
	} // Fin do POST
	
} // Fin de classe


/* 
		
		List<Long> articles_id=null;
		
		List<Commande> commandesListe= new ArrayList<Commande>();
		for(int i = 0; i <= ventesArticles.size(); i++) {
			
			articles_id.add(ventesArticles.get(i).getId() );
			
			
			
			
			
		
		System.out.println("article id are " + articles_id);
		System.out.println("recherche vente article statut reserve and user session");
		
		
		
		for(int j =0; j <= articles_id.size(); j++) {
			
			
			Long id = articles_id.get(j);
			Commande commandeArticles = metierCommande.lireTousCommandeByArticleException(id);
			System.out.println("articles par Commande vente" + commandeArticles);
			
		
			commandesListe.add(commandeArticles);
			System.out.println("articles List par Commande vente" + commandesListe);
			
			
		}

		}*/
