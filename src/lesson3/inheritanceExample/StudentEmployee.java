package lesson3.inheritanceExample;

public class StudentEmployee extends Student {
	
	double rateOfPayPerHour;
	String employeeId;
	
	public StudentEmployee(String firstName, String lastName, String studentId, String employeeId, double rateOfPayPerHour) {
		super(firstName, lastName, studentId);
		// TODO Auto-generated constructor stub
		this.employeeId = employeeId;
		this.rateOfPayPerHour = rateOfPayPerHour;
	}
	
	public String getEmployeeId() {
		return this.employeeId;
	}
	
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	
	public double getRatePerHour() {
		return this.rateOfPayPerHour;
	}
	
	public void setRatePerHour(double rateOfPayPerHour) {
		this.rateOfPayPerHour = rateOfPayPerHour;
	}
	
	@Override
	public String toString() {
		return super.toString() + " employeeId " + employeeId + " pay " + rateOfPayPerHour;
	}
}
