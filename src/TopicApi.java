import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.jayway.jsonpath.JsonPath;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class TopicApi {
	private final List<String> TYPE_LIST = Arrays.asList("/people/person", "/book/author", "/film/actor",
	                                        "/tv/tv_actor", "/organization/organization_founder",
	                                        "/business/board_member", "/sports/sports_league",
	                                        "/sports/sports_team", "/sports/professional_sports_team");
	private String key;
	private JSONObject topic;
	
	public TopicApi(String key){
		this.key = key;

	}
	
	public JSONObject getTopic(){
		return topic;
	}
	
	public boolean useApi(String mid){
		// store types that fit our requirements
		List<String> types = new ArrayList<String>();
		try {
			HttpTransport httpTransport = new NetHttpTransport();
			HttpRequestFactory requestFactory = httpTransport.createRequestFactory();
			JSONParser parser = new JSONParser();
			GenericUrl url = new GenericUrl("https://www.googleapis.com/freebase/v1/topic" + mid);
			url.put("key", key);
			url.put("filter", "all");
			HttpRequest request = requestFactory.buildGetRequest(url);
			HttpResponse httpResponse = request.execute();
			//System.out.println(parser.parse(httpResponse.parseAsString()));
			topic = (JSONObject)parser.parse(httpResponse.parseAsString());	
			JSONObject prop = (JSONObject) topic.get("property");
			//System.out.println(prop);
			//JSONObject free = (JSONObject) prop.get("/architecture/architectural_structure_owner/structures_owned");
			//System.out.println(free);
			JSONObject free = (JSONObject) prop.get("/freebase/object_profile/linkcount");
			
			// get the top-level /type/object/type
			JSONObject type = (JSONObject) prop.get("/type/object/type");
			
			try{
				int i = 0;
				while (true){
					String id = JsonPath.read(type,"$.values["+i+"].id").toString();
					if (TYPE_LIST.contains(id)){
						types.add(id);
					}
					i ++;
				}
			} catch (IndexOutOfBoundsException e){
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		if (types == null || types.size() == 0){
			return false;
		} else{
			return true;
		}
		
		

	}
	
	public void useUtility(){
		JsonUtility utility = new JsonUtility(topic);
		ArrayList<String> utilityList = utility.getAllProperties();
		boolean[] object = new boolean[6];
		for(String s:utilityList){
			
			if(s.equals(TYPE_LIST.get(1))){
				object[0] = true; //book/author
			}else if(s.equals(TYPE_LIST.get(2))||s.equals(TYPE_LIST.get(3))){
				object[1] = true; //"/film/actor","/tv/tv_actor"
			}else if(s.equals(TYPE_LIST.get(4))||s.equals(TYPE_LIST.get(5))){
				object[2] = true; // /organization/organization_founder,/business/board_member
			}else if (s.equals(TYPE_LIST.get(6))){
				object[3] = true; ///sports/sports_league
			}else if(s.equals(TYPE_LIST.get(7))||s.equals(TYPE_LIST.get(8))){
				object[4] = true;///sports/sports_team,/sports/professional_sports_team
			}else if(s.equals(TYPE_LIST.get(0))){
				object[5] = true; // /people/person
			}
		}
		
		StringBuffer b = new StringBuffer();
		b.append(" "); // default
		Person person = null;
		if(object[5] == true){
			person = new Person();
			person.getValue(utility);
			b = new StringBuffer();
			//System.out.println("        ----------------------------------------------------------------------------------------------        ");
			b.append(person.name+"(Person, ");
			if(object[0] == true){
				b.append("Author, ");
			}
			if(object[1] == true){
				b.append("Actor, ");
			}
			if(object[2] == true){
				b.append("Business Person, ");
			}
			if(object[3] == true){
				b.append("League, ");
			}
			if(object[4] == true){
				b.append("Sports Team, ");
			}
			b.deleteCharAt(b.length()-1);
			b.deleteCharAt(b.length()-1);
			b.append(")");
			//System.out.printf("%-8s%-30s%-64s%s\n","","|", b.toString(), "|");
			
			
		}else if(object[3] == true){
			League league = new League();
			league.getValue(utility);
			//System.out.println("        ----------------------------------------------------------------------------------------------        ");
			b = new StringBuffer();
			b.append(league.name+"(");
			if(object[0] == true){
				b.append("Author, ");
			}
			if(object[1] == true){
				b.append("Actor, ");
			}
			if(object[2] == true){
				b.append("Business Person, ");
			}
			if(object[3] == true){
				b.append("League, ");
			}
			if(object[4] == true){
				b.append("Sports Team, ");
			}
			b.deleteCharAt(b.length()-1);
			b.deleteCharAt(b.length()-1);
			b.append(")");
			//System.out.printf("%-8s%-30s%-64s%s\n","","|", b.toString(), "|");
			
			
		}else if(object[4] == true){
			SportsTeam team = new SportsTeam();
			team.getValue(utility);
			//System.out.println("        ----------------------------------------------------------------------------------------------        ");
			b = new StringBuffer();
			b.append(team.name+"(");
			if(object[0] == true){
				b.append("Author, ");
			}
			if(object[1] == true){
				b.append("Actor, ");
			}
			if(object[2] == true){
				b.append("Business Person, ");
			}
			if(object[3] == true){
				b.append("League, ");
			}
			if(object[4] == true){
				b.append("Sports Team, ");
			}
			b.deleteCharAt(b.length()-1);
			b.deleteCharAt(b.length()-1);
			b.append(")");
			//System.out.printf("%-8s%-30s%-64s%s\n","","|", b.toString(), "|");
			
		}
		
		if(object[5]){
			System.out.println("        ----------------------------------------------------------------------------------------------        ");
			System.out.printf("%-8s%-30s%-64s%s\n","","|", b.toString(), "|");
			System.out.println("        ----------------------------------------------------------------------------------------------        ");
			printTable.printTablePerson(person);
		}
		else{
			System.out.println("        ----------------------------------------------------------------------------------------------        ");
			System.out.printf("%-8s%-30s%-64s%s\n","","|", b.toString(), "|");
			System.out.println("        ----------------------------------------------------------------------------------------------        ");
		}
		
		if(object[0] == true){
			Author author = new Author();
			author.getValue(utility);
			printTable.printTableAuthor(author);
		}
		if(object[1] == true){
			Actor actor = new Actor();
			actor.getValue(utility);
			printTable.printTableFilmActor(actor);
		}
		if(object[2] == true){
			BusinessPerson bp = new BusinessPerson();
			bp.getValue(utility);
			printTable.printTableBussinessPerson(bp);
		}
		if(object[3] == true){
			League league = new League();
			league.getValue(utility);
			printTable.printTableLeague(league);
		}
		if(object[4] == true){
			SportsTeam team = new SportsTeam();
			team.getValue(utility);
			printTable.printTableTeam(team);
		}
		//System.out.println("        ----------------------------------------------------------------------------------------------        ");
		
		/*
		 * private final List<String> TYPE_LIST = Arrays.asList("/people/person", "/book/author", "/film/actor",
	                                        "/tv/tv_actor", "/organization/organization_founder",
	                                        "/business/board_member", "/sports/sports_league",
	                                        "/sports/sports_team", "/sports/professional_sports_team");*/
		
		
		
		
		
		
		
	}

	
}

