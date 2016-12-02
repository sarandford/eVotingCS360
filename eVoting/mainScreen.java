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

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainScreen frame = new mainScreen();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public mainScreen() {
		Driver driver = new Driver();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);

		JLabel lblNewLabel = new JLabel("Welcome to the eVoting System for the State of South Carolina");
		panel.add(lblNewLabel);

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));//TODO only allow one radio button to be selected and select voter by default

		JRadioButton voterRadioButton = new JRadioButton("I am a voter");
		voterRadioButton.setSelected(true);
		panel_2.add(voterRadioButton);
		voterRadioButton.setVerticalAlignment(SwingConstants.BOTTOM);

		JRadioButton pollingOfficialRadioButton = new JRadioButton("I am a polling official");
		panel_2.add(pollingOfficialRadioButton);

		idTextField = new JTextField("id");
		panel_2.add(idTextField);
		idTextField.setColumns(10);

		JButton signInButtonforVoterAndPollingOfficial = new JButton("Sign In");
		boolean statusHasVoted = false;
		JLabel returnLabel = new JLabel();
		JButton confirmVoterInfoButton = new JButton();
		signInButtonforVoterAndPollingOfficial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("HERE I AM IN THE action");
				// TODO add validate input checks
				if (driver.signInCounter < 3) {
					int result = driver.validate(idTextField.getText(), "V");
					if (result == 0) {
						returnLabel.setText("This id is associated with a voter who has already voted");
						System.out.println("VOTER HAS VOTED");
						driver.signInCounter--;
						
					} else if (result == 1) {
						returnLabel.setText("VOTER INFO HERE");//TODO get voter info
						driver.getVoterInfo(idTextField.getText());
					} else {
						System.out.println("NOT A VALID VOTER");
						driver.signInCounter--;
					}
				}
				else{
					System.out.println("in the else for action");
				}
			}
		});
		panel_2.add(signInButtonforVoterAndPollingOfficial);
		panel_2.add(returnLabel);
	}

}
