package game;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ClickListener implements MouseListener {
	
	private InputHandler input;
	
	public ClickListener(InputHandler ih){
		this.input = ih;
	}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {
		input.setButton(e.getButton());
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(!input.isDragging()){
			input.modifyGrid();
		}
		input.setDragging(false);
	}
	
}
