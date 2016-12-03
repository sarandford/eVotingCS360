package eVoting;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.ButtonGroup;

public class CandidateChoiceScreen extends JPanel {
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Create the panel.
	 */
	public CandidateChoiceScreen(JFrame frame) {
		setLayout(frame.getLayout());
		setBounds(frame.getBounds()); 
		
		int x = frame.getX();
		int y = frame.getY(); 
		int w = frame.getWidth();
		int h = frame.getHeight(); 
		
		JLabel lblChooseACandidate = new JLabel("Choose a Candidate");
		lblChooseACandidate.setBounds(frame.getBounds());
		add(lblChooseACandidate);
		
		JRadioButton rdbtnHillaryRClinton = new JRadioButton("Hillary R. Clinton");
		buttonGroup.add(rdbtnHillaryRClinton);
		rdbtnHillaryRClinton.setActionCommand("1");
		rdbtnHillaryRClinton.setBounds(154, 63, 141, 23);
		add(rdbtnHillaryRClinton);
		
		JRadioButton rdbtnDonaldJTrump = new JRadioButton("Donald J. Trump");
		buttonGroup.add(rdbtnDonaldJTrump);
		rdbtnDonaldJTrump.setActionCommand("2");
		rdbtnDonaldJTrump.setBounds(154, 109, 141, 23);
		add(rdbtnDonaldJTrump);
		
		JRadioButton rdbtnGaryJohnson = new JRadioButton("Gary Johnson");
		buttonGroup.add(rdbtnGaryJohnson);
		rdbtnGaryJohnson.setActionCommand("3");
		rdbtnGaryJohnson.setBounds(154, 155, 141, 23);
		add(rdbtnGaryJohnson);
		
		JRadioButton rdbtnJillStein = new JRadioButton("Jill Stein");
		buttonGroup.add(rdbtnJillStein);
		rdbtnJillStein.setActionCommand("4");
		rdbtnJillStein.setBounds(154, 201, 141, 23);
		add(rdbtnJillStein);
		
		JButton btnMakeSelection = new JButton("Make Selection");
		btnMakeSelection.setBounds(156, 247, 138, 29);
		add(btnMakeSelection);
		

	}

}
