package game;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class InputHandler implements MouseListener, MouseMotionListener {
	
	private boolean isDown = false;
	
	private int x = 0;
	private int y = 0;

	@Override
	public void mouseDragged(MouseEvent e) {}

	@Override
	public void mouseMoved(MouseEvent e) {
		x = e.getX() / Game.getCellSize();
		y = e.getY() / Game.getCellSize();
	}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {
		isDown = true;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(isDown){
			if(e.getButton() == 1){
				Game.setCellState(x + (y * (RES.WIDTH / Game.getCellSize())), true);
			}else if(e.getButton() == 3){
				Game.setCellState(x + (y * (RES.WIDTH / Game.getCellSize())), false);
			}
			
			isDown = false;
		}
	}

}
