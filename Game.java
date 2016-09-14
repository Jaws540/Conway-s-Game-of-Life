package game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.JFrame;

public final class Game {
	
	private static Canvas c;
	private static JFrame frame;
	private static boolean isRunning = false;
	private static double ticksPerSecond = 2.0;
	private static int cellSize = 50;
	private static int gridWidth;
	private static int gridHeight;
	
	private static ArrayList<Cell> cells = new ArrayList<Cell>();
	private static ArrayList<Cell> changedCells = new ArrayList<Cell>();
	
	private static int population = 0;
	private static int generation = 0;
	private static NumberFormat format = new DecimalFormat("###,###,###,###,###,###");
	
	public Game(Canvas c, JFrame frame){
		Game.c = c;
		gridWidth = c.getWidth() / cellSize;
		gridHeight = c.getHeight() / cellSize;
		
		Game.frame = frame;
		for(int y = 0; y < gridHeight; y++){
			for(int x = 0; x < gridWidth; x++){
				cells.add(new Cell((x + (y * gridWidth)), false));
				changedCells.add(new Cell((x + (y * gridWidth)), false));
			}
		}
		
		Loop l = new Loop();
		l.start();
	}
	
	public static final void render(){
		if(c.getBufferStrategy() == null){
			c.createBufferStrategy(3);
			return;
		}
		
		BufferStrategy bs = c.getBufferStrategy();
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, RES.WIDTH, RES.HEIGHT);
		
		for(int y = 0; y < gridHeight * cellSize; y += Game.getCellSize()){
			for(int x = 0; x < gridWidth * cellSize; x += Game.getCellSize()){
				if(cells.get((x + (y * gridWidth)) / Game.getCellSize()).isAlive()){
					g.setColor(Color.BLUE);
				}else{
					g.setColor(Color.WHITE);
				}
				g.fillRect(x, y, Game.getCellSize(), Game.getCellSize());
			}
		}
		
		g.setColor(Color.BLACK);
		for(int y = 0; y < gridHeight * cellSize + 1; y += Game.getCellSize()){
			g.drawLine(0, y, cellSize * gridWidth, y);
		}
		
		for(int x = 0; x < gridWidth * cellSize + 1; x += Game.getCellSize()){
			g.drawLine(x, 0, x, cellSize * gridHeight);
		}
		
		bs.show();
		g.dispose();
		frame.setTitle(RES.TITLE + " " + RES.VERSION + " - Population: " + format.format(population) + "\t Generation: " + format.format(generation));
	}
	
	public static void tick(){
		generation++;
		
		for(Cell c : cells){
			int aliveNeighbours = 0;
			
			boolean north = false, south = false, east = false, west = false;
			
			if(c.getIndex() - gridWidth < 0){
				north = true;
			}
			if(c.getIndex() + gridWidth >= cells.size()){
				south = true;
			}
			if(c.getIndex() % gridWidth == gridWidth - 1){
				east = true;
			}
			if(c.getIndex() % gridWidth == 0){
				west = true;
			}
			
			if(!north && !west){
				aliveNeighbours = getCellState(c.getIndex() - gridWidth - 1) ? aliveNeighbours + 1 : aliveNeighbours;
			}
			if(!north){
				aliveNeighbours = getCellState(c.getIndex() - gridWidth) ? aliveNeighbours + 1 : aliveNeighbours;
			}
			if(!north && !east){
				aliveNeighbours = getCellState(c.getIndex() - gridWidth + 1) ? aliveNeighbours + 1 : aliveNeighbours;
			}
			if(!east){
				aliveNeighbours = getCellState(c.getIndex() + 1) ? aliveNeighbours + 1 : aliveNeighbours;
			}
			if(!south && !east){
				aliveNeighbours = getCellState(c.getIndex() + gridWidth + 1) ? aliveNeighbours + 1 : aliveNeighbours;
			}
			if(!south){
				aliveNeighbours = getCellState(c.getIndex() + gridWidth) ? aliveNeighbours + 1 : aliveNeighbours;
			}
			if(!south && !west){
				aliveNeighbours = getCellState(c.getIndex() + gridWidth - 1) ? aliveNeighbours + 1 : aliveNeighbours;
			}
			if(!west){
				aliveNeighbours = getCellState(c.getIndex() - 1) ? aliveNeighbours + 1 : aliveNeighbours;
			}
			
			if(c.isAlive()){
				System.out.println(aliveNeighbours);
			}
			
			if(c.isAlive() && aliveNeighbours < 2){
				changedCells.get(c.getIndex()).setAlive(false);
			}
			if(c.isAlive() && (aliveNeighbours == 2 || aliveNeighbours == 3)){
				changedCells.get(c.getIndex()).setAlive(true);
			}
			if(c.isAlive() && aliveNeighbours > 3){
				changedCells.get(c.getIndex()).setAlive(false);
			}
			if(!c.isAlive() && aliveNeighbours == 3){
				changedCells.get(c.getIndex()).setAlive(true);
			}
		}
		System.out.println("\n");
		
		population = 0;
		for(Cell c : cells){
			if(c.isAlive()){
				population++;
			}
		}
		
		cells = changedCells;
	}

	public static boolean isRunning() {
		return isRunning;
	}

	public static void setRunning(boolean isRunning) {
		Game.isRunning = isRunning;
	}

	public static double getTicksPerSecond() {
		return ticksPerSecond;
	}

	public static void setTicksPerSecond(double ticksPerSecond) {
		Game.ticksPerSecond = ticksPerSecond;
	}

	public static ArrayList<Cell> getCells() {
		return cells;
	}

	public static void setCells(ArrayList<Cell> cells) {
		Game.cells = cells;
	}
	
	public static void setCellState(int index, boolean alive){
		Cell c = cells.get(index);
		c.setAlive(alive);
		cells.set(index, c);
	}
	
	public static boolean getCellState(int index){
		return cells.get(index).isAlive();
	}

	public static ArrayList<Cell> getChangedCells() {
		return changedCells;
	}

	public static void setChangedCells(ArrayList<Cell> changedCells) {
		Game.changedCells = changedCells;
	}

	public static int getCellSize() {
		return cellSize;
	}

	public static  void setCellSize(int cellSize) {
		Game.cellSize = cellSize;
	}

	public static int getPopulation() {
		return population;
	}

	public static void setPopulation(int population) {
		Game.population = population;
	}

	public static int getGeneration() {
		return generation;
	}

	public static void setGeneration(int generation) {
		Game.generation = generation;
	}

	public static int getGridWidth() {
		return gridWidth;
	}

	public static void setGridWidth(int gridWidth) {
		Game.gridWidth = gridWidth;
	}

	public static int getGridHeight() {
		return gridHeight;
	}

	public static void setGridHeight(int gridHeight) {
		Game.gridHeight = gridHeight;
	}

}
