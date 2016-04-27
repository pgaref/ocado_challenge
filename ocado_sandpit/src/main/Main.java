package main;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.util.Random;

import javax.swing.JFrame;

import Utils.MyPoint;
import Utils.Statistics;
import navigation.Grid;
import navigation.Robot;
import view.GridViewPanel;
import view.LinePlotPanel;

public class Main {

	static int PANEL_COUNT = 2;
	static Statistics stats = new Statistics();
	
	static int total_robots = 5000/20;

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
			Thread.sleep(10);

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
			
			

			//dumbLRouting(grid);

			oeRowsLRouting(grid);


			for (Robot r : grid.getGrid()) {
				r.was_blocked = r.blocked;
			}

			for (Robot r : grid.getGrid()) {
				r.blocked = false;
				for (Robot o : grid.getGrid()) {
					if (r == o) continue;
					if (o.currentLocation.equals(r.nextLocation) || o.nextLocation.equals(r.nextLocation)) {
						if (r.nextLocation.y != r.currentLocation.y || r.nextLocation.equals(o.currentLocation)) {
							r.blocked = true;
							break;
						} else {

							System.out.println("Not blocked: " + r.currentLocation + " --- " + r.nextLocation);
						}
					}
				}
			}
			
			for (Robot r : grid.getGrid()) {
				if (r.blocked && r.was_blocked) {
					if (r.nextLocation.x == r.currentLocation.x)
						validHorizontal(r);
					else
						validVertical(r);
				}
			}
			
			for (Robot r : grid.getGrid()) {
				r.blocked = false;
				for (Robot o : grid.getGrid()) {
					if (r == o) continue;
					if (o.currentLocation.equals(r.nextLocation) || o.nextLocation.equals(r.nextLocation)) {
						if (r.nextLocation.y != r.currentLocation.y || r.nextLocation.equals(o.currentLocation))
						{
							System.out.println("Blocked: " + r.currentLocation + " --- " + r.nextLocation);
							r.blocked = true;
							break;
						}
					}
				}
			}
			
			frame.repaint();
		}
	}

	private static void oeRowsLRouting(Grid grid) {
		for (Robot r : grid.getGrid()) {
			if (r.currentLocation.x != r.destination.x) {
				if (r.currentLocation.x > r.destination.x)
					if (r.currentLocation.y %2 == 1) {
						r.nextLocation = r.currentLocation.left();
					} else {
						validVertical(r);
					}
				else
					if (r.currentLocation.y %2 == 0) {
						r.nextLocation = r.currentLocation.right();
					} else {
						validVertical(r);
					}
			} else {
				if (r.currentLocation.y > r.destination.y)
					if (r.currentLocation.x %2 == 1) {
						r.nextLocation = r.currentLocation.up();
					} else {
						validHorizontal(r);
					}
				else
					if (r.currentLocation.x %2 == 0) {
						r.nextLocation = r.currentLocation.down();
					} else {
						validHorizontal(r);
					}
			}
		}
	}

	private static void validVertical(Robot r) {
		if (r.currentLocation.x %2 == 1)
			r.nextLocation = r.currentLocation.up();
		else
			r.nextLocation = r.currentLocation.down();
	}

	private static void validHorizontal(Robot r) {
		if (r.currentLocation.y %2 == 1)
			r.nextLocation = r.currentLocation.left();
		else
			r.nextLocation = r.currentLocation.right();
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