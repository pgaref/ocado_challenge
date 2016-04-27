package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Utils.MyPoint;
import navigation.Grid;
import navigation.Robot;

public class Main {

	static int total_robots = 20;

	public static void main(String[] args) throws InterruptedException {

		Grid grid = new Grid(total_robots);
		grid.initGrid();
		
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.add(new JPanel() {
			{
				{// public JPanel() {
					setBorder(BorderFactory.createLineBorder(Color.black));
				}
			}

			public Dimension getPreferedSize() {
				return new Dimension(250, 250);
			}

			public void paintComponent(Graphics g) {
				super.paintComponents(g);
				for (int i = 0; i <= grid.getxGridSize(); i++) {
					// if (i%0 == 1) g.drawRect(0+i*res, 0, 0+(i+1)*res,
					// ny*res);
					g.drawLine(0 + i * grid.getGridResolution(), 0, 0 + i * grid.getGridResolution(), grid.getyGridSize() * grid.getGridResolution());
				}
				for (int i = 0; i <= grid.getyGridSize(); i++) {
					g.drawLine(0, 0 + i * grid.getGridResolution(), grid.getxGridSize() * grid.getGridResolution(), 0 + i * grid.getGridResolution());
				}

				for (Robot o : grid.getGrid()) {

					if (o.currentLocation.x == o.destination.x && o.currentLocation.y == o.destination.y)
						g.setColor(Color.green);
					else if (o.blocked)
						g.setColor(Color.red);
					else
						g.setColor(Color.blue);

					g.fillRect(o.currentLocation.x * grid.getGridResolution() + 1, o.currentLocation.y * grid.getGridResolution() + 1, grid.getGridResolution() - 1, grid.getGridResolution() - 1);
				}
			}
		});
		
		frame.setVisible(true);
		frame.pack();
		
		Random rand = new Random();
		while (true) {
			Thread.sleep(500);

			for (Robot r : grid.getGrid()) {

				if (r.blocked)
					continue;
				else
					r.currentLocation = r.nextLocation;

				if (r.currentLocation.equals(r.destination))
					r.destination = new MyPoint(rand.nextInt(grid.getxGridSize()), rand.nextInt(grid.getyGridSize()));

			}

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
			
				

			for (Robot r : grid.getGrid()) {
				r.blocked = false;
				for (Robot o : grid.getGrid()) {
					if (r == o) continue;
					if (o.currentLocation.equals(r.nextLocation) || o.nextLocation.equals(r.nextLocation)) {
						r.blocked = true;
						break;
					}
				}
			}
			frame.repaint();
		}
	}
}