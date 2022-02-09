package Controller;

import Data.ContactsData;
import Data.CustomersData;
import Data.UserNamePassQuery;
import Model.Contact;
import Model.Customer;
import Model.User;
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
import java.time.*;
import java.util.ResourceBundle;
import java.util.TimeZone;

public class AddAppointment implements Initializable{
    public ComboBox<String> contactCombo;
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
    public ComboBox <Integer> customerCombo;
    public ComboBox <Integer> userIDCombo;
    public ComboBox <String> typeCombo;
    public ComboBox <String> aptLocationCombo;

/** Cancels and returns to MainMenu and confirms users action with a dialog box
 * @param actionEvent button click of cancel button
 * @throws IOException thrown while accessing information using streams, files and directories*/
    public void cancelAptAddHandler(ActionEvent actionEvent) throws IOException {

        addErrors(3);

        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Parent scene = FXMLLoader.load(getClass().getResource("/View/MainScreen.fxml"));
        stage.setTitle("Home");
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /** */
    public void SaveAptAddHandler(ActionEvent actionEvent) throws IOException {
        if (appointmentChecks()){
            Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            Parent scene = FXMLLoader.load(getClass().getResource("/View/MainScreen.fxml"));
            stage.setTitle("Home");
            stage.setScene(new Scene(scene));
            stage.show();
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
        ObservableList<Integer> customerIDList = FXCollections.observableArrayList();

        try{
            if (CustomersData.getAllCustomers() != null){
                for (Customer customer: CustomersData.getAllCustomers()) {
                    customerIDList.add(customer.getCustomerID());
                }
            }
            customerCombo.setItems(customerIDList);
        }

        catch (SQLException e){
        System.out.println(e.getStackTrace());
        }
    }

    /**Populates all user IDs in combo box from query of users */
    public void userIDCombo(){
        ObservableList<Integer> userList = FXCollections.observableArrayList();
        try {

            if (UserNamePassQuery.allUsers() != null){
                for (User user: UserNamePassQuery.allUsers()) {
                    userList.add(user.getUserID());
                }
            }
            userIDCombo.setItems(userList);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /** Populates time combos in 30 min increments for both start and end combos*/
    public void timeCombo(){
        ObservableList<String> aptSlots = FXCollections.observableArrayList();

        int now = LocalTime.now().getHour();
//        long diff = TimeZone.getTimeZone(ZoneId.systemDefault()).getOffset(now) - TimeZone.getTimeZone("EST").getOffset(now);
        int diff = TimeZone.getTimeZone(ZoneId.systemDefault()).getOffset(now) - TimeZone.getTimeZone("EST").getOffset(now);
        System.out.println(diff);

        LocalTime startHour = LocalTime.of(8 ,0);


        int i = 0;
        aptSlots.add(startHour.toString());
        while(i < 28){

            startHour = startHour.plusMinutes(30);
            aptSlots.add(startHour.toString());
            ++i;

            startTime.setItems(aptSlots);
        }

//        int zoneDifference = TimeZone.getTimeZone("EST").getOffset();
//        LocalTime bizHoursStart = LocalTime.of(+ 2);
//        ZonedDateTime zdtStart = ZonedDateTime.of();
//        //Opening is 1pm in UTC
//        int openingTime = ZonedDateTime.of();
//
//        // Closing time in UTC 4am next day
//        int closingTime =
//        LocalTime bizHoursEnd = LocalTime.of(closingTime)
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
                alert.setAlertType(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirm Save");
                alert.setHeaderText("Confirm Save");
                alert.setContentText("Confirm Save");
                alert.showAndWait();
                break;
            case 3:
                alert.setAlertType(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirm Cancel");
                alert.setHeaderText("Confirm Cancel");
                alert.setContentText("Confirm Cancel");
                alert.showAndWait();
                break;



        }
    }

/** Calls methods that set default data in combo boxes for creating new appointments
 * @param url resolve relative paths for the root object
 * @param resourceBundle resources used to localize the root object */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        aptTypeCombo();
        aptLocationCombo();
        contactCombo();
        customerIDCombo();
        userIDCombo();
        timeCombo();
    }
}
