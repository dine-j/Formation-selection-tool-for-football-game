package amandinearunita.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import amandinearunita.Model.Player;
import amandinearunita.Model.Squad;
import amandinearunita.View.Fantasy;

public class Controller implements ActionListener{

	private JComboBox formationPlayer;
	private Fantasy frame;

	public Controller(JComboBox formationPlayer, Fantasy frame){

		this.formationPlayer = formationPlayer;
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//namePlayer.contains(e.getSource())
		if (e.getSource().getClass().equals(JTextField.class)){
			JTextField changedField = (JTextField) e.getSource();
			Squad currentSquad = frame.getSquad();
			String playerId = changedField.getName();
			currentSquad.searchPlayer(playerId).setName(changedField.getText());
//			System.out.println(currentSquad.searchPlayer(playerId).getName());
		}
		if (e.getSource() == formationPlayer){

			frame.removePlayerPanel();
			
			frame.addGoalkeeper();
			
			String selectedFormation = (String) formationPlayer.getSelectedItem();
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
			frame.repaint();
		}		
		if (e.getSource().getClass().equals(JButton.class)){
			JFileChooser chooser = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & GIF Images", "jpg", "gif");

			chooser.setFileFilter(filter);

			int returnVal = chooser.showOpenDialog((JButton) e.getSource());

			String playerId = ((JButton) e.getSource()).getName();
			Squad currentSquad = frame.getSquad();

			if(returnVal == JFileChooser.APPROVE_OPTION) {
				Player playerToUpdate = currentSquad.searchPlayer(playerId);
				File imageFile = chooser.getSelectedFile();
				String imagePath = imageFile.getPath();
				String imageName = imageFile.getName();

				playerToUpdate.setImage(imagePath);
				playerToUpdate.setName(imageName.substring(0, imageName.lastIndexOf('.')));

				frame.updatePlayerImage(playerId, imagePath);
				frame.updatePlayerName(playerId, imageName);
			}

			frame.revalidate();
		}
	}
}
