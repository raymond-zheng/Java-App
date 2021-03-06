import java.util.*;
import edu.duke.*;

public class EarthQuakeClient {
    public EarthQuakeClient() {
        // TODO Auto-generated constructor stub
    }

    //done 
    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData,
    double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData){
        	//larger than => strictly larger than
        	if(qe.getMagnitude() > magMin){
        		answer.add(qe);
        	}
        }
        return answer;
    }


    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData,
    double distMax,
    Location from) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe: quakeData){
        	//get the Location of the QuakeEntry object
        	Location loc = qe.getLocation();
        	if(loc.distanceTo(from) < distMax){
        		answer.add(qe);
        	}
        }
        return answer;
    }

    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }

    }

    //done 
    public void bigQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        ArrayList<QuakeEntry> listBig = filterByMagnitude(list,5.0);
        for(QuakeEntry qe: listBig){
        	System.out.println(qe);
        }
    }

    //done
    public void closeToMe(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        

        // This location is Durham, NC
        //Location city = new Location(35.988, -78.907);

        // This location is Bridgeport, CA
        Location city =  new Location(38.17, -118.82);
        //need to convert the 100km to 1000000
        ArrayList<QuakeEntry> listClose = filterByDistanceFrom(list,1000000,city);
        for(QuakeEntry qe: listClose){
        	System.out.println(qe.getLocation().distanceTo(city) +" " +qe.getInfo() );
        	//System.out.println("hello");
        }
    }
    
    //exclusive,done
    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData,
    		double minDepth, double maxDepth){
    	ArrayList<QuakeEntry> list = new ArrayList<QuakeEntry>();
    	for(QuakeEntry qe: quakeData){
    		double qeDepth = qe.getDepth();
    		if(minDepth < qeDepth && qeDepth < maxDepth){
    			list.add(qe);
    		}
    	}
    	return list;
    }
    
    //done
    public void quakesOfDepth(){
    	EarthQuakeParser parser = new EarthQuakeParser();
    	String source = "data/nov20quakedata.atom";
    	ArrayList<QuakeEntry> list  = parser.read(source);
    	System.out.println("size before: " + list.size());
    	ArrayList<QuakeEntry> listOfDepth = filterByDepth(list,  -4000.0 , -2000.0);
    	System.out.println("size after: " + listOfDepth.size());
    	for(QuakeEntry qe: listOfDepth){
    		System.out.println(qe);
    	}
    	//System.out.println(("hello").equals("hello"));
    	System.out.println("Found " + listOfDepth.size() +  " quakes that match that criteria");
    }
    
    //done
    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData, 
    		String where, String phrase){
    	ArrayList<QuakeEntry> newList = new ArrayList<QuakeEntry>();
    	for(QuakeEntry qe:quakeData){
    		String qeTitle = qe.getInfo();
    		if(where.equals("start")){
    			if(qeTitle.indexOf(phrase) == 0){
    				newList.add(qe);
    			}
    		}else if(where.equals("end")){
    			if(qeTitle.indexOf(phrase) != -1 && qeTitle.indexOf(phrase) + phrase.length() == qeTitle.length()){
    				newList.add(qe);
    			}
    		}else if(where.equals("any")){
    			if(qeTitle.indexOf(phrase) != -1){
    				newList.add(qe);
    			}
    		}
    	}
    	return newList;
    }
    
    //any, start, end work
    public void  quakesByPhrase(){
    	EarthQuakeParser parser = new EarthQuakeParser();
    	String source = "data/nov20quakedata.atom";
    	ArrayList<QuakeEntry> list  = parser.read(source);
    	System.out.println("size before: " + list.size());
    	ArrayList<QuakeEntry> newList = filterByPhrase(list,"any","Can");
    	//int i = 0;
    	for(QuakeEntry qe: newList){
    		//i ++ ;
    		//System.out.println(i);
    		System.out.println(qe);	
    	}
    	System.out.println("There are " + newList.size() + " quakes in quakesByPhrase");
    	//System.out.println("Found " + newList.size() + " quakes that match California at end" );
    }
 
    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
        int i = 0;
        for (QuakeEntry qe : list) {
            System.out.println(qe);
            i += 1;
        }
        System.out.println(i);
    }
    
}
