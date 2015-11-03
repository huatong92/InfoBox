import java.util.ArrayList;

public class printTable {
	
	/*Print Person Table*/
	public static void printTablePerson(Person personInfo){
		//Name, Birthday, Place of Birth, Death(Place, Date, Cause), Siblings, Spouses, Description
		
		if(personInfo.name != null){
			System.out.printf("%-8s%-21s%-73s%s\n","","| Name:", personInfo.name, "|");
			System.out.println("        ----------------------------------------------------------------------------------------------        ");
			
		}
		if(personInfo.birthday != null){
			System.out.printf("%-8s%-21s%-73s%s\n","","| Birthday:", personInfo.birthday, "|");
			System.out.println("        ----------------------------------------------------------------------------------------------        ");

		}
		if(personInfo.placeOfBirth != null){
			System.out.printf("%-8s%-21s%-73s%s\n","","| Place of birth:", personInfo.placeOfBirth, "|");
			System.out.println("        ----------------------------------------------------------------------------------------------        ");

		}
		if(personInfo.deathPlace != null){
			System.out.printf("%-8s%-21s%-73s%s\n","","| Place of death:", personInfo.deathPlace, "|");
			System.out.println("        ----------------------------------------------------------------------------------------------        ");

		}
		if(personInfo.deathDate != null){
			System.out.printf("%-8s%-21s%-73s%s\n","","| Date of death:", personInfo.deathDate, "|");
			System.out.println("        ----------------------------------------------------------------------------------------------        ");

		}
		if(personInfo.deathCause != null){
			System.out.printf("%-8s%-21s%-73s%s\n","","| Cause of death", personInfo.deathCause, "|");
			System.out.println("        ----------------------------------------------------------------------------------------------        ");

		}
		if(personInfo.siblings != null){
			String[] temp2 = personInfo.siblings.split("\n");
			for(int i=0; i<temp2.length;i++){
				if(i == 0){
					System.out.printf("%-8s%-21s%-73s%s\n","","| Siblings:", temp2[i], "|");
				}else{
					System.out.printf("%-8s%-21s%-73s%s\n","","|", temp2[i], "|");
				}
			}
			System.out.println("        ----------------------------------------------------------------------------------------------        ");

			
		}
		if(personInfo.spouses != null){
			String[] temp1 = personInfo.spouses.split("\n");
			for(int i=0; i<temp1.length;i++){
				if(i == 0){
					System.out.printf("%-8s%-21s%-73s%s\n","","| Spouses:", temp1[i], "|");
				}else{
					System.out.printf("%-8s%-21s%-73s%s\n","","|", temp1[i], "|");
				}
			}
			System.out.println("        ----------------------------------------------------------------------------------------------        ");

		}
		if(personInfo.description != null){			
			String[] descriptionArray =personInfo.description.split("\n");
			ArrayList<String> desciptionList = new ArrayList<String>();
			for(int i=0; i<descriptionArray.length; i++){
				descriptionArray[i] = descriptionArray[i].replaceAll("(.{1,73})", "$1\n"); //splits a string into lines of 25 characters max by whitespace
				String[] temp = descriptionArray[i].split("\n");
				for(int j=0; j<temp.length;j++){
					desciptionList .add(temp[j]);
				}
				
			}
			
			for(int k=0; k<desciptionList .size();k++){
				if(k==0){
					System.out.printf("%-8s%-21s%-73s%s\n","","| Description:", desciptionList .get(k), "|");
				}else{
					System.out.printf("%-8s%-21s%-73s%s\n","","|", desciptionList .get(k), "|");
				}
			}
			System.out.println("        ----------------------------------------------------------------------------------------------        ");
			
		}
		
	}
	
