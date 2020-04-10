package contenu.enume;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum Status {

	NOW("En cours"), VAL("Commande Validée"), ENV("Commande Envoyée");
	
	
	
	private final String status;
	 
	Status(String Status ) {
        this.status = Status;
    }
 
    public String getStatus() {
        return status;
    }

    public static Status valueOfLabel(String label) {
        for (Status r : values()) {
            if (r.status.equals(label)) {
                return r;
            }
        }
        return null;
    }
    
    //Lookup table
    private static final Map<Status,String> lookup = new HashMap<>();
    
    public static Map<Status,String> setLookUpValues() {
    	
    	for(Status rub : Status.values()) {
    		
    		lookup.put(rub, rub.getStatus());
    		
    	}
    	
    	return lookup;
    }
    
    
    public static Map<Status, String> getLookup() {
    	return lookup;
    	
    }
  
    //Populate the lookup table on loading time
/*
 * public static Rubriques getValueOf(String value) { Rubriques values = null;
 * for(Rubriques rub : Rubriques.values()) { values = Rubriques.get(value); }
 * return values; }
 */
  
    //This method can be used for reverse lookup purpose
/*
 * public static Rubriques get(String url) { // return lookup.get(url); }
 */
    
  
    
    
    public static Map<String, String> getAllValueString() {
    	
    	Map<String, String> allMap = new HashMap<>();
    for(Status rub : Status.values())
    {
    	allMap.put(rub.name(), rub.getStatus());
        System.out.println(rub.name() + " :: "+ rub.getStatus());
    }
	return allMap;
    }
    
    
    
    
    
public static List<Status> getAllValuesRubrique() {
    	
    	List<Status> allMap = null;
    	String values = null;
    for(Status rub : Status.values())
    {
 
    	allMap.add(rub);
    	
       // System.out.println(rub.name() + " :: "+ rub.getRubrique());
    }
	return allMap;
    }
    
    
public static List<String> getAllStringValList() {
	
	List<String> rubriqueList = null;
	for(Status rub : Status.values() ) {
	 	String rubGetter = rub.getStatus();
	 	
	 	
	rubriqueList.add(rubGetter);
  

	}
	return rubriqueList;
	
}
	
public static Status[] getAllStringVal() {
	
	Status[] rubriqueList = null;
	for(Status rub : Status.values() ) {
	 	String rubGetter = rub.getStatus();
	 	
	 	//rub.get(url);
	 	
	 			rub.getStatus();
	 				System.out.println(rub.getStatus());
	}
	
	return rubriqueList;
}



}
