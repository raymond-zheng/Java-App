import java.util.*;
import edu.duke.*;

public class VigenereBreaker {
	

    public String sliceString(String message, int whichSlice, int totalSlices) {
    	String temp = "";
    	for(int i = whichSlice; i < message.length(); i += totalSlices){
    		temp += message.substring(i, i+1);
    	}
    	
        return temp;
    }


    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        CaesarCracker cc = new CaesarCracker(mostCommon);
        for(int i = 0; i < klength; i ++){
        	String temp = sliceString(encrypted, i, klength);
        	key[i] = cc.getKey(temp);
        }
        return key;
    }

  
    public void breakVigenere () {
    	FileResource fr = new FileResource("data/Encryption/secretmessage4.txt");
    	String s = fr.asString();
    	
    	HashMap<String, HashSet<String>> languagesDict = new HashMap<String, HashSet<String>> ( );
    	String[] lanS = {"Danish", "Dutch", "English", "French", "Italian", "Portuguese", "Spanish", "German"};
    	for(String lan : lanS){
    		FileResource lanfr = new FileResource("data/Encryption/" + lan );
    		languagesDict.put(lan, readDictionary(lanfr));
    	}
    	breakForAllLangs(s,languagesDict);
 
    }
    
    public HashSet<String> readDictionary(FileResource fr){
    	HashSet<String> hs = new HashSet<String>();
    	for(String s: fr.lines()){
    		String lower = s.toLowerCase();
    		if(!hs.contains(lower)){
    			hs.add(lower);
    		}
    	}
    	return hs;
    }
    

    public int countWords(String message, HashSet<String> dictionary){
    	String[] array = message.split("\\W");
    	int c = 0;
    	for(String s: array){
    		String word = s.toLowerCase();
    		if(dictionary.contains(word)){
    			c ++;
    		}
    	}
    	return c;
    }
    
    public String breakForLanguage(String encrypted, HashSet<String> dictionary){
    	int max = -1;
    	String realDecrypted = "";
    	char mostCommonChar = mostCommonCharIn(dictionary);
    	for(int i = 1; i <= 100; i++){
    		int[] keys = tryKeyLength(encrypted, i, mostCommonChar);
    		VigenereCipher vc = new VigenereCipher(keys);
    		String decrypted = vc.decrypt(encrypted);
    		int currentCount = countWords(decrypted,dictionary);
    		if(currentCount > max){
    			max = currentCount;
    			realDecrypted = decrypted;
    		}
    	}
   	
    	return realDecrypted;
    }
    
    public char mostCommonCharIn(HashSet<String> dictionary){
    	int count[] = new int[26];
    	String alphabet = "abcdefghijklmnopqrstuvwxyz";
    	for(String s: dictionary){
    		s = s.toLowerCase();
    		for(char c : s.toCharArray()){
    			int idx = alphabet.indexOf(c);
    			if(idx != -1){
    				count[idx] ++;
    			}
    		}
    	}	
    	int max = -1;
    	int maxIdx = -1;
    	for(int i =0 ; i < count.length; i ++){
    		if(count[i] > max){
    			max = count[i];
    			maxIdx = i;
    		}
    	}
    	return alphabet.charAt(maxIdx);
    }
    
    public void breakForAllLangs(String encrypted, HashMap<String, HashSet<String>> languages){
    	int max = -1;
    	String actualLanguage = "";
    	String actualDecrpyted = "";
    	for(String l : languages.keySet()){
    		HashSet<String> dict = languages.get(l);
    		String curDecrypted = breakForLanguage(encrypted,dict);
    		if(countWords(curDecrypted,dict) > max){
    			max = countWords(curDecrypted,dict);
    			actualLanguage = l;
    			actualDecrpyted = curDecrypted;
    		}
    	}
    	System.out.println(actualDecrpyted);
    	System.out.println(actualLanguage);
    }
    
    
    
    
    
}