	/*Print Author Table*/
	public static void printTableAuthor(Author authorInfo){
		if(authorInfo.books != null){
			String[] booksArray = authorInfo.books.split("\n");
			for(int i=0; i<booksArray.length;i++){
				if(i == 0){
					System.out.printf("%-8s%-21s%-73s%s\n","","| Books:", booksArray[i], "|");
				}else{
					System.out.printf("%-8s%-21s%-73s%s\n","","|", booksArray[i], "|");
				}
			}
			
			System.out.println("        ----------------------------------------------------------------------------------------------        ");

		}
		if(authorInfo.booksAbout != null){			
			String[] booksAboutArray =authorInfo.booksAbout.split("\n");
			ArrayList<String> booksAboutList = new ArrayList<String>();
			for(int i=0; i<booksAboutArray.length; i++){
				booksAboutArray[i] = booksAboutArray[i].replaceAll("(.{1,74})", "$1\n"); //splits a string into lines of 25 characters max by whitespace
				String[] temp = booksAboutArray[i].split("\n");
				for(int j=0; j<temp.length;j++){
					booksAboutList.add(temp[j]);
				}
				
			}
			
			for(int k=0; k<booksAboutList.size();k++){
				if(k==0){
					System.out.printf("%-8s%-21s%-73s%s\n","","| Books About:", booksAboutList.get(k), "|");
				}else{
					System.out.printf("%-8s%-21s%-73s%s\n","","|", booksAboutList.get(k), "|");
				}
			}
			
			System.out.println("        ----------------------------------------------------------------------------------------------        ");

			
		}
		if(authorInfo.influenced != null){
			String[] influenced = authorInfo.influenced.split("\n");
			for(int i=0; i<influenced.length; i++){
				if(i == 0){
					System.out.printf("%-8s%-21s%-73s%s\n","","| Influenced:", influenced[i], "|");
				}else{
					System.out.printf("%-8s%-21s%-73s%s\n","","|", influenced[i], "|");
				}
				
			}
			System.out.println("        ----------------------------------------------------------------------------------------------        ");

		}
		if(authorInfo.influencedBy != null){
			String[] influencedByArray = authorInfo.influencedBy.split("\n");
			for(int i=0; i<influencedByArray.length;i++){
				if(i == 0){
					System.out.printf("%-8s%-21s%-73s%s\n","","| Influenced by:", influencedByArray[i], "|");
				}else{
					System.out.printf("%-8s%-21s%-73s%s\n","","|", influencedByArray[i], "|");
				}
			}
			System.out.println("        ----------------------------------------------------------------------------------------------        ");

		}
		
	}
	
	/*Print filmActor Table*/
	public static void printTableFilmActor(Actor actorInfo){
		
		System.out.printf("%-8s%-21s%-36s%-37s%s\n","","| Films:","Character" , "FilmName","|");
		System.out.println("        ----------------------------------------------------------------------------------------------        ");
		
		for(int i=0; i<actorInfo.filmName.size(); i++){
			System.out.printf("%-8s%-21s%-36s%-37s%s\n","","|",actorInfo.character.get(i),actorInfo.filmName.get(i),"|");
		}
		System.out.println("        ----------------------------------------------------------------------------------------------        ");

	}
	
	/*Print tvActor Table*/
	public static void printTableTVActor(Actor actorInfo){
		
		System.out.printf("%-8s%-21s%-36s%-37s%s\n","","| TV:","Character" , "TVName","|");
		System.out.println("        ----------------------------------------------------------------------------------------------        ");
		
		for(int i=0; i<actorInfo.tvName.size(); i++){
			System.out.printf("%-8s%-21s%-36s%-37s%s\n","","|",actorInfo.tvCharacter.get(i),actorInfo.tvName.get(i),"|");
		}
		System.out.println("        ----------------------------------------------------------------------------------------------        ");

	}
	
