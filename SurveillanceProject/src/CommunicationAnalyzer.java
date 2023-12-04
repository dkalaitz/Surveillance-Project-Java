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
                if (containsMatchingPhoneNumbers(currentPhoneCall, num1, num2)
                        && currentPhoneCall.getDuration() > maxDuration) {
                    maxDuration = currentPhoneCall.getDuration();
                    longestPhoneCall = currentPhoneCall;
                }
            }
        } return longestPhoneCall;
    }
	
	// Refactored Version 4
    public ArrayList<SMS> getSuspiciousMessagesBetween(String num1, String num2) {
        ArrayList<SMS> listOfMessages = new ArrayList<>();
		for (Communication communication: communicationsList) 
            if (communication instanceof SMS) {
                SMS currentMessage = (SMS) communication;
                if (containsMatchingPhoneNumbers(currentMessage, num1, num2) 
                		&& isSuspiciousMessage(currentMessage)) 
                        listOfMessages.add(currentMessage);
            } return listOfMessages; 
    }
    
    // Extracted method Version 4
    private boolean containsMatchingPhoneNumbers(Communication currentCommunication, String num1, String num2) {
        return (currentCommunication.getFirstPhoneNumber().equals(num1) && currentCommunication.getSecondPhoneNumber().equals(num2))
                || (currentCommunication.getFirstPhoneNumber().equals(num2) && currentCommunication.getSecondPhoneNumber().equals(num1));
    }


    private boolean isSuspiciousMessage(SMS message) {
        String messageContent = message.getMessage();
        return messageContent.contains("Bomb") || messageContent.contains("Explosives")
                || messageContent.contains("Gun") || messageContent.contains("Attack");
    }

}
