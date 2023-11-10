
public class PhoneCall extends Communication {
	
	private String FirstPhoneNumber,SecondPhoneNumber;
	private int Day,Month,Year;
	public int DurationInSeconds;
	
	
	public PhoneCall(String firstPhoneNumber, String secondPhoneNumber, int day, int month, int year,
			int durationInSeconds) {
		super(firstPhoneNumber, secondPhoneNumber, day, month, year);
		FirstPhoneNumber = firstPhoneNumber;
		SecondPhoneNumber = secondPhoneNumber;
		Day = day;
		Month = month;
		Year = year;
		DurationInSeconds = durationInSeconds;
	}


	public int getDuration() {
		return DurationInSeconds;
	}
	
	public void printInfo() {
		System.out.println("This phone call has the following info");
		System.out.println("Between " + FirstPhoneNumber + " --- "+ SecondPhoneNumber);
		System.out.println("on " + Year + "/" + Month + "/" + Day);
		System.out.println("Duration: " + DurationInSeconds);
	}
	

}
