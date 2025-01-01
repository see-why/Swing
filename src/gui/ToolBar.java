package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JToolBar;

import gui.interfaces.ColorChangeListener;

public class ToolBar extends JToolBar {
	public ToolBar(ColorChangeListener colorChanger) {
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
