package gui;

import gui.interfaces.ColorChangeListener;
import java.awt.Color;
import javax.swing.JPanel;

public class MainPanel extends JPanel implements ColorChangeListener {
	public MainPanel() {
		super();
		setBackground(Color.green);
	}

	private static final long serialVersionUID = 1L;
	
    @Override
	public void changeColor(Color color) {
		setBackground(color);
	}
}
