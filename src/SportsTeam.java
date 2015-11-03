import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SportsTeam {
	public String name;
	public String description;
	public String sport;
	public String arena;
	public String championships;
	public String founded;
	public String locations;
	
	public List<String> leagues;
	
	public List<String> coachName;
	public List<String> coachPosition;
	public List<String> coachFromTo;
	
	public List<String> playerName;
	public List<String> playerPosition;
	public List<String> playerNumber;
	public List<String> playerFromTo;
	
	private List<String> leagueList = Arrays.asList("/sports/sports_league_participation/league");
	private List<String> coachList = Arrays.asList("/sports/sports_team_coach_tenure/coach", "/sports/sports_team_coach_tenure/from",
			"/sports/sports_team_coach_tenure/to", "/sports/sports_team_coach_tenure/position");
	private List<String> playerList = Arrays.asList("/sports/sports_team_roster/from", "/sports/sports_team_roster/player",
			"/sports/sports_team_roster/position", "/sports/sports_team_roster/to", "/sports/sports_team_roster/number");
	
	public SportsTeam(){
		coachName = new ArrayList<String>();
		coachPosition = new ArrayList<String>();
		coachFromTo = new ArrayList<String>();
		leagues = new ArrayList<String>();
		playerName = new ArrayList<String>();
		playerPosition = new ArrayList<String>();
		playerNumber = new ArrayList<String>();
		playerFromTo = new ArrayList<String>();
	}
	
	public void getValue(JsonUtility json){
		name = json.getProperty("/type/object/name", "value");
		description = json.getProperty("/common/topic/description", "value");
		sport = json.getProperty("/sports/sports_team/sport", "text");
		arena = json.getProperty("/sports/sports_team/arena_stadium", "text");
		championships = json.getProperty("/sports/sports_team/championships", "text");
		founded = json.getProperty("/sports/sports_team/founded", "text");
		locations = json.getProperty("/sports/sports_team/location", "text");
		
		// get league
		List<String> leagueTemp= json.getPropertyComplex("/sports/sports_team/league", "text", leagueList);
		leagueTemp.removeAll(Arrays.asList("*"));
		for (String str: leagueTemp){
			leagues.add(str.replaceAll("league ", ""));
		}
		
		// get coach
		List<String> coach = json.getPropertyComplex("/sports/sports_team/coaches", "text", coachList);
		List<String> coachFrom = new ArrayList<String>();
		List<String> coachTo = new ArrayList<String>();
		int count = 0;
		for (String str : coach){
			if ( ! str.equals("*")){
				String temp = str.split(" ")[0];
				if (temp.equals("from")){
					coachFrom.add(str.substring(temp.length()+1));
				}
				else if (temp.equals("to")){
					if (str.substring(temp.length()+1).equals("[]")){
						coachTo.add("now");
					}else{
						coachTo.add(str.substring(temp.length()+1));
					}
				}
				else if (temp.equals("coach")){
					coachName.add(str.substring(temp.length()+1));
				}
				else if (temp.equals("position")){
					coachPosition.add(str.substring(temp.length()+1));
				}
				
			}else{
				if (coachFrom.size() < count + 1){
					coachFrom.add(" ");
				}
				if (coachTo.size() < count + 1){
					coachTo.add(" ");
				}
				if (coachName.size() < count + 1){
					coachName.add(" ");
				}
				if (coachPosition.size() < count + 1){
					coachPosition.add(" ");
				}
				
				count ++;
			}
			
		}
		coachFromTo = combineFromTo(coachFrom, coachTo);
		
		// get player
		List<String> player = json.getPropertyComplex("/sports/sports_team/roster", "text", playerList);
		List<String> playerFrom = new ArrayList<String>();
		List<String> playerTo = new ArrayList<String>();
		count = 0;
		for (String str : player){
			if ( ! str.equals("*")){
				String temp = str.split(" ")[0];
				if (temp.equals("from")){
					playerFrom.add(str.substring(temp.length()+1));
				}
				else if (temp.equals("to")){
					if (str.substring(temp.length()+1).equals("[]")){
						playerTo.add("now");
					}else{
						playerTo.add(str.substring(temp.length()+1));
					}
				}
				else if (temp.equals("player")){
					playerName.add(str.substring(temp.length()+1));
				}
				else if (temp.equals("number")){
					playerNumber.add(str.substring(temp.length()+1));
				}
				else {
					playerPosition.add(str);
				}
				
			}else{
				if (playerFrom.size() < count + 1){
					playerFrom.add(" ");
				}
				if (playerTo.size() < count + 1){
					playerTo.add(" ");
				}
				if (playerName.size() < count + 1){
					playerName.add(" ");
				}
				if (playerPosition.size() < count + 1){
					playerPosition.add(" ");
				}
				if (playerNumber.size() < count + 1){
					playerNumber.add(" ");
				}
				count ++;
			}
			
		}
		playerFromTo = combineFromTo(playerFrom, playerTo);

	}
	
	public List<String> combineFromTo(List<String> from, List<String> to){
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < from.size(); i ++){
			list.add(from.get(i) + "/" + to.get(i));
		}
		return list;
	}
}
