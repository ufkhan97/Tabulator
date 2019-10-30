//TODO: Add print method which prints team information, more neatly. Mirror tab summary format? 
//TODO: test the updated structure 

import java.util.Arrays;

public class Team {

	private int number; //team number, id
	private String school; //School name
	private char designation; //School designation for this team, e.g A or B or C or D
	private int[] ballots; //Point Differential on each ballot
	private double cs; //CS Combined Strength, sum of competitors scores (where "score" is # of ballots won), by round
	private double ocs; //OCS Opponents Combined Strength, sum of competitors CS, by round
	private boolean isDefense; //let true be D, and false be P
	
	
	//TODO: impermissible? school name? think of how this would be constructed 
	//Problem with using a String schoolName field is that a school can be represented as a string in many different way
	// For example: "Emory" or "emory" or "Emory University". or "Virginia" "virginia" "UVA" "uva" "University of Virginia" or "
	// So, instead have a int[] holding all impermissible matchups, such as school and prev opps 
	
	public Team(int number, String school, char designation) { //assumed 4 round tournament, 2 ballots per round
		this.number = number;
		this.school = school;
		this.designation = designation;
		this.ballots = new int[8];
		this.cs = 0; 
		this.ocs = 0; 
		this.isDefense = true;
		 
	}
	
	public Team(int number, String school) { 
		this.number = number;
		this.school = school;
		this.designation = 'A';
		this.ballots = new int[8];
		this.cs = 0;
		this.ocs = 0;
		this.isDefense = true;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder("Team Number: ").append(this.number).append("\n");
		sb.append(school).append(" ").append(designation).append("\n");
		sb.append("Ballot Record: ").append(Arrays.toString(ballots)).append("\n");
		sb.append("CS: ").append(cs).append("\n");
		sb.append("OCS: ").append(ocs).append("\n");
		sb.append("Side: ").append(getSide()).append("\n");
		return sb.toString();
	}
	
	public boolean equals(Object o) {
		  if (!(o instanceof Team)) {
		    return false;
		  }
		  Team other = (Team) o;
		  
		  return this.number == other.getNumber();
		}

	public int hashCode() {
		String num = Integer.toString(this.number);
		return num.hashCode();
	}
	
	public int getNumber() {
		return this.number;
	}
	
	public void setNumber(int n) {
		this.number = n;
	}
	
	public String getSchool() {
		return this.school;
	}
	
	public void setSchool(String s) {
		this.school = s;
	}
	
	public char getDesignation() {
		return designation;
	}
	
	public void setDesignation(char c) {
		this.designation = c;
	}
	
	public int[] getBallots() {
		return this.ballots;
	}
	
	public void setBallots(int a[]) {
		this.ballots = a;
	}
	
	public double getCS() {
		return this.cs;
	}
	
	public void setCS(double d) {
		this.cs = d;
	}
	
	public double getOCS() {
		return this.ocs;
	}
	
	public void setOCS(double d) {
		this.ocs = d;
	}
	
	public boolean getIsDefense() {
		return this.isDefense;
	}
	
	public void setIsDefense(boolean b) {
		this.isDefense = b;
	}
	
	public String getSide() {
		if(isDefense){
			return "Defense";
		}else {
			return "Prosecution";
		}
	}
	
	
	public void updateBallots(int round, int ballot1, int ballot2) {
		this.ballots[2*round - 2] = ballot1; 
		this.ballots[2*round - 1] = ballot2;
	}
	
	public double getNumBallots(int round) {
		// Positive ballot score = 1 ballot (win)
		// 0 ballot score = 0.5 ballots (tie)
		// Negative ballot score = 0 ballots (loss)
		double result = 0;
		for(int i = 0; i < 2*round; i++) {
			if(ballots[i] > 0) {
				result += 1.0;
			}else if(ballots[i] == 0) {
				result += 0.5;
			}
		}
		return result;
	}
	
	public int getPD() {
		int result = 0;
		for(int i = 0; i < this.ballots.length; i++) {
			result += this.ballots[i];
		}
		return result;
	}
	
}
