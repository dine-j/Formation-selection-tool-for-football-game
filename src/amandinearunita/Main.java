package amandinearunita;

import amandinearunita.controller.Controller;
import amandinearunita.model.Squad;
import amandinearunita.view.Fantasy;

public class Main {

	public static void main(String[] args) {
	
		Fantasy fantasy = new Fantasy();

		Squad squad = new Squad();

		Controller controller = new Controller(fantasy, squad);
		fantasy.addActionListener(controller);

		fantasy.setVisible(true);
	}
}
