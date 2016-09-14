package game;

import java.awt.BorderLayout;
import java.awt.Canvas;

import javax.swing.JFrame;

public class GUI extends Canvas {
	private static final long serialVersionUID = 1L;
	
	private JFrame frame = new JFrame();
	private TitleBar titleBar = new TitleBar();
	private InputHandler input = new InputHandler();
	
	public GUI(){
		frame.setTitle(RES.TITLE + " " + RES.VERSION);
		frame.setSize(RES.WIDTH, RES.HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setLayout(new BorderLayout());
		
		frame.add(titleBar, BorderLayout.NORTH);
		frame.add(this, BorderLayout.CENTER);
		this.addMouseListener(input.getClicker());
		this.addMouseMotionListener(input.getMover());
		
		frame.setVisible(true);
		
		new Game(this, frame);
	}
	
	public static void main(String[] args){
		new GUI();
	}

}
