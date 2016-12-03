package eVoting;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JRadioButton;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;
import java.awt.CardLayout;
import javax.swing.JInternalFrame;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.awt.event.ActionEvent;

import eVoting.Driver;

public class mainScreen extends JFrame {

	private JPanel contentPane;
	private JTextField idTextField;
	private Driver driver;
	private JPanel title;
	private JLabel titleLabel;
	private JPanel body;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainScreen frame = new mainScreen();
					frame.transitionScreens();
					
					frame.createVoterSelectionScreen(); 
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public void transitionScreens(){
		body.removeAll();
		body.revalidate();
		body.repaint();
	}
	
	public void createVoterSelectionScreen(){
		final ButtonGroup buttonGroup = new ButtonGroup();

		titleLabel.setText("Choose a Candidate");
		
		JRadioButton rdbtnHillaryRClinton = new JRadioButton("Hillary R. Clinton");
		buttonGroup.add(rdbtnHillaryRClinton);
		rdbtnHillaryRClinton.setActionCommand("1");
		
		JRadioButton rdbtnDonaldJTrump = new JRadioButton("Donald J. Trump");
		buttonGroup.add(rdbtnDonaldJTrump);
		rdbtnDonaldJTrump.setActionCommand("2");
		
		JRadioButton rdbtnGaryJohnson = new JRadioButton("Gary Johnson");
		buttonGroup.add(rdbtnGaryJohnson);
		rdbtnGaryJohnson.setActionCommand("3");
		
		JRadioButton rdbtnJillStein = new JRadioButton("Jill Stein");
		buttonGroup.add(rdbtnJillStein);
		rdbtnJillStein.setActionCommand("4");
		
		JButton btnMakeSelection = new JButton("Make Selection");
		btnMakeSelection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (buttonGroup.getSelection() != null){
					System.out.println("Selection made!");
					String selection = buttonGroup.getSelection().getActionCommand();
					transitionScreens();
					
					switch (selection){
					case "1":
						createVoteConfirmScreen("Hillary R. Clinton", selection);
						break; 
					case "2":
						createVoteConfirmScreen("Donald J. Trump", selection);
						break;
					case "3":
						createVoteConfirmScreen("Gary Johnson", selection);
						break; 
					case "4":
						createVoteConfirmScreen("Jill Stein", selection); 
						break; 
					}
				}
				else {
					System.out.println("NO CHOICE MADE!");
				}
				
			}
		});
		
		
		body.add(rdbtnHillaryRClinton);
		body.add(rdbtnDonaldJTrump);
		body.add(rdbtnGaryJohnson);
		body.add(rdbtnJillStein);
		body.add(btnMakeSelection);
		
		
		//TODO add print method
	}

	public void createVoteConfirmScreen(String candidateName, String candidateID){
		titleLabel.setText("Confirm Your Choice");
		final ButtonGroup buttonGroup = new ButtonGroup(); 
		
		JLabel lblYourchoicehere = new JLabel("Vote for:\n" + candidateName);
			
		JButton btnDynamicbutton = new JButton("No Selection");
		btnDynamicbutton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String selection = buttonGroup.getSelection().getActionCommand(); 
				
				if (selection.equalsIgnoreCase("submit")){
					
					//driver.postVote(candidateID); 
					
					transitionScreens(); 
					
					JLabel confirmSubmission = new JLabel("You voted for: " + candidateName);
					
					body.add(confirmSubmission);
					
				}
				else if (selection.equalsIgnoreCase("change")){
					transitionScreens(); 
					createVoterSelectionScreen();
				}
				
			}
		});
		
		btnDynamicbutton.setEnabled(false);

		JRadioButton rdbtnSumbitChoice = new JRadioButton("Sumbit Vote");
		rdbtnSumbitChoice.setActionCommand("submit");
		rdbtnSumbitChoice.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				btnDynamicbutton.setText("Submit Your Vote");
				btnDynamicbutton.setEnabled(true);
			}
		});
		
		buttonGroup.add(rdbtnSumbitChoice);
		
		JRadioButton rdbtnChangeChoice = new JRadioButton("Change Choice");
		rdbtnChangeChoice.setActionCommand("change");
		rdbtnChangeChoice.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				btnDynamicbutton.setText("Change Your Vote");
				btnDynamicbutton.setEnabled(true);
			}
		});
		
		buttonGroup.add(rdbtnChangeChoice);
		
		
		
		body.add(lblYourchoicehere);
		body.add(rdbtnSumbitChoice);
		body.add(rdbtnChangeChoice);
		
		body.add(btnDynamicbutton);

		
		
	}
	public void createPollingOfficialScreen(){
		titleLabel.setText("HIGHLY CONFIDENTIAL: Results of 2016 Election for South Carolina");
		JTextPane results = new JTextPane();
		results.setText(driver.getTally());	
		body.add(results);
		//TODO add print method
	}
	
	public void createSignInScreen(){
		titleLabel.setText("Welcome to the eVoting System for the State of South Carolina");
		title.add(titleLabel);
		ButtonGroup roleRadioButtons = new ButtonGroup();
		JRadioButton voterRadioButton = new JRadioButton("I am a voter");
		voterRadioButton.setSelected(true);
		JRadioButton pollingOfficialRadioButton = new JRadioButton("I am a polling official");
		roleRadioButtons.add(voterRadioButton);
		roleRadioButtons.add(pollingOfficialRadioButton);
		body.add(voterRadioButton);
		body.add(pollingOfficialRadioButton);

		idTextField = new JTextField("id");
		body.add(idTextField);
		idTextField.setColumns(10);

		JButton signInButtonforVoterAndPollingOfficial = new JButton("Sign In");
		JLabel returnLabel = new JLabel();
		
		signInButtonforVoterAndPollingOfficial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = idTextField.getText().trim();
				if(voterRadioButton.isSelected()){
					driver.signInCounter++;
					JLabel voterSignInAttempts = new JLabel("Voter Sign In Attempts(No more than 3 allowed): "+ driver.signInCounter);
					body.add(voterSignInAttempts);
					// TODO add validate input checks
					if (driver.signInCounter < 3) {
						int result = driver.validate(id, "V");
						if (result == 1) {//voter has voted
							returnLabel.setText("This id is associated with a voter who has already voted.");
							idTextField.setText("");
						} else if(result == 2){
							returnLabel.setText("This ID does not match that of a valid voter");
							idTextField.setText("");
						}
						else if (result == 0) {//voter has not voted and is valid
							returnLabel.setText(driver.getVoterInfo(id));
							JButton voterVerifiedInformation = new JButton("This is me");
							JButton voterFailstoVerifyInformation = new JButton("This is NOT me");
							body.add(voterVerifiedInformation);
							body.add(voterFailstoVerifyInformation);
							//TODO voter proceeds, voter does not proceed
						} 
					}
					else{
						//TODO Alert official
						System.out.println("in the else for action");
					}
				}
				else{
					if(pollingOfficialRadioButton.isSelected()){
						int result = driver.validate(id, "P");
						if(result == 0){
							System.out.println("VALID POLLING OFFICIAL");
							transitionScreens();
							createPollingOfficialScreen();
						
						}
						else{
							
							System.out.println("RESULT IS: " + result);
						}
					}
					
				}
			}
		});
		body.add(signInButtonforVoterAndPollingOfficial);
		body.add(returnLabel);
		
	}
	/**
	 * Create the frame.
	 */
	public mainScreen() {
		this.driver = new Driver();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		this.contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		this.title = new JPanel();
		contentPane.add(title, BorderLayout.NORTH);
		this.titleLabel = new JLabel();
		this.body = new JPanel();
		contentPane.add(body, BorderLayout.CENTER);
		body.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));//TODO only allow one radio button to be selected and select voter by default
	}
	

}
