package hk.edu.polyu.comp.comp2021.clevis.model;


@SuppressWarnings("ALL")
public class Clevis {
	
	private String id;
	
	public Clevis() {	
	}
	//Creates a new Shape with a randomly defined unique name
	public Clevis(String name){
		this.id = name;
	}


	//To get the name of the shape
	public String getId() {
		return id;
	}

	//To set the name of the shape
	public void setId(String id){
		this.id = id;
	}
}


