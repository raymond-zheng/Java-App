public class CaesarCipher {
	private String alphabet;
	private String shiftedAlphabet;
	private int mainKey;
	
	public  CaesarCipher(int key){
		mainKey = key;
		alphabet = "abcdefghijklmnopqrstuvwxyz" ;
		shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0,key);
	}
	
	public String encrypt(String input){
		StringBuilder sb = new StringBuilder(input);
		for(int i = 0; i < input.length(); i++){
			int idx = alphabet.indexOf(Character.toLowerCase(sb.charAt(i)));
			if(idx != -1){ /* it is an alphabet letter, avoiding index out of bound */
				if(Character.isUpperCase(sb.charAt(i))){
					sb.setCharAt(i, Character.toUpperCase(shiftedAlphabet.charAt(idx)));
				}else if(Character.isLowerCase(sb.charAt(i))){
					sb.setCharAt(i, shiftedAlphabet.charAt(idx));
				}
			}
		}
		return sb.toString();
	}
	
	/* a method can 'utilize' the class that it belongs to */
	public String decrypt(String input){
		CaesarCipher cc = new CaesarCipher(26-mainKey);
		return cc.encrypt(input);
	}
	
	/* note for myself: use this implementation to specify to println(currentClass) */
	//	public String toString() { 	
	//        return phrase + " is " + word + " repeated";
	//    }
}
