package amandinearunita;

import amandinearunita.Controller.Controller;
import amandinearunita.Model.Squad;
import amandinearunita.View.Fantasy;

public class Main {

	public static void main(String[] args) {
	
		Fantasy fantasy = new Fantasy();

		Squad squad = new Squad();

		Controller controller = new Controller(fantasy, squad);

		fantasy.setVisible(true);
	}

}
