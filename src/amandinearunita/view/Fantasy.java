package amandinearunita.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.*;

import amandinearunita.controller.Controller;

public class Fantasy extends JFrame {

	private Controller controller;
	private JPanel pitchPanel;
	private JPanel panelGoalkeeper;
	private JPanel panelDefender;
	private JPanel panelMidfielder;
	private JPanel panelStriker;
	private JPanel panelBench;
	private List<JPanel> playerPanels;
	private JComboBox cmboFormation;

	/**
	 * Constructor of Fantasy class, creates the frame
	 */
	public Fantasy(){

		super("Fantasy Football");

		setSize(400, 600);
		setLayout(new BorderLayout());

		playerPanels = new ArrayList<JPanel>();

		createPitchPanel();

		createBenchPanel();

		createFormationSelectionBox();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * Removes all Player panels from the frame
	 */
	public void removeAllPlayerPanels(){
		panelGoalkeeper.removeAll();
		panelDefender.removeAll();
		panelMidfielder.removeAll();
		panelStriker.removeAll();
		panelBench.removeAll();
		playerPanels.clear();
	}

	/**
	 * Adds the specified Goalkeeper to the corresponding panel
	 * @param name	Goalkeeper's name
	 * @param id	Goalkeeper's ID
	 * @param image	Path to Goalkeeper's image
	 */
	public void addGoalkeeper(String name, String id, String image){
		panelGoalkeeper.add(createIndividualPlayerPanel(name, id, image));
	}

	/**
	 * Adds the specified Defender to the corresponding panel
	 * @param name	Defender's name
	 * @param id	Defender's ID
	 * @param image	Path to Defender's image
	 */
	public void addDefender(String name, String id, String image) {
		panelDefender.add(createIndividualPlayerPanel(name, id, image));
	}

	/**
	 * Adds the specified Midfielder to the corresponding panel
	 * @param name	Midfielder's name
	 * @param id	Midfielder's ID
	 * @param image	Path to Midfielder's image
	 */
	public void addMidfielder(String name, String id, String image) {
		panelMidfielder.add(createIndividualPlayerPanel(name, id, image));
	}

	/**
	 * Adds the specified Striker to the corresponding panel
	 * @param name	Striker's name
	 * @param id	Striker's ID
	 * @param image	Path to Striker's image
	 */
	public void addStriker(String name, String id, String image) {
		panelStriker.add(createIndividualPlayerPanel(name, id, image));
	}

	/**
	 * Adds the specified Player to the bench panel
	 * @param name	Player's name
	 * @param id	Player's ID
	 * @param image	Path to Player's image
     */
	public void addToBench(String name, String id, String image) {
		panelBench.add(createIndividualPlayerPanel(name, id, image));
	}

	/**
	 * Updates the Player's name given his ID
	 * @param id	Player's ID
	 * @param name	Player's name
	 */
	public void updatePlayerName(String id, String name){
		for(JPanel panel : playerPanels) {
			if(panel.getName().equals(id)) {
				panel.remove(((BorderLayout) panel.getLayout()).getLayoutComponent(BorderLayout.SOUTH));

				panel.add(createPlayerNameTextField(id, name), BorderLayout.SOUTH);
			}
		}
	}

	/**
	 * Updates Player's image
	 * @param playerId	Player's ID
	 * @param path	Path to Player's image
	 */
	public void updatePlayerImage(String playerId, String path) {
		for(JPanel panel : playerPanels) {
			if(panel.getName().equals(playerId)) {
				// Makes impossible to add an image to a Player more than once
				if(!((BorderLayout) panel.getLayout()).getLayoutComponent(BorderLayout.CENTER).getClass().equals(JLabel.class)) {
					panel.remove(((BorderLayout) panel.getLayout()).getLayoutComponent(BorderLayout.CENTER));

					addImageToPanel(playerId, path, panel);
				}
			}
		}
	}

	/**
	 * Getter of the selected formation
	 * @return String team formation position
	 */
	public String getSelectedFormation() {
		return (String) cmboFormation.getSelectedItem();
	}

	/**
	 * Adds an ActionListener to the frame
	 * @param controller	An ActionListener
	 */
	public void addActionListener(Controller controller) {
		this.controller = controller;
		cmboFormation.addActionListener(controller);
	}

	/**
	 * Creates the pitch panel
	 */
	private void createPitchPanel() {
		pitchPanel = new JPanel();
		pitchPanel.setLayout(new GridLayout(4, 1));

		panelGoalkeeper = new JPanel(new FlowLayout());
		panelDefender = new JPanel(new FlowLayout());
		panelMidfielder = new JPanel(new FlowLayout());
		panelStriker = new JPanel(new FlowLayout());

		pitchPanel.add(panelGoalkeeper);
		pitchPanel.add(panelDefender);
		pitchPanel.add(panelMidfielder);
		pitchPanel.add(panelStriker);

		add(pitchPanel, BorderLayout.CENTER);
	}


	/**
	 * Creates the bench panel
	 */
	private void createBenchPanel() {
		panelBench = new JPanel(new FlowLayout());
		add(panelBench, BorderLayout.SOUTH);
	}

	/**
	 * Creates an individual Player panel
	 * @param name	Player's name
	 * @param id	Player's ID
	 * @param imgPath	Path to Player's nam
	 * @return panel for the players name with the text field
	 */
	private JPanel createIndividualPlayerPanel(String name, String id, String imgPath){
		JPanel playerPanel= new JPanel(new BorderLayout());
		playerPanel.setName(id);

		playerPanel.add(createPlayerNameTextField(id, name), BorderLayout.SOUTH);

		if(imgPath.equals("None")) {
			JButton addImage = new JButton("+");
			addImage.setName(id);
			addImage.addActionListener(controller);
			playerPanel.add(addImage, BorderLayout.CENTER);
		}
		else {
			addImageToPanel(id, imgPath, playerPanel);
		}
		playerPanels.add(playerPanel);
		return playerPanel;
	}

	/**
	 * Creates the combo box for the selection of the players positions
	 */
	private void createFormationSelectionBox() {
		cmboFormation = new JComboBox();
		cmboFormation.addItem("Select formation");
		cmboFormation.addItem("4-4-2");
		cmboFormation.addItem("4-3-3");
		cmboFormation.addItem("3-5-2");
		cmboFormation.addItem("5-3-2");
		cmboFormation.addItem("3-4-3");
		cmboFormation.addItem("4-5-1");
		add(cmboFormation, BorderLayout.NORTH);
	}
	
	/**
	 * Adds an image to the specified panel
	 * @param playerId	Player's ID to pass it as the image's name
	 * @param path	Path to Player's image
	 * @param panel	Panel to update
	 */
	private void addImageToPanel(String playerId, String path, JPanel panel) {
		BufferedImage bfImage = null;
		try {
			bfImage = ImageIO.read(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		JLabel lblImage= new JLabel(new ImageIcon(bfImage));
		lblImage.setName(playerId);

		panel.add(lblImage, BorderLayout.CENTER);
	}

	/**
	 * Creates a text field for Player's name
	 * @param id	Player's ID
	 * @param name	PLayer's name
	 * @return the text field where the user can write the name if the player is mentioned here
	 */
	private JTextField createPlayerNameTextField(String id, String name) {
		JTextField playerName = new JTextField(name);
		playerName.setHorizontalAlignment(SwingConstants.CENTER);
		/**
		 * Adding the Action Listener event to the controller 
		 */
		playerName.setName(id);
		playerName.addActionListener(controller);
		return playerName;
	}
}
