import java.util.ArrayList;

public class Registry {
	
	ArrayList<Suspect> suspects = new ArrayList<Suspect>();
	ArrayList<Suspect> possiblesuspects = new ArrayList<Suspect>();
	ArrayList<Communication> communications = new ArrayList<Communication>();
	ArrayList<Communication> tempcommunications = new ArrayList<Communication>();
	ArrayList<SMS> ListofMessages = new ArrayList<SMS>();
	ArrayList<SMS> TempMessages = new ArrayList<SMS>();
	private Suspect TopSuspect;
	private int MaximumNumberofTelephoneNumbers;
	private PhoneCall LongestPhoneCall;
	private int MaxDuration;
	private PhoneCall CurrentPhoneCall;	
	
	// Add suspect to the registry
	public void addSuspect(Suspect suspect) {
		suspects.add(suspect);
		}
	
	// Add communication to the registry
	public void addCommunication(Communication communication) {
		communications.add(communication);
		for(int i=0; i<suspects.size(); i++) {
			for(int j=0; j<suspects.get(i).getTelephoneNumbers().size(); j++) {
				if(suspects.get(i).getTelephoneNumbers().get(j) == communication.getFirstPhoneNumber()) {
					for(int k=0; k<suspects.size(); k++) {
						for(int y=0; y<suspects.get(k).getTelephoneNumbers().size(); y++) {
							if(suspects.get(k).getTelephoneNumbers().get(y) == communication.getSecondPhoneNumber()) {
								suspects.get(i).addPossibleSuspects(suspects.get(k), communication);
							}
						}
					}
				}	
			}
		}
	}
	
	
	public Suspect getSuspectWithMostPartners() {
		MaximumNumberofTelephoneNumbers = suspects.get(0).getTelephoneNumbers().size();
		for(int i=1; i<suspects.size(); i++) {
			if(suspects.get(i).getTelephoneNumbers().size() > MaximumNumberofTelephoneNumbers ) {
				MaximumNumberofTelephoneNumbers = suspects.get(i).getTelephoneNumbers().size();
				TopSuspect = suspects.get(i);
			}
		}
		return TopSuspect;
	}
	
	public PhoneCall getLongestPhoneCallBetween(String Num1, String Num2) {
		LongestPhoneCall = (PhoneCall)communications.get(0);
		MaxDuration = LongestPhoneCall.getDuration();
		for(int i=0; i<7; i++) {
			CurrentPhoneCall = (PhoneCall)communications.get(i);
			if((communications.get(i).getFirstPhoneNumber() == Num1 && communications.get(i).getSecondPhoneNumber() == Num2)
					|| communications.get(i).getFirstPhoneNumber() == Num2 && communications.get(i).getSecondPhoneNumber() == Num1) {
				if(MaxDuration < CurrentPhoneCall.getDuration()) {
					MaxDuration = CurrentPhoneCall.getDuration();
					LongestPhoneCall = (PhoneCall)communications.get(i);
				}
			}
		}
		return LongestPhoneCall;

	}
	
	public ArrayList<SMS> getSuspiciousMessagesBetween(String Num1, String Num2) {
		for(int i=7; i<communications.size(); i++) {
			TempMessages.add((SMS)communications.get(i));
		}
		int j = 0;
		for(int i=7; i<communications.size(); i++) {
			if((communications.get(i).getFirstPhoneNumber().equals(Num1)) && communications.get(i).getSecondPhoneNumber().equals(Num2)
					|| communications.get(i).getFirstPhoneNumber().equals(Num2) && communications.get(i).getSecondPhoneNumber().equals(Num1)) {
				if(TempMessages.get(j).getMessage().contains("Bomb") || TempMessages.get(j).getMessage().contains("Explosives") ||
						TempMessages.get(j).getMessage().contains("Gun") || TempMessages.get(j).getMessage().contains("Attack"))
					ListofMessages.add(TempMessages.get(j));
			}
			j++;
		}
		return ListofMessages;
	}
	
	public void printSuspectsFromCountry(String country) {
		for(int i=0; i<suspects.size(); i++) {
			if(country == suspects.get(i).getCountryName()) {
				System.out.println(suspects.get(i).getName()+" ("+suspects.get(i).getCodeName()+")");
			}
		}
	}
}
