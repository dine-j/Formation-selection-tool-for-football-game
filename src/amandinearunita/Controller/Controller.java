package amandinearunita.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import amandinearunita.Model.*;
import amandinearunita.View.Fantasy;

public class Controller implements ActionListener {

	private Fantasy frame;
	private Squad currentSquad;

	public Controller(Fantasy frame, Squad currentSquad){

		this.frame = frame;
		this.currentSquad = currentSquad;
		this.currentSquad.generate(2, 5, 5, 3);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource().getClass().equals(JTextField.class)){

			JTextField changedField = (JTextField) e.getSource();
			String playerId = changedField.getName();

			currentSquad.searchPlayer(playerId).setName(changedField.getText());
//			System.out.println(currentSquad.searchPlayer(playerId).getName());
		}

		if (e.getSource().getClass().equals(JComboBox.class)){

			frame.removePlayerPanel();
			
			String selectedFormation = frame.getSelectedFormation();
			int numberOfDefender = 0;
			int numberOfMidfielder = 0;
			int numberOfStriker = 0;

			if(selectedFormation.equals("Select formation"))
				frame.removePlayerPanel();
			else {
				numberOfDefender = Integer.parseInt(Character.toString(selectedFormation.charAt(0)));
				numberOfMidfielder = Integer.parseInt(Character.toString(selectedFormation.charAt(2)));
				numberOfStriker = Integer.parseInt(Character.toString(selectedFormation.charAt(4)));

				Set<Player> listOfPlayers = currentSquad.getListOfPlayers();
				Set<Player> availablePlayer = new HashSet<Player>(listOfPlayers);
				Set<Goalkeeper> goalkeepers = new HashSet<Goalkeeper>();
				Set<Defender> defenders = new HashSet<Defender>();
				Set<Midfielder> midfielders = new HashSet<Midfielder>();
				Set<Striker> strikers = new HashSet<Striker>();

				for(Player player : listOfPlayers) {
					if(player instanceof Goalkeeper && goalkeepers.size() < 1) {
						goalkeepers.add((Goalkeeper) player);
						availablePlayer.remove(player);
					}
					if(player instanceof Defender && defenders.size() < numberOfDefender) {
						defenders.add((Defender) player);
						availablePlayer.remove(player);
					}
					if(player instanceof Midfielder && midfielders.size() < numberOfMidfielder) {
						midfielders.add((Midfielder) player);
						availablePlayer.remove(player);
					}
					if(player instanceof Striker && strikers.size() < numberOfStriker) {
						strikers.add((Striker) player);
						availablePlayer.remove(player);
					}
				}

				for(Goalkeeper goalkeeper : goalkeepers) {
					frame.addGoalkeeper(goalkeeper.getName(), goalkeeper.getID(), goalkeeper.getImage());
				}
				for(Defender defender : defenders) {
					frame.addDefender(defender.getName(), defender.getID(), defender.getImage());
				}
				for(Midfielder midfielder : midfielders) {
					frame.addMidfielder(midfielder.getName(), midfielder.getID(), midfielder.getImage());
				}
				for(Striker striker : strikers) {
					frame.addStriker(striker.getName(), striker.getID(), striker.getImage());
				}
				for(Player player : availablePlayer) {
					frame.addToBench(player.getName(), player.getID(), player.getImage());
				}
			}

			frame.revalidate();
			frame.repaint();
		}

		if (e.getSource().getClass().equals(JButton.class)){
			JFileChooser chooser = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & GIF Images", "jpg", "gif");

			chooser.setFileFilter(filter);

			int returnVal = chooser.showOpenDialog((JButton) e.getSource());

			String playerId = ((JButton) e.getSource()).getName();

			if(returnVal == JFileChooser.APPROVE_OPTION) {

				Player playerToUpdate = currentSquad.searchPlayer(playerId);
				File imageFile = chooser.getSelectedFile();

				String imagePath = imageFile.getPath();
				String imageName = imageFile.getName();
				String playerName = imageName.substring(0, imageName.lastIndexOf('.'));
				playerName = playerName.substring(0, 1).toUpperCase() + playerName.substring(1);

				playerToUpdate.setImage(imagePath);
				playerToUpdate.setName(playerName);

				frame.updatePlayerImage(playerId, imagePath);
				frame.updatePlayerName(playerId, playerName);
			}

			frame.revalidate();
			frame.repaint();
		}
	}

	public Set<Player> getListOfPlayers() {
		return currentSquad.getListOfPlayers();
	}
}
