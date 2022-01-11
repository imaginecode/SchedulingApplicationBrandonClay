package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
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
        if (loginChecks()) {


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
    private boolean loginChecks() {
        if(userNameFld.isEmpty()){
            loginErrors(1);
            return false;
        }
        if(PasswordFld.isEmpty()){
            loginErrors(2);
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
                alert.setContentText("Your password or UserName is invalid.");
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
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
