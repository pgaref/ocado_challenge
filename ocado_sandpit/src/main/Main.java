package main;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.jfree.data.time.Millisecond;

import Utils.MyPoint;
import Utils.Statistics;
import navigation.Grid;
import navigation.Robot;
import strategies.IRoutingStrategy;
import strategies.TorusRouting2;
import view.GridViewPanel;
import view.StatsPanel;
import view.TimeSeriesChart;

public class Main {

	static int PANEL_COUNT = 2;
	static Statistics stats = new Statistics();
	private static int CYCLE_SLEEP_TIME = 10;
	
	static int total_robots;
	static double robot_density = 1.0 / 20;
	

	private static StatsPanel sPanel;

	static boolean loop = true;
	static int iterations = 10000000;
	static boolean print = true;

	public static void main(String[] args, int robots, int time, int iterations) throws InterruptedException {
		CYCLE_SLEEP_TIME = time;
		loop = false;
		total_robots = robots;
		Main.iterations = iterations;
		
		print = false;

		main(args);
		getStats();
	}

	public static void main(String[] args) throws InterruptedException {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

		stats = new Statistics();
		
		Grid grid = new Grid(robot_density);
		grid.initGrid();

		IRoutingStrategy strategy = new TorusRouting2();

		JFrame frame = new JFrame();
		frame.setTitle("Ocado Challenge - Sandpit 2016");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.getContentPane().setLayout(new GridBagLayout());
		final GridViewPanel gridPanel = new GridViewPanel(grid);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.fill = GridBagConstraints.BOTH;
		frame.add(gridPanel, gbc);
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.weightx = 0;
		gbc.weighty = 0;
		gbc.fill = GridBagConstraints.BOTH;

		sPanel = new StatsPanel(stats);
		sPanel.addDestinationCheckBoxListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gridPanel.setDebugModeActive(((JCheckBox) e.getSource()).isSelected());
			}
		});
		sPanel.addSimpleDestinationCheckBoxListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gridPanel.setSimpleDebugModeActive(((JCheckBox) e.getSource()).isSelected());
			}
		});
		sPanel.addColoursCheckBoxListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gridPanel.setColoursActive(((JCheckBox) e.getSource()).isSelected());
			}
		});
		sPanel.addThingCheckBoxListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gridPanel.setThingActive(((JCheckBox) e.getSource()).isSelected());
			}
		});
		frame.add(sPanel, gbc);

		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.weightx = 1;
		gbc.weighty = 0;
		TimeSeriesChart throughputChart = new TimeSeriesChart();
		frame.add(throughputChart.initTimeSeriesChart("Average Throughput"), gbc);
		frame.setSize(820, 650);
		frame.setVisible(true);

		int currentCollisions = 0;
		int currentCompletions = 0;
		int i = 0;
		long timer = System.currentTimeMillis();

		Random rand = new Random();

		while ( i < iterations | loop) {
			i++;
			Thread.sleep(CYCLE_SLEEP_TIME);

			for (Robot r : grid.getGrid()) {

				r.goal = false;
				r.addStep();

				if (r.blocked) {
					currentCollisions += 1;
					continue;
				} else {
					r.currentLocation = r.nextLocation;
					if (r.currentLocation.equals(r.destination)) {
						currentCompletions += 1;
						stats.overhead.add(r.getOverhead());
						stats.avgSteps.add(r.steps);
						r.goal = true;
						r.destination = newDestination(grid, rand);
						r.cleanSteps();
						r.calcOptSteps();
					}
				}

				/*
				 * Update stats every seconds
				 */
				if (System.currentTimeMillis() - timer > 1000) {

					stats.getCollisions().add(currentCollisions);
					stats.getCompletions().add(currentCompletions);

					final Millisecond now = new Millisecond();
					throughputChart.getSeries().add(now, stats.getAverageThroughput());

					currentCollisions = 0;
					currentCompletions = 0;
					timer = System.currentTimeMillis();
					
					if (print){
						System.out.println("Avg Thr: " + stats.getAverageThroughput() + " Completions: "
								+ stats.getCompletionSum() + " Collisions: " + stats.getCollistionSum() + " Overhead: "
								+ stats.getAverageOverhead() + " Avg Steps: " + stats.getAverageSteps());
					}
					
					sPanel.updateStats();
				}

			}

			strategy.route(grid);

			frame.repaint();
			
		}
	}

	private static MyPoint newDestination(Grid grid, Random rand) {
		return new MyPoint(2 + rand.nextInt(grid.getxGridSize() - 4), 2 + rand.nextInt(grid.getyGridSize() - 4));
	}

	public static  void getStats(){
		System.out.println("===================================================================");
		System.out.println("Robots: " + total_robots);
		System.out.println("Avg Thr: " + stats.getAverageThroughput() + " Completions: "
				+ stats.getCompletionSum() + " Collisions: " + stats.getCollistionSum() + " Overhead: "
				+ stats.getAverageOverhead() + " Avg Steps: " + stats.getAverageSteps());
		
		System.out.println("===================================================================");
	}
}