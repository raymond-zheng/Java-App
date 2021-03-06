import java.util.*;
import edu.duke.*; 

public class WordFrequencies {
	//The kth integer of myFreqs represents the frequency of the kth word in myWords
	private ArrayList<String> myWords;
	private ArrayList<Integer> myFreqs;
	
	public WordFrequencies(){
		myWords = new ArrayList<String>();
		myFreqs = new ArrayList<Integer>();
	}
	
	//non case-sensitive 
	public void findUnique(){
		myWords.clear();
		myFreqs.clear();
		FileResource fr = new FileResource();
		for(String s: fr.words()){
			int idx = myWords.indexOf(s.toLowerCase());
			if(idx == -1){ 
				//avoid duplicate
				myWords.add(s.toLowerCase());
				myFreqs.add(1);
			}else{
				int count = myFreqs.get(idx);
				myFreqs.set(idx,count + 1);
			}
		}
	}
	
	public void tester(){ //good 
		findUnique();
		System.out.println("Number of unique words: " + myWords.size() );
		for(int i = 0; i < myWords.size(); i++){
			System.out.println(myFreqs.get(i) + "\t" + myWords.get(i));
		}
		System.out.println("The word that occurs most often and its count are: "+ myWords.get(findIndexOfMax()) +" " +myFreqs.get(findIndexOfMax() ));
	}
	
	//If there is a tie, return the first one
	public int findIndexOfMax(){
		 int max = -1, maxIdx = -1;
		 for(int i = 0; i < myFreqs.size(); i++){
			 if(myFreqs.get(i) > max){ //must be strictly greater than
				 max = myFreqs.get(i);
				 maxIdx = i;
			 }	
		 }
		 return maxIdx;
	}
	
}