	/*Print BusinessPerson Table*/
	//Leadership(From, To, Organization, Role, Title), BoardMember(From, To, Organization,Role, Title), Founded(OrganizationName)
	public static void printTableBussinessPerson(BusinessPerson bpInfo){
		
		
		if(bpInfo.leaderOrganization != null && bpInfo.leaderOrganization.size()!=0){
			System.out.printf("%-8s%-21s%-22s%-17s%-17s%-17s%s\n","","| LeaderShip:","Organization" , "Role","Title","From-To","|");
			System.out.println("        ----------------------------------------------------------------------------------------------        ");
			for(int i=0;i<bpInfo.leaderOrganization.size();i++){
				if(bpInfo.leaderOrganization.get(i).length()>16){
					StringBuffer b1 = new StringBuffer();
					b1.append(bpInfo.leaderOrganization.get(i).substring(0,16)+"...");
					bpInfo.leaderOrganization.set(i,b1.toString());	
				}
				if(bpInfo.leaderRole.get(i).length()>12){
					StringBuffer b2 = new StringBuffer();
					b2.append(bpInfo.leaderRole.get(i).substring(0,12)+"...");
					bpInfo.leaderRole.set(i,b2.toString());
				}
				if(bpInfo.leaderTitle.get(i).length()>12){
					StringBuffer b3 = new StringBuffer();
					b3.append(bpInfo.leaderTitle.get(i).substring(0,12)+"...");
					bpInfo.leaderTitle.set(i,b3.toString());
				}
				if(bpInfo.leaderFromTo.get(i).length()>12){
					StringBuffer b4 = new StringBuffer();
					b4.append(bpInfo.leaderFromTo.get(i).substring(0,12)+"...");
					bpInfo.leaderFromTo.set(i,b4.toString());
				}
				
				System.out.printf("%-8s%-21s%-22s%-17s%-17s%-17s%s\n","","|",bpInfo.leaderOrganization.get(i), bpInfo.leaderRole.get(i),bpInfo.leaderTitle.get(i),bpInfo.leaderFromTo.get(i),"|");	
			}
			System.out.println("        ----------------------------------------------------------------------------------------------        ");

			
		}
		if(bpInfo.boardOrganization != null && bpInfo.boardOrganization.size()!=0){
			System.out.printf("%-8s%-21s%-22s%-17s%-17s%-17s%s\n","","| BoardMember:","Organization" , "Role","Title","From-To","|");
			System.out.println("        ----------------------------------------------------------------------------------------------        ");
			for(int i=0; i<bpInfo.boardOrganization.size();i++){
				if(bpInfo.boardOrganization.get(i).length()>16){
					StringBuffer b1 = new StringBuffer();
					b1.append(bpInfo.boardOrganization.get(i).substring(0,16)+"...");
					bpInfo.boardOrganization.set(i,b1.toString());	
				}
				if(bpInfo.boardRole.get(i).length()>12){
					StringBuffer b2 = new StringBuffer();
					b2.append(bpInfo.boardRole.get(i).substring(0,12)+"...");
					bpInfo.boardRole.set(i,b2.toString());
				}
				if(bpInfo.boardTitle.get(i).length()>12){
					StringBuffer b3 = new StringBuffer();
					b3.append(bpInfo.boardTitle.get(i).substring(0,12)+"...");
					bpInfo.boardTitle.set(i,b3.toString());
				}
				if(bpInfo.boardFromTo.get(i).length()>12){
					StringBuffer b4 = new StringBuffer();
					b4.append(bpInfo.boardFromTo.get(i).substring(0,12)+"...");
					bpInfo.boardFromTo.set(i,b4.toString());
				}
				System.out.printf("%-8s%-21s%-22s%-17s%-17s%-17s%s\n","","|",bpInfo.boardOrganization.get(i), bpInfo.boardRole.get(i),bpInfo.boardTitle.get(i),bpInfo.boardFromTo.get(i),"|");
			}
			System.out.println("        ----------------------------------------------------------------------------------------------        ");

		}
		
		if(bpInfo.founded != null){
			String[] foundedArray = bpInfo.founded.split("\n");
			for(int i=0; i<foundedArray.length;i++){
				if(i == 0){
					System.out.printf("%-8s%-21s%-73s%s\n","","| Founded:", foundedArray[i], "|");
				}else{
					System.out.printf("%-8s%-21s%-73s%s\n","","|", foundedArray[i], "|");
				}
			}
			System.out.println("        ----------------------------------------------------------------------------------------------        ");

		}
		
		
	}
	
