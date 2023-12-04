
public abstract class Communication {
	
	protected String firstPhoneNumber,secondPhoneNumber;
	protected int day,month,year;
	
	public Communication(String firstPhoneNumber, String secondPhoneNumber, int day, int month, int year) {
		this.firstPhoneNumber = firstPhoneNumber;
		this.secondPhoneNumber = secondPhoneNumber;
		this.day = day;
		this.month = month;
		this.year = year;
	}
	
	public String getFirstPhoneNumber() {
		return firstPhoneNumber;
	}
	
	public String getSecondPhoneNumber() {
		return secondPhoneNumber;
	}

	// Moved from CommunicationAnalyzer (Version 6)	
	public boolean containsMatchingPhoneNumbers(String num1, String num2) {
		return (getFirstPhoneNumber().equals(num1) && getSecondPhoneNumber().equals(num2))
				|| (getFirstPhoneNumber().equals(num2) && getSecondPhoneNumber().equals(num1));
	}
	
	
	
}
