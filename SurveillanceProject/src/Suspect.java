import java.util.ArrayList;

public class Suspect{

	private String Name;
	private String CodeName;
	private String Country;
	private String City;
	
	private ArrayList<String> TelephoneNumbers = new ArrayList<String>();
	ArrayList<Suspect> SuggestedPartners = new ArrayList<Suspect>();
	ArrayList<Suspect> CommonPartners = new ArrayList<Suspect>();
	ArrayList<Suspect> possibleSuspects = new ArrayList<Suspect>();
	
	Registry registry = new Registry();

	// Constructor
	public Suspect(String name, String codeName, String country, String city) {
		Name = name;
		CodeName = codeName;
		Country = country;
		City = city;
	}
	
	// Add a phone number of the suspect in their list of phone numbers
	public void addNumber(String number) {
		TelephoneNumbers.add(number);
	}
	
	public String getName() {
		return Name;
	}
	
	public String getCodeName() {
		return CodeName;
	}
	
	public String getCountryName() {
		return Country;
	}
	
	public String getCity() {
		return City;
	}
	
	public ArrayList<String> getTelephoneNumbers(){
		return TelephoneNumbers;
	}
	
	
	// Add possible suspect to the possibleSuspects list of each suspect respectively
	public void addPossibleSuspects(Suspect aSuspect,Communication aCommunication) {
			if(!possibleSuspects.contains(aSuspect)){
				possibleSuspects.add(aSuspect);
				aSuspect.possibleSuspects.add(this);
			}
	}
	
	// Check if a suspect is connected to this suspect
	public boolean isConnectedTo(Suspect suspect) {
		boolean connected = false;
		
		for(int i=0; i<possibleSuspects.size(); i++) {
			if(possibleSuspects.get(i) == suspect)
				connected = true;
		}
		return connected;
	}

	// Get the common partners
	public ArrayList<Suspect> getCommonPartners(Suspect suspect){
		
		for(int i=0; i<possibleSuspects.size(); i++) {
			for(int j=0; j<suspect.possibleSuspects.size(); j++) {
				if(possibleSuspects.get(i) == suspect.possibleSuspects.get(j)) {
					CommonPartners.add(possibleSuspects.get(i));
				}
			}
		}
		return CommonPartners;
	}
	
	
	// Print potential Partners
	public void printPossiblePartners() {
		for(int i=0; i<possibleSuspects.size(); i++) {
			System.out.print("Name " + getName() +", "+ getCodeName());
			if(possibleSuspects.get(i).getCountryName() == Country) {
				System.out.print("*");
			}
		}
	}
	
	// Get suggested partners
	public ArrayList<Suspect> getSuggestedPartners(){
		
		for(int i=0; i<possibleSuspects.size(); i++) {
			for(int j=0; j<possibleSuspects.get(i).possibleSuspects.size(); j++) {
				if(!this.isConnectedTo(possibleSuspects.get(i).possibleSuspects.get(j))
						&& !this.equals(possibleSuspects.get(i).possibleSuspects.get(j))) {
					SuggestedPartners.add(possibleSuspects.get(i).possibleSuspects.get(j));
				}
			}
		}
		return SuggestedPartners;
	}

	
	
}
