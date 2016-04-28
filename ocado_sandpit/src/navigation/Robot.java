package navigation;

import java.awt.Color;
import java.util.Random;

import Utils.MyPoint;

public class Robot {
	public boolean blocked;
	public MyPoint currentLocation;
	public MyPoint nextLocation; 
	public MyPoint destination;
	public boolean goal;
	public boolean was_blocked;
	public Color debugColour;
	public Color color;
	
	public int steps;
	public int optimalSteps;
	
	
	public Robot(MyPoint loc, MyPoint dest) {
		this.currentLocation = loc;
		this.nextLocation = loc;
		this.destination = dest;
		this.color = new Color(new Random().nextInt(256), new Random().nextInt(256), new Random().nextInt(256));
		this.calcOptSteps();
	}
	
	public void cleanSteps(){
		steps=0;
	}
	
	public void addStep(){
		steps++;
	}

	public void calcOptSteps(){
		optimalSteps =  Math.abs(currentLocation.x - destination.x) +  Math.abs(currentLocation.y - destination.y) ;
	}
	
	public int getOverhead(){
		return steps - optimalSteps ;
	}
	
}