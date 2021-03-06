
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;


public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     //should not do the following, since WebLogParser is statis
     //WebLogParser wlp = new WebLogParser();
     
     public LogAnalyzer() {
         // complete constructor
    	 records = new ArrayList<LogEntry>();
     }
     
     //empty the ArrayList of LogEntry records
     public void clearRecords(){
    	 records.clear();
     }
        
     public void readFile(String filename) {
         // complete method
    	 //File f = new File(filename);
    	 FileResource fr = new FileResource("data/" + filename);
    	 for(String l: fr.lines()){
    		 records.add(WebLogParser.parseEntry(l));
    	 }   	 
     }
        
     public void printAll() {
    	 System.out.println("Printing all LogEntry");
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     public int countUniqueIPs(){
    	 ArrayList<String> uniqueIPs = new ArrayList<String>();
    	 for(int i = 0 ; i < records.size(); i ++){
    		 String currentIP = records.get(i).getIpAddress();
    		 //contains means 'equal' method..
    		 if(!(uniqueIPs.contains(currentIP))){
    			 uniqueIPs.add(currentIP);
    		 }
    	 }
    	 return uniqueIPs.size();
     }
     
     public void printAllHigherThanNum(int num){
    	 System.out.println("Printing LogEntry with status code higher than " + num);
    	 for(int i = 0; i < records.size(); i++){
    		 int statusCode = records.get(i).getStatusCode();
    		 if(statusCode > num){
    			 System.out.println(records.get(i));
    		 }
    	 }
     }
     
     //someday has the Format Mmm dd
     public ArrayList<String>  uniqueIPVisitsOnDay(String someday){
    	 //System.out.println(someday);
    	 ArrayList<String> someDay = new ArrayList<String>();
    	 for(int i = 0; i < records.size(); i ++){
    		 String ip = records.get(i).getIpAddress();
    		 String d = records.get(i).getAccessTime().toString().substring(4,10);
    		 if(someday.equals(d)){
    			 if(!someDay.contains(ip)){
    				 someDay.add(records.get(i).getIpAddress());
    			 }
    		 }
    	 }
    	 return someDay;
     }
     
     public int countUniqueIPsInRange(int low, int high){
    	 int count = 0;
    	 ArrayList<String> seen = new ArrayList<String>();
    	 for(int i = 0; i < records.size(); i ++){
    		 int statusCode = records.get(i).getStatusCode();
    		 if(low <= statusCode && statusCode <= high){
    			 String ip = records.get(i).getIpAddress();
    			 if(! seen.contains(ip)){
    				 count ++; 
    				 seen.add(ip);
    			 }
    		 } 
    	 }
    	 return count;
     }
     
     
     
     
     
     
     
     
     
     public HashMap<String, Integer>  countVisitsPerIP(){
    	 HashMap<String,Integer> c = new HashMap<String, Integer>();
    	 for(LogEntry le: records){
    		 String curIp = le.getIpAddress();
    		 if(!c.containsKey(curIp)){
    			 c.put(curIp, 1);
    		 }else{
    			 c.put(curIp,c.get(curIp)+1);
    		 }
    	 }
    	 return c;
     }
     
     public int mostNumberVisitsByIP(HashMap<String, Integer> c){
    	 int max = -1;
    	 for(String key: c.keySet()){
    		 if(c.get(key)>max){
    			 max = c.get(key);
    		 }
    	 }
    	 return max;
     }
     
     public ArrayList<String> iPsMostVisits(HashMap<String, Integer> c){
    	 ArrayList <String> ipMost = new ArrayList<String>();
    	 int maxTimes = mostNumberVisitsByIP(c);
    	 for(String key : c.keySet() ){
    		 if(c.get(key) == maxTimes){
    			 ipMost.add(key);
    		 }
    	 }
    	 return ipMost;
     }
     
     public HashMap<String, ArrayList<String>> iPsForDays(){
    	 HashMap<String,ArrayList<String>> ipDays = new HashMap<String,ArrayList<String>>();
    	 for(LogEntry le: records){
    		 //MMM DD 
    		 String date = le.getAccessTime().toString().substring(4,10);
    		 String ipAdd = le.getIpAddress();
    		 if(! ipDays.containsKey(date)){
    			 ArrayList <String> ips = new ArrayList<String>();
    			 ips.add(ipAdd);
    			 ipDays.put(date, ips);
    		 }else{
    			 //cannot use ipDays.put(data,ipDays.get(date).add(ipAdd)) here, since the second argument returns a boolen....)
    			 ArrayList <String> temp = ipDays.get(date);
    			 temp.add(ipAdd);
    			 ipDays.put(date, temp);
    		 }
    	 }
    	 return ipDays;
     }
     
     public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> ipDays){
    	 int maxNum = -1;
    	 String maxDate = null;
    	 for(String date: ipDays.keySet()){
    		 if(ipDays.get(date).size() > maxNum){
    			 maxNum = ipDays.get(date).size();
    			 maxDate = date;
    		 }
    	 }
    	 return maxDate;
     }
     
     public  ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> ipDays, String date){
    	 ArrayList<String> mostIpDay = new ArrayList<String>();
    	 ArrayList<String> ipsOfDay = ipDays.get(date);
    	 
    	 HashMap<String, Integer> tempCount = new HashMap<String, Integer>();
    	
    	 for(String ip : ipsOfDay){
    		 if(!tempCount.containsKey(ip)){
    			 tempCount.put(ip, 1);
    		 }else{
    			 tempCount.put(ip, tempCount.get(ip)+1);
    		 }
    	 }
    	 
    	 int mostNumOfDay = mostNumberVisitsByIP(tempCount);
    	 
    	 for(String ip : tempCount.keySet()){
    		 if(tempCount.get(ip) == mostNumOfDay){
    			 mostIpDay.add(ip);
    		 }
    	 }
    	 return mostIpDay;
     }
}
