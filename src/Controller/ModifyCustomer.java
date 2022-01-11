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

public class ModifyCustomer {
    public TextField zip;
    public TextField streetAddress;
    public ComboBox firstLevelUpdate;
    public ComboBox countryCombo;
    public Button cancelAdd;
    public Button saveAdd;
    public TextField Customer_ID;
    public TextField Name;
    public TextField Postal;
    public TextField Phone;

    public void PhoneHandler(ActionEvent actionEvent) {
    }

    public void PostalHandler(ActionEvent actionEvent) {
    }

    public void NameHandler(ActionEvent actionEvent) {
    }

    public void Customer_IDHandler(ActionEvent actionEvent) {
    }

    public void saveAddHandler(ActionEvent actionEvent) {
    }

    public void cancelAddHandler(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Parent scene = FXMLLoader.load(getClass().getResource("/View/MainScreen.fxml"));
        stage.setTitle("Home");
        stage.setScene(new Scene(scene));
        stage.show();
    }

    public void countryComboHandler(ActionEvent actionEvent) {
    }

    public void firstLevelUpdateHandler(ActionEvent actionEvent) {
    }

    public void streetAddressHandler(ActionEvent actionEvent) {
    }

    public void zipHandler(ActionEvent actionEvent) {
    }
}
