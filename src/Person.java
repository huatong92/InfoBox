import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Person {
	public String name;
	public String birthday;
	public String placeOfBirth;
	public String deathPlace;
	public String deathDate;
	public String deathCause;
	public String siblings;
	public String spouses;
	public String description; 
	private final List<String> siblings_detail = Arrays.asList("/people/sibling_relationship/sibling");
	private final List<String> spouses_detail = Arrays.asList("/people/marriage/from", 
			"/people/marriage/location_of_ceremony", "/people/marriage/spouse", "/people/marriage/to");
	
	public void getValue(JsonUtility json){
		
		description = json.getProperty("/common/topic/description", "value");
		name = json.getProperty("/type/object/name", "value");
		deathCause = json.getProperty("/people/deceased_person/cause_of_death", "text");
		deathDate = json.getProperty("/people/deceased_person/date_of_death", "text");
		deathPlace = json.getProperty("/people/deceased_person/place_of_death", "text");
		birthday = json.getProperty("/people/person/date_of_birth", "text");
		placeOfBirth = json.getProperty("/people/person/place_of_birth", "text");
		
		// get siblings
		List<String> siblinglist = json.getPropertyComplex("/people/person/sibling_s", "text", siblings_detail);
		StringBuilder info = new StringBuilder();
		// format the string
		for(int i = 0; i < siblinglist.size(); i ++){
			String str = siblinglist.get(i);
			if (!str.equals("*")){
				info.append(str.substring(8));
				if (i < (siblinglist.size()-2)){
					info.append("\n");
				}
			}
			
		}
		siblings = info.toString();
		if (siblings.equals("")){
			siblings = null;
		}

		// get spouse
		List<String> spouselist = json.getPropertyComplex("/people/person/spouse_s", "text", spouses_detail);
		List<String[]> valuelist = new ArrayList<String[]>();
		List<String[]> keylist = new ArrayList<String[]>();
		
		// reorder spouse information
		if (spouselist.size()>0){
			String[] key = new String[4];
			String[] value = new String[4];
			for (int k = 0; k < spouselist.size(); k ++){
				if (!spouselist.get(k).equals("*")){
					key[k%4] = spouselist.get(k).split(" ")[0];
					value[k%4] = spouselist.get(k).substring(key[k%4].length());
				}
				else{
					valuelist.add(value);
					keylist.add(key);
					key = new String[4];
					value = new String[4];
				}
				
			}
		}
		
		info = new StringBuilder();

		for (int k = 0; k < valuelist.size(); k ++){
			String[] value = valuelist.get(k);
			String [] key = keylist.get(k);
			String[] newValue = new String[4];
			// put the values in order for printing
			for (int i = 0; i < 4; i ++){
				if (key[i].equals("spouse")){
					newValue[0] = value[i];
				}
				else if (key[i].equals("from")){
					newValue[1] = value[i];
				}
				else if (key[i].equals("to")){
					newValue[2] = value[i];
				}
				else if (key[i].equals("location_of_ceremony")){
					newValue[3] = value[i];
				}
			}
			
			// store the info in order to spouse
			if (newValue[0].equals(" []")){
				info.append("unknown");
			}else{
				info.append(newValue[0].substring(1));
			}
			
			if (newValue[1].equals(" []")){
				info.append(" ( - ");
			} else{
				info.append(" (");
				info.append(newValue[1]);
				info.append(" - ");
			}
			if (newValue[2].equals(" []")){
				info.append("now) ");
			} else{
				info.append(newValue[2]);
				info.append(" )");
			}
			if (!newValue[3].equals(" []")){
				info.append(" @ ");
				info.append(newValue[3]);
			} 
			if (k < valuelist.size()-1){
				info.append("\n");
			}
			
		}
		
		spouses = info.toString();
		if (spouses.equals("")){
			spouses = null;
		}
		
	}
}
