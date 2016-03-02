package amandinearunita.View;

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

import amandinearunita.Controller.Controller;
import amandinearunita.Model.Defender;
import amandinearunita.Model.Goalkeeper;
import amandinearunita.Model.Midfielder;
import amandinearunita.Model.Player;
import amandinearunita.Model.Striker;

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

	public Fantasy(){

		super("Fantasy Football");

		setSize(400, 600);
		setLayout(new BorderLayout());

		playerPanels = new ArrayList<JPanel>();

		createPitchPanel();

		panelBench = new JPanel(new FlowLayout());
		add(panelBench, BorderLayout.SOUTH);

		cmboFormation = new JComboBox();
		cmboFormation.addItem("Select formation");
		cmboFormation.addItem("4-4-2");
		cmboFormation.addItem("4-3-3");
		cmboFormation.addItem("3-5-2");
		cmboFormation.addItem("5-3-2");
		cmboFormation.addItem("3-4-3");
		cmboFormation.addItem("4-5-1");

		add(cmboFormation, BorderLayout.NORTH);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

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

	private JPanel createIndividualPlayerPanel(String name, String id, String imgPath){
		JPanel playerPanel= new JPanel(new BorderLayout());
		playerPanel.setName(id);

		JTextField playerName = new JTextField(name);
		playerName.setName(id);
		playerName.addActionListener(controller);

		playerPanel.add(playerName, BorderLayout.SOUTH);

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
		return playerPanel; //specify the image file
	}

	public void updatePlayerName(String id, String name){
		for(JPanel panel : playerPanels) {
			if(panel.getName().equals(id)) {
				panel.remove(((BorderLayout) panel.getLayout()).getLayoutComponent(BorderLayout.SOUTH));

				JTextField playerName = new JTextField(name);
				playerName.setHorizontalAlignment(SwingConstants.CENTER);

				playerName.setName(id);
				playerName.addActionListener(controller);

				panel.add(playerName, BorderLayout.SOUTH);
			}
		}
	}

	public void updatePlayerImage(String playerId, String path) {
		for(JPanel panel : playerPanels) {
			if(panel.getName().equals(playerId)) {
				panel.remove(((BorderLayout) panel.getLayout()).getLayoutComponent(BorderLayout.CENTER));

				addImageToPanel(playerId, path, panel);
			}
		}
	}

	public void removePlayerPanel(){
		panelGoalkeeper.removeAll();
		panelDefender.removeAll();
		panelMidfielder.removeAll();
		panelStriker.removeAll();
		panelBench.removeAll();
		playerPanels.clear();
	}

	public void addGoalkeeper(String name, String id, String image){
		panelGoalkeeper.add(createIndividualPlayerPanel(name, id, image));
/*		List<Player> goalkeepers = new ArrayList<Player>();

		for(Player player : controller.getListOfPlayers()) {
			if(player instanceof Goalkeeper) {
				goalkeepers.add(player);
			}
		}

		Player firstGK = goalkeepers.get(0);
		Player benchGK = goalkeepers.get(1);

		panelGoalkeeper.add(createIndividualPlayerPanel(firstGK.getName(), firstGK.getID(), firstGK.getImage()));
		panelBench.add(createIndividualPlayerPanel(benchGK.getName(), benchGK.getID(), benchGK.getImage()));*/
	}

	public void addDefender(int numberOfDefender) {
/*		List<Player> defenders = new ArrayList<Player>();

		for(Player player : controller.getListOfPlayers()) {
			if(player instanceof Defender) {
				defenders.add(player);
			}
		}

		for(int i = 0; i < numberOfDefender; ++i) {
			Player defender = defenders.get(0);
			panelDefender.add(createIndividualPlayerPanel(defender.getName(), defender.getID(), defender.getImage()));
			defenders.remove(defender);
		}

		for(int i = 0; i < defenders.size(); ++i) {
			Player defender = defenders.get(0);
			panelBench.add(createIndividualPlayerPanel(defender.getName(), defender.getID(), defender.getImage()));
		}*/
	}

	public void addMidfielder(int numberOfMidfielder) {
		List<Player> midfielders = new ArrayList<Player>();
		for(Player player : controller.getListOfPlayers()) {
			if(player instanceof Midfielder) {
				midfielders.add(player);
			}
		}

		for(int i = 0; i < numberOfMidfielder; ++i) {
			Player midfielder = midfielders.get(0);

			panelMidfielder.add(createIndividualPlayerPanel(midfielder.getName(), midfielder.getID(), midfielder.getImage()));
			midfielders.remove(midfielder);
		}

		for(int i = 0; i < midfielders.size(); ++i) {
			Player midfielder = midfielders.get(0);
			panelBench.add(createIndividualPlayerPanel(midfielder.getName(), midfielder.getID(), midfielder.getImage()));
		}
	}

	public void addStriker(int numberOfStriker) {
		List<Player> strikers = new ArrayList<Player>();
		for(Player player : controller.getListOfPlayers()) {
			if(player instanceof Striker) {
				strikers.add(player);
			}
		}

		for(int i = 0; i < numberOfStriker; ++i) {
			Player striker= strikers.get(0);
			panelStriker.add(createIndividualPlayerPanel(striker.getName(), striker.getID(), striker.getImage()));
			strikers.remove(striker);
		}

		for(int i = 0; i < strikers.size(); ++i) {
			Player striker = strikers.get(0);
			panelBench.add(createIndividualPlayerPanel(striker.getName(), striker.getID(), striker.getImage()));
		}
	}

	public String getSelectedFormation() {
		return (String) cmboFormation.getSelectedItem();
	}

	public void addActionListener(Controller controller) {
		this.controller = controller;
		cmboFormation.addActionListener(controller);
	}

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
}
