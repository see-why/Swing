# Game of Life

A Java Swing implementation of Conway's Game of Life cellular automaton.

## Description

This project implements the classic Game of Life with a graphical user interface using Java Swing. The game simulates cellular evolution based on simple rules where cells live or die depending on their neighbors.

## Features

- Interactive grid where cells can be toggled by clicking
- Controls:
  - `SPACE` - Advance to next generation
  - `BACKSPACE` - Clear the grid
  - `ENTER` - Randomize cell positions
- Resizable window with automatically adjusting grid
- Color-coded display:
  - Live cells: Green
  - Dead cells: Black
  - Grid lines: Gray

## Project Structure

- `App.java` - Main application entry point
- `World.java` - Core game logic and grid management

### GUI Components

- `MainFrame.java` - Main window and keyboard controls
- `GamePanel.java` - Game grid rendering and mouse interaction

## Getting Started

### Prerequisites

- Java Development Kit (JDK)
- Eclipse IDE (optional)

### Running the Application

1. Compile the project
2. Run `App.java`
3. The game window will appear with an empty grid

## How to Play

- Click cells to toggle them between alive/dead states
- Use keyboard controls to:
  - Advance the simulation (`SPACE`)
  - Clear the grid (`BACKSPACE`)
  - Generate random patterns (`ENTER`)

## Rules

- Any live cell with fewer than two live neighbors dies (underpopulation)
- Any live cell with two or three live neighbors survives
- Any live cell with more than three live neighbors dies (overpopulation)
- Any dead cell with exactly three live neighbors becomes alive (reproduction)

## Development

This project is set up as a Java project with Eclipse project files included. The main development files are in the `src` directory.