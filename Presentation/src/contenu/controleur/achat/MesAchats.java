

package contenu.controleur.achat;

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

import contenu.entite.Article;
import contenu.metier.article.MetierInterfaceArticle;
import contenu.model.ModelCommande;
import contenu.model.ModelContenu;
import interaction.entite.Commande;
import interaction.metier.MetierInterfaceCommande;
import utilisateurs.entite.User;
import utilisateurs.model.ModelUser;



@WebServlet("/mesAchats")
public class MesAchats extends HttpServlet { 
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

	public static final String VUE   = "WEB-INF/contenu/achat/mesAchats.jsp";
	
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
		System.out.println("MyServlet Accueil est la"+ nb);
		nb++;
	
		
		ModelCommande modelCommande = new ModelCommande();
		
		HttpSession session = request.getSession();
		
		Long session_id = (Long) session.getAttribute(ATTRIBUT_USER_ID);
		System.out.println("acheteur_id = " + session_id);
		User userSession = metierArticle.rechercherUserIndex(session_id);
		System.out.println(userSession);

		List<Article> achatArticles = userSession.getAchatArticles();
	//	List<Article> achatArticles = metierCommande.lireTousAr
		System.out.println(achatArticles);	
		modelCommande.setAchatArticles(achatArticles);
		

		
		  
		  //List<Commande> listeAchatUser =  metierCommande.lireTousCommandeByAcheteur(session_id);
		  List<Commande> listeAchatUser =  metierCommande.lireTousCommandeByAcheteurException(session_id);
		  modelCommande.setCommandesListe(listeAchatUser);
		 
		
		
		
		
		request.setAttribute("modelCommande", modelCommande);
		
		request.getRequestDispatcher(VUE).forward(request, response); 
		
		
	}
	
	
}

