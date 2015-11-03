import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Actor {
	public List<String> filmName;
	public List<String> character;
	public List<String> tvName;
	public List<String> tvCharacter;
	private final List<String> film_detail = Arrays.asList("/film/performance/character", "/film/performance/film");
	private final List<String> tv_detail = Arrays.asList("/tv/regular_tv_appearance/character", "/tv/regular_tv_appearance/series");
	
	public Actor(){
		filmName = new ArrayList<String>();
		character = new ArrayList<String>();
		tvName = new ArrayList<String>();
		tvCharacter = new ArrayList<String>();
	}
	
	public void getValue(JsonUtility json){
		
		// get a list of info containing both film name and character information
		int count = 0;
		List<String> list = json.getPropertyComplex("/film/actor/film", "text", film_detail);
		for (String str: list){
			if ( ! str.equals("*")){
				String temp = str.split(" ")[0];
				if (temp.equals("character")){
					character.add(str.substring(temp.length()+1));
				}
				else if (temp.equals("film")){
					filmName.add(str.substring(temp.length()+1));
				}
			}else{
				count ++;
				if (character.size() < count){
					character.add(" ");
				}
				if (filmName.size() < count){
					filmName.add(" ");
				}
			}
			
		}
		
		// get a list of info containing both film name and character information
		List<String> tvlist = json.getPropertyComplex("/tv/tv_actor/starring_roles", "text", tv_detail);
		count = 0;
		for (String str: tvlist){
			if ( ! str.equals("*")){
				String temp = str.split(" ")[0];
				if (temp.equals("character")){
					tvCharacter.add(str.substring(temp.length()+1));
				}
				else if (temp.equals("series")){
					tvName.add(str.substring(temp.length()+1));
				}
			}
			else{
				count ++;
				if (tvCharacter.size() < count){
					tvCharacter.add(" ");
				}
				if (tvName.size() < count){
					tvName.add(" ");
				}
			}
		}
	}
}
