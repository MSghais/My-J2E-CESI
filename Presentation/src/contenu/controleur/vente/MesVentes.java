

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
import utilisateurs.entite.User;
import utilisateurs.model.ModelUser;



@WebServlet("/mesVentes")
public class MesVentes extends HttpServlet { 
	//private MetierItf metier = MetierEtudiantPromo.getInstance(); 
	
	@EJB
	MetierInterfaceArticle metierArticle;
	
	public static final String ATTRIBUT_USER         = "utilisateur";
	public static final String ATTRIBUT_USER_SESSION         = "utilisateurSession";
	public static final String ATTRIBUT_USER_LOGIN         = "userLogin";
	public static final String ATTRIBUT_USER_ID      = "userId";
	public static final String ATTRIBUT_USER_ROLE      = "userRole";
	
    public static final String ATTRIBUT_ERREUR_MSG   = "msgErreur";
    public static final String ATTRIBUT_ERREUR_MAP  = "erreursMaps";
    
	
	
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
		
		
		ModelUser modelUser = new ModelUser(); 
		
		ModelContenu modelContenu = new ModelContenu(); 
		
		//List<Article> articles = metierArticle.lireTousArticle();
		
		User user = (User) session.getAttribute(ATTRIBUT_USER);
		
		Long user_id = (Long) session.getAttribute(ATTRIBUT_USER_ID);
		
		System.out.println("USER ID session are = "+ user_id);

		List<Article> articlesEnVentes = metierArticle.lireTousArticleByUserVente(user_id);
		 
		 modelContenu.setArticles(articlesEnVentes);
		 

			// List<Article> articlesVentes = metierArticle.lireTousArticleByUserVente(user_id);
			 
			 //modelContenu.setArticles(articlesVentes);
			request.setAttribute("modelContenu", modelContenu);
			
			request.getRequestDispatcher(VUE).forward(request, response); 
	
		
		
	}
}

