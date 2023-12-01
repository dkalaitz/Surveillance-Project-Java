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
		
		private JPanel panel,panel1,panel2,panel3,panel4,panel5;
		private ArrayList<Suspect> listSuggestedPartners = new ArrayList<>();
		private JLabel labelPartners,labelSuggestedPartners,labelSuspectsFromSameCountry;
		private Suspect suspectGUIPage;
		private JTextField textFieldCodeName,textFieldPhoneNumber;
		private JTextArea textAreaSuspectTelephoneNumbers,textAreaSMS,textAreaPartners,textAreaSuggestedPartners,textAreaSuspectsFromSameCountry;
		private JButton buttonReturntoSearch;
		private Registry registryGUI;

		
		// Create a page of the suspect with all the information
		public GUISuspectPage(Registry registry, Suspect suspect) {
			registryGUI = registry;
			suspectGUIPage = suspect;
			initializeGUISuspectPage();
					
		}
		
		public void initializeGUISuspectPage() {
			
			Border blackline = BorderFactory.createLineBorder(Color.BLACK);	
			panel = new JPanel();
			panel1 = new JPanel();
			JTextField textFieldSuspectName = new JTextField(20);
			JLabel label = new JLabel();
			
			panel.add(panel1);
			panel1.add(label);
			panel1.add(textFieldSuspectName);
			
			// Show suspect's name
			textFieldSuspectName.setText(suspectGUIPage.getName());
			textFieldSuspectName.setEditable(false);
			
			
			// Show suspect's nickname
		
			textFieldCodeName = new JTextField(20);
			
			panel1.add(textFieldCodeName);
			textFieldCodeName.setText(suspectGUIPage.getCodeName());
			textFieldCodeName.setEditable(false);
			
			// Show phone numbers of the suspect
			
			textAreaSuspectTelephoneNumbers = new JTextArea();
			
			panel1.add(textAreaSuspectTelephoneNumbers);
			textAreaSuspectTelephoneNumbers.setSize(50, 50);
			for(int i=0; i<suspectGUIPage.getTelephoneNumbers().size(); i++) {
				textAreaSuspectTelephoneNumbers.append(suspectGUIPage.getTelephoneNumbers().get(i));
				textAreaSuspectTelephoneNumbers.append("\n");
			}
			textAreaSuspectTelephoneNumbers.setEditable(false);
			
			panel1.setBorder(blackline);
			
			// User inserts phone number and shows suspicious SMS between the suspect and the inserted phone number
			
			panel2 = new JPanel();
			JButton button = new JButton("Find Suspicious SMS");
			ButtonListenerFindSMS findSMSlistener = new ButtonListenerFindSMS();
			button.addActionListener(findSMSlistener);
			
			textFieldPhoneNumber = new JTextField(20);
			textFieldPhoneNumber.setText("Enter here a phone number");
			textAreaSMS = new JTextArea(5,20);
			textAreaSMS.setEnabled(false);
			textAreaSMS.setDisabledTextColor(Color.black);

			panel.add(panel2);
			panel2.add(textFieldPhoneNumber);
			panel2.add(textAreaSMS);
			panel2.add(button);
			
			panel2.setBorder(blackline);
			
			// Show potential partners
			
			panel3 = new JPanel();
			labelPartners = new JLabel();
			textAreaPartners = new JTextArea(5,40);
			
			panel.add(panel3);
			panel3.add(labelPartners);
			labelPartners.setText("Partners");
			panel3.add(textAreaPartners);
			textAreaPartners.setEnabled(false);
			textAreaPartners.setDisabledTextColor(Color.black);
			
			for(int i=0; i<suspectGUIPage.possibleSuspects.size(); i++) {
				textAreaPartners.append(suspectGUIPage.possibleSuspects.get(i).getName() + " , " + suspectGUIPage.possibleSuspects.get(i).getCodeName());
				textAreaPartners.append("\n");
				}
			
			panel3.setBorder(blackline);
			
			//Show the list of the possible partners
			
			panel4 = new JPanel();
			labelSuggestedPartners = new JLabel();
			textAreaSuggestedPartners = new JTextArea(5,20);

			panel.add(panel4);
			panel4.add(labelSuggestedPartners);
			labelSuggestedPartners.setText("Suggested Partners ------->");
			panel4.add(textAreaSuggestedPartners);
			textAreaSuggestedPartners.setEnabled(false);
			textAreaSuggestedPartners.setDisabledTextColor(Color.black);
			
			listSuggestedPartners = suspectGUIPage.getSuggestedPartners();
			
			for(int i=0; i<listSuggestedPartners.size(); i++) {
				textAreaSuggestedPartners.append(listSuggestedPartners.get(i).getName());
				textAreaSuggestedPartners.append("\n");
			}
			
			panel4.setBorder(blackline);
			
			// Show the suspects' names with whom they are from the same country
			
			panel5 = new JPanel();
			labelSuspectsFromSameCountry = new JLabel();
			textAreaSuspectsFromSameCountry = new JTextArea(10,40);
			textAreaSuspectsFromSameCountry.setEnabled(false);
			textAreaSuspectsFromSameCountry.setDisabledTextColor(Color.black);
			
			panel.add(panel5);
			panel5.add(labelSuspectsFromSameCountry);
			panel5.add(textAreaSuspectsFromSameCountry);
			textAreaSuspectsFromSameCountry.append("Suspects coming from " + suspectGUIPage.getCountryName() + "\n");
			for(int i=0; i<registryGUI.suspects.size(); i++) {
				if(suspectGUIPage.getCountryName().equals(registryGUI.suspects.get(i).getCountryName())) {
					textAreaSuspectsFromSameCountry.append(registryGUI.suspects.get(i).getName() + "\n");
				}
			}
			
			panel5.setBorder(blackline);
			
			// Return to Search Screen Button
			
			buttonReturntoSearch = new JButton("Return to Search Screen");
			ButtonReturntoSearchScreen listenerReturn = new ButtonReturntoSearchScreen();
			buttonReturntoSearch.addActionListener(listenerReturn);
			panel.add(buttonReturntoSearch);
			
			
			
			this.setContentPane(panel);
			
			setVisible(true);
			setSize(650,650);
			setTitle("Suspect Page");
			setResizable(false);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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