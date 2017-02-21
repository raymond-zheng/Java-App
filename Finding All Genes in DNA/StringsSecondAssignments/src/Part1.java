
public class Part1{
	//can be "TAA", "TAG", or "TGA"
	//start with "ATG"
	public int findStopCodon(String dna, int startIndex, String stopCodon){
		dna = dna.toUpperCase();
		stopCodon = stopCodon.toUpperCase();
		//cannot use  stopCodon == "TAA" to make string comparison here
		if(!(stopCodon.equals("TAA") || stopCodon.equals("TAG") || stopCodon.equals("TGA"))){
			System.out.print("Invalid stopCodon\n");
			return -1;
		}
		int curIndex = dna.indexOf(stopCodon,startIndex+3);
		if(curIndex == -1){
			return -1;
		}
		while((curIndex-startIndex)%3 != 0){
			//should add 1 here instead of add 3 
			curIndex = dna.indexOf(stopCodon,curIndex+1);
			//must add this if statement here , otherwise it won't get out of the while loop
			if(curIndex == -1){
				return -1;
			}
		}
		return curIndex;
	}
	
	public void testFindStopCodon(){
		System.out.print("Test results for testFindStopCodon()\n");
		String dna = "atgccctag";
		System.out.print("The DNA is " + dna + " \n");
		System.out.print(findStopCodon(dna,0,"tag")+"\n");
		
		dna = "aaaccatgtag";
		System.out.print("The DNA is " + dna + " \n");
		System.out.print(findStopCodon(dna,5,"tag")+"\n");
		
		dna = "atgaaaccctttaaccttggtag";
		System.out.print("The DNA is " + dna + " \n");
		System.out.print(findStopCodon(dna,0,"tag")+"\n");
		
		dna = "atgaaaaaaaaagggtttTAGTAA";
		System.out.print("The DNA is " + dna + " \n");
		System.out.print(findStopCodon(dna,0,"tag")+"\n\n");
		
	}
	
	
	public String findGene(String dna){
		String temp = dna.toUpperCase();
		int indexATG = temp.indexOf("ATG");
		if(indexATG == -1){
			return "";
		}
		int indexTAA = findStopCodon(dna,indexATG,"TAA");
		int indexTAG = findStopCodon(dna,indexATG,"TAG");
		int indexTGA = findStopCodon(dna,indexATG,"TGA");	
		int minIndex;
		//The logic is a little complicated here , and must understand is throughly
		if(indexTAA ==-1  || (indexTAG != -1 && (indexTAG < indexTAA))){
			minIndex = indexTAG;
		}else{ //indexTAA >= 0 && (indexTAA <= 0 || indexTAG >= indexTAA)
			minIndex = indexTAA;
		}
	
		if(minIndex ==-1 || (indexTGA != -1 && indexTGA < minIndex)){
			minIndex = indexTGA;
		}
		
		if(minIndex == -1){
			return "";
		}
		return dna.substring(indexATG,minIndex+3);
		
		
	}
	
	public void testFindGene(){
		System.out.print("Test results for testFindGene()\n");
		String dna = "atgtaatgatag";
		System.out.print("Input is " + dna + "\n");
		System.out.print(findGene(dna) + "\n");
		
		dna = "atgaaaccctttaaaTg";
		System.out.print("Input is " +dna + "\n");
		System.out.print(findGene(dna) + "\n");
		
		dna = "atgtagtgataa";
		System.out.print("Input is " +dna + "\n");
		System.out.print(findGene(dna) + "\n");
		
		dna = "atgaaaaaaaaagggtttTAGTAA";
		System.out.print("Input is " +dna + "\n");
		System.out.print(findGene(dna) + "\n\n");
	}
	
	public void printAllGenes(String dna){
		String cur = dna.toUpperCase();
		int curIndex = cur.indexOf("ATG");
		while(true){
			//.substring( ) can only take one parameter which is beingIndex
			String tobePrint = findGene(dna.substring(curIndex));
			int length = tobePrint.length();
			if(length == 0){
				break;
			}
			System.out.print(tobePrint + "\n");
			curIndex = curIndex + length;
		}
	}
	
	//taa,tag,tga
	public void testPrintAllGenes(){
		System.out.print("Test results for testPrintAllGenes()\n");
		String dna = "atgcccaaaccctagcccatgtaaaatgcccaaaccctagtgtgacatgcccaaaccctagcatgcccaaaccctagcatgcccaaaccctag";
		printAllGenes(dna);
	}
		
}
