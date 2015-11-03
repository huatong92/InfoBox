import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class League {
	public String name;
	public String championship;
	public String sport;
	public String slogan;
	public String officialWebsite;
	public String description;
	public List<String> teams;
	private List<String> teamList = Arrays.asList("/sports/sports_league_participation/team");
	
	public League(){
		teams = new ArrayList<String>();
	}
	
	public void getValue(JsonUtility json){
		name = json.getProperty("/type/object/name", "value");
		championship = json.getProperty("/sports/sports_league/championship", "text");
		sport = json.getProperty("/sports/sports_league/sport", "text");
		slogan = json.getProperty("/organization/organization/slogan", "text");
		officialWebsite = json.getProperty("/common/topic/official_website", "text");
		description = json.getProperty("/common/topic/description", "value");
		
		// get teams
		List<String> temp = json.getPropertyComplex("/sports/sports_league/teams", "text", teamList);
		temp.removeAll(Arrays.asList("*"));
		for (String str: temp){
			teams.add(str.replaceAll("team ", ""));
		}
	}
}
