// TODO: pairing and ranking (the hard part)
// TODO: design interface, for using this system. 
// TODO: add "printTabSummary" method

//import java.util.Arrays;
import java.util.ArrayList;
import java.util.Random;


public class Tabulator {
	
	private int[][] bracket;
	private ArrayList<Team> teams;
	private int round; 
	//private boolean coin; // Let heads be false, tails be true. Not used for now
	
	
	public Tabulator(int numTeams) { //assume 4 rounds
		this.bracket = new int[numTeams][5];
		this.teams = new ArrayList<Team>(numTeams);
		this.round = 0;
	}
	
	public Tabulator(int teamNumbers[], String schools[], char desigs[]) { //assume 4 rounds
		int numTeams = teamNumbers.length;
		this.bracket = new int[numTeams][5];
		this.teams = new ArrayList<Team>();
		for(int i = 0; i < numTeams; i++) {
			this.bracket[i][0] = teamNumbers[i];
			this.teams.add(new Team(teamNumbers[i], schools[i], desigs[i]));
		}
		this.round = 0; 
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder("Round: ").append(this.round).append("\n");
		sb.append("Bracket:");
		for(int row = 0; row < bracket.length ; row++) {
			sb.append("\n");
			for(int col = 0; col < bracket[0].length; col++) {
					sb.append(this.bracket[row][col]).append(" ");
			}
		}
		sb.append("\n");
		return sb.toString();
	}
	
	
	
	

	public void pairTwoTeams(int team1number, int team2number) {
		//Update Bracket
		int col; 
		for(int row = 0; row < bracket.length; row++) {
			if(bracket[row][0] == team1number) {
				col = round;
				bracket[row][col] = team2number;
			}else if(bracket[row][0] == team2number) {
				col = round;
				bracket[row][col] = team1number;
			}
		}
		//Update Team objects with side
		Team team1 = getTeam(team1number);
		team1.setIsDefense(false); //Team 1 is P
		Team team2 = getTeam(team2number);
		team2.setIsDefense(true); //Team 2 is D
	}
	
	public void randomPair() {
		//Used for Round 1 ONLY to randomly assign all teams an opponent and a side
		//Only impermissible this checks is school
		ArrayList<Team> teamsToPair = new ArrayList<Team>(teams);
		
		Random rnd = new Random();
		Team team1;
		Team team2;
		int[] lastPairedTeams = new int[2];
		
		while(teamsToPair.size() > 0) {	
			System.out.println("Randomly pairing " + teamsToPair.size() + " teams.");
			int team1index = rnd.nextInt(teamsToPair.size());
			team1 = teamsToPair.get(team1index);
			System.out.println("Team 1 is " + team1.getNumber());
			int team2index;
			do {
				team2index = rnd.nextInt(teamsToPair.size());
				team2 = teamsToPair.get(team2index);
				System.out.println("Team 2 is " + team2.getNumber());
			}while (team1.getNumber() == team2.getNumber() || (team1.getSchool() == team2.getSchool() && teamsToPair.size() > 2) );
			
			if(team1.getSchool() == team2.getSchool() && teamsToPair.size() == 2) {
				pairTwoTeams(team1.getNumber(), lastPairedTeams[1]);
				pairTwoTeams(lastPairedTeams[0], team2.getNumber());
				System.out.println("Swapping because Team 1 and 2 were same school");
			} else {
				pairTwoTeams(team1.getNumber(), team2.getNumber());
				lastPairedTeams[0] = team1.getNumber();
				lastPairedTeams[1] = team2.getNumber();
				teamsToPair.remove(team1);
				teamsToPair.remove(team2);
			}
		}	
	}
	
	public boolean isPermissible(Team t1, Team t2) {
		if( t1.getSchool().equals(t2.getSchool()) ) {
			return false;
		}
		if(isPreviousOpp(t1.getNumber(), t2.getNumber()) ) {
			return false;
		}
		return true;
	}
	
	public boolean isPreviousOpp(int t1, int t2) {
		for(int row = 0; row < bracket.length; row++) {
			if(t1 == bracket[row][0]) {
				for(int col = 1; col < bracket[row].length; col++) {
					if(t2 == bracket[row][col]) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public boolean isAlreadyPaired(int teamNumber) {
		int col = round;
		for(int row = 0; row < bracket.length; row++) {
			if(bracket[row][col] == teamNumber) {
				return true;
			}
		}
		return false;
	}
	
	public void updateBallots(int teamNumber, int ballot1, int ballot2) {
		Team t = getTeam(teamNumber);
		t.updateBallots(round, ballot1, ballot2);
	}
	
	public void updateCS(Team t) {
		int num = t.getNumber();
		double cs = 0; 
		for(int row = 0; row < bracket.length; row++) {
			if(bracket[row][0] == num) {
				for(int col = 1; col < bracket[row].length; col++) {
					int opp = bracket[row][col];
					if(opp > 0) { 
						Team temp = getTeam(opp);
						cs += temp.getNumBallots(round);
					}	
				}
			}
		}
		t.setCS(cs);
	}
	
	public void updateCS() { //for every team, when a team is not specified 
		for (Team t : teams) {
			updateCS(t);
		}
	}
	
	public void updateOCS(Team t) {
		int num = t.getNumber();
		double ocs = 0; 
		for(int row = 0; row < bracket.length; row++) {
			if(bracket[row][0] == num) {
				for(int col = 1; col < bracket[row].length; col++) {
					int opp = bracket[row][col];
					if(opp > 0) { 
						Team temp = getTeam(opp);
						ocs += temp.getCS();
					}	
				}
			}
		}
		t.setOCS(ocs);
	}
	
	public void updateOCS() { //for every team, when a team is not specified 
		for (Team t : teams) {
			updateOCS(t);
		}
	}
	
	public Team getTeam(int number) {
		int i = 0;
		while( i < teams.size() ) {
			if(teams.get(i).getNumber() == number) {
				return teams.get(i);
			}
			i++;
		}
		return null;
	}
	
	public int[][] getBracket(){
		return this.bracket;
	}
	
	public void setBracket(int a[][]) {
		this.bracket = a;
	}
	
	public ArrayList<Team> getTeams(){
		return this.teams;
	}
	
	public void setTeams(ArrayList<Team> ts) {
		this.teams = ts;
	}
	
	public void addTeam(Team t) {
		this.teams.add(t);
	}
	
	public void addTeam(int number, String school, char designation) {
		Team t = new Team(number, school, designation);
		this.teams.add(t);
	}
	
	public void removeTeam(Team t) {
		this.teams.remove(t);
	}
	
	public void removeTeam(int teamNumber) {
		Team t = getTeam(teamNumber);
		removeTeam(t);
	}
	
	public int getRound() {
		return this.round;
	}
	
	public void setRound(int a) {
		this.round = a;
	}
	
	public void nextRound() {
		this.round++;
	} 
	
}
