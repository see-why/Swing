package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JToolBar;

import gui.interfaces.ColorChangeListener;

class ColorButtonListener implements ActionListener {
	private final ColorChangeListener colorChanger;
	private final Color color;
	
	public ColorButtonListener(ColorChangeListener colorChanger, Color color) {
		this.colorChanger = colorChanger;
		this.color = color;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		colorChanger.changeColor(color);
	}
}


public class ToolBar extends JToolBar {
	public ToolBar(ColorChangeListener colorChanger) {
		var redButton = new JButton("Red");
		var blueButton = new JButton("Blue");
		
		redButton.addActionListener(new ColorButtonListener(colorChanger, Color.RED));
		blueButton.addActionListener(new ColorButtonListener(colorChanger, Color.blue));
		
		add(redButton);
		add(blueButton);
	}

	private static final long serialVersionUID = 1L;
}
