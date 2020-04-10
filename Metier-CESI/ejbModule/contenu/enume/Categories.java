package contenu.enume;

public enum Categories {

	ADMINISTRATION, VISITEUR, TEACHER, LEARNER;
	
	public String action() {
		switch(this) {
			case ADMINISTRATION : return "cinema";
			case VISITEUR : return "dormir";
			default : return "travailler";
		}
 	}
	
	
	
}
