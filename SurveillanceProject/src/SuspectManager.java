import java.util.ArrayList;

public class SuspectManager {

    private ArrayList<Suspect> allSuspectsList = new ArrayList<>();
    
    public SuspectManager(ArrayList<Suspect> allSuspectsList) {
        this.allSuspectsList = allSuspectsList;
    }

    // Connect Suspects
    public void connectSuspects(Suspect suspect1, Suspect suspect2) {
        suspect1.connectTo(suspect2);
    }

	// Get the common partners
    public ArrayList<Suspect> findCommonPartners(Suspect suspect1, Suspect suspect2) {
    	ArrayList<Suspect> commonPartners = new ArrayList<>();
        for (Suspect possible : suspect1.getPartners()) 
            for (Suspect otherPossible : suspect2.getPartners()) 
                if (possible.equals(otherPossible)) 
                    commonPartners.add(possible);
        return commonPartners;
    }
	
	// Print potential Partners
    public void printPossiblePartners(Suspect suspect) {
        for (Suspect possiblePartner : suspect.getPartners()) {
            System.out.print("Name " + possiblePartner.getName() + ", " + possiblePartner.getCodeName());
            if (possiblePartner.getCountryName().equals(suspect.getCountryName())) 
                System.out.print("*");
            System.out.println("\n");
        }
    }
    
	// Get suggested partners
    public ArrayList<Suspect> getSuggestedPartners(Suspect suspect) {
        ArrayList<Suspect> suggestedPartners = new ArrayList<>();
        for (Suspect partner : suspect.getPartners()) 
            for (Suspect suggestedPartner : partner.getPartners()) 
                if (!suspect.isConnectedTo(suggestedPartner) && !suspect.equals(suggestedPartner)) 
                    suggestedPartners.add(suggestedPartner); 	
        return suggestedPartners;
    }
    
	// Connect Suspects through communication
    public void connectSuspectsByCommunication(Communication communication) {
        String firstPhoneNumber = communication.getFirstPhoneNumber();
        String secondPhoneNumber = communication.getSecondPhoneNumber();

        for (Suspect suspect1 : allSuspectsList) 
            if (suspect1.hasPhoneNumber(firstPhoneNumber)) 
                for (Suspect suspect2 : allSuspectsList) 
                    if (suspect2.hasPhoneNumber(secondPhoneNumber)) 
                        connectSuspects(suspect1, suspect2);             
    }
    
    
    
}
