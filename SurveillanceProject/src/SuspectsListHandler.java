import java.util.ArrayList;

public class SuspectsListHandler {
	
    private ArrayList<Suspect> suspectsList = new ArrayList<Suspect>();

    public SuspectsListHandler(ArrayList<Suspect> suspectsList) {
        this.suspectsList = suspectsList;
    }

    public Suspect getSuspectWithMostPartners() {
        Suspect topSuspect = null;
        int maxNumberOfTelephoneNumbers = 0;
        for (Suspect suspect : suspectsList) {
            int numberOfTelephoneNumbers = suspect.getTelephoneNumbers().size();
            if (numberOfTelephoneNumbers > maxNumberOfTelephoneNumbers) 
                maxNumberOfTelephoneNumbers = numberOfTelephoneNumbers;
                topSuspect = suspect;
        } return topSuspect;
    }

	public void printSuspectsFromCountry(String country) {
		for(int i=0; i<suspectsList.size(); i++) {
			if(country == suspectsList.get(i).getCountryName()) {
				System.out.println(suspectsList.get(i).getName()+" ("+suspectsList.get(i).getCodeName()+")");
			}
		}
	}
	
}
