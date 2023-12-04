import java.util.ArrayList;

public class SuspectsListHandler {
	
    private ArrayList<Suspect> suspectsList = new ArrayList<Suspect>();

    public SuspectsListHandler(ArrayList<Suspect> suspectsList) {
        this.suspectsList = suspectsList;
    }
    
    // Refactored version 5
    public Suspect getSuspectWithMostPotentialPartners() {
		int maxNumberOfTelephoneNumbers = 0;
		Suspect topSuspect = null;
		for (Suspect suspect : suspectsList) {
			int currSusNumberOfTelephoneNumbers = suspect.getTelephoneNumbers().size();
			if (currSusNumberOfTelephoneNumbers > maxNumberOfTelephoneNumbers) {
				maxNumberOfTelephoneNumbers = currSusNumberOfTelephoneNumbers;
				topSuspect = suspect;
			}
		}  return topSuspect;

    }

	public void printSuspectsFromCountry(String country) {
		for(int i=0; i<suspectsList.size(); i++) 
			if(country == suspectsList.get(i).getCountryName()) 
				System.out.println(suspectsList.get(i).getName()+" ("+suspectsList.get(i).getCodeName()+")");
	}
	
}
