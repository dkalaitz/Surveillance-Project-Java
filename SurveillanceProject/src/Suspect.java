import java.util.ArrayList;

public class Suspect{

	  private String name;
	  private String codeName;
	  private String country;
	  private String city;
	  private ArrayList<String> suspectTelephoneNumbers = new ArrayList<>();
	  private ArrayList<Suspect> potentialPartners = new ArrayList<>();

	  public Suspect(String name, String codeName, String country, String city ) {
	        this.name = name;
	        this.codeName = codeName;
	        this.country = country;
	        this.city = city;
	    }
		
		// Add potential suspect to the partners list of each suspect respectively
		public void connectTo(Suspect aSuspect) {
				if(!potentialPartners.contains(aSuspect)){
					potentialPartners.add(aSuspect);
					aSuspect.potentialPartners.add(this);
				}
		}
		
		// Check if a suspect is connected to this suspect
		public boolean isConnectedTo(Suspect suspect) {
	        return potentialPartners.contains(suspect);
		}

		public boolean hasPhoneNumber(String phoneNumber) {
	        return suspectTelephoneNumbers.contains(phoneNumber);
		}
		
		
		// Add a phone number of the suspect in their list of phone numbers
		public void addNumber(String number) {
			suspectTelephoneNumbers.add(number);
		}
		
		public String getName() {
			return name;
		}
		
		public String getCodeName() {
			return codeName;
		}
		
		public String getCountryName() {
			return country;
		}
		
		public String getCity() {
			return city;
		}
		
		public ArrayList<String> getTelephoneNumbers(){
			return suspectTelephoneNumbers;
		}
		
		public ArrayList<Suspect> getPotentialPartners(){
			return potentialPartners;
		}


	
}
