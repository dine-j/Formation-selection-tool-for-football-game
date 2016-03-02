package amandinearunita.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import amandinearunita.Model.Player;
import amandinearunita.Model.Squad;
import amandinearunita.View.Fantasy;

public class Controller implements ActionListener {

	private Fantasy frame;
	private Squad currentSquad;

	public Controller(Fantasy frame, Squad currentSquad){

		this.frame = frame;
		this.currentSquad = currentSquad;
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
			
			frame.addGoalkeeper();
			
			String selectedFormation = frame.getSelectedFOrmation();
			int numberOfDefender = 0;
			int numberOfMidfielder = 0;
			int numberOfStriker = 0;

			if(selectedFormation.equals("Select formation"))
				frame.removePlayerPanel();
			else {
				numberOfDefender = Integer.parseInt(Character.toString(selectedFormation.charAt(0)));
				numberOfMidfielder = Integer.parseInt(Character.toString(selectedFormation.charAt(2)));
				numberOfStriker = Integer.parseInt(Character.toString(selectedFormation.charAt(4)));


				frame.addDefender(numberOfDefender);
				frame.addMidfielder(numberOfMidfielder);
				frame.addStriker(numberOfStriker);
			}

			frame.revalidate();
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
		}
	}

	public List<Player> getListOfPlayers() {
		return currentSquad.getListOfPlayers();
	}
}
