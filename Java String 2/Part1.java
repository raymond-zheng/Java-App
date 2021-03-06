import edu.duke.*;
public class Part1 {
	
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
		
		//add one case, if taa , tag and taa are -1, should change the index of *next* atg
		//* This is a critical point that solves the problem and bugs */
		if(indexTAA == -1 && indexTAG == -1 && indexTGA == -1){
			indexATG = temp.indexOf("ATG",indexATG + 1);
			while(indexATG != -1){
				indexTAA = findStopCodon(dna,indexATG,"TAA");
				indexTAG = findStopCodon(dna,indexATG,"TAG");
				indexTGA = findStopCodon(dna,indexATG,"TGA");
				if(indexTAA != -1 || indexTAG != -1 || indexTGA != -1){
					break;
				}
				indexATG = temp.indexOf("ATG",indexATG + 1);
			}
		}
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
	
	public StorageResource getAllGenes(String dna){
		StorageResource storage = new StorageResource();
		String cur = dna.toUpperCase();
		int curIndex = cur.indexOf("ATG");
		while(true){
			//.substring( ) can only take one parameter which is beingIndex
			String tobePrint = findGene(dna.substring(curIndex));
			int length = tobePrint.length();
			if(length == 0){
				break;
			}
			storage.add(tobePrint);
			curIndex = cur.indexOf("ATG",curIndex+length);
			if(curIndex == -1){
				break;
			}
		}
		return storage;
	}
	//taa, tag, tga
	public void testGetAllGenes(){
		String dna = "atgcccttttaaatgtagatgtgaatgcccctgaatgcccttcttagatgttggggggatgttggggatgttgaaaatgtagatgtga";
		StorageResource temp = getAllGenes(dna);
		for(String s : temp.data()){
			System.out.print(s + "\n");
		}
		System.out.print("7 DNAs should be printed above\n");
	}
}
