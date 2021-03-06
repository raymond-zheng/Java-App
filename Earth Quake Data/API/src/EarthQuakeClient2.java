import java.util.*;
import edu.duke.*;

public class EarthQuakeClient2 {
    public EarthQuakeClient2() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) { 
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) { 
            if (f.satisfies(qe)) { 
                answer.add(qe); 
            } 
        } 
        
        return answer;
    } 

    public void quakesWithFilter() { 
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");

//        Filter f1 = new MagnitudeFilter(4.0,5.0);
//        Filter f2 = new DepthFilter(-35000.0,-12000.0);
//        Filter f = new MinMagFilter(4.0); 
//        ArrayList<QuakeEntry> m6 = filter(list,f1);
//        ArrayList<QuakeEntry> m7  = filter(m6, f2); 
        Location japan = new Location(35.42, 139.43);
        Filter f1 = new DistanceFilter(japan,10000000);
        Filter f2 = new PhraseFilter("end","Japan");
        ArrayList<QuakeEntry> m6 = filter(list,f1);
        ArrayList<QuakeEntry> m7  = filter(m6, f2);
        for (QuakeEntry qe: m7) { 
            System.out.println(qe);
        } 
    }

    public void testMatchAllFilter(){
    	 EarthQuakeParser parser = new EarthQuakeParser(); 
         String source = "data/nov20quakedatasmall.atom";
         ArrayList<QuakeEntry> list  = parser.read(source);  
         System.out.println("read data for "+list.size()+" quakes");
//         for(QuakeEntry qe: list){
//        	 System.out.println(qe);
//         }
         
         MatchAllFilter maf = new MatchAllFilter();
         MagnitudeFilter mf = new MagnitudeFilter(0.0,2.0);
         DepthFilter df = new DepthFilter(-100000.0,-10000.0);
         PhraseFilter pf = new PhraseFilter("any","a");
         maf.addFilter(mf);
         maf.addFilter(df);
         maf.addFilter(pf);
         
         ArrayList<QuakeEntry> result = filter(list, maf);
         
         for(QuakeEntry qe: result){
        	 System.out.println(qe);
         }
         System.out.println(maf.getName());
    }
    
    public void testMatchAllFilter2(){
    	 EarthQuakeParser parser = new EarthQuakeParser(); 
         String source = "data/nov20quakedatasmall.atom";
         ArrayList<QuakeEntry> list  = parser.read(source);  
         System.out.println("read data for "+list.size()+" quakes");
         
         MatchAllFilter maf = new MatchAllFilter();
         MagnitudeFilter mf = new MagnitudeFilter(0.0,3.0);
         Location loc = new Location (36.1314, -95.9372);
         DistanceFilter disf = new DistanceFilter(loc, 10000000);
         PhraseFilter pf = new PhraseFilter("any","Ca");
         maf.addFilter(mf);
         maf.addFilter(disf);
         maf.addFilter(pf);
         
         ArrayList<QuakeEntry> result = filter(list, maf);
         
         for(QuakeEntry qe: result){
        	 System.out.println(qe);
         }
         System.out.println("There are " + result.size() + " results");
    }
     
    public void attempt(){
    	 EarthQuakeParser parser = new EarthQuakeParser(); 
         String source = "data/nov20quakedata.atom";
         ArrayList<QuakeEntry> list  = parser.read(source);  
         System.out.println("read data for "+list.size()+" quakes");
       
         //TODO
         MatchAllFilter maf = new MatchAllFilter();
         MagnitudeFilter mf = new MagnitudeFilter(0.0,5.0);
         //DepthFilter df = new DepthFilter  ( -180000.0 ,-30000.0);
         PhraseFilter pf = new PhraseFilter("any","e");
         Location loc = new Location(55.7308, 9.1153);
         DistanceFilter df = new DistanceFilter(loc,3000000 );
         //PhraseFilter pf = new PhraseFilter("end","a");
         maf.addFilter(mf);
         maf.addFilter(pf);
         maf.addFilter(df);
         
         ArrayList<QuakeEntry> result = filter(list, maf);
         
         for(QuakeEntry qe: result){
        	 System.out.println(qe);
         }
         System.out.println("There are " + result.size() + " results");
         
    }
    
    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "../data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: "+list.size());
    }

    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }
    }

}
