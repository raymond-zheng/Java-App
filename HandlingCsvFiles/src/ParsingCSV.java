/**
 * Created by Siyuan Zheng on 2017-02-23.
 */
import org.apache.commons.csv.*;
import edu.duke.*;

public class ParsingCSV
{
    public void tester(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        //each time calling a new method needs resetting the parser
        //parser = fr.getCSVParser();
        System.out.print("The information about  Nauru :" + "\n");
        System.out.print(countryInfo(parser,"Nauru"));
        System.out.print("\n");

        parser = fr.getCSVParser();
        System.out.print("The countries that both export fish and nut: " + "\n");
        listExportersTwoProducts(parser,"fish","nuts");
        System.out.print("\n");

        parser = fr.getCSVParser();
        System.out.print("The number of countries that export sugar" + "\n");
        System.out.print(numberOfExporters(parser,"sugar"));
        System.out.print("\n\n");

        System.out.print("The countries that has larger exports than $999,999,999,999" + "\n");
        parser = fr.getCSVParser();
        bigExporters(parser,"$999,999,999,999");
    }

    //part2
    public String countryInfo(CSVParser parser, String country){
        for(CSVRecord record : parser){
            String countryName = record.get("Country");
            if(countryName.contains(country)){
                String exports = record.get("Exports");
                String value = record.get("Value (dollars)");
                return countryName + ": " + exports + ": " + value + "\n";
            }
        }
        return "";
    }

    //part3
    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2){
        for(CSVRecord record: parser) {
            if (record.get("Exports").contains(exportItem1) && record.get("Exports").contains(exportItem2)) {
                System.out.print(record.get("Country") + "\n");
            }
        }
    }

    //part4
    public int numberOfExporters(CSVParser parser, String exportItem){
        int count = 0;
        for(CSVRecord record: parser){
            if(record.get("Exports").contains(exportItem)){
                count ++;
            }
        }
        return count;
    }

    //part5
    public void bigExporters(CSVParser parser, String amount){
        for(CSVRecord record: parser){
            if(record.get("Value (dollars)").length() > amount.length()){
                System.out.print(record.get("Country") + " " + record.get("Value (dollars)") + "\n");
            }
        }
    }
}
