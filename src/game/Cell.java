package game;

public class Cell {
	
	private boolean alive = false;
	private int index = 0;
	
	public Cell(int index){
		setIndex(index);
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	

}
