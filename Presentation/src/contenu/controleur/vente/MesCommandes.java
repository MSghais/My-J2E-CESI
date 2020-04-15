

package contenu.controleur.vente;

import java.io.IOException;
import java.security.Principal;
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
		
		//List<Article> articlesVentes = userSession.getVentesArticles();
		
		System.out.println("recherche vente article statut reserve and user session");
		List<Article> ventesArticles = metierCommande.lireTousArticleReserveByVendeurException(session_id);
		
		System.out.println("articles Liste vente" + ventesArticles);
		
		modelCommande.setVentesArticles(ventesArticles);
		
		// List<Commande> listeCommandeUser =  


		request.setAttribute("modelCommande", modelCommande);
		
		request.getRequestDispatcher(VUE).forward(request, response); 
		
		
		
		
		if( request.getParameter("modifier") != null)
		{
			
			
		}
		
		if( request.getParameter("supprimer") != null)
		{
			
			Long article_id = Long.valueOf(request.getParameter("article_id"));
			
			Article articleDelete = metierArticle.rechercherArticleIndex(article_id);
			metierArticle.supprimerArticle(articleDelete);
			
		}
		
		if( request.getParameter("envoyer") != null)
		{
			
			Long article_id = Long.valueOf(request.getParameter("article_id"));
			
			Article articleEnvoyer = metierArticle.rechercherArticleIndex(article_id);
			
			metierCommande.updateArticleStatut(articleEnvoyer, StatutArticle.VENDU);
			
			
			Commande commande = metierCommande.selectCommandeByArticle(article_id);
			
			
			metierCommande.updateCommandeStatut( commande, StatutCommande.VALIDEE);
			
		}
		
		//List<Commande> listeCommandeUser =  metierCommande.lireTousVenteEnCours(acheteur_id);	
				//List<Commande> listeCommandeUser =  metierCommande.rechercherCommandeByAcheteur(session_id);	
				/* 	System.out.println(listeCommandeUser);
				
				modelCommande.setCommandesListe(listeCommandeUser); */
		
		
	}
}

