package amandinearunita.Model;
import amandinearunita.Model.Player;

import java.util.ArrayList;
import java.util.List;

public class Squad {

	List<Player> listOfPlayers;

	public Squad(){
		listOfPlayers= new ArrayList<Player>();

		for(int i=0; i<2; i++){
			listOfPlayers.add(new Goalkeeper());
		}
		
		for(int i=0; i<5; i++){
			listOfPlayers.add(new Defender());
		}
		for(int i=0; i<5; i++){
			listOfPlayers.add(new Midfielder());
		}
		for(int i=0; i<3; i++){
			listOfPlayers.add(new Striker());
		}
	}

	public Player searchPlayer(String id) {
		for(Player player : listOfPlayers) {
			if(player.getID().equals(id))
				return player;
		}
		return null;
	}

	public List<Player> getListOfPlayers(){
		
		return listOfPlayers;
	}
}
