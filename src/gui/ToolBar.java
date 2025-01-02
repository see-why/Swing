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

		class ColorButtonListener implements ActionListener {
			private final Color color;
			
			public ColorButtonListener(Color color) {
				this.color = color;
			}

			@Override
			public void actionPerformed(ActionEvent e) {
				colorChanger.changeColor(color);
			}
		}

		redButton.addActionListener(new ColorButtonListener(Color.RED));
		blueButton.addActionListener(new ColorButtonListener(Color.blue));
		
		add(redButton);
		add(blueButton);
	}

	private static final long serialVersionUID = 1L;
}
