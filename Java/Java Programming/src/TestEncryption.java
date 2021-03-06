import java.util.Arrays;
import java.util.HashSet;

import edu.duke.*;

public class TestEncryption {

	public static void main(String[] args) {
		//CaesarCracker CCrackerNoKey = new CaesarCracker();
//		FileResource fr0 = new FileResource("data/Encryption/titus-small_key5.txt");
//		//cannot use toString() here, must use asString()
//		String s0 = fr0.asString();
//		System.out.println(CCrackerNoKey.decrypt(s0));
//		
//		//"rome" , which is [17,14,12,4]
//		int[] ROME = new int[]{17,14,12,4};
//		VigenereCipher vcROME = new VigenereCipher(ROME);
//		FileResource fr1 = new FileResource("data/Encryption/titus-small.txt");
//		String s1 = fr1.asString();
//		//calling its method toString
//		System.out.println("keys being used here");
//		System.out.println(vcROME);
//		System.out.println("encrypted message....");
//		System.out.println(vcROME.encrypt(s1));
//		System.out.println("decrypted message ....");
//		System.out.println(vcROME.decrypt(vcROME.encrypt(s1)));
//		
//		
		VigenereBreaker vb = new VigenereBreaker();
//		System.out.println(vb.sliceString("abcdefghijklm", 0, 3));
//		System.out.println(vb.sliceString("abcdefghijklm", 1, 3));
//		System.out.println(vb.sliceString("abcdefghijklm", 2, 3));
//		System.out.println(vb.sliceString("abcdefghijklm", 0, 4));
//		System.out.println(vb.sliceString("abcdefghijklm", 1, 4));
//		System.out.println(vb.sliceString("abcdefghijklm", 2, 4));
//		System.out.println(vb.sliceString("abcdefghijklm", 4, 5));
//		
//		FileResource fr2 = new FileResource("data/Encryption/athens_keyflute.txt");
//		String s2 = fr2.asString();
//		System.out.println(Arrays.toString(vb.tryKeyLength(s2, 5, 'e')));
//		
		
        vb.breakVigenere();
//		
//		//first q 
//		FileResource fr3 = new FileResource("data/Encryption/secretmessage1.txt");
//		String s3 = fr3.asString();
//		//English => mostCommon is 'e'
//		System.out.println(Arrays.toString(vb.tryKeyLength(s3, 4,'e')));
		
		//vb.breakVigenere();	
		FileResource fr4 = new FileResource("data/Encryption/secretmessage2.txt");
		FileResource fr5 = new FileResource("data/Encryption/English");
		String s4 = fr4.asString();
		
		int keys[] = vb.tryKeyLength(s4,38,'e');
		VigenereCipher vc = new VigenereCipher(keys);
 		System.out.println(vb.countWords(vc.decrypt(s4),vb.readDictionary(fr5)));

		
		
		
	}

}
