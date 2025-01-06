package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import models.World;

public class GamePanel extends JPanel {
	private final static long serialVersionUID = 1L;

	private final int CELLSIZE = 20;
	private int leftRightMargin;
	private int topBottomMargin;

	private final static Color BACKGROUNDCOLOR = Color.BLACK;
	private final static Color FOREGROUNDCOLOR = Color.GREEN;
	private final static Color GRIDCOLOR = Color.GRAY;
	
	private World world;
	
	public GamePanel() {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = ((e.getY() - topBottomMargin) / CELLSIZE);
				int column = ((e.getX() - leftRightMargin) / CELLSIZE);
				
				if (row >= world.getRows() || column >= world.getColumns()) {
					return;
				}
				
				world.setCellState(row, column, !world.getCellState(row, column));
				world.next();
				repaint();
			}
			
		});
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		Graphics g2 = (Graphics2D)g;
		
		int width = getWidth();
		int height = getHeight();
		
		leftRightMargin = ((width % CELLSIZE) + CELLSIZE) / 2;
		topBottomMargin = ((height % CELLSIZE) + CELLSIZE) / 2;
		
		int columns = (width -  (2 * leftRightMargin)) / CELLSIZE;
		int rows = (height - (2 * topBottomMargin)) / CELLSIZE;
		
		if (world == null) {
			world = new World(rows, columns);
		}
		
		if (world.getRows() != rows || world.getColumns() != columns) {
			world = new World(rows, columns);
		}

		g2.setColor(BACKGROUNDCOLOR);
		g2.fillRect(0, 0, width, height);
		
		fillGrid(g2, rows, columns);
		drawGrid(g2, width, height);
	}
	
	private void fillGrid(Graphics g2, int rows, int columns) {
		for (int x = 0; x < rows; x++) {
			for (int y = 0; y < columns; y++) {
				fillCell(g2, x, y, world.getCellState(x, y));
			} 
		}
		
	}
	
	private void fillCell(Graphics g2, int row, int col, boolean status) {
		Color color = status ? FOREGROUNDCOLOR : BACKGROUNDCOLOR;
		g2.setColor(color);
		
		int x = leftRightMargin + col * CELLSIZE;
		int y = topBottomMargin + row * CELLSIZE;
		
		g2.fillRect(x, y, CELLSIZE, CELLSIZE);
		
	}
	
	private void drawGrid(Graphics g, int width, int height) {
		g.setColor(GRIDCOLOR);
		
		for (int x = leftRightMargin; x <= width - leftRightMargin; x += CELLSIZE) {
			g.drawLine(x, topBottomMargin, x, height - topBottomMargin);
		}
		
		for (int y = topBottomMargin; y <= height - topBottomMargin; y += CELLSIZE) {
			g.drawLine(leftRightMargin, y, width - leftRightMargin, y);
		}
	}

	public void randomize() {
		world.randomize();
		repaint();
	}

	public void clear() {
		world.clear();
		repaint();
	}

	public void next() {
		world.next();
		repaint();
	}

}