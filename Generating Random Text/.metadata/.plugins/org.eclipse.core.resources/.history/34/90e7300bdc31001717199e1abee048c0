import java.util.ArrayList;
import java.util.Random;

public class MarkovModel {
	private String myText;
    //used to generate a random number
	private Random myRandom;
	private int N;
	
	public MarkovModel(int n) {
		myRandom = new Random();
		N = n;
	}
	
	//define the behavior of randomness
	public void setRandom(int seed){
		myRandom = new Random(seed);
	}
	
	public void setTraining(String s){
		myText = s.trim();
	}
	
	public String getRandomText(int numChars){
		if (myText == null){
			return "";
		}
		StringBuilder sb = new StringBuilder();
		int idx = myRandom.nextInt(myText.length() - N);
		String key = myText.substring(idx, idx + N);
		sb.append(key);
		
		
		
		//already append N characters before the for loop
		for(int  i = 0 ; i < numChars - N  ; i ++){
			ArrayList<String> follows = getFollows(key);
			if(follows.size() == 0){
				break;
			}
			idx = myRandom.nextInt(follows.size());
			String temp = follows.get(idx);
			sb.append(temp);
			//don't use the following line ..
			//key = sb.substring(i-3,i+1);
			//but use the following line instead..
			key = key.substring(1) + temp;
		}
		
		return sb.toString();
	
	}
	
	//works
	public ArrayList<String> getFollows(String key){ 
		ArrayList<String> follows = new ArrayList<String>();
		int pos = 0;
		while(pos < myText.length()){
			int idx = myText.indexOf(key,pos);
			//key not found 
			if(idx == -1){
				break;
			}
			//out of bound
			if(idx + key.length() >= myText.length()){
				break;
			}
			follows.add(myText.substring(idx+key.length(), idx +key.length() +1));
			//for string "helloe" , indexOf(h) + "hello".length() == indexOf(e)
			pos = idx + key.length();
				
		}
		return follows;
	}
}
