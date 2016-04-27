package main;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Random;

import javax.swing.JFrame;

import Utils.MyPoint;
import Utils.Statistics;
import navigation.Grid;
import navigation.Robot;
import view.GridViewPanel;
import view.LinePlotPanel;

public class Main {

	static int total_robots = 20;
	static int PANEL_COUNT = 2;
	static Statistics stats = new Statistics();
	
	public static void main(String[] args) throws InterruptedException {
		Grid grid = new Grid(total_robots);
		grid.initGrid();
		
		JFrame frame = new JFrame();
		frame.setTitle("Ocado Challenge - Sandpit 2016");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.fill = GridBagConstraints.BOTH;		
		frame.add(new GridViewPanel(grid), gbc);
		gbc.gridy = 1;
		gbc.weighty = 0;
		gbc.fill = GridBagConstraints.BOTH;
		frame.add(new LinePlotPanel(stats), gbc);
		frame.setSize(820, 650);
		frame.setVisible(true);
		
		Random rand = new Random();
		while (true) {
			Thread.sleep(100);

			for (Robot r : grid.getGrid()) {
				
				r.goal = false;
				
				if (r.blocked){
					stats.addCollision();
					continue;
				}
				else{
					r.currentLocation = r.nextLocation;

				if (r.currentLocation.equals(r.destination)) {
					r.goal = true;
				}
				if (r.currentLocation.equals(r.destination)){
					stats.addCompletion();
					r.destination = new MyPoint(rand.nextInt(grid.getxGridSize()), rand.nextInt(grid.getyGridSize()));
				}
				
				}

			}
			
			

			dumbLRouting(grid);

				

			for (Robot r : grid.getGrid()) {
				r.blocked = false;
				for (Robot o : grid.getGrid()) {
					if (r == o) continue;
					if (o.currentLocation.equals(r.nextLocation) || o.nextLocation.equals(r.nextLocation)) {
						if (r.nextLocation.x==r.currentLocation.x) {
							r.blocked = true;
							break;
						}
					}
				}
			}
			
			frame.repaint();
		}
	}


	private static void dumbLRouting(Grid grid) {
		for (Robot r : grid.getGrid()) {
			if (r.currentLocation.x != r.destination.x) {
				if (r.currentLocation.x > r.destination.x)
					r.nextLocation = r.currentLocation.left();
				else
					r.nextLocation = r.currentLocation.right();
				r.nextLocation.y = r.currentLocation.y;
			} else {
				if (r.currentLocation.y > r.destination.y)
					r.nextLocation = r.currentLocation.up();
				else
					r.nextLocation = r.currentLocation.down();
				r.nextLocation.x = r.currentLocation.x;
			}
		}
		
	}
}