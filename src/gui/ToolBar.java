package gui;

import javax.swing.JButton;
import javax.swing.JToolBar;

public class ToolBar extends JToolBar {
	public ToolBar() {
		add(new JButton("Click"));
		add(new JButton("Submit"));
	}

	private static final long serialVersionUID = 1L;
}
