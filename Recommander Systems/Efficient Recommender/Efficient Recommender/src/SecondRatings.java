/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<EfficientRater> myRaters;
    
    public SecondRatings() {
        // default constructor
        this("ratedmoviesfull.csv", "ratings.csv");
    }

	public SecondRatings(String moviefile, String ratingsfile) {
		//store in the two private variables
		FirstRatings fr = new FirstRatings();
		myMovies = fr.loadMovies(moviefile);
		myRaters = fr.loadRaters(ratingsfile);
	}
	
	public int getMovieSize(){
		return myMovies.size();
	}
	
	public int  getRaterSize(){
		return myRaters.size();
	}
    
	//id is the movie's id 
	private double getAverageByID(String id, int minimalRaters){
		//use Rater's method getRating
		double count = 0;
		//do not use int here, use double instead
		double tRate = 0;
		for(EfficientRater r : myRaters){
			if(r.hasRating(id)){
				count += 1;
				tRate += r.getRating(id);
			}
		}
		
		if(count >= minimalRaters){
			return (double)(tRate/count);
		}
		
		return (double)0.0;
	}	
	
	//create new Rating for each movie, but use avg rate for the rating
	public ArrayList<Rating> getAverageRatings(int minimalRaters){
		ArrayList<Rating> result = new ArrayList<Rating>();
		for(Movie m : myMovies){
			if(getAverageByID(m.getID(), minimalRaters) > 0){
				Rating rating = new Rating(m.getID(),getAverageByID(m.getID(), minimalRaters));
				result.add(rating);
			}
		}
		
		return result;
	}
	
	public String getTitle(String id){
		for(Movie m : myMovies){
			if(m.getID().equals(id)){
				return m.getTitle();
			}
		}
		return "id not found";
	}
	
	public String getID(String title){
		for(Movie m : myMovies){
			if(m.getTitle().equals(title)){
				return m.getID();
			}
		}
		return "title not found";
	}
	
	
	
}