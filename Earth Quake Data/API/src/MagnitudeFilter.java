
public class MagnitudeFilter implements Filter  {

	private double minimum;
	private double maximum;
	
	//constructor
	public MagnitudeFilter(double min, double max){
		maximum = max;
		minimum = min;
	}
	
	@Override
	public boolean satisfies(QuakeEntry qe) {
		return (qe.getMagnitude() >= minimum && qe.getMagnitude() <= maximum);
	}

	@Override
	public String getName() {
		return "MagnitudeFilter(" + minimum + " , " + maximum + ")";
	}
}
