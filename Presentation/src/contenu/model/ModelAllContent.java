package contenu.model;

import java.util.ArrayList;
import java.util.List;

import contenu.entite.Article;
import contenu.entite.Theme;
import utilisateurs.entite.User;




public class ModelAllContent {
	private List<Article> articles;

	private List<User> usersLectures;

	private List<Theme> themes;

	public ModelAllContent() {

	}


	public ModelAllContent(List<Article> articles, List<User> usersLectures
			) {
		super();
		this.articles = articles;

		this.usersLectures = usersLectures;
	
	}




	public List<Article> getArticles() {
		return articles;
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

	
	public List<Theme> getThemes() {
		return themes;
	}


	public void setThemes(List<Theme> themes) {
		this.themes = themes;
	}


	@Override
	public String toString() {
		return "ModelContenu [articles=" + articles + ", usersLectures="
				+ usersLectures + "]";
	}


	
	
}
