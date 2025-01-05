package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class GamePanel extends JPanel {
	private final static long serialVersionUID = 1L;
	private final int CELLSIZE = 50;
	private int leftRightMargin;
	private int topBottomMargin;
	private final static Color BACKGROUND_COLOR = Color.BLACK;
	private final static Color FOREGROUND_COLOR = Color.GREEN;
	private final static Color GRID_COLOR = Color.GRAY;
	
	public GamePanel() {
		setBackground(Color.RED);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		Graphics g2 = (Graphics2D)g;
		
		int width = getWidth();
		int height = getHeight();
		
		leftRightMargin = ((width % CELLSIZE) + CELLSIZE) / 2;
		topBottomMargin = ((height % CELLSIZE) + CELLSIZE) / 2;

		g2.setColor(BACKGROUND_COLOR);
		g2.fillRect(0, 0, width, height);
		
		drawGrid(g2, width, height);
		fillCell(g2, 3, 5, true);
	}
	
	private void fillCell(Graphics g2, int row, int col, boolean status) {
		Color color = status ? FOREGROUND_COLOR : BACKGROUND_COLOR;
		g2.setColor(color);
		
		int x = leftRightMargin + col * CELLSIZE;
		int y = topBottomMargin + row * CELLSIZE;
		
		g2.fillRect(x, y, CELLSIZE, CELLSIZE);
		
	}
	
	private void drawGrid(Graphics g, int width, int height) {
		g.setColor(GRID_COLOR);
		
		for (int x = leftRightMargin; x <= width - leftRightMargin; x += CELLSIZE) {
			g.drawLine(x, topBottomMargin, x, height - topBottomMargin);
		}
		
		for (int y = topBottomMargin; y <= height - topBottomMargin; y += CELLSIZE) {
			g.drawLine(leftRightMargin, y, width - leftRightMargin, y);
		}
	}

}