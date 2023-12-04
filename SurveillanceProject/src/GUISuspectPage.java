import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;


class GUISuspectPage extends JFrame{
		
		private JPanel panel,panelSuspectInfo,panelSMS,panelPotentialPartners,panelSuggestedPartners,panelSameCountry;
		private ArrayList<Suspect> listSuggestedPartners = new ArrayList<>();
		private JLabel labelPotentialPartners,labelSuggestedPartners,labelSuspectsFromSameCountry;
		private Suspect suspectGUIPage;
		private JTextField textFieldCodeName,textFieldPhoneNumber;
		private JTextArea textAreaSuspectTelephoneNumbers,textAreaSMS,textAreaPotentialPartners,textAreaSuggestedPartners,textAreaSuspectsFromSameCountry;
		private JButton buttonReturntoSearch;
		private Registry registryGUI;
		private Border blackline = BorderFactory.createLineBorder(Color.BLACK);	

		
		// Create a page of the suspect with all the information
		public GUISuspectPage(Registry registry, Suspect suspect) {
			registryGUI = registry;
			suspectGUIPage = suspect;
			initializeGUISuspectPage();
		}
		
		public void initializeGUISuspectPage() {
		       initializePanels();
		       setupSuspectInfoPanel();
		       setupSMSPanel();
		       setupPotentialPartnersPanel();
		       setupSuggestedPartnersPanel();
		       setupSameCountryPanel();
		       setupReturnToSearchButton();
		}
		
		
		private void initializePanels() {
			
			panel = new JPanel();
			panelSuspectInfo = new JPanel();
			panelSMS = new JPanel();
			panelPotentialPartners = new JPanel();
			panelSuggestedPartners = new JPanel();
			panelSameCountry = new JPanel();

			this.setContentPane(panel);
			setVisible(true);
			setSize(650,650);
			setTitle("Suspect Page");
			setResizable(false);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
		
		// Show suspect's Info
		private void setupSuspectInfoPanel() {
			
			JTextField textFieldSuspectName = new JTextField(20);
			JLabel label = new JLabel();
			
			panel.add(panelSuspectInfo);
			panelSuspectInfo.add(label);
			panelSuspectInfo.add(textFieldSuspectName);
			
			// Show suspect's name
			textFieldSuspectName.setText(suspectGUIPage.getName());
			textFieldSuspectName.setEditable(false);
			
			// Show suspect's nickname
			
			textFieldCodeName = new JTextField(20);
			
			panelSuspectInfo.add(textFieldCodeName);
			textFieldCodeName.setText(suspectGUIPage.getCodeName());
			textFieldCodeName.setEditable(false);
			
			// Show phone numbers of the suspect
			
			textAreaSuspectTelephoneNumbers = new JTextArea();
			
			panelSuspectInfo.add(textAreaSuspectTelephoneNumbers);
			textAreaSuspectTelephoneNumbers.setSize(50, 50);
			for(int i=0; i<suspectGUIPage.getTelephoneNumbers().size(); i++) {
				textAreaSuspectTelephoneNumbers.append(suspectGUIPage.getTelephoneNumbers().get(i));
				textAreaSuspectTelephoneNumbers.append("\n");
			}
			textAreaSuspectTelephoneNumbers.setEditable(false);
			
			panelSuspectInfo.setBorder(blackline);
		}
			
		// User inserts phone number and shows suspicious SMS between the suspect and the inserted phone number
		private void setupSMSPanel() {
			
			JButton button = new JButton("Find Suspicious SMS");
			ButtonListenerFindSMS findSMSlistener = new ButtonListenerFindSMS();
			button.addActionListener(findSMSlistener);
			
			textFieldPhoneNumber = new JTextField(20);
			textFieldPhoneNumber.setText("Enter here a phone number");
			textAreaSMS = new JTextArea(5,20);
			textAreaSMS.setEnabled(false);
			textAreaSMS.setDisabledTextColor(Color.black);

			panel.add(panelSMS);
			panelSMS.add(textFieldPhoneNumber);
			panelSMS.add(textAreaSMS);
			panelSMS.add(button);
			
			panelSMS.setBorder(blackline);
		}
			
		// Show partners
		private void setupPotentialPartnersPanel() {
			
			labelPotentialPartners = new JLabel();
			textAreaPotentialPartners = new JTextArea(5,40);
			
			panel.add(panelPotentialPartners);
			panelPotentialPartners.add(labelPotentialPartners);
			labelPotentialPartners.setText("Potential Partners");
			panelPotentialPartners.add(textAreaPotentialPartners);
			textAreaPotentialPartners.setEnabled(false);
			textAreaPotentialPartners.setDisabledTextColor(Color.black);
			
			for(int i=0; i<suspectGUIPage.getPotentialPartners().size(); i++) {
				textAreaPotentialPartners.append(suspectGUIPage.getPotentialPartners().get(i).getName() + " , " + suspectGUIPage.getPotentialPartners().get(i).getCodeName());
				textAreaPotentialPartners.append("\n");
				}
			
			panelPotentialPartners.setBorder(blackline);
		}
		
		
		//Show the list of the possible partners
		private void setupSuggestedPartnersPanel() {
			
			labelSuggestedPartners = new JLabel();
			textAreaSuggestedPartners = new JTextArea(5,20);

			panel.add(panelSuggestedPartners);
			panelSuggestedPartners.add(labelSuggestedPartners);
			labelSuggestedPartners.setText("Suggested Partners ------->");
			panelSuggestedPartners.add(textAreaSuggestedPartners);
			textAreaSuggestedPartners.setEnabled(false);
			textAreaSuggestedPartners.setDisabledTextColor(Color.black);
			
			listSuggestedPartners = registryGUI.getSuggestedPartners(suspectGUIPage);
			
			for(int i=0; i<listSuggestedPartners.size(); i++) {
				textAreaSuggestedPartners.append(listSuggestedPartners.get(i).getName());
				textAreaSuggestedPartners.append("\n");
			}
			
			panelSuggestedPartners.setBorder(blackline);
		}
			
		// Show the suspects' names with whom they are from the same country
		private void setupSameCountryPanel() {
			labelSuspectsFromSameCountry = new JLabel();
			textAreaSuspectsFromSameCountry = new JTextArea(10,40);
			textAreaSuspectsFromSameCountry.setEnabled(false);
			textAreaSuspectsFromSameCountry.setDisabledTextColor(Color.black);
			
			panel.add(panelSameCountry);
			panelSameCountry.add(labelSuspectsFromSameCountry);
			panelSameCountry.add(textAreaSuspectsFromSameCountry);
			textAreaSuspectsFromSameCountry.append("Suspects coming from " + suspectGUIPage.getCountryName() + "\n" +
			"----------------------------------------------" + "\n");
			
			for(int i=0; i<registryGUI.getSuspects().size(); i++) {
				if(suspectGUIPage.getCountryName().equals(registryGUI.getSuspects().get(i).getCountryName())) {
					textAreaSuspectsFromSameCountry.append(registryGUI.getSuspects().get(i).getName() + "\n");
				}
			}
			
			panelSameCountry.setBorder(blackline);
		}
		
		// Return to Search Screen Button
		private void setupReturnToSearchButton() {
			buttonReturntoSearch = new JButton("Return to Search Screen");
			ButtonReturntoSearchScreen listenerReturn = new ButtonReturntoSearchScreen();
			buttonReturntoSearch.addActionListener(listenerReturn);
			panel.add(buttonReturntoSearch);
			}
		
		
		
class ButtonListenerFindSMS implements ActionListener {
	
		 public void actionPerformed(ActionEvent e) {
			// Clear text area
			textAreaSMS.setText("");
			
			boolean susSMS = false;
			ArrayList<SMS> listOfMessagesForThisPhoneNumber = new ArrayList<SMS>();
			String textedPhoneNumber = textFieldPhoneNumber.getText();

			// For every Phone Number of our Suspect, check if there is suspicious SMS with the entered number
			for(int i=0; i<suspectGUIPage.getTelephoneNumbers().size(); i++) {
				 listOfMessagesForThisPhoneNumber = registryGUI.getSuspiciousMessagesBetween(suspectGUIPage.getTelephoneNumbers().get(i) ,textedPhoneNumber);
				 for(int j=0; j<listOfMessagesForThisPhoneNumber.size(); j++) {
					 textAreaSMS.append(listOfMessagesForThisPhoneNumber.get(j).getMessage());
					 textAreaSMS.append("\n");
					 susSMS = true;
				 }
				 // Clearing SMS List for next phone number
				 listOfMessagesForThisPhoneNumber.clear();
			}
			if(!susSMS) { textAreaSMS.setText("Did not found suspicious messages."); }

	}
}
				 

class ButtonReturntoSearchScreen implements ActionListener{
	
	public void actionPerformed(ActionEvent e) {
		dispose();
		new GUIFindSuspectWindow(registryGUI);
	}
  }

}