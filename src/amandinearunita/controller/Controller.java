package amandinearunita.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashSet;
import java.util.Set;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import amandinearunita.model.*;
import amandinearunita.view.Fantasy;

public class Controller implements ActionListener {

	private Fantasy frame;
	private Squad currentSquad;

	/**
	 * Constructor of Controller class
	 * @param frame	The tracked view
	 * @param currentSquad	The model used
     */
	public Controller(Fantasy frame, Squad currentSquad){

		this.frame = frame;
		this.currentSquad = currentSquad;
		this.currentSquad.generate(2, 5, 5, 3);
	}

	/**
	 * Method to handle dropdown box selections, button press and updates in a text field events
	 * @param e	Action that was performed and triggered the method
     */
	@Override
	public void actionPerformed(ActionEvent e) {

		/**
		 * Actions to perform if the event comes from a text field update
		 * The method takes the text input and change the name of the corresponding Player
		 */
		if (e.getSource().getClass().equals(JTextField.class)){

			JTextField changedField = (JTextField) e.getSource();
			String playerId = changedField.getName();

			currentSquad.searchPlayer(playerId).setName(changedField.getText());
//			This following line is for debugging purposes:
//			System.out.println(currentSquad.searchPlayer(playerId).getName());
		}

		/**
		 * Actions to perform if the event comes for a dropdown box selection
		 * The method analyse the selected formation and instruct the view to create Player panels in consequence
		 */
		if (e.getSource().getClass().equals(JComboBox.class)){

			// First, remove all Player panels
			frame.removeAllPlayerPanels();

			// Analysis of the selected formation
			String selectedFormation = frame.getSelectedFormation();
			int numberOfDefender = 0;
			int numberOfMidfielder = 0;
			int numberOfStriker = 0;

			if(selectedFormation.equals("Select formation"))
				frame.removeAllPlayerPanels();
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

				// Instructions for the view
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

		/**
		 * Action to perform if the event comes from a button press
		 * Open a window to allow the user to choose an image file and update the corresponding Player and the view
		 */
		if (e.getSource().getClass().equals(JButton.class)){
			// Open a window to choose a file
			JFileChooser chooser = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & GIF Images", "jpg", "gif");

			chooser.setFileFilter(filter);

			int returnVal = chooser.showOpenDialog((JButton) e.getSource());

			String playerId = ((JButton) e.getSource()).getName();

			// Update the Player and the view
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
			//For when the new player formation is selected again
			frame.revalidate();
			frame.repaint();
		}
	}
}
