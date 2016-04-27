package view;
/**
 * @file LinePlot.java
 * @brief LinePlot to display sensor data.
 * 
 * @author Francis Engelmann, Martin Serror
 * @date May 22 2012
 */

import javax.swing.*;

import Utils.Statistics;

import java.awt.*; // For Graphics, etc.
import java.awt.geom.*; // For Ellipse2D, etc.
import java.text.DecimalFormat;
import java.util.ArrayList;

/* 
 * This class is responsible for plotting the RSS in the GUI
 *
 */
public class LinePlotPanel extends JPanel {

	// origin at top left
	int width = 600;
	int height = 200;

	public static ArrayList<Integer> collisions;
	public static ArrayList<Integer> completions;
	public static ArrayList<Double> throughput;
	private Statistics stats;

	Rectangle2D.Double square = new Rectangle2D.Double(0, 0, width, height);

	public LinePlotPanel(Statistics s) {
		collisions = new ArrayList<Integer>();
		completions = new ArrayList<Integer>();
		throughput = new ArrayList<Double>();
		this.stats = s;
	}

	// Methods, constructors, fields.
	@Override
	public void paintComponent(Graphics g) {
		// System.out.println("Paint method called!
		// tempSize="+temperatureData.size());
		super.paintComponent(g); // paints background
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		// draw background
		g2d.setPaint(Color.WHITE);
		g2d.fill(square);
		g2d.setPaint(Color.BLACK);
		g2d.draw(square);

		double incX;
		double incY;

		int padX = 0;
		int padY = 0;

		g2d.setStroke(new BasicStroke(2));

		// draw Completions
//		System.out.println("Completions: " + completions.size());
		if (completions.size() > 1) {
			incX = (double) ((width / 3.0) / (double) (completions.size() - 1));
			// +5 to move line up
			incY = (double) (height / 100.0f) + 5;
			for (int i = 0; i < completions.size() - 1; i++) {
				g2d.setPaint(Color.BLUE);
				double x1 = padX + i * incX;
				double y1 = height - padY - incY * (completions.get(i) + 50);
				double x2 = padX + (i + 1) * incX;
				double y2 = height - padY - incY * (completions.get(i + 1) + 50);
				g2d.draw(new Line2D.Double(x1, y1, x2, y2));
			}

		}

		if (throughput.size() > 1) {
			incX = (double) ((width / 3.0) / (double) (throughput.size() - 1));
			// +5 to move line up
			incY = (double) (height / 100.0f);
			for (int i = 0; i < throughput.size() - 1; i++) {
				g2d.setPaint(Color.GREEN);
				double x1 = padX + i * incX;
				double y1 = height - padY - incY * (throughput.get(i) + 50);
				double x2 = padX + (i + 1) * incX;
				double y2 = height - padY - incY * (throughput.get(i + 1) + 50);
				g2d.draw(new Line2D.Double(x1, y1, x2, y2));
			}

		}

		g2d.setStroke(new BasicStroke(3));
		if (collisions.size() > 1) {
			incX = (double) ((width / 3.0) / (double) (collisions.size() - 1));
			incY = (double) (height / 100.0f) + 5;
			for (int i = 0; i < collisions.size() - 1 - 1; i++) {
				g2d.setPaint(Color.RED);
				double x1 = padX + i * incX;
				double y1 = height - padY - incY * (collisions.get(i) + 50);
				double x2 = padX + (i + 1) * incX;
				double y2 = height - padY - incY * (collisions.get(i + 1) + 50);
				g2d.draw(new Line2D.Double(x1, y1, x2, y2));
			}

			DecimalFormat numberFormat = new DecimalFormat("#.######");

			g2d.setPaint(Color.BLACK);
			/*
			 * g2d.drawString( "Current RSSI= " + a.rssi.get(a.rssi.size() - 1),
			 * 20 + padX, 40);
			 */
			 g2d.drawString("Completions = " + completions.size(), 20 + padX, 20);
			 g2d.drawString("Collisions = " + numberFormat.format(collisions.size()), 20 + padX, 40);
			 g2d.drawString("Throughput = " + numberFormat.format(throughput.get(throughput.size()-1)), 20 + padX, 60);
		}
		g2d.setStroke(new BasicStroke(1));
		padX += (int) (width / 3.0);
		g2d.draw(new Line2D.Double(padX, 0, padX, height));
		// }

		Rectangle2D.Double legend = new Rectangle2D.Double(5, height - 70 + 15, 100, 50);
		g2d.setStroke(new BasicStroke(1));

		g2d.setPaint(Color.WHITE);
		g2d.fill(legend);
		g2d.setPaint(Color.BLACK);
		g2d.draw(legend);

		g2d.setPaint(Color.BLUE);
		g2d.drawString("Completions", 12, height - 70 + 30);
		g2d.setPaint(Color.RED);
		g2d.drawString("Collisions", 12, height - 55 + 30);
		 g2d.setPaint(Color.GREEN);
		 g2d.drawString("Thoughput", 12, height - 40 + 30);
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(width, height);
	}
}