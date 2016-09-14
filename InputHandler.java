package game;

public class InputHandler {
	
	private ClickListener clicker = new ClickListener(this);
	private MotionListener mover = new MotionListener(this);
	
	private int x = 0;
	private int y = 0;
	private boolean dragging = false;
	private int button = 0;
	
	public ClickListener getClicker() {
		return clicker;
	}
	
	public void modifyGrid(){
		if(getX() <= Game.getGridWidth() - 1 && getY() <= Game.getGridHeight() - 1){
			boolean currentState = Game.getCellState((getX() + (getY() * Game.getGridWidth())));
			if(getButton() == 1){
				if(currentState == false){
					Game.setPopulation(Game.getPopulation() + 1);
					Game.setCellState((getX() + (getY() * Game.getGridWidth())), !currentState);
				}
			}else if(getButton() == 3){
				if(currentState == true){
					Game.setPopulation(Game.getPopulation() - 1);
					Game.setCellState((getX() + (getY() * Game.getGridWidth())), !currentState);
				}
			}
		}
	}
	
	public void setClicker(ClickListener clicker) {
		this.clicker = clicker;
	}
	
	public MotionListener getMover() {
		return mover;
	}
	
	public void setMover(MotionListener mover) {
		this.mover = mover;
	}
	
	public boolean isDragging() {
		return dragging;
	}
	
	public void setDragging(boolean dragging) {
		this.dragging = dragging;
	}
	
	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getButton() {
		return button;
	}
	
	public void setButton(int button) {
		this.button = button;
	}
	
}
