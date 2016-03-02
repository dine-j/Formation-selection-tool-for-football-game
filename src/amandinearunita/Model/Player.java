package amandinearunita.Model;

public class Player {

	String name; 
	String id; 
	String path;
	
	public Player(String name, String id, String path){
		this.name = name;
		this.id = id;
		this.path = path;
	}
	public Player(String name, String id){
		this.name = name;
		this.id = id;
		path = "None";
	}
	public Player(){
		name = getClass().getSimpleName();
		id = String.valueOf(this.hashCode());
		path = "None";
	}
	public String getName(){
		return name; 
	}
	
	public String getID() {
		return id;
	}

	public String getImage() {
		return path;
	}
	
	public String toString(){
		return name+" "+id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setImage(String path) {
		this.path = path;
	}
}

