package school_원우연v4;

public class Subject {
	private int stuNo;
	private String subName;
	private int score;


	public Subject(int stuNo, String subName, int score) {
		this.stuNo = stuNo;
		this.subName = subName;
		this.score = score;

	}


	public int getStuNo() {
		return this.stuNo;
	}

	public String getSubName() {
		return this.subName;
	}
	public void setSubName(String subName) {
		this.subName = subName;
	}

	public int getScore() {
		return this.score;
	}
	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public String toString() {
		String data = "";
		data += subName + " " + score +"점";
		return data;
	}
	
}
