package Controller;

import Data.AddressData;
import Data.ContactsData;
import Data.CustomersData;
import Model.Contact;
import Model.Country;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddCustomer implements Initializable {
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

    public void countryComboInit(){
        ObservableList<String> CountryList = FXCollections.observableArrayList();
        try {

            if (AddressData.getAllCountries() != null){
                for (Country country: AddressData.getAllCountries()) {
                    CountryList.add(country.getCountry());
                }
            }
            countryCombo.setItems(CountryList);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public void firstLvlInit(){

    }

/** Calls methods that set default data in combo boxes for creating new appointments
 * @param url resolve relative paths for the root object
 * @param resourceBundle resources used to localize the root object */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        countryComboInit();
        firstLvlInit();


    }
}
