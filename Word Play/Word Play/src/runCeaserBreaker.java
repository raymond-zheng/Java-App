import edu.duke.FileResource;

public class runCeaserBreaker {

	public static void main(String[] args) {
		CaesarBreaker cb = new CaesarBreaker();
		//cb.testDecrypt();
		//cb.testHalfOfString();
		//cb.decryptTwoKeys("Akag tjw Xibhr awoa aoee xakex znxag xwko");
		
		CaesarCipher cc = new CaesarCipher();
		//String temp = cc.encryptTwoKeys("This message contains a lof of eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee", 5,10);
		//System.out.println("encrypted is "+ temp);
		FileResource fr = new FileResource();
		String string = fr.asString();
		cb.decryptTwoKeys(string);
	}

}