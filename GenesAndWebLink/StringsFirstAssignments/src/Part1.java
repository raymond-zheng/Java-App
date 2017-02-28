//Finds a valid DNA in dna, a valid DNA begins with ATG and ends with TAA
public class Part1 {

	public String findSimpleGene(String dna){
		int start = dna.indexOf("ATG");
		if(start == -1){
			return "";
		}
		int stop = dna.indexOf("TAA",start+3);
		if(stop == -1){
			return "";
		}
		//a valid dna has the length multiple of 3 
		if((stop - start) % 3 == 0){
			return dna.substring(start, stop+3);
		}
		return "";
	}
	
	public void testSimpleGene(){
		//DNA with no “ATG”
		String dna = "AAATTTGGGAAATTTGGGTAACCCCCCC";
		System.out.print("DNA to be retrieved: " + dna + "\n");
		System.out.print("Result: " + findSimpleGene(dna) + "\n\n");
		
		//DNA with no “TAA”
		dna = "ATGGGGGATGTAGAATGTAGCCCCCCC";
		System.out.print("DNA to be retrieved: " + dna + "\n");
		System.out.print("Result: " + findSimpleGene(dna) + "\n\n");
		
		//DNA with no “ATG” or “TAA”
		dna = "AAAGGGTTTACACCCCCCC";
		System.out.print("DNA to be retrieved: " + dna + "\n");
		System.out.print("Result: " + findSimpleGene(dna) +"\n\n");
		
		//DNA with ATG, TAA and the substring between them is a multiple of 3 (a gene)
		dna = "CCCATGAAACCCGGGAAAGGGCCCCTAA";
		System.out.print("DNA to be retrieved: " + dna + "\n");
		System.out.print("Result: " + findSimpleGene(dna) + "\n\n");
		
		//DNA with ATG, TAA and the substring between them is not a multiple of 3
		dna = "CCCATGAAACCCGGGAAACCCGGGTAA";
		System.out.print("DNA to be retrieved: " + dna + "\n");
		System.out.print("Result: " + findSimpleGene(dna) + "\n\n");	
	}	
}
