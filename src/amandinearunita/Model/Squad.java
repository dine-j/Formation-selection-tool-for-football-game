package amandinearunita.Model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Squad {

	Set<Player> listOfPlayers;

	public Squad(){
		listOfPlayers = new HashSet<Player>();
	}

	public Player searchPlayer(String id) {
		for(Player player : listOfPlayers) {
			if(player.getID().equals(id))
				return player;
		}
		return null;
	}

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

	public Set<Player> getListOfPlayers(){
		
		return listOfPlayers;
	}
}
