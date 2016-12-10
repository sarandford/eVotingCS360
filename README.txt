eVoting
By: Sarah Ford & Hassam Solano 

**Any file paths described within are rooted in the directory of this README**
**All necessary files are contained within the root directory of this README (Ford&Solano_Deliverable_6)**

Set-Up:

- Prepare database 
	•	Open mySQLWorkbench
	•	Select database connection you would like to use ("localhost" is recommended)
	•	In mySQLWorkbench menu select: File -> Open SQL Script (Shift-Command-O [Mac]; Shift-Ctrl-O [Windows])
	•	Choose eVotingDB.sql (File Path: /eVotingDB.sql )
	•	Run Script (One of the following ways):
	⁃	Press lightning bolt icon
	⁃	In mySQLWorkbench menu select: Query -> Execute
	⁃	Shift-Command-Enter/Return (Mac)
	⁃	Shift-Ctrl-Enter/Return (Windows)
**The required schema is now set-up but may not show up in your list of schemas. To resolve this close and reopen your database 
connection and you should now see the new schema. eVoting system will work even if you do not restart your connection.**

- Set up code
	•	Using Eclipse:
	⁃	Open Eclipse and select a workspace
	⁃	In the Eclipse system menu press: File -> Import 
	⁃	In the "General" folder select: Existing Projects into Workspace
	⁃	Choose: Select Archive File and press the browse button to find the included “EclipseProject.zip” file
	⁃	Make sure “eVoting” is checked in the projects window. 
	⁃	Press Finish

	•	Not Using Eclipse: 
	⁃	Extract "EclipseProject.zip" (File Path: /EclipseProject.zip)
	⁃	Import the "src" folder found in the extracted folder into your favorite IDE 
	⁃	IMPORTANT: Import entire "src" folder as is to maintain package structure 

	•	Open “Driver.java” (File Path: /eVoting/src/eVoting )
	•	Scroll to line 22 (Beginning of constructor method)
	•	Enter/change user, password, serverName and port to match the information of your database connection.
	⁃	**DO NOT CHANGE THE 'dbname' VARIABLE**

_End Set-up_

Running eVoting System

- Open ‘mainScreen.java’ (File Path: /eVoting/src/eVoting/mainScreen.java) in your favorite Java IDE (Eclipse is recommended)
- Run ‘mainScreen.java’ file 
- Follow prompts to interact with the eVoting system 

_End Running eVoting System_

Useful Information for Testing 

- Polling Officials Ids
	•	12345 (Hassam Solano)
	•	67890 (Sarah Ford)
- Voters Ids
	•	NO voter has voted yet:
	⁃	1600121301099 ; (Nicholas Mcintosh)
	⁃	1604040301699 ; (Tana Yang)
	⁃	1606061549799 ; (Garrison Perez)
	⁃	1655081038099 ; (Martina Merrill)
	⁃	1623031865099 ; (Phyllis Cash)
	⁃	1661051987899 ; (Pearl Larson)
	** There are 100 voters but for the sake of space, we only include these ids for testing, you will not be able to view other ids because they are encrypted

- Clean-Up
	•	Database connections are automatically terminated once the user submits their vote. If the program is terminated without
	casting a vote the connection may remain open. 
	⁃	Connections can be manually terminated in mySQLWorkbench:
	⁃	In mySQLWorkbench application menu: Server -> Client Connections

 Test cases to prove the requirements:
 	• Voter votes normally: Pick a voter from the list above and vote. You should be able to enter your id, and if you entered it correctly, you should see the name and 
 	birthday of the voter whose id you entered (corresponding to the data above). Click the "This is me" button and then you should see a ballot with the canidates 
 	for the 2016 election. Select your choice and submit it. You should be directed to a screen that shows you who you voted for and gives you the option to confirm or 
 	change your vote.  If you choose to change your vote, you will be redirected to the ballot and must re-select and re-submit your vote. You will then be asked once again
 	to confirm your vote. If you confirm your vote, you will be take to a screen and you will be shown who you voted for. You must then print your vote in order to reset
 	the machine to the waiting state.
 	• Voter has already voted: Pick the voter whom you used to test the normal vote once you have finished the use case for a normal vote and try to enter their id into the
 	sign in box. When you submit this id, you should then see a warning saying you already voted and your number of sign-in attempts will be decreased to two.
 	• Voter misenters id 3 times in a row: To test this, you can either enter a voter id who has already voted or a string of numbers which is not correlated to any voter.
 	After three attempts, there will be a noise emitted from the computer and the polling official will be prompted to enter their id in order to continue the voting process.
 	• Voter misenters their id and it matches to another voter: Pick another voter id correlating to a voter who has not voted. Pretend as if this is not the identity you expected
 	and choose the "This is NOT me" button after you are shown the voter information. The screen should be reset but the sign in attempts will have increased. 
 	• Polling Official Enters id: Pick a polling official id and enter it into the field and sign in. If it is correct, you should be immediately redirected
 	to an unofficial tally which you must print in order to exit the screen and set the system back to its waiting state. 
 	• Polling Official mistenters id 5 times: If you attempt to enter a polling official id incorrectly 5 times, the fifth incorrect attempt will render
 	a pop up warning saying the system will close because of an attempted security breach. If you click the 'x' button or 'ok' the system will exit.
 	You will notice, there is nothing you can do to prevent the system from shutting down at this point making it very secure. 
 	
_End Useful Information for Testing_

