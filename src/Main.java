
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class Main {
	private static final String help = "Usage:\n"+
			"1. -key <account_key> -q <query> -t [infobox|question]\n"+
				 "If the type is infobox (i.e., -t infobox) the system tries to find the most relevant entity to the query <query> and create an infobox about it.\n"+
				 "If the type is question (i.e., -t question), the system tries to answer the question if it is of type \"Who created [X]?\".\n"+
				 "Note that the query has to be given as a single parameter.\n"+
			"2. -key <account_key> -f <file_with_queries> -t [infobox|question]"+
				 "If the type is infobox (i.e. -t infobox) the system reads the file <file_with_queries> and treats each line as a query for infobox creation."+ 
				 "If the type is question (i.e., -t question), the system treats each line of the <file_with_queris> files as a \"Who created [X]?\" question and tries to answer it."+
				 "Note that the file name has to be given as a single parameter.\n"+
			"3. -key <account_key>\n"+
				 "A shell is spawned for interactive queries (either for infobox creation or question answering). This functionality is not required for your implementation.\n"+
			"4. --help | -h\n"+
				 "What you see :)\"";
	
	public static void main(String args[]) throws Exception{
		//String key = "AIzaSyAA7sgelI9ZQyWwyEpmD4OYbEc__h8tk60";
		
		// wrong input format
		if (args.length < 1 || !args[0].equals("-key") || args.length < 2){
			System.out.println(help);
			System.exit(0);
		} 
		
		String key = args[1];
		String query = "";
		
		
		// track the next index of args to read
		int index = 3; 
		// record if a explore mode should be used, if format is wrong, use explore mode
		boolean explore = false; 
		// get query
		if (args.length > 2){
			// read query from args
			if (args[2].equals("-q")){
				while (!args[index].equals("-t") && index < args.length){
					query += args[index];
					query += " ";
					index ++;
				}
			}
			// read query from file
			else if (args[2].equals("-f")){
				if (args.length > 3){
					BufferedReader br = new BufferedReader(new FileReader(args[3]));
					index = 4;
				    try {
				        StringBuilder sb = new StringBuilder();
				        String line = br.readLine();
	
				        while (line != null) {
				            sb.append(line);
				            sb.append("\n");
				            line = br.readLine();
				        }
				        query = sb.toString();
				    } finally {
				        br.close();
				    }
				} else {
					explore = true;
				}
			}
			else {
				explore = true;
			}
		
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			
			// decide which mode to use
			if (args.length > index + 1){
				if (args[index].equals("-t")){
					if (args[index + 1].equals("infobox")){
						// print infobox and quit the program
						infobox(query, key);
						System.exit(0);
					} else if (args[index + 1].equals("question")){
						// print question result and quit the program
						String[] queries = query.split("\\s");
						while (true){
							if (queries[0].toLowerCase().equals("who") && queries[1].toLowerCase().equals("created")){
								question(query, key);
								break;
							} 
							else {
								System.out.println("Ask questions like: " + "Who created OBJECT?");
								try {
									query = reader.readLine();
								} catch (IOException e) {
									e.printStackTrace();
								}
							}
						}
						System.exit(0);
					} else {
						explore = true;
					}
				}
			} else {
				explore = true;
			}
			
		}
		// only two parameters passed in 
		else {
			explore = true;
		}
		
		if (explore){
			BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Welcome to infoxbox creator using Freebase knowledge graph.");
			System.out.println("Feel curious? Start exploring...");
			while(true){
				System.out.print("freebase>");
				String str = read.readLine();
				String[] queries = str.split("\\s+");
				if (str.equals("--help") || str.equals("-h")){
					System.out.println(help);
				}
				// use question
				if (queries.length > 2 && queries[0].toLowerCase().equals("who") && queries[1].toLowerCase().equals("created")){
					question(str, key);
				} 
				// use infobox
				else {
					infobox(str, key);
				}
			}
		}
	
	}
	
	// part1: infobox
	public static void infobox(String query, String key){
		SearchApi search = new SearchApi(key);
		List<String> mid = new ArrayList<String>(search.getMid(query));
		TopicApi topic = new TopicApi(key);
		
		if (mid.size() == 0 || mid == null){
			System.out.println("No related information about query ["+query+"] was found!");
			return;
		}
		
		boolean mid_check = false;
		for (int i = 0; i < mid.size(); i++){
			mid_check = topic.useApi(mid.get(i));
			if (mid_check){
				break;
			};
			if (i == 4){
				System.out.println("5 Search API result entries were considered. None of them of a supported type.");
			}
			if (i == 9){
				System.out.println("10 Search API result entries were considered. None of them of a supported type.");
			}
			if (i == 14){
				System.out.println("15 Search API result entries were considered. None of them of a supported type.");
			}
			if (i == 19){
				System.out.println("20 Search API result entries were considered. None of them of a supported type.");
			}
		}
		
		if (!mid_check){
			System.out.println("No related information about query ["+query+"] was found!");
			return;
		}
			
		topic.useUtility();
	}
	
	// part2: question
	public static void question(String query, String key){
		String[] queries = query.split("\\s");
		String queryWord = "";
		for (int i = 2; i < queries.length; i ++){
			queryWord = queryWord + queries[i] + " ";
		}
		queryWord = queryWord.substring(0, queryWord.length()-1);
		if (queryWord.charAt(queryWord.length()-1) == '?'){
			queryWord = queryWord.substring(0, queryWord.length()-1);
		}
		
		MQLReadApi mql = new MQLReadApi(key);
		mql.getInfo(queryWord);
		
			
		
	}
}