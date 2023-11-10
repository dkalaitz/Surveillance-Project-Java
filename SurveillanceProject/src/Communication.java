
public class Communication {
	
	private String FirstPhoneNumber,SecondPhoneNumber;
	private int Day,Month,Year;
	
	public Communication(String firstPhoneNumber, String secondPhoneNumber, int day, int month, int year) {
		FirstPhoneNumber = firstPhoneNumber;
		SecondPhoneNumber = secondPhoneNumber;
		Day = day;
		Month = month;
		Year = year;
	}
	
	public String getFirstPhoneNumber() {
		return FirstPhoneNumber;
	}
	
	public String getSecondPhoneNumber() {
		return SecondPhoneNumber;
	}
	
	
	
}
