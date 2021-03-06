import edu.duke.*;

public class Part3 {
	Part2 temp2 = new Part2();
	Part1 temp1 = new Part1();
	//part1.getAllGenes(dna) will return a storage
	public void  processGenes(StorageResource sr){
		StorageResource sr2 = sr;
		int longerThan9 = 0;
		int highCgRatio = 0;
		int longerThan60 = 0;
		String l = "";
		System.out.print("The strings that with length no longer than 9 : \n");
		for(String s: sr.data()){
			if(s.length() <= 9){
				System.out.print(s + "\n");
			}else{
				longerThan9 ++;
			}
			if(s.length() >= l.length()){
				l = s;
			}
			if(s.length() > 60){
				longerThan60++;
			}
		}
		
		System.out.print("There are " + longerThan60 + " DNAs that are longer than 60 \n");
		
		System.out.print("There are " + longerThan9 + " strings that have length greater than 9 \n");
		
		System.out.print("\n The strings that have c-g-ratios greater than 0.35 : \n");
		for(String s:sr2.data() ){
			if(temp2.cgRatio(s) > 0.35){
				System.out.print(s + "\n");
				highCgRatio ++;
			}
		}
		System.out.print("The number of strings that have c-g-ratios higher than 0.35 is " + highCgRatio +"\n");
		System.out.print("The longest string is " + l + "\n");
		System.out.print("The longest string has length " + l.length() + "\n");
	}
	
	public void testProcessGenes(){
		FileResource fr = new FileResource("brca1line.fa");
		String dna = fr.asString();
		System.out.print(dna);
		StorageResource storage = temp1.getAllGenes(dna);
		processGenes(storage);
	}
	
	public void forQuiz(){
		FileResource fr = new FileResource("GRch38dnapart.fa");
		String dna = fr.asString();
		int count = 0;
		StorageResource temp = temp1.getAllGenes(dna);
		for(String s: temp.data()){
			if(s != ""){
				count ++;
			}
		}
		System.out.print(count + "\n\n");
		
		StorageResource storage = temp1.getAllGenes(dna);
		processGenes(storage);
		
		Part2 part2 = new Part2();
		System.out.print("There are "+ part2.countCTG(dna) + " CTGs in the string \n");
		
		
	}

}
