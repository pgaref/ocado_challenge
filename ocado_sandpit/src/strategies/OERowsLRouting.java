package strategies;

import navigation.Grid;
import navigation.Robot;

public class OERowsLRouting extends DumbLRouting {

	@Override
	public void route(Grid grid) {
		for (Robot r : grid.getGrid()) {
			if (r.currentLocation.x != r.destination.x) {
				if (r.currentLocation.x > r.destination.x)
					if (r.currentLocation.y %2 == 1) {
						r.nextLocation = r.currentLocation.left();
					} else {
						validVertical(r);
					}
				else
					if (r.currentLocation.y %2 == 0) {
						r.nextLocation = r.currentLocation.right();
					} else {
						validVertical(r);
					}
			} else {
				if (r.currentLocation.y > r.destination.y)
					if (r.currentLocation.x %2 == 1) {
						r.nextLocation = r.currentLocation.up();
					} else {
						validHorizontal(r);
					}
				else
					if (r.currentLocation.x %2 == 0) {
						r.nextLocation = r.currentLocation.down();
					} else {
						validHorizontal(r);
					}
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
}
