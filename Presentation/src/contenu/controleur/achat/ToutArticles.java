

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
import utilisateurs.entite.User;
import utilisateurs.model.ModelUser;



@WebServlet("/Shopping")
public class ToutArticles extends HttpServlet { 
	//private MetierItf metier = MetierEtudiantPromo.getInstance(); 
	
	@EJB
	MetierInterfaceArticle metierArticle;
	
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
		int nb = 0;
		System.out.println("MyServlet Tout article");
		nb++;
	
		
		
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
			
			
			//request.getRequestDispatcher(VUE).forward(request, response); 
			//request.getAttribute(arg0)
			if(request.getParameter("acheterA") != null ) {
				System.out.println("acheterA capté");
				Long id = Long.valueOf(request.getParameter("acheterA"));
				System.out.println("achat article id=" + id);
				
				
				request.setAttribute(ATTRIBUT_ARTICLE_ID, id);
				session.setAttribute(ATTRIBUT_ARTICLE_ID, id);
				System.out.println("article session id in tout article is =" + session.getAttribute(ATTRIBUT_ARTICLE_ID));
				
			/*
			 * Article articleAchat = metierArticle.rechercherArticleIndex(id);
			 * System.out.println("article Achat = " + articleAchat);
			 * request.setAttribute(ATTRIBUT_ARTICLE_ACHAT, articleAchat);
			 * session.setAttribute(ATTRIBUT_ARTICLE_ACHAT, articleAchat);
			 * 
			 * System.out.println(session.getAttribute(ATTRIBUT_ARTICLE_ACHAT));
			 */
				
				//metierArticle.ajouterArticleAchat(userSession, articleAchat);
				
				
				response.sendRedirect( request.getContextPath() + "/acheterArticle");
				
				request.getRequestDispatcher("/acheterArticle").forward(request, response);
				
				//request.getRequestDispatcher(VUE_COMMANDE).forward(request, response);
				
			//	metierArticle.
				//metier.ajouterRetard(id);
				
			}
			if(request.getParameter("lienAcheterIn") != null ) {
				System.out.println("Lien acheter In : acheter article ID capté");
				Long id = Long.valueOf(request.getParameter("acheter"));
				System.out.println("achat article id=" + id);
				
				
				request.setAttribute(ATTRIBUT_ARTICLE_ID, id);
				session.setAttribute(ATTRIBUT_ARTICLE_ID, id);
				
		
				
				System.out.println("lienIn include ");
				//request.getRequestDispatcher("/acheterArticle").include(request, response);
				request.getRequestDispatcher("/acheterArticle").include(request, response);

					
			}
			if(request.getParameter("acheter") != null ) {
				System.out.println("acheter article ID capté");
				Long id = Long.valueOf(request.getParameter("acheter"));
				System.out.println("achat article id=" + id);
				
				
				request.setAttribute(ATTRIBUT_ARTICLE_ID, id);
				session.setAttribute(ATTRIBUT_ARTICLE_ID, id);
				
			
			/*
			 * System.out.println(" Response getContextPath acheter  Article forward");
			 * response.sendRedirect( request.getContextPath() + "/acheterArticle");
			 */
				System.out.println(" response toutArticle sendRedirect acheter Article");
				response.sendRedirect( request.getContextPath() + "acheterArticle");
				
				
				System.out.println(" Acheter Article forward");
				request.getRequestDispatcher("acheterArticle").forward(request, response);
			//	System.out.println(" Acheter Article include"); 
				//request.getRequestDispatcher("/acheterArticle").include(request, response);
				//request.getRequestDispatcher("/acheterArticle").include(request, response);
				
				//request.getRequestDispatcher(VUE_COMMANDE).forward(request, response);
				
			//	metierArticle.
				//metier.ajouterRetard(id);
				
				
			//request.getRequestDispatcher(VUE_COMMANDE).include(request, response);
			
				//request.getRequestDispatcher("/acheterArticle").include(request, response);
				
				//request.getRequestDispatcher(VUE_COMMANDE).forward(request, response);
				
			//	metierArticle.
				//metier.ajouterRetard(id);
				/*
				 * Article articleAchat = metierArticle.rechercherArticleIndex(id);
				 * System.out.println("article Achat = " + articleAchat);
				 * request.setAttribute(ATTRIBUT_ARTICLE_ACHAT, articleAchat);
				 * session.setAttribute(ATTRIBUT_ARTICLE_ACHAT, articleAchat);
				 * 
				 * System.out.println(session.getAttribute(ATTRIBUT_ARTICLE_ACHAT));
				 */
					
					//metierArticle.ajouterArticleAchat(userSession, articleAchat);
				
			}
			if(request.getParameter("lienAcheter") != null ) {
				System.out.println("lienacheter article");
				Long id = Long.valueOf(request.getParameter("acheter"));
				System.out.println("achat article id=" + id);
				
				
				request.setAttribute(ATTRIBUT_ARTICLE_ID, id);
				session.setAttribute(ATTRIBUT_ARTICLE_ID, id);
				
			/*
			 * Article articleAchat = metierArticle.rechercherArticleIndex(id);
			 * System.out.println("article Achat = " + articleAchat);
			 * request.setAttribute(ATTRIBUT_ARTICLE_ACHAT, articleAchat);
			 * session.setAttribute(ATTRIBUT_ARTICLE_ACHAT, articleAchat);
			 * 
			 * System.out.println(session.getAttribute(ATTRIBUT_ARTICLE_ACHAT));
			 */
				
				//metierArticle.ajouterArticleAchat(userSession, articleAchat);
				
				
				System.out.println("Redirection metier commande vue Acheter Article forward");
				//request.getRequestDispatcher("/acheterArticle").include(request, response);
				
				request.getRequestDispatcher("/acheterArticle").include(request, response);
			
				//request.getRequestDispatcher("/acheterArticle").include(request, response);
				
				//request.getRequestDispatcher(VUE_COMMANDE).forward(request, response);
				
			//	metierArticle.
				//metier.ajouterRetard(id);
				
			}
			
			
			if(request.getParameter("acheterButton") != null ) {
				System.out.println("acheter article ID capté");
				Long id = Long.valueOf(request.getParameter("article_id"));
				System.out.println("achat article id=" + id);
				
				
				request.setAttribute(ATTRIBUT_ARTICLE_ID, id);
				session.setAttribute(ATTRIBUT_ARTICLE_ID, id);
				
				
				System.out.println("Redirection metier commande vue Acheter Article forward");
				request.getRequestDispatcher("/acheterArticle").forward(request, response);
			/*
			 * Article articleAchat = metierArticle.rechercherArticleIndex(id);
			 * System.out.println("article Achat = " + articleAchat);
			 * request.setAttribute(ATTRIBUT_ARTICLE_ACHAT, articleAchat);
			 * session.setAttribute(ATTRIBUT_ARTICLE_ACHAT, articleAchat);
			 * 
			 * System.out.println(session.getAttribute(ATTRIBUT_ARTICLE_ACHAT));
			 */
				
				//metierArticle.ajouterArticleAchat(userSession, articleAchat);
				

				//request.getRequestDispatcher("/acheterArticle").include(request, response);
				
				//request.getRequestDispatcher(VUE_COMMANDE).forward(request, response);
				
			//	metierArticle.
				//metier.ajouterRetard(id);
				
			}
		/*
		 * if(request.getParameter("acheter") != null ) {
		 * 
		 * 
		 * 
		 * }
		 */
			
	
		if(request.getParameter("supprimer") != null ) {
			
			
			
		}
		
	}
}