	/*Print League Table*/
	public static void printTableLeague(League leagueInfo){
		
		if(leagueInfo.name != null){
			System.out.printf("%-8s%-21s%-73s%s\n","","| Name:", leagueInfo.name, "|");
			System.out.println("        ----------------------------------------------------------------------------------------------        ");

		}
		if(leagueInfo.championship != null){			
			
			String[] championArray =leagueInfo.championship.split("\n");
			ArrayList<String> championList = new ArrayList<String>();
			for(int i=0; i<championArray.length; i++){
				championArray[i] = championArray[i].replaceAll("(.{1,74})", "$1\n"); //splits a string into lines of 25 characters max by whitespace
				String[] temp = championArray[i].split("\n");
				for(int j=0; j<temp.length;j++){
					championList.add(temp[j]);
				}
				
			}
			
			for(int k=0; k<championList.size();k++){
				if(k==0){
					System.out.printf("%-8s%-21s%-73s%s\n","","| Championships:", championList.get(k), "|");
				}else{
					System.out.printf("%-8s%-21s%-73s%s\n","","|", championList.get(k), "|");
				}
			}
			
			System.out.println("        ----------------------------------------------------------------------------------------------        ");

		}
		if(leagueInfo.sport != null){
			System.out.printf("%-8s%-21s%-73s%s\n","","| Sport:", leagueInfo.sport, "|");
			System.out.println("        ----------------------------------------------------------------------------------------------        ");

		}
		if(leagueInfo.slogan != null){
			System.out.printf("%-8s%-21s%-73s%s\n","","| Slogan:", leagueInfo.slogan, "|");
			System.out.println("        ----------------------------------------------------------------------------------------------        ");

		}
		if(leagueInfo.officialWebsite != null){
			System.out.printf("%-8s%-21s%-73s%s\n","","| Official Website :", leagueInfo.officialWebsite, "|");
			System.out.println("        ----------------------------------------------------------------------------------------------        ");

		}
		if(leagueInfo.teams != null){
			for(int i=0; i<leagueInfo.teams.size();i++){
				if(i==0){
					System.out.printf("%-8s%-21s%-73s%s\n","","| Team:", leagueInfo.teams.get(i), "|");
				}else{
					System.out.printf("%-8s%-21s%-73s%s\n","","|", leagueInfo.teams.get(i), "|");
				}
			}
			System.out.println("        ----------------------------------------------------------------------------------------------        ");

		}
		if(leagueInfo.description != null){
			
			String[] descriptionArray =leagueInfo.description.split("\n");
			ArrayList<String> desciptionList = new ArrayList<String>();
			for(int i=0; i<descriptionArray.length; i++){
				descriptionArray[i] = descriptionArray[i].replaceAll("(.{1,73})", "$1\n"); //splits a string into lines of 25 characters max by whitespace
				String[] temp = descriptionArray[i].split("\n");
				for(int j=0; j<temp.length;j++){
					desciptionList .add(temp[j]);
				}
				
			}
			
			for(int k=0; k<desciptionList .size();k++){
				if(k==0){
					System.out.printf("%-8s%-21s%-73s%s\n","","| Description:", desciptionList .get(k), "|");
					//System.out.println("        | Championships:      "+championArray[i]);
				}else{
					System.out.printf("%-8s%-21s%-73s%s\n","","|", desciptionList .get(k), "|");
					//System.out.println("        |                    "+championArray[i]);
				}
			}

			System.out.println("        ----------------------------------------------------------------------------------------------        ");

		}
		
		
		
	}
	
