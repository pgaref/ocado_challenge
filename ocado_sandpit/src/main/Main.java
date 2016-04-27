package main;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Random;

import javax.swing.JFrame;

import Utils.MyPoint;
import Utils.Statistics;
import navigation.Grid;
import navigation.Robot;
import strategies.RoutingStrategy;
import strategies.TorusRouting2;
import view.GridViewPanel;
import view.LinePlotPanel;

public class Main {

	static int PANEL_COUNT = 2;
	static Statistics stats = new Statistics();
	
	static int total_robots = 500/20;

	public static void main(String[] args) throws InterruptedException {
		Grid grid = new Grid(total_robots);
		grid.initGrid();
		
		RoutingStrategy strategy = new TorusRouting2();
		
		
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
					r.destination = newDestination(grid, rand);
				}
				
				}

			}
			
			strategy.route(grid);
			
			frame.repaint();
		}
	}

	private static MyPoint newDestination(Grid grid, Random rand) {
		return new MyPoint(2+rand.nextInt(grid.getxGridSize()-4), 2+rand.nextInt(grid.getyGridSize()-4));
	}

}