import java.util.ArrayList;
import java.util.Collections;

public class MovieRunnerWithFilters {
	
	public void printAverageRatings (){
		// ratings_short.csv and ratedmovies_short.csv
		ThirdRatings tr = new ThirdRatings("ratings.csv");
		System.out.println(tr.getRaterSize() + " raters loaded");
		//can have parameter for the constructor
		MovieDatabase.initialize("ratedmoviesfull.csv");
		//static 
		System.out.println(MovieDatabase.size() + " movies loaded");
		//call getAverageRatings to get every movie
		//print in ascending order 
		//use compareTo , Rating implements Comparable
		int min = 35;
		ArrayList<Rating> sorted = tr.getAverageRatings(min);
		//use Collections.sort for AraayList, to call compareTo of the method which implements Comparable
		Collections.sort(sorted);
		for(Rating r: sorted){
			System.out.println(r.getValue() + "   " + MovieDatabase.getTitle(r.getItem ()));
		}
		System.out.println("There are " +  sorted.size() + " movies with at least " + min + " ratings");
	}
	
	public void printAverageRatingsByYear(){
		MovieDatabase.initialize("ratedmoviesfull.csv");
		ThirdRatings tr= new ThirdRatings("ratings.csv");
		int year = 2000;
		YearAfterFilter yaf = new YearAfterFilter(year);
		int min = 20;
		ArrayList<Rating> result = tr.getAverageRatingsByFilter(min,yaf);
		Collections.sort(result);
		System.out.println("There are " + result.size() + " movies found that are after year " + year +" and have at least" + min + " ratings");
		for(Rating r: result){
			System.out.println(r.getValue() + "   " + MovieDatabase.getYear(r.getItem())  + "  " + MovieDatabase.getTitle(r.getItem ()));
		}
	}
	
	public void printAverageRatingsByGenre(){
		GenreFilter gf = new GenreFilter("Comedy");
		MovieDatabase.initialize("ratedmoviesfull.csv");
		ThirdRatings tr= new ThirdRatings("ratings.csv");
		ArrayList<Rating> result =tr.getAverageRatingsByFilter(20, gf);
		Collections.sort(result);
		System.out.println("found " + result.size() + " movies");
		for(Rating r: result){
			System.out.println(r.getValue() + "   "  + MovieDatabase.getTitle(r.getItem ()));
			System.out.println("      "+ MovieDatabase.getGenres(r.getItem()));
		}
	}
	
	public void printAverageRatingsByMinutes(){
		MinutesFilter mf = new MinutesFilter(105,135);
		MovieDatabase.initialize("ratedmoviesfull.csv");
		ThirdRatings tr= new ThirdRatings("ratings.csv");
		ArrayList<Rating> result =tr.getAverageRatingsByFilter(5, mf);
		Collections.sort(result);
		System.out.println("found " + result.size() + " movies");
		for(Rating r: result){
			System.out.println("Rating : " + r.getValue() + " Length:  "+ MovieDatabase.getMinutes(r.getItem())+ "   "   + MovieDatabase.getTitle(r.getItem ()));
		}
	}
	
	public void printAverageRatingsByDirectors(){
		//ratedmovies_short.csv
		//ratings_short.csv
		DirectorsFilter df = new DirectorsFilter("Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack");
		MovieDatabase.initialize("ratedmoviesfull.csv");
		ThirdRatings tr= new ThirdRatings("ratings.csv");
		ArrayList<Rating> result =tr.getAverageRatingsByFilter(4, df);
		Collections.sort(result);
		System.out.println("found " + result.size() + " movies");
		for(Rating r: result){
			System.out.println("Rating : " + r.getValue() + "   "   + MovieDatabase.getTitle(r.getItem ()));
			System.out.println("        "+ MovieDatabase.getDirector(r.getItem()));
		}
	}
	
	public void  printAverageRatingsByYearAfterAndGenre(){
		AllFilters af = new AllFilters();
		String genre = "Drama";
		GenreFilter gf = new GenreFilter(genre);
		int year = 1990;
		YearAfterFilter yaf = new YearAfterFilter(year);
		af.addFilter(gf);
		af.addFilter(yaf);
		MovieDatabase.initialize("ratedmoviesfull.csv");
		ThirdRatings tr= new ThirdRatings("ratings.csv");
		ArrayList<Rating> result =tr.getAverageRatingsByFilter(8, af);
		Collections.sort(result);
		System.out.println("found "+ result.size() + " movies that are after year " + year + " and have genre " + genre );
		for(Rating r: result){
			System.out.println(r.getValue() + "  " + MovieDatabase.getYear(r.getItem()) +"  " + MovieDatabase.getTitle(r.getItem()));
			System.out.println("        " + MovieDatabase.getGenres(r.getItem()));
		}
	}
	
	public void  printAverageRatingsByDirectorsAndMinutes (){
		//something wrong with director filter
		MovieDatabase.initialize("ratedmoviesfull.csv");
		ThirdRatings tr= new ThirdRatings("ratings.csv");
		AllFilters af = new AllFilters();
		int min = 90;
		int max = 180;
		MinutesFilter mf = new MinutesFilter(min,max);
		String directors = "Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack";
		DirectorsFilter df = new DirectorsFilter(directors);
		af.addFilter(mf);
		af.addFilter(df);
		ArrayList<Rating> result =tr.getAverageRatingsByFilter(3, af);
		Collections.sort(result);
		System.out.println("found "+ result.size() + " movies that are between " + min + " and " + max + " minutes and have directors in " + directors);
		for(Rating r : result){
			System.out.println(r.getValue() + "    length: " + MovieDatabase.getMinutes(r.getItem()) + "  " + MovieDatabase.getTitle(r.getItem()) );
			System.out.println("          " + MovieDatabase.getDirector(r.getItem()));
		}
		
		
		
	}
	
	
	
	
}
