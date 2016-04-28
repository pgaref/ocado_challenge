package view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import Utils.Statistics;

/* 
 * This class is responsible for plotting the RSS in the GUI
 *
 */
public class StatsPanel extends JPanel {

	// origin at top left
	int width = 220;
	int height = 200;
	private static final DecimalFormat NUMBER_FORMAT = new DecimalFormat("#.###");
	
	private Statistics stats;

//	Rectangle2D.Double square = new Rectangle2D.Double(0, 0, width, height);
	
	private JLabel completionsLabel;
	private JLabel collisionsLabel;
	private JLabel throughputLabel;
	private JLabel avgStepsLabel;
	private JLabel overheadLabel;
	private JCheckBox destinationCheckBox;
	private JCheckBox coloursCheckBox;

	public StatsPanel(Statistics s) {
		this.stats = s;
		
		init();
	}

	private void init() {
		setLayout(new GridBagLayout());
		
		int currentY = 0;
		
		JButton restartButton = new JButton("Restart");
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridx = 0;
		gbc.gridy = currentY++;
		gbc.gridwidth = 2;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		add(restartButton, gbc);

		destinationCheckBox = new JCheckBox("Show Destinations");
		gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridx = 0;
		gbc.gridy = currentY++;
		gbc.gridwidth = 2;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		add(destinationCheckBox, gbc);

		coloursCheckBox = new JCheckBox("Show Colours");
		gbc.gridx = 0;
		gbc.gridy = currentY++;
		gbc.gridwidth = 2;
		gbc.fill = GridBagConstraints.NONE;
		add(coloursCheckBox, gbc);
		
		JSeparator sep = new JSeparator(JSeparator.HORIZONTAL);
		gbc.gridx = 0;
		gbc.gridy = currentY++;
		gbc.gridwidth = 2;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		add(sep, gbc);
		
		JLabel completionsDescLabel = new JLabel("Completions: ", JLabel.LEFT);
		gbc.gridx = 0;
		gbc.gridy = currentY;
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.NONE;
		add(completionsDescLabel, gbc);
		
		completionsLabel = new JLabel("", JLabel.LEFT);
		gbc.gridx = 1;
		gbc.gridy = currentY++;
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.NONE;
		add(completionsLabel, gbc);
		
		JLabel collisionsDescLabel = new JLabel("Collisions: ", JLabel.LEFT);
		gbc.gridx = 0;
		gbc.gridy = currentY;
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.NONE;
		add(collisionsDescLabel, gbc);
		
		collisionsLabel = new JLabel("", JLabel.LEFT);
		gbc.gridx = 1;
		gbc.gridy = currentY++;
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.NONE;
		add(collisionsLabel, gbc);
		
		
		JLabel throughputDescLabel = new JLabel("Throughput: ", JLabel.LEFT);
		gbc.gridx = 0;
		gbc.gridy = currentY;
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.NONE;
		add(throughputDescLabel, gbc);
		
		throughputLabel = new JLabel("", JLabel.LEFT);
		gbc.gridx = 1;
		gbc.gridy = currentY++;
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.NONE;
		add(throughputLabel, gbc);

		JLabel avgStepsDescLabel = new JLabel("Avg Steps: ", JLabel.LEFT);
		gbc.gridx = 0;
		gbc.gridy = currentY;
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.NONE;
		add(avgStepsDescLabel, gbc);
		
		avgStepsLabel = new JLabel("", JLabel.LEFT);
		gbc.gridx = 1;
		gbc.gridy = currentY++;
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.NONE;
		add(avgStepsLabel, gbc);

		JLabel overheadDescLabel = new JLabel("Overhead: ", JLabel.LEFT);
		gbc.gridx = 0;
		gbc.gridy = currentY;
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.NONE;
		add(overheadDescLabel, gbc);
		
		overheadLabel = new JLabel("", JLabel.LEFT);
		gbc.gridx = 1;
		gbc.gridy = currentY++;
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.NONE;
		add(overheadLabel, gbc);
	}

	public void addDestinationCheckBoxListener(ActionListener al) {
		destinationCheckBox.addActionListener(al);
	}
	
public void addColoursCheckBoxListener(ActionListener al) {
		coloursCheckBox.addActionListener(al);
	}
	
	public void updateStats() {
		completionsLabel.setText(Integer.toString(stats.getCompletionSum()));
		collisionsLabel.setText(Integer.toString(stats.getCollistionSum()));
		throughputLabel.setText(NUMBER_FORMAT.format(stats.getAverageThroughput()));
		avgStepsLabel.setText(NUMBER_FORMAT.format(stats.getAverageSteps()));
		overheadLabel.setText(NUMBER_FORMAT.format(stats.getAverageOverhead()));
	}
//	
//	// Methods, constructors, fields.
//	@Override
//	public void paintComponent(Graphics g) {
//		// System.out.println("Paint method called!
//		// tempSize="+temperatureData.size());
//		super.paintComponent(g); // paints background
//		Graphics2D g2d = (Graphics2D) g;
//		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//		// draw background
//		g2d.setPaint(Color.WHITE);
//		g2d.fill(square);
//		g2d.setPaint(Color.BLACK);
//		g2d.draw(square);
//
//		g2d.setStroke(new BasicStroke(2));
//
//		DecimalFormat numberFormat = new DecimalFormat("#.###");
//		
//		Rectangle2D.Double legend = new Rectangle2D.Double(5, height/2 -30, 200, 70);
//
//		g2d.setPaint(Color.WHITE);
//		g2d.fill(legend);
//		g2d.setPaint(Color.BLACK);
//		g2d.draw(legend);
//
//		g2d.setPaint(Color.BLUE);
//		g2d.drawString("Completions = " + stats.getCompletionSum(), 30, height/2 - 15);
//		g2d.setPaint(Color.RED);
//		g2d.drawString("Collisions = " + numberFormat.format(stats.getCollistionSum()), 30, height/2);
//		g2d.setPaint(Color.BLACK);
//		g2d.drawString("Avg Throughput = " + numberFormat.format(stats.getAverageThroughput()), 30, height/2 + 15);
//	}
//
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(width, height);
	}
}
