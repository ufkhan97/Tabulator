//TODO: Add field opponents storing opponents team numbers, by round
//TODO: Add method "updateRecord" takes 3 int inputs: opponent team number, ballot 1 PD, ballot 2 PD
// and uses those numbers to update scores, cs, ocs, point diff
//TODO: Add "ranker"/sorter method to rank/sort teams by score, cs, ocs, pd



import java.util.Arrays;

public class Team {

	private int number; //team number, id
	private double[] scores; //number of ballots won
	private double[] combinedStrength; //CS Combined Strength, sum of competitors scores
	private double[] opponentsCS; //OCS Opponents Combined Strength, sum of competitors CS
	private int[] pointDiff; //PD: Point Differential, sum of PD over all ballots
	
	public Team(int number) { //assumed 4 round tournament
		this.number = number;
		this.scores = new double[4];
		this.combinedStrength = new double[4];
		this.opponentsCS = new double[4];
		this.pointDiff = new int[4];
	}
	
	public Team(int number, int numRounds) { //variable number of Rounds
		this.number = number;
		this.scores = new double[numRounds];
		this.combinedStrength = new double[numRounds];
		this.opponentsCS = new double[numRounds];
		this.pointDiff = new int[numRounds];
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder("Team Number: ").append(this.number).append("\n");
		sb.append("Ballots Won: ").append(Arrays.toString(scores)).append("\n");
		sb.append("CS: ").append(Arrays.toString(combinedStrength)).append("\n");
		sb.append("OCS: ").append(Arrays.toString(opponentsCS)).append("\n");
		sb.append("PD: ").append(Arrays.toString(pointDiff)).append("\n"); 
		return sb.toString();
	}
	
}
