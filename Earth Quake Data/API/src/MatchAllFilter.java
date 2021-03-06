import java.util.ArrayList;

public class MatchAllFilter implements Filter {
	
	private ArrayList<Filter> filters;
	
	public MatchAllFilter(){
		filters = new ArrayList<Filter>();
	}
	
	public void addFilter(Filter f){
		filters.add(f);
	}
	
	@Override
	public boolean satisfies(QuakeEntry qe) {
		for(Filter f :filters){
			if(! f.satisfies(qe)){
				return false;
			}
		}
		return true;
	}

	@Override
	public String getName() {
		String name = "Filters used are: ";
		for(int i  = 0 ; i < filters.size(); i ++){
			if(i == filters.size() - 1 ){
				name += filters.get(i).getName();
			}else if(i == filters.size() - 2 ){
				name += filters.get(i).getName() + " and ";
			}else{
				name += filters.get(i).getName() + " , ";
			}
		}
		return name;
	}
	
}
