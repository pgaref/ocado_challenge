package Utils;

import java.awt.Point;

public class MyPoint extends Point{
	
	/**
	 * Avoid Imutatbility issues
	 */
	private static final long serialVersionUID = 1L;
	
	public MyPoint(int x, int y) {
		super(x,y);
	}

	public MyPoint left() {return new MyPoint(x-1, y);}
	public MyPoint right(){return new MyPoint(x+1, y);}
	public MyPoint up()   {return new MyPoint(x, y-1);}
	public MyPoint down() {return new MyPoint(x, y+1);}
}