Scheduling Application by Brandon Clay
---------------------------------------------------------------------------------------------------------------------------
Contact Information: bclay18@wgu.edu
2/15/2021 v1
---------------------------------------------------------------------------------------------------------------------------
This application is an multipurpose scheduling tool that can be used for many different types of organizations. It has appointment and customer creation and editing capabilities
 as well as time zone translation so that geographically separated teams can keep appointments synced and display upcoming appointments in local time.
  There are 3 reports that give detailed breakdowns of appointments using filters built into the UI. Additionally, the log in UI is EN-FR translatable
---------------------------------------------------------------------------------------------------------------------------
Built completely in the WGU provided VM.

IDE: IntelliJ Community 2021.1.1 x64, JavaFX API of version 16 by JavaFX runtime of version 11.0.2
---------------------------------------------------------------------------------------------------------------------------
  Directions for how to run the program.
Note: This project was completely built in the WGU VM for Java 11. However, as a convience I have included a zipped version of the JDK and mysql connector.
Unzip, open main.java in IDE of choice, once opened you will see the login UI the default credentials: Username:admin Password:admin

---------------------------------------------------------------------------------------------------------------------------
An additional report is run, finding customers by zip code through a MySQL of the database, a valid zip code must be entered or no results
 are displayed and multiple results can be displayed for one zip/postal code
---------------------------------------------------------------------------------------------------------------------------
 the MySQL Connector driver version number: mysql-connector-java-8.0.25
---------------------------------------------------------------------------------------------------------------------------
JavaDocs can be found in root folder
---------------------------------------------------------------------------------------------------------------------------

Additional Instructions:
-Week and month views show the next 7 days starting for the current DateTime.
-When working with appointments the time combos are contained to EST 8AM-10PM and
then translated into local time to accommodate an easier appointment booking experience
 for the user without them having to think about time translation.