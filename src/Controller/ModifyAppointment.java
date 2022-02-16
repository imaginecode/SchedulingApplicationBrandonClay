package Controller;


import Data.AppointmentsData;
import Data.ContactsData;
import Data.CustomersData;
import Data.UserNamePassQuery;
import Model.Appointment;
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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.ResourceBundle;
/**
 * This class contains edits appointments to the database and performs checks to make sure it is a valid appointment before update query
 /** @author Brandon Clay */
public class ModifyAppointment implements Initializable {

    public Button cancelUpdate;
    public Button saveUpdate;
    public TextField appointment_ID;
    public TextField description;
    public DatePicker aptStartDate;
    public ComboBox<String> startTime;
    public ComboBox<String> endTime;
    public ComboBox<String> aptLocationCombo;
    public ComboBox contactCombo;
    public ComboBox<String> typeCombo;
    public ComboBox<Integer> customerCombo;
    public ComboBox<Integer> userIDCombo;
    public TextField title;
    // Recording what the initial times and dates are for testing later for appointment overlaps
    private String initialStart;
    private String initialEnd;
    private LocalDate intialDate;

    /** saves and returns to MainMenu and confirms users action with a dialog box
     * @param actionEvent button click of save button
     * @throws IOException while accessing information using streams, files and directories*/
    public void saveUpdateHandler(ActionEvent actionEvent) throws SQLException {
        LocalDateTime startAptTime = LocalDateTime.of(aptStartDate.getValue(), LocalTime.parse(startTime.getSelectionModel().getSelectedItem()));
        LocalDateTime endAptTime = LocalDateTime.of(aptStartDate.getValue(), LocalTime.parse(endTime.getSelectionModel().getSelectedItem()));
        Contact contactID = ContactsData.getContactByID((String) contactCombo.getSelectionModel().getSelectedItem());
        //ContactID to pass into new appointment insert query
        Integer contactToPass = contactID.getContactID();


        try{

            if (appointmentChecks()){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Confirm Save");
                Optional<ButtonType> result = alert.showAndWait();
                if(result.isPresent() && (result.get() == ButtonType.OK)) {
                    AppointmentsData.editAppointment(Integer.valueOf(appointment_ID.getText()),title.getText(), description.getText(), aptLocationCombo.getSelectionModel().getSelectedItem(),
                            typeCombo.getSelectionModel().getSelectedItem(), startAptTime, endAptTime, customerCombo.getSelectionModel().getSelectedItem(),
                            userIDCombo.getSelectionModel().getSelectedItem(), contactID.getContactID());

                    Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
                    Parent scene = FXMLLoader.load(getClass().getResource("/View/MainScreen.fxml"));
                    stage.setTitle("Home Screen");
                    stage.setScene(new Scene(scene));
                    stage.show();
                }
            }

        }
        catch(SQLException | IOException e){
            e.getStackTrace();
        }
    }
    /** Cancels and returns to MainMenu and confirms users action with a dialog box
     * @param actionEvent button click of cancel button
     * @throws IOException thrown while accessing information using streams, files and directories*/
    public void cancelUpdateHandler(ActionEvent actionEvent) throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Confirm Cancel");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && (result.get() == ButtonType.OK)) {

            Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            Parent scene = FXMLLoader.load(getClass().getResource("/View/MainScreen.fxml"));
            stage.setTitle("Home Screen");
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }

    /**Checks to make sure appointment is valid fields are not empty as well as checking for overlapping appointments,
     * valid dates and times
     * @return boolean of t/f for if the appointment is valid if (f) then alerts are displayed*/
    private boolean appointmentChecks() {

        //Checking to make sure text fields aren't empty
        // commented fields are not able to be empty due to data passed in automatically set to fields.
//        if(contactCombo.getSelectionModel().isEmpty()) {
//            addErrors(1);
//            return false;
//        }
//        if(endTime.getSelectionModel().isEmpty()) {
//            addErrors(1);
//            return false;
//        }
//        if(startTime.getSelectionModel().isEmpty()) {
//            addErrors(1);
//            return false;
//        }
        if(title.getText().isEmpty()) {
            addErrors(1);
            return false;
        }
        if(description.getText().isEmpty()) {
            addErrors(1);
            return false;
        }
        if(aptStartDate.getValue() == null) {
            addErrors(1);
            return false;
        }
//        if(customerCombo.getSelectionModel().isEmpty()) {
//            addErrors(1);
//            return false;
//        }
//        if(userIDCombo.getSelectionModel().isEmpty()) {
//            addErrors(1);
//            return false;
//        }
//        if(typeCombo.getSelectionModel().isEmpty()) {
//            addErrors(1);
//            return false;
//        }
//        if(aptLocationCombo.getSelectionModel().isEmpty()) {
//            addErrors(1);
//            return false;
//        }

        //Checking to make sure end time is after start time
        LocalTime start = LocalTime.parse(startTime.getSelectionModel().getSelectedItem());
        LocalTime end = LocalTime.parse(endTime.getSelectionModel().getSelectedItem());
        long diffBetweenAptTimes = ChronoUnit.MINUTES.between(end,start);
//        System.out.println(diffBetweenAptTimes);
        // Appointments are min 30 minutes and appointments can't start at the same time so difference must be less than 0 and !=
        if(diffBetweenAptTimes >= 31 || diffBetweenAptTimes == 0){
            addErrors(6);
            return false;
        }

        //Checks that appointment date is in the future
        if (aptStartDate.getValue().isBefore(LocalDateTime.now().toLocalDate())) {
            addErrors(5);
            return false;
        }


        //Checking for overlapping appointments. However, an appointment with the same existing time should not be considered overlap
        try{
            ObservableList<Appointment> existingAppointments = FXCollections.observableArrayList();
            existingAppointments = AppointmentsData.getAllAppointments();



            for(Appointment appointment : existingAppointments){
                LocalDate existingDate = appointment.getStart().toLocalDate();

                //Checking to see if the date has changed
                if(aptStartDate.getValue().isEqual(existingDate)){
                    LocalTime existingStart = appointment.getStart().toLocalTime();
                    LocalTime existingEnd = appointment.getEnd().toLocalTime();
                    LocalTime potentialStart = LocalTime.parse(startTime.getSelectionModel().getSelectedItem());
                    LocalTime potentialEnd = LocalTime.parse(endTime.getSelectionModel().getSelectedItem());



                    //Checking to see if appointment time has remained the same
                    if(initialStart.equals(potentialStart.toString()) & initialEnd.equals(potentialEnd.toString()) & intialDate.equals(aptStartDate.getValue())){
                        System.out.println("The initial times remain for the appointment. Appointment will be able to save.");

                    }

                    else if(!appointment_ID.getText().equals(String.valueOf(appointment.getAptID()))){


                            if(potentialStart.isBefore(existingStart) & potentialEnd.isAfter(existingEnd))
                            {
                                addErrors(7);
                                return false;
                            }
                            if(potentialStart.isBefore(existingEnd) & potentialEnd.isAfter(existingEnd)) {
                                addErrors(7);
                                return false;
                            }
                            if((potentialStart.isAfter(existingStart) & (potentialEnd.isBefore(existingEnd)))){
                                addErrors(7);
                                return false;
                            }
                    }




                }


            }



        }
        catch(SQLException e){
            e.printStackTrace();
            return false;
        }


        return true;
    }

    /** DataHandoff method takes selected item from appointments table view and sets fields of edit appointment with appropriate data
     * @param selectedAppointment appointment selected to edit*/
        public void dataHandoff(Appointment selectedAppointment){
        try {appointment_ID.setText(String.valueOf(selectedAppointment.getAptID()));
            description.setText(selectedAppointment.getAptDescription());
            aptStartDate.setValue(selectedAppointment.getEnd().toLocalDate());
            //Recording initial date so it can be compared on save to see if it was changed to avoid appointment overlaps
            intialDate = selectedAppointment.getStart().toLocalDate();
            startTime.setValue(String.valueOf(selectedAppointment.getStart().toLocalTime()));
            endTime.setValue(String.valueOf(selectedAppointment.getEnd().toLocalTime()));
            //Recording what the original appointment time was
            initialStart = selectedAppointment.getStart().toLocalTime().toString();
            initialEnd = selectedAppointment.getEnd().toLocalTime().toString();
            aptLocationCombo.setValue(selectedAppointment.getAptLocation());
            contactCombo.setValue(ContactsData.getContactNameByID(selectedAppointment.getContactID()).getContactName());
            typeCombo.setValue(selectedAppointment.getType());
            customerCombo.setValue(selectedAppointment.getCustomerID());
            userIDCombo.setValue(selectedAppointment.getUserID());
            title.setText(selectedAppointment.getAptTitle());

        }


        catch(SQLException e){
                e.getStackTrace();
            }

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

    /** Populates time combos in 30 min increments for both start and end combos. Start and end times are shown in local times that are between 8am-10pm EST
     *  to make it impossible for a user to schedule an appointment outside of business hours*/
    public void timeCombo(){
        ObservableList<String> aptSlotsStart = FXCollections.observableArrayList();
        ObservableList<String> aptSlotsEnd = FXCollections.observableArrayList();

        LocalTime one = LocalTime.now();
        ZoneId zone2 = ZoneId.of("America/New_York");
        LocalTime two = LocalTime.now(zone2);
// Finding the difference between local time and EST
        int diff = (int) ChronoUnit.HOURS.between(two, one);

        //Business starts  at 8am and then difference is added to convert  starttime from EST to local
        LocalTime Hour = LocalTime.of(8 + diff, 0);
        if(diff > 0) {
            diff = diff + 1;
        }


        int i = 0;
        aptSlotsStart.add(Hour.toString());
        while(i < 28){

            Hour = Hour.plusMinutes(30);
            if(i <27){
                aptSlotsStart.add(Hour.toString());
            }

            aptSlotsEnd.add(Hour.toString());
            ++i;

        }
        startTime.setItems(aptSlotsStart);

        endTime.setItems(aptSlotsEnd);
    }

    /** Displays messages for add appointment errors such as empty fields
     * @param alertnum is for selecting which specific alert case is needed */
    public void addErrors(int alertnum) {
        Alert alert = new Alert(Alert.AlertType.ERROR);

        switch (alertnum) {
            case 1:
                alert.setTitle("All fields must be filled");
                alert.setHeaderText("All fields must be filled");
                alert.setContentText("All fields  and combo boxes must be filled or and combo boxes must be selected with one of the given values. If editing a legacy appointment" +
                        " please double check to make sure that you have selected allowed  updated values for combo boxes");
                alert.showAndWait();
                break;
            case 2:
                alert.setAlertType(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirm Save");
                alert.setHeaderText("Confirm Save");
                alert.setContentText("Confirm Save");
                Optional<ButtonType> yesSave = alert.showAndWait();
                break;
            case 3:
                alert.setAlertType(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirm Cancel");
                alert.setHeaderText("Confirm Cancel");
                alert.setContentText("Confirm Cancel");
                alert.showAndWait();
                break;
            case 5:
                alert.setTitle("Date must be in the future");
                alert.setHeaderText("Date must be in the future");
                alert.setContentText("Date must be in the future as appointments can not be made in the past.");
                alert.showAndWait();
                break;
            case 6:
                alert.setTitle("End time can't be before Start Time ");
                alert.setHeaderText("End time can't be before Start Time");
                alert.setContentText("End time can't be before Start Time. Also start and end times can't be the same");
                alert.showAndWait();
                break;
            case 7:
                alert.setTitle("Appointment overlap");
                alert.setHeaderText("Appointment overlap");
                alert.setContentText("Appointment overlap, you can not schedule an appointment that overlaps with another customers appointment." +
                        " The appointment you overlap with is" );
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
