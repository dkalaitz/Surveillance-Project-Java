public class SMS extends Communication {

    private String message;

    public SMS(String firstPhoneNumber, String secondPhoneNumber, int day, int month, int year, String message) {
        super(firstPhoneNumber, secondPhoneNumber, day, month, year);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void printInfo() {
        System.out.println("This SMS has the following info");
        System.out.println("Between " + getFirstPhoneNumber() + " --- " + getSecondPhoneNumber());
        System.out.println("on " + this.year + "/" + this.month + "/" + this.day);
        System.out.println("Text: " + this.message);
    }

    // Moved from CommunicationAnalyzer Version 6
	public boolean isSuspiciousMessage() {
		String messageContent = getMessage();
		return messageContent.contains("Bomb") || messageContent.contains("Explosives")
				|| messageContent.contains("Gun") || messageContent.contains("Attack");
	}
}