	/*Print SportsTeam Table*/
	public static void printTableTeam(SportsTeam teamInfo){
		if(teamInfo.name != null){
			System.out.printf("%-8s%-21s%-73s%s\n","","| Name:", teamInfo.name, "|");
			System.out.println("        ----------------------------------------------------------------------------------------------        ");

		}
		if(teamInfo.sport != null){
			System.out.printf("%-8s%-21s%-73s%s\n","","| Sport:", teamInfo.sport, "|");
			System.out.println("        ----------------------------------------------------------------------------------------------        ");

		}
		if(teamInfo.arena != null){
			System.out.printf("%-8s%-21s%-73s%s\n","","| Name:", teamInfo.arena, "|");
			System.out.println("        ----------------------------------------------------------------------------------------------        ");

		}
		if(teamInfo.championships != null){
			
			String[] championArray =teamInfo.championships.split("\n");
			ArrayList<String> championList = new ArrayList<String>();
			for(int i=0; i<championArray.length; i++){
				championArray[i] = championArray[i].replaceAll("(.{1,74})", "$1\n"); //splits a string into lines of 25 characters max by whitespace
				String[] temp = championArray[i].split("\n");
				for(int j=0; j<temp.length;j++){
					championList.add(temp[j]);
				}
				
			}
			
			for(int k=0; k<championList.size();k++){
				if(k==0){
					System.out.printf("%-8s%-21s%-73s%s\n","","| Championships:", championList.get(k), "|");
					//System.out.println("        | Championships:      "+championArray[i]);
				}else{
					System.out.printf("%-8s%-21s%-73s%s\n","","|", championList.get(k), "|");
					//System.out.println("        |                    "+championArray[i]);
				}
			}
			System.out.println("        ----------------------------------------------------------------------------------------------        ");

			
		}
		if(teamInfo.founded != null){
			System.out.printf("%-8s%-21s%-73s%s\n","","| Founded:", teamInfo.founded, "|");
			System.out.println("        ----------------------------------------------------------------------------------------------        ");

		}
		if(teamInfo.leagues != null){
			for(int i=0; i<teamInfo.leagues.size();i++){
				if(i==0){
					System.out.printf("%-8s%-21s%-73s%s\n","","| League:", teamInfo.leagues.get(i), "|");
				}else{
					System.out.printf("%-8s%-21s%-73s%s\n","","|", teamInfo.leagues.get(i), "|");
				}
			}
			System.out.println("        ----------------------------------------------------------------------------------------------        ");

		}
		if(teamInfo.locations != null){
			System.out.printf("%-8s%-21s%-73s%s\n","","| Locations:", teamInfo.locations, "|");
			System.out.println("        ----------------------------------------------------------------------------------------------        ");

		}
		if(teamInfo.coachName != null){
			System.out.printf("%-8s%-21s%-25s%-25s%-23s%s\n","","| Coaches:","Name" , "Position","From-To","|");
			System.out.println("        ----------------------------------------------------------------------------------------------        ");
			for(int i=0; i<teamInfo.coachName.size();i++){
				System.out.printf("%-8s%-21s%-25s%-25s%-23s%s\n","","|", teamInfo.coachName.get(i), teamInfo.coachPosition.get(i),teamInfo.coachFromTo.get(i),"|");
			}
			System.out.println("        ----------------------------------------------------------------------------------------------        ");

		}
		if(teamInfo.playerName != null){
			System.out.printf("%-8s%-21s%-21s%-21s%-13s%-18s%s\n","","| PlayersRoster:","Name" , "Position","Number","From/To", "|");
			System.out.println("        ----------------------------------------------------------------------------------------------        ");
			for(int i=0; i<teamInfo.playerName.size();i++){
				if(teamInfo.playerName.get(i).length()>18){
					StringBuffer b1 = new StringBuffer();
					b1.append(teamInfo.playerName.get(i).substring(0,18)+"...");
					teamInfo.playerName.set(i,b1.toString());	
				}
				if(teamInfo.playerPosition.get(i).length()>16){
					StringBuffer b2 = new StringBuffer();
					b2.append(teamInfo.playerPosition.get(i).substring(0,16)+"...");
					teamInfo.playerPosition.set(i,b2.toString());
				}
				if(teamInfo.playerFromTo.get(i).length()>13){
					StringBuffer b3 = new StringBuffer();
					b3.append(teamInfo.playerFromTo.get(i).substring(0,13)+"...");
					teamInfo.playerFromTo.set(i,b3.toString());
				}
				System.out.printf("%-8s%-21s%-21s%-21s%-13s%-18s%s\n","","|",teamInfo.playerName.get(i), teamInfo.playerPosition.get(i),teamInfo.playerNumber.get(i),teamInfo.playerFromTo.get(i),"|");
			}
			System.out.println("        ----------------------------------------------------------------------------------------------        ");

		}
		if(teamInfo.description != null){
			String[] temp =teamInfo.description.split("\n");
			for(int i=0; i<temp.length; i++){
				temp[i] = temp[i].replaceAll("(.{1,73})", "$1|\n        |                    "); //splits a string into lines of 25 characters max by whitespace
				if(i==0){
					System.out.printf("%-8s%-21s%-73s%s\n","","| Descriptions:", temp[i], "|");
				}else{
					System.out.printf("%-8s%-21s%-73s%s\n","","|", temp[i], "|");
				}
			}
			System.out.println("        ----------------------------------------------------------------------------------------------        ");

			
		}
		
	}
		
