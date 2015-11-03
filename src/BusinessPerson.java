import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BusinessPerson {
	//leadership
	public List<String> leaderFromTo;
	public List<String> leaderOrganization;
	public List<String> leaderRole;
	public List<String> leaderTitle;
	
	//board member
	public List<String> boardFromTo;
	public List<String> boardOrganization;
	public List<String> boardRole;
	public List<String> boardTitle;
	
	public String founded;
	
	private final List<String> leader_detail = Arrays.asList("/organization/leadership/from", 
			"/organization/leadership/to","/organization/leadership/organization",
			"/organization/leadership/role","/organization/leadership/title"); 
	
	private final List<String> board_detail = Arrays.asList("/organization/organization_board_membership/from", 
			"/organization/organization_board_membership/to","/organization/organization_board_membership/organization",
			"/organization/organization_board_membership/role","/organization/organization_board_membership/title");
	
	
	public BusinessPerson(){
		leaderFromTo = new ArrayList<String>();
		leaderOrganization = new ArrayList<String>();
		leaderRole = new ArrayList<String>();
		leaderTitle = new ArrayList<String>();

		boardFromTo = new ArrayList<String>();
		boardOrganization = new ArrayList<String>();
		boardRole = new ArrayList<String>();
		boardTitle = new ArrayList<String>();
	}
	
	public void getValue(JsonUtility json){
		List<String> leaderFrom = new ArrayList<String>();
		List<String> leaderTo = new ArrayList<String>();
		List<String> boardFrom = new ArrayList<String>();
		List<String> boardTo = new ArrayList<String>();
		
		founded = json.getProperty("/organization/organization_founder/organizations_founded", "text");
		
		//leadership
		List<String> leader = json.getPropertyComplex("/business/board_member/leader_of", "text", leader_detail);

		int count = 0;
		for (String str : leader){
			if ( ! str.equals("*")){
				String temp = str.split(" ")[0];
				if (temp.equals("from")){
					leaderFrom.add(str.substring(temp.length()+1));
				}
				else if (temp.equals("to")){
					leaderTo.add(str.substring(temp.length()+1));
				}
				else if (temp.equals("organization")){
					leaderOrganization.add(str.substring(temp.length()+1));
				}
				else if (temp.equals("role")){
					leaderRole.add(str.substring(temp.length()+1));
				}
				else if (temp.equals("title")){
					leaderTitle.add(str.substring(temp.length()+1));
				}
			}else{
				if (leaderFrom.size() < count + 1){
					leaderFrom.add(" ");
				}
				if (leaderTo.size() < count + 1){
					leaderTo.add(" ");
				}
				if (leaderOrganization.size() < count + 1){
					leaderOrganization.add(" ");
				}
				if (leaderRole.size() < count + 1){
					leaderRole.add(" ");
				}
				if (leaderTitle.size() < count + 1){
					leaderTitle.add(" ");
				}
				count ++;
			}
			
		}
		leaderFromTo = combineFromTo(leaderFrom, leaderTo);

		// board member
		List<String> board = json.getPropertyComplex("/business/board_member/organization_board_memberships", "text", board_detail);
	
		count = 0;
		for (String str: board){
			if ( ! str.equals("*")){
				String temp = str.split(" ")[0];
				if (temp.equals("from")){
					boardFrom.add(str.substring(temp.length()+1));
				}
				else if (temp.equals("to")){
					boardTo.add(str.substring(temp.length()+1));
				}
				else if (temp.equals("organization")){
					boardOrganization.add(str.substring(temp.length()+1));
				}
				else if (temp.equals("role")){
					boardRole.add(str.substring(temp.length()+1));
				}
				else if (temp.equals("title")){
					boardTitle.add(str.substring(temp.length()+1));
				}
			}
			else{
				if (boardFrom.size() < count + 1){
					boardFrom.add(" ");
				}
				if (boardTo.size() < count + 1){
					boardTo.add(" ");
				}
				if (boardOrganization.size() < count + 1){
					boardOrganization.add(" ");
				}
				if (boardRole.size() < count + 1){
					boardRole.add(" ");
				}
				if (boardTitle.size() < count + 1){
					boardTitle.add(" ");
				}
				count ++;
			}
			
		}
		boardFromTo = combineFromTo(boardFrom, boardTo);		
	}
	
	public List<String> combineFromTo(List<String> from, List<String> to){
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < from.size(); i ++){
			list.add(from.get(i) + "/" + to.get(i));
		}
		return list;
	}
}
