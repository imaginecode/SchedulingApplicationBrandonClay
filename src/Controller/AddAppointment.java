package Controller;

import Data.ContactsData;
import Data.UserNamePassQuery;
import Model.Contact;
import Model.Customer;
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
import java.util.ResourceBundle;

public class AddAppointment implements Initializable{
    public ComboBox <String> contactCombo;
    public ComboBox <String> endTime;
    public ComboBox <String> startTime;
    public TextField title;
    public TextField Customer_ID;
    public TextField type;
    public TextField aptDescription;
    public TextField appointment_ID;
    public Button SaveAptAdd;
    public Button cancelAptAdd;
    public DatePicker aptStartDate;
    public ComboBox <String> customerCombo;
    public ComboBox <Integer> userIDCombo;
    public ComboBox <String> typeCombo;
    public ComboBox <String> aptLocationCombo;


    public void cancelAptAddHandler(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Parent scene = FXMLLoader.load(getClass().getResource("/View/MainScreen.fxml"));
        stage.setTitle("Home");
        stage.setScene(new Scene(scene));
        stage.show();
    }

    public void SaveAptAddHandler(ActionEvent actionEvent) {
        if (appointmentChecks()){

        }
        else{

        }


    }
/**Checks to make sure appointment is valid fields are not empty as well as checking for overlapping appointments,
 * valid dates, and that appointment is within business hours
 * @return boolean of t/f for if the appointment is valid if (f) then alerts are displayed*/
    private boolean appointmentChecks() {

        //Checking to make sure text fields aren't empty

        //Checking to make sure end date is after start date

        //Checking for overlapping appointments

        //Checks too see if appointment is with business hours defined as 8:00 a.m. to 10:00 p.m. EST, including weekends


        return true;
    }


    public void startTimeHandler(ActionEvent actionEvent) {
    }

    public void endTimeHandler(ActionEvent actionEvent) {
    }

    public void aptStartDateHandler(ActionEvent actionEvent) {

    }


    /**Populates type combo with default data*/
    public void aptTypeCombo() {
        ObservableList<String> aptTypes = FXCollections.observableArrayList();

        aptTypes.addAll("Sprint","Demo","Breakout","Kick off");
        typeCombo.setItems(aptTypes);
    }

    /**Populates location combo with default data*/
    public void aptLocationCombo(){
        ObservableList<String> aptLocations = FXCollections.observableArrayList();
        aptLocations.addAll("ConferenceRoom1", "ConferenceRoom2", "ConferenceRoom3");

        aptLocationCombo.setItems(aptLocations);
    }

    /**Populates type combo with data from query of all contacts*/
    public void contactCombo(){
        ObservableList<String> contactList = FXCollections.observableArrayList();
        try {

            if (ContactsData.getAllContacts() != null){
                for (Contact contact: ContactsData.getAllContacts()) {
                    contactList.add(contact.getContactName());
                }
            }
            contactCombo.setItems(contactList);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /** Populates all customer ID from query in combo box*/
    public void customerIDCombo(){

    }

//    public void dataHandoff(Customer selectedCustomer){
//        Customer_ID.setText(String.valueOf(selectedCustomer.getCustomerID()));
//    }

    /** Displays messages for add appointment errors such as empty fields
     * @param alertnum is for selecting which specific alert case is needed */
    public void addErrors(int alertnum) {
        Alert alert = new Alert(Alert.AlertType.ERROR);

        switch (alertnum) {
            case 1:
                alert.setTitle("All fields must be filled");
                alert.setHeaderText("All fields must be filled");
                alert.setContentText("All fields  and combo boxes must be filled selected");
                alert.showAndWait();
                break;
            case 2:
                alert.setTitle("Confirm Save");
                alert.setHeaderText("Confirm Save");
                alert.setContentText("Confirm Save");
                alert.showAndWait();
                break;
            case 3:
                alert.setTitle("Confirm Cancel");
                alert.setHeaderText("Confirm Cancel");
                alert.setContentText("Confirm Cancel");
                alert.showAndWait();
                break;





        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        aptTypeCombo();
        aptLocationCombo();
        contactCombo();
    }
}
