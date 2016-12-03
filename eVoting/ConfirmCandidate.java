package eVoting;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;

public class ConfirmCandidate extends JPanel {
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Create the panel.
	 */
	public ConfirmCandidate() {
		setLayout(null);
		
		JLabel lblConfirmYourChoice = new JLabel("Confirm Your Choice");
		lblConfirmYourChoice.setBounds(156, 32, 131, 16);
		add(lblConfirmYourChoice);
		
		JRadioButton rdbtnSumbitChoice = new JRadioButton("Sumbit Choice");
		buttonGroup.add(rdbtnSumbitChoice);
		rdbtnSumbitChoice.setBounds(151, 128, 141, 23);
		add(rdbtnSumbitChoice);
		
		JRadioButton rdbtnChangeChoice = new JRadioButton("Change Choice");
		buttonGroup.add(rdbtnChangeChoice);
		rdbtnChangeChoice.setBounds(151, 183, 141, 23);
		add(rdbtnChangeChoice);
		
		JLabel lblYourchoicehere = new JLabel("YOURCHOICEHERE");
		lblYourchoicehere.setBounds(163, 80, 116, 16);
		add(lblYourchoicehere);
		
		JButton btnDynamicbutton = new JButton("dynamicButton ");
		btnDynamicbutton.setBounds(150, 238, 142, 29);
		add(btnDynamicbutton);

	}

}
