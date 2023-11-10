
public class SMS extends Communication{

	private String FirstPhoneNumber,SecondPhoneNumber;
	private int Day,Month,Year;
	private String Message;
	
	public SMS(String firstPhoneNumber, String secondPhoneNumber, int day, int month, int year, String message) {
		super(firstPhoneNumber, secondPhoneNumber, day, month, year);
		FirstPhoneNumber = firstPhoneNumber;
		SecondPhoneNumber = secondPhoneNumber;
		Day = day;
		Month = month;
		Year = year;
		Message = message;
	}

	public String getMessage() {
		return Message;
	}
	
	public void printInfo() {
		System.out.println("This SMS has the following info");
		System.out.println("Between " + FirstPhoneNumber + " --- " + SecondPhoneNumber);
		System.out.println("on " + Year + "/" + Month + "/" + Day);
		System.out.println("Text: "+ Message);
	}
	
	
	
	
}
