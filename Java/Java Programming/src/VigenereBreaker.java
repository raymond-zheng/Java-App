import java.util.*;
import edu.duke.*;

public class VigenereBreaker {
	//OK..
    public String sliceString(String message, int whichSlice, int totalSlices) {
        //REPLACE WITH YOUR CODE
    	String temp = "";
    	for(int i = whichSlice; i < message.length(); i += totalSlices){
    		temp += message.substring(i, i+1);
    	}
    	
        return temp;
    }

    //OK..
    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        //by default the most common character is 'e'..
        CaesarCracker cc = new CaesarCracker(mostCommon);
        for(int i = 0; i < klength; i ++){
        	String temp = sliceString(encrypted, i, klength);
        	key[i] = cc.getKey(temp);
        }
        //WRITE YOUR CODE HERE
        return key;
    }

    //OK..
    public void breakVigenere () {
        //WRITE YOUR CODE HERE
    	//default constructor.. 
    	FileResource fr = new FileResource();
    	String s = fr.asString();
    	//using 5 as klength and 'e' as most common for now
    	int key[] = tryKeyLength(s,4,'e');
    	VigenereCipher vc = new VigenereCipher(key);
    	System.out.println(vc.decrypt(s));
    }
    
}
