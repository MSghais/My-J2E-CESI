

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

import contenu.entite.Article;
import contenu.metier.article.MetierInterfaceArticle;
import contenu.model.ModelContenu;
import interaction.entite.Commande;
import interaction.metier.MetierInterfaceCommande;
import utilisateurs.entite.User;
import utilisateurs.model.ModelUser;



@WebServlet("/mesVentes")
public class MesVentes extends HttpServlet { 
	//private MetierItf metier = MetierEtudiantPromo.getInstance(); 
	
	@EJB
	MetierInterfaceArticle metierArticle;
	 
	@EJB
	MetierInterfaceCommande metierCommande;
		
	
	public static final String ATTRIBUT_USER         = "utilisateur";
	public static final String ATTRIBUT_USER_SESSION         = "utilisateurSession";
	public static final String ATTRIBUT_USER_LOGIN         = "userLogin";
	public static final String ATTRIBUT_USER_ID      = "userId";
	public static final String ATTRIBUT_USER_ROLE      = "userRole";
	
    public static final String ATTRIBUT_ERREUR_MSG   = "msgErreur";
    public static final String ATTRIBUT_ERREUR_MAP  = "erreursMaps";
	public static final String ATTRIBUT_ARTICLE_MODIF      = "articleModification";
	
	
	private String erreurMsg;

	public static final String VUE   = "WEB-INF/contenu/vente/mesArticlesVente.jsp";
	
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
	
		HttpSession session = request.getSession();		
		ModelContenu modelContenu = new ModelContenu(); 
	
		User user = (User) session.getAttribute(ATTRIBUT_USER);
		Long user_id = (Long) session.getAttribute(ATTRIBUT_USER_ID);
		
		System.out.println("USER ID session are = "+ user_id);
		List<Article> articlesEnVentes = metierArticle.lireTousArticleByUserVente(user_id); 
		 modelContenu.setArticles(articlesEnVentes);
	
		 request.setAttribute("modelContenu", modelContenu);		
   		 request.getRequestDispatcher(VUE).forward(request, response); 
			
			
			if( request.getParameter("modifier") != null)
			{
				System.out.println("Boutton modifier un Article");
				Long article_id = Long.valueOf(request.getParameter("modifier"));
				System.out.println("article id is = " + article_id);
				
				Article articleModif = metierArticle.rechercherArticleIndex(article_id);
				System.out.println("Article modif is " + articleModif);
				request.setAttribute(ATTRIBUT_ARTICLE_MODIF, articleModif);
				
			/*
			 * System.out.println(" response toutArticle sendRedirect acheter Article");
			 * response.sendRedirect( request.getContextPath() + "/modificationArticle");
			 */
				System.out.println("Renvoi modification Article with include");
				request.getRequestDispatcher("/modificationArticle").include(request, response);
				
			}
			
			if( request.getParameter("supprimer") != null)
			{
				
				System.out.println("Boutton supprimer un Article avec la Commande ");
				Long article_id = Long.valueOf(request.getParameter("supprimer"));
				System.out.println("Article id params = "+ article_id);
				

				
				System.out.println("supprimer commande with Article index");
				metierCommande.supprimerCommandeByArticleIndexException(article_id);
				
				
				System.out.println("supprimer article by index exception");
				metierArticle.supprimerArticleByIndexException(article_id);
		
				request.getRequestDispatcher(VUE).forward(request, response); 
				
			
			}
		
		
	}
}

