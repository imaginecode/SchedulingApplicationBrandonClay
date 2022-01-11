package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AddCustomer {
    public TextField Phone;
    public TextField Postal;
    public TextField Name;
    public TextField Customer_ID;
    public Button saveAdd;
    public Button cancelAdd;
    public ComboBox countryCombo;
    public ComboBox firstLevel;
    public TextField streetAddress;
    public TextField zip;

    public void zipHandler(ActionEvent actionEvent) {
    }

    public void streetAddressHandler(ActionEvent actionEvent) {
    }

    public void firstLevelHandler(ActionEvent actionEvent) {
    }

    public void countryComboHandler(ActionEvent actionEvent) {
    }

    public void cancelAddHandler(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Parent scene = FXMLLoader.load(getClass().getResource("/View/MainScreen.fxml"));
        stage.setTitle("Home");
        stage.setScene(new Scene(scene));
        stage.show();
    }

    public void saveAddHandler(ActionEvent actionEvent) {
    }

    public void Customer_IDHandler(ActionEvent actionEvent) {
    }

    public void NameHandler(ActionEvent actionEvent) {
    }

    public void PostalHandler(ActionEvent actionEvent) {
    }

    public void PhoneHandler(ActionEvent actionEvent) {
    }
}
