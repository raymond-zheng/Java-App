
/**
 * Write a description of interface IMarkovModel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public interface IMarkovModel {
	//setTraining is defined at abstract-based class
    public void setTraining(String text);
    //getRandomText is defined at child class
    public String getRandomText(int numChars);
    //setRandom is defined at abstract-based class(parent class)
    public void setRandom(int seed);
    
}
