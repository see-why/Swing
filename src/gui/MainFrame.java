package gui;

import java.awt.BorderLayout;
import javax.swing.JFrame;

public class MainFrame extends JFrame {
	public MainFrame(String name){
		super(name);

		setLayout(new BorderLayout());
		var mainPanel = new MainPanel();
		var toolbar = new ToolBar();
		
		toolbar.setColorChanger(mainPanel);
		add(toolbar, BorderLayout.NORTH);
		add(mainPanel, BorderLayout.CENTER);

		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	private static final long serialVersionUID = 1L;
}
