import java.util.Comparator;

public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry> {

	@Override
	public int compare(QuakeEntry q1, QuakeEntry q2) {
		// TODO Auto-generated method stub
		String titleLast1 = q1.getInfo().substring(q1.getInfo().lastIndexOf(" ")+1);
		String titleLast2 = q2.getInfo().substring(q2.getInfo().lastIndexOf(" ")+1);
		if(titleLast1.compareTo(titleLast2) != 0){
			return titleLast1.compareTo(titleLast2);
		}
		return Double.compare(q1.getMagnitude(), q2.getMagnitude());
	}

}
