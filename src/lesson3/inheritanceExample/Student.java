package lesson3.inheritanceExample;

public class Student extends Person{

	private String studentId;
	
	public Student(String firstName, String lastName, String studentId) {
		super(firstName, lastName);
		// TODO Auto-generated constructor stub
		this.studentId = studentId;
	}
	
	public String getStudentId() {
		return this.studentId;
	}
	
	public void setStudentId() { 
		this.studentId = studentId;
	}
	
	@Override
	public String toString() {
		return super.toString() + " studentId " + studentId;
	}
}
