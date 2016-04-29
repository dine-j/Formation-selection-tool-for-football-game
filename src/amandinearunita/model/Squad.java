package amandinearunita.model;

import java.util.HashSet;
import java.util.Set;

public class Squad {

	Set<Player> listOfPlayers;

	/**
	 * Constructor of Squad class, creates a set of players
	 */
	public Squad(){
		listOfPlayers = new HashSet<Player>();
	}

	/**
	 * Searches a Player with his ID
	 * @param id	Player's ID
	 * @return Player	The matching Player
	 */
	public Player searchPlayer(String id) {
		for(Player player : listOfPlayers) {
			if(player.getID().equals(id))
				return player;
		}
		return null;
	}
	
	/**
	 * Generates the number of players according to the player formation selected
	 * @param numberOfGoalkeepers	Number of Goalkeepers
	 * @param numberOfDefenders	Number of Defenders
	 * @param numberOfMidfielders	Number of Midfielders
	 * @param numberOfStrikers	Number of Strikers
	 */
	public void generate(int numberOfGoalkeepers, int numberOfDefenders, int numberOfMidfielders, int numberOfStrikers) {
		for(int i=0; i<numberOfGoalkeepers; i++){
			listOfPlayers.add(new Goalkeeper());
		}

		for(int i=0; i<numberOfDefenders; i++){
			listOfPlayers.add(new Defender());
		}
		for(int i=0; i<numberOfMidfielders; i++){
			listOfPlayers.add(new Midfielder());
		}
		for(int i=0; i<numberOfStrikers; i++){
			listOfPlayers.add(new Striker());
		}
	}
	
	/**
	 * Getter of list of Players
	 * @return the Players list 
	 */
	public Set<Player> getListOfPlayers(){
		
		return listOfPlayers;
	}
}
