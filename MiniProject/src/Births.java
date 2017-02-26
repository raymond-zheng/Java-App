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
            if(r.get(1).equals("M") || r.get(1).equals("m")){
                girlsTotal ++;
            }else{
                boysTotal ++ ;
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
           /* works
            System.out.print(r.get(0) + ", " + r.get(1) + ", " + r.get(2) + "\n");*/
            if (r.get(1).equals(gender.toUpperCase()) && r.get(0).equals(name)) {
                count = Integer.parseInt(r.get(2));
                found = 1;
                /* System.out.print(count); */
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
            if(r.get(1).equals(gender.toUpperCase()) && Integer.parseInt(r.get(2)) > count){
                rank += 1;
            }
        }
        return rank;

    }

    /*This method returns the name of the person in the file at this rank, for the given gender,
    where rank 1 is the name with the largest number of births*/
    public String getName(int year, int rank, String gender){
        File f = new File("/Users/raymondz/desktop/Github Projects/Java-Programs/MiniProject/us_babynames/us_babynames_test/yob" + Integer.toString(year) + "short.csv");
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
        File f = new File("/Users/raymondz/desktop/Github Projects/Java-Programs/MiniProject/us_babynames/us_babynames_test/yob" + Integer.toString(year) + "short.csv");
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



    /* works */
    public void testBirths()
    {
        totalBirths();
    }

    /* works */
    public void testGetRank(){
        System.out.print("The rank of " + "Ava within the year 2014 is " + getRank(2014,"Ava","F") + "\n");
    }

    /* works */
    public void testGetName(){
        System.out.print("The 1th rank births of female of 2014 is " + getName(2014,1,"F") + "\n" );
    }

    /* works */
    public void testWhatIsNameInYear(){
        System.out.print("Isabella born in 2012 would be "+whatIsNameInYear("Isabella",2012,2014)+" if she was born in 2014.\n");
    }
}
