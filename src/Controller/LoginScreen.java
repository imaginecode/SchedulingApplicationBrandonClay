package Controller;

import Data.UserNamePassQuery;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class LoginScreen implements Initializable {
    public Label locationLabelTxt;
    public Label userLocationTxt;
    public Label passwordTxt;
    public Label usernameTxt;
    public Button LoginButton;
    public TextField userNameFld;
    public Label SchedulingAppLoginTxt;
    public PasswordField PasswordFld;
    /** Resource Bundle for language translation */
    ResourceBundle rb = ResourceBundle.getBundle("LanguageProperties/language", Locale.getDefault());
    /** String for name of file that will record login attempts */
    String filename = "login_activity";

    public void PasswordFldHandler(ActionEvent actionEvent) {
    }

    public void userNameFldHandler(ActionEvent actionEvent) {
    }

    /** Login button and checks for a successful or unsuccessful login attempt. Directs to main menu if successful otherwise
     * logs invalid attempt and displays error messages.
     * @param actionEvent login button
     */
    public void LoginHandler(ActionEvent actionEvent) throws IOException {

//            createLoginAttemptsLog();
        if (loginChecks(userNameFld.getText(), PasswordFld.getText())) {


//            successfulLogin();

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
            invalidLoginAttempt();
        }

    }
    /** Checks username and password fields to see if they are correct and also checks for empty fields. If incorrect calls appropriate loginError
     * @return*/
    private boolean loginChecks(String userNameFld, String PasswordFld) {
        if(userNameFld.isEmpty()){
            loginErrors(1);
            return false;
        }
        if(PasswordFld.isEmpty()){
            loginErrors(2);
            return false;
        }
        if(!UserNamePassQuery.validateUser(userNameFld, PasswordFld)){
            return false;
        }
        return true;
    }


    /** Displays messages for empty login fields and invalid usernames and passwords
     * @param alertnum is for selecting which specific alert case is needed */
    public void loginErrors(int alertnum) {

        Alert alert = new Alert(Alert.AlertType.ERROR);

        switch (alertnum) {
            case 1:
                alert.setTitle("Invalid password or UserName");
                alert.setHeaderText("Invalid values");
                alert.setContentText("UserName is empty.");
                alert.showAndWait();
                break;
            case 2:
                alert.setTitle("Empty Password Field");
                alert.setHeaderText("Invalid Values");
                alert.setContentText("The password field can not be empty.\n");
                alert.showAndWait();
                break;
            case 3:
                alert.setTitle("Empty UserName Field");
                alert.setHeaderText("Invalid Values");
                alert.setContentText("The UserName Field can not be empty. Please enter a UserName");
                alert.showAndWait();
                break;
            case 4:
                alert.setTitle("Upcoming Appointment");
                alert.setHeaderText("Appointments");
                alert.setContentText("You have no upcoming appointments in the next 15 minutes");
                alert.showAndWait();
                break;
        }
    }




    /** On a successful login program  */
    public void successfulLogin(){}
    public void invalidLoginAttempt () {}


    /** Creates Login_Attempt.txt if not already created
     * catches exception and prints stack trace */
    public void createLoginAttemptsLog() {
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        public void initialize(URL url, ResourceBundle rb) {
            Locale locale = Locale.getDefault();
            rb = ResourceBundle.getBundle("languages/login", locale);
            usernameTxt.setText(rb.getString("Username:"));
            passwordTxt.setText(rb.getString("Password:"));
            LoginButton.setText(rb.getString("Login"));
            mainMessage.setText(rb.getString("message"));
            languageMessage.setText(rb.getString("language"));
            errorHeader = rb.getString("errorheader");
            errorTitle = rb.getString("errortitle");
            errorText = rb.getString("errortext");
        }

    }
}
