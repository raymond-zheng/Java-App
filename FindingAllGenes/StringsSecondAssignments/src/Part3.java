
public class Part3 {
	Part1 temp = new Part1();
	//temp.findGene(String dna)
	
	public int countGenes(String dna){
		String cur = dna.toUpperCase();
		int count = 0;
		int curIndex = cur.indexOf("ATG");
		while(true){
			//.substring( ) can only take one parameter which is beingIndex
			String tobePrint = temp.findGene(dna.substring(curIndex));
			if(tobePrint.isEmpty()){
				break;
			}
			count ++;
			curIndex = curIndex + tobePrint.length();
		}
		return count;
	}
	
	public void testCountGenes(){
		//can be "TAA", "TAG", or "TGA"
		System.out.print("Test results for testCountGenes()\n");
		String dna = "atgcccaaaccctagcccatgtaaaatgcccaaaccctagtgtgacatgcccaaaccctagcatgcccaaaccctagcatgcccaaaccctag";
		//ATGTAAGATGCCCTAGT
		System.out.print(dna + "\n" + "# of DNA " + countGenes(dna) + "\n");
		
		dna = "atgagtatgtagatggta";
		System.out.print(dna + "\n" + "# of DNA " + countGenes(dna) + "\n");
		
		dna = "ATGTAAGATGCCCTAGT";
		System.out.print(dna + "\n" + "# of DNA " + countGenes(dna) + "\n");
		
		dna = "atgatgatgtgacccccccccccccc";
		System.out.print(dna + "\n" + "# of DNA " + countGenes(dna) + "\n");
	}
}
