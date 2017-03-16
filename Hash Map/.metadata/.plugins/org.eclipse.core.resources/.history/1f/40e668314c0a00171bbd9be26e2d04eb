import edu.duke.*;
import java.io.*;
import java.util.*;

public class WordsInFiles {
	private HashMap<String, ArrayList<String>> occur ;
	
	public WordsInFiles(){
		occur =  new HashMap<String, ArrayList<String>>();
	}
	
	private void addWordsFromFile(File f){
		FileResource fr = new FileResource(f);
		for(String s: fr.words()){
			if(occur.containsKey(s)){ //in the map
				if(occur.get(s).indexOf(f.getName()) == -1){
					//file name not in the ArrayList
					//adding the filename to it
					occur.get(s).add(f.getName());
				}
			}else{//not in the map
				ArrayList<String> temp = new ArrayList<String>();
				temp.add(f.getName());
				occur.put(s, temp);
			}
		}
	}
	
	public void buildWordFileMap(){
		occur.clear();
		DirectoryResource dr = new DirectoryResource();
		for(File f: dr.selectedFiles()){
			addWordsFromFile(f);
		}
		
	}
	
	public int maxNumber(){
		int max = -1;
		for(String s : occur.keySet()){
			if(occur.get(s).size() > max){
				max = occur.get(s).size();
			}
		}
		return max;
	}
	
	public ArrayList<String> wordsInNumFiles(int number){
		ArrayList<String> temp = new ArrayList<String>();
		for(String s: occur.keySet()){
			if(occur.get(s).size() == number){
				temp.add(s);
			}
		}
		return temp;
	}
	
	public void  printFilesIn(String word){
		for(int i = 0 ; i< occur.get(word).size(); i++){
			System.out.println(occur.get(word).get(i));
		}
	}
	
	public void tester(){
		buildWordFileMap();
		int maxNum = maxNumber();
		for(String s: occur.keySet()){
			if(occur.get(s).size() == maxNum){
				System.out.println(s);
				printFilesIn(s);
			}
		}
	}
	
}
