package navigation;

import Utils.MyPoint;

public class Robot {
	public boolean blocked;
	public MyPoint currentLocation;
	public MyPoint nextLocation; 
	public MyPoint destination;
	public boolean goal;
	public boolean was_blocked;
	
	public Robot(MyPoint loc, MyPoint dest) {
		this.currentLocation = loc;
		this.nextLocation = loc;
		this.destination = dest;
	}
}