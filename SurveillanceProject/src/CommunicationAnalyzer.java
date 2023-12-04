import java.util.ArrayList;

public class CommunicationAnalyzer {
	
    private ArrayList<Communication> communicationsList = new ArrayList<Communication>();
    
    public CommunicationAnalyzer(ArrayList<Communication> communicationsList) {
    	this.communicationsList = communicationsList;
    }

    public PhoneCall getLongestPhoneCallBetween(String num1, String num2) {
        PhoneCall longestPhoneCall = (PhoneCall) communicationsList.get(0);
        int maxDuration = longestPhoneCall.getDuration();

        for (int i = 0; i < 7; i++) {
            PhoneCall currentPhoneCall = (PhoneCall) communicationsList.get(i);

            if ((communicationsList.get(i).getFirstPhoneNumber().equals(num1) && communicationsList.get(i).getSecondPhoneNumber().equals(num2))
                    || (communicationsList.get(i).getFirstPhoneNumber().equals(num2) && communicationsList.get(i).getSecondPhoneNumber().equals(num1)))
                if (maxDuration < currentPhoneCall.getDuration()) 
                    maxDuration = currentPhoneCall.getDuration();
                    longestPhoneCall = currentPhoneCall;
        }return longestPhoneCall;
    }
    
    public ArrayList<SMS> getSuspiciousMessagesBetween(String num1, String num2) {
        ArrayList<SMS> listOfMessages = new ArrayList<>();
        ArrayList<SMS> tempMessages = new ArrayList<>();

        // Extract SMS messages from communicationsList
        for (int i = 0; i < communicationsList.size(); i++) {
            if (communicationsList.get(i) instanceof SMS) {
                tempMessages.add((SMS) communicationsList.get(i));
            }
        }

        // Check for suspicious messages
        int currentIndex = 0;
        for (int i = 0; i < communicationsList.size(); i++) 
            if (communicationsList.get(i) instanceof SMS) {
                SMS currentMessage = (SMS) communicationsList.get(i);

                if ((currentMessage.getFirstPhoneNumber().equals(num1) && currentMessage.getSecondPhoneNumber().equals(num2))
                        || (currentMessage.getFirstPhoneNumber().equals(num2) && currentMessage.getSecondPhoneNumber().equals(num1))) 
                    if (isSuspiciousMessage(currentMessage)) 
                        listOfMessages.add(currentMessage);
                currentIndex++;
            } return listOfMessages; 
    }

    private boolean isSuspiciousMessage(SMS message) {
        String messageContent = message.getMessage();
        return messageContent.contains("Bomb") || messageContent.contains("Explosives")
                || messageContent.contains("Gun") || messageContent.contains("Attack");
    }

}
