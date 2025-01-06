package gui;

import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

public class MainFrame extends JFrame {
	private static final GamePanel gamePanel = new GamePanel();

	public MainFrame(String name){
		super(name);

		setLayout(new BorderLayout());
		add(gamePanel, BorderLayout.CENTER);
		
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int code = e.getKeyCode();
				
				switch(code) {
				case 32:
					System.out.println("space bar");
					gamePanel.next();
					break;
				case 8:
					System.out.println("back space");
					gamePanel.clear();
					break;
				case 10:
					System.out.println("enter");
					gamePanel.randomize();
					break;
				}
			}
			
		});

		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	private static final long serialVersionUID = 1L;
}
