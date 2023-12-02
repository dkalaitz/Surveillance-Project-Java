import java.util.ArrayList;

public class Registry {
	
	private ArrayList<Suspect> suspectsList = new ArrayList<Suspect>();
	private ArrayList<Communication> communicationsList = new ArrayList<Communication>();
	private ArrayList<SMS> ListofMessages = new ArrayList<SMS>();
	private ArrayList<SMS> TempMessages = new ArrayList<SMS>();
	private Suspect TopSuspect;
	private int MaximumNumberofTelephoneNumbers, MaxDuration;
	private PhoneCall LongestPhoneCall, CurrentPhoneCall;
	private SuspectManager suspectManager = new SuspectManager(suspectsList);

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
		MaximumNumberofTelephoneNumbers = suspectsList.get(0).getTelephoneNumbers().size();
		for(int i=1; i<suspectsList.size(); i++) {
			if(suspectsList.get(i).getTelephoneNumbers().size() > MaximumNumberofTelephoneNumbers ) {
				MaximumNumberofTelephoneNumbers = suspectsList.get(i).getTelephoneNumbers().size();
				TopSuspect = suspectsList.get(i);
			}
		}
		return TopSuspect;
	}
	
	public PhoneCall getLongestPhoneCallBetween(String Num1, String Num2) {
		LongestPhoneCall = (PhoneCall)communicationsList.get(0);
		MaxDuration = LongestPhoneCall.getDuration();
		for(int i=0; i<7; i++) {
			CurrentPhoneCall = (PhoneCall)communicationsList.get(i);
			if((communicationsList.get(i).getFirstPhoneNumber() == Num1 && communicationsList.get(i).getSecondPhoneNumber() == Num2)
					|| communicationsList.get(i).getFirstPhoneNumber() == Num2 && communicationsList.get(i).getSecondPhoneNumber() == Num1) {
				if(MaxDuration < CurrentPhoneCall.getDuration()) {
					MaxDuration = CurrentPhoneCall.getDuration();
					LongestPhoneCall = (PhoneCall)communicationsList.get(i);
				}
			}
		}
		return LongestPhoneCall;

	}
	
	public ArrayList<SMS> getSuspiciousMessagesBetween(String Num1, String Num2) {
		for(int i=7; i<communicationsList.size(); i++) {
			TempMessages.add((SMS)communicationsList.get(i));
		}
		int j = 0;
		for(int i=7; i<communicationsList.size(); i++) {
			if((communicationsList.get(i).getFirstPhoneNumber().equals(Num1)) && communicationsList.get(i).getSecondPhoneNumber().equals(Num2)
					|| communicationsList.get(i).getFirstPhoneNumber().equals(Num2) && communicationsList.get(i).getSecondPhoneNumber().equals(Num1)) {
				if(TempMessages.get(j).getMessage().contains("Bomb") || TempMessages.get(j).getMessage().contains("Explosives") ||
						TempMessages.get(j).getMessage().contains("Gun") || TempMessages.get(j).getMessage().contains("Attack"))
					ListofMessages.add(TempMessages.get(j));
			}
			j++;
		}
		return ListofMessages;
	}
	
	public void printSuspectsFromCountry(String country) {
		for(int i=0; i<suspectsList.size(); i++) {
			if(country == suspectsList.get(i).getCountryName()) {
				System.out.println(suspectsList.get(i).getName()+" ("+suspectsList.get(i).getCodeName()+")");
			}
		}
	}
	
	
	public ArrayList<Suspect> getSuspects(){
		return suspectsList;
	}
	
}
