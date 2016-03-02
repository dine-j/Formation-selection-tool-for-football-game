package amandinearunita.View;

import java.awt.BorderLayout;
import java.awt.Component;
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
import amandinearunita.Model.Squad;
import amandinearunita.Model.Striker;

public class Fantasy extends JFrame {

	private Controller controller;
	private JPanel playingPanel;
	private Squad squad;
	private JPanel panelGoalkeeper;
	private JPanel panelDefender;
	private JPanel panelMidfielder;
	private JPanel panelStriker;
	private JPanel panelBench;

	public Fantasy(){

		super("Fantasy Football");

		squad = new Squad();

		setSize(400, 600);
		setLayout(new BorderLayout());

		playingPanel = new JPanel();
		playingPanel.setLayout(new GridLayout(4, 1));

		panelGoalkeeper = new JPanel(new FlowLayout());
		panelDefender = new JPanel(new FlowLayout());
		panelMidfielder = new JPanel(new FlowLayout());
		panelStriker = new JPanel(new FlowLayout());
		panelBench = new JPanel(new FlowLayout());

		playingPanel.add(panelGoalkeeper);
		playingPanel.add(panelDefender);
		playingPanel.add(panelMidfielder);
		playingPanel.add(panelStriker);

		add(playingPanel, BorderLayout.CENTER);
		add(panelBench, BorderLayout.SOUTH);

		JComboBox cmboFormation= new JComboBox();
		cmboFormation.addItem("Select formation");
		cmboFormation.addItem("4-4-2");
		cmboFormation.addItem("4-3-3");
		cmboFormation.addItem("3-5-2");
		cmboFormation.addItem("5-3-2");
		cmboFormation.addItem("3-4-3");
		cmboFormation.addItem("4-5-1");

		add(cmboFormation, BorderLayout.NORTH);
		controller = new Controller(cmboFormation, this);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cmboFormation.addActionListener(controller);
	}

	private JPanel createPlayerPanel(String name, String id, String imgPath){
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
			BufferedImage bfImage = null;
			try {
				bfImage= ImageIO.read(new File(imgPath));
			} catch (IOException e) {
				e.printStackTrace();
			}
			JLabel lblImage= new JLabel(new ImageIcon(bfImage));
			lblImage.setName(id);
			playerPanel.add(lblImage, BorderLayout.CENTER);
		}
		return playerPanel; //specify the image file
	}

	public void updatePlayerName(String id, String Name){
/*		JPanel panelUpdate = idToPanel.get(id);
		Component[] components = panelUpdate.getComponents();
		for(Component component: components){
			if(component.getClass().equals(JTextField.class)){
				((JTextField)component).setText(Name);
			}
		}*/

	}

/*	public void updatePlayerImage(String id, String imgPath){
//		JPanel panelUpdate= idToPanel.get(id);
		BufferedImage bfImage = null;
		try {
			bfImage= ImageIO.read(new File(imgPath));// updating the image using the panel commands
		} catch (IOException e) {
			e.printStackTrace();
		}
		JLabel lblImage= new JLabel(new ImageIcon(bfImage));//add or remove?
		panelUpdate.add(lblImage, BorderLayout.CENTER);
	}*/

	public void removePlayerPanel(){
		panelGoalkeeper.removeAll();
		panelDefender.removeAll();
		panelMidfielder.removeAll();
		panelStriker.removeAll();
		panelBench.removeAll();
	}

	public void addGoalkeeper(){
		List<Player> goalkeepers = new ArrayList<Player>();

		for(Player player : squad.getListOfPlayers()) {
			if(player instanceof Goalkeeper) {
				goalkeepers.add(player);
			}
		}

		Player firstGK = goalkeepers.get(0);
		Player benchGK = goalkeepers.get(1);

		panelGoalkeeper.add(createPlayerPanel(firstGK.getName(), firstGK.getID(), firstGK.getImage()));
		panelBench.add(createPlayerPanel(benchGK.getName(), benchGK.getID(), benchGK.getImage()));
	}

	public void addDefender(int numberOfDefender) {
		List<Player> defenders = new ArrayList<Player>();

		for(Player player : squad.getListOfPlayers()) {
			if(player instanceof Defender) {
				defenders.add(player);
			}
		}

		for(int i = 0; i < numberOfDefender; ++i) {
			Player defender = defenders.get(0);
			panelDefender.add(createPlayerPanel(defender.getName(), defender.getID(), defender.getImage()));
			defenders.remove(defender);
		}

		for(int i = 0; i < defenders.size(); ++i) {
			Player defender = defenders.get(0);
			panelBench.add(createPlayerPanel(defender.getName(), defender.getID(), defender.getImage()));
		}
	}

	public void addMidfielder(int numberOfMidfielder) {
		List<Player> midfielders = new ArrayList<Player>();
		for(Player player : squad.getListOfPlayers()) {
			if(player instanceof Midfielder) {
				midfielders.add(player);
			}
		}

		for(int i = 0; i < numberOfMidfielder; ++i) {
			Player midfielder = midfielders.get(0);

			panelMidfielder.add(createPlayerPanel(midfielder.getName(), midfielder.getID(), midfielder.getImage()));
			midfielders.remove(midfielder);
		}

		for(int i = 0; i < midfielders.size(); ++i) {
			Player midfielder = midfielders.get(0);
			panelBench.add(createPlayerPanel(midfielder.getName(), midfielder.getID(), midfielder.getImage()));
		}
	}

	public void addStriker(int numberOfStriker) {
		List<Player> strikers = new ArrayList<Player>();
		for(Player player : squad.getListOfPlayers()) {
			if(player instanceof Striker) {
				strikers.add(player);
			}
		}

		for(int i = 0; i < numberOfStriker; ++i) {
			Player striker= strikers.get(0);
			panelStriker.add(createPlayerPanel(striker.getName(), striker.getID(), striker.getImage()));
			strikers.remove(striker);
		}

		for(int i = 0; i < strikers.size(); ++i) {
			Player striker = strikers.get(0);
			panelBench.add(createPlayerPanel(striker.getName(), striker.getID(), striker.getImage()));
		}
	}

	public Squad getSquad() {
		return squad;
	}
}
