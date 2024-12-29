package application;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class app {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			JFrame mainframe = new JFrame();
			mainframe.setSize(600, 400);
			mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			mainframe.setVisible(true);
		});
	}

}
