package school_원우연v4;

public class Student {
	private int stuNo;
	private String name;
	private String stuId;
	private double avg;
	
	
	public int getStuNo() {
		return stuNo;
	}



	public String getName() {
		return name;
	}


	public String getStuId() {
		return stuId;
	}

	public double getAvg() {
		return avg;
	}


	public void setAvg(double avg) {
		this.avg = avg;
	}


	Student(int stuNo, String name, String stuId) {
		super();
		this.stuNo = stuNo;
		this.name = name;
		this.stuId = stuId;
	}


	@Override
	public String toString() {
		return stuNo + " " + name + " " + stuId;
	}
	
	
}
