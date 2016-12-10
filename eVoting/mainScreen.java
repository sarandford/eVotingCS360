package eVoting;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.Timer;

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
					frame.transitionScreens();

					frame.createSignInScreen();
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void transitionScreens() {
		// CITATION: this code informed from reading on
		// https://coderanch.com/t/340488/java/Adding-removing-components-JPanel
		body.removeAll();
		body.revalidate();
		body.repaint();
	}

	public void createVoterSelectionScreen(String voterId) {
		final ButtonGroup buttonGroup = new ButtonGroup();

		titleLabel.setText("Choose a Candidate");

		JRadioButton rdbtnHillaryRClinton = new JRadioButton("Hillary R. Clinton");
		rdbtnHillaryRClinton.setFont(new Font("Dialog", Font.PLAIN, 35));
		buttonGroup.add(rdbtnHillaryRClinton);
		rdbtnHillaryRClinton.setActionCommand("1");

		JRadioButton rdbtnDonaldJTrump = new JRadioButton("Donald J. Trump");
		rdbtnDonaldJTrump.setFont(new Font("Dialog", Font.PLAIN, 35));
		buttonGroup.add(rdbtnDonaldJTrump);
		rdbtnDonaldJTrump.setActionCommand("2");

		JRadioButton rdbtnGaryJohnson = new JRadioButton("Gary Johnson");
		rdbtnGaryJohnson.setFont(new Font("Dialog", Font.PLAIN, 35));
		buttonGroup.add(rdbtnGaryJohnson);
		rdbtnGaryJohnson.setActionCommand("3");

		JRadioButton rdbtnJillStein = new JRadioButton("Jill Stein");
		rdbtnJillStein.setFont(new Font("Dialog", Font.PLAIN, 35));
		buttonGroup.add(rdbtnJillStein);
		rdbtnJillStein.setActionCommand("4");

		JRadioButton rdbtnNone = new JRadioButton("None of these");
		rdbtnNone.setFont(new Font("Dialog", Font.PLAIN, 35));
		buttonGroup.add(rdbtnNone);
		rdbtnNone.setActionCommand("5");

		JButton btnMakeSelection = new JButton("Make Selection");
		btnMakeSelection.setFont(new Font("Dialog", Font.PLAIN, 35));
		btnMakeSelection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (buttonGroup.getSelection() != null) {
					System.out.println("Selection made!");
					String selection = buttonGroup.getSelection().getActionCommand();
					transitionScreens();

					switch (selection) {
					case "1":
						createVoteConfirmScreen("Hillary R. Clinton", selection, voterId);
						break;
					case "2":
						createVoteConfirmScreen("Donald J. Trump", selection, voterId);
						break;
					case "3":
						createVoteConfirmScreen("Gary Johnson", selection, voterId);
						break;
					case "4":
						createVoteConfirmScreen("Jill Stein", selection, voterId);
						break;

					case "5":
						createVoteConfirmScreen("None of these", selection, voterId);
						break;
					}
				} else {
					System.out.println("NO CHOICE MADE!");
				}

			}
		});

		body.add(rdbtnHillaryRClinton);
		body.add(rdbtnDonaldJTrump);
		body.add(rdbtnGaryJohnson);
		body.add(rdbtnJillStein);
		body.add(rdbtnNone);
		body.add(btnMakeSelection);
	}

	public void createPollingOfficialAlertedScreen() {
		titleLabel.setText("Invalid ID has been entered too many times");
		JTextField pollingOfficialIdField = new JTextField();
		pollingOfficialIdField.setColumns(20);
		pollingOfficialIdField.setText("Enter polling official id");
		pollingOfficialIdField.setFont(new Font("Dialog", Font.PLAIN, 35));
		JButton submitIdButton = new JButton("Sign In");
		body.add(pollingOfficialIdField);
		body.add(submitIdButton);
		JTextPane signInFailure = new JTextPane();
		signInFailure.setFont(new Font("Dialog", Font.PLAIN, 35));
		submitIdButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String result = pollingOfficialIdField.getText().trim();
				if (driver.validate(result, "P") == 0) {
					driver.voterSignInCounter = 0;
					driver.pollingOfficialSignInCounter = 0;
					transitionScreens();
					createSignInScreen();
				} else {
					signInFailure.setText("This ID does not match that of a valid polling official.\nPlease try again");
					pollingOfficialIdField.setText("");
				}
			}
		});
		body.add(signInFailure);

	}

	public void createVoteConfirmScreen(String candidateName, String candidateID, String voterId) {
		titleLabel.setText("Confirm Your Choice");
		final ButtonGroup buttonGroup = new ButtonGroup();

		JLabel lblYourChoiceHere = new JLabel("Vote for:\n" + candidateName);
		lblYourChoiceHere.setFont(new Font("Dialog", Font.PLAIN, 35));
		JButton btnDynamicButton = new JButton("No Selection");
		btnDynamicButton.setFont(new Font("Dialog", Font.PLAIN, 35));
		btnDynamicButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selection = buttonGroup.getSelection().getActionCommand();

				if (selection.equalsIgnoreCase("submit")) {

					if (driver.postVote(candidateID, voterId)) {

						transitionScreens();

						JTextPane confirmSubmission = new JTextPane();
						confirmSubmission.setText("You voted for: " + candidateName + "\n");
						confirmSubmission.setFont(new Font("Dialog", Font.PLAIN, 35));
						JButton printAndExit = new JButton("Print and Exit Booth");
						printAndExit.setFont(new Font("Dialog", Font.PLAIN, 35));
						body.add(confirmSubmission);
						body.add(printAndExit);

						printAndExit.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								try {
									confirmSubmission.print();
									transitionScreens();
									driver.voterSignInCounter = 0;
									driver.pollingOfficialSignInCounter = 0;
									createSignInScreen();
								} catch (PrinterException printException) {
									printException.printStackTrace();
								}
							}
						});
					} else {
						JTextPane error = new JTextPane();
						error.setText(
								"A database error has occurred. Please notify official. You may vote on paper as an alternative");
						body.add(error);
					}

				} else if (selection.equalsIgnoreCase("change")) {
					transitionScreens();
					createVoterSelectionScreen(voterId);
				}

			}
		});

		btnDynamicButton.setEnabled(false);
		JRadioButton rdbtnSumbitChoice = new JRadioButton("Sumbit Vote");
		rdbtnSumbitChoice.setFont(new Font("Dialog", Font.PLAIN, 35));
		rdbtnSumbitChoice.setActionCommand("submit");
		rdbtnSumbitChoice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnDynamicButton.setText("Submit Your Vote");
				btnDynamicButton.setEnabled(true);
			}
		});

		buttonGroup.add(rdbtnSumbitChoice);

		JRadioButton rdbtnChangeChoice = new JRadioButton("Change Choice");
		rdbtnChangeChoice.setFont(new Font("Dialog", Font.PLAIN, 35));
		rdbtnChangeChoice.setActionCommand("change");
		rdbtnChangeChoice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnDynamicButton.setText("Change Your Vote");
				btnDynamicButton.setEnabled(true);
			}
		});
		buttonGroup.add(rdbtnChangeChoice);

		body.add(lblYourChoiceHere);
		body.add(rdbtnSumbitChoice);
		body.add(rdbtnChangeChoice);

		body.add(btnDynamicButton);

	}

	public void createPollingOfficialScreen() {
		titleLabel.setText("HIGHLY CONFIDENTIAL: Results of 2016 Election for South Carolina");
		JTextPane results = new JTextPane();
		results.setFont(new Font("Dialog", Font.PLAIN, 35));
		results.setText(driver.getTally());
		JButton print = new JButton("print");
		print.setFont(new Font("Dialog", Font.PLAIN, 35));
		body.add(results);
		body.add(print);

		print.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					results.print();
					transitionScreens();
					driver.voterSignInCounter = 0;// the voter count should not
													// have
													// changed but we reset it
													// to
													// ensure it is 0 again
					driver.pollingOfficialSignInCounter = 0;
					createSignInScreen();
				} catch (PrinterException printException) {
					// TODO Auto-generated catch block
					printException.printStackTrace();
				}
			}
		});
	}

	public void alertToImpendingLockOut(){
		JOptionPane.showMessageDialog(contentPane, "You have entered your id incorrectly too many times and we suspect an attempted security breach\nThe system will shut down when you confirm or exit this pop up.");
		System.exit(0);
	}
	public void createSignInScreen() {
		titleLabel.setText("Welcome to the eVoting System for the State of South Carolina");
		title.add(titleLabel);
		ButtonGroup roleRadioButtons = new ButtonGroup();
		JRadioButton voterRadioButton = new JRadioButton("I am a voter");
		JRadioButton pollingOfficialRadioButton = new JRadioButton("I am a polling official");
		JLabel signInAttempts = new JLabel("Voter sign in attempts(3 Allowed): " + driver.voterSignInCounter);
		voterRadioButton.setSelected(true);
		voterRadioButton.setFont(new Font("Dialog", Font.PLAIN, 35));
		signInAttempts.setFont(new Font("Dialog", Font.PLAIN, 35));
		pollingOfficialRadioButton.setActionCommand("change");
		pollingOfficialRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				signInAttempts.setText(
						"Polling official sign in attempts(5 Allowed): " + driver.pollingOfficialSignInCounter);
			}
		});
		voterRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				signInAttempts.setText("Voter sign in attempts(3 Allowed): " + driver.voterSignInCounter);
			}
		});
		pollingOfficialRadioButton.setFont(new Font("Dialog", Font.PLAIN, 35));
		roleRadioButtons.add(voterRadioButton);
		roleRadioButtons.add(pollingOfficialRadioButton);

		body.add(voterRadioButton);
		body.add(pollingOfficialRadioButton);

		JTextField idTextField = new JTextField("ID");
		idTextField.setColumns(20);
		idTextField.setFont(new Font("Dialog", Font.PLAIN, 35));
		body.add(idTextField);

		JButton signInButtonforVoterAndPollingOfficial = new JButton("Sign In");
		signInButtonforVoterAndPollingOfficial.setFont(new Font("Dialog", Font.PLAIN, 35));
		JTextPane returnText = new JTextPane();
		returnText.setFont(new Font("Dialog", Font.PLAIN, 35));

		body.add(signInAttempts);

		signInButtonforVoterAndPollingOfficial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = idTextField.getText().trim();
				if (voterRadioButton.isSelected()) {
					driver.voterSignInCounter++;
					signInAttempts
							.setText("Voter Sign In Attempts(No more than 3 allowed): " + driver.voterSignInCounter);

					if (id.length() == 13) {
						int result = driver.validate(id, "V");
						if (result == 1) {// voter has voted
							returnText.setText("ERROR: This id is associated with a voter who has already voted.");
							idTextField.setText("");
						} else if (result == 2) {
							returnText.setText("ERROR: This ID does not match that of a valid voter");
							idTextField.setText("");
						} else if (result == 0) {// voter has not voted and
													// is valid
							returnText.setText(driver.getVoterInfo(id));
							JButton voterVerifiedInformation = new JButton("This is me");
							voterVerifiedInformation.setFont(new Font("Dialog", Font.PLAIN, 35));
							JButton voterFailstoVerifyInformation = new JButton("This is NOT me");
							voterFailstoVerifyInformation.setFont(new Font("Dialog", Font.PLAIN, 35));
							body.add(voterVerifiedInformation);
							body.add(voterFailstoVerifyInformation);

							voterVerifiedInformation.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									transitionScreens();
									createVoterSelectionScreen(id);
								}
							});
							voterFailstoVerifyInformation.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									transitionScreens();
									if (driver.voterSignInCounter == 3) {
										Toolkit.getDefaultToolkit().beep(); // found
																			// from
										// http://stackoverflow.com/questions/10771441/java-equivalent-of-c-sharp-system-beep
//										transitionScreens();
										createPollingOfficialAlertedScreen();
									} else {
										createSignInScreen();
									}
								}
							});

						}
					} else {
						returnText.setText("ERROR: This ID does not match that of a valid voter");
						idTextField.setText("");
						if (driver.voterSignInCounter == 3) {
							Toolkit.getDefaultToolkit().beep(); // found
							transitionScreens();
							createPollingOfficialAlertedScreen();
						}
					}

				} else if (pollingOfficialRadioButton.isSelected()) {
					driver.pollingOfficialSignInCounter++;
					if (id.length() == 5) {
						int result = driver.validate(id, "P");
						if (result == 0) {
							if (result == 0) {
								transitionScreens();
								createPollingOfficialScreen();
							} else {
								System.out.println("INVALID");
								returnText.setText("This id does not match that of a valid polling official");
								signInAttempts.setText("Polling official sign in attempts(5 Allowed): "
										+ driver.pollingOfficialSignInCounter);
								if(driver.pollingOfficialSignInCounter == 5){
									alertToImpendingLockOut();
								}
							}
						} else {
							returnText.setText("This id does not match that of a valid polling official");
						}
						idTextField.setText("");
					} else {
						returnText.setText("This id does not match that of a valid polling official");
						idTextField.setText("");
						signInAttempts.setText(
								"Polling official sign in attempts(5 Allowed): " + driver.pollingOfficialSignInCounter);
						if(driver.pollingOfficialSignInCounter == 5){
							alertToImpendingLockOut();
						}

					}

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
		setTitle("South Carolina eVoting System");
		setFont(new Font("Dialog", Font.PLAIN, 38));
		this.driver = new Driver();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(600, 600, 2000, 2000);
		this.contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		this.title = new JPanel();
		contentPane.add(title, BorderLayout.NORTH);
		this.titleLabel = new JLabel();
		titleLabel.setFont(new Font("Dialog", Font.PLAIN, 33));
		this.body = new JPanel();
		contentPane.add(body, BorderLayout.CENTER);
		body.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
	}

}
