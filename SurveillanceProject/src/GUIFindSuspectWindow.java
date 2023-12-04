import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


// Search of a suspect panel
public class GUIFindSuspectWindow extends JFrame{


		private JPanel panel;
		private JButton button;
		private JTextField textField;
		private JLabel label;
		private Registry registryGUI;

		
		public GUIFindSuspectWindow(Registry registry) {
			registryGUI = registry;
			initializeGUISearchWindow();
		}
		
		public void initializeGUISearchWindow() {
			
			panel = new JPanel();
			button = new JButton("Find");
	        label = new JLabel("Please enter a suspect's name");

			textField = new JTextField(20);

			ButtonListenerFindSuspect listener = new ButtonListenerFindSuspect();
			button.addActionListener(listener);
			
			
			panel.add(label);
			panel.add(textField);
			panel.add(button);
			
			this.setContentPane(panel);
			
			setVisible(true);
			setSize(400,150);
			setResizable(false);
			setTitle("Find Suspect");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);			
		}

		
class ButtonListenerFindSuspect implements ActionListener {
			
			 public void actionPerformed(ActionEvent e) {
				 
				boolean SuspectFound = false;
				
				 for(int i=0; i<registryGUI.getSuspects().size(); i++) {
					 if(registryGUI.getSuspects().get(i).getName().equals(textField.getText())) {
						 new GUISuspectPage(registryGUI, registryGUI.getSuspects().get(i));
						 SuspectFound = true;
						 dispose();
					 }
				 }
				 if(!SuspectFound)
					 JOptionPane.showMessageDialog(null, "Suspect "+ textField.getText() + " not found");
			 }
			 
		}


}
