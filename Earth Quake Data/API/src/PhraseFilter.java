public class PhraseFilter implements Filter {

	private String where;
	private String phrase;
	
	public PhraseFilter(String w, String p){
		where = w;
		phrase = p;
	}
	
	@Override
	public boolean satisfies(QuakeEntry qe) {
		String check = qe.getInfo();
		if(where.equals("any")){
			return (check.indexOf(phrase) != -1) ;
		}else if(where.equals("start")){
			return (check.indexOf(phrase) == 0);
		}else if(where.equals("end")){
			return (check.indexOf(phrase) + phrase.length() == check.length());
		}
		return false;
	}

	@Override
	public String getName() {
		return "PhraseFilter(\"" + where + "\" , \"" + phrase + "\")";
	}

}
