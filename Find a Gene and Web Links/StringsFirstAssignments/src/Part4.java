import edu.duke.*;

public class Part4{
	//lastIndexOf(s, num)
	public void youtubeUrl(){
		URLResource url = new URLResource("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
		for(String word: url.words()){
			String lowerCase = word.toLowerCase();
			int youtubeIndex = lowerCase.indexOf("youtube.com");
			if(youtubeIndex != -1){
				int begin = word.lastIndexOf("\"",youtubeIndex);
				int end = word.indexOf("\"",youtubeIndex);
				System.out.print(word.substring(begin, end) + "\n");
			}
		}
		
	}
}
