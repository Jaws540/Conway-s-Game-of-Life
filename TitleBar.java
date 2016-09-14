package game;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TitleBar extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private JButton start = new JButton("Start");
	private JButton stop = new JButton("Stop");
	private JButton step = new JButton("Step");
	private JButton clear = new JButton("Clear Grid");
	private JTextField tps = new JTextField(5);
	
	public TitleBar(){
		JPanel titleBar = this;
		this.setLayout(new FlowLayout());
		this.setBackground(Color.WHITE);
		
		this.add(start);
		start.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				start.setEnabled(false);
				step.setEnabled(false);
				stop.setEnabled(true);
				clear.setEnabled(false);
				tps.setEnabled(false);
				try{
					Game.setTicksPerSecond(Double.parseDouble(tps.getText()));
				}catch(NumberFormatException e1){
					JOptionPane.showMessageDialog(titleBar, "Error: Ticks per second may only contain numbers", "Error", JOptionPane.ERROR_MESSAGE);
				}
				Game.setRunning(true);
			}
		});
		
		this.add(stop);
		stop.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				start.setEnabled(true);
				step.setEnabled(true);
				stop.setEnabled(false);
				clear.setEnabled(true);
				tps.setEnabled(true);
				Game.setRunning(false);
			}
		});
		stop.setEnabled(false);
		
		this.add(step);
		step.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				Game.tick();
			}
		});
		
		this.add(clear);
		clear.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				for(Cell c : Game.getCells()){
					c.setAlive(false);
				}
				Game.setPopulation(0);
			}
		});
		
		this.add(new JLabel("Ticks per Second: "));
		tps.setText("2.0");
		this.add(tps);
	}

}
