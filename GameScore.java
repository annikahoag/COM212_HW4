/** 
 * The GameScore class: Each object has a string name and integer score value 
 */


public class GameScore {
	private String name;	//name of the person earning this score
	private int score;		//the score value;

	public GameScore(String n, int s) {
		name = n;
		score = s;
	}

	//Returns the name field
	public String getName() {
		return name;
	}

	//Returns the score field
	public int getScore() {
		return score;
	}

	//Returns the string representation of the GameScore
	public String toString() {
		return "(" + name + ", " + score + ")";		//Eg. (William, 78)
	}

	//Sets the name field
	public void setName(String n) {
		name = n;
	}

	//Sets the score field
	public void setScore(int s) {
		score = s;
	}

	//Testing the class in the main method
	public static void main(String[] args) {
		GameScore gs1;
		gs1 = new GameScore("William", 78);

		String gs1Name = gs1.getName();
		int gs1Score = gs1.getScore();

		System.out.println(gs1);

		GameScore gs2 = new GameScore("Mary", 250);

		//Update gs2's name and score
		gs2.setName("Jane");

		System.out.println(gs2);
	}
}