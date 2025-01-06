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
				
				switch (code) {
					case KeyEvent.VK_SPACE -> {
						System.out.println("space bar");
						gamePanel.next();
					}
					case KeyEvent.VK_BACK_SPACE -> {
						System.out.println("back space");
						gamePanel.clear();
					}
					case KeyEvent.VK_ENTER -> {
						System.out.println("enter");
						gamePanel.randomize();
					}
				}
			}
			
		});

		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	private static final long serialVersionUID = 1L;
}
