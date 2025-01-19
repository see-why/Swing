package gui;

import java.awt.BorderLayout;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private static final GamePanel gamePanel = new GamePanel();

	public MainFrame(String name){
		super(name);

		setLayout(new BorderLayout());
		add(gamePanel, BorderLayout.CENTER);
		JFileChooser fileChooser = new JFileChooser();
		
		MenuItem openItem = new MenuItem("Open");
		openItem.addActionListener(e -> {
			int userChoice = fileChooser.showOpenDialog(MainFrame.this);
			
			if(userChoice == JFileChooser.APPROVE_OPTION) {
				System.out.println("open");
			}
		});
		
		MenuItem saveItem = new MenuItem("Save");
		saveItem.addActionListener(e -> {
			int userChoice = fileChooser.showSaveDialog(MainFrame.this);
			
			if(userChoice == JFileChooser.APPROVE_OPTION) {
				System.out.println("save");
			}
		});
		
		Menu fileMenu = new Menu("File");
		fileMenu.add(openItem);
		fileMenu.add(saveItem);
		
		MenuBar menuBar = new MenuBar();
		menuBar.add(fileMenu);
		
		setMenuBar(menuBar);
		
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int code = e.getKeyCode();
				
				switch (code) {
					case KeyEvent.VK_SPACE -> {
						gamePanel.next();
					}
					case KeyEvent.VK_BACK_SPACE -> {
						gamePanel.clear();
					}
					case KeyEvent.VK_ENTER -> {
						gamePanel.randomize();
					}
				}
			}
			
		});

		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}
