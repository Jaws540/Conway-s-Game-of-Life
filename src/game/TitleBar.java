package game;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class TitleBar extends JPanel {
	
	private JButton start = new JButton("Start");
	private JButton stop = new JButton("Stop");
	private JButton step = new JButton("Step");
	
	public TitleBar(){
		this.setLayout(new FlowLayout());
		
		this.add(start);
		start.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				Game.setRunning(true);
			}
		});
		
		this.add(stop);
		stop.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				Game.setRunning(false);
			}
		});
		
		this.add(step);
		step.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				Game.tick();
			}
		});
		
	}

}
