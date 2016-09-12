package game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

public class Game {
	
	private static Canvas c;
	private static boolean isRunning = false;
	private static double ticksPerSecond = 60.0;
	private static int cellSize = 20;
	
	private static ArrayList<Cell> cells = new ArrayList<Cell>();
	private ArrayList<Cell> changedCells = new ArrayList<Cell>();
	
	public Game(Canvas c){
		Game.c = c;
		for(int y = 0; y < RES.HEIGHT / getCellSize(); y++){
			for(int x = 0; x < RES.WIDTH / getCellSize(); x++){
				cells.add(new Cell(x + (y * (RES.WIDTH / getCellSize())), false));
				changedCells.add(new Cell(x + (y * (RES.WIDTH / getCellSize())), false));
			}
		}
		Loop l = new Loop();
		l.start();
	}
	
	public static void render(){
		if(c.getBufferStrategy() == null){
			c.createBufferStrategy(3);
			return;
		}
		
		BufferStrategy bs = c.getBufferStrategy();
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, RES.WIDTH, RES.HEIGHT);
		
		for(int y = 0; y < RES.HEIGHT; y += getCellSize()){
			for(int x = 0; x < RES.WIDTH; x += getCellSize()){
				System.out.println(x + (y * (RES.WIDTH / getCellSize())));
				if(Game.cells.get(x + (y * (RES.WIDTH / getCellSize()))).isAlive()){
					g.setColor(Color.BLUE);
				}else{
					g.setColor(Color.WHITE);
				}
				g.fillRect(x, y, getCellSize(), getCellSize());
			}
		}
		
		g.setColor(Color.BLACK);
		for(int y = 0; y < RES.HEIGHT; y += getCellSize()){
			g.drawLine(0, y, RES.WIDTH, y);
		}
		
		for(int x = 0; x < RES.WIDTH; x += getCellSize()){
			g.drawLine(x, 0, x, RES.HEIGHT);
		}
		
		bs.show();
		g.dispose();
	}
	
	public static void tick(){
		
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

	public void setCells(ArrayList<Cell> cells) {
		Game.cells = cells;
	}
	
	public static void setCellState(int index, boolean alive){
		Cell c = Game.getCells().get(index);
		c.setAlive(alive);
		Game.cells.set(index, c);
	}

	public ArrayList<Cell> getChangedCells() {
		return changedCells;
	}

	public void setChangedCells(ArrayList<Cell> changedCells) {
		this.changedCells = changedCells;
	}

	public static int getCellSize() {
		return cellSize;
	}

	public static void setCellSize(int cellSize) {
		Game.cellSize = cellSize;
	}

}
