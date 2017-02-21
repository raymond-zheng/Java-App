
public class Part2 {
	public String findSimpleGene(String dna, String startCodon, String stopCodon){
		String startCodonUpper = startCodon.toUpperCase();
		String stopCodonUpper = stopCodon.toUpperCase();
		String dnaUpper = dna.toUpperCase();
		int start = dnaUpper.indexOf(startCodonUpper);
		if(start == -1){
			return "";
		}
		int stop = dnaUpper.indexOf(stopCodonUpper,start+3);
		if(stop == -1){
			return "";
		}
		//a valid dna has the length multiple of 3 
		if((stop - start) % 3 == 0){
			return dnaUpper.substring(start, stop+3);
		}
		return "";
	}
	
	public void testSimpleGene(){

		String dna = "AAATTTGGGAAATTTGGGTAACCCCCCC";
		dna = dna.toLowerCase();
		System.out.print("DNA to be retrieved: " + dna + "\n");
		System.out.print("Start with aAa and ends with Ccc\n");
		System.out.print("Result: " + findSimpleGene(dna,"aAa","Ccc") + "\n\n");
		
		dna = "ATGGGGGATGTAGAATGTAGCCCCCCC";
		dna = dna.toLowerCase();
		System.out.print("DNA to be retrieved: " + dna + "\n");
		System.out.print("Start with agg and ends with ccc\n");
		System.out.print("Result: " + findSimpleGene(dna,"agg","ccc") + "\n\n");
		
		dna = "AAAGGGTTTACACCCCCCC";
		dna = dna.toLowerCase();
		System.out.print("DNA to be retrieved: " + dna + "\n");
		System.out.print("Start with aac and ends with acc\n");
		System.out.print("Result: " + findSimpleGene(dna,"aac","acc") +"\n\n");
			
	}	
}
