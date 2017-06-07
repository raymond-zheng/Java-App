
public class GenreFilter implements Filter{
	private String myGenre;

	public GenreFilter(String genre){
		myGenre = genre;
	}
	
	@Override
	public boolean satisfies(String id) {
		String[] genres = MovieDatabase.getGenres(id).split(",");
		
//		for(String d : directors){
//			//really important line here
//			d = d.trim();
//			if(myDirector.contains(d)){
//				return true;
//			}
//		}
		for(String g: genres){
			g = g.trim();
			if(myGenre.contains(g)){
				return true;
			}
		}
		return false;
		//return MovieDatabase.getGenres(id).contains(myGenre);
	}

}
