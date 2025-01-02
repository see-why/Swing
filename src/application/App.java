package application;

import gui.MainFrame;
import javax.swing.SwingUtilities;

public class App {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			MainFrame frame = new MainFrame("Swing Low");
			frame.setVisible(true);
		});
	}
}
