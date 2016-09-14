package game;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MotionListener implements MouseMotionListener {
	
	private InputHandler input;
	
	public MotionListener(InputHandler ih){
		this.input = ih;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		input.setDragging(true);
		input.setX(e.getX() / Game.getCellSize());
		input.setY(e.getY() / Game.getCellSize());
		
		input.modifyGrid();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		input.setX(e.getX() / Game.getCellSize());
		input.setY(e.getY() / Game.getCellSize());
	}

}
