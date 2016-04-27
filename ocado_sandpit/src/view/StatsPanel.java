package view;

// For Graphics, etc.
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
// For Ellipse2D, etc.
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.text.DecimalFormat;

import javax.swing.JPanel;

import Utils.Statistics;

/* 
 * This class is responsible for plotting the RSS in the GUI
 *
 */
public class StatsPanel extends JPanel {

	// origin at top left
	int width = 220;
	int height = 200;

	private Statistics stats;

	Rectangle2D.Double square = new Rectangle2D.Double(0, 0, width, height);

	public StatsPanel(Statistics s) {
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

		g2d.setStroke(new BasicStroke(2));

		DecimalFormat numberFormat = new DecimalFormat("#.###");
		
		Rectangle2D.Double legend = new Rectangle2D.Double(5, height/2 -30, 200, 70);

		g2d.setPaint(Color.WHITE);
		g2d.fill(legend);
		g2d.setPaint(Color.BLACK);
		g2d.draw(legend);

		g2d.setPaint(Color.BLUE);
		g2d.drawString("Completions = " + stats.getCompletionSum(), 30, height/2 - 15);
		g2d.setPaint(Color.RED);
		g2d.drawString("Collisions = " + numberFormat.format(stats.getCollistionSum()), 30, height/2);
		g2d.setPaint(Color.BLACK);
		g2d.drawString("Avg Throughput = " + numberFormat.format(stats.getAverageThroughput()), 30, height/2 + 15);
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(width, height);
	}
}
