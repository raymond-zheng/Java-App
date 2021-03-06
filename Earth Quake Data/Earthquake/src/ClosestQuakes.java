
/**
 * Find N-closest quakes
 * 
 * @author Duke Software/Learn to Program
 * @version 1.0, November 2015
 */

import java.util.*;

//done 
public class ClosestQuakes {
    public ArrayList<QuakeEntry> getClosest(ArrayList<QuakeEntry> quakeData, Location current, int howMany) {
        ArrayList<QuakeEntry> ret = new ArrayList<QuakeEntry>();
        // TO DO
        //make a copy to the array list , in order to delete element
        ArrayList<QuakeEntry> temp = quakeData;
        for(int i = 0; i < howMany; i++){
        	int minIndex = 0;
        	//cannot use for(QuakeEntry qe:temp){ } here
        	//since minIndex is initial to 0 , no need to loop from 0 , just from 1 
        	for(int j = 1; j < temp.size(); j ++){
        		Location loc = temp.get(j).getLocation();
        		if(loc.distanceTo(current) < current.distanceTo(temp.get(minIndex).getLocation())){
        			minIndex = j;
        		}
        	}
        	ret.add(temp.get(minIndex));
        	temp.remove(minIndex);
        }
        return ret;
    }

    public void findClosestQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size());

        Location jakarta  = new Location(-6.211,106.845);

        ArrayList<QuakeEntry> close = getClosest(list,jakarta,3);
        for(int k=0; k < close.size(); k++){
            QuakeEntry entry = close.get(k);
            double distanceInMeters = jakarta.distanceTo(entry.getLocation());
            System.out.printf("%4.2f\t %s\n", distanceInMeters/1000,entry);
        }
        System.out.println("number found: "+close.size());
    }
    
}
