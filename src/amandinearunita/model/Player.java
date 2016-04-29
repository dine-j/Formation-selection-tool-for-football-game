package amandinearunita.model;

public class Player {

	protected String name;
	protected String id;
	protected String path;

	/**
	 * Constructor of Player class with parameters
	 * @param name	Player's name
	 * @param id	Player's ID
	 * @param path	Path to Player's image
	 */
	public Player(String name, String id, String path) {
		this.name = name;
		this.id = id;
		this.path = path;
	}
	//making the player when it has to be called by its id 
	/**
	 * Constructor of Player class with name and ID but without image
	 * @param name
	 * @param id
	 */
	public Player(String name, String id) {
		this.name = name;
		this.id = id;
		path = "None";
	}
	
	/**
	 * Constructor of Player class without parameters
	 */
	public Player() {
		name = getClass().getSimpleName();
		id = String.valueOf(this.hashCode());
		path = "None";
	}

	/**
	 * Getter of Player's name
	 * @return	Player's name
     */
	public String getName(){
		return name; 
	}

	/**
	 * Getter of Player's ID
	 * @return	Player's ID
     */
	public String getID() {
		return id;
	}

	/**
	 * Getter of Player's image path
	 * @return	Player's image path
     */
	public String getImage() {
		return path;
	}

	/**
	 * toString method for Player class
	 * @return	A String representation of the Player with its name and id
     */
	public String toString(){
		return name + " " + id;
	}

	/**
	 * Setter of Player's name
	 * @param name	A new name for the Player
     */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Setter of Player's image
	 * @param path	Path to Player's image
     */
	public void setImage(String path) {
		this.path = path;
	}
}

