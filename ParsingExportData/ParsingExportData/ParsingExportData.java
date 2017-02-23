
/**
 * Write a description of ParsingExportData here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;

public class ParsingExportData {
    //call this each time to reset the parser 
    //parser = fr.getCSVParser();
    
    public String countryInfo(CSVParser parser, String country){
        for(CSVRecord record: parser){
            String countryName = record.get("Country");
            if(countryName.contains(country)){
                String exports = record.get("Exports");
                String Value = record.get("Value (dollars)");
                return countryName + ": " + exports + ": " + Value + "\n";
            }
        }
        return "";
    }
    
    public void tester(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        System.out.print(countryInfo(parser,"United States"));
        
    }
    
    
    
}
