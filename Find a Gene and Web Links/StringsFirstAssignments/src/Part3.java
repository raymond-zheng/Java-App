
public class Part3 {
	public boolean twoOccurrences(String a, String b){
		int first = b.indexOf(a);
		if(first == -1){
			return false;
		}
		int second = b.indexOf(a,first+1);
		if(second == -1){
			return false;
		}
		return true;
	}
	
	public void testing(){
		String a = "a",  b = "I love hambergur a lot";
		System.out.print(a + "     " +b+"\n");
		System.out.print(twoOccurrences(a,b)+"\n\n");
		
		a = "hello";  b = "hellohello lets go";
		System.out.print(a + "     " +b+"\n");
		System.out.print(twoOccurrences(a,b)+"\n\n");
		
		a = "I love you";  b = "IloveyouIloveyou";
		System.out.print(a + "     " +b+"\n");
		System.out.print(twoOccurrences(a,b)+"\n\n");
		
		a = "boring";  b = "boringboring";
		System.out.print(a + "     " +b+"\n");
		System.out.print(twoOccurrences(a,b)+"\n\n");
		
		a = "wow";  b = "wowwow";
		System.out.print(a + "     " +b+"\n");
		System.out.print(twoOccurrences(a,b)+"\n\n");
		
		a = "wow";  b = "wowow";
		System.out.print(a + "     " +b+"\n");
		System.out.print(twoOccurrences(a,b)+"\n\n");
		
		a = "an" ; b = "banana";
		System.out.print("The part of the string after " + a + " in " + b  +" is " + lastPart(a,b) + "\n\n");
		
		a = "k" ; b = "keightknight";
		System.out.print("The part of the string after " + a + " in " + b  +" is " + lastPart(a,b) + "\n\n");
		
		a = "raymond" ; b = "raymondisveryhandsome";
		System.out.print("The part of the string after " + a + " in " + b  +" is " + lastPart(a,b) + "\n\n");
		
		a = "zzz" ; b = "whatthefuckisthis";
		System.out.print("The part of the string after " + a + " in " + b  +" is " + lastPart(a,b) + "\n\n");
	}
	
	public String lastPart(String stringa, String stringb ){
		if(stringb.indexOf(stringa) == -1){
			return stringb;
		}else{
			//.substring(a,b) includes a and excludes b 
			return stringb.substring(stringb.indexOf(stringa)+stringa.length(), stringb.length());
		}
	}
}


