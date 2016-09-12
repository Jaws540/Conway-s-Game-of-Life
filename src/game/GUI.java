package game;

import java.awt.BorderLayout;
import java.awt.Canvas;

import javax.swing.JFrame;

public class GUI extends Canvas {
	
	private JFrame frame = new JFrame();
	private Game game;
	
	public GUI(){
		frame.setTitle(RES.TITLE + " " + RES.VERSION);
		frame.setSize(RES.WIDTH, RES.HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setLayout(new BorderLayout());
		
		frame.add(this, BorderLayout.CENTER);
		
		frame.setVisible(true);
		
		game = new Game(this);
	}
	
	public static void main(String[] args){
		new GUI();
	}

}
