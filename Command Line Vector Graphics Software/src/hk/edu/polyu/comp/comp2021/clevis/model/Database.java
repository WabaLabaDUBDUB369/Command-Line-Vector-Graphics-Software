package hk.edu.polyu.comp.comp2021.clevis.model;

import java.util.ArrayList;
import java.util.NoSuchElementException;

@SuppressWarnings("ALL")
public class Database extends ArrayList<Clevis>{
	
	//A String containing all the information regarding all the instances in the database
	@Override
	public String toString() {
		StringBuilder text = new StringBuilder();
		for (int i = 0; i < size(); i++){
			text.append(get(i).toString()).append("\n\n");
		}
		return text.toString();
	}

	
	//Changes the name of a shape
	public void changeID(String oldID, String newID){
		int count = 0;
		for (Clevis item:this) {
			if(item.getId().equals(oldID)){
				item.setId(newID);
				count++;
				break;
			}
		}
		if (count == 0)
			throw new NoSuchElementException();
	}

	
	//Inserts a Shape into the database
	public void insert(Clevis s){
		this.add(s);
	}


	//Gives us the shape or a group shape with its name
	public Clevis get(String id){
		for (Clevis item:this) {
			if(item.getId().equals(id))
				return item;
		}
		throw new NoSuchElementException();
	}
}
