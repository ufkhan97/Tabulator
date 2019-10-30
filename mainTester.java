//For Testing

public class mainTester {

	public static void main(String[] args) {
		int sampleTeams[] = {1130, 1131, 1301, 1551,1559,1577};
		String teamSchools[] = {"Emory", "Emory","UVA","Duke","Duke","UGA"};
		char designations[] = {'A','B','A','A','B','A'};
		Tabulator tab = new Tabulator(sampleTeams, teamSchools, designations);
		tab.setRound(1);
		tab.randomPair();
		
		/** Royal Tab Summary 
		tab.setRound(1); 
		tab.pairTwoTeams(1130, 1301);
		tab.pairTwoTeams(1131, 1577);
		tab.pairTwoTeams(1551, 1559);
		tab.updateBallots(1130, -18, 6);
		tab.updateBallots(1131, 6, 6);
		tab.updateBallots(1301, +18, -6);
		tab.updateBallots(1551, 6, -10);
		tab.updateBallots(1559, -6, 10);
		tab.updateBallots(1577, -6, -6);
		tab.updateCS();
		tab.updateOCS();
		
		tab.nextRound(); //Round 2
		tab.pairTwoTeams(1130, 1559);
		tab.pairTwoTeams(1131, 1551);
		tab.pairTwoTeams(1301, 1577);
		tab.updateBallots(1130, 3, 3);
		tab.updateBallots(1131, 5, 8);
		tab.updateBallots(1301, -18, -12);
		tab.updateBallots(1551, -5, -8);
		tab.updateBallots(1559, -3, -3);
		tab.updateBallots(1577, 18, 12);
		tab.updateCS();
		tab.updateOCS();
		**/
		
		System.out.println(tab);
		for(int teamNumber : sampleTeams) {
			Team t = tab.getTeam(teamNumber);
			System.out.println(t);
		}
		
		
		
	}

}
