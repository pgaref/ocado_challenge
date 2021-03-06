package view;

import java.awt.Color;
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
	private boolean simpleDebugModeActive = false;
	private boolean debugModeActive = false;
	private boolean thingActive = false;
	private boolean coloursActive = false;
	
	public GridViewPanel(Grid grid) {
		this.grid = grid;
		setBorder(BorderFactory.createLineBorder(Color.black));
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

		boolean first = true;
		for (Robot o : grid.getGrid()) {

			
			if (o.blocked)
				g.setColor(Color.red);
			else if (o.goal)
				g.setColor(Color.cyan);
			else if (coloursActive)
				g.setColor(o.color);
			else
				g.setColor(Color.blue);
			
			
			
			if (debugModeActive || (simpleDebugModeActive&&first)) {
				if (o.debugColour != null)
					g.setColor(o.debugColour);
			}

	
			g.fillRect(o.currentLocation.x * grid.getGridResolution() + 1,
					o.currentLocation.y * grid.getGridResolution() + 1, grid.getGridResolution() - 1,
					grid.getGridResolution() - 1);
			
			if ((debugModeActive || (simpleDebugModeActive&&first) ) && thingActive) {
				g.setColor(Color.MAGENTA);
				g.drawLine(o.currentLocation.x * grid.getGridResolution(),o.currentLocation.y * grid.getGridResolution(),o.destination.x * grid.getGridResolution(),o.destination.y * grid.getGridResolution());
				g.fillRect(o.destination.x * grid.getGridResolution() + 1, o.destination.y * grid.getGridResolution() + 1, grid.getGridResolution() - 1, grid.getGridResolution() - 1);
			}
			
			first = false;
		}
		
		
		
	}

	public boolean isDebugModeActive() {
		return debugModeActive;
	}

	public void setDebugModeActive(boolean debugModeActive) {
		this.debugModeActive = debugModeActive;
	}

	public boolean isSimpleDebugModeActive() {
		return simpleDebugModeActive;
	}

	public void setSimpleDebugModeActive(boolean simpleDebugModeActive) {
		this.simpleDebugModeActive = simpleDebugModeActive;
	}

	public boolean isColoursActive() {
		return coloursActive;
	}

	public void setColoursActive(boolean coloursActive) {
		this.coloursActive = coloursActive;
	}

	public boolean isThingActive() {
		return thingActive;
	}

	public void setThingActive(boolean thingActive) {
		this.thingActive = thingActive;
	}

}
