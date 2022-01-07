package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginScreen {
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

    public void LoginHandler(ActionEvent actionEvent) throws IOException {
        try{Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            Parent scene = FXMLLoader.load(getClass().getResource("/View/MainScreen.fxml"));
            stage.setTitle("Home");
            stage.setScene(new Scene(scene));
            stage.show();}
        catch (Exception e )
        {
            e.printStackTrace();
        }


    }
}
