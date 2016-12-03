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
import java.awt.Color;

import javax.swing.JInternalFrame;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
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
		// CITATION: this code informed from reading on https://coderanch.com/t/340488/java/Adding-removing-components-JPanel 
		body.removeAll();
		body.revalidate();
		body.repaint();
	}
	
	public void createVoterSelectionScreen(){
		
		//TODO add print method
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
		
		JTextField idTextField = new JTextField("ID");
		idTextField.setColumns(20);
		body.add(idTextField);
		
		JButton signInButtonforVoterAndPollingOfficial = new JButton("Sign In");
		JTextPane returnText = new JTextPane();
		
		signInButtonforVoterAndPollingOfficial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = idTextField.getText().trim();
				if(id.length() == 5 || id.length() == 13 ){
				if(voterRadioButton.isSelected()){
					driver.signInCounter++;
					JLabel voterSignInAttempts = new JLabel("Voter Sign In Attempts(No more than 3 allowed): "+ driver.signInCounter);
					body.add(voterSignInAttempts);
					// TODO add validate input checks
					if (driver.signInCounter < 3) {
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
						//TODO Alert official
						System.out.println("in the else for action");
					}
				}
				else{
					if(pollingOfficialRadioButton.isSelected()){
						int result = driver.validate(id, "P");
						if(result == 0){
							transitionScreens();
							createPollingOfficialScreen();
						
						}
						else{
							
							System.out.println("RESULT IS: " + result);
						}
					}
					
				}
			}
				else{
					returnText.setText("ERROR: The input is not in a valid format.\n Ensure you have entered your id correctly and try again");
				}
		}});
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
