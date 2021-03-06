public class Part2 {
	public float cgRatio(String dna){
		String upper = dna.toUpperCase();
		float countc = 0;
		float countg = 0;
		float length = upper.length();
		int indexc = upper.indexOf("C");
		int indexg = upper.indexOf("G");
		while(indexc != -1){
			countc ++;
			indexc = upper.indexOf("C",indexc + 1);
		}
		while(indexg != -1){
			countg ++ ;
			indexg = upper.indexOf("G",indexg + 1);
		}
		//System.out.print(upper.length());
		return (countc + countg)/length;
		
	}
	
	public int countCTG(String dna){
		String upper = dna.toUpperCase();
		int count = 0;
		int index = upper.indexOf("CTG");
		while(index != -1){
			count ++;
			index = upper.indexOf("CTG",index+1);
		}
		return count;
	}
	
	public void testCgRatio(){
		String temp = "ATCCCCCGGGGAAGG";
		System.out.print(temp + "\n");
		System.out.print(cgRatio(temp)+"\n\n");
	}
	
	public void testCountCTG(){
		String dna = "ctgctgaaacccacacctgCtGctgctggctg";
		System.out.print(dna+"\n");
		System.out.print(countCTG(dna));
	}
}
