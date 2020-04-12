package interaction;

import java.io.IOException;
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



@WebServlet("/vueArticle")
public class VueArticle extends HttpServlet { 
	
	
	@EJB 
	MetierInterfaceArticle metier;
	
	
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
		System.out.println("MyServlet doPost");
		String urlVue = "WEB-INF/contenu/vueArticle.jsp";
		request.getRequestDispatcher(urlVue).forward(request, response); 
		
		ModelContenu model = new ModelContenu(); 
		
		   HttpSession sessionServlet = request.getSession();
		   
		   
		  // if( request.getParameter(arg0) != null )
		if(request.getParameter("creationArticle") != null){
			
			
			if(request.getParameter("acronymeTheme") != null){
				  String theme = request.getParameter("theme");
			        
			}
			
			 String theme = request.getParameter("theme");

	      
	        String nom = request.getParameter("art_titre");
	        String url_link = request.getParameter("art_url");
	        String description = request.getParameter("art_description");
	        String contenu = request.getParameter("art_contenu");
	        //String contenu = request.getParameter("contenu");
	    
	        HttpSession sessionControl = request.getSession();
	   
	        sessionControl.setAttribute("theme", theme);
	        
	        
	        sessionControl.setAttribute("url_link", url_link);
	        
	        sessionControl.setAttribute("nom", nom);
	        sessionControl.setAttribute("description", description);
	        sessionControl.setAttribute("contenu", contenu);
	        
	        
	        Article articleNoLInk = new Article(nom, description, contenu);
	        
	        
	        
	        Article articleLInk = new Article(nom, description, contenu, url_link); 
			
	        metier.persisterArticle(articleNoLInk);
	        
	        metier.persisterArticle(articleLInk);
	        //this.getServletContext().getRequestDispatcher("/WEB-INF/vueInscription.jsp").forward(request, response);
		
	        request.getRequestDispatcher(urlVue).forward(request, response); 
	       

	       // this.getServletContext().getRequestDispatcher("/WEB-INF/vueInscription.jsp").forward(request, response);
	       /* if(password == password2) {
	        session.setAttribute("username", username);
	        session.setAttribute("login", login);
	        
	        session.setAttribute("password", password);
	        session.setAttribute("email", email);
	        
	        User user = new User(username, login, email, password);
	        metier.persisterUser(user);
	        
	        //
		
	        request.getRequestDispatcher(urlVue).forward(request, response); */
	       
		
		
		}
	        
	        
	        //request.getRequestDispatcher(urlVue).forward(request, response); 
		
	        //this.getServletContext().getRequestDispatcher("/WEB-INF/vueConnection.jsp").forward(request, response);
			
	        /*
	        List<User> users = metier.lireTousUser();
			List<Teacher> teachers = metier.lireTousTeacher();

			model.setUsers(users);
			model.setTeachers(teachers);
		
			request.setAttribute("model", model);
			request.getRequestDispatcher(urlVue).forward(request, response); */
		}
		
	

	}

