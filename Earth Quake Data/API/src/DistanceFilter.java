
public class DistanceFilter implements Filter {
	
	private Location location;
	private double maxDistance;
	
	public DistanceFilter(Location loc, double maxDis){
		location = loc;
		maxDistance = maxDis;
	}
	
	@Override
	public boolean satisfies(QuakeEntry qe) {
		return (location.distanceTo(qe.getLocation()) <= maxDistance);
	}

	@Override
	public String getName() {
		return "DistanceFilter(" + location + " , " + maxDistance + ")";
	}
	
}
