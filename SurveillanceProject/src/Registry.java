import java.util.ArrayList;

public class Registry {
	
	private ArrayList<Suspect> suspectsList = new ArrayList<Suspect>();
	private ArrayList<Communication> communicationsList = new ArrayList<Communication>();
	private SuspectManager suspectManager = new SuspectManager(suspectsList);
	private SuspectsListHandler suspectsListHandler = new SuspectsListHandler(suspectsList);
	private CommunicationAnalyzer communicationAnalyzer = new CommunicationAnalyzer(communicationsList);

	// Add suspect to the registry
	public void addSuspect(Suspect suspect) {
		suspectsList.add(suspect);
	}

	// Get suggested partners through a function of SuspectManager
    public ArrayList<Suspect> getSuggestedPartners(Suspect suspect) {
        return suspectManager.getSuggestedPartners(suspect);
    }
    
    public ArrayList<Suspect> findCommonPartners(Suspect suspect1, Suspect suspect2){
    	return suspectManager.findCommonPartners(suspect1, suspect2);
    }
    
    public void printPartners(Suspect suspect) {
    	suspectManager.printPossiblePartners(suspect);
    }
    
	// Add communication to the registry
	public void addCommunication(Communication communication) {
		communicationsList.add(communication);
        suspectManager.connectSuspectsByCommunication(communication);
	}
	
	public Suspect getSuspectWithMostPartners() {
		return suspectsListHandler.getSuspectWithMostPotentialPartners();
	}
	
	public PhoneCall getLongestPhoneCallBetween(String num1, String num2) {
		return communicationAnalyzer.getLongestPhoneCallBetween(num1, num2);
	}
	
	public ArrayList<SMS> getSuspiciousMessagesBetween(String num1, String num2) {
		return communicationAnalyzer.getSuspiciousMessagesBetween(num1, num2);
	}
    
	public void printSuspectsFromCountry(String country) {
		suspectsListHandler.printSuspectsFromCountry(country);
	}
	
	public ArrayList<Suspect> getSuspects(){
		return suspectsList;
	}
	
}
