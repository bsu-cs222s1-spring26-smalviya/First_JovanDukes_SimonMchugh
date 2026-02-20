# First_JovanDukes_SimonMchugh

First Iteration
----------------------------------
Authors: Jovan Dukes & Simon Mchugh
----------------------------------
Project Summary:

This project is a java program that uses the meediaWiki API to retrieve an show the 15 most recent edits to a wikipedia article
----------------------------------
Program Function:

1. The user is prompted to input the name of a wikipedia article using scanner (Main.java)
2. The revised data is retrieved via an HTTP request (WikipediaClient.java)
3. Parses the JSON response that was returned (RevisionParser.java)
4. The revision number, timestamp, and usersname are displayed (RevisonFormatter.java)
5. Shows the relevant error messages for network problems (Main.java)

----------------------------------
Build Requirements:
- Gradle

Run instructions:

- Click "run" to initiate the program
- Input article title(Zappa, Frank Zappa, etc.) into command line
----------------------------------
Entry Point # 1 : Graphical User Interface(GUI)
----------------------------------
Entry point: Wikipedia.WikiUI

Description: Launches a windowed interface where you can enter a Wikipedia article name and view its most recent revisions.

Run instructions: Run with gradle(Gradle>Task>Application>Run)
Enter Wikipedia article name(Frank Zappa)
For test Classes: Gradle>Task>Verification>Test

----------------------------------
Entry Point # 2 : Console Application
----------------------------------
Entry point: Wikipedia.main

Description: Launches a text-based console application. Users can type an article name, and the program prints the 15 most recent revisions in the terminal.

Run instructions: Go to main.java and select Run Main.main()
Enter Wikipedia article name(Frank Zappa)
