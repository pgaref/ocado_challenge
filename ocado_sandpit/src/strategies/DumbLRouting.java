package strategies;

import navigation.Grid;
import navigation.Robot;

public class DumbLRouting implements IRoutingStrategy {

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
					}
				}
			}
		}
	}
	
}
