package contenu.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import contenu.entite.Article;
import contenu.entite.Theme;
import interaction.entite.Commande;
import utilisateurs.entite.User;




public class ModelAllContent {
	private List<Article> articles;
	
	private List<Commande> commandes;
	
	private Article articleAchat;
	
	private List<User> usersLectures;
	
	private List<Theme> themes;

	private Map<Long,Article> articlesMap;
	
	public ModelAllContent() {

	}
	
	public ModelAllContent(List<Article> articles, Article article, List<User> usersLectures,
			Map<Long, Article> articlesMap) {
		super();
		this.articles = articles;
		this.articleAchat = article;
		this.usersLectures = usersLectures;
		this.articlesMap = articlesMap;
	}

	public ModelAllContent(List<Article> articles, List<User> usersLectures, HashMap<Long,Article> articlesMap) {
		super();
		this.articles = new ArrayList<Article>();

		this.usersLectures =  new ArrayList<User>();	
		this.articlesMap = new HashMap<Long, Article>();
		
		this.themes = new ArrayList<Theme>();	
		this.commandes = new ArrayList<Commande>();
		
	}

	public ModelAllContent(List<Article> articles, List<User> usersLectures) {
		super();
		this.articles = articles;

		this.usersLectures = usersLectures;
	}



	public Article getArticleAchat() {
		return articleAchat;
	}

	public void setArticleAchat(Article articleAchat) {
		this.articleAchat = articleAchat;
	}

	public List<Theme> getThemes() {
		return themes;
	}

	public void setThemes(List<Theme> themes) {
		this.themes = themes;
	}

	public Article getArticle() {
		return articleAchat;
	}

	public void setArticle(Article article) {
		this.articleAchat = article;
	}

	public List<Article> getArticles() {
		return articles;
	}
	
	public void addArticle(Article article) {
		
		articles.add(article);
	}


	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}



	public List<User> getUsersLectures() {
		return usersLectures;
	}


	public void setUsersLectures(List<User> usersLectures) {
		this.usersLectures = usersLectures;
	}

	@Override
	public String toString() {
		return "ModelContenu [articles=" + articles + ", usersLectures="
				+ usersLectures + "]";
	}

	public Map<Long, Article> getArticlesMap() {
		return articlesMap;
	}

	public void setArticlesMap(Map<Long, Article> articlesMap) {
		this.articlesMap = articlesMap;
	}

	public void addArticleMap(User user,Article article) {
		
		articlesMap.put(user.getUser_id(), article);
	
	}
	

	
}
