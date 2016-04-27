package strategies;

import navigation.Grid;
import navigation.Robot;

public class DumbLRouting implements RoutingStrategy {

	@Override
	public void route(Grid grid) {
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
			r.was_blocked = r.blocked;
		}

		for (Robot r : grid.getGrid()) {
			r.blocked = false;
			for (Robot o : grid.getGrid()) {
				if (r == o) continue;
				if (o.currentLocation.equals(r.nextLocation) || o.nextLocation.equals(r.nextLocation)) {
					if (r.nextLocation.y != r.currentLocation.y || r.nextLocation.equals(o.currentLocation)) {
						r.blocked = true;
						break;
					} else {

						System.out.println("Not blocked: " + r.currentLocation + " --- " + r.nextLocation);
					}
				}
			}
		}
		
		for (Robot r : grid.getGrid()) {
			if (r.blocked && r.was_blocked) {
				if (r.nextLocation.x == r.currentLocation.x)
					validHorizontal(r);
				else
					validVertical(r);
			}
		}
		
		for (Robot r : grid.getGrid()) {
			r.blocked = false;
			for (Robot o : grid.getGrid()) {
				if (r == o) continue;
				if (o.currentLocation.equals(r.nextLocation) || o.nextLocation.equals(r.nextLocation)) {
					if (r.nextLocation.y != r.currentLocation.y || r.nextLocation.equals(o.currentLocation))
					{
						System.out.println("Blocked: " + r.currentLocation + " --- " + r.nextLocation);
						r.blocked = true;
						break;
					}
				}
			}
		}
	}

	protected void validVertical(Robot r) {
		if (r.currentLocation.x %2 == 1)
			r.nextLocation = r.currentLocation.up();
		else
			r.nextLocation = r.currentLocation.down();
	}

	protected void validHorizontal(Robot r) {
		if (r.currentLocation.y %2 == 1)
			r.nextLocation = r.currentLocation.left();
		else
			r.nextLocation = r.currentLocation.right();
	}
	
}
