import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;


public class FourthRatings {
	
	private double dotProduct(Rater me, Rater r){
		double result = 0;
		for(String rated : me.getItemsRated()){
			if(r.hasRating(rated)){
				double meCurRating = (double)(me.getRating(rated) - 5);
				double rCurRating = (double)(r.getRating(rated) - 5);
				result += meCurRating * rCurRating;
			}
		}
		return result;
	}

	private ArrayList<Rating> getSimilarities(String id){
		ArrayList<Rating> result = new ArrayList<Rating>();
		for(Rater r : RaterDatabase.getRaters()){
			if(!(r.getID().equals(id))){
				if(dotProduct(RaterDatabase.getRater(id),r) > 0 ){
					Rating cur = new Rating(r.getID(), dotProduct(RaterDatabase.getRater(id),r));
					result.add(cur);
				}
			}
		}
		Collections.sort(result);
		Collections.reverse(result);
		return result;
	}
	
	

	public ArrayList<Rating> getSimilarRatings (String id, int numSimilarRaters, int  minimalRaters){
		ArrayList<Rating> result = new ArrayList<Rating> ();
		
		HashMap<String,Integer> hm = new HashMap<String,Integer>();
		
		ArrayList<Rating> raters = getSimilarities(id);

		
		
		for(int i = 0; i <  numSimilarRaters ; i ++){
			String curRater = raters.get(i).getItem();
			for(String movie: RaterDatabase.getRater(curRater).getItemsRated()){
				if( ! RaterDatabase.getRater(id).hasRating(movie)){
					if(hm.containsKey(movie)){
						hm.put(movie, hm.get(movie) + 1);
					}else{
						hm.put(movie,1);
					}
				}
				
			}
		}	
		
		for(String movieID : hm.keySet()){
			if( ! RaterDatabase.getRater(id).hasRating(movieID)){
				if(hm.get(movieID) >= minimalRaters){
					double curWeightedRating = 0;
					double count = 0 ; 
					for(int i = 0; i < numSimilarRaters ; i ++){
						Rater cur = RaterDatabase.getRater(raters.get(i).getItem());
						//********
						double weight = raters.get(i).getValue();
						if(cur.hasRating(movieID)){
							if(cur.getRating(movieID) != 0){
								count += 1;
								curWeightedRating += (double)(weight * (double)cur.getRating(movieID));
							}	
						}
					}
					Rating movieIdRating = new Rating(movieID, (double)(curWeightedRating/count));
					result.add(movieIdRating);
				}
			}
		}

		Collections.sort(result);
		Collections.reverse(result);
		return result;
	}
	
	public ArrayList<Rating> getSimilarRatingsByFilter(String id, int numSimilarRaters, int minimalRaters, Filter filterCriteria){
		ArrayList<Rating> tempResult = getSimilarRatings (id, numSimilarRaters, minimalRaters);
		ArrayList<Rating> result = new ArrayList<Rating>();
		for(Rating r : tempResult){
			if(filterCriteria.satisfies(r.getItem())){
				result.add(r);
			}
		}
		Collections.sort(result);
		Collections.reverse(result);
		return result;
	}
	
	
	private double getAverageByID(String id, int minimalRaters){
		ArrayList<Rater> raters = RaterDatabase.getRaters();
		double count = 0;
		double tRate = 0;
		for(Rater r : raters){
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
	
	
	
	public ArrayList<Rating> getAverageRatings(int minimalRaters){
		ArrayList<Rating> result = new ArrayList<Rating>();
		ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
		for(String id : movies){
			if(getAverageByID(id, minimalRaters) > 0){
				Rating rating = new Rating(id,getAverageByID(id, minimalRaters));
				result.add(rating);
			}
		}
		return result;
	}
	
	public ArrayList<Rating>  getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria){
		ArrayList<Rating> result = new ArrayList<Rating>();
		ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
		for(String m : movies){
			if(getAverageByID(m,minimalRaters) > 0){
				Rating rating = new Rating(m,getAverageByID(m,minimalRaters));
				result.add(rating);
			}
		}
		return result;	
	}
}
