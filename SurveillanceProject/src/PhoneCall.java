
public class PhoneCall extends Communication {
	
	public int durationInSeconds;
	
	
	public PhoneCall(String firstPhoneNumber, String secondPhoneNumber, int day, int month, int year,
			int durationInSeconds) {
		super(firstPhoneNumber, secondPhoneNumber, day, month, year);
		this.durationInSeconds = durationInSeconds;
	}


	public int getDuration() {
		return durationInSeconds;
	}
	
	public void printInfo() {
		System.out.println("This phone call has the following info");
		System.out.println("Between " + this.firstPhoneNumber + " --- "+ this.secondPhoneNumber);
		System.out.println("on " + this.year + "/" + this.month + "/" + this.day);
		System.out.println("Duration: " + this.durationInSeconds);
	}
	

}
