

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
import contenu.entite.Theme;
import contenu.metier.article.MetierInterfaceArticle;
import contenu.metier.theme.MetierInterfaceTheme;
import contenu.model.ModelAllContent;
import contenu.model.ModelContenu;
import utilisateurs.entite.User;
import utilisateurs.model.ModelUser;



@WebServlet("/Shopping")
public class ToutArticles extends HttpServlet { 
	//private MetierItf metier = MetierEtudiantPromo.getInstance(); 
	
	@EJB
	MetierInterfaceArticle metierArticle;
	
	@EJB
	MetierInterfaceTheme metierTheme;
	
    public static final String ATT_USER = "utilisateur";
    public static final String ATT_FORM = "form";
	public static final String ATTRIBUT_USER         = "utilisateur";
	public static final String ATTRIBUT_USER_SESSION         = "utilisateurSession";
	public static final String ATTRIBUT_USER_LOGIN         = "userLogin";
	public static final String ATTRIBUT_USER_ID      = "userId";
	public static final String ATTRIBUT_USER_ROLE      = "userRole";
	
	public static final String ATTRIBUT_ARTICLE_ID      = "acheter";
	public static final String ATTRIBUT_ARTICLE_ACHAT      = "acheterArticle";
    public static final String ATTRIBUT_ERREUR_MSG   = "msgErreur";
    
    public static final String VUE_COMMANDE   = "WEB-INF/contenu/vente/ajouterArticle.jsp";

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

		System.out.println("MyServlet Tout article");
		ModelAllContent modelTheme = new ModelAllContent();
		
		List<Theme> themes = metierTheme.lireTousTheme();
		modelTheme.setThemes(themes);
		
		request.setAttribute("modelTheme", modelTheme);
		HttpSession session = request.getSession();
		
		Long user_id = (Long) session.getAttribute(ATTRIBUT_USER_ID);
		System.out.println("userId = " + user_id);
		User userSession = (User) session.getAttribute(ATTRIBUT_USER);
		System.out.println("User session Tout Article = " + userSession);
		
		
		ModelUser modelUser = new ModelUser(); 
		ModelContenu modelContenu = new ModelContenu(); 
		
		List<Article> articles = metierArticle.lireTousArticle();
		modelContenu.setArticles(articles);
		request.setAttribute("modelContenu", modelContenu);
		request.getRequestDispatcher(VUE).forward(request, response); 
			
	
			if(request.getParameter("choixTheme") != null ) {
				System.out.println("condition select theme");
				String theme_id = String.valueOf(request.getParameter("acronymeTheme"));
				
				
				System.out.println("theme =" + theme_id);
				
				ModelContenu modelContenuSelect = new ModelContenu(); 
				
				System.out.println("select Article By Theme");
				
				List<Article> articlesThemes = metierArticle.selectArticleByTheme(theme_id);
				modelContenuSelect.setArticles(articlesThemes);
				request.setAttribute("modelContenuSelect", modelContenuSelect);
			
				
				}
			

			if(request.getParameter("supprimer") != null ) {
			
			
			
			}	
		
	} // Fin DO POST
	
} // Fin de classe

