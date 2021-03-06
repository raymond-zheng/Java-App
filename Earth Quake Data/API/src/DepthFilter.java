
public class DepthFilter implements Filter {
	
	private double minimum;
	private double maximum;
	
	public DepthFilter(double min, double max){
		minimum = min;
		maximum = max;
	}
	
	@Override
	public boolean satisfies(QuakeEntry qe) {
		return (qe.getDepth() <= maximum && qe.getDepth() >= minimum);
	}

	@Override
	public String getName() {
		return "DepthFilter(" + minimum + " , " + maximum + ")";
	}
}
