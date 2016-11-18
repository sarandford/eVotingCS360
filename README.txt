eVoting (Command Line Edition)
By: Sarah Ford & Hassam Solano 

**Any file paths described within are rooted in the directory of this README**
**All necessary files are contained within the root directory of this README (Ford&Solano_Deliverable_5)**

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
	⁃	In the "General" folder select: Projects from Folder or Archive
	⁃	In the "Import Source" text box enter the file path to "eVotingSystem.tar.gz" (File Path: /eVotingSystem.tar.gz)
	⁃	Alternatively you can press the "Archive" button and search for "eVotingSystem.tar.gz" using your system's file explorer. 
	⁃	Uncheck "eVotingSystem.tar.gz_expanded"
	⁃	Leave second option (Has "Eclipse Project" under Import As column) checked.
	⁃	Press Finish

	•	Not Using Eclipse: 
	⁃	Extract "eVotingSystem.tar.gz" (File Path: /eVotingSystem.tar.gz)
	⁃	Import the "src" folder found in the extracted folder into your favorite IDE 
	⁃	IMPORTANT: Import entire "src" folder as is to maintain package structure 

	•	Open Driver.java (File Path: /eVoting/src/eVoting )
	•	Scroll to line 104 (Beginning of main method)
	•	Enter/change user, password, serverName and port to match the information of your database connection.
	⁃	**DO NOT CHANGE THE 'dbname' VARIABLE**

_End Set-up_

Running eVoting System

- Open 'Driver.java' (File Path: /eVoting/src/eVoting/Driver.java) in your favorite Java IDE (Eclipse is recommended)
- Run 'Driver.java' file 
- Follow prompts to interact with the eVoting system 

_End Running eVoting System_

Useful Information for Testing 

- Polling Officials Ids
	•	12345 (Hassam Solano)
	•	678910 (Sarah Ford)
- Voters Ids
	•	Have Already Voted:
	⁃	1600121301099 ; (Nicholas Mcintosh)
	⁃	1604040301699 ; (Tana Yang)
	⁃	1606061549799 ; (Garrison Perez)
	•	Have NOT Yet Voted:
	⁃	1655081038099 ; (Martina Merrill)
	⁃	1623031865099 ; (Phyllis Cash)
	⁃	1661051987899 ; (Pearl Larson)
	⁃	**For more see SQL queries below**

- SQL Queries (To be run on mySQLWorkbench)
	•	Find all voters:
	⁃	SELECT * FROM voters; 

- Clean-Up
	•	Database connections are automatically terminated once the user submits their vote. If the program is terminated without
	casting a vote the connection may remain open. 
	⁃	Connections can be manually terminated in mySQLWorkbench:
	⁃	In mySQLWorkbench application menu: Server -> Client Connections

_End Useful Information for Testing_

