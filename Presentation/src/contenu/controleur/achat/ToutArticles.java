

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
import contenu.model.ModelContenu;
import utilisateurs.model.ModelUser;



@WebServlet("/toutArticles")
public class ToutArticles extends HttpServlet { 
	//private MetierItf metier = MetierEtudiantPromo.getInstance(); 
	
	@EJB
	MetierInterfaceArticle metierArticle;
	
	public static final String ATTRIBUT_USER         = "utilisateur";
    public static final String ATTRIBUT_ERREUR_MSG   = "msgErreur";
	private String erreurMsg;

	public static final String VUE   = "WEB-INF/contenu/toutArticles.jsp";
	
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
	
		
		
		
		
		ModelUser modelUser = new ModelUser(); 
		
		ModelContenu modelContenu = new ModelContenu(); 
		
		List<Article> articles = metierArticle.lireTousArticle();
		 
		 modelContenu.setArticles(articles);
			request.setAttribute("modelContenu", modelContenu);
			
			
			request.getRequestDispatcher(VUE).forward(request, response); 
			
			
	
		if(request.getParameter("supprimer") != null ) {
			
			
		}
		
	}
}

