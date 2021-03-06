import java.util.*;
import edu.duke.*;

public class LargestQuakes {
	//done
	public void findLargestQuakes(){
		EarthQuakeParser parser = new EarthQuakeParser();
    	String source = "data/nov20quakedata.atom";
    	ArrayList<QuakeEntry> list  = parser.read(source);
//    	for(QuakeEntry qe: list){
//    		System.out.println(qe);
//    	}
//    	System.out.println("There are  " + list.size() +" quake entries");
//    	System.out.println("The quake with max magnitude is ");
//    	System.out.println(list.get(indexOfLargest(list)));
    	ArrayList<QuakeEntry> listTwo = getLargest(list,50);
    	for(QuakeEntry qe : listTwo){
    		System.out.println(qe);
    	}
    	System.out.println("There are totally " + listTwo.size() + " entries return by getLargest" );
	}
	
	//getting the largest magnitude's index
	//done
	public int indexOfLargest(ArrayList<QuakeEntry> data){
		int maxIdx = 0;
		for(int i = 1; i < data.size() ; i++){
			if(data.get(i).getMagnitude() > data.get(maxIdx).getMagnitude()){
				maxIdx = i;
			}
		}
		return maxIdx;
	}
	
	//done
	public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData, int howMany){
		//call indexOfLarget, and if howMany > size, the ArrayList contains size entries
		//but desc order, with the largest one at index 0
		//not modifying the original entry
		int maxIdx = -1;
		ArrayList<QuakeEntry> list = quakeData;
		ArrayList<QuakeEntry> newList = new ArrayList<QuakeEntry>();
		if(howMany > list.size()){
			howMany = list.size();
		}
		for(int i = 0; i < howMany; i++){
			maxIdx = indexOfLargest(list);
			newList.add(list.get(maxIdx));
			list.remove(maxIdx);
		}
		return newList;
	}

}
