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


