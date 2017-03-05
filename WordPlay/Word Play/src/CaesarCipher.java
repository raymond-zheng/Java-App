
public class CaesarCipher {
	/*Write the method encrypt that has two parameters, a String named input and 
	an int named key. This method returns a String that has been encrypted using 
	the Caesar Cipher algorithm explained in the videos. Assume that all the 
	alphabetic characters are uppercase letters. For example, the call*/
	public String encrypt(String input , int key){
		StringBuilder sb = new StringBuilder(input);
		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String shifted = alphabet.substring(key)  + alphabet.substring(0,key);
		for(int i = 0 ; i < input.length(); i ++){
			char upper = Character.toUpperCase(input.charAt(i));
			int idx =alphabet.indexOf(upper);
			if(idx != -1){
				if(Character.isUpperCase(input.charAt(i))){ //upper case
					sb.setCharAt(i, shifted.charAt(idx));
				}else{ //lower case
					sb.setCharAt(i,Character.toLowerCase(shifted.charAt(idx)));
				}
			}
		}
		return sb.toString() ;
	}
	
	/* test method */
	public void testEncrypt(){
		System.out.println("calling encrypt(“FIRST LEGION ATTACK EAST FLANK!”, 23) will return " + encrypt("FIRST LEGION ATTACK EAST FLANK!", 23));
		System.out.println("calling encrypt(“First Legion”, 23) will return "+encrypt("First Legion", 23));
		System.out.println("calling encrypt(“First Legion”, 17) will return "+encrypt("First Legion", 17));
		System.out.println("calling encrypt(“At noon be in the conference room with your hat on for a surprise party. YELL LOUD!”, 15) will return "+encrypt("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", 15));
	}
	
	public String encryptTwoKeys(String input, int key1, int key2){
		String first = encrypt(input,key1);
		String second = encrypt(input, key2);
		StringBuilder sb = new StringBuilder(first);
		for(int i = 1; i < sb.length(); i+= 2){
			sb.setCharAt(i, second.charAt(i));
		}
		return sb.toString();
	}
	
	/* test method */ 
	public void testEncryptTwoKeys(){
		//System.out.println("calling encryptTwoKeys(“First Legion”, 23, 17) will return " + encryptTwoKeys("First Legion", 23, 17));
		//System.out.println("calling encryptTwoKeys(“At noon be in the conference room with your hat on for a surprise party. YELL LOUD!”, 8, 21) will return " + encryptTwoKeys("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", 8, 21));
		String encrypted = "Top ncmy qkff vi vguv vbg ycpx";
		System.out.println(encryptTwoKeys(encrypted,26-2,26-20));
	}
	
}
