/**
 * Created by Siyuan Zheng on 2017-02-26.
 */

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

/*Use shift + command + / to block commment OR shift + / for line comment*/

public class Births {
    public void  totalBirths(){
        /*print the number of girls names , the number of boys names and the total names in the file.*/
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        int boysTotal = 0;
        int girlsTotal = 0;
        int total = 0;
        for(CSVRecord r : parser){
            total += 1;
            if(r.get(1).equals("M")){
                boysTotal ++;
            }else{
                girlsTotal ++ ;
            }
        }
        System.out.print("There are " + boysTotal + " boys that were born in the year\n" );
        System.out.print("There are " + girlsTotal + " girls that were born in the year\n" );
        System.out.print("There are " + total + " babies that were born in the year\n" );
    }

    /*This method returns the rank of the name in the file for
     the given gender, where rank 1 is the name with the largest number of births.*/

    public int getRank(int year,String name, String gender){
        File f = new File("/Users/raymondz/desktop/Github Projects/Java-Programs/MiniProject/us_babynames/us_babynames_by_year/yob" + Integer.toString(year) + ".csv");
        FileResource fr = new FileResource(f);
        CSVParser parser = fr.getCSVParser();
        int count = 0;
        int rank = 1;
        int found = 0;
        for(CSVRecord r : parser) {
           /* works */
            //System.out.print(r.get(0) + ", " + r.get(1) + ", " + r.get(2) + "\n");
            if (r.get(1).equals(gender) && r.get(0).equals(name)) {
                count = Integer.parseInt(r.get(2));
                found = 1;
                //System.out.print(count + "\n");
                break;
            }
        }

        if(found == 0){
            return -1;
        }

        parser = fr.getCSVParser();

        for(CSVRecord r : parser){
            /* works
            System.out.print(r.get(0) + ", " + r.get(1) + ", " + r.get(2) + "\n");
            */
            if(r.get(1).equals(gender) && Integer.parseInt(r.get(2)) > count){
                rank += 1;
            }
        }
        return rank;

    }

    /*This method returns the name of the person in the file at this rank, for the given gender,
    where rank 1 is the name with the largest number of births*/
    public String getName(int year, int rank, String gender){
        File f = new File("/Users/raymondz/desktop/Github Projects/Java-Programs/MiniProject/us_babynames/us_babynames_by_year/yob" + Integer.toString(year) + ".csv");
        FileResource fr = new FileResource(f);
        CSVParser parser = fr.getCSVParser();
        for(CSVRecord r : parser){
            if(getRank(year,r.get(0),gender) == rank && r.get(1).equals(gender.toUpperCase())){
                return r.get(0);
            }
        }
        return "NO NAME";
    }

    public String whatIsNameInYear(String name, int year, int newYear){
        int rank = 0;
        String gender = null;
        File f = new File("/Users/raymondz/desktop/Github Projects/Java-Programs/MiniProject/us_babynames/us_babynames_by_year/yob" + Integer.toString(year) + ".csv");
        FileResource fr = new FileResource(f);
        CSVParser parser = fr.getCSVParser();
        for(CSVRecord r : parser){
            if(r.get(0).equals(name)){
                gender = r.get(1);
                break;
            }
        }
        if(gender == null){
            return "No " + name + " is born in " + Integer.toString(year);
        }

        rank = getRank(year,name,gender);
        return getName(newYear,rank,gender);
    }

    public int yearOfHighestRank(String name, String gender){
        DirectoryResource dr = new DirectoryResource();
        int highestYear = 10000;
        int highestRank = 10000;
        for(File f : dr.selectedFiles()) {
            int fileYear  = Integer.parseInt(f.getName().substring(3,7));
            //System.out.print(fileYear + "\n");
            //System.out.print(getRank(fileYear,"name",gender) + "\n");
            if(getRank(fileYear,name,gender) < highestRank && getRank(fileYear,name,gender) != -1){
                highestYear = Integer.parseInt(f.getName().substring(3,7));
                highestRank = getRank(fileYear,name,gender);
            }
        }
        if(highestRank == 10000){
            return -1;
        }else{
            return highestYear;
        }
    }

    /*This method selects a range of files to process and returns a double representing the
    average rank of the name and gender over the selected files. It should return -1.0 if the
    name is not ranked in any of the selected files*/
    public double getAverageRank(String name, String gender){
        DirectoryResource dr = new DirectoryResource();
        int totalRank = 0;
        int count = 0;
        for(File f: dr.selectedFiles()){
            int fileYear = Integer.parseInt(f.getName().substring(3,7));
            if(getRank(fileYear,name,gender ) != -1){
                count += 1;
                totalRank += getRank(fileYear,name,gender);
            }
        }
        if(totalRank == 0){
            return -1.0;
        }else{
            return (double)totalRank/(double)count;
        }
    }

    /*This method returns an integer, the total number of births of those names
    with the same gender and same year who are ranked higher than name*/

    public int getTotalBirthsRankedHigher(int year, String name, String gender){
        int rank = getRank(year,name,gender);
        int count = 0;
        File f = new File("/Users/raymondz/desktop/Github Projects/Java-Programs/MiniProject/us_babynames/us_babynames_by_year/yob" + Integer.toString(year) + ".csv");
        FileResource fr = new FileResource(f);
        CSVParser parser = fr.getCSVParser();
        for(CSVRecord r: parser){
            if(!(r.get(0).equals(name)) && r.get(1).equals(gender)){
                count += Integer.parseInt(r.get(2));
            }
        }
        return count;
    }


    /* works */
    public void testBirths()
    {
        totalBirths();
    }

    /* works */
    public void testGetRank(){
        System.out.print("The rank of " + "Emily within the year 1960 is " + getRank(1960,"Emily","F") + "\n");
    }

    /* works */
    public void testGetName(){
        System.out.print("The 450th rank births of male of 1982 is " + getName(1982,450,"M") + "\n" );
    }

    /* works */
    public void testWhatIsNameInYear(){
        System.out.print("Owen born in 1974 would be "+whatIsNameInYear("Owen",1974,2014)+" if she was born in 2014.\n");
    }

    /* works */
    public void testyearOfHighestRank(){
        System.out.print("The year of highest rank of Mich within selected years is "+ yearOfHighestRank("Mich","M") + "\n");
    }

    /* it seems works */
    public void testGetAverageRank(){
        System.out.print("The average rank of Susan within selected years is "+getAverageRank("Susan","F") + "\n");
    }

    public void testGetTotalBirthsRankedHigher(){
        System.out.print("The total birth ranked higher then Drew is " + getTotalBirthsRankedHigher(1990,"Drew","M") + "\n");
    }
}
