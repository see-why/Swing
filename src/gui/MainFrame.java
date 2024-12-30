package gui;

import java.awt.BorderLayout;
import javax.swing.JFrame;

public class MainFrame extends JFrame {
	public MainFrame(String name){
		super(name);

		setLayout(new BorderLayout());
		add(new ToolBar(), BorderLayout.NORTH);
		add(new MainPanel(), BorderLayout.CENTER);

		setSize(600, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	private static final long serialVersionUID = 1L;
}
