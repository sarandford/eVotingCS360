package eVoting;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;
import java.awt.CardLayout;
import javax.swing.JInternalFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
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
					frame.setVisible(true);
					frame.createSignInScreen();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public void transitionScreens(){
		title.removeAll();
		body.removeAll();
	}
	
	public void createVoterSelectionScreen(){
		
	}
	
	public void createPollingOfficialSelectionScreen(){
		
	}
	
	public void createSignInScreen(){
		titleLabel.setText("Welcome to the eVoting System for the State of South Carolina");
		title.add(titleLabel);
		JRadioButton voterRadioButton = new JRadioButton("I am a voter");
		voterRadioButton.setSelected(true);
		body.add(voterRadioButton);
		voterRadioButton.setVerticalAlignment(SwingConstants.BOTTOM);

		JRadioButton pollingOfficialRadioButton = new JRadioButton("I am a polling official");
		body.add(pollingOfficialRadioButton);

		idTextField = new JTextField("id");
		body.add(idTextField);
		idTextField.setColumns(10);

		JButton signInButtonforVoterAndPollingOfficial = new JButton("Sign In");
		boolean statusHasVoted = false;
		JLabel returnLabel = new JLabel();
		JButton confirmVoterInfoButton = new JButton();
		JLabel voterSignInAttempts = new JLabel("Voter Sign In Attempts(No more than 3 allowed): "+ driver.signInCounter);
		body.add(voterSignInAttempts);
		signInButtonforVoterAndPollingOfficial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(voterRadioButton.isSelected()){
					driver.signInCounter++;
					voterSignInAttempts.setText("Voter Sign In Attempts(No more than 3 allowed): "+ driver.signInCounter);
					// TODO add validate input checks
					if (driver.signInCounter < 3) {
						int result = driver.validate(idTextField.getText(), "V");
						if (result == 1) {//voter has voted
							returnLabel.setText("This id is associated with a voter who has already voted.");
							idTextField.setText("");
						} else if(result == 2){
							returnLabel.setText("This ID does not match that of a valid voter");
							idTextField.setText("");
						}
						else if (result == 0) {//voter has not voted and is valid
							returnLabel.setText(driver.getVoterInfo(idTextField.getText()));
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
