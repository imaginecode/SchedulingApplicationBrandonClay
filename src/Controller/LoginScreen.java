package Controller;

import Data.AppointmentsData;
import Data.UserNamePassQuery;
import Model.Appointment;
import Model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;
/** class LoginScreen.java
 * Login screen for getting into the scheduling application. Includes username and password and user location as well as login validity checks.
 * Also methods are included that automatically translate this login menu to french*/

/**@author Brandon Clay */
public class LoginScreen implements Initializable {
    public Label locationLabelTxt;
    public Label userLocationTxt;
    public Label passwordTxt;
    public Label usernameTxt;
    public Button LoginButton;
    public TextField userNameFld;
    public Label SchedulingAppLoginTxt;
    public PasswordField PasswordFld;
    private ResourceBundle rb;
    public int userID;
// Future implementation tracking user across the application
// private String currentUser;
    /** Status of if user name and password credentials are valid */
    private boolean successStatus;

    /** String for name of file that will record login attempts */
    String filename = "login_activity";


    /** Login button and checks for a successful or unsuccessful login attempt. Directs to main menu if successful otherwise
     * logs invalid attempt and displays error messages.
     * @param actionEvent login button
     */
    public void LoginHandler(ActionEvent actionEvent) throws IOException {

        try{

            if (loginChecks(userNameFld.getText(), PasswordFld.getText())) {
                successStatus = true;
                loginLogger();
                appointmentAlert();

                try {
                    Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
                    Parent scene = FXMLLoader.load(getClass().getResource("/View/MainScreen.fxml"));
                    stage.setTitle("Home");
                    stage.setScene(new Scene(scene));
                    stage.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }

            else {
                successStatus = false;
                loginLogger();
            }}

        catch(SQLException e){
            System.out.println(e.getMessage());
        }

    }
    /** Checks username and password fields to see if they are correct and also checks for empty fields. If incorrect calls appropriate loginError
     * @param userNameFld username entered by user in login screen
     * @param PasswordFld password entered by user in login screen
     * @return if credentials are verified in the database with t/f bool*/
    private boolean loginChecks(String userNameFld, String PasswordFld) throws SQLException {
        if(userNameFld.isEmpty()){
            loginErrors(1);
            return false;
        }
        if(PasswordFld.isEmpty()){
            loginErrors(2);
            return false;
        }
        if (UserNamePassQuery.validateUser(userNameFld, PasswordFld)) {
            //Future implementation for tracking user across app
//            currentUser = String.valueOf(userNameFld);
            return true;
        }
        else {
            loginErrors(3);
            return false;
        }

    }


    /** Displays messages for empty login fields and invalid usernames and passwords
     * @param alertnum is for selecting which specific alert case is needed */
    public void loginErrors(int alertnum) {
        if((Locale.getDefault().getLanguage().equals("fr") || Locale.getDefault().getLanguage().equals("en") )) {
            Alert alert = new Alert(Alert.AlertType.ERROR);

            switch (alertnum) {
                case 1:
                    alert.setTitle(rb.getString("nousername"));
                    alert.setHeaderText(rb.getString("nousername"));
                    alert.setContentText(rb.getString("nousername"));
                    alert.showAndWait();
                    break;
                case 2:
                    alert.setTitle(rb.getString("nopassword"));
                    alert.setHeaderText(rb.getString("nopassword"));
                    alert.setContentText(rb.getString("nopassword"));
                    alert.showAndWait();
                    break;
                case 3:
                    alert.setTitle(rb.getString("incorrectCredentials"));
                    alert.setHeaderText(rb.getString("incorrectCredentials"));
                    alert.setContentText(rb.getString("incorrectCredentials"));
                    alert.showAndWait();
                    break;

            }
        }
    }

/** Method alerts user of appointment within 15 minutes of login */
public void appointmentAlert() throws SQLException {



    try {
        userID =UserNamePassQuery.getUserIDByName(userNameFld.getText());

        Appointment returnedAppoint = AppointmentsData.within15Minutes(userID);
        if(returnedAppoint != null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Upcoming appointment: " + +returnedAppoint.getAptID() + " " + returnedAppoint.getStart() + "For active User ID: " + userID);
            alert.showAndWait();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION, " No Upcoming appointment for active USER ID: "  + userID);
            alert.showAndWait();
        }
    }
    catch(SQLException | NullPointerException e){
        e.printStackTrace();
    }
}


    /** Logs successful and unsuccessful logins to Login_Attempt.txt   */

    public void loginLogger () {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu/MM/dd");
        LocalDate localDate = LocalDate.now();
        String hms = "HH:mm:ss";
        DateTimeFormatter timeColonFormatter = DateTimeFormatter.ofPattern(hms);
        LocalTime currentTime = LocalTime.now();
        try {
            File newlog = new File(filename);
            if (newlog.createNewFile())
                System.out.println("Log file created");
            else
                System.out.println("Log file already created previously");

                //Success of login and log it as a valid attempt with userdata and time stamp
            FileWriter fw = new FileWriter(filename, true);
            PrintWriter outputFile = new PrintWriter(fw);
            if (successStatus) {
                outputFile.println("Valid Login attempt by Username: " + userNameFld.getText() + " Password:" + PasswordFld.getText() + " at Datetime "  + dtf.format(localDate) + " " + currentTime + " " + String.valueOf(ZoneId.systemDefault()));
            }
                //Log invalid details
            else {
                outputFile.println("Invalid Login attempt by Username: " + userNameFld.getText() + " Password:" + PasswordFld.getText() + " at time "  + dtf.format(localDate) + " " + currentTime + " " + String.valueOf(ZoneId.systemDefault()) );
            }
            outputFile.close();
        }
        catch (IOException e){ e.printStackTrace();}

    }

    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        /** Resource Bundle for language translation */
         rb = ResourceBundle.getBundle("LanguageProperties/language", Locale.getDefault());
        if (Locale.getDefault().getLanguage().equals("fr") ) {

            usernameTxt.setText(rb.getString("Username"));
           passwordTxt.setText(rb.getString("Password"));
            LoginButton.setText(rb.getString("Login"));
           locationLabelTxt.setText(rb.getString("Location"));
           SchedulingAppLoginTxt.setText(rb.getString("Scheduling"));

            usernameTxt.setText(rb.getString("Username"));
            passwordTxt.setText(rb.getString("Password"));
            LoginButton.setText(rb.getString("Login"));
            locationLabelTxt.setText(rb.getString("Location"));

        }
        // Sets user location based on time zone of users system
        userLocationTxt.setText(String.valueOf(ZoneId.systemDefault()));


    }
}
