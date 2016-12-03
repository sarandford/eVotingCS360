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
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.awt.event.ActionEvent;

import eVoting.Driver;

public class mainScreen extends JFrame {

	private JPanel contentPane;
	private Driver driver;
	private JPanel title;
	private JLabel titleLabel;
	private JPanel body;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
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
		// CITATION: this code informed from reading on https://coderanch.com/t/340488/java/Adding-removing-components-JPanel 
		body.removeAll();
		body.revalidate();
		body.repaint();
	}
	
	public void createVoterSelectionScreen(){
		
		//TODO add print method
	}
	
	public void createPollingOfficialAlertedScreen(){
		titleLabel.setText("Invalid ID has been entered too many times");
		JTextField pollingOfficialIdField = new JTextField();
		pollingOfficialIdField.setColumns(20);
		pollingOfficialIdField.setText("Enter polling official id");
		JButton submitIdButton = new JButton("Sign In");
		body.add(pollingOfficialIdField);
		body.add(submitIdButton);
		JTextPane signInFailure = new JTextPane();
		submitIdButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String result = pollingOfficialIdField.getText().trim();
				if(driver.validate(result, "P") == 0){
					driver.signInCounter = 0;
					transitionScreens();
					createSignInScreen();
				}
				else{
					signInFailure.setText("This ID does not match that of a valid polling official.\nPlease try again");
					pollingOfficialIdField.setText("");
				}
			}});
		body.add(signInFailure);
		
	}
	
	public void createPollingOfficialScreen(){
		titleLabel.setText("HIGHLY CONFIDENTIAL: Results of 2016 Election for South Carolina");
		JTextPane results = new JTextPane();
		results.setText(driver.getTally());	
		JButton print = new JButton("print");
		body.add(results);
		body.add(print);

		print.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					results.print();
					transitionScreens();
					driver.signInCounter = 0;//the voter count should not have changed but we reset it to ensure it is 0 again
					createSignInScreen();
				} catch (PrinterException printException) {
					// TODO Auto-generated catch block
					printException.printStackTrace();
				}
				}
			});
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
		
		JTextField idTextField = new JTextField("ID");
		idTextField.setColumns(20);
		body.add(idTextField);
		
		JButton signInButtonforVoterAndPollingOfficial = new JButton("Sign In");
		JTextPane returnText = new JTextPane();
		JLabel voterSignInAttempts = new JLabel("Voter Sign In Attempts(No more than 3 allowed): "+ driver.signInCounter);
		body.add(voterSignInAttempts);

		signInButtonforVoterAndPollingOfficial.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String id = idTextField.getText().trim();
				if(voterRadioButton.isSelected()){
					driver.signInCounter++;
					voterSignInAttempts.setText("Voter Sign In Attempts(No more than 3 allowed): "+ driver.signInCounter);
					if (driver.signInCounter < 3) {
						if(id.length() == 13){
							int result = driver.validate(id, "V");
							if (result == 1) {//voter has voted
								returnText.setText("ERROR: This id is associated with a voter who has already voted.");
								idTextField.setText("");
							} else if(result == 2){
								returnText.setText("ERROR: This ID does not match that of a valid voter");
								idTextField.setText("");
							}
							else if (result == 0) {//voter has not voted and is valid
								returnText.setText(driver.getVoterInfo(id));
								JButton voterVerifiedInformation = new JButton("This is me");
								JButton voterFailstoVerifyInformation = new JButton("This is NOT me");
								body.add(voterVerifiedInformation);
								body.add(voterFailstoVerifyInformation);
								//TODO voter proceeds, voter does not proceed
							} 
						}
						else{
							returnText.setText("ERROR: Invalid id format");
						}
					}
					else{
						//TODO Alert official
						java.awt.Toolkit.getDefaultToolkit().beep(); //found from http://stackoverflow.com/questions/10771441/java-equivalent-of-c-sharp-system-beep 
						transitionScreens();
						createPollingOfficialAlertedScreen();
						
					}
				}
				else if(pollingOfficialRadioButton.isSelected()){
					if(id.length() == 5){
						int result = driver.validate(id, "P");
						if(result == 0){
							transitionScreens();
							createPollingOfficialScreen();
						
						}
						else{
							returnText.setText("This id does not match that of a valid polling official");
						}
					}
					else{
						returnText.setText("ERROR: Invalid ID format. Please try again");
					}
					idTextField.setText("");
				}
			}
			
		});
		body.add(signInButtonforVoterAndPollingOfficial);
		body.add(returnText);
		
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
		body.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
	}
	

}
