
public class runWordPaly {

	public static void main(String[] args) {
		WordPlay wordplay = new WordPlay();
		wordplay.testIsVowel();
		wordplay.testReplaceVowels();
		wordplay.testEmphasize();
		CaesarCipher caeserCipher = new CaesarCipher();
		caeserCipher.testEncrypt();
		caeserCipher.testEncryptTwoKeys();
	}

}
