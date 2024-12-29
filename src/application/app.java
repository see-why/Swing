package application;

import gui.MainFrame;
import javax.swing.SwingUtilities;

public class App {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			new MainFrame("Swing Low");
		});
	}
}
