import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.jayway.jsonpath.JsonPath;



public class JsonUtility {
	JSONObject topic;
	private final List<String> TYPE_LIST = Arrays.asList("/people/person", "/book/author", "/film/actor",
            "/tv/tv_actor", "/organization/organization_founder",
            "/business/board_member", "/sports/sports_league",
            "/sports/sports_team", "/sports/professional_sports_team");
	
	
	// constructor
	public JsonUtility(JSONObject topicApi){
		topic = topicApi;
	}
	
	public JSONObject getJSONObject(){
		return topic;
	}
	
	
	public ArrayList<String> getAllProperties (){
		/*Get the list of all properties*/
		
		JSONObject prop = (JSONObject) topic.get("property");
		JSONObject type =(JSONObject) prop.get("/type/object/type");
		ArrayList<String> list = new ArrayList<String>();
		try{
			int i = 0;
			while (true){
				String id = JsonPath.read(type,"$.values["+i+"].id").toString();
				if (TYPE_LIST.contains(id)){
					list.add(id);				}
				i ++;
			}
		} catch (IndexOutOfBoundsException e){
		}
		return list;
	}

	// this function read in a detailed type of format /a/b/c,
	// a property takes value like "text", "value"
	// returns a list of String containing values of property in section /a/b/c
	public String getProperty(String detailedType, String property){
		
		JSONObject prop = (JSONObject) topic.get("property");
		JSONObject unit = (JSONObject) prop.get(detailedType);

		StringBuilder res = new StringBuilder();
		String result;

			try {
				int i = 0;
				while (true){
					if (JsonPath.read(unit,"$.values[0]." + property).toString().equals("[]")){
						System.out.println(detailedType);
						return null;
					}
					if (i != 0){
						res.append("\n");
					}
					res.append(JsonPath.read(unit,"$.values["+i+"]." + property).toString());
					i++;
					System.out.println(i + " " + JsonPath.read(unit,"$.values["+i+"]." + property).toString());
					System.out.println(detailedType);
				}
	
				
			} catch (IllegalArgumentException e){
				System.out.println("illegal");
			} catch (IndexOutOfBoundsException e1){
				
			}
	
			if (res.length() > 1){
				result = res.toString();
			}
			else{
				result = null;
			}
			return result;
		//}
		
	}
	
	// detailedType ex: people/person/sibling_s
	// property ex: text
	// innerDetail ex: list of /people/sibling_relationship/sibling and others.
	public List<String> getPropertyComplex(String detailedType, String property, List<String> innerDetail){
		// to store the value extracted
		List<String> res =  new ArrayList<String>();
		
		JSONObject prop = (JSONObject) topic.get("property");
		JSONObject outterType = (JSONObject) prop.get(detailedType);
		JSONArray values = null;
		try{
			values = (JSONArray) outterType.get("values");
			
			for (int i = 0; i < values.size(); i ++){
				JSONObject value = (JSONObject) values.get(i);
				JSONObject innerProp = (JSONObject) value.get("property");
				List<String> prop_keys = new ArrayList<String>();
				prop_keys.addAll(innerProp.keySet());
				
				for (String str : prop_keys){
					
					// if this detail is needed, extract value
					if ( innerDetail.contains(str)){
						JSONObject detail = (JSONObject) innerProp.get(str);
						StringBuilder string = new StringBuilder();
						int count = 0;
						String temp = "";
						
						try{
							while (true){
								temp = JsonPath.read(detail,"$.values["+count+"]." + property).toString();
								if (temp.equals("[]")){
									break;
								}
								string.append(temp);
								string.append(", ");
								count ++;
								
							}
						} catch (IndexOutOfBoundsException e1){
						}

						
						if (count > 1){
							res.add(string.toString().substring(0, string.toString().length()-2));
						}
						else {
							res.add(str.split("/")[3] + " " + temp);
						}
						
					}
					
				}
				// indicate the end of an object
				res.add("*");
			}
		} catch(NullPointerException e){
		}
		
		return res;
	}
	


}
