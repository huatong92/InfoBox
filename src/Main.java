import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;


public class Main {
	
	public static void main(String args[]) throws Exception{
		//String key = args[0];
		//String query = arg[1];
		
		String key = "AIzaSyAA7sgelI9ZQyWwyEpmD4OYbEc__h8tk60";
		String query = "bill gates";
    	query = URLEncoder.encode(query, "UTF-8");

		SearchApi search = new SearchApi(key);
		List<String> mid = new ArrayList<String>(search.getMid(query));
		TopicApi topic = new TopicApi(key);
		topic.useApi(mid.get(0));
	}
	
}
