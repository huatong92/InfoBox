import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.jayway.jsonpath.JsonPath;

import java.io.FileInputStream;
import java.util.Properties;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class TopicApi {
	private String key;
	
	public TopicApi(String key){
		this.key = key;

	}
	
	public void useApi(String mid){
		try {
			HttpTransport httpTransport = new NetHttpTransport();
			HttpRequestFactory requestFactory = httpTransport.createRequestFactory();
			JSONParser parser = new JSONParser();
			GenericUrl url = new GenericUrl("https://www.googleapis.com/freebase/v1/topic" + mid);
				url.put("key", key);
				HttpRequest request = requestFactory.buildGetRequest(url);
				HttpResponse httpResponse = request.execute();
				JSONObject topic = (JSONObject)parser.parse(httpResponse.parseAsString());
				//System.out.println(JsonPath.read(topic,"$.property['/organization/organization_founder/organizations_founded'].values[0].text").toString());
				System.out.println(JsonPath.read(topic,"$.property['/type/object/type'].values[0].id").toString());
		} catch (Exception ex) {
			ex.printStackTrace();
		}	
	}
}

