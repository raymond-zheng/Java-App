import edu.duke.*;
public class RunTheseFunctions {

	public static void main(String[] args) {
		TestCaesarCipherTwo tcct = new TestCaesarCipherTwo();
		/* 1 */
		CaesarCipher cc15 = new CaesarCipher(15);
		System.out.println("1 , " + cc15.encrypt("Can you imagine life WITHOUT the internet AND computers in your pocket?"));
		/* 2 */
		CaesarCipherTwo cct21And8 = new CaesarCipherTwo(21,8);
		System.out.println("2, " + cct21And8.encrypt("Can you imagine life WITHOUT the internet AND computers in your pocket?"));
		/* 3 */
		/* 4 */
		/* 5 */
		/* 6 */
		CaesarCipherTwo cct14And24 = new CaesarCipherTwo(14,24);
		System.out.println("6 , " + cct14And24.decrypt("Hfs cpwewloj loks cd Hoto kyg Cyy."));
		/* 7 */
		tcct.breakCaesarCipher("Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx!");
		/* 8 , 9  */
		FileResource fr = new FileResource();
		String input = fr.asString();
		tcct.breakCaesarCipher(input);
	}

}
