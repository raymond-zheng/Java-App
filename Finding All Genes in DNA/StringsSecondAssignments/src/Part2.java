
public class Part2 {
	//return how many times stringa appears in stringb
	public int howMany(String a, String b){
		int count = 0;
		int cur = 0;
		while(true){
			cur = b.indexOf(a,cur);
			if(cur == -1){
				return count;
			}else{
				count ++;
				//without the below statement won't work
				cur += a.length();
			}
		}
	}
	
	//a method that test howMany() method
	//cannot directly print integers here 
	public void testHowMany(){
		System.out.print("Test results for howMany()\n");
		String a ="hi" ,b ="hiiiihiiihiiiihiiii"; 
		System.out.print(a + " appears " + howMany(a,b) + " times in " + b+"\n");
		
		a ="hi" ;b ="hihi"; 
		System.out.print(a + " appears " + howMany(a,b) + " times in " + b+"\n");
		
		a ="aa" ;b ="aaaaaaaaaa"; 
		System.out.print(a + " appears " + howMany(a,b) + " times in " + b+"\n");
		
		a ="letsgo" ;b ="raymondletsgo"; 
		System.out.print(a + " appears " + howMany(a,b) + " times in " + b+"\n");
	}
}
