package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import navigation.Grid;
import navigation.Robot;

public class GridViewPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Grid grid;

	public GridViewPanel(Grid grid) {
		this.grid = grid;
		setBorder(BorderFactory.createLineBorder(Color.black));
	}

	public Dimension getPreferedSize() {
		return new Dimension(350, 350);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponents(g);
		for (int i = 0; i <= grid.getxGridSize(); i++) {
			if (i%2==1){
				g.setColor(new Color(192,192,192,255));
				g.fillRect(0 + i * grid.getGridResolution() + 1 + 1*2, 0,
						   grid.getGridResolution() - 1 - 2*2, grid.getyGridSize() * grid.getGridResolution());
			}
		}
		for (int i = 0; i <= grid.getyGridSize(); i++) {
			if (i%2==1){
				g.setColor(new Color(192,192,192,255));
				g.fillRect(0, 0 + i * grid.getGridResolution() + 1 + 1*2,
						   grid.getxGridSize() * grid.getGridResolution(),  grid.getGridResolution() - 1 - 2*2);
			}
		}
		for (int i = 0; i <= grid.getxGridSize(); i++) {
			g.setColor(new Color(0,0,0,255));
			
			g.drawLine(0 + i * grid.getGridResolution(), 0, 0 + i * grid.getGridResolution(),
					grid.getyGridSize() * grid.getGridResolution());
		}
		for (int i = 0; i <= grid.getyGridSize(); i++) {
			g.setColor(new Color(0,0,0,255));
			g.drawLine(0, 0 + i * grid.getGridResolution(), grid.getxGridSize() * grid.getGridResolution(),
					0 + i * grid.getGridResolution());
		}

		for (Robot o : grid.getGrid()) {

			if (o.currentLocation.x == o.destination.x && o.currentLocation.y == o.destination.y)
				g.setColor(Color.green);
			else if (o.blocked)
				g.setColor(Color.red);
			else
				g.setColor(Color.blue);

			g.fillRect(o.currentLocation.x * grid.getGridResolution() + 1,
					o.currentLocation.y * grid.getGridResolution() + 1, grid.getGridResolution() - 1,
					grid.getGridResolution() - 1);
		}
	}
}
