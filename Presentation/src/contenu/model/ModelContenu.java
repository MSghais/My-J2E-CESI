package contenu.model;

import java.util.ArrayList;
import java.util.List;

import contenu.entite.Article;

import utilisateurs.entite.User;




public class ModelContenu {
	private List<Article> articles;

	private List<User> usersLectures;
	

	public ModelContenu() {

	}

	public ModelContenu(List<Article> articles, List<User> usersLectures) {
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

	@Override
	public String toString() {
		return "ModelContenu [articles=" + articles + ", usersLectures="
				+ usersLectures + "]";
	}


	

	
}
