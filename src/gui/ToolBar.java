package gui;

import gui.interfaces.ColorChangeListener;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JToolBar;

public class ToolBar extends JToolBar {
	public ColorChangeListener getColorChanger() {
		return colorChanger;
	}

	public void setColorChanger(ColorChangeListener colorChanger) {
		this.colorChanger = colorChanger;
	}

	private ColorChangeListener colorChanger;

	public ToolBar() {
		var redButton = new JButton("Red");
		var blueButton = new JButton("Blue");

		redButton.addActionListener(e->colorChanger.changeColor(Color.RED));
		blueButton.addActionListener(e->colorChanger.changeColor(Color.BLUE));
		
		add(redButton);
		add(blueButton);
	}

	private static final long serialVersionUID = 1L;
}
