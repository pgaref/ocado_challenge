package navigation;

import java.util.ArrayList;
import java.util.Random;

import Utils.MyPoint;

public class Grid {

	private int xGridSize = 100;
	private int yGridSize = 50;
	private int gridResolution = 8;
	private int num_robots;
	private ArrayList<Robot> grid = new ArrayList<Robot>();

	public Grid(int nrobots){
		this.num_robots = nrobots;
	}
	
	public void initGrid() {
		
		Random rand = new Random();
		for (int i = 0; i < num_robots; i++) {
			while (true) {
				boolean colide = false;
				MyPoint p = new MyPoint(2+rand.nextInt(this.xGridSize-4), 2+rand.nextInt(this.yGridSize-4));
				for (Robot o : grid){
					if (o.currentLocation.equals(p)) {
						colide = true;
						break;
					}
				}
				if(!colide){
					//p = new MyPoint(this.xGridSize/2, this.yGridSize-1);
					grid.add(new Robot(p, new MyPoint(2+rand.nextInt(this.xGridSize-4), 2+rand.nextInt(this.yGridSize-4))));
					break;
				}
			}
		}
	}
	
	/**
	 * @return the grid
	 */
	public ArrayList<Robot> getGrid() {
		return grid;
	}

	/**
	 * @param grid the grid to set
	 */
	public void setGrid(ArrayList<Robot> grid) {
		this.grid = grid;
	}

	/**
	 * @return the xGridSize
	 */
	public int getxGridSize() {
		return xGridSize;
	}

	/**
	 * @param xGridSize the xGridSize to set
	 */
	public void setxGridSize(int xGridSize) {
		this.xGridSize = xGridSize;
	}

	/**
	 * @return the yGridSize
	 */
	public int getyGridSize() {
		return yGridSize;
	}

	/**
	 * @param yGridSize the yGridSize to set
	 */
	public void setyGridSize(int yGridSize) {
		this.yGridSize = yGridSize;
	}

	/**
	 * @return the num_robots
	 */
	public int getNum_robots() {
		return num_robots;
	}

	/**
	 * @param num_robots the num_robots to set
	 */
	public void setNum_robots(int num_robots) {
		this.num_robots = num_robots;
	}

	/**
	 * @return the gridResolution
	 */
	public int getGridResolution() {
		return gridResolution;
	}

	/**
	 * @param gridResolution the gridResolution to set
	 */
	public void setGridResolution(int gridResolution) {
		this.gridResolution = gridResolution;
	}

}
