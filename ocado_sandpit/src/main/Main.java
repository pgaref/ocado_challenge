package main;

import java.util.Random;

import javax.swing.JFrame;

import Utils.MyPoint;
import navigation.Grid;
import navigation.Robot;
import view.GridViewPanel;

public class Main {

	static int total_robots = 20;

	public static void main(String[] args) throws InterruptedException {

		Grid grid = new Grid(total_robots);
		grid.initGrid();
		
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.add(new GridViewPanel(grid));
		
		frame.setVisible(true);
		frame.pack();
		
		Random rand = new Random();
		while (true) {
			Thread.sleep(500);

			for (Robot r : grid.getGrid()) {
				
				r.goal = false;
				
				if (r.blocked)
					continue;
				else
					r.currentLocation = r.nextLocation;

				if (r.currentLocation.equals(r.destination)) {
					r.goal = true;
					r.destination = new MyPoint(rand.nextInt(grid.getxGridSize()), rand.nextInt(grid.getyGridSize()));
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