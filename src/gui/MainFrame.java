package gui;

import java.awt.BorderLayout;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private static final GamePanel gamePanel = new GamePanel();
	private static final String DEFAULT_FILE = "gameOfLife.gol";

	public MainFrame(String name){
		super(name);

		setLayout(new BorderLayout());
		add(gamePanel, BorderLayout.CENTER);
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setAcceptAllFileFilterUsed(false);
		var filter = new FileNameExtensionFilter("Game of Life files", "gol");
		fileChooser.addChoosableFileFilter(filter);
		fileChooser.setFileFilter(filter);
		MenuItem openItem = new MenuItem("Open");
		openItem.addActionListener(e -> {
			fileChooser.setSelectedFile(new File(DEFAULT_FILE));
			int userChoice = fileChooser.showOpenDialog(MainFrame.this);
			
			if(userChoice == JFileChooser.APPROVE_OPTION) {
				var file = fileChooser.getSelectedFile();
				gamePanel.open(file);
				System.out.println(file);
			}
		});
		
		MenuItem saveItem = new MenuItem("Save");
		saveItem.addActionListener(e -> {
			fileChooser.setSelectedFile(new File(DEFAULT_FILE));
			int userChoice = fileChooser.showSaveDialog(MainFrame.this);
			
			if(userChoice == JFileChooser.APPROVE_OPTION) {
				var file = fileChooser.getSelectedFile();
				gamePanel.save(file);
				System.out.println(file);
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
