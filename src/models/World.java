package models;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Random;

public class World implements Serializable {
	private int rows;
	private int columns;
	private final boolean[][] grid;
	private boolean[][] gridBuffer;
	
	@Override
	public String toString() {
		StringBuilder gridString = new StringBuilder();
		for (boolean[] row : grid) {
			gridString.append(Arrays.toString(row)).append("\n");
		}

		StringBuilder gridBufferString = new StringBuilder();
		for (boolean[] row : gridBuffer) {
			gridBufferString.append(Arrays.toString(row)).append("\n");
		}
		return "World [rows=" + rows + ", columns=" + columns + ", grid=" + gridString + ", gridBuffer="
				+ gridBufferString + "]";
	}

	public World(int rows, int columns) {
		this.rows =rows;
		this.columns = columns;
		this.grid = new boolean[rows][columns];
		this.gridBuffer = new boolean[rows][columns];
	}
	
	public void setCellState(int row, int column, boolean state) {
		grid[row][column] = state;
	}
	
	public boolean getCellState(int row, int column) {
		return grid[row][column];
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getColumns() {
		return columns;
	}

	public void setColumns(int columns) {
		this.columns = columns;
	}

	public void randomize() {
		Random ramdom = new Random();
		
		for (int i = 0; i < (rows * columns) / 10; i++) {
			int ramdomRow = ramdom.nextInt(rows);
			int ramdomColumn = ramdom.nextInt(columns);
			
			setCellState(ramdomRow, ramdomColumn, true);
		}
		
	}

	public void clear() {
		for (int i = 0; i < rows; i++) {
			Arrays.fill(grid[i], false);
		}
	}
	
	public int countNeighbours(int row, int column) {
		int neighbours = 0;
		for (int rowOffset = -1; rowOffset < 2; rowOffset++) {
			for (int columnOffset = -1; columnOffset < 2; columnOffset++) {
				int rowNeighbour = row + rowOffset;
				int columnNeighbour = column + columnOffset;
				
				if (rowNeighbour == 0 && columnNeighbour == 0) {
					continue;
				}
				
				if (rowNeighbour == rows && columnNeighbour == columns) {
					continue;
				}
				
				if (rowNeighbour < 0 || rowNeighbour >= rows) {
					continue;
				}
				
				if (columnNeighbour < 0 || columnNeighbour >= columns) {
					continue;
				}
				
				if (getCellState(rowNeighbour, columnNeighbour)) {
					neighbours++;
				}
				
			}
		}
		return neighbours;
		
	}
	public void next() {
		gridBuffer = grid;
		for (int row = 0; row < rows; row++) {
			for (int column = 0; column < columns; column++) {
				int neighbours = countNeighbours(row, column);

				/*
				 * if neighbors count < 2, deactivate cell
				 * if neighbors count > 3, deactivate cell
				 * if neighbors count == 3, activate cell or do nothing
				 */
				
				if (neighbours == 2) {
					continue;
				}
				boolean status = false;
				
				if (neighbours == 3) {
					status = true;
				}
				
				gridBuffer[row][column] = status;
			}
 		}
		
		for (int row = 0; row < rows; row++) {
			for (int column = 0; column < columns; column++) {
				grid[row][column] = gridBuffer[row][column];
			}
		}
	}
	
}
