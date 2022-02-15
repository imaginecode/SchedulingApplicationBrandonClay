package Controller;

import Data.AddressData;
import Data.CustomersData;
import Model.Country;
import Model.Customer;
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
 * This class contains modify customers to the database using a mysql update and populates the UI with data from
 * the selected Customer
 /** @author Brandon Clay */
public class ModifyCustomer implements Initializable {
    public TextField streetAddress;
    public ComboBox countryCombo;
    public Button saveAdd;
    public TextField Customer_ID;
    public TextField Name;
    public TextField Postal;
    public TextField Phone;
    public ComboBox firstLevel;

    /**  Takes data from selected customer and populates fields for customers to edit*/
    public void dataHandoff(Customer selectedCustomer) throws SQLException {
        streetAddress.setText(selectedCustomer.getAddress());
        Customer_ID.setText(String.valueOf(selectedCustomer.getCustomerID()));
        Name.setText(selectedCustomer.getCustomerName());
        Postal.setText(selectedCustomer.getPostalCode());
        Phone.setText(selectedCustomer.getPhone());
        // Does a query to find the first level division by division ID and passes that as the arg for set val
        int divID = selectedCustomer.getDivisionID();
        //divisionName returns object that also has countryID
        FirstLvlDivisions divisionName = AddressData.getDivisionName(divID);
        firstLevel.setValue(divisionName.getDivision());
        Country countryName = AddressData.getCountryName(divisionName.getCountryID());
        countryCombo.setValue(countryName.getCountry());


    }

    /** saves and returns to MainMenu and confirms users action with a dialog box
     * @param actionEvent button click of save button
     * @throws IOException while accessing information using streams, files and directories*/
    public void saveAddHandler(ActionEvent actionEvent) throws SQLException, IOException {
        if(customerChecks()){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Confirm Update");
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
    /** Cancels and returns to MainMenu and confirms users action with a dialog box
     * @param actionEvent button click of cancel button
     * @throws IOException thrown while accessing information using streams, files and directories*/
    public void cancelUpdateHandler(ActionEvent actionEvent) throws IOException {
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        countryComboInit();
    }


}
