package main;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Random;

import javax.swing.JFrame;

import org.jfree.data.time.Millisecond;

import Utils.MyPoint;
import Utils.Statistics;
import navigation.Grid;
import navigation.Robot;
import strategies.RoutingStrategy;
import strategies.TorusRouting2;
import view.GridViewPanel;
import view.StatsPanel;
import view.TimeSeriesChart;

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
		gbc.gridwidth = 2;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.fill = GridBagConstraints.BOTH;
		frame.add(new GridViewPanel(grid), gbc);
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.weightx = 0;
		gbc.weighty = 0;
		gbc.fill = GridBagConstraints.BOTH;
		
		StatsPanel spanel = new StatsPanel(stats);
		frame.add(spanel, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.weightx = 1;
		gbc.weighty = 0;
		TimeSeriesChart throughputChart = new TimeSeriesChart();
		frame.add(throughputChart.initTimeSeriesChart("Average Throughput") , gbc);
		frame.setSize(820, 650);
		frame.setVisible(true);

		int currentCollisions = 0;
		int currentCompletions = 0;
		long timer = System.currentTimeMillis();

		Random rand = new Random();
		while (true) {
			Thread.sleep(10);

			for (Robot r : grid.getGrid()) {

				r.goal = false;

				if (r.blocked) {
					currentCollisions += 1;
					continue;
				} else {
					r.currentLocation = r.nextLocation;
					if (r.currentLocation.equals(r.destination)) {
						currentCompletions += 1;
						r.goal = true;
						r.destination = newDestination(grid, rand);
					}
				}
				
				/*
				 * Update stats every seconds
				 */
				if(System.currentTimeMillis() - timer > 1000){
					
					stats.getCollisions().add(currentCollisions);
					stats.getCompletions().add(currentCompletions);
					
					final Millisecond now = new Millisecond();
					throughputChart.getSeries().add(now, stats.getAverageThroughput());
					System.out.println("Avg Thr: "+ stats.getAverageThroughput() + " Completions: "+ stats.getCompletionSum() +" Collisions: "+stats.getCollistionSum());
					
					currentCollisions = 0;
					currentCompletions = 0;
					timer=System.currentTimeMillis();
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