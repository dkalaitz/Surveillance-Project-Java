import java.util.ArrayList;

public class CommunicationAnalyzer {
	
    private ArrayList<Communication> communicationsList = new ArrayList<Communication>();
    
    public CommunicationAnalyzer(ArrayList<Communication> communicationsList) {
    	this.communicationsList = communicationsList;
    }

    // getLongestPhoneCallBetween Refactored
    public PhoneCall getLongestPhoneCallBetween(String num1, String num2) {
        int maxDuration = 0;
        PhoneCall longestPhoneCall = null;
        for (Communication communication : communicationsList) {
            if (communication instanceof PhoneCall) {
                PhoneCall currentPhoneCall = (PhoneCall) communication;
                if (currentPhoneCall.containsMatchingPhoneNumbers(num1, num2)
                        && currentPhoneCall.getDuration() > maxDuration) {
                    maxDuration = currentPhoneCall.getDuration();
                    longestPhoneCall = currentPhoneCall;
                }
            }
        } return longestPhoneCall;
    }
	
	// Refactored Version 5
    public ArrayList<SMS> getSuspiciousMessagesBetween(String num1, String num2) {
        ArrayList<SMS> listOfMessages = new ArrayList<>();
		for (Communication communication: communicationsList) 
            if (communication instanceof SMS) {
                SMS currentMessage = (SMS) communication;
                if (currentMessage.containsMatchingPhoneNumbers(num1, num2) 
                		&& currentMessage.isSuspiciousMessage()) 
                        listOfMessages.add(currentMessage);
            } return listOfMessages; 
    }

}
