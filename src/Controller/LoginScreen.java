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
import java.sql.SQLException;
import java.time.ZoneId;
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
    private ResourceBundle rb;


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

        try{
            //            createLoginAttemptsLog();
            if (loginChecks(userNameFld.getText(), PasswordFld.getText())) {

                loginLogger(Boolean.TRUE);
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
                loginLogger(Boolean.FALSE);
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
        if (UserNamePassQuery.validateUser(userNameFld, PasswordFld))
            loginErrors(3);
        return true ;

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




    /** Logs successful and unsuccessful logins to Login_Attempt.txt   */

    public void loginLogger (Boolean successStatus ) {

    }


    /** Creates Login_Attempt.txt if not already created
     * catches exception and prints stack trace */
    public void createLoginAttemptsLog() {
    }

    /** Alerts User to appointment that is within 15 minutes of user login */
    private void appointmentAlert(){}

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
