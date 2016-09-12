package game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

public class Game {
	
	private static Canvas c;
	private static boolean isRunning = true;
	private static double ticksPerSecond = 60.0;
	private static int cellSize = 20;
	
	private ArrayList<Cell> cells = new ArrayList<Cell>();
	private ArrayList<Cell> changedCells = new ArrayList<Cell>();
	
	public Game(Canvas c){
		Game.c = c;
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

	public ArrayList<Cell> getCells() {
		return cells;
	}

	public void setCells(ArrayList<Cell> cells) {
		this.cells = cells;
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
