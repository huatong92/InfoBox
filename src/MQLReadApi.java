
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.jayway.jsonpath.JsonPath;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class MQLReadApi {
	private String key;
	private JSONObject response;
	
	public MQLReadApi(String key){
		this.key = key;
	}

	// use HTTP request to get json page
	public void getJSON(String query){
	    try {
	    	HttpTransport httpTransport = new NetHttpTransport();
	    	HttpRequestFactory requestFactory = httpTransport.createRequestFactory();
	    	JSONParser parser = new JSONParser();
	    	GenericUrl url = new GenericUrl("https://www.googleapis.com/freebase/v1/mqlread");
	    	url.put("query", query);
	    	url.put("key", key);
	    	HttpRequest request = requestFactory.buildGetRequest(url);
	    	HttpResponse httpResponse = request.execute();
	    	response = (JSONObject)parser.parse(httpResponse.parseAsString());
	    	
	    } catch (Exception ex) {
	    	ex.printStackTrace();
	    }
	    
	} 
	
	// get json info and sort them alphabetically
	public void getInfo(String queryWord){
		String query1 = "[{\"/organization/organization_founder/organizations_founded\": [{"+
				"\"a:name\": null,\"name~=\": \""+ queryWord +"\"}],\"id\": null,\"name\": null,"+
				"\"type\": \"/organization/organization_founder\"}]";
		String query2 = "[{\"/book/author/works_written\": [{\"a:name\": null,\"name~=\": \""+ queryWord +"\""+
				  "}], \"id\": null,\"name\": null,\"type\": \"/book/author\"}]";
    	//query = URLEncoder.encode(query, "UTF-8");
    	MQLReadApi mql = new MQLReadApi(key);
    	// "1" represent business person, "2" represent author
    	mql.getJSON(query1);
    	HashMap<String, ArrayList<String>> map = mql.getCreator("1");
    	mql.getJSON(query2);
    	HashMap<String, ArrayList<String>> map1 = mql.getCreator("2");
    	map.putAll(map1);
    	
    	// sort names alphabetically
    	int size = map.keySet().size();
    	if (size > 0){
        	String[] keys = new String[size];
        	List<String> keylist = new ArrayList<String>(map.keySet());
        	for (int i = 0; i < size; i ++ ){
        		keys[i] = keylist.get(i).substring(1);
        	};
        	Arrays.sort(keys);
        	ArrayList<String> creatorList = new ArrayList<String>();
        	ArrayList<ArrayList<String>> creationList = new ArrayList<ArrayList<String>>();
        	String last = keys[0];
        	for(int i = 0; i < keys.length; i++){
        		if (!keys[i].equals(last)){
        			String keyset = "1" + keys[i];
            		if (map.containsKey(keyset)){
            			creatorList.add(keyset);
            			creationList.add(map.get(keyset));
            		} 
            		keyset = "2" + keys[i];
            		if (map.containsKey(keyset)){
            			creatorList.add(keyset);
            			creationList.add(map.get(keyset));
            		}
        		}
        		
        		last = keys[i];
        	}
        	
        	printTable.printTableAuthor2(queryWord, creatorList, creationList);
    	}
    	else{
    		System.out.println("Sorry, no information is found about your question.");
    	}
	}
	
	// get creator name and creation using json parser
	public HashMap<String, ArrayList<String>> getCreator(String type){
		HashMap<String, ArrayList<String>> res = new HashMap<String, ArrayList<String>>();
		JSONArray result = null;
		try{
			result = (JSONArray) response.get("result");
			for (int i = 0; i < result.size(); i ++){
				// get name of creators, put into hashmap
				String name = JsonPath.read(response,"$.result["+i+"]." + "name").toString();
				ArrayList<String> list = new ArrayList<String>();
				
				// get creations
				JSONObject value = (JSONObject) result.get(i);
				int count = 0;
				while(true){
					try{
						String detailType = "";
						if (type.equals("2")){
							detailType = "/book/author/works_written";
						}
						else{
							detailType = "/organization/organization_founder/organizations_founded";
						}
						String creation = JsonPath.read(value,"$."+ detailType +"["+ count +"]." + "a:name").toString();
						list.add(creation);
						count ++;
					} catch (IndexOutOfBoundsException e){
						break;
					}
				}
				res.put(type+name, list);

				
			}
		} catch(NullPointerException e){
		}
		
		
		
		return res;
	}
}

