package gui;

import java.awt.Color;

import javax.swing.JPanel;

import gui.interfaces.ColorChangeListener;

public class MainPanel extends JPanel implements ColorChangeListener {
	public MainPanel() {
		super();
		setBackground(Color.green);
	}

	private static final long serialVersionUID = 1L;
	
	public void changeColor(Color color) {
		setBackground(color);
	}
}
