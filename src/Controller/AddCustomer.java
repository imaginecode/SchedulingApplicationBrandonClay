package Controller;

import Data.AddressData;
import Data.ContactsData;
import Data.CustomersData;
import Model.Contact;
import Model.Country;
import Model.FirstLvlDivisions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * This class contains Adds customers to the database using a mysql insert and populates the UI with data from the database
 /** @author Brandon Clay */
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

    /** saves and returns to MainMenu and confirms users action with a dialog box
     * @param actionEvent button click of save button
     * @throws IOException while accessing information using streams, files and directories*/
    public void saveAddHandler(ActionEvent actionEvent) throws IOException, SQLException {

        if(customerChecks()){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Confirm Save");
            Optional<ButtonType> result = alert.showAndWait();

            if(result.isPresent() && (result.get() == ButtonType.OK)) {
                // Getting first level division ID to pass into insert customer query
                int divID = AddressData.getFirstLvlID((String) firstLevel.getSelectionModel().getSelectedItem());

                CustomersData.newCustomer(Name.getText(),streetAddress.getText(),Postal.getText(),Phone.getText(),divID);

                Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
                Parent scene = FXMLLoader.load(getClass().getResource("/View/MainScreen.fxml"));
                stage.setTitle("Home");
                stage.setScene(new Scene(scene));
                stage.show();
            }

        }
    }

    /** /**Checks to make sure customer is valid fields are not empty
     * @return boolean of t/f for if the appointment is valid if (f) then alerts are displayed**/
    public boolean customerChecks(){
        if(Phone.getText().isEmpty() || Postal.getText().isEmpty() || Name.getText().isEmpty()
                || streetAddress.getText().isEmpty())
        {
            addErrors(1);
            return false;
        }

        if(countryCombo.getSelectionModel().isEmpty() || firstLevel.getSelectionModel().isEmpty()) {
            addErrors(1);
            return false;
        }
        return true;
    }


/** Cancels and returns to MainMenu and confirms users action with a dialog box
 * @param actionEvent button click of cancel button
 * @throws IOException thrown while accessing information using streams, files and directories*/
    public void cancelAddHandler(ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Confirm Cancel Action");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && (result.get() == ButtonType.OK)) {

            Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            Parent scene = FXMLLoader.load(getClass().getResource("/View/MainScreen.fxml"));
            stage.setTitle("Home Screen");
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }



/** Initializes country combo box with list of countries from a query */
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
    /** Initializes first level combo box based off what country is selected. A query of the country name returns the ID
     * of the country and first lvl divs with that ID are loaded into the second combo box */
    public void countryComboHandler(ActionEvent actionEvent)  {
        try{if(!countryCombo.getSelectionModel().isEmpty()) {

            String selectedCountry =  countryCombo.getSelectionModel().getSelectedItem().toString();
            firstLvlInit(AddressData.getCountryID(selectedCountry).getCountryID());
            System.out.println("Item Selected move to next step and select FLD");


        }}
        catch (SQLException e){
            e.getStackTrace();
        }


    }
    /** Sets data in first level combo box see countryComboHandler for more details */
    public void firstLvlInit(int selectedCountryID){
        ObservableList<String> firstLvlList = FXCollections.observableArrayList();
        try {

            if (AddressData.getFirstLVLByID(selectedCountryID) != null){
                for (FirstLvlDivisions firstLvlDivisions: AddressData.getFirstLVLByID(selectedCountryID)) {
                    firstLvlList.add(firstLvlDivisions.getDivision());
                }
            }
            firstLevel.setItems(firstLvlList);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /** Displays messages for add appointment errors such as empty fields
     * @param alertnum is for selecting which specific alert case is needed */
    public void addErrors(int alertnum) {
        Alert alert = new Alert(Alert.AlertType.ERROR);

        switch (alertnum) {
            case 1:
                alert.setTitle("All fields must be filled");
                alert.setHeaderText("All fields must be filled");
                alert.setContentText("All fields  and combo boxes must be filled or selected");
                alert.showAndWait();
                break;
            case 2:
                alert.setTitle("A country must be selected");
                alert.setHeaderText("A country must be selected");
                alert.setContentText("A country must be selected before getting first level division");
                break;




        }
    }



/** Calls methods that set default data in combo boxes for creating new appointments
 * @param url resolve relative paths for the root object
 * @param resourceBundle resources used to localize the root object */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        countryComboInit();

    }


}
