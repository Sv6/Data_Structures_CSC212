public class VehicleHiringManager {

	private LocatorMap<String> lM;
	
	public VehicleHiringManager() {
		lM = new TreeLocatorMap<String>();
	}

	// Returns the locator map.
	public LocatorMap<String> getLocatorMap() {
		return lM;
	}

	// Sets the locator map.
	public void setLocatorMap(LocatorMap<String> locatorMap) {
		lM = locatorMap;
	}

	// Inserts the vehicle id at location loc if it does not exist and returns true.
	// If id already exists, the method returns false.
	public boolean addVehicle(String id, Location loc) {
		return lM.add(id, loc).first;
	}

	// Moves the vehicle id to location loc if id exists and returns true. If id not
	// exist, the method returns false.
	public boolean moveVehicle(String id, Location loc) {
		return lM.move(id, loc).first;
	}

	// Removes the vehicle id if it exists and returns true. If id does not exist,
	// the method returns false.
	public boolean removeVehicle(String id) {
		return lM.remove(id).first;
	}

	// Returns the location of vehicle id if it exists, null otherwise.
	public Location getVehicleLoc(String id) {
		if(lM.getLoc(id) != null)
			return lM.getLoc(id).first;
			
			return null;
	}

	// Returns all vehicles located within a square of side 2*r centered at loc
	// (inclusive of the boundaries).
	public List<String> getVehiclesInRange(Location loc, int r) {
		int right = loc.x + r; int up = loc.y + r;
		int left = loc.x - r; int down = loc.y - r;
		
		return lM.getInRange(new Location(left,down), new Location(right,up)).first;
	}
}