	/*Print Part 2 Table*/
	public static void printTableAuthor2(String query, ArrayList<String> creator, ArrayList<ArrayList<String>> creation){
		System.out.println("        ----------------------------------------------------------------------------------------------        ");
		System.out.printf("%-8s%-35s%-58s%s\n","","|","Who Created "+query , "|");
		
		// a boolean to check if the name of the creator has appeared, names are sorted
		boolean sameEntity = false;
		if(creator.size() != 0){
			for(int k = 0; k < creator.size(); k ++){
				String key = creator.get(k);
				if (!sameEntity){
					System.out.println("        ----------------------------------------------------------------------------------------------        ");
					System.out.printf("%-8s%-26s%-21s%-46s%s\n","","| "+key.substring(1),"|As" , "|Creation","|");
					System.out.println("                                  --------------------------------------------------------------------        ");
				} 
				
				// if this name is the same as the next name
				if ( k + 1 < creator.size()){
					String next = creator.get(k+1).substring(1);
					if ((key.substring(1)).equals(next)){
						sameEntity = true;
					} else {
						sameEntity = false;
					}

				}
			
				for(int i=0; i< creation.get(k).size();i++){
					ArrayList<String> list = creation.get(k);
					if( i==0){
						if(list.get(i).length()>33){
							StringBuffer b1 = new StringBuffer();
							b1.append(list.get(i).substring(0,33)+"...");
							list.set(i,b1.toString());	
						}
						if(key.charAt(0) == '2'){
							System.out.printf("%-8s%-26s%-21s%-46s%s\n","","|","|Author" , "|"+list.get(i),"|");
						}else if(key.charAt(0) == '1'){
							System.out.printf("%-8s%-26s%-21s%-46s%s\n","","|","|Business Person" , "|"+list.get(i),"|");
						}
						
					}
					else {
						if(list.get(i).length()>33){
							StringBuffer b1 = new StringBuffer();
							b1.append(list.get(i).substring(0,33)+"...");
							list.set(i,b1.toString());	
						}
						if(key.charAt(0) == '2'){
							System.out.printf("%-8s%-26s%-21s%-46s%s\n","","|","|" , "|"+list.get(i),"|");
						}else if(key.charAt(0) == '1'){
							System.out.printf("%-8s%-26s%-21s%-46s%s\n","","|","|" , "|"+list.get(i),"|");
						}
					}
				}
				
			}
			
		}
		System.out.println("        ----------------------------------------------------------------------------------------------        ");
		
	}
		
	
	
}
