/**
 * Created by Siyuan Zheng on 2017-02-23.
 */

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class WeatherCSV {
    public CSVRecord coldestHourInFile(CSVParser parser) {
        CSVRecord coldest = null;
        for (CSVRecord record : parser) {
            if (coldest == null) {
                coldest = record;
            } else {
                double coldestTem = Double.parseDouble(coldest.get("TemperatureF"));
                double currentTemp = Double.parseDouble(record.get("TemperatureF"));
                if (currentTemp < coldestTem) {
                    coldest = record;
                }
            }
        }
        return coldest;
    }

    public void testColdestHourInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord record = coldestHourInFile(parser);
        String DateUTC = record.get("DateUTC");
        String TemperatureF = record.get("TemperatureF");
        System.out.print("At time " + DateUTC + " , it reached the coldest temperature which is " + TemperatureF + "\n");
    }

    //File is different from FileResource
    //return the name of the file
    //works now, but return the path of the expected file instead of name
    public String fileWithColdestTemperature() {
        DirectoryResource d = new DirectoryResource();
        File coldestFile = null;
        double coldestTemp = 1000;
        for (File f : d.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            CSVRecord current = coldestHourInFile(parser);
            if (Double.parseDouble(current.get("TemperatureF")) < coldestTemp) {
                coldestTemp = Double.parseDouble(current.get("TemperatureF"));
                coldestFile = f;
            }
        }
        return coldestFile.getPath();
    }

    public void testFileWithColdestTemperatureReturn() {
        System.out.print(fileWithColdestTemperature() + "\n");
    }

    public void testFileWithColdestTemperature() {
        File f = new File(fileWithColdestTemperature());
        FileResource fr = new FileResource(f);
        CSVParser parser = fr.getCSVParser();
        System.out.print("Coldest day was in file " + f.getName() + "\n");
        System.out.print("Coldest temperature on that day was " + coldestHourInFile(parser).get("TemperatureF") + "\n");
        System.out.print("All the Temperatures on the coldest day were: \n");
        //remember to add the following line to the code to make it work..
        parser = fr.getCSVParser();
        for (CSVRecord record : parser) {
            System.out.print(record.get("DateUTC") + ": " + record.get("TemperatureF") + "\n");
        }
    }

    //make sure that humidity is not N/A
    public CSVRecord lowestHumidityInFile(CSVParser parser) {
        double lowest = 10000;
        CSVRecord lowestRecord = null;
        for (CSVRecord record : parser) {
            //after first condition fail, java will not check the second one
            if (!(record.get("Humidity").contains("N/A")) && Double.parseDouble(record.get("Humidity")) < lowest) {
                lowestRecord = record;
                lowest = Double.parseDouble(record.get("Humidity"));
            }
        }
        return lowestRecord;
    }

    public void testLowestHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        System.out.print("Lowest Humidity was " + csv.get("Humidity") + " at " + csv.get("DateUTC") + "\n");
    }

    public CSVRecord lowestHumidityInManyFiles(){
        DirectoryResource d = new DirectoryResource();
        CSVRecord lowest = null;
        double lowestHumidity = 1000;
        for(File f: d.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            for(CSVRecord record : parser){
                if(!(record.get("Humidity").contains("N/A")) &&  Double.parseDouble(record.get("Humidity")) < lowestHumidity){
                    lowest = record;
                    lowestHumidity = Double.parseDouble(record.get("Humidity"));
                }
            }
        }
        return lowest;

    }

    //Lowest Humidity was 24 at 2014-01-20 19:51:00
    public void testLowestHumidityInManyFiles(){
        CSVRecord temp = lowestHumidityInManyFiles();
        System.out.print("Lowest Humidity was " + temp.get("Humidity") + " at "  + temp.get("DateUTC") + "\n");
    }

    public double averageTemperatureInFile(CSVParser parser) {
        double tempCount = 0;
        double daysCount = 0;
        for (CSVRecord record : parser) {
            daysCount++;
            tempCount += Double.parseDouble(record.get("TemperatureF"));
        }
        return tempCount / daysCount;
    }

    public void testAverageTemperatureInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        System.out.print("Average temperature in file is " + averageTemperatureInFile(parser) + "\n");
    }

    // average temperature of only those temperatures when the humidity was greater than or equal to value
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value) {
        double count = 0;
        double tempCount = 0;
        for (CSVRecord record : parser) {
            if ((!record.get("Humidity").contains("N/A")) && Double.parseDouble(record.get("Humidity")) > (double) value) {
                count++;
                tempCount += Double.parseDouble(record.get("TemperatureF"));
            }
        }
        if (count == 0) {
            return -1;
        } else {
            return tempCount / count;
        }
    }

    public void testAverageTemperatureWithHighHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double avg = averageTemperatureWithHighHumidityInFile(parser, 79);
        if(avg == -1 ){
            System.out.print("No temperatures with that humidity");
        }else{
            System.out.print("Average Temp when high Humidity is " + " "+ avg);
        }
    }

}






