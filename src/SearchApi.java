
import java.util.ArrayList;
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

public class SearchApi {
	private String key;
	
	public SearchApi(String key){
		this.key = key;
	}
	
	public List<String> getMid(String query){
		List<String> mid = new ArrayList<String>();
	    try {
	    	HttpTransport httpTransport = new NetHttpTransport();
	    	HttpRequestFactory requestFactory = httpTransport.createRequestFactory();
	    	JSONParser parser = new JSONParser();
	    	GenericUrl url = new GenericUrl("https://www.googleapis.com/freebase/v1/search");
	    	url.put("query", query);
	    	url.put("key", key);
	    	HttpRequest request = requestFactory.buildGetRequest(url);
	    	HttpResponse httpResponse = request.execute();
	    	JSONObject response = (JSONObject)parser.parse(httpResponse.parseAsString());
	    	JSONArray results = (JSONArray)response.get("result");
	    	for (Object result : results) {
	    		mid.add(JsonPath.read(result,"$.mid").toString());
	    	}
	    } catch (Exception ex) {
	    	ex.printStackTrace();
	    }
	    return mid;
	} 
}


