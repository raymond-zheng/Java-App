import edu.duke.*;

public class CaesarBreaker {
	//countLetters, maxIndex, and decrypt
	//we assume that e is the most frequent letter in any text,
	//and e's index is 4 , "abcde"
	CaesarCipher cc = new CaesarCipher();
	//String message = cc.encrypt(encrypted, 26 - key);
	String alphabet = "abcdefghijklmnopqrstuvwxyz";
	
	public int[] countLetters(String s){
		int[] counts = new int[26];
		for(int i = 0; i < s.length(); i++){
			int idx = alphabet.indexOf(Character.toLowerCase((s.charAt(i))));
			if(idx != -1){
				counts[idx] += 1;
			}
		}
		return counts;
	};
	
	public int maxIndex(int[] a){
		int maxIdx = -1;
		int max = 0;
		for(int i = 0; i<a.length;i++){
			if(a[i] > max){
				maxIdx = i;
				/* plz do not forget this important line */
				max = a[i];
			}
		}
		return maxIdx;
	}
	
	/* works */
	public String decrypt(String encryptedMsg, int encryptionKey){
		int dKey = 26 - encryptionKey;
		return cc.encrypt(encryptedMsg, dKey);
	}
	
	/* test method */
	public void testDecrypt(){
		FileResource fr = new FileResource();
		String wholeFileString = fr.asString();
		wholeFileString = cc.encrypt(wholeFileString, 8);
		System.out.println("The decrypted message is "+ decrypt(wholeFileString,8));
	}
	
	/*This method should return a new String that is every other character 
		from message starting with the start position
	 */
	/* works */
	public String halfOfString(String message, int start){
		String newS = "";
		for(int i = start; i < message.length(); i += 2){
			newS += message.substring(i,i+1);
		}
		return newS;
	}
	
	/* test method */
	public void testHalfOfString(){
		String test1 = "hello my name is Raymond";
		String test2 = "hello my name is Edmond";
		String test3 = "abcd abcd abcd abcd abcd";
		System.out.println(halfOfString(test1,0) +  "\n" +halfOfString(test1,1) );
		System.out.println(halfOfString(test2,3) + "\n" + halfOfString(test2,4));
		System.out.println(halfOfString(test3,4) + "\n" + halfOfString(test3,5));
	}
	
	/* this method assumes that 'e' is the most frequent letter 
	 * and based on that to return an integer DecryptionKey
	 */
	public int getKey(String s){
		int[] counts = countLetters(s);
		int maxIdx = maxIndex(counts);
		int dKey = maxIdx - 4 ;
		if(maxIdx < 4){
			dKey = 26-(4 - maxIdx);
		}
		return dKey;
	}
	
	public void decryptTwoKeys(String encrypted){
		String one = halfOfString(encrypted,0);
		//System.out.println("first string : " + one);
		String two = halfOfString(encrypted,1);
		//System.out.println("second string : " + two);
		/* get the decryption key of one; */
		int key1 = getKey(one);
		/* get the decryption key of two */
		int key2 = getKey(two);
		String decryptedOne = cc.encrypt(one, 26-key1);
		String decryptedTwo = cc.encrypt(two, 26-key2);
		StringBuilder sb = new StringBuilder(encrypted);
		int length1 = decryptedOne.length();
		int length2 = decryptedTwo.length();
		for(int i = 0; i < length1 ; i++){
			sb.setCharAt(2*i, decryptedOne.charAt(i));
		};
		
		for(int i = 0; i < length2; i ++){
			sb.setCharAt(2*i+1, decryptedTwo.charAt(i));
		};
		
//		for(int i = 0; i < one.length(); i++){
//			if(i % 1 == 0){ /* even index */ 
//				sb.setCharAt(curOne, decryptedOne.charAt(curOne));
//				curOne += 1;
//			}else{ /* odd index */
//				sb.setCharAt(curTwo,decryptedTwo.charAt(curTwo) );
//				curTwo += 1;
//			}
//		}
		String result =  sb.toString();
		System.out.println("The two keys being used are Key1 : " + key1 + " , Key2 : "+ key2);
		System.out.println("The decrypted message is " + result);
	}
	
	
	
}