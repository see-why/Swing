package models;

public class World {
	private int rows;
	private int columns;
	private boolean[][] grid;
	
	public World(int rows, int columns) {
		this.setRows(rows);
		this.setColumns(columns);
		this.grid = new boolean[rows][columns];
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
	
}